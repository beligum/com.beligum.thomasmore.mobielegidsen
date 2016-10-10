base.plugin("mobielegidsen.route.map.Main", ["base.core.Class", "blocks.core.Broadcaster", "constants.mobielegidsen.core", "mobielegidsen.route.map.Store", function (Class, Broadcaster, MgConstants, Store)
{
    //-----CONSTANTS-----
    //the minimum distance to cover before any expand/fold action is performed
    var DRAWER_MINIMUM_DISTANCE = 100;

    //-----VARIABLES-----
    var Map = this;

    this.transitionEnd = null;

    //-----FUNCTIONS-----
    var _reinitUi = function ()
    {
        Store.mapWrapper = $('.map-wrapper');
        Store.mapContainer = $('.map');
        Store.mapBlock = Store.mapContainer.closest(Store.TAGS.join(','));
        Store.mapData = $('.'+MgConstants.MAP_DATA_WRAPPER_CLASS);

        //save the original (once) to be able to restore it later
        if (!Store.mapContainerClone) {
            Store.mapContainerClone = Store.mapContainer.clone();
        }

        Map.header = $('.header-wrapper');
        Map.drawer = $('.drawer');
        Map.drawerHeader = Map.drawer.find('.header');
        Map.drawerHeaderLink = Map.drawerHeader.find('a');
        Map.drawerClose = Map.header.find('.back a');
        Map.drawerBody = Map.drawer.find('.body');
    };
    var _clearMap = function ()
    {
        if (Store.map) {
            //don't know why, but the layers don't seem to re-appear when we don't remove them explicitly first
            Store.map.eachLayer(function (layer)
            {
                Store.map.removeLayer(layer);
            });

            Store.map.stopLocate();
            Store.map.clearAllEventListeners();

            Store.map.remove();
            Store.map = null;
            //this one is removed from the map in the loop above
            Store.locationMarker = null;
            Store.routingLayer = null;

            //found in the leafletjs docs: make sure you completely remove the div and recreate it to lose all cached references
            Store.mapContainer.remove();
            Store.mapWrapper.prepend(Store.mapContainerClone);
        }
    };
    var _reinitMap = function ()
    {
        //only needs to happen once?
        if (!Store.map) {

            //make sure all the UI variables are pointing to the last version
            //(especially important when opening/closing the sidebar because they're wrapped in a container)
            _reinitUi();

            Store.map = new L.Map(Store.mapContainer[0]);

            //load the tiles
            _restyleMap();

            Store.map.on('click', function (e)
            {
                //deselect the poi and hide the drawer
                _hideAndDeselect();

                //pass along some variables down the chain (especially to the admin side)
                e.originalEvent.data = {
                    map: Store.map,
                    event: e,
                    main: Map
                };
            });

            Store.map.on('locationfound', _onLocationFound);
            Store.map.on('locationerror', _onLocationError);
            Store.map.locate({
                //see http://leafletjs.com/reference.html#map-locate-options
                watch: true,
                //adjust the view to the detected location?
                setView: false,
                //use GPS technology
                enableHighAccuracy: true
            });

            Store.routingMarkerLayer = L.layerGroup([]).addTo(Store.map);
            Store.routingLayer = L.geoJson(null, {
                style: {
                    color: "#00cc33",
                    weight: 5,
                    opacity: 0.6
                }
            }).addTo(Store.map);

            //load the data
            _initRouteData();

            //set the viewport of the map
            _updateMap(false);

            _updateRoute();
        }
        //TODO not ok yet
        // else {
        //     //check if the map-block was deleted and destroy the data structure if it was
        //     if (!$.contains(document, Store.mapBlock[0])) {
        //         _clearMap();
        //         Store.clearRouteData();
        //     }
        // }
    };
    var _restyleMap = function ()
    {
        if (Store.map) {

            var newHeight = null;
            if (Store.mapBlock.hasAttribute(MgConstants.MAP_STYLE_FULLHEIGHT_ATTR)) {
                var body = $('html');
                var bodyBottom = body.position().top + body.outerHeight(true);
                var mapTop = Store.mapWrapper.offset().top;
                var mapBottom = mapTop + Store.mapWrapper.outerHeight(true);
                if (mapBottom < bodyBottom) {
                    newHeight = bodyBottom - mapTop;
                }
            }
            else {
                //little dirty trick to make the map block the height of it's closest row
                var row = Store.mapBlock.closest('.row');
                if (row.length) {
                    //when coming back from full height, first make the row shrink before calculating
                    Store.mapWrapper.outerHeight('auto');

                    newHeight = row.outerHeight(true);
                }
            }
            if (newHeight) {
                Store.mapWrapper.outerHeight(newHeight);
                Store.map.invalidateSize();
            }

            Store.map.eachLayer(function (layer)
            {
                if (layer instanceof L.TileLayer) {
                    Store.map.removeLayer(layer);
                }
            });

            // set up the layers
            var mapStyle = Store.MAP_STYLE_LAYERS[Store.mapBlock.attr(MgConstants.MAP_STYLE_TILES_ATTR)];
            if (!mapStyle) {
                mapStyle = Store.MAP_STYLE_LAYERS[Store.MAP_STYLE_LAYER_DEFAULT];
            }

            mapStyle.tileLayer.setZIndex(MgConstants.ROUTE_MAP_ZINDEX);
            Store.map.addLayer(mapStyle.tileLayer);
        }
    };
    var _updateMap = function (animated)
    {
        var centerLat = Store.routeData ? Store.routeData.center.lat : undefined;
        var centerLon = Store.routeData ? Store.routeData.center.lon : undefined;
        var centerZoom = Store.routeData ? Store.routeData.center.zoom : undefined;

        if (centerLat || centerLon || centerZoom) {
            var lat = centerLat || 0;
            var lon = centerLon || 0;
            var zoom = centerZoom || 0;

            Store.map.setView([lat, lon], zoom, {
                animate: animated ? true : false
            });
        }
        else {
            //Default to center of brussels
            //Store.map.setView([50.84667, 4.35472], 8);
            Store.map.fitWorld();
        }
    };
    /**
     * Re-sync the drawn route to the stored route object
     */
    var _updateRoute = function ()
    {
        var _this = this;

        Store.routingMarkers = [];
        Store.routingMarkerLayer.clearLayers();
        if (Store.routeData) {
            if (Store.routeData.points) {
                //this creates a nice clean closure context for each iteration (contrary to a regular for loop)
                Store.routeData.points.forEach(function(point) {

                    var loc = L.latLng(point.lat, point.lon);
                    var iconRef = Store.MAP_ICONS[point.icon];

                    var marker = L.marker(loc, {
                        icon: iconRef.icon,
                    });

                    //hide the shape markers by default
                    if (Store.isShapeMarker(marker)) {
                        marker.setOpacity(0.0);
                    }

                    marker.on('click', function (e)
                    {
                        var label = point.rdf ? point.rdf.label : null;
                        if (label) {
                            _showDrawer(point);
                        }
                    });

                    Store.routingMarkers.push(marker);
                    marker.addTo(Store.routingMarkerLayer);
                });
            }

            Store.routingLayer.clearLayers();
            if (Store.routeData.path) {
                Store.routingLayer.addData({
                    "type": "Feature",
                    "geometry": Store.routeData.path
                });
            }
        }
    };
    var _initRouteData = function(forceReload)
    {
        //load in the back-end data structure if it doesn't exist
        if (!Store.routeData || forceReload) {
            Store.routeData = Store.loadRouteData();
        }
    };
    var _deselectAllMarkers = function ()
    {
        //TODO
        // for (var m = 0; m < Map.allMarkers.length; m++) {
        //     Map.allMarkers[m].setIcon(Store.NUMBER_MARKER_ICONS[m + 1]);
        // }
    };
    var _showDrawer = function (point)
    {
        if (!Map.drawer.hasClass(MgConstants.MAP_DRAWER_CLASS_FOLDED) && !Map.drawer.hasClass(MgConstants.MAP_DRAWER_CLASS_EXPANDED)) {

            Map.drawerHeaderLink.html(point.rdf.label);

            //needed for good overflow handling
            Map.drawerBody.height(Store.mapWrapper.outerHeight() - Map.drawerHeader.outerHeight());

            Map.drawerBody.html('');
            Map.drawer.one(Map.transitionEnd, function ()
            {
                //The .load() method, unlike $.get(), allows us to specify a portion of the remote document to be inserted
                //Map.drawerBody.load(point.rdf.resourceUri + ' .container .row:not(\'mobielegidsen-header-route\')', _initDrawerBody);
                //Note: now done in css
                Map.drawerBody.load(point.rdf.resourceUri + ' .container', _initDrawerBody);
            });

            _foldDrawer();
        }
        else {
            //function will be called (once) on transition end
            Map.drawer.one(Map.transitionEnd, function ()
            {
                //when the drawer is closed, open it again with the new marker
                _showDrawer(point);
            });

            _hideDrawer();
        }
    };
    var _initDrawerBody = function ()
    {
        // Main.initMain();
        // Slider.initSliders();
        // Video.initVideos();
    };
    var _resetDrawerBody = function ()
    {
        if (Map.drawerBody.html() != '') {
            // Video.resetAllVideos();
            // //needed so we can reuse the id's
            // Video.destroyAllVideos();

            Map.drawerBody.html('');
        }
    };
    var _hideDrawer = function (e)
    {
        _resetDrawerBody();
        Map.drawer.removeClass(MgConstants.MAP_DRAWER_CLASS_EXPANDED);
        Map.drawer.removeClass(MgConstants.MAP_DRAWER_CLASS_FOLDED);
        _translateDrawer(0, Store.mapWrapper.offset().top + Store.mapWrapper.outerHeight());
    };
    var _moveDrawer = function (e)
    {
        if (e.type == 'panup' || e.type == 'pandown') {
            if (Map.drawer.hasClass(MgConstants.MAP_DRAWER_CLASS_FOLDED)) {
                var translateY = Store.mapWrapper.outerHeight() - Map.drawerHeader.outerHeight() - e.distance;
                if (translateY >= 0) {
                    _translateDrawer(0, translateY);
                }
                else {
                    _translateDrawer(0, 0);
                }
            }
            else if (Map.drawer.hasClass(MgConstants.MAP_DRAWER_CLASS_EXPANDED)) {
                if (e.deltaY > 0) {
                    _translateDrawer(0, e.distance);
                }
                else {
                    _translateDrawer(0, 0);
                }
            }
        }
        else if (e.type == 'panstart') {
            //disables easing between translations
            Map.drawer.addClass(MgConstants.MAP_DRAWER_CLASS_PANNING);
        }
        else if (e.type == 'panend' || e.type == 'tap') {

            //remove this class first to re-enable css animations
            Map.drawer.removeClass(MgConstants.MAP_DRAWER_CLASS_PANNING);

            if (Map.drawer.hasClass(MgConstants.MAP_DRAWER_CLASS_FOLDED)) {
                if (e.distance >= DRAWER_MINIMUM_DISTANCE || e.type == 'tap') {
                    _expandDrawer();
                }
                else {
                    _foldDrawer();
                }
            }
            else if (Map.drawer.hasClass(MgConstants.MAP_DRAWER_CLASS_EXPANDED)) {
                if (e.distance >= DRAWER_MINIMUM_DISTANCE || e.type == 'tap') {
                    _foldDrawer();
                }
                else {
                    _expandDrawer();
                }
            }
        }
    };
    var _foldDrawer = function ()
    {
        //pause the video when folding
        //Video.resetAllVideos();

        Map.drawer.one(Map.transitionEnd, function ()
        {
            Map.drawer.removeClass(MgConstants.MAP_DRAWER_CLASS_EXPANDED);
            Map.drawer.addClass(MgConstants.MAP_DRAWER_CLASS_FOLDED);
        });
        _translateDrawer(0, Store.mapWrapper.outerHeight() - Map.drawerHeader.outerHeight());

        Map.drawerClose.off('click', _foldDrawer);

        //disable the normal behavior of the back button
        return false;
    };
    var _expandDrawer = function ()
    {
        Map.drawer.one(Map.transitionEnd, function ()
        {
            Map.drawer.addClass(MgConstants.MAP_DRAWER_CLASS_EXPANDED);
            Map.drawer.removeClass(MgConstants.MAP_DRAWER_CLASS_FOLDED);
        });
        _translateDrawer(0, 0);

        //we'll re-use the back button of the main header as the close button for the drawer
        Map.drawerClose.on('click', _foldDrawer);
    };
    var _translateDrawer = function (x, y)
    {
        if (x == 0 && y == 0) {
            Map.drawer.css('transform', '');
        }
        else {
            Map.drawer.css('transform', 'translate(' + x + 'px, ' + y + 'px)');
        }
    };
    var _getDrawerTranslate = function ()
    {
        var retVal = parseFloat(Map.drawer.css('transform').split(',')[5]);
        //Note: this will be a NaN eg on tap...
        if (!retVal) {
            retVal = 0;
        }

        return retVal;
    };
    //on click outside drawer: close it
    var _hideAndDeselect = function (e)
    {
        //TODO
        // _deselectAllMarkers();
         _hideDrawer(e);
    };
    var _selectTransitionEvent = function ()
    {
        var t;
        var el = document.createElement('fakeelement');
        var transitions = {
            'transition': 'transitionend',
            'OTransition': 'oTransitionEnd',
            'MozTransition': 'transitionend',
            'WebkitTransition': 'webkitTransitionEnd'
        }

        for (t in transitions) {
            if (el.style[t] !== undefined) {
                return transitions[t];
            }
        }
    };
    var _onLocationFound = function (e)
    {
        if (!Store.locationMarker) {
            var radius = e.accuracy / 2;

            Store.locationMarker = L.marker(e.latlng, {icon: Store.MAP_ICONS[MgConstants.MAP_ICON_LOCATION_CURRENT].icon});
            Store.locationMarker.addTo(Store.map);
            //.bindPopup("You are within " + radius + " meters from this point").openPopup();
            //Note: openPopup() changes view to the current location (despite 'setView: false'), let's not do that
            Store.locationMarker.bindPopup("You are here")/*.openPopup()*/;

            // L.circle(e.latlng, radius).addTo(map);
        }
        else {
            Store.locationMarker.setLatLng(e.latlng);
        }
    };
    var _onLocationError = function (e)
    {
        Logger.error(e.message);
        //alert("Could not determine your current location.");
    };

    //-----MAIN INIT-----
    var _init = function ()
    {
        _reinitMap();

        Map.transitionEnd = L.DomUtil.TRANSITION ? L.DomUtil.TRANSITION_END : false;
        //Map.transitionEnd = _selectTransitionEvent();

        //drawer touch init
        var mtController = new Hammer(Map.drawerHeader[0], {
            //touchAction: 'pan-y',
            preventDefault: true
        });

        // by default, it only adds horizontal recognizers
        // let the pan gesture support all directions.
        mtController.get('pan').set({direction: Hammer.DIRECTION_VERTICAL});

        // this will block the vertical scrolling on a touch-device while on the element
        // listen to events...
        mtController.on("panstart panup pandown panend tap press", _moveDrawer);

        _hideDrawer();
        //activate the drawer now it's in its place
        Map.drawer.show();
    };

    $(document).on(Broadcaster.EVENTS.PRE_START_BLOCKS, function (event)
    {
        _clearMap();
        _hideAndDeselect();
    });
    $(document).on(Broadcaster.EVENTS.START_BLOCKS, function (event)
    {
        _reinitMap();
    });
    $(document).on(Broadcaster.EVENTS.PRE_STOP_BLOCKS, function (event)
    {
        _clearMap();
    });
    $(document).on(Broadcaster.EVENTS.STOP_BLOCKS, function (event)
    {
        _reinitMap();
    });
    //TODO doesn't work as it should, yet
    // $(document).on(Broadcaster.EVENTS.DOM_CHANGED, function (event)
    // {
    //     _reinitMap();
    //     _restyleMap();
    // });
    $(document).on(Store.EVENTS.REFRESH_MAP, function (event)
    {
        _updateMap(true);
    });
    $(document).on(Store.EVENTS.RESTYLE_MAP, function (event)
    {
        _restyleMap();
    });

    _init();

}]);
