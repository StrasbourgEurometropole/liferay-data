Loader.addModule({
	dependencies : [],
	name : 'jquery',
	path : 'http://code.jquery.com/jquery-2.1.4.min.js'
});

// Autocomplete
require(
		'jquery',
		'/o/legacyplaceviewer/js/jquery.autocomplete.js',
		function(jQuery) {
			jQuery.noConflict();
			if (!!window.placeAutocompleteURL) {
				var options = {
					serviceUrl : placeAutocompleteURL,
					params : {
						_eu_strasbourg_portlet_legacy_place_LegacyPlaceViewerPortlet_name : '[name]'
					},
					paramName : '_eu_strasbourg_portlet_legacy_place_LegacyPlaceViewerPortlet_name',
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
						jQuery('input.chosen-place').val(suggestion.value);
						jQuery('input.selected-place').val(suggestion.value);
					},
					appendTo : '.place-autocomplete-input-wrapper'
				};
				jQuery('.place-autocomplete-input-wrapper input').autocomplete(
						options);
			}
			
			// Validation formulaire contact
			jQuery(function() {
				$('.legacy-place-viewer-portlet .contact-form').on('submit', function(e) {
					$('.all-fields-required-message').hide();
					$('.invalid-mail-message').hide();
					$('input.first-name, input.last-name, input.email, textarea.message').each(function() {
						$(this).removeClass('error');
						if ($(this).val() === "") {
							e.preventDefault();
							$(this).addClass('error');
							$('.all-fields-required-message').show();
						}
						if( !/(.+)@(.+){2,}\.(.+){2,}/.test($('input.email').val())) {
							e.preventDefault();
							$('input.email').addClass('error');
							$('.invalid-mail-message').show();
						}
					});
				});
			});

		}, function(e) {
			console.log(e);
		});