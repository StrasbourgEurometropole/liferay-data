/*
//Parallax

$paralax = $('#organization .society');

function parallaxScroll($element) {
  var scrolled = $(window).scrollTop();

  if (environment == 'desktop') {
      $element.css('background-position', 'center ' + (0 - (scrolled * .20)) + 'px');
  } else {
      $element.css('background-position', 'center center');
  }
}*/

//Ratio de la video pour envlever les bandes noires
function ratioYoutube(containerSelector, videoSelector){
	// Ratio header
	var container_width = $(containerSelector).width();
	var container_height= $(containerSelector).height();
	// Ratio parameter-video
	var video_width = $(videoSelector).data('video-width');
	var video_height = $(videoSelector).data('video-height');

	var video_ratio = (video_height*100)/video_width;
	var target_height = (container_width*video_ratio)/100;
	var target_width = '100%';

	if (target_height < container_height) {
		target_height = container_height;
		target_width = ( target_height * 100 ) / video_ratio;
	};

	var target_top_position = -((target_height-container_height)/2);
	var target_left_position = -((target_width-container_width)/2);

	$(videoSelector).css({
		top: target_top_position,
		left: target_left_position,
		position: 'absolute',
		height: target_height,
		width: target_width
	});
}

//Video youtube
if ($('#player').length){
	var deviceDesktop = device.desktop();

	if ( (environment == 'desktop') && (deviceDesktop == true) ) {
		var tag = document.createElement('script');
		tag.src = "//www.youtube.com/player_api";
		var firstScriptTag = document.getElementsByTagName('script')[0];
		firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
		var widthWindow = $(window).innerWidth(),
			heightHeader = $('header').height();
		var video_src = $('.parameter-video').data('video-src');

		function onYouTubeIframeAPIReady() {
			player = new YT.Player('player', {
			      //height: '100%',
			      //width: '100%',
			      videoId: video_src,
			      //playerVars: { 'iv_load_policy': 3, 'modestbranding' : 1, 'rel' : 0, 'showinfo' : 0, 'controls': 0, 'loop' : 0, 'autoplay': 0 },
			      events: {
			      'onReady': onPlayerReady,
			      'onStateChange' : onPlayerStateChange
			    }
			});
		}

		//Start the video
		function onPlayerReady(event) {
			ratioYoutube('header', '.parameter-video');
			player.mute();

			// Enlever la tabulation
			//$('#player').attr('tabindex', '-1');
		}
		onPlayerReady();

		function onPlayerStateChange(event) {
			player.playVideo();
			player.mute();
		}

	}
}

// Dotdotdot responsive
function dotdotdot(){
	if(environment == 'desktop'){
		$('#organization .infos p').dotdotdot({wrap: 'letter', height: 63, watch:true}); // HP infos
	}else if(environment == 'tablet'){

	}else{
		$('#organization .infos p').trigger("destroy"); // HP infos
	}
}

//Sous menu 
function sousMenu(){
	if(environment == 'desktop'){
		function showHideSousMenu(rubrique, action){
	      	if(action=="show"){
	      	  	// On cache tout
		      	$('.menu-category').removeClass('opened');
		      	$('.menu-category').hide();

		      	//On ouvre le bon sous menu
		        $('.menu-category.'+ rubrique).addClass('opened').slideToggle();
	      }else if(action=='hide'){
	          $('.menu-category.'+ rubrique).removeClass('opened').slideToggle();
	      }
	    }

		$('#main-menu .main-nav li .link-leading').mouseenter(function(){
			var target = $(this).attr('data-sousMenu-rubrique');
			if(environment == 'desktop'){
				showHideSousMenu(target, 'show');
			}
		});

		$('.menu-category').mouseleave(function(){
			var target = $(this).prev().attr('data-sousMenu-rubrique');
			showHideSousMenu(target, 'hide');
		});

		//si on clique en dehors du menu
	    /*$( document ).on( "click", function( e ){
		    if ( e.target.id != "main-menu" ) {
		       	var target = 'menu-category';
		      	showHideSousMenu(target, 'hide-all');
		    }
		});*/

	}
}

//Search engine
function toggleSearch(){
	$('#search-engine').addClass('opened');
	$('.search-input').focus();
}

