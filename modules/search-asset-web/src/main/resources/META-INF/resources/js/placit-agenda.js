// Initialisation des "pointeurs" vers les elements utiles
var leafletMap = null;
eventMarkers = []

function updateLeafletMap () {
	$('.pro-bloc-listing-event > form > a').each(function () {
		var mercators = [$(this).data('lat'), $(this).data('lng')];
		
		if (mercators[0] != "0" && mercators[1] != "0") {
			var link = $(this).attr('href');
			var publishDate = $('time', this).text();
			var place = $('p', this).text();
			var title = $('h3', this).text();
			
			var marker = getEventListingMarker(mercators, link, publishDate, place, title);
			marker.addTo(leafletMap);
			
			eventMarkers.push(eventMarkers);
		}
	});
}

$(document).ready(function() {

    // Gestion de la carte interactive

    //Création de la carte au centre de strasbourg
    leafletMap = L.map('mapid', {
        // crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
        center: [48.573, 7.752],
        maxBounds: [[48.42, 7.52], [48.72, 7.94]],
        minZoom: 13,
        zoom: 13,
        minZoom: 12,
        zoomControl: false,
        attributionControl: false
    });

    // Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
    var wmsLayer = L.tileLayer.wms('http://adict.strasbourg.eu/mapproxy/service?', {
        layers: 'gct_fond_de_carte_couleur'
    }).addTo(leafletMap);
    
    // Récuperation et placement des points
    updateLeafletMap();

});