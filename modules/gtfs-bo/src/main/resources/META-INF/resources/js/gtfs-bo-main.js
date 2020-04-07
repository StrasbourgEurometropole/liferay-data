jQuery(function() {
	var namespace = '_eu_strasbourg_portlet_gtfs_GTFSBOPortlet_';

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

function comparDatesYMD(startDate, endDate) {
	var startDay = parseInt(startDate.substr(8, 2));
	var startMonth = parseInt(startDate.substr(5, 2)) - 1;
	var startYear = parseInt(startDate.substr(0, 4));
	var date1 = new Date(startYear, startMonth, startDay, 0, 0, 0, 0);

    var endDay = parseInt(endDate.substr(8, 2));
    var endMonth = parseInt(endDate.substr(5, 2)) - 1;
    var endYear = parseInt(endDate.substr(0, 4));
    var date2 = new Date(endYear, endMonth, endDay, 0, 0, 0, 0);

	// si la date d'arrviée et superieur a la date de depart en afficher un
	// message d'erreur
	if (date1.getTime() > date2.getTime()) {
		return false;
	} else {
		return true;
	}

}
