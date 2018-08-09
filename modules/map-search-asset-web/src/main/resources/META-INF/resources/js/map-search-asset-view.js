// Initialisation des variables de références
leafletMap = null;

$(document).ready(function() {

	//Création de la carte au centre de strasbourg
    leafletMap = getLeafletMap()

});

// Soumission du formulaire de la facette
$('#district').change(function() {
	
	alert('select');
	
	var selectedDistrict = this.value;
	
	AUI().use('aui-io-request', function(A) {
		A.io.request(changeDistrictURL, {
			method : 'post',
			data : {
				_eu_strasbourg_portlet_map_search_asset_MapSearchAssetPortlet_selectedDistrict : selectedDistrict
			},
			on: {
                success: function(e) {
                	alert('success');
			 	}
			 }
		});
	});
});