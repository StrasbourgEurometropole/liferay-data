
$('.seu-no-js').removeClass('seu-no-js');
function dot(){
	(function ($) {
		$('#seu-socials .seu-item.seu-has-picture .seu-content').attr('data-dot', 3);
		$('[data-dot]').each(function(){
			var lines = $(this).attr('data-dot');
			if(lines == ''){
				$(this).dotdotdot({wrap: 'word', watch: true});
			}else{
				var height = $(this).css('line-height');
				height = lines * parseInt(height);
				height += 1;
				$(this).dotdotdot({wrap: 'word', watch: true, height: height});
			}
		});
	}(jQuery));
}
(function ($) {
	function manageStickyHeader(){
		if($('.seu-front').length){
			if($(window).scrollTop() > 0){
				$('.seu-header').addClass('scrolled');
			}else{
				$('.seu-header').removeClass('scrolled');
				$('.seu-scrolled-search-engine').slideUp();
			}
			if($(window).scrollTop() > ($('#seu-banner').height() - $('.seu-nav-top').height() - ($('.control-menu-level-1').height()!= undefined? $('.control-menu-level-1').height() : 0))){
				$('.seu-header').addClass('scrolled-hp');
			}else{
				$('.seu-header').removeClass('scrolled-hp');
				$('.seu-scrolled-search-engine').slideUp();
			}
		}else{
			$('.seu-header').addClass('scrolled');
			$('.seu-header').addClass('scrolled-hp');
		}
	}
	$(document).ready(function() {
		// INIT
		$('.seu-toCustomSelect, .SingleSelectHintWidget select').customSelect();
		$('select[multiple="multiple"]').each(function(index, element){
			var placeholder = $(element).find('option[disabled]').text();
			$(element).select2({
				placeholder: placeholder,
				closeOnSelect: false
			});
		})
		
		dot();
		// Moteur de recherche dépliant
		$('#seu-search-trigger').on('click', function(){
			$('.seu-scrolled-search-engine').stop();
			$('.seu-scrolled-search-engine').slideToggle();
		});
		$('#seu-search-trigger2').on('click', function(){
			$('.seu-scrolled-search-engine').stop();
			$('.seu-scrolled-search-engine').slideToggle();
		});
		$('.seu-search-close').on('click', function(){
			$('.seu-scrolled-search-engine').stop();
			$('.seu-scrolled-search-engine').slideUp();
		});
		$('#seu-menu-trigger').on('click', function(){
			$('.seu-header').toggleClass('seu-mmenu');
		})
		// Menu Lang
		$('.seu-lang-list').on('click', function(){
			$(this).toggleClass('seu-open');
		});
		// Big Menu
		function attachBigMenu(){
			if(environment != 'desktop'){
				$('.seu-menu-item-container').off('mouseenter mouseeleave');
			}else{	
				$('.seu-menu-item-container').on('mouseenter', function(){
					$('.seu-menu-subitem').stop().slideUp(300);
					$(this).find('.seu-menu-subitem').stop().slideDown(300);
				}).on('mouseleave', function(){
					$('.seu-menu-subitem').stop().slideUp(300);
					$(this).find('.seu-menu-subitem').stop().slideUp(300);
				});
			}
		}
		attachBigMenu();

		// SplashScreen
		$('#seu-splashscreen .seu-close').on('click', function(){
			$('#seu-splashscreen').fadeOut();
		});
		// RESIZE
			$(window).resize(function(){
				if (environmentChanged) {
					dot();
					//manageMenuRwd();
					attachBigMenu();
				}
			});
			
	}); // End onReady  

	$(window).on( "load",function(){
		manageStickyHeader();
		//manageMenuRwd();
		$(window).scroll(function(){
			manageStickyHeader();
		});
	}) //End onLoad
}(jQuery));
