var loop = true;
/*
 * Initialisation des carousels du site
 */

/* Carousel slider d'images */
var owl = jQuery('ul.slider_home');
loop = owl.children().length > 1;
owl.owlCarousel({
	items: 1,
	dots: true,
	loop: loop,
	autoplay: true,
	autoplayHoverPause:false,
	autoHeight: true,
	responsive: {
		640: {
			autoHeight: false
		}
	}
});




// On arrête le carousel au survol
jQuery(function() {
	jQuery('.diapo').on('mouseenter', function() {
		owl.trigger('stop.autoplay.owl');
	})
});

/* Owl agenda 30% */
var owlEvents30 = jQuery(".aui-w30 .agenda-carousel");
loop = owlEvents30.children().length > 1;
owlEvents30.owlCarousel({
	items: 1,
	dots: true,
	loop: loop,
	autoplay: true,
	autoplayTimeout: 5000,
	autoplayHoverPause: false,
	autoHeight: true,
	responsive: {
		640: {
			autoHeight: false
		}
	}
});

// On arrête le carousel au survol
jQuery(function() {
	jQuery('img', owlEvents30).on('mouseenter', function() {
		owlEvents30.trigger('stop.autoplay.owl');
	})
});

/* Owl agenda 70% */
var owlEvents70 = jQuery(".aui-w70 .agenda-carousel");
loop = owlEvents70.children().length > 1;

owlEvents70.owlCarousel({
	items: 1,
	dots: true,
	loop: loop,
	autoplay: true,
	autoplayTimeout: 5000,
	autoplayHoverPause: false,
	autoHeight: true,
	responsive: {
		640: {
			items: 3,
			margin: 10,
			autoHeight: false
		}
    }
});

// On arrête le carousel au survol
jQuery(function() {
	jQuery('img', owlEvents70).on('mouseenter', function() {
		owlEvents70.trigger('stop.autoplay.owl');
	})
});

/* Owl agenda 100% */
var owlEvents100 = jQuery(".portlet-column-only .agenda-carousel");
loop = owlEvents100.children().length > 1;

owlEvents100.owlCarousel({
	items: 1,
	dots: true,
	loop: loop,
	autoplay: true,
	autoplayTimeout: 5000,
	autoplayHoverPause: false,
	autoHeight: true,
	responsive: {
		640: {
			items: 5,
			margin: 10,
			autoHeight: false
		}
    }
});

// On arrête le carousel au survol
jQuery(function() {
	jQuery('img', owlEvents100).on('mouseenter', function() {
		owlEvents100.trigger('stop.autoplay.owl');
	})
});