jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_agenda_AgendaBOPortlet_";
	// Lors d'un clic sur le selecteur de langues global
	jQuery('body').off('click').on('click', '.lfr-translation-manager-translation', function() {
		// on déclenche un clic sur chaque drapeau de langue correspondant
		var language = $(this).attr('locale') ? $(this).attr('locale') : Liferay.ThemeDisplay.getLanguageId();
		var flags = jQuery('.palette-item[data-value=' + language + ']');
		
		// On s'occupe également des pickers
		flags.trigger('click');
		$('.picker-fileId').removeClass('active');
		$('.picker-fileId.' + language).addClass('active');
	})
	// Lors du clic sur le bouton 'publier', on set le champ 'forceStatus'
	.on('click', '#' + namespace + 'publish', function() {
		jQuery('input[name=' + namespace + 'workflowAction]')[0].value
		= Liferay.Workflow.ACTION_PUBLISH;
	})
	// Lors du clic sur le bouton 'dépublier', on set le champ 'forceStatus'
	.on('click', '#' + namespace + 'save-as-draft', function() {
		jQuery('input[name=' + namespace + 'workflowAction]')[0].value
		= Liferay.Workflow.ACTION_SAVE_DRAFT;
	});
});


// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_agenda_AgendaBOPortlet_";
	
	$('[name=placeType]').on('click change', function(e) {
		var classOfDivToShow = e.target.value;
		$('.sig, .manual').hide();	
		$('.' + classOfDivToShow).show();
		setConditionalValidators();
	});
	
	$('[name=imageType]').on('click change', function(e) {
		var classOfDivToShow = e.target.value;
		$('.internalImage, .externalImage').hide();	
		$('.' + classOfDivToShow).show();
		setConditionalValidators();
	});
	
	Liferay.on('allPortletsReady', setConditionalValidators);
	
	function setConditionalValidators() {
		// Validation des champos obligatoires conditionnels
		AUI().use('liferay-form', function() {
			var rules = Liferay.Form.get(namespace + 'fm').formValidator.get('rules');
			if (jQuery('.manual').is(':visible')) {
				rules[namespace + 'placeSIGId'].required = false;
				rules[namespace + 'placeName'].required = true;
				rules[namespace + 'placeCity'].required = true;
			} else {
				rules[namespace + 'placeSIGId'].required = true;
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
		});
		
	}
	
});

// Périodes
var autoFields = undefined; // Référence au champ répétable (setté plus loin)
(function($) {
	var namespace = "_eu_strasbourg_portlet_agenda_AgendaBOPortlet_"; // Namespace du portlet
	var dateRangePickerLocaleSettings =  { // Configuration française du dateRangePicker
		format: 'DD/MM/YYYY',
		applyLabel: 'Appliquer',
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
		// Création de l'autofield
		autoFields = new Liferay.AutoFields({
			contentBox : '#date-fields',
			fieldIndexes : namespace + 'periodIndexes',
			namespace : namespace,
			url: getPeriodRowJSPURL
		}).render();
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
	$('#periodGenerator').daterangepicker({
		autoApply: false,
		parentEl: '.portlet-body',
		locale: dateRangePickerLocaleSettings
	});
	// Lors du clic sur le bouton "Appliquer
	$('#periodGenerator').on('apply.daterangepicker', function (ev, picker) {
		// On laisse le calendrier ouvert
		$('#periodGenerator').trigger('click');

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
