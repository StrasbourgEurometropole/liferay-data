jQuery(function() {
	var namespace = '_eu_strasbourg_portlet_place_PlaceBOPortlet_';

	// Lors d'un clic sur le selecteur de langues global
	jQuery('body').off('click').on('click', '.lfr-translation-manager-translation', function() {
		// on déclenche un clic sur chaque drapeau de langue correspondant
		var language = $(this).attr('locale') ? $(this).attr('locale') : Liferay.ThemeDisplay.getLanguageId();
		var flags = jQuery('.palette-item[data-value=' + language + ']');
		flags.trigger('click');

		// on s'occupe également des pickers
		flags.trigger('click');
		$('.picker-fileId').removeClass('active');
		$('.picker-fileId.' + language).addClass('active');
	})	
	// Lors du clic sur le bouton 'publier', on set le champ 'workflowStatus'
	.on('click', '#' + namespace + 'publish', function() {
		if(jQuery('input[name=' + namespace + 'workflowAction]')[0] != undefined){
			jQuery('input[name=' + namespace + 'workflowAction]')[0].value
			= Liferay.Workflow.ACTION_PUBLISH;
		}
	})
	// Lors du clic sur le bouton 'dépublier', on set le champ 'forceStatus'
	.on('click', '#' + namespace + 'save-as-draft', function() {
		if(jQuery('input[name=' + namespace + 'workflowAction]')[0] != undefined){
			jQuery('input[name=' + namespace + 'workflowAction]')[0].value
			= Liferay.Workflow.ACTION_SAVE_DRAFT;
		}
	});;
});

function affichageDates(objet, id) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	if (objet.checked) {
		$('.dates' + id).hide();
	} else {
		$('.dates' + id).show();
	}

}

function affichageOuverture(objet, id) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	if (objet.checked) {
		$('.ouvertures' + id).hide();
	} else {
		$('.ouvertures' + id).show();
	}

}

function affichageHeures(objet, id) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	if (objet.checked) {
		$('.heure' + id).hide();
	} else {
		$('.heure' + id).show();
	}

}

function copyHoraire(idPeriod) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	for (var indexSlot = 0; indexSlot < 3; indexSlot++) {
		var heureDebut = $(namespaceAUI + 'startHour' + idPeriod + '-0-' + indexSlot).val();
		var heureFin = $(namespaceAUI + 'endHour' + idPeriod + '-0-' + indexSlot).val();
		for (var jour = 1; jour < 7; jour++) {
			$(namespaceAUI + 'startHour' + idPeriod + '-' + jour + '-' + indexSlot).val(heureDebut);
			$(namespaceAUI + 'endHour' + idPeriod + '-' + jour + '-' + indexSlot).val(heureFin);
		}
	}

}
