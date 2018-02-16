//Création de la carte au centre de strasbourg
var mymap = L.map('mapid', {
	//crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
	center: [48.573, 7.752],
	zoom: 13
});	

//Couche gui gère le clustering des points
var markers = L.markerClusterGroup({
	showCoverageOnHover: false
	});

//Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
var wmsLayer = L.tileLayer.wms('http://adict.strasbourg.eu/mapproxy/service?', {
	layers: 'gct_fond_de_carte_couleur'
}).addTo(mymap);


function onEachFeature(feature, layer) {
    // does this feature have a property named nom?
    if (feature.properties && feature.properties.nom) {
    	//popup du marker
        layer.bindPopup(feature.properties.nom);
        //Titre dans la liste des markers
        layer.options['title'] = feature.properties.nom;
    }
}

//Récupère les données au format GeoJSON
$.getJSON("http://adict.strasbourg.eu/api/v1.0/pois?srid=4326&poitype=Cat_06_07&radius=-1&token=aa72a01e643db472f3e7843ac1f3e48c", function(data) {
	
	//Convertion des données geoJSON en marker
	var geoData = L.geoJson(data, {
		onEachFeature: onEachFeature
		});
	
	markers.addLayers(geoData);
	mymap.addControl(new L.Control.ListMarkers({layer: markers, itemIcon: null}));
	mymap.addLayer(markers);
	
	//Copie la liste des markers dans la div prévue à cet effet
	$('#map-markers').append($('ul.list-markers-ul'));
});