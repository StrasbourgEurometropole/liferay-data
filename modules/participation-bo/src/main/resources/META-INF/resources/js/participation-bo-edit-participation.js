// Champs conditionnelles
jQuery(function() {
	var namespace = "_eu_strasbourg_portlet_participation_ParticipationBOPortlet_";
	
	$('[name=imageType]').on('click change', function(e) {
		var classOfDivToShow = e.target.value;
		var classOfDivToHide = 'internalImageexternalImage'.replace(classOfDivToShow, '');
		$('.internalImage, .externalImage').hide();	
		$('.' + classOfDivToShow).show();
		$('.' + classOfDivToHide + ' input').val('');
		$('.' + classOfDivToHide + ' .image-thumbnail').remove();
		setConditionalValidators();
	});
	
	Liferay.on('allPortletsReady', setConditionalValidators);

});