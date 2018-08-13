// Initialisation des variables de références
leafletMap = null;

$(document).ready(function() {

	//Création de la carte au centre de strasbourg
    leafletMap = getLeafletMap()

});

// Soumission du formulaire de la facette
$('#district').change(function() {
	
	var selectedDistrict = this.value;
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeMapSelectionURL, {
			method : 'post',
			dataType: 'json',
			data : {
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedDistrict : selectedDistrict
			},
			on: {
                success: function(e) {
                	var data = this.get('responseData');
                	console.log(data.projects);
			 	}
			 }
		});
	});
});