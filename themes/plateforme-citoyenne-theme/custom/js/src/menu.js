$(function()
{
	ar_menu();
});

function ar_menu()
{
	// mobile sidebar menu
	$('.th-menu').click(function () {
		if (!$(this).hasClass('open')) {
			$(this).addClass('open');
			$('nav').addClass('show');
			$('#pro-shadow-bg').addClass('pro-display-block');
			$('#header').addClass('menu-open');
			$('.social-share').css('opacity','0');
		}
		else {
			$('#pro-shadow-bg').removeClass('pro-display-block');
			$(this).removeClass('open');
			$('#header').removeClass('menu-open');
			$('.social-share').css('opacity','1');
			setTimeout(function () {
				$('nav').removeClass('show');
			}, 150);
		}
	});

	$('#pro-shadow-bg').click(function () {
		$(this).removeClass('pro-display-block');
		$('nav').removeClass('show');
		$('.th-menu').removeClass('open');
		$('#header').removeClass('menu-open');
		$('#header').removeClass('pro-wrapper-search-open');
	});
}


$('.currentLang > a').on('focus',function(){
	$('.is-focus-lang').removeClass('is-focus-lang');
	$(this).parents().next().addClass('is-focus-lang');
});


/* Detect the scroll of the page and animate the menu */
$(window).on('scroll', function (e) {
	var st = $(this).scrollTop();

	if (st > 100) {
		$("body").addClass("is-scrolled");
	}
	else {
		$("body").removeClass("is-scrolled");
	}
});


var lastscrolltop = 0;
var lastIsDirTop = 0;
document.addEventListener('scroll',function(){
	var st = $(document).scrollTop();
	if(st<lastscrolltop && lastIsDirTop == 0){
		lastIsDirTop = 1;
		$(document.body).addClass('scrolldir-top',true);
	}
	if(st>lastscrolltop && lastIsDirTop == 1){
		lastIsDirTop = 0;
		$(document.body).removeClass('scrolldir-top',true);
	}
	lastscrolltop = st;
});


// Move Affiner la recherche on Tablet Portrait

if ($(window).width() < 992) {
	$(".pro-inside-affine-search").prepend($('.pro-bloc-facette-participation'));
	$('.pro-wrapper-nav').append($('.pro-top-header'));
}

var menuEmplacement = 0;
var timeoutResizeMenuEmplacement = null;

$(window).resize(function() {
	clearTimeout(timeoutResizeMenuEmplacement);
	timeoutResizeMenuEmplacement = setTimeout(moveMenuOnResize,500);
});

function moveMenuOnResize(){
	var wW = $(window).width();
	if (wW < 992 && menuEmplacement == 0) {
		menuEmplacement = 1;
		$(".pro-inside-affine-search").prepend($('.pro-bloc-facette-participation'));
		$('.pro-wrapper-nav').append($('.pro-top-header'));
	}
	if (wW >= 992 && menuEmplacement == 1) {
		menuEmplacement = 0;
		$('.pro-wrapper-aside').append($('.pro-bloc-facette-participation'));
		$('.pro-wrapper-top-header').prepend($('.pro-top-header'));
	}
}