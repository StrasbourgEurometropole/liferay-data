//Création de la carte au centre de strasbourg
var mymap = L.map('mapid', {
	crs: L.CRS.EPSG4326,
	center: [48.573, 7.752],
	zoom: 13
});	

//Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
var wmsLayer = L.tileLayer.wms('http://adict.strasbourg.eu/mapproxy/service?', {
	layers: 'gct_fond_de_carte_couleur'
}).addTo(mymap);

function onEachFeature(feature, layer) {
    // does this feature have a property named nom?
    if (feature.properties && feature.properties.nom) {
        layer.bindPopup(feature.properties.nom);
    }
}

$.getJSON("http://adict.strasbourg.eu/api/v1.0/pois?srid=4326&poitype=Cat_06_07&radius=-1&token=aa72a01e643db472f3e7843ac1f3e48c", function(data) {
	L.geoJson(data, {
		onEachFeature: onEachFeature
		}).addTo(mymap);
});