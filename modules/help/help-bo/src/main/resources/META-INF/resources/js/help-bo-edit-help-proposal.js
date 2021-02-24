// On garde une référence globale aux ²

jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_help_HelpBOPortlet_";
	
	// Selection d'image interne ou externe
	$('[name=imageType]').on('click change', function(e) {
		var classOfDivToShow = e.target.value;
		var classOfDivToHide = 'internalImageexternalImage'.replace(classOfDivToShow, '');
		$('.internalImage, .externalImage').hide();	
		$('.' + classOfDivToShow).show();
		$('.' + classOfDivToHide + ' input').val('');
		$('.' + classOfDivToHide + ' .image-thumbnail').remove();
		setConditionalValidators();
	});
});

//Soumission du formulaire
function submitForm(event) {
	return true;
}

