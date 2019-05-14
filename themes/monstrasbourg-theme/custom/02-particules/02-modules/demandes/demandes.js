jQuery(function() {
    jQuery('#formSuivi').submit( function() {
    	var codeSuivi = jQuery('#codeSuivi').val();
    	var urlSuivi = jQuery('#formSuivi [type=submit]').data('url-suivi').replace("[CODE_SUIVI]", codeSuivi);
      	window.location.replace(urlSuivi);
      	return false;
    });
});