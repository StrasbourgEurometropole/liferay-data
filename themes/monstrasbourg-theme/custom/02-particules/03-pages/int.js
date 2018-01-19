
$('.no-js').removeClass('no-js');
function dot(){
	(function ($) {
		$('[data-dot]').each(function(){
			var lines = $(this).attr('data-dot');
			if(lines == ''){
				$(this).dotdotdot({wrap: 'word', watch: true});
			}else{
				var height = $(this).css('line-height');
				height = lines * parseInt(height);
				height += 2;
				$(this).dotdotdot({wrap: 'word', watch: true, height: height});
			}
		});
	}(jQuery));
}
(function ($) {
	function manageStickyHeader(){
		if($('.front').length){
			if($(window).scrollTop() > 0){
				$('header').addClass('scrolled');
			}else{
				$('header').removeClass('scrolled');
				$('.scrolled-search-engine').slideUp();
			}
			if($(window).scrollTop() > ($('#banner').height())){
				$('header').addClass('scrolled-hp');
			}else{
				$('header').removeClass('scrolled-hp');
				$('.scrolled-search-engine').slideUp();
			}
		}else{
			$('header').addClass('scrolled');
			$('header').addClass('scrolled-hp');
		}
	}
	$(document).ready(function() {
		// INIT
		$('.toCustomSelect, .SingleSelectHintWidget select').customSelect();
		$('select[multiple="multiple"]').each(function(index, element){
			var placeholder = $(element).find('option[disabled]').text();
			$(element).select2({
				placeholder: placeholder,
				closeOnSelect: false
			});
		})
		
		dot();
		// Menu Lang
		$('.lang-list').on('click', function(){
			$(this).toggleClass('open');
		});
		// RESIZE
			$(window).resize(function(){
				if (environmentChanged) {
					dot();
				}
			});
			
	}); // End onReady  

	$(window).load(function(){
		manageStickyHeader();
		$(window).scroll(function(){
			manageStickyHeader();
		});
	}) //End onLoad
}(jQuery));
