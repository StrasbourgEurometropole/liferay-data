var removeCssLinks = function(mfpResponse) {
	// mfpResponse.data is a "data" object from ajax "success" callback
	var data = jQuery.parseHTML(mfpResponse.data);
	// On supprime les eventuels fichiers CSS récupérés
	for (var i = 0; i < data.length; i++) {
		if(data[i].tagName == "LINK") {
			data.splice(i, 1);
		}
	}
	mfpResponse.data = data;
}

var rebuildArrows = function() {
	// re-appends controls inside the main container
	if((this.arrowLeft !== null && this.arrowRight !== null) && (typeof(this.arrowLeft) !== "undefined" && typeof(this.arrowRight) !== "undefined"))
		this.contentContainer.append(this.arrowLeft.add(this.arrowRight));
}

// Affichage d'un glossaire dans une popup Magnific Lightbox
jQuery('.link-list .article-popup-link').magnificPopup({
	type: 'ajax',
	callbacks: {
		parseAjax: removeCssLinks,
		buildControls: rebuildArrows
	},
    gallery: {
      enabled: true,
      tPrev: 'Précédent',
      tNext: 'Suivant'
    }
});

jQuery('.article-popup-link.lightbox-item-illustration-link').magnificPopup({
	type: 'ajax',
	callbacks: {
		parseAjax: removeCssLinks,
		buildControls: rebuildArrows
	},
    gallery: {
      enabled: true,
      tPrev: 'Précédent',
      tNext: 'Suivant'
    }
});

jQuery('.article-popup-link.lightbox-item-title-link').magnificPopup({
	type: 'ajax',
	callbacks: {
		parseAjax: removeCssLinks,
		buildControls: rebuildArrows
	},
    gallery: {
      enabled: true,
      tPrev: 'Précédent',
      tNext: 'Suivant'
    }
});

jQuery('.article-popup-link.lightbox-item-catcher-link').magnificPopup({
	type: 'ajax',
	callbacks: {
		parseAjax: removeCssLinks,
		buildControls: rebuildArrows
	},
    gallery: {
      enabled: true,
      tPrev: 'Précédent',
      tNext: 'Suivant'
    }
});

jQuery('#how-to-order-popup').magnificPopup({
	type:'inline'
});

// Popup pour les images dans les CW
jQuery('.magnific-popup').magnificPopup({ 
	type: 'image',
	closeOnContentClick: true,
	closeBtnInside: true,
	fixedContentPos: true,
	mainClass: 'mfp-no-margins mfp-with-zoom mfp-image', // class to remove default margin from left and right side
	image: {
		verticalFit: true
	},
	zoom: {
		enabled: true,
		duration: 300 // don't foget to change the duration also in CSS
	}
});


// How to buy popup
jQuery('.btn-comment.boutique-popup-link').magnificPopup({
	type: 'ajax',
	mainClass: 'mfp-no-margins',
	callbacks: {
		  parseAjax: removeCssLinks
	}
});


// Store galleries
jQuery('.boutique-popup-link.btn-more').magnificPopup({
	type: 'ajax',
	mainClass: 'mfp-no-margins',
    gallery: {
      enabled: true,
      tPrev: 'Précédent',
      tNext: 'Suivant'
    },
	callbacks: {
		parseAjax: removeCssLinks,
		buildControls: rebuildArrows
	}
});
jQuery('.entry-header>.boutique-popup-link').magnificPopup({
	type: 'ajax',
	mainClass: 'mfp-no-margins',
    gallery: {
      enabled:true,
      tPrev: 'Précédent',
      tNext: 'Suivant'
    },
	callbacks: {
		parseAjax: removeCssLinks,
		buildControls: rebuildArrows
	}
});
jQuery('h3 .boutique-popup-link').magnificPopup({
	type: 'ajax',
	mainClass: 'mfp-no-margins',
    gallery: {
      enabled:true,
      tPrev: 'Précédent',
      tNext: 'Suivant'
    },
	callbacks: {
		parseAjax: removeCssLinks,
		buildControls: rebuildArrows
	}
});

// Image gallery
jQuery('.entry-meta .openImageLightboxGallery').magnificPopup({
	type:'inline',
	mainClass:'image-gallery-lightbox',
	midClick: true, // Allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source in href.
	gallery: {
		enabled:true,
		tPrev: 'Précédent',
		tNext: 'Suivant' 
	},
	callbacks: {
		buildControls: rebuildArrows
	}
});

jQuery('.entry-header h2 .openImageLightboxGallery').magnificPopup({
	type:'inline',
	mainClass:'image-gallery-lightbox',
	midClick: true, // Allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source in href.
	gallery: {
		enabled:true,
		tPrev: 'Précédent',
		tNext: 'Suivant'
	},
	callbacks: {
		buildControls: rebuildArrows
	}
});

jQuery('.entry-image .openImageLightboxGallery').magnificPopup({
	type:'inline',
	mainClass:'image-gallery-lightbox',
	midClick: true, // Allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source in href.
	gallery: {
		enabled:true,
		tPrev: 'Précédent',
		tNext: 'Suivant'
	},
	callbacks: {
		buildControls: rebuildArrows
	}
});