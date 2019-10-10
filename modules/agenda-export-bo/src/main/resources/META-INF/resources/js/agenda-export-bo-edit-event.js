// Transformation des champs select-multiple
new Choices('.choices-element', {
	removeItemButton: true
});

// Périodes
var autoFields = undefined; // Référence au champ répétable (setté plus loin)
(function($) {
	var namespace = "_eu_strasbourg_portlet_agendaExport_AgendaExportBOPortlet_"; // Namespace du portlet
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
	
	/**
	 * RangePicker permettant la création à la chaîne
	 */
	// Activation du RangePicker 
	$('#' + namespace + 'periodGenerator').daterangepicker({
		autoApply: false,
		parentEl: '.portlet-body',
		locale: dateRangePickerLocaleSettings
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

//option d'Export
(function($) {
    var namespace = "_eu_strasbourg_portlet_agendaExport_AgendaExportBOPortlet";

    /**
     * Retourne la valeur d'un champ dans une liste d'objet donnée
     */
    var getValueWithName = function(items, name) {
        for(var index in items) {
            if(items[index].name === name) {
                return items[index].value;
            }
        }
    };

    /**
     * Retourne les valeurs d'un champ dans une liste d'objet donnée
     */
    var getAllValuesWithName = function(items, name) {
        var values = [];
        for(var index in items) {
            if(items[index].name === name) {
                values.push(items[index].value);
            }
        }

        if(values.length === 0) {
            return undefined;
        }

        return values;
    }

    /**
     * Renvoit les categories triées par vocabulaires
     */
    var getCategories = function(items, token, idField, name) {

        var vocabularies = {};
        var key = 0;

        for(var index in items) {
            var itemName = items[index].name;
            var itemValue = items[index].value;
            key = itemName.replace(/\D/g,'');
            itemName = itemName.replace(/[0-9]/g, token);

            if(itemName === idField && key !== undefined) {
                var fieldToFind = name.replace("[index]", key);
                var values = getAllValuesWithName(items, fieldToFind);
                if(values !== undefined) {
                    vocabularies[itemValue] = values;
                }
            }
        }

        return vocabularies;
    }

    /**
     * Appel de l'export
     *
     */
    $("#"+ namespace + "_" + "export-btn").on("click", function() {

        //Récupération des valeurs du formulaire
        var values = $("#" + namespace + "_" + "fm").serializeArray();

        //Champs du formulaire
        var fields = {
            title: namespace + "_title",
            startDate: namespace + "_startDate[index]",
            endDate: namespace + "_endDate[index]",
            vocabularyId: namespace + "_vocabulary_[index]_id",
            vocabularySelect: namespace + "_vocabulary_[index]_select",
            vocabularyNumber: namespace + "_vocabulary_number",
            tags: namespace + "_assetTagNames",
            language: namespace + "_language",
        };

        var AgendaExport= {
            title: getValueWithName(values, fields.title),
            startDate: getValueWithName(values, fields.startDate.replace("[index]", 0)),
            endDate: getValueWithName(values, fields.endDate.replace("[index]", 0)),
            vocabularies: getCategories(values, "[index]", fields.vocabularyId, fields.vocabularySelect),
            tags: getValueWithName(values, fields.tags).split(','),
            language: getValueWithName(values, fields.language)
        };

        console.log(AgendaExport);

//        Liferay.Service(
//        '/gtfs.arret/get-arret-real-time', {
//            stopCode: feature.properties.code
//        },
//        function(json) {
//            console.log(json);
//        }
    });

})(jQuery);