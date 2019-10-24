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
            exportFormat: namespace + "_exportFormat",
            template: namespace + "_template",
            dataOrder: namespace + "_dataOrder",
        };

        //Fill data array
        var data = {};
        data[fields.title] = getValueWithName(values, fields.title);
        data[fields.startDate.replace("[index]", 0)] = getValueWithName(values, fields.startDate.replace("[index]", 0));
        data[fields.endDate.replace("[index]", 0)] = getValueWithName(values, fields.endDate.replace("[index]", 0));

        var vocabularies = getCategories(values, "[index]", fields.vocabularyId, fields.vocabularySelect);

        var i = 0;
        $.each(vocabularies, function(key, value) {
            data[fields.vocabularyId.replace("[index]", i)] = key;
            data[fields.vocabularySelect.replace("[index]", i)] = value;
            i++;
        });

        data[fields.vocabularyNumber] = getValueWithName(values, fields.vocabularyNumber);
        data[fields.tags] = getValueWithName(values, fields.tags).split(',');
        data[fields.language] = getValueWithName(values, fields.language);
        data[fields.exportFormat] = getValueWithName(values, fields.exportFormat);
        data[fields.template] = getValueWithName(values, fields.template);
        data[fields.dataOrder] = getValueWithName(values, fields.dataOrder);

        AUI().use('aui-io-request', function(A) {
            A.io.request(exportResourceUrl, {
                method : 'POST',
                data:data,
                on: {
                    success: function(data) {
                        console.log(data);
                        $('#result').html(data);
                    }
                }
            });
        });
    });

})(jQuery);

(function($) {

    var namespace = "_eu_strasbourg_portlet_agendaExport_AgendaExportBOPortlet_";
    var aggregationLevelSelect = $('#'+ namespace +'aggregationLevel');
    var firstAggregationBlock = $('#firstAggregationBlock');
    var secondAggregationBlock = $('#secondAggregationBlock');
    var firstAggregationTypeSelect = $('#'+ namespace +'firstAggregationType');
    var secondAggregationTypeSelect = $('#'+ namespace +'secondAggregationType');
    var vocabulariesSelect = $('.vocabulary-select');
    var firstCategorySelect = $('#'+ namespace +'firstAggregationCategory');
    var secondCategorySelect = $('#'+ namespace +'secondAggregationCategory');

    /** Affichage des champs **/

    //Affichage des types d'agrégations
    aggregationLevelSelect.on("change", function() {
        var value = $(this).val();

        if(value === "0") {
            firstAggregationBlock.hide();
            secondAggregationBlock.hide();
        } else if(value === "1") {
            firstAggregationBlock.show();
            secondAggregationBlock.hide();
        } else if(value === "2") {
            firstAggregationBlock.show();
            secondAggregationBlock.show();
        }
    });

    //Affichage des champs (première agrégation)
    firstAggregationTypeSelect.on("change", function() {
        var value = $(this).val()
        if(value === "VOCABULARY"){
            vocabulariesSelect.closest('.aggregationFields').find('#'+ namespace +'firstAggregationVocabulary').closest(".wrapper").show();
            firstCategorySelect.closest(".wrapper").hide();
        } else if(value === "CATEGORY") {
            vocabulariesSelect.closest('.aggregationFields').find('#'+ namespace +'firstAggregationVocabulary').closest(".wrapper").show();
            firstCategorySelect.closest(".wrapper").show();
        } else {
            vocabulariesSelect.closest('.aggregationFields').find('#'+ namespace +'firstAggregationVocabulary').closest(".wrapper").hide();
            firstCategorySelect.closest(".wrapper").hide();
        }
    });

    //Affichage des champs (deuxième agrégation)
    secondAggregationTypeSelect.on("change", function() {
        if($(this).val() === "VOCABULARY"){
            vocabulariesSelect.closest('.aggregationFields').find('#'+ namespace +'secondAggregationVocabulary').closest(".wrapper").show();
            secondCategorySelect.closest(".wrapper").hide();
        } else if($(this).val() === "CATEGORY") {
            vocabulariesSelect.closest('.aggregationFields').find('#'+ namespace +'secondAggregationVocabulary').closest(".wrapper").show();
            secondCategorySelect.closest(".wrapper").show();
        } else {
            vocabulariesSelect.closest('.aggregationFields').find('#'+ namespace +'secondAggregationVocabulary').closest(".wrapper").hide();
            secondCategorySelect.closest(".wrapper").hide();
        }
    });

    //Chargements des categories en fonction du vocabulaire choisi
    vocabulariesSelect.on("change", function() {
        var vocabularySelect = this;
        var select = null;
        if($(this).val() != ""){
            // on initialise le select des catégories parentes
            Liferay.Service(
                '/agenda.agendaexport/get-parent-categories', {
                    vocabularyId: $(this).val(),
                    localeId: Liferay.ThemeDisplay.getLanguageId()
            }).then(function(data) {

                //Choose the right select
                var name = $(vocabularySelect).attr("name");
                if(name === namespace+"firstAggregationVocabulary") {
                    select = firstCategorySelect;
                } else if (name === namespace+"secondAggregationVocabulary") {
                    select = secondCategorySelect;
                }

                $(select).find('option').remove();
                select.append('<option class="" value=""></option>');
                jQuery.each(data, function(index, categ){
                    select.append('<option class="" value="' + categ.id + '">' + categ.title + ' </option>');
                });
                select.show();
            });
        }else{
            firstCategorySelect.hide();
            secondCategorySelect.hide();
        }
    });
})(jQuery);