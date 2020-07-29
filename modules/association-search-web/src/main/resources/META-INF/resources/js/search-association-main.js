jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_association_SearchAssociationPortlet_";
	var namespaceAUI = "#" + namespace;


    var domainsSelect = $('select.domains');
    var specialityWidget = $('.widget.speciality');
    var specialitiesSelect = $('select.specialities');
    var subSpecialityWidget = $('.widget.subSpeciality');
    var subSpecialitiesSelect = $('select.subSpecialities');
    var subSubSpecialityWidget = $('.widget.subSubSpeciality');
    var subSubSpecialitiesSelect = $('select.subSubSpecialities');

    // Lors d'une selection de domaine
    domainsSelect.change(function() {
        if($(this).val() != ""){
            // on initialise le select des spécialités
            Liferay.Service(
                '/strasbourg.strasbourg/get-practice-categories', {
                    parentCategoryId: $(this).val(),
                    localeId: Liferay.ThemeDisplay.getLanguageId()
            }).then(function(data) {
                specialitiesSelect.empty();
                specialityWidget.find('.customSelectInner').text("");
                specialitiesSelect.append('<option class="" value=""> </option>');
                jQuery.each(data, function(index, categ){
                    specialitiesSelect.append('<option class="" value="' + categ.id + '">' + categ.title + ' </option>');
                });
                // on affiche les spécialités
                specialityWidget.show();
            });
            // on cache les autres select
            subSpecialityWidget.hide();
            subSubSpecialityWidget.hide();
        }else{
            specialityWidget.hide();
            subSpecialityWidget.hide();
            subSubSpecialityWidget.hide();
        }
    });

    // Lors d'une selection de spécialité
    specialitiesSelect.change(function() {
        if($(this).val() != ""){
            // on initialise le select des sous-spécialités
            Liferay.Service(
                '/strasbourg.strasbourg/get-practice-categories', {
                    parentCategoryId: $(this).val(),
                    localeId: Liferay.ThemeDisplay.getLanguageId()
            }).then(function(data) {
                // on réinistialise les sous-spécialités
                subSpecialitiesSelect.empty();
                subSpecialityWidget.find('.customSelectInner').text("");
                subSpecialitiesSelect.append('<option class="" value=""> </option>');
                if(data.length > 0){
                    jQuery.each(data, function(index, categ){
                        subSpecialitiesSelect.append('<option class="" value="' + categ.id + '">' + categ.title + ' </option>');
                    });
                    // on affiche les sous-spécialités
                    subSpecialityWidget.show();
                }else{
                    // on cache les sous-spécialités
                    subSpecialityWidget.hide();
                }
            });

            // on cache les autres select
            subSubSpecialityWidget.hide();
        }else{
            subSpecialityWidget.hide();
            subSubSpecialityWidget.hide();
        }
    });

    // Lors d'une selection de sous-spécialité
    subSpecialitiesSelect.change(function() {
        if($(this).val() != ""){
            // on initialise le select des sous-sous-spécialités
            Liferay.Service(
                '/strasbourg.strasbourg/get-practice-categories', {
                    parentCategoryId: $(this).val(),
                    localeId: Liferay.ThemeDisplay.getLanguageId()
            }).then(function(data) {
                // on réinistialise les sous-sous-spécialités
                subSubSpecialitiesSelect.empty();
                subSubSpecialityWidget.find('.customSelectInner').text("");
                subSubSpecialitiesSelect.append('<option class="" value=""> </option>');
                if(data.length > 0){
                    jQuery.each(data, function(index, categ){
                        subSubSpecialitiesSelect.append('<option class="" value="' + categ.id + '">' + categ.title + ' </option>');
                    });
                    // on affiche les sous-sous-spécialités
                    subSubSpecialityWidget.show();
                }else{
                    // on cache les sous-sous-spécialités
                    subSubSpecialityWidget.hide();
                }
            });
        }else{
            subSubSpecialityWidget.hide();
        }
    });

	$(":submit").on('click', function(e) {
		setVocabulariesValidators(e);
	});

	function setVocabulariesValidators(event) {
		// Validation des champs obligatoires conditionnels
		AUI().use('liferay-form',function() {
			var domainHasValue = $(namespaceAUI + 'domain')
					.val().length > 0;
			if (!domainHasValue) {
			    $('p.error').show();
			    event.preventDefault();
			}else{
			    $('p.error').hide();
			}
		});
	}
});