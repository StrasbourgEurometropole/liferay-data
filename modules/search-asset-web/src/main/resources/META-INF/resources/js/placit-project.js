// Initialisation des variables de références
var projects = null;

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
	var selectedStatut = $('#statut-projet')[0].value;
	var selectedDistricts = getSelectedMarkerElements(entityType.DISTRICT);
	var selectedThematics = getSelectedMarkerElements(entityType.THEMATIC);

	AUI().use('aui-io-request', function(A) {
		A.io.request(projectsSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedKeyWords : selectedKeyWords,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedStatut : selectedStatut,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedDistricts : selectedDistricts,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_selectedThematics : selectedThematics,
				_eu_strasbourg_portlet_search_asset_SearchAssetPortlet_sortFieldAndType : sortField + ',' + sortType,
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	getResult('project', data);
			 	}
			}
		});
	});
}

// Lors d'une recherche par mots clés
$('#name').on('input',function() {
	getSelectedEntries();
});

// Lors d'une selection de statut
$('#statut-projet').change(function() {
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
