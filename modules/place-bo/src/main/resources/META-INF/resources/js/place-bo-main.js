jQuery(function() {
	var namespace = '_eu_strasbourg_portlet_place_PlaceBOPortlet_';

	// Lors d'un clic sur le selecteur de langues global
	jQuery('body')
			.off('click')
			.on(
					'click',
					'.lfr-translation-manager-translation',
					function() {
						// on déclenche un clic sur chaque drapeau de langue
						// correspondant
						var language = $(this).attr('locale') ? $(this).attr(
								'locale') : Liferay.ThemeDisplay
								.getLanguageId();
						var flags = jQuery('.palette-item[data-value='
								+ language + ']');
						flags.trigger('click');

						// on s'occupe également des pickers
						flags.trigger('click');
						$('.picker-fileId').removeClass('active');
						$('.picker-fileId.' + language).addClass('active');
					})
			// Lors du clic sur le bouton 'publier', on set le champ
			// 'workflowStatus'
			.on(
					'click',
					'#' + namespace + 'publish',
					function() {
						if (jQuery('input[name=' + namespace
								+ 'workflowAction]')[0] != undefined) {
							jQuery('input[name=' + namespace
									+ 'workflowAction]')[0].value = Liferay.Workflow.ACTION_PUBLISH;
						}
					})
			// Lors du clic sur le bouton 'dépublier', on set le champ
			// 'forceStatus'
			.on(
					'click',
					'#' + namespace + 'save-as-draft',
					function() {
						if (jQuery('input[name=' + namespace
								+ 'workflowAction]')[0] != undefined) {
							jQuery('input[name=' + namespace
									+ 'workflowAction]')[0].value = Liferay.Workflow.ACTION_SAVE_DRAFT;
						}
					});
	;
});

function addPeriod() {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	var nbPeriod = $(namespaceAUI + 'nbPeriod').val();
	var lastPeriod = 0;
	if (nbPeriod > 0) {
		lastPeriod = parseInt($('input[name*=numPeriod]')[nbPeriod - 1].id
				.split(namespace + 'numPeriod')[1]) + 1;
	}
	var htmlOnglet = "<li role='presentation' id='onglet" + lastPeriod
			+ "' class='active' >" + "<a aria-controls='period" + lastPeriod
			+ "' href='#period" + lastPeriod
			+ "' data-toggle='tab' role='tab'>"
			+ Liferay.Language.get('period') + " " + (parseInt(lastPeriod) + 1)
			+ " <span class='btn-icon icon icon-trash' onClick='deletePeriod("
			+ lastPeriod + "); return false;'></span>" + "</a>" + "</li>";

	$.ajax({
		url : getperiodRowJSPURL + '&' + namespace + 'index=' + lastPeriod,
		success : function(html) {
			// on on enleve le focus à l'ancienne période
			$('li[class=active]').attr('class', '');
			$('div[class*=active]').attr('class', 'tab-pane fade in');

			$('.tab-content').append(html);
			$('#addPeriod').before(htmlOnglet);

			// on donne le focus à la nouvelle période
			$('#period' + lastPeriod).attr('class', 'tab-pane active fade in');
		}
	});

	// ajout de la fréquentation
	$.ajax({
		url : getattendanceRowJSPURL + '&' + namespace + 'indexPeriod='
				+ lastPeriod,
		success : function(html) {
			$('#attendanceContent').children().append(html);
		}
	});

	// on ajuste le nb de période
	$(namespaceAUI + 'nbPeriod').val(parseInt(nbPeriod) + 1);
}

function deletePeriod(idPeriod) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	var nbPeriod = $(namespaceAUI + 'nbPeriod').val();
	var selected = $('#onglet' + idPeriod).attr('class') == 'active';

	$('#onglet' + idPeriod).remove();
	$('#period' + idPeriod).remove();

	// si la période à supprimer a le focus, on le donne à la dernière période
	// ouverte
	if (nbPeriod > 1 && selected) {
		var lastPeriod = $('input[name*=numPeriod]')[nbPeriod - 2].id
				.split(namespace + 'numPeriod')[1];
		$('#period' + lastPeriod).attr('class', 'tab-pane active fade in');
		$('#onglet' + lastPeriod).attr('class', 'active');
	} else {
		if (nbPeriod == 1) {
			$('#noPeriod').attr('class', 'tab-pane active fade in');
			$('#addPeriod').attr('class', 'active');
		}
	}

	// on supprime la fréquentation
	$('#attendance' + idPeriod).remove();

	// on ajuste le nb de période
	$(namespaceAUI + 'nbPeriod').val(
			parseInt($(namespaceAUI + 'nbPeriod').val()) - 1);
}

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

function deleteSlot(idPeriod, jour, slot) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	$('#' + idPeriod + '-' + jour + '-' + slot).remove();
	var nbSlot = $(namespaceAUI + 'nbSlot' + idPeriod + '-' + jour).val();
	$(namespaceAUI + 'nbSlot' + idPeriod + '-' + jour).val(nbSlot - 1);
}