//Pour débogguer le focus
// setInterval(function() { 
// 	console.log(document.activeElement);
// }, 2000); 

$(document).ready(function() {

	//Appel function
	testEnvironment();
	dotdotdot();
	sousMenu();

	// Dotdotdot
	$('.agenda-carousel .item .infos-item .event-date').dotdotdot({wrap: 'letter', height: 25, watch:true}); // HP slider news date
	$('.agenda-carousel h4').dotdotdot({wrap: 'letter', height: 50, watch:true}); // HP slider news title
	$('.agenda-carousel .event-meta p').dotdotdot({wrap: 'letter', height: 50, watch:true}); // HP slider news texte
	$('#twitter .pseudo p span').dotdotdot({wrap: 'letter', height: 20, watch:true}); // pseudo twitter 
	$('#twitter .pseudo p').dotdotdot({wrap: 'letter', height: 45, watch:true}); // @compte twitter 
	$('#activities .block-content p').dotdotdot({wrap: 'letter', height: 72, watch:true}); // HP activities
	
	$('.list-search li a h3').dotdotdot({wrap: 'letter', height: 23, watch:true}); // Page search title
	$('.list-search li a p:not(p.infos)').dotdotdot({wrap: 'letter', height: 50, watch:true}); // Page search paragraphe

	$('.list-evt .category').dotdotdot({wrap: 'letter', height: 50, watch:true});// Page agenda Category
	$('.list-evt h2').dotdotdot({wrap: 'letter', height: 50, watch:true});// Page agenda title
	$('.list-evt .date').dotdotdot({wrap: 'letter', height: 25, watch:true});// Page agenda date
	$('.list-evt .place').dotdotdot({wrap: 'letter', height: 21, watch:true});// Page agenda place

	//Slider HP Actus
	$('.agenda-carousel').owlCarousel({
	    loop:true,
	    nav:true,
	    responsive:{
	        0:{
	            items:1
	        },
	        768:{
	            items:2
	        },
	        1200:{
	            items:3
	        }
	    }
	});

	//Slider partenaires HP
	$('.owl-partners').owlCarousel({
	    loop:true,
	    nav:true,
	    margin:15,
	    responsive:{
	        0:{
	            items:1
	        },
	        768:{
	            items:4
	        },
	        1200:{
	            items:6
	        }
	    }
	});

	//Slider gallery
	$('.owl-gallery').owlCarousel({
	    loop:true,
	    nav:true,
	    autoHeight:true,
	    items:1
	});

	//Tabulations carousel
  $('.owl-carousel .owl-item.cloned a').attr('tabindex', '-1');
  $('.owl-carousel.owl-gallery .item-gallery').attr('tabindex', '0');

    // button search responsive
	var btnSearch = $('#main-menu .item-search').html();
	$('.nav-rwd').prepend(btnSearch);

	//Button search
	$('button.search').on('click', function(){
		toggleSearch();
		$('#search-engine').slideToggle();
	});
	$('#search-close').on('click', function(){
		toggleSearch();
		$('#search-engine').slideToggle();
	});

	//Close popup gmap
	$('.gmap-close').on('click', function(){
		$('.gmap-popup').addClass('hidden');
	});

	//menu responsive
	if($('#mmenu').length){

		//Mettre du contenu dans le mmenu
		var mainNav = $('.main-nav').html();

		$('#mmenu .list-principal').append(mainNav);

		//On créé le mmenu
		$("#mmenu").mmenu({
			
		}, {
	         // configuration
	        offCanvas: {
	            pageNodetype: "main"
	        }
	    });

       	$('#mmenu .mm-panel').each(function(){
        	var delay = 1;
			$(this).find('li').each(function(){
	        	$(this).css('animation-delay', delay * 0.2 +'s');
	        	delay++;
	        });
        });
    }

    //Custom select 
    if ($('select').length){
    	$('select.aui-field-input-select').customSelect({
	    	customArrow: true
	    }); 
    }
    
}); // End onReady

$(window).load(function(){
	$('html.no-js').removeClass('no-js');
}); //End onLoad

$(window).scroll(function(e) {

}); // End scroll

$(window).resize(function(){
  var old=environment;
  testEnvironment();
  ratioYoutube('header', '.parameter-video');
  if(environment != old){
    dotdotdot();
  }
}); // End resize
