base.plugin("mobielegidsen.RouteIndex", ["base.core.Class", "mobielegidsen.Slider", "mobielegidsen.RouteIndexStops", function (Class, Slider, RouteIndexStops)
{
    var Map = this;
    var mapContainer = $('.map');

    Slider.setAutoSlide(true);

    // set up the map
    var map = new L.Map(mapContainer[0], {
        zoomControl: false,
        dragging: false,
        touchZoom: false,
        scrollWheelZoom: false,
        doubleClickZoom: false,
        boxZoom: false,
        tap: false,
    });

    // create the tile layer with correct attribution
    var osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    // var osmAttrib='Map data © <a href="http://openstreetmap.org">OpenStreetMap</a> contributors';
    // var osm = new L.TileLayer(osmUrl, {minZoom: 8, maxZoom: 12, attribution: osmAttrib});
    var osmLayerHot = L.tileLayer('http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });
    var osmLayerRoads = L.tileLayer('http://korona.geog.uni-heidelberg.de/tiles/roads/x={x}&y={y}&z={z}', {
        maxZoom: 20,
        attribution: 'Imagery from <a href="http://giscience.uni-hd.de/">GIScience Research Group @ University of Heidelberg</a> &mdash; Map data &copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });
    var osmLayerMq = L.tileLayer('http://{s}.mqcdn.com/tiles/1.0.0/map/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright" title="OpenStreetMap" target="_blank">OpenStreetMap</a> contributors | Tiles Courtesy of <a href="http://www.mapquest.com/" title="MapQuest" target="_blank">MapQuest</a> <img src="http://developer.mapquest.com/content/osm/mq_logo.png" width="16" height="16">',
        subdomains: ['otile1', 'otile2', 'otile3', 'otile4']
    });

    map.setView(new L.LatLng(RouteIndexStops.location.lat, RouteIndexStops.location.lon), RouteIndexStops.location.zoom);
    L.marker([RouteIndexStops.location.lat, RouteIndexStops.location.lon]).addTo(map);
    map.addLayer(OSM_LAYER_HOT);

}]);
