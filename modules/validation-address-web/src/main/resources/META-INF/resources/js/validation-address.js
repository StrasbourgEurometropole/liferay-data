(function(jQuery) {
    var namespace = '_eu_strasbourg_portlet_validationAddress_ValidationAddressWebPortlet_';

    $('.portlet-column-content').on('click','input[type="radio"]', function() {
        if($(this).val() == "other"){
            $('#validation').hide();
            $('#other-address').show();
        }else{
            $('#' + namespace + 'fm #' + namespace + 'address').val($(this).val().split(',')[0]);
            $('#' + namespace + 'fm #' + namespace + 'zipCode').val($(this).val().split(',')[1]);
            $('#' + namespace + 'fm #' + namespace + 'city').val($(this).val().split(',')[2]);
            $('#validation').show();
            $('#other-address').hide();
        }
   });

	//Autocomplete des lieux
	var options = {
		type : "POST",
		serviceUrl : "/api/jsonws/strasbourg.strasbourg/search-streets/",
		params : {
			query : '[query]',
			city : window.forcedCity,
			p_auth: Liferay.authToken
		},
		preventBadQueries : false,
		paramName : 'query',
		transformResult : function(response) {
			return {
				suggestions : jQuery.map(
						JSON.parse(response).streets, function(
								street) {
							return {
								value : street.label,
								data : street.x + ":" + street.y,
								rue : street.name,
								cp : street.zipCode,
								ville : street.city
							};
						})
			};
		},
		onSelect : function(suggestion) {
			console.log(suggestion);
			$('#' + namespace + 'fmChoice #' + namespace + 'address').val(suggestion.rue);
			$('#' + namespace + 'fmChoice #' + namespace + 'zipCode').val(suggestion.cp);
			$('#' + namespace + 'fmChoice #' + namespace + 'city').val(suggestion.ville);
			$('#' + namespace + 'fmChoice').show();
		}
	};
	$('input[name=query]').autocomplete(options);
})(jQuery);