function addSlot(idPeriod, jour) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	var nbSlot = $(namespaceAUI + 'nbSlot' + idPeriod + '-' + jour).val();
	var lastSlot = 0;
	if (nbSlot > 0) {
		lastSlot = nbSlot;
	}
	$.ajax({
		url : getslotRowJSPURL + '&' + namespace + 'indexPeriod=' + idPeriod
				+ '&' + namespace + 'jour=' + jour + '&' + namespace
				+ 'indexSlot=' + lastSlot,
		success : function(html) {
			$('#' + idPeriod + '-' + jour).before(html);
		}
	});
	$(namespaceAUI + 'nbSlot' + idPeriod + '-' + jour)
			.val(parseInt(nbSlot) + 1);
}

function copyHoraire(idPeriod) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	var nbSlotLundi = $(namespaceAUI + 'nbSlot' + idPeriod + '-0').val();
	for (var indexSlot = 0; indexSlot < nbSlotLundi; indexSlot++) {
		var heureDebutLundi = $(
				namespaceAUI + 'startHour' + idPeriod + '-0-' + indexSlot)
				.val();
		var heureFinLundi = $(
				namespaceAUI + 'endHour' + idPeriod + '-0-' + indexSlot).val();
		for (var jour = 1; jour < 7; jour++) {
			var heureDebut = $(
					namespaceAUI + 'startHour' + idPeriod + '-' + jour + '-'
							+ indexSlot).val();
			var heureFin = $(
					namespaceAUI + 'endHour' + idPeriod + '-' + jour + '-'
							+ indexSlot).val();
			if (heureDebut != undefined && heureFin != undefined) {
				$(
						namespaceAUI + 'startHour' + idPeriod + '-' + jour
								+ '-' + indexSlot).val(heureDebutLundi);
				$(
						namespaceAUI + 'endHour' + idPeriod + '-' + jour + '-'
								+ indexSlot).val(heureFinLundi);
			} else {
				addSlot(idPeriod, jour, indexSlot, heureDebutLundi,
						heureFinLundi);
			}
		}
	}
}

function addSlot(idPeriod, jour, indexSlot, heureDebutLundi, heureFinLundi) {
	var namespace = "_eu_strasbourg_portlet_place_PlaceBOPortlet_";
	var namespaceAUI = "#" + namespace;
	var nbSlot = $(namespaceAUI + 'nbSlot' + idPeriod + '-' + jour).val();
	var lastSlot = 0;
	if (nbSlot > 0) {
		lastSlot = nbSlot;
	}
	$.ajax({
		url : getslotRowJSPURL + '&' + namespace + 'indexPeriod=' + idPeriod
				+ '&' + namespace + 'jour=' + jour + '&' + namespace
				+ 'indexSlot=' + lastSlot,
		success : function(html) {
			$('#' + idPeriod + '-' + jour).before(html);
			$(
					namespaceAUI + 'startHour' + idPeriod + '-' + jour + '-'
							+ indexSlot).val(heureDebutLundi);
			$(
					namespaceAUI + 'endHour' + idPeriod + '-' + jour + '-'
							+ indexSlot).val(heureFinLundi);
		}
	});
	$(namespaceAUI + 'nbSlot' + idPeriod + '-' + jour)
			.val(parseInt(nbSlot) + 1);
}

function comparDatesYMD(startDate, endDate) {
	var date1 = new Date();
	date1.setFullYear(startDate.substr(0, 4));
	date1.setMonth(startDate.substr(5, 2) -1);
	date1.setDate(startDate.substr(8, 2));
	date1.setHours(0);
	date1.setMinutes(0);
	date1.setSeconds(0);
	date1.setMilliseconds(0);

	var date2 = new Date();
	date2.setFullYear(endDate.substr(0, 4));
	date2.setMonth(endDate.substr(5, 2) -1);
	date2.setDate(endDate.substr(8, 2));
	date2.setHours(0);
	date2.setMinutes(0);
	date2.setSeconds(0);
	date2.setMilliseconds(0);

	// si la date d'arrviée et superieur a la date de depart en afficher un
	// message d'erreur
	if (date1.getTime() > date2.getTime()) {
		return false;
	} else {
		return true;
	}

}

function comparHour(startHour, endHour) {
	var date1 = new Date();
	date1.setHours(startHour.substr(0, 2));
	date1.setMinutes(startHour.substr(3, 2));
	date1.setSeconds(0);
	date1.setMilliseconds(0);

	var date2 = new Date();
	date2.setHours(endHour.substr(0, 2));
	date2.setMinutes(endHour.substr(3, 2));
	date2.setSeconds(0);
	date2.setMilliseconds(0);

	// si la date d'arrviée et superieur a la date de depart en afficher un
	// message d'erreur
	if (date1.getTime() >= date2.getTime()) {
		return false;
	} else {
		return true;
	}

}
