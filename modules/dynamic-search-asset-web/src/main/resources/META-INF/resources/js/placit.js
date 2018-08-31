// Initialisation des variables de références
var resultEntities = [];

/**
 * Supprime l'affichage de la totalité des vignettes
 */
function removeAllThumbnails() {
	$("#dynamic-search-asset-results").empty();
}

/**
 * Renvoi la liste des types entités demandées
 * @return liste des classNames sous forme d'une chaine de caractères séparée par des ","
 */
function getSelectedClassNames() {
	var results = "";
	
	$("input[id^='dynamic_search_type_']:checked").each(function() {
		results =  results.concat(this.value, ',');
	});
	
	return results;
}

/**
 * Lors d'une soumission de formulaire de recherche dynamique
 */
$('#dynamic-search-submit').submit(function() {
	
	var selectedClassNames = getSelectedClassNames();
	var keywords = $( "input[name='dynamic-search-keywords']" ).val();
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(searchAssetURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_selectedClassNames : selectedClassNames,
				_eu_strasbourg_portlet_dynamic_search_asset_DynamicSearchAssetPortlet_keywords : keywords
			},
			on: {
                success: function(e) {
                	removeAllThumbnails();
                	
                	var data = this.get('responseData');
                	
			 	}
			 }
		});
	});
});