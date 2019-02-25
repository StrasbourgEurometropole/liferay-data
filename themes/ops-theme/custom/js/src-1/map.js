// th_maps.addThemes('default',{
//     accessToken: 'pk.eyJ1IjoibG9ycmFpbmV0b3VyaXNtZSIsImEiOiJjamhramNhOGMweXk1MzBrZGRyNmY0cTFuIn0.3uDgPr9gtiaAsSJPla3cFA',
//     style: 'mapbox://styles/lorrainetourisme/cjhrff70j3ywu2slc764dggkb',
//     attribution: '&copy; <a href="https://www.mapbox.com/about/maps/" target="_blank" rel="nofollow">Mapbox</a> &copy; <a href="https://www.openstreetmap.org/" target="_blank" rel="nofollow">OpenStreetMap</a> <a href="https://www.mapbox.com/map-feedback/#/-74.5/40/10" target="_blank" rel="nofollow"><strong>Improve this map</strong></a>',
// });
// th_maps.addThemes('landing',{
//     accessToken: 'pk.eyJ1IjoibG9ycmFpbmV0b3VyaXNtZSIsImEiOiJjamhramNhOGMweXk1MzBrZGRyNmY0cTFuIn0.3uDgPr9gtiaAsSJPla3cFA',
//     style: 'mapbox://styles/lorrainetourisme/cjhsrggia19f12roa4z02j3v1',
//     attribution: '&copy; <a href="https://www.mapbox.com/about/maps/" target="_blank" rel="nofollow">Mapbox</a> &copy; <a href="https://www.openstreetmap.org/" target="_blank" rel="nofollow">OpenStreetMap</a> <a href="https://www.mapbox.com/map-feedback/#/-74.5/40/10" target="_blank" rel="nofollow"><strong>Improve this map</strong></a>',
// });





th_maps.onLoad(function () {

    th_maps.addMarkerIcon('marker', {
        iconUrl: '' + document.location.origin + '/wp-content/themes/sandemans/assets/images/ico/ico-marker-map-2x.png',
        shadowUrl: null,
        iconSize:[35, 41]
    });

});


function callbackMapPartner(macarte){
 //   var marker = th_maps.createMarker(macarte,{lat:45.780524,lng:3.089576},'marker');
}



/* FILTER MAP FOR PARTNER'S PAGE */
function filterMapPartner(options) {
    options.mapTypeControl = false;
    options.streetViewControl = false;
    options.zoomControl = true;

    options.zoomControlOptions = {position:google.maps.ControlPosition.LEFT_TOP};
    options.mapTypeId = google.maps.MapTypeId.ROADMAP;

    return options;
}


th_maps.init({
    maps_class: '.maps > *',
    tileLayerUrl: 'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}',
    tileLayerOptions: {
        attribution: '&copy; <a href="https://www.mapbox.com/about/maps/" target="_blank" rel="nofollow">Mapbox</a> &copy; <a href="https://www.openstreetmap.org/" target="_blank" rel="nofollow">OpenStreetMap</a> <a href="https://www.mapbox.com/map-feedback/#/-74.5/40/10" target="_blank" rel="nofollow"><strong>Improve this map</strong></a>',
        maxZoom: 18,
        id: 'mapbox.streets',
        accessToken: 'pk.eyJ1IjoidGhvbWFza2lyc2NoIiwiYSI6ImNqaWxoNDF5OTA1OGEzbGs3N2hxanJzNjgifQ.06QJZaF3qT1lJ4-0p3louA'
    }
});

