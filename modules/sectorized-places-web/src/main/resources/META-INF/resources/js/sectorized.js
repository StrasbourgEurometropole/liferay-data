(function(jQuery) {
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
								data : street.x + ":" + street.y
							};
						})
			};
		},
		onSelect : function(suggestion) {
			console.log(suggestion);
		}
	};
	$('input[name=query]').autocomplete(options);
})(jQuery);