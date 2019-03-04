th_maps.addThemes('default', thConfig.map.defaultThemes);

th_maps.addMarkerIcon('default', {
    iconUrl: './assets/images/ico/ico-marker-map.png',
    shadowUrl: null,
    iconSize: [26, 42],
    scaledSize: { width: 26, height: 42},
    anchor: { x: 13, y: 21 }
});


// Map - Bloc Map
function callbackBlocMap(macarte) {
    macarte.zoomControl.setPosition('bottomleft');
    macarte.setZoom(12);
    parseMapPopup(macarte);
}

th_maps.init(thConfig.map.init);



function parseMapPopup(macarte){
    var markers = [];

    $(macarte._container).parent().find('.maps-popup').each(function(){
        var latlng = {lat:this.getAttribute('data-lat')*1,lng:this.getAttribute('data-lng')*1};
        var marker = th_maps.createMarker(macarte,latlng,this.getAttribute('data-markericon'));
        th_maps.createInfoWindow(this.innerHTML,marker,275);
        markers.push(marker);
    });
    var group = new L.featureGroup(markers);
    macarte.fitBounds(group.getBounds());

    if(macarte.getZoom() > 12){
        macarte.setZoom(12);
    }

    return markers;
}
