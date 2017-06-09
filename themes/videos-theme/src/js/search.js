(function($) {

	$('.search-form').submit(function (e) {
		e.preventDefault();
		if ($('#search-input', this).val().length > 0) {
			var searchUrl = searchPageUrl + $('#search-input', this).val();
			window.location.replace(searchUrl);
		}
	});

})(jQuery);