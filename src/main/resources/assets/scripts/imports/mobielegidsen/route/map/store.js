base.plugin("mobielegidsen.route.map.Store", ["base.core.Class", "messages.blocks.core", "constants.mobielegidsen.core", "messages.mobielegidsen.core", "blocks.core.Broadcaster", "blocks.core.Notification", function (Class, BlocksMessages, MgConstants, MgMessages, Broadcaster, Notification)
{
    var Store = this;

    //-----CONSTANTS-----
    this.TAGS = ["mobielegidsen-route-map"];

    this.EVENTS = {
        REFRESH_MAP: "REFRESH_MAP",
        RESTYLE_MAP: "RESTYLE_MAP",
    };

    this.MODE_VIEW = 0;
    this.MODE_SELECT = 1;
    this.MODE_CREATE = 2;
    this.MODE_SHAPE = 3;
    this.MODE_DELETE = 4;

    this.ZOOM_VALUES = [];
    for (var i = 1; i <= 19; i++) {
        var val = '' + i;
        this.ZOOM_VALUES.push({
            value: val,
            name: val
        });
    }

    // create the tile layer with correct attribution
    // Good site: http://mc.bbbike.org/mc/?num=2&mt0=mapnik&mt1=lyrk-retina
    // see http://mc.bbbike.org/mc/js/mc.js?version=1471115029
    this.MAP_STYLE_LAYER_DEFAULT = MgConstants.MAP_STYLE_TILES_MAPNIK;
    this.MAP_STYLE_LAYERS = {};
    var DEFAULT_ATTRIBUTION = '&copy; <a href="http://openstreetmap.org/copyright">OpenStreetMap</a>';
    // this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_HOT] = {
    //     name: 'Hot',
    //     tileLayer: L.tileLayer('http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
    //         attribution: DEFAULT_ATTRIBUTION,
    //         subdomains: ['a', 'b']
    //     })
    // };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_MAPNIK] = {
        name: 'Mapnik',
        tileLayer: L.tileLayer("http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
            attribution: DEFAULT_ATTRIBUTION,
            subdomains: ['a', 'b', 'c']
        })
    };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_ROADS] = {
        name: 'Roads',
        tileLayer: L.tileLayer('http://korona.geog.uni-heidelberg.de/tiles/roads/x={x}&y={y}&z={z}', {
            attribution: DEFAULT_ATTRIBUTION
        })
    };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_MAPQUEST] = {
        name: 'Mapquest',
        tileLayer: (window['MQ'] ? MQ.mapLayer() : null)
    };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_MAPTOOLKIT] = {
        name: 'Maptoolkit',
        tileLayer: L.tileLayer("https://{s}.maptoolkit.net/terrain/{z}/{x}/{y}.png", {
            attribution: DEFAULT_ATTRIBUTION,
            subdomains: ['tile1', 'tile2', 'tile3', 'tile4']
        })
    };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_SKOBBLER] = {
        name: 'Skobbler',
        tileLayer: L.tileLayer("http://tiles{s}.skobbler.net/osm_tiles2/{z}/{x}/{y}.png", {
            attribution: DEFAULT_ATTRIBUTION,
            subdomains: ['1', '2', '3', '4'],
            maxZoom: 18
        })
    };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_SPUTNIK] = {
        name: 'Sputnik',
        tileLayer: L.tileLayer("http://{s}.tiles.maps.sputnik.ru/tiles/kmt2/{z}/{x}/{y}.png", {
            attribution: DEFAULT_ATTRIBUTION,
            subdomains: ['a', 'b', 'c', 'd']
        })
    };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_WAZE] = {
        name: 'Waze',
        tileLayer: L.tileLayer("https://{s}.waze.com/tiles/{z}/{x}/{y}.png", {
            attribution: DEFAULT_ATTRIBUTION,
            subdomains: ['worldtiles1', 'worldtiles2', 'worldtiles3', 'worldtiles4']
        })
    };
    this.MAP_STYLE_LAYERS[MgConstants.MAP_STYLE_TILES_MAPBOX] = {
        name: 'Mapbox',
        tileLayer: L.tileLayer("https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=" + MgConstants.MAP_STYLE_TILES_MAPBOX_KEY, {
            attribution: DEFAULT_ATTRIBUTION,
            id: 'mapbox.streets'
        })
    };

    //markers
    this.MAP_ICONS = {};
    this.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_POI] = {
        name: MgConstants.MAP_ICON_ROUTE_POI,
        icon: L.icon({
            iconUrl: '/assets/images/app/markers/red/marker-icon.png',
            iconRetinaUrl: '/assets/images/app/markers/red/marker-icon-2x.png',
            //shadowUrl: '/assets/images/app/markers/red/marker-shadow.png',
            //shadowRetinaUrl: '/assets/images/app/markers/red/marker-shadow-2x.png',
            iconSize: [50, 70],
            iconAnchor: [24, 53],
            //shadowSize: [47, 27],
            //shadowAnchor: [6, 21],
            popupAnchor: [1, -70],

            //this is not an offical option but will help us a lot during reverse lookup
            name: MgConstants.MAP_ICON_ROUTE_POI,
            isSelected: false,
            reversedSelection: MgConstants.MAP_ICON_ROUTE_POI_SELECTED,
        })
    };
    this.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_POI_SELECTED] = {
        name: MgConstants.MAP_ICON_ROUTE_POI_SELECTED,
        icon: L.icon({
            iconUrl: '/assets/images/app/markers/red/marker-icon_selected.png',
            iconRetinaUrl: '/assets/images/app/markers/red/marker-icon-2x_selected.png',
            //shadowUrl: '/assets/images/app/markers/red/marker-shadow.png',
            //shadowRetinaUrl: '/assets/images/app/markers/red/marker-shadow-2x.png',
            iconSize: [50, 70],
            iconAnchor: [24, 53],
            //shadowSize: [47, 27],
            //shadowAnchor: [6, 21],
            popupAnchor: [1, -70],

            //this is not an offical option but will help us a lot during reverse lookup
            name: MgConstants.MAP_ICON_ROUTE_POI_SELECTED,
            isSelected: true,
            reversedSelection: MgConstants.MAP_ICON_ROUTE_POI,
        })
    };
    this.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_NODE] = {
        name: MgConstants.MAP_ICON_ROUTE_NODE,
        icon: L.icon({
            iconUrl: '/assets/images/app/markers/node/marker-icon.png',
            iconRetinaUrl: '/assets/images/app/markers/node/marker-icon-2x.png',
            iconSize: [17, 17],
            iconAnchor: [8, 8],
            popupAnchor: [1, -34],

            //this is not an offical option but will help us a lot during reverse lookup
            name: MgConstants.MAP_ICON_ROUTE_NODE,
            isSelected: false,
            reversedSelection: MgConstants.MAP_ICON_ROUTE_NODE_SELECTED,
        })
    };
    this.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_NODE_SELECTED] = {
        name: MgConstants.MAP_ICON_ROUTE_NODE_SELECTED,
        icon: L.icon({
            iconUrl: '/assets/images/app/markers/node/marker-icon_selected.png',
            iconRetinaUrl: '/assets/images/app/markers/node/marker-icon-2x_selected.png',
            iconSize: [17, 17],
            iconAnchor: [8, 8],
            popupAnchor: [1, -34],

            //this is not an offical option but will help us a lot during reverse lookup
            name: MgConstants.MAP_ICON_ROUTE_NODE_SELECTED,
            isSelected: true,
            reversedSelection: MgConstants.MAP_ICON_ROUTE_NODE,
        })
    };
    this.MAP_ICONS[MgConstants.MAP_ICON_LOCATION_CURRENT] = {
        name: MgConstants.MAP_ICON_LOCATION_CURRENT,
        icon: L.icon({
            iconUrl: '/assets/images/app/markers/crosshair/marker-icon.png',
            iconRetinaUrl: '/assets/images/app/markers/red/marker-icon-2x.png',
            //shadowUrl: '/assets/images/app/markers/red/marker-shadow.png',
            //shadowRetinaUrl: '/assets/images/app/markers/red/marker-shadow-2x.png',
            iconSize: [39, 39],
            iconAnchor: [19, 19],
            //shadowSize: [47, 27],
            //shadowAnchor: [6, 21],
            popupAnchor: [1, -28],

            //this is not an offical option but will help us a lot during reverse lookup
            name: MgConstants.MAP_ICON_LOCATION_CURRENT,
            isSelected: false,
            reversedSelection: MgConstants.MAP_ICON_LOCATION_CURRENT,
        })
    };

    this.NUMBER_MARKER_ICONS = {};
    for (var i = 1; i <= 13; i++) {
        this.NUMBER_MARKER_ICONS[i] = L.icon({
            iconUrl: '/assets/images/app/markers/' + i + '/marker-icon.png',
            iconRetinaUrl: '/assets/images/app/markers/' + i + '/marker-icon-2x.png',
            shadowUrl: '/assets/images/app/markers/' + i + '/marker-shadow.png',
            iconSize: [25, 41],
            iconAnchor: [12, 41],
            popupAnchor: [1, -34],
            shadowSize: [41, 41]
        });
    }

    //-----VARIABLES-----
    this.mode = this.MODE_VIEW;
    this.locationMarker = null;
    this.map = null;
    this.routeData = null;
    this.routingMarkers = null;
    this.routingMarkerLayer = null;
    this.selectedMarker = null;
    this.routingLayer = null;
    this.router = null;

    //-----PRIVATE VARIABLES-----
    var DATA_POINTS_CLASS = 'points';
    var DATA_POINT_CLASS = 'point';

    //-----FUNCTIONS-----
    this.clearRouteData = function ()
    {
        this.mode = this.MODE_VIEW;
        this.locationMarker = null;
        this.map = null;
        this.routeData = null;
        this.routingMarkers = null;
        this.routingMarkerLayer = null;
        this.selectedMarker = null;
        this.routingLayer = null;
        this.router = null;
    };
    /**
     * Note: the data storage is inspired by http://www.heppnetz.de/files/RDF2RDFa-TR.pdf
     */
    this.writeRouteData = function ()
    {
        Store.mapData.html('');
        //clear all attributes (except the class) so we can start from scratch
        Store.mapData.each(function ()
        {
            //Note: plain javascript
            var ignored = ['class', 'property', 'data-property'];
            $.each(this.attributes, function (i, attrib)
            {
                if (attrib && $.inArray(attrib.name, ignored) < 0) {
                    Store.mapData.removeAttr(attrib.name);
                }
            });
        });

        //serialize the memory element to a RDFa compatible data structure
        if (Store.routeData) {

            storeContent(Store.mapData, 'property', MgConstants.MAP_RDF_TERM_LATITUDE_NAME, MgConstants.MAP_RDF_TERM_LATITUDE_TYPE, Store.routeData.center.lat);
            storeContent(Store.mapData, 'property', MgConstants.MAP_RDF_TERM_LONGITUDE_NAME, MgConstants.MAP_RDF_TERM_LONGITUDE_TYPE, Store.routeData.center.lon);
            storeContent(Store.mapData, 'property', MgConstants.MAP_RDF_TERM_ZOOM_NAME, MgConstants.MAP_RDF_TERM_ZOOM_TYPE, Store.routeData.center.zoom);
            storeContent(Store.mapData, 'property', MgConstants.MAP_RDF_TERM_DISTANCE_NAME, MgConstants.MAP_RDF_TERM_DISTANCE_TYPE, Store.routeData.distance);
            storeContent(Store.mapData, 'property', MgConstants.MAP_RDF_TERM_TIME_NAME, MgConstants.MAP_RDF_TERM_TIME_TYPE, Store.routeData.time);

            //serialize the detailed path of the route so we don't need to query the server to load it
            if (Store.routeData.path) {
                storeContent(Store.mapData, 'property', MgConstants.MAP_RDF_TERM_PATH_NAME, MgConstants.MAP_RDF_TERM_PATH_TYPE, btoa(JSON.stringify(Store.routeData.path)), true);
            }

            //store the single POIs
            if (Store.routeData.points) {
                //we'll wrap them in a div to group them together
                var pointsEl = $('<div />').addClass(DATA_POINTS_CLASS).appendTo(Store.mapData);
                for (var i = 0; i < Store.routeData.points.length; i++) {
                    var point = Store.routeData.points[i];

                    var propAttr = 'data-property';
                    //working with a class allows us to work in rdf and non-rdf mode
                    var pointEl = $('<div />').addClass(DATA_POINT_CLASS).appendTo(pointsEl);
                    if (point.rdf) {
                        //when it's official RDF, make it an official attribute
                        propAttr = 'property';
                        pointEl.attr('typeof', point.rdf.resourceType);
                        pointEl.attr('about', point.rdf.resourceUri);
                    }

                    var type = this.isRoutingIcon(point.icon) ? MgConstants.MAP_RDF_TERM_POI_TERM_POI : MgConstants.MAP_RDF_TERM_POI_TERM_NODE;
                    storeContent(pointEl, propAttr, MgConstants.MAP_RDF_TERM_TYPE_NAME, MgConstants.MAP_RDF_TERM_TYPE_TYPE, type);
                    storeContent(pointEl, propAttr, MgConstants.MAP_RDF_TERM_LATITUDE_NAME, MgConstants.MAP_RDF_TERM_LATITUDE_TYPE, point.lat);
                    storeContent(pointEl, propAttr, MgConstants.MAP_RDF_TERM_LONGITUDE_NAME, MgConstants.MAP_RDF_TERM_LONGITUDE_TYPE, point.lon);

                    //no need to pass this to the triplestor, it's just easy to work with client side
                    storeContent(pointEl, 'data-property', 'icon', 'xsd:string', point.icon);
                }
            }
        }
    };
    this.loadRouteData = function ()
    {
        var retVal = {};

        retVal.center = {
            lat: parseFloat(loadContent(Store.mapData, MgConstants.MAP_RDF_TERM_LATITUDE_NAME)),
            lon: parseFloat(loadContent(Store.mapData, MgConstants.MAP_RDF_TERM_LONGITUDE_NAME)),
            zoom: parseInt(loadContent(Store.mapData, MgConstants.MAP_RDF_TERM_ZOOM_NAME)),
        };

        retVal.distance = parseFloat(loadContent(Store.mapData, MgConstants.MAP_RDF_TERM_DISTANCE_NAME));
        retVal.time = parseInt(loadContent(Store.mapData, MgConstants.MAP_RDF_TERM_TIME_NAME));

        var serializedPath;
        var serializedPath64 = loadContent(Store.mapData, MgConstants.MAP_RDF_TERM_PATH_NAME, true);
        if (serializedPath64) {
            serializedPath = atob(serializedPath64);
        }

        if (serializedPath) {
            retVal.path = $.parseJSON(serializedPath);
        }
        else {
            retVal.path = null;
        }

        retVal.points = [];
        var pointsEl = Store.mapData.find('.' + DATA_POINT_CLASS);
        pointsEl.each(function ()
        {
            var pointEl = $(this);

            var point = {
                type: loadContent(pointEl, MgConstants.MAP_RDF_TERM_TYPE_NAME),
                lat: parseFloat(loadContent(pointEl, MgConstants.MAP_RDF_TERM_LATITUDE_NAME)),
                lon: parseFloat(loadContent(pointEl, MgConstants.MAP_RDF_TERM_LONGITUDE_NAME)),

                icon: loadContent(pointEl, 'icon'),
            };

            //make it null or the store function above will write out real RDF properties
            var rdf = null;
            if (pointEl.hasAttribute('typeof')) {
                //Note: needs to be compatible with the store structure, but also the autocomplete-structure!
                //Note 2: we'll fill the rdf object with some temp values we know from deserialization, but the real
                // data will be overwritten later using the JSon call below
                rdf = {
                    resourceType: pointEl.attr('typeof'),
                    resourceUri: pointEl.attr('about'),
                };

                $.getJSON(MgConstants.MAP_RDF_TERM_POI_CONFIG_valEndpoint + encodeURIComponent(rdf.resourceUri))
                    .done(function (data)
                    {
                        point.rdf = data;
                    })
                    .fail(function (xhr, textStatus, exception)
                    {
                        Notification.error(BlocksMessages.generalServerDataError + (exception ? "; " + exception : ""), xhr);
                    });

            }
            point.rdf = rdf;

            retVal.points.push(point);
        });

        return retVal;
    };
    this.isRoutingMarker = function (marker)
    {
        //Note: all these .options are actually not documented, so let's hope they stay accessible...
        return marker.options.icon.options.iconUrl == Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_POI].icon.options.iconUrl || marker.options.icon.options.iconUrl == Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_POI_SELECTED].icon.options.iconUrl;
    };
    this.isShapeMarker = function (marker)
    {
        //Note: all these .options are actually not documented, so let's hope they stay accessible...
        return marker.options.icon.options.iconUrl == Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_NODE].icon.options.iconUrl || marker.options.icon.options.iconUrl == Store.MAP_ICONS[MgConstants.MAP_ICON_ROUTE_NODE_SELECTED].icon.options.iconUrl;
    };
    this.isRoutingIcon = function (iconName)
    {
        return iconName == MgConstants.MAP_ICON_ROUTE_POI || iconName == MgConstants.MAP_ICON_ROUTE_POI_SELECTED;
    };
    this.isShapeIcon = function (iconName)
    {
        return iconName == MgConstants.MAP_ICON_ROUTE_NODE || iconName == MgConstants.MAP_ICON_ROUTE_NODE_SELECTED;
    };

    //-----PRIVATE FUNCTIONS-----
    var storeContent = function (parentEl, propertyAttr, property, datatype, value, html)
    {
        var retVal = null;

        if (value) {
            retVal = $('<span />').attr(propertyAttr, property).attr('datatype', datatype).appendTo(parentEl);

            if (html) {
                retVal.html(value);
            }
            else {
                retVal.attr('content', value);
            }
        }

        return retVal;
    };
    var loadContent = function (parentEl, property, html)
    {
        var retVal = null;

        var centerLatEl = parentEl.find('[property="' + property + '"],[data-property="' + property + '"]');
        if (centerLatEl.length) {
            if (html) {
                retVal = centerLatEl.html();
            }
            else {
                retVal = centerLatEl.attr('content');
            }
        }

        return retVal;
    };

    //-----MAIN INIT-----

}]);
