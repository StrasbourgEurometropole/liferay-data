jQuery(document).ready(function() {
	
	//Custom select 
	jQuery(".block-filter select").uniform();
	
	//Datepicker
	jQuery(".textdate").datepicker({
		dateFormat : 'dd/mm/yy'
	});

	jQuery('.btn-top').bind('click', function() {
		jQuery('html, body').stop().animate({ scrollTop: 0}, 400);
		return false;
	});
	
	
	jQuery( ".article-detail img" ).hover(function() {
		jQuery('.copyright-caption').css({ display : 'block'});
	});
	
	jQuery( ".article-detail img" ).mouseout(function() {
		jQuery('.copyright-caption').css({ display : 'none'});
	});

	jQuery('.btn-menu').bind('click', function() {
	
		if( jQuery(this).hasClass('active') ){
			//jQuery('.nav-primary ul').fadeOut();
			jQuery('.nav-primary ul').stop().animate({ height: 0 }, 400);
		}
		else {
			//jQuery('.nav-primary ul').fadeIn();
			jQuery('.nav-primary ul')
				.stop()
				.animate({
					height: 
						jQuery('.nav-primary ul.menu > li').size() 
						* jQuery('.nav-primary ul.menu > li')
						.outerHeight(true) 
				}, 400, function() { 
					jQuery(this).css({
						height : 'auto'
					}
				)
			});
		}
        
		jQuery(this).toggleClass('active');
		return false;
	});
	
	jQuery('.nav-primary ul.menu > li.expanded > .submenu > ul.child-menu > li').hover(function(){
			jQuery(this).parents( "li" ).css( "background-color", "#268033" );	
			jQuery(this).parents( "li").find("a").first().css( "color", "#FFF" );
	});
	
	jQuery('.nav-primary ul.menu > li.expanded > .submenu > ul.child-menu > li').mouseleave(function(){
			jQuery('.nav-primary ul.menu > li.expanded').css( "background-color", "#fff" );	
			jQuery('.nav-primary ul.menu > li.expanded > a').css( "color", "#268033" );	
	});     
	

	if( jQuery(window).width() < 640 ) {
		jQuery('.menu .expanded > a').bind('click', function(e) {
			e.preventDefault();
			
			if( jQuery(this).parent().find('.submenu').height() != 0 ) {
				jQuery(this).parent().find('.submenu').stop().animate({ height : 0 });
			}
			else {
				var height = jQuery(this).parent().find('.submenu ul > li').size() * jQuery(this).parent().find('.submenu ul > li').outerHeight(true);
				jQuery(this).parent().find('.submenu').stop().animate({ height : height });
			}
			return false;
		});
	}
	jQuery('.menu .expanded > a').bind('click', function(e) {
		//e.preventDefault();
	});
});

function add3Dots(string, limit)
{
  var dots = "...";
  if(string.length > limit)
  {
    // you can also use substr instead of substring
    string = string.substring(0,limit) + dots;
  }
 
    return string;
}

/**
 * Slider home
 */
var summerCarousel = jQuery('.summer-slider .owl-carousel').owlCarousel({
  items: 1,
  dots: true,
  loop: true
});

jQuery('.summer-slider .prev').on('click', function() {
  summerCarousel.trigger('prev.owl.carousel');
})

jQuery('.summer-slider .next').on('click', function() {
  summerCarousel.trigger('next.owl.carousel');
})


// Magnific popup (lightbox images)
if (jQuery().magnificPopup) {
    jQuery('.lightbox').on('click', function(e) {
        jQuery.magnificPopup.open({
            type: 'image',
            items: {
                src: jQuery(this).attr('src'),
                title: jQuery(this).data('title')
            },
            zoom: {
                enabled: true
            },
            callbacks: {
                elementParse: function(qw) {
                    qw.el = jQuery(e.target);
                }
            }
        });
    });
}