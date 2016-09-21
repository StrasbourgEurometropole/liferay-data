jQuery(function() {
	var namespace = '_eu_strasbourg_portlet_link_LinkBOPortlet_';

	// Lors d'un clic sur le selecteur de langues global
	jQuery('body').off('click').on('click', '.lfr-translation-manager-translation', function() {
		// on déclenche un clic sur chaque drapeau de langue correspondant
		var language = $(this).attr('locale') ? $(this).attr('locale') : 'fr_FR';
		var flags = jQuery('.palette-item[data-value=' + language + ']');
		flags.trigger('click');

		// on s'occupe également des pickers
		flags.trigger('click');
		$('.picker-fileId').removeClass('active');
		$('.picker-fileId.' + language).addClass('active');
	});
});
