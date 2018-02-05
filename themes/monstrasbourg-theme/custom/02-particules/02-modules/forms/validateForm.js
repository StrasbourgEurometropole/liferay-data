(function ($) {
	$(document).ready(function() {
		$('.webform-client-form.toValidate, .generic-form.toValidate').each(function(){
			$(this).validate({
				lang: 'fr',
				errorElement: 'span',
				errorPlacement : function(error, element){
					error.appendTo( element.parents('.form-field') );
				},
				ignore: []
			});
		});
		

		$('input[type="date"], input[data-type="date"]').datepicker($.datepicker.regional[ "fr" ]);
	}); 
}(jQuery));
