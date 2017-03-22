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


// Add hash containing image title to URL
var addHashToURL = function() {
	window.location.hash = this.currItem.src;
}

// Remove hash from URL (leave an underscore not to scroll top)
var removeHashFromURL = function() {
	window.location.hash = "_";
}

var rebuildArrows = function() {
	// re-appends controls inside the main container
	if((this.arrowLeft !== null && this.arrowRight !== null) && (typeof(this.arrowLeft) !== "undefined" && typeof(this.arrowRight) !== "undefined"))
		this.contentContainer.append(this.arrowLeft.add(this.arrowRight));
}

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

// Image gallery
jQuery('.entry-meta .openImageLightboxGallery').magnificPopup({
	type:'inline',
	mainClass:'image-gallery-lightbox',
	midClick: true, // Allow opening popup on middle mouse click. Always set it to true if you don't provide alternative source in href.
	gallery: {
		enabled:true,
		tPrev: 'Précédent',
		tNext: 'Suivant' ,
	},
	callbacks: {
		buildControls: rebuildArrows,
		change: addHashToURL,
		close: removeHashFromURL
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
		buildControls: rebuildArrows,
		change: addHashToURL,
		close: removeHashFromURL
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
		buildControls: rebuildArrows,
		change: addHashToURL,
		close: removeHashFromURL
	}
});

// Open Magnitif Popup on page load if anchor is in URL and set counters
jQuery(function() {

	if(jQuery('.entry-meta .openImageLightboxGallery').length > 0) { // If we are on a page with a lightbox gallery

		// Set the counters
		var i = 0;
		var total = jQuery('.mfp-counter').length;
		jQuery('.mfp-counter').each(function() {
			i++;
			jQuery(this).html(i + " / " + total);
		});

		// Hash in URL
		var URLHash = window.location.hash;
		// Array containing all item titles
		var imageTitleArray = [];
		jQuery('.entry-meta .openImageLightboxGallery').each(function() {imageTitleArray.push($(this).attr("href"))});

		// Search the index of the item set in the URL hash
		var itemIndex = 0;
		var itemFound = false;
		for(var index = 0; index < imageTitleArray.length; index++) {
			if(URLHash == imageTitleArray[index]) {
				itemIndex = index;
				itemFound = true;
				break;
			}
		}

		// If the item is found, open the lightbox
		if(itemFound)
			jQuery('.entry-meta .openImageLightboxGallery').magnificPopup('open', itemIndex);
	}
});