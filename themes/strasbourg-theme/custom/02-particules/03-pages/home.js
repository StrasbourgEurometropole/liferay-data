(function ($) {
	if ($('.seu-front').length) {
		$(document).ready(function() {
			// Event click sur le bouton scroll To en hp
			$('#seu-banner .seu-scrollTo').on('click', function(){
				if(canScrollMagic){
					$('html, body').animate({
						scrollTop: $('#seu-une').offset().top - 150
					}, 1000);
				}else{
					$('html, body').animate({
						scrollTop: $('#seu-une').offset().top + 1
					}, 1000);
				}
			});
			
			
		});

		$(window).on( "load",function(){
		
		});
	};
}(jQuery));
