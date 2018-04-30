jQuery(function() {
	var namespace = '_eu_strasbourg_portlet_project_ProjectBOPortlet_';

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
		jQuery('input[name=' + namespace + 'workflowAction]')[0].value
		= Liferay.Workflow.ACTION_PUBLISH;
	})
	// Lors du clic sur le bouton 'dépublier', on set le champ 'forceStatus'
	.on('click', '#' + namespace + 'save-as-draft', function() {
		jQuery('input[name=' + namespace + 'workflowAction]')[0].value
		= Liferay.Workflow.ACTION_SAVE_DRAFT;
	});;
});

function deleteTimeline(idTimeline) {
	var namespace = "_eu_strasbourg_portlet_project_ProjectBOPortlet_";
	var namespaceAUI = "#" + namespace;
	$('#timeline' + idTimeline).remove();
	var nbTimeline = $(namespaceAUI + 'nbTimeline').val();
	alert(nbTimeline),
	$(namespaceAUI + 'nbTimeline').val(nbTimeline - 1);
}

function addTimeline() {
	var namespace = "_eu_strasbourg_portlet_project_ProjectBOPortlet_";
	var namespaceAUI = "#" + namespace;
	var nbTimeline = $(namespaceAUI + 'nbTimeline').val();
	var lastTimeline = 0;
	if (nbTimeline > 0) {
		lastTimeline = nbTimeline;
	}
	$.ajax({
		url : getTimelineRowJSPURL + '&' + namespace + 'indexTimeline=' + idTimeline
				+ '&' + namespace + 'jour=' + jour + '&' + namespace
				+ 'indexTimeline=' + lastSlot,
		success : function(html) {
			$('#' + idPeriod + '-' + jour).before(html);
		}
	});
	$(namespaceAUI + 'nbSlot' + idPeriod + '-' + jour)
			.val(parseInt(nbSlot) + 1);
}
