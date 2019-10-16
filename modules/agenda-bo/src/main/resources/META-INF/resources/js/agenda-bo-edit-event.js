// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_agenda_AgendaBOPortlet_";
	var namespaceAUI = "#" + namespace;

	$('[name=placeType]').on('click change', function(e) {
		var classOfDivToShow = e.target.value;
		var classOfDivToHide = 'sigmanual'.replace(classOfDivToShow, '');
		$('.sig, .manual').hide();
		$('.' + classOfDivToShow).show();
		$('.' + classOfDivToHide + ' input').val('');
		$('.' + classOfDivToHide + ' input[type=checkbox]').attr('checked', false);
		$('.' + classOfDivToHide + ' option').attr('selected', false);
		setConditionalValidators();
	});
	
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

	$(":submit").on('click', function(e) {
        allValidate = true;

		setConditionalValidators(e);
	});
	
	function setConditionalValidators() {
		// Validation des champos obligatoires conditionnels
		AUI().use('liferay-form', function() {
			if (!!window.editEvent) {
				var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
				if (jQuery('.manual').is(':visible')) {
					rules[namespace + 'selectedPlace'].required = false;
					rules[namespace + 'placeName'].required = true;
					rules[namespace + 'placeCity'].required = true;
				} else {
					rules[namespace + 'selectedPlace'].required = true;
					rules[namespace + 'placeName'].required = false;
					rules[namespace + 'placeCity'].required = false;
				}
				
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
	
});

// Périodes
var autoFields = undefined; // Référence au champ répétable (setté plus loin)
(function($) {
	var namespace = "_eu_strasbourg_portlet_agenda_AgendaBOPortlet_"; // Namespace du portlet
	var dateRangePickerLocaleSettings =  { // Configuration française du dateRangePicker
		format: 'DD/MM/YYYY',
		applyLabel: 'Valider',
		applyAndNewLabel: 'Valider et ajouter une nouvelle période',
		cancelLabel: 'Fermer',
		 daysOfWeek: [
            "Di",
            "Lu",
            "Ma",
            "Me",
            "Je",
            "Ve",
            "Sa"
        ],
        monthNames: [
            "Janvier",
            "Février",
            "Mars",
            "Avril",
            "Mai",
            "Juin",
            "Juillet",
            "Août",
            "Septembre",
            "Octobre",
            "Novembre",
            "Décembre"
        ],
        firstDay: 1
	};
	/**
	 * RangePicker répétable
	 */
	// Options du date range picker répétable
	var options = { 
		autoApply: false,
		parentEl: '.portlet-body',
		opens: 'right',
		showDates: true,
		autoUpdateInput: false,
		locale: dateRangePickerLocaleSettings
	};

	// Fonction appelée lors du choix d'une nouvelle range
	var onDateChange = function(ev, picker) { 
		// Set du texte du label
		$(this).text(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
		// Set des champs input cachés
		$('.startDate', $(this).parent()).val(picker.startDate.format('DD/MM/YYYY'));
		$('.endDate', $(this).parent()).val(picker.endDate.format('DD/MM/YYYY'));
	};

   // Fonction appelé pour ouvrir le calendrier général
	var openGeneralRange = function (ev, picker) {
		$('#' + namespace + 'periodGenerator').trigger('click');
    };

	// On active le composant
	options.startDate = $('span.date-range').text().split(' - ')[0];
	options.endDate = $('span.date-range').text().split(' - ')[1];
	var dateRangePicker = $('span.date-range').daterangepicker(options);
	// On attache l'événement de changement de range de date
	$('span.date-range').on('apply.daterangepicker', onDateChange);
	$('span.date-range').on('applyAndNew.daterangepicker', onDateChange);
    // Lors du clic sur le bouton "Valider et ajouter une nouvelle période"
    $('span.date-range').on('applyAndNew.daterangepicker', openGeneralRange);
	
	// Configuration de l'autofield
	AUI().use('liferay-auto-fields', function(Y) {
		if (!!document.getElementById('date-fields')) {
			// Création de l'autofield
			autoFields = new Liferay.AutoFields({
				contentBox : '#date-fields',
				fieldIndexes : namespace + 'periodIndexes',
				namespace : namespace,
				url: getPeriodRowJSPURL
			}).render();
		}
	});

	// Evenement appelé après un "clone" : on doit reactiver le datepicker et rattacher l'event
	$('#date-fields').on('dateRangeCreated', function(event, index) {
        options.startDate = $('#dateRange' + index).text().split(' - ')[0];
        options.endDate = $('#dateRange' + index).text().split(' - ')[1];
		dateRangePicker = $('#dateRange' + index).daterangepicker(options);
		$('#dateRange' + index).on('apply.daterangepicker', onDateChange);
		$('#dateRange' + index).on('applyAndNew.daterangepicker', onDateChange);
        $('#dateRange' + index).on('applyAndNew.daterangepicker', openGeneralRange);
	});
	
	/**
	 * RangePicker permettant la création à la chaîne
	 */
	// Activation du RangePicker 
	$('#' + namespace + 'periodGenerator').daterangepicker({
		autoApply: false,
		parentEl: '.portlet-body',
		locale: dateRangePickerLocaleSettings
	});
	// Lors du clic sur le bouton "Appliquer
	$('#' + namespace + 'periodGenerator').on('apply.daterangepicker', function (ev, picker) {
		// On simule le clic sur le bouton "+" de l'autoField
		// On modifie également l'URL appelée pour récupérer la ligne répétable
		// afin d'ajouter les paramètres de dates de début et de fin
		var formattedStartDate = picker.startDate.format('DD/MM/YYYY');
		var formattedEndDate = picker.endDate.format('DD/MM/YYYY');
		var previousURL = autoFields.url;
		autoFields.url = autoFields.url + '&' + namespace + 'startDate=' + formattedStartDate
			+ '&' + namespace + 'endDate=' + formattedEndDate;

		$('button.add-row', $('#date-fields .lfr-form-row:not(.hide)').first()).trigger('click');

		// On reset l'URL à sa valeur initiale
		autoFields.url = previousURL;
	});
	// Lors du clic sur le bouton "Valider et ajouter une nouvelle période
	$('#' + namespace + 'periodGenerator').on('applyAndNew.daterangepicker', function (ev, picker) {

		// On simule le clic sur le bouton "+" de l'autoField
		// On modifie également l'URL appelée pour récupérer la ligne répétable
		// afin d'ajouter les paramètres de dates de début et de fin
		var formattedStartDate = picker.startDate.format('DD/MM/YYYY');
		var formattedEndDate = picker.endDate.format('DD/MM/YYYY');
		var previousURL = autoFields.url;
		autoFields.url = autoFields.url + '&' + namespace + 'startDate=' + formattedStartDate
			+ '&' + namespace + 'endDate=' + formattedEndDate;

		$('button.add-row', $('#date-fields .lfr-form-row:not(.hide)').first()).trigger('click');

		// On laisse le calendrier ouvert
        picker.startDate = moment().startOf('day');
        picker.endDate = moment().startOf('day');
		$('#' + namespace + 'periodGenerator').trigger('click');
		
		// On reset l'URL à sa valeur initiale
		autoFields.url = previousURL;		
	});
	
	/**
	 * Modification globale des horaires
	 */
	$('#' + namespace + 'changeTimes').on('click', function() { 
		// Au clic sur le bouton "Modifier les horaires", on set les valeurs de tous les champs localisables
		// Et on lance une méthode du composant afin que la modification soit prise en compte
		var newValue = $('#' + namespace + 'timeDetailGenerator').val();
		$('#date-fields .input-localized input[type=text]').each(function() {
			$(this).val(newValue);
			Liferay.component($(this).attr('id')).updateInputLanguage(newValue);
		});
	});
})(jQuery);


// Validation des périodes
function validatePeriods(event) {
	var allValidated = true;
	var dateRanges = document.querySelectorAll('#date-fields .date-range')
	for (var i = 0; i < dateRanges.length; i++) {
		var dateRange = dateRanges[i];
		var validated = true;
		var id = $(dateRange).attr('id');
		// On ne lance la validation que si une période a déjà été sélectionnée
		// et que l'élément ne contient pas la classe "hide"
		if ($(dateRange).text().indexOf('-') > 0 
				&& $(dateRange).parents('.lfr-form-row').attr('class').indexOf('hide') === -1) {
			var startDate = moment($(dateRange).text().split(' - ')[0], 'DD/MM/YYYY');
			var endDate = moment($(dateRange).text().split(' - ')[1], 'DD/MM/YYYY');
			var otherDateRanges = document.querySelectorAll('#date-fields .date-range');
			for (var j = 0; j < otherDateRanges.length; j++) {
				var otherDateRange = document.querySelectorAll('#date-fields .date-range')[j];
				var otherId = $(otherDateRange).attr('id');
				if (otherId !== id && $(otherDateRange).text().indexOf('-') > 0  
						&& $(otherDateRange).parents('.lfr-form-row').attr('class').indexOf('hide') === -1) {
					var otherStartDate = moment($(otherDateRange).text().split(' - ')[0], 'DD/MM/YYYY');
					var otherEndDate = moment($(otherDateRange).text().split(' - ')[1], 'DD/MM/YYYY');
				    if (startDate.isSameOrBefore(otherEndDate) && endDate.isSameOrAfter(otherStartDate)) {
				    	validated = false;
				    	break;
				    }
				}
			}
		}
		if (!validated) {
			$('.event-period-conflict', $(dateRange).parent()).show();
            $('html,body').animate({scrollTop: $(dateRange).offset().top - 100}, 'slow');
			allValidated = false;
		}
		
	}
	
	if (!allValidated) {
		event.preventDefault();
	} else {
		autoFields.save(event.target);
	}
	return allValidated;
}

// Autocomplete des lieux
jQuery(function() {
	if (!!window.placeAutocompleteURL) {
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
				jQuery('#place-autocomplete-hidden-value input').val(
						suggestion.data);
				jQuery('input.selected-place').val(suggestion.value);
			},
			appendTo : '.place-autocomplete-input-wrapper'
		};
		jQuery('.place-autocomplete-input-wrapper input').autocomplete(
				options);
	}
});
