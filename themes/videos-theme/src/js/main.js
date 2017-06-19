AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		// Bouton recherche
		jQuery('#search-toggle').on('click', function (e) {
			e.preventDefault();
			jQuery('.search-box').toggleClass('active');
		});

		// Scroll to top
		jQuery('.to-top').on('click', function () {
			jQuery("html, body").animate({scrollTop: 0}, "slow");
		});

		// Reduce strings length
		jQuery('.trimTo70').each(function () {
			var text = $(this).text();
			if (text.length > 70) {
				var shortText = jQuery.trim(text).substring(0, 70).split(" ").slice(0, -1).join(" ") + "...";
				$(this).text(shortText);
			}
		});

		jQuery('.owl-item .video-title a').each(function () {var text = $(this).text();
			if (text.length > 100) {
				var shortText = jQuery.trim(text).substring(0, 70).split(" ").slice(0, -1).join(" ") + "...";
				$(this).text(shortText);
			}
		});

		// Close search when clicking outside
		jQuery(document).on('click', function (e) {
			var container = $('.search-box, #search-toggle');

			if (!container.is(e.target) && container.has(e.target).length === 0){
				jQuery('.search-box').removeClass('active');
			}
		});
		if (jQuery('.view-video-portlet h2.video-title').length > 0) {
			document.title = jQuery('h2.video-title').text() + ' | Videos.strasbourg.eu';
		}
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',
	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
		if (window.fromSearch) {
			$('html, body').animate({
		        scrollTop: $("#results-count").offset().top
		    }, 500);
		}
	}
);
