// Initialisation des variables de références
var agendas = null;

var entityType = {
	DISTRICT : 'vocabulary_1',
	THEMATIC : 'vocabulary_2',
}

var sortField = "publishDate_sortable";
var sortType = "asc";


$(document).ready(function(){
    getSelectedEntries();
});

/**
 * Renvoi la liste des IDs des entités demandées
 * @param entityName Nom de l'entité à sonder
 * @return liste des IDs sous forme d'une chaine de caractères séparée par des ","
 */
function getSelectedMarkerElements(entityName) {
	var results = "";

	$("input[id*='" + entityName + "_']:checked").each(function() {
		results =  results.concat(this.value, ',');
	});

	return results;
}

/**
 * Renvoi la liste des vidéos demandées
 * @return
 */
function getSelectedEntries() {
	var selectedKeyWords = $('#name')[0].value;

    var selectedStartDay ;
    var selectedStartMonth ;
    var selectedStartYear;
    var selectedEndDay;
    var selectedEndMonth ;
    var selectedEndYear;
	if($('.pro-facette-date').size() > 0){
        selectedStartDay = $('input[data-name="fromDay"]')[0].value;
        selectedStartMonth = $('input[data-name="fromMonth"]')[0].value;
        selectedStartYear = $('input[data-name="fromYear"]')[0].value;
        selectedEndDay = $('input[data-name="toDay"]')[0].value;
        selectedEndMonth = $('input[data-name="toMonth"]')[0].value;
        selectedEndYear = $('input[data-name="toYear"]')[0].value;
	}
	var selectedProject = $('#projet')[0].value;
	var selectedDistricts = getSelectedMarkerElements(entityType.DISTRICT);
	var selectedThematics = getSelectedMarkerElements(entityType.THEMATIC);

	AUI().use('aui-io-request', function(A) {
		A.io.request(agendasSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedKeyWords : selectedKeyWords,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartDay : selectedStartDay,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartMonth : selectedStartMonth,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStartYear : selectedStartYear,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndDay : selectedEndDay,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndMonth : selectedEndMonth,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedEndYear : selectedEndYear,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedProject : selectedProject,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedDistricts : selectedDistricts,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedThematics : selectedThematics,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_sortFieldAndType : sortField + ',' + sortType,
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	getResult('agenda', data);
                    updateLeafletMap();
			 	}
			}
		});
	});
}

// Lors d'une recherche par mots clés
$('#name').on('input',function() {
	getSelectedEntries();
});

// Lors d'une selection de projet
$('#projet').change(function() {
	getSelectedEntries();
});

// Lors d'une selection d'un quartier
$("fieldset[id='districts_fieldset'] input").change(function() {
	getSelectedEntries();
});

// Lors d'une selection d'une thématique
$("fieldset[id='thematics_fieldset'] input").change(function() {
	getSelectedEntries();
});

// Permet le tri des vidéos
function sortVideo(type) {
    sortType = type;
    // change l'affichage du tri
    if(type == "asc"){
        $('#sortType').text = "Tri A-Z";
    }else{
        $('#sortType').text = "Tri Z-A";
    }
    getSelectedEntries();
}



// Initialisation des "pointeurs" vers les elements utiles
var leafletMap = null;
eventMarkers = []

// Mettre à jour 
function updateLeafletMap () {
	$('.pro-bloc-listing-event > form  a').each(function () {
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
        layers: 'monstrasbourg'
    }).addTo(leafletMap);
    
    // Récuperation et placement des points
    updateLeafletMap();

});