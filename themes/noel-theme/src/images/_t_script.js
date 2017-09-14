$(function()
{

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



	$(function() {
		FastClick.attach(document.body);
	});

	/* Display search bar */ 
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

	$('#layer').click(function(){
		$(this).removeClass('mns-layer');
		$('#search-bar').removeClass('search-display');
		$('.mns-navbar-wrapper').removeClass('mns-navbar-top-translate');
		$('nav').removeClass('mns-bigger');
	});

	$('.menu-search > a').click(function(e){
		e.preventDefault();
	});
	

	/* Detect iPad */ 
	if ((navigator.userAgent).match(/iPad/i) || ((navigator.userAgent).match(/Android/i) && height > width)){
		// alert('ipad');
		$('nav').addClass('mns-nav-scroll','mns-nav-ipad');
	}

	if ($(window).width() >= 1024){
		$('.navbar-nav > li.dropdown').mouseenter(function(){
			$(this).addClass('open');
		});

		$('.navbar-nav > li.dropdown').mouseleave(function(){
			$(this).removeClass('open');
		});
	}


	/* Search filtres on mobile */ 
	$('#search-mobile-filtres').click(function(e){
		e.preventDefault();
		console.log('click');
		var otop = $('main').offset().top;
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
	$('.mns-affiner').click(function(){
		$('.mns-z-filtres-search').slideToggle('500');
		$(this).parent().parent().toggleClass('hide-button');
	});

	/* Script Homepage - Filtres de recherche */
	$('select').selectric();


	/* Change comportement OwlCarousel */
	function opacifySlider(){
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
	/* End Change comportement OwlCarousel */



	/* Dectect if scroll */
	var positionElementInPage = $('.mns-nav').offset().top;

	var height = $(window).height();
	var width = $(window).width();

	// var Navipad = $('.mns-nav-ipad').offset().top;
	if ((navigator.userAgent).match(/iPad/i) || ((navigator.userAgent).match(/Android/i)) && height > width){
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
				// if ((navigator.userAgent).match(/iPad/i) && width > height){
				// 	// $('nav').removeClass('mns-nav-scroll');
				// 		// $('nav').addClass('mns-nav-ipad');
				// 		$('body').addClass('lol');
				// 	if ($(window).scrollTop() < 0) {
				// 		$('.mns-nav').removeClass("mns-nav-scroll");
				// 	}		
				// }

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

	$('.wrapper-dropdown').click(function(){
		$(this).toggleClass('active');
	});


	/* Carsousel section Agenda */
	$('#owl-agenda').owlCarousel({
		loop:false,
		margin:40,
		dots: false,
		nav:true,
		items: 3,
		autoWidth: true,
		navText: ["<span class='icon-chevron-thin-left'></span>","<span class='icon-chevron-thin-right'></span>"]
	})


	/* Carsousel section Testimonial */
	$('#owl-testi').owlCarousel({
		loop:true,
		margin:30,
		dots: false,
		nav:true,
		items: 1,
		navText: ["<span class='icon-chevron-thin-left'></span>","<span class='icon-chevron-thin-right'></span>"]
	})

	/* Carsousel section Testimonial */
	$('#owl-full').owlCarousel({
		loop:true,
		dots: false,
		nav:true,
		items: 1,
		navText: ["<span class='icon-chevron-thin-left'></span>","<span class='icon-chevron-thin-right'></span>"]
	})



	if ($(window).width() >= 1025){
		/* Hover Bloc Entry Homepage */ 
		$('.mns-bloc-entry > div').mouseenter(function(){
			$(this).find('.caption').addClass('open');
		});
		$('.mns-bloc-entry > div').mouseleave(function(){
			$(this).find('.caption').removeClass('open');
		});
	}
});
