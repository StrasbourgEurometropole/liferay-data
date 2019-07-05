// Lors du clic sur le bouton 'save-as-draft', on set le champ cmd à la valeur correspondante
jQuery('body').off('click').on('click', '#' + namespace + 'save-as-draft', function() {
	jQuery('input[name=' + namespace + 'newStatus]')[0].value
	= Liferay.Workflow.STATUS_DRAFT;
})
// De même pour le bouton 'save-and-submit'
.on('click', '#' + namespace + 'save-and-submit', function() {
	jQuery('input[name=' + namespace + 'newStatus]')[0].value
	= Liferay.Workflow.STATUS_PENDING;
})
.on('click', '#' + namespace + 'save-and-approve', function() {
	jQuery('input[name=' + namespace + 'newStatus]')[0].value
	= Liferay.Workflow.STATUS_APPROVED;
})
.on('click', '#' + namespace + 'save-and-deny', function() {
	jQuery('input[name=' + namespace + 'newStatus]')[0].value
	= Liferay.Workflow.STATUS_DENIED;
})
.on('click', '#' + namespace + 'save-and-deny-deletion', function() {
	jQuery('input[name=' + namespace + 'newStatus]')[0].value
	= Liferay.Workflow.STATUS_ANY;
})
.on('click', '#' + namespace + 'save-and-request-deletion', function() {
	jQuery('input[name=' + namespace + 'newStatus]')[0].value
	= 8; // STATUS_IN_TRASH
});

// Gestion du champ conditionnel service
$('[name=serviceType]').on('click change', function(e) {
	var classOfDivToShow = e.target.value;
	var classOfDivToHide = 'emsServiceotherService'.replace(classOfDivToShow, '');
	$('.emsService, .otherService').hide();	
	$('.' + classOfDivToShow).show();
	$('.' + classOfDivToHide + ' input').val('');
	$('.' + classOfDivToHide + ' select').val('');
});

//Autocomplete des lieux
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
				jQuery('.selected-place span').text(suggestion.value);
				jQuery('.place-autocomplete-input-wrapper input').hide();
				jQuery('.show-link-manual-place').hide();
				jQuery('.selected-place').show();
			},
			appendTo : '.place-autocomplete-input-wrapper'
		};
		jQuery('.place-autocomplete-input-wrapper input').autocomplete(
				options);
	}
});

//Réinitialisation du lieu
$('.remove_place').on('click',function(e){
    jQuery('#place-autocomplete-hidden-value input').val('');
    jQuery('.place-autocomplete-input-wrapper input').val('');
    jQuery('.place-autocomplete-input-wrapper input').show();
    jQuery('.show-link-manual-place').show();
    jQuery('.selected-place').hide();
});

// Switch entre les sélections de lieux
$('.show-autocomplete-place').on('click', function(e) {
	e.preventDefault();
	$('.place-manual').hide();
	$('.place-autocomplete').show();
	$('.place-manual input').val('');
	$('.place-manual option').attr('selected', false);
});
$('.show-manual-place').on('click', function(e) {
	e.preventDefault();
	$('.place-autocomplete').hide();
	$('.place-manual').show();
	$('.place-autocomplete input').val('');
	$('.place-autocomplete option').attr('selected', false);
});

//Périodes
var autoFields = undefined; // Référence au champ répétable (setté plus loin)
(function($) {
	var dateRangePickerLocaleSettings =  { // Configuration française du dateRangePicker
		format: 'DD/MM/YYYY',
		applyLabel: 'Ajouter',
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
		parentEl: '.portlet-boundary_eu_strasbourg_portlet_agenda_portlet_AgendaCampaignPortlet_ .portlet-body',
		opens: 'right',
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
	// On active le composant
	$('span.date-range').daterangepicker(options);
	// On attache l'événement de changement de range de date
	$('span.date-range').on('apply.daterangepicker', onDateChange);	
	
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
		$('#dateRange' + index).daterangepicker(options);
		$('#dateRange' + index).on('apply.daterangepicker', onDateChange);
	});
	
	/**
	 * RangePicker permettant la création à la chaîne
	 */
	// Activation du RangePicker 
	$('#' + namespace + 'periodGenerator').daterangepicker({
		autoApply: false,
		parentEl: '.portlet-boundary_eu_strasbourg_portlet_agenda_portlet_AgendaCampaignPortlet_ .portlet-body',
		locale: dateRangePickerLocaleSettings
	});
	// Lors du clic sur le bouton "Appliquer
	$('#' + namespace + 'periodGenerator').on('apply.daterangepicker', function (ev, picker) {
		// On laisse le calendrier ouvert
		$('#' + namespace + 'periodGenerator').trigger('click');

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

// Choices.js et dropdown des thèmes et des types en fonction de la dropdown des campagnes
(function($) {
	// Champs select multiples
	var choicesOptions = {
		removeItemButton: true,
		loadingText: 'Chargement...',
	    noResultsText: 'Aucun résultat trouvé',
	    noChoicesText: 'Aucune option disponible',
	    itemSelectText: 'Cliquer pour sélectionner',
	    shouldSort: false
	}

	var manifestationChoices = new Choices('[name=' + namespace + 'manifestations][multiple]', choicesOptions);
	var themeChoices = new Choices('[name=' + namespace + 'themesIds][multiple]', choicesOptions);
	var typeChoices = new Choices('[name=' + namespace + 'typesIds][multiple]', choicesOptions);
	var publicChoices = new Choices('[name=' + namespace + 'publicsIds][multiple]', choicesOptions);
	window.themeChoices = themeChoices; // On en a besoin ci dessous

	// Met à jour la dropdown des thèmes selon le paramètre "campaignId"
	// La désactive si campaignId est vide
	function updateThemeOptions(campaignId) {
		if (!campaignId) {
			themeChoices.clearInput();
			themeChoices.clearStore();
			themeChoices.disable();
		} else {
			var themesIdsString = campaignThemes[campaignId];
			var themesIds = themesIdsString.split(',');
			var choices = [];
			for (var i = 0; i < themesIds.length; i++) {
				choices.push({
					label: themeLabels[themesIds[i]],
					value: themesIds[i],
					selected: eventThemes.indexOf(themesIds[i]) > -1
				});
			}
			themeChoices.clearInput();
			themeChoices.clearStore();
			themeChoices.setChoices(choices, 'value', 'label', true);
			themeChoices.enable();
		}	
	}

	// Met à jour la dropdown des types selon le paramètre "campaignId"
	// La réinitialise si campaignId est vide
	function updateTypeOptions(campaignId) {
		if (!campaignId || campaignTypes[campaignId] == '') {
			typeChoices.clearInput();
			typeChoices.clearStore();
			var choices = [];
			for (var type in typeLabels) {
				choices.push({
					label: typeLabels[type],
					value: type,
					selected: eventTypes.indexOf(type) > -1
				});
			}
			typeChoices.setChoices(choices, 'value', 'label', true);
		} else {
			var typesIdsString = campaignTypes[campaignId];
			var typesIds = typesIdsString.split(',');
			var choices = [];
			for (var i = 0; i < typesIds.length; i++) {
				choices.push({
					label: typeLabels[typesIds[i]],
					value: typesIds[i],
					selected: eventTypes.indexOf(typesIds[i]) > -1
				});
			}
			typeChoices.clearInput();
			typeChoices.clearStore();
			typeChoices.setChoices(choices, 'value', 'label', true);
		}
	}
	
	var campaignDropdown = $('[name=' + namespace + 'campaignId]');
	campaignDropdown.on('change', function(event) {
		updateThemeOptions(campaignDropdown.val());
	    updateTypeOptions(campaignDropdown.val());
	});
	updateThemeOptions(campaignDropdown.val());
	updateTypeOptions(campaignDropdown.val());
	
})(jQuery);


// Validation des périodes (et des langues)
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
			allValidated = false;
		}
		
	}
	
	// Validation des langues
	var languages = [];
	var params = [];
	var numberOfPeriods = 0;
	for (var i = 0; i < event.target._node.elements.length; i++) {
	   	var e = event.target._node.elements[i];
		if (e.value && /.+_([a-zA-Z]{2})_([a-zA-Z]){2}$/.test(e.name)) {
			params.push(e);
			var locale = e.name.substring(e.name.length - 5);
			if(languages.indexOf(locale) == -1) {
				languages.push(locale);
			}
	    }
		if (!!e.name && e.name.indexOf('_startDate') > -1 && e.value && $(e.parentElement).is(':visible')) {
			numberOfPeriods++;
		}
	}
	
	var languageValidation = [];
	var hasLanguageError = false;
	for (var i = 0; i < languages.length; i++) {
		var titles = params.filter(function(p) {
			return p.name.indexOf('_title_') > -1 && p.name.indexOf(languages[i]) > -1 && p.value;
		});
		var titleOk = titles.length == 1;
		
		var descriptions = params.filter(function(p) {
			return p.name.indexOf('_description_') > -1 && p.name.indexOf(languages[i]) > -1 && p.value;
		});
		var descriptionOk = descriptions.length == 1;

		var timeDetails = params.filter(function(p) {
			return $(p.parentElement.parentElement).is(':visible') 
				&& p.name.indexOf('_timeDetail') > -1 
				&& p.name.indexOf(languages[i]) > -1 
				&& p.value;
		});
		var timeDetailOk = timeDetails.length >= numberOfPeriods;
		
		var placeNameOk = true;
		if (jQuery('.place-manual').css('display') != 'none') {
			var placeNames = params.filter(function(p) {
				return p.name.indexOf('_placeName_') > -1 && p.name.indexOf(languages[i]) > -1 && p.value;
			});
			placeNameOk = placeNames.length == 1;
		}
		
		
		if (!titleOk || !descriptionOk || !timeDetailOk || !placeNameOk) {
			hasLanguageError = true;
		}
		languageValidation.push({
			language: languages[i],
			titleOk: titleOk,
			descriptionOk: descriptionOk,
			timeDetailOk: timeDetailOk,
			placeNameOk: placeNameOk
		});
	}
	$('.language-error').hide();
	if (hasLanguageError) {
		var txt = "<ul>";
		for (var i = 0; i < languageValidation.length; i++) {
			if (!languageValidation[i].titleOk) {
				txt += "<li>Dans la langue '" + languageValidation[i].language + "' veuillez remplir le titre</li>";
			}
			if (!languageValidation[i].descriptionOk) {
				txt += "<li>Dans la langue '" + languageValidation[i].language + "' veuillez remplir la description</li>";
			}
			if (!languageValidation[i].placeNameOk) {
				txt += "<li>Dans la langue '" + languageValidation[i].language + "' veuillez remplir le nom du lieu</li>";
			}
			if (!languageValidation[i].timeDetailOk) {
				txt += "<li>Dans la langue '" + languageValidation[i].language + "' veuillez remplir tous les horaires</li>";
			}
		}
		txt += "</ul>"
		$('.language-error').html(txt);
		$('.language-error').show();
	}	
	
	if (!allValidated || hasLanguageError) {
		event.preventDefault();
	} else {
		autoFields.save(event.target);
	}
	return allValidated || hasLanguageError;
}