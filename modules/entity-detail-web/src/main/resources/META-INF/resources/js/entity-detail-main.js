// Validation formulaire contact
jQuery(function() {
	$('.entity-detail-portlet .contact-form').on('submit', function(e) {
		$('.all-fields-required-message').hide();
		$('.invalid-mail-message').hide();
		$('#g-recaptcha-response').attr('name', '_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_g-recaptcha-response');
		$('input.first-name, input.last-name, input.email, textarea.message').each(function() {
			$(this).removeClass('error');
			if ($(this).val() === "") {
				e.preventDefault();
				$(this).addClass('error');
				$('.all-fields-required-message').show();
			}
			if( !/(.+)@(.+){2,}\.(.+){2,}/.test($('input.email').val())) {
				e.preventDefault();
				$('input.email').addClass('error');
				$('.invalid-mail-message').show();
			}
		});
	});
});
