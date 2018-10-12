// On garde une référence globale aux ²
var placeAutoFields;

jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_project_ProjectBOPortlet_";
	
	// Selection d'image interne ou externe
	$('[name=imageType]').on('click change', function(e) {
		var classOfDivToShow = e.target.value;
		var classOfDivToHide = 'internalImageexternalImage'.replace(classOfDivToShow, '');
		$('.internalImage, .externalImage').hide();	
		$('.' + classOfDivToShow).show();
		$('.' + classOfDivToHide + ' input').val('');
		$('.' + classOfDivToHide + ' .image-thumbnail').remove();
		setConditionalValidators();
	});
	
	Liferay.on('allPortletsReady', setConditionalValidators);
	
	function setConditionalValidators() {
		// Validation des champos obligatoires conditionnels
		AUI().use('liferay-form', function() {
			if (!!window.editPetition) {
				var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
				
				if (jQuery('.internalImage').is(':visible')) {
					rules[namespace + 'imageId'].required = true;
					rules[namespace + 'externalImageURL'].required = false;
					rules[namespace + 'externalImageCopyright'].required = false;
				} else {
					rules[namespace + 'imageId'].required = false;
					rules[namespace + 'externalImageURL'].required = true;
					rules[namespace + 'externalImageCopyright'].required = true;
				}
			}
		});
	}
	
	// Lieux répétables
	AUI().use('liferay-auto-fields', function(Y) {
		if (!!document.getElementById('place-fields')) {
			// Création de l'autofield
			placeAutoFields = new Liferay.AutoFields({
				contentBox : '#place-fields',
				fieldIndexes : namespace + 'placeIndexes',
				namespace : namespace,
				url: getPetitionPlaceRowURL
			}).render();
		}
	});
	
	//Autocomplete des lieux
	var options = {
		type : "POST",
		serviceUrl : "/api/jsonws/place.place/get-places-by-name-and-language/",
		params : {
			name : '[name]',
			language: 'fr_FR',
			p_auth: Liferay.authToken
		},
		paramName : 'name',
		transformResult : function(response) {
			return {
				suggestions : jQuery.map(
						JSON.parse(response), function(
								dataItem) {
							return {
								value : dataItem.name.fr_FR,
								data : dataItem.idSurfs
							};
						})
			};
		},
		onSelect : function(suggestion) {
			jQuery('#place-autocomplete-hidden-value input', $(this).closest('.row-fields')).val(
					suggestion.data);
			jQuery('input.selected-place', $(this).closest('.row-fields')).val(suggestion.value);
		}
	};
	jQuery('.place-autocomplete-input-wrapper').each(function() {
		options.appendTo = this;
		$('input', this).autocomplete(options);
	})
	
	// Switch entre les sélections de lieux
	$('#place-fields').on('click', '.show-autocomplete-place',  function(e) {
		e.preventDefault();
		$('.place-manual', $(this).closest('.row-fields')).hide();
		$('.place-autocomplete', $(this).closest('.row-fields')).show();
		$('.place-manual input', $(this).closest('.row-fields')).val('');
		$('.place-manual option', $(this).closest('.row-fields')).prop('selected', false);
	})
	.on('click', '.show-manual-place', function(e) {
		e.preventDefault();
		$('.place-autocomplete', $(this).closest('.row-fields')).hide();
		$('.place-manual', $(this).closest('.row-fields')).show();
		$('.place-autocomplete input', $(this).closest('.row-fields')).val('');
		$('.place-autocomplete option', $(this).closest('.row-fields')).prop('selected', false);
	});
	// A la création d'une nouvelle ligne de lieu on remet en place l'autocomplete
	$('#place-fields').on('placeCreated', function(event, index) {
		options.appendTo = '#place-autocomplete-input-wrapper-' + index;
		jQuery('#place-' + index + ' .place-autocomplete-input-wrapper input').autocomplete(
				options);
	});

});

//Soumission du formulaire
function submitForm(event) {
	placeAutoFields.save(event.target);
	return true;
}

