base.plugin("mobielegidsen.route.map.Admin", ["base.core.Class", "base.core.Commons", "blocks.core.Notification", "blocks.imports.Block", "constants.blocks.core", "messages.blocks.core", "blocks.core.Sidebar", "constants.mobielegidsen.core", "messages.mobielegidsen.core", "blocks.core.Broadcaster", "mobielegidsen.route.map.Store", function (Class, Commons, Notification, Block, BlocksConstants, BlocksMessages, Sidebar, MgConstants, MgMessages, Broadcaster, Store)
{
    //-----CONSTANTS-----
    this.TAGS = Store.TAGS;

    //-----VARIABLES-----
    var MapAdmin = this;

    this.latInput;
    this.lonInput;
    this.zoomSlider;
    this.router;

    //-----FUNCTIONS-----

    //-----PRIVATE FUNCTIONS-----

    //-----MAIN INIT-----

    (this.Class = Class.create(Block.Class, {

        //-----VARIABLES-----

        //-----CONSTRUCTORS-----
        constructor: function ()
        {
            MapAdmin.Class.Super.call(this);
        },

        //-----IMPLEMENTED METHODS-----
        init: function ()
        {
        },
        focus: function (block, element, hotspot, event)
        {
            MapAdmin.Class.Super.prototype.focus.call(this, block, element, hotspot, event);

            //register ourself to receive map clicks
            var _this = this;
            element.click(function (e)
            {
                if (e.originalEvent && e.originalEvent.data) {
                    var map = e.originalEvent.data.map;
                    var mapEvent = e.originalEvent.data.event;
                    var main = e.originalEvent.data.main;

                    _this._mapClicked(e, map, mapEvent, main);
                }
            });

            this._updateAllMarkerEvents(this);
        },
        blur: function (block, element)
        {
            MapAdmin.Class.Super.prototype.blur.call(this, block, element);

            this._resetEditControls(element);
        },
        getConfigs: function (block, element)
        {
            var _this = this;

            var retVal = MapAdmin.Class.Super.prototype.getConfigs.call(this, block, element);

            retVal.push($('<hr>'));

            MapAdmin.latInput = this.createTextInput(Sidebar,
                function getterFunction()
                {
                    return Store.routeData.center.lat;
                },
                function setterFunction(val)
                {
                    Store.routeData.center.lat = parseFloat(val);
                    Store.writeRouteData();

                    Broadcaster.send(Store.EVENTS.REFRESH_MAP);
                },
                MgMessages.widgetMapLocationCenter, MgMessages.widgetMapCenterLat, false, {}
            );
            retVal.push(MapAdmin.latInput);


            MapAdmin.lonInput = this.createTextInput(Sidebar,
                function getterFunction()
                {
                    return Store.routeData.center.lon;
                },
                function setterFunction(val)
                {
                    Store.routeData.center.lon = parseFloat(val);
                    Store.writeRouteData();

                    Broadcaster.send(Store.EVENTS.REFRESH_MAP);
                },
                '', MgMessages.widgetMapCenterLon, false, {}
            );
            retVal.push(MapAdmin.lonInput);


            MapAdmin.zoomSlider = this.addSliderClass(Sidebar, block.element, MgMessages.widgetMapCenterZoom, Store.ZOOM_VALUES, false,
                function (oldValue, newValue)
                {
                    //Note: this will also save the zoom level in the class of the element
                    Store.routeData.center.zoom = parseInt(newValue);
                    Store.writeRouteData();

                    Broadcaster.send(Store.EVENTS.REFRESH_MAP);
                }
                , null);
            retVal.push(MapAdmin.zoomSlider);


            var mapCenterActions = $("<ul/>").addClass(BlocksConstants.BLOCK_ACTIONS_CLASS);

            var mapCenterGotoAction = $("<li><label>" + MgMessages.widgetMapCenterGoto + "</label></li>").appendTo(mapCenterActions);
            var mapCenterGotoButton = $("<a class='btn btn-default btn-sm pull-right'><i class='fa fa-fw fa-bullseye'></i></a>").appendTo(mapCenterGotoAction);
            mapCenterGotoButton.click(function ()
            {
                Broadcaster.send(Store.EVENTS.REFRESH_MAP);
            });

            var mapCenterSelectAction = $("<li><label>" + MgMessages.widgetMapCenterSelect + "</label></li>").appendTo(mapCenterActions);
            var mapCenterSelectButton = $("<a class='btn btn-default btn-sm pull-right'><i class='fa fa-fw fa-crosshairs'></i></a>").appendTo(mapCenterSelectAction);
            mapCenterSelectButton.click(function ()
            {
                if (Store.map) {
                    _this._setMapCenterValues(element, Store.map.getCenter().lat, Store.map.getCenter().lng, Store.map.getZoom());
                }

                //OLD code: uses a crosshair to precisely set the center of the map
                // //must come first to net mess with the click handlers
                // Notification.info(MgMessages.widgetMapCenterSelectInfo);
                //
                // Store.mode = Store.MODE_SELECT;
                //
                // element.hover(
                //     function (e)
                //     {
                //         element.addClass(MgConstants.MAP_SELECT_MODE_CLASS);
                //     },
                //     function (e)
                //     {
                //         element.removeClass(MgConstants.MAP_SELECT_MODE_CLASS);
                //     }
                // );
                // element.click(function (e)
                // {
                //     //these are set on the map side
                //     var map = e.originalEvent.data.map;
                //     var mapEvent = e.originalEvent.data.event;
                //     _this._setMapCenterValues(element, mapEvent.latlng.lat, mapEvent.latlng.lng, map.getZoom());
                //
                //     element.removeClass(MgConstants.MAP_SELECT_MODE_CLASS);
                //     //remove the both from hover() and ourself
                //     element.unbind('mouseenter mouseleave click');
                // });
            });

            retVal.push(mapCenterActions);

            retVal.push($('<hr>'));

            var styleValues = [];
            $.each(Store.MAP_STYLE_LAYERS, function (key, value)
            {
                styleValues.push({
                    name: value.name,
                    value: key
                });
            });
            //set a default map style if none is set so we always have a visual
            if (!block.element.attr(MgConstants.MAP_STYLE_TILES_ATTR) && styleValues.length > 0) {
                //note: this will trigger a change below
                block.element.attr(MgConstants.MAP_STYLE_TILES_ATTR, Store.MAP_STYLE_LAYER_DEFAULT);
            }
            var styleCombo = this.addUniqueAttributeValue(Sidebar, block.element, MgMessages.widgetMapTileStyle, MgConstants.MAP_STYLE_TILES_ATTR, styleValues,
                function (oldValue, newValue)
                {
                    Broadcaster.send(Store.EVENTS.RESTYLE_MAP);
                }
            );
            retVal.push(styleCombo);

            retVal.push(this.addOptionalClass(Sidebar, block.element, MgMessages.widgetMapFullheight, null,
                function (oldValue, newValue)
                {
                    Broadcaster.send(Store.EVENTS.RESTYLE_MAP);
                },
                MgConstants.MAP_STYLE_FULLHEIGHT_ATTR));

            retVal.push(this._createEditModeToggle(element));

            //we'll add a dummy container for now, which will be filled/cleared on selection
            retVal.push($('<div class="' + MgConstants.MAP_EDIT_TOGGLE_SELECTION_CLASS + '"></div>'));

            return retVal;
        },
        getWindowName: function ()
        {
            return MgMessages.widgetMapTitle;
        },

        //-----PRIVATE METHODS-----
        _createEditModeToggle: function (element)
        {
            var _this = this;

            var editModeWrapper = $('<div class="' + MgConstants.MAP_EDIT_TOGGLE_CLASS + '"></div>');
            var editModeRadio = this._createEditModeRadio();
            var editModeToggle = this.createToggleButton(MgMessages.widgetMapModeToggleLabel,
                function initStateCallback()
                {
                    //start out with the toggle in off mode
                    editModeRadio.hide();
                    return false;
                },
                function switchStateCallback(oldState, newState)
                {
                    var radioSelector = 'input:radio:not(:disabled)';

                    //activate edit mode
                    if (newState) {
                        editModeRadio.show();

                        //we need to 'click' to trigger a change event
                        editModeRadio.find(radioSelector).first().click();

                        //register all poins on the map to receive clicks too
                        _this._updateAllMarkerEvents(_this);
                    }
                    //deactivate edit mode
                    else {
                        //make sure we deselect everything, so the re-activation triggers a change
                        editModeRadio.find(radioSelector).attr('checked', false);
                        editModeRadio.hide();

                        //reset the marker handling
                        Store.mode = Store.MODE_VIEW;
                        _this._updateAllMarkerEvents(_this);
                    }
                },
                BlocksMessages.toggleLabelYes,
                BlocksMessages.toggleLabelNo
            );

            editModeWrapper.append(editModeToggle);
            editModeWrapper.append(editModeRadio);

            return editModeWrapper;
        },
        _updateSelectionConfig: function(_this, element)
        {
            var selectionWrapper = $('.'+MgConstants.MAP_EDIT_TOGGLE_SELECTION_CLASS);

            selectionWrapper.html('');

            //if we have a selection, rebuild the input
            if (Store.selectedMarker) {
                selectionWrapper.append($('<hr>'));

                var options = {};
                options[BlocksConstants.INPUT_TYPE_CONFIG_RESOURCE_AC_ENDPOINT] = MgConstants.MAP_RDF_TERM_POI_CONFIG_acEndpoint;
                options[BlocksConstants.INPUT_TYPE_CONFIG_RESOURCE_VAL_ENDPOINT] = MgConstants.MAP_RDF_TERM_POI_CONFIG_valEndpoint;
                options[BlocksConstants.INPUT_TYPE_CONFIG_RESOURCE_MAXRESULTS] = MgConstants.MAP_RDF_TERM_POI_CONFIG_maxResults;

                var selectedPoint = _this._getSelectedPoint();
                var initialValue = undefined;
                if (selectedPoint && selectedPoint.rdf) {
                    initialValue = selectedPoint.rdf.resourceUri;
                }

                var editModePoiSelector = this.createAutocompleteWidget(element, null, null, options, MgMessages.widgetMapPoiAcLabel, initialValue,
                    //Note: this function receives the entire object as it was returned from the server endpoint (class AutocompleteSuggestion)
                    function setterFunction(propElement, newValue)
                    {
                        //Note: this method is also called during initialization
                        if (newValue) {
                            if (Store.selectedMarker) {
                                var selectedPoint = _this._getSelectedPoint();
                                if (!selectedPoint) {
                                    Logger.error("Couldn't find point in back-end data store for selected marker; this shouldn't happen");
                                }
                                else {
                                    if (newValue) {
                                        selectedPoint.rdf = newValue;
                                    }
                                    else {
                                        selectedPoint.rdf = null;
                                    }

                                    Store.writeRouteData();
                                }
                            }
                            else {
                                //NOOP: this shouldn't be possible, right?
                                Logger.error("No POI selected during autocomplete callback; this shouldn't happen");
                            }
                        }
                    });

                selectionWrapper.append(editModePoiSelector);
            }
        },
        _getSelectedPoint: function()
        {
            //go and search for the selected marker in the back-end data store
            //Note: this works because the data store is updated in _routingPathChanged() after each modification
            var retVal = null;
            for (var i = 0; i < Store.routeData.points.length; i++) {
                var point = Store.routeData.points[i];
                if (point.lat == Store.selectedMarker.getLatLng().lat && point.lon == Store.selectedMarker.getLatLng().lng) {
                    retVal = point;
                    break;
                }
            }

            return retVal;
        },
        _updateAllMarkerEvents: function (_this)
        {
            if (Store.routingMarkerLayer) {
                Store.routingMarkerLayer.eachLayer(function (marker)
                {
                    _this._updateMarkerEvents(_this, marker);
                });
            }
        },
        _updateMarkerEvents: function (_this, marker)
        {
            marker.clearAllEventListeners();
            marker.on('click', function (e)
            {
                _this._markerClicked(_this, e.target);
            });

            if (Store.mode==Store.MODE_VIEW) {

                marker.options.draggable = false;
                if (marker.dragging) {
                    marker.dragging.disable();
                }

                //hide the shape nodes when not editing
                if (Store.isShapeMarker(marker)) {
                    marker.setOpacity(0.0);
                }
            }
            else {
                //Note that after the dragend, the click is called (setting the selection, which is ok)
                marker.on('dragend', function (e)
                {
                    _this._markerDragged(_this, e.target);
                });

                marker.options.draggable = true;
                if (marker.dragging) {
                    marker.dragging.enable();
                }

                if (Store.isShapeMarker(marker)) {
                    marker.setOpacity(1.0);
                }
            }
        },
        _resetEditControls: function (element)
        {
            var _this = this;

            Store.selectedMarker = null;
            this._routingSelectionChanged(_this);

            Store.mode = Store.MODE_VIEW;

            //unregister the map clicks
            element.unbind('click');

            //make sure all the click listeners of the markers are gone
            _this._updateAllMarkerEvents(_this);
        },
        _mapClicked: function (e, map, mapEvent, main)
        {
            var _this = this;

            if (Store.mode == Store.MODE_CREATE || Store.mode == Store.MODE_SHAPE) {

                //Note: large parts of this code is duplicated below during deserialization

                var icon = Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_POI].icon;
                if (Store.mode == Store.MODE_SHAPE) {
                    icon = Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_NODE].icon;
                }

                var marker = L.marker(mapEvent.latlng, {
                    icon: icon,
                    draggable: true,
                });

                _this._updateMarkerEvents(_this, marker);

                //if nothing specific is selected, just add the marker at the end
                if (Store.selectedMarker == null) {
                    Store.routingMarkers.push(marker);
                }
                //otherwise, insert it after the selection
                else {
                    var selectedIdx = Store.routingMarkers.indexOf(Store.selectedMarker);
                    if (selectedIdx == Store.routingMarkers.length - 1) {
                        Store.routingMarkers.push(marker);
                    }
                    else {
                        Store.routingMarkers.splice(selectedIdx + 1, 0, marker);
                    }
                }

                marker.addTo(Store.routingMarkerLayer);
                Store.selectedMarker = marker;
                _this._routingSelectionChanged(_this);

                _this._routingPathChanged();
            }
            else {
                //just a click on the map in view mode: deselect a marker if one is selected
                if (Store.selectedMarker) {
                    Store.selectedMarker = null;
                    _this._routingSelectionChanged(_this);
                }
            }
        },
        _markerClicked: function (_this, marker)
        {
            if (Store.mode == Store.MODE_DELETE) {
                var idx = Store.routingMarkers.indexOf(marker);
                if (idx >= 0) {
                    Store.routingMarkers.splice(idx, 1);
                }
                Store.routingMarkerLayer.removeLayer(marker);

                if (Store.selectedMarker == marker) {
                    Store.selectedMarker = null;
                }

                _this._routingSelectionChanged(_this);
                _this._routingPathChanged();
            }
            else {
                Store.selectedMarker = marker;
                _this._routingSelectionChanged(_this);
            }
        },
        _markerDragged: function (_this, marker)
        {
            _this._routingPathChanged();
        },
        _routingSelectionChanged: function (_this)
        {
            for (var i = 0; i < Store.routingMarkers.length; i++) {
                var marker = Store.routingMarkers[i];

                if (marker == Store.selectedMarker) {
                    if (Store.isRoutingMarker(marker)) {
                        marker.setIcon(Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_POI_SELECTED].icon);
                    }
                    else if (Store.isShapeMarker(marker)) {
                        marker.setIcon(Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_NODE_SELECTED].icon);
                    }

                    selectedMarker = marker;
                }
                else {
                    if (Store.isRoutingMarker(marker)) {
                        marker.setIcon(Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_POI].icon);
                    }
                    else if (Store.isShapeMarker(marker)) {
                        marker.setIcon(Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_NODE].icon);
                    }
                }
            }

            //toggle the POI autocomplete searcher
            this._updateSelectionConfig(_this, Store.mapBlock);

        },
        _routingPathChanged: function ()
        {
            var _this = this;

            //make sure we have a router
            this._initRouter();

            this.router.clearPoints();

            //a two dimensional array that will hold the route points and will be serialized later on
            var pointsStore = [];
            for (var i = 0; i < Store.routingMarkers.length; i++) {
                var marker = Store.routingMarkers[i];
                this.router.addPoint(new GHInput(marker.getLatLng().lat, marker.getLatLng().lng));

                //Note: don't save selected icons
                var icon = marker.options.icon.options.name;
                if (marker.options.icon.options.isSelected) {
                    icon = marker.options.icon.options.reversedSelection;
                }
                pointsStore.push({
                    lat: marker.getLatLng().lat,
                    lon: marker.getLatLng().lng,
                    icon: icon
                });
            }

            //first clear the path, then rebuild it of we have at least 2 nodes
            Store.routingLayer.clearLayers();
            if (this.router.points.length > 1) {
                this.router.doRequest(function (json)
                {
                    if (json.message) {
                        Logger.error(json);

                        var str = "An error occured: " + json.message;
                        if (json.hints) {
                            str += json.hints;
                        }
                        alert(str);
                    }
                    else {
                        var path = json.paths[0];

                        //sometimes, in admin mode, a second request has already cleared the layer,
                        // if that happens, skip the processing of this (= previous request) and wait for the next to arrive
                        if (Store.routingLayer) {
                            Store.routingLayer.addData({
                                "type": "Feature",
                                "geometry": path.points
                            });

                            //the individual POIs
                            Store.routeData.points = pointsStore;
                            //distance in meters
                            Store.routeData.distance = path.distance;
                            //travel time in millis
                            Store.routeData.time = path.time;
                            //the detailed path of the route-line
                            Store.routeData.path = path.points;

                            Store.writeRouteData();
                        }
                    }
                });
            }
            else {
                //the individual POIs
                Store.routeData.points = pointsStore;
                //clear the path cause we might be deleting
                Store.routeData.distance = null;
                Store.routeData.time = null;
                Store.routeData.path = null;

                Store.writeRouteData();
            }
        },
        _initRouter: function ()
        {
            if (!this.router) {
                // create a routing client to fetch real routes, elevation.true is only supported for vehicle bike or foot
                this.router = new GraphHopperRouting({
                    key: MgConstants.MAP_ROUTER_KEY,
                    host: MgConstants.MAP_ROUTER_ENDPOINT,
                    vehicle: MgConstants.MAP_ROUTER_VEHICLE,
                    elevation: false,
                    instructions: false,
                    type: 'json'
                });
            }
        },
        _createEditModeRadio: function ()
        {
            var values = [
                {
                    label: MgMessages.widgetMapModeCreate,
                    value: Store.MODE_CREATE,
                },
                {
                    label: MgMessages.widgetMapModeShape,
                    value: Store.MODE_SHAPE,
                },
                {
                    label: MgMessages.widgetMapModeDelete,
                    value: Store.MODE_DELETE,
                }
            ];

            return this.addRadioAttribute(MgMessages.widgetMapModeLabel, null, values, null,
                function changeCallback(newValue)
                {
                    Store.mode = newValue;
                });
        },
        _setMapCenterValues: function (element, lat, lon, zoom)
        {
            MapAdmin.latInput.find('input[type=text]').val(''+lat);
            Store.routeData.center.lat = parseFloat(lat);

            MapAdmin.lonInput.find('input[type=text]').val(''+lon);
            Store.routeData.center.lon = parseFloat(lon);

            //sliders work with indexes, so find the index for this value
            var zoomIdx = -1;
            //we stringify the int values, see above
            var zoomStr = '' + zoom;
            for (var i = 0; i < Store.ZOOM_VALUES.length; i++) {
                var val = Store.ZOOM_VALUES[i];
                if (val.value == zoomStr) {
                    zoomIdx = i;
                    break;
                }
            }
            if (zoomIdx >= 0) {
                var zoomSlider = MapAdmin.zoomSlider.find('input[type=range]');
                zoomSlider.val('' + zoomIdx);
                //this will trigger the attribute change and the map callback
                zoomSlider.trigger('change');

                Store.routeData.center.zoom = parseInt(zoom);
            }

            Store.writeRouteData();
        },

    })).register(this.TAGS);

}]);
