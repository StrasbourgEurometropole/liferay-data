Loader.addModule({
	dependencies : [],
	name : 'jquery',
	path : 'http://code.jquery.com/jquery-2.1.4.min.js'
});

// Autocomplete
require(
		'jquery',
		'/o/agendabo/js/vendors/jquery.autocomplete.js',
		function(jQuery) {
			jQuery.noConflict();
			if (!!window.placeAutocompleteURL) {
				var options = {
					serviceUrl : placeAutocompleteURL,
					params : {
						_eu_strasbourg_portlet_agenda_AgendaBOPortlet_name : '[name]'
					},
					paramName : '_eu_strasbourg_portlet_agenda_AgendaBOPortlet_name',
					transformResult : function(response) {
						return {
							suggestions : jQuery.map(
									JSON.parse(response).places, function(
											dataItem) {
										return {
											value : dataItem.name,
											data : dataItem.idSurfs
										};
									})
						};
					},
					onSelect : function(suggestion) {
						jQuery('#place-autocomplete-hidden-value input').val(
								suggestion.data);
						jQuery('input.selected-place').val(suggestion.value);
					},
					appendTo : '.place-autocomplete-input-wrapper'
				};
				jQuery('.place-autocomplete-input-wrapper input').autocomplete(
						options);
			}
		}, function(e) {
			console.log(e);
		});