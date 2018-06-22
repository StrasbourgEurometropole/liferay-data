	$(window).scroll(function() {
		if ($(window).scrollTop() > 100) {
			$('.mns-share-button').addClass('fadein');
		}
		else {
			$('.mns-share-button').removeClass('fadein');
		}
	});

	// Bloc video
	$('.mns-bloc-video').each(function() {

		var mask = $('.mns-mask-video',this);
		var vidContainer = $('.mns-embed-container', this);

		var urlVideo = vidContainer.data("urlvideo");
		if(urlVideo.match(/\?/)){
			urlVideo+='&autoplay=1';
		}else{
			urlVideo+='?autoplay=1';
		}
		var iframe  ='<iframe src="' + urlVideo + '" width="1280px"  height="auto"></iframe>';


		$(mask).click(function (e) {
			e.preventDefault();
			vidContainer.append(iframe);

			setTimeout(function(){
				mask.addClass('hide');

				setTimeout(function(){
					mask.remove();
				}, 500);
			}, 200);
		});
	});

	// Flexibility
	flexibility(document.documentElement);

	// Script Homepage - Filtres de recherche
	$('select').selectric();

	// FastClick 
	$(function() {
		FastClick.attach(document.body);
	});
	
	// Controle Date Picker (Pikaday)
	var picker_start = new Pikaday({ field: document.getElementById('datepicker-start') });
	var picker_end = new Pikaday({ field: document.getElementById('datepicker-end') });

	// Page Marché - Ouverture / Fermeture du volet 
	$('.mns-volet-map').click(function(){
		$('#mns-wrapper-volet').toggleClass('expand');
	});

	// Détecte si l'utilisateur est sur une tablette ou un smartphone
	function is_touch_device() {
		try {
			document.createEvent("TouchEvent");
			return true;
		} catch (e) {
			return false;
		}
	}

	if(is_touch_device() && document.body.clientWidth <= 1024){
		$('html').addClass('touch-device');
	}else{
		$('html').addClass('not-touch-device');
	}

	$(window).on('resize',function(){
		if(is_touch_device() && document.body.clientWidth <= 1024){
			$('html').addClass('touch-device');
			$('html').removeClass('not-touch-device');
		}else{
			$('html').removeClass('touch-device');
			$('html').addClass('not-touch-device');
		}
	});

	var nua = navigator.userAgent;
	var is_android_native_browser = ((nua.indexOf('Mozilla/5.0') > -1 && nua.indexOf('Android ') > -1 && nua.indexOf('AppleWebKit') > -1));
	if(nua.indexOf('Chrome') > -1){
		is_android_native_browser = false;
	}
	if(is_android_native_browser){
		$('html').addClass('android_browser');
	}else{
		$('html').addClass('not_android_b');
	}

	// Affichage de la Barre de recherche 
	$('.menu-search').click(function(){
		$('.mns-search-bar').addClass('search-display');
		$('.mns-navbar-wrapper').addClass('mns-navbar-top-translate');
		$('#search').focus();
		$('#layer').addClass('mns-layer');
		if ($('#search-bar').hasClass('search-display')){
			$('nav').addClass('mns-bigger');
		}
		else{
			$('nav').removeClass('mns-bigger');
		}
	});	

	// Affichage du Masque quand la barre de recherche est ouverte
	$('#layer').click(function(){
		$(this).removeClass('mns-layer');
		$('#search-bar').removeClass('search-display');
		$('.mns-navbar-wrapper').removeClass('mns-navbar-top-translate');
		$('nav').removeClass('mns-bigger');
	});

	$('.menu-search > a').click(function(e){
		e.preventDefault();
	});
	
	// Changement de comportement de la NavBar si nous sommes sur un iPad ou une tablette Android en mode portrait
	if (((navigator.userAgent).match(/Tablet/i) && height > width)) {
		$('nav').addClass('mns-nav-scroll','mns-nav-ipad');
	}
	else if ((navigator.userAgent).match(/Android/i)){
		$('nav').removeClass('mns-nav-scroll','mns-nav-ipad');
	}
	if ($(window).width() > 1200){
		$('.navbar-nav > li.dropdown').mouseenter(function(){
			$(this).addClass('open');
		});

		$('.navbar-nav > li.dropdown').mouseleave(function(){
			$(this).removeClass('open');
		});
	}


	if ((navigator.userAgent).match(/iPad/i) || ((navigator.userAgent).match(/Android/i))){
		$('.mns-header > video').css('display', 'none');
	}

	/* Search filtres on mobile */ 
	$('#search-mobile-filtres').click(function(e){
		e.preventDefault();
		var otop = $('.mns-m-filtres-search').offset().top;
		$('body,html').animate({scrollTop:otop-80},500,function(){	
			
		});
		$('.mns-filtres').slideDown('500');
		$('#search-mobile-filtres').parent().parent().addClass('hide-button');
	});

	$('#cross-mobile').click(function(){
		$('.mns-filtres').slideUp('500');
		$('.mns-m-filtres-search').removeClass('hide-button');
	});

	/* For the Agenda's page */ 
	$('.mns-affiner-m').click(function(){
		$('.mns-z-filtres-search').slideToggle('500');
		$('.mns-z-filtres-search').addClass('row');
		//$(this).parent().parent().toggleClass('hide-button');
	});

	// Dectect if scroll 
	var positionElementInPage = $('.mns-nav').offset().top;

	var height = $(window).height();
	var width = $(window).width();
	// If qui sert pour quand on a un Header simple sur lequel on veut tt le temps le menu en sticky
	if(!$('.mns-nav').hasClass("mns-nav-no-header")) {
		if (((navigator.userAgent).match(/Tablet/i)) && height > width){
			$('body').addClass('ipad');
			$('.mns-nav').addClass("mns-nav-scroll");
			$('.mns-nav').addClass("mns-nav-ipad");
			$(window).scroll(function(){

				if ($(window).scrollTop() == 0) {
					$('.mns-nav-ipad').removeClass('mns-top');
				}
				else{
					$('.mns-nav-ipad').addClass('mns-top');
				}
			});

		}else {
			$(window).scroll(
				function() {
					if ($(window).scrollTop() > 0) {
						$('.mns-nav').addClass("mns-nav-scroll");
						$('#layer').addClass('mns-nav-scroll-layer')
					} else {
						$('.mns-nav').removeClass("mns-nav-scroll");
						$('#layer').removeClass('mns-nav-scroll-layer')
					}
				}
				);
		}
	}

	// Affiche Dropdown menu
	$('.wrapper-dropdown').click(function(){
		$(this).toggleClass('active');
	});

	// Change comportement OwlCarousel
/*	function opacifySlider(){
		$('.owl-opacify').on('translated.owl.carousel',function(){
			var $el = $(this);
			opacifyOffSlide($el);
			$el.removeClass('translate');

		}).on('translate.owl.carousel drag.owl.carousel',function(){
			$(this).addClass('translate');
		}).on('initialized.owl.carousel',function(){
			var $el = $(this);
			opacifyOffSlide($el);
		});
	}
	opacifySlider();

	function opacifyOffSlide($el) {
		var elOffset = $el.offset();
		var left = elOffset.left;
		var width = $el.width();
		var right = left + width;

		var slides = [];

		$('.owl-item',$el).each(function(){
			$slide = $(this);
			var o = $slide.offset();
			var w = $slide.width();

			if(o.left < left){
				slides.push(this);
			}
			if(o.left + w > right){
				slides.push(this);
			}
		}).removeClass('opacify');

		$(slides).addClass('opacify');
	}
	// End Change comportement OwlCarousel
*/
	// Carsousel section Agenda 
	$('#owl-agenda').owlCarousel({
		loop:false,
		dots: false,
		nav:true,
		margin: 40,
		autoWidth: true,
		navText: ["<span class='mns-picto'></span>","<span class='mns-picto'></span>"],
	    responsive:{
	        0:{
	            items:1
	        },
	        700:{
	            items:2
	        },
	        1100:{
	            items:3
	        },
	        1375:{
	            items:4
	        }
	    }
	})


	// Carsousel section Testimonial
	$('#owl-full').owlCarousel({
		loop:true,
		dots: false,
		nav:true,
		items: 1,
		autoplay: true,
    	autoplayTimeout: 4000,
    	autoplayHoverPause: true,
		navText: ["<span class='mns-picto'></span>","<span class='mns-picto'></span>"]
	})

	// Carsousel section Testimonial
	$('#owl-slider').owlCarousel({
		loop:true,
		dots: false,
		nav:true,
		items: 1,
		autoplay: true,
    	autoplayTimeout: 4000,
    	autoplayHoverPause: true,
		navText: ["<span class='mns-picto'></span>","<span class='mns-picto'></span>"]
	})

	$('#owl-full .owl-item').each(function() {
		$(this).css('width', $(this).width() + 1);
	});

	// Page d'accueil - Comportement en hover sur les grandes bulles
	if ($(window).width() >= 1025){
		$('.mns-bloc-entry > div').mouseenter(function(){
			$(this).find('.caption').addClass('open');
		});
		$('.mns-bloc-entry > div').mouseleave(function(){
			$(this).find('.caption').removeClass('open');
		});
	};

	$('.mns-expand-collapse').click(function(){
    	var checkboxesId = $(this).data("checkboxes-id");
		var checkboxes = $('#checkbox-'+checkboxesId);
		if($(this).hasClass('expanded')){
           $(this).find('.mns-filter-expand').show();
           $(this).find('.mns-filter-collapse').hide();
           $(this).removeClass('expanded');
           checkboxes.height("0px");
        } else {
           $(this).find('.mns-filter-expand').hide();
           $(this).find('.mns-filter-collapse').show();
           $(this).addClass('expanded');
           checkboxes.height("100%");
        }
    });

// Resize le titre de l'agenda de la home en allemand sur mobile
if($('html').attr('lang')=="de-DE") {
	if ($(window).width() <= 320){
		$('.mns-section-agenda').find('h1').css('font-size', '28px');
	}
	else if ($(window).width() <= 375){
		$('.mns-section-agenda').find('h1').css('font-size', '32px');
	}
	else if ($(window).width() <= 420){
		$('.mns-section-agenda').find('h1').css('font-size', '36px');
	}
}


if($('iframe[src^="https://www.youtube.com"]')) {
	$iframe_yt = $('iframe[src^="https://www.youtube.com"]');
	$iframe_yt.css('position','absolute');
	$iframe_yt.css('top','0');
	$iframe_yt.css('left','0');
	$iframe_yt.css('width','100%');
	$iframe_yt.css('height','100%');

	$parent = $iframe_yt.parent();
	$parent.css('position','relative');
	$parent.css('padding-bottom','56.25%');
	$parent.css('padding-top','30px');
	$parent.css('overflow','hidden');
	$parent.css('height','0');

}