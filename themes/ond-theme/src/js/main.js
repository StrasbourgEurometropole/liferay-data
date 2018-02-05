jQuery(document).ready(function() {
    
	//Custom select
    if (jQuery().uniform) {
	   jQuery(".ond .block-filter select").uniform({selectAutoWidth:false});
	}
    if (jQuery().datepicker) {
    	//Datepicker
    	jQuery(".ond .textdate").datepicker({
    		dateFormat : 'dd/mm/yy'
    	});
    }
    
    //Slider home
    if (jQuery().bxSlider) {
    	var slider_img = jQuery('.ond .slider_home').bxSlider({
            mode: 'fade',
            pager:true,
            autoControls:true,
            auto:true
          });

        //HEADER HOME  
    	jQuery('.ond .slide_header').bxSlider({
            mode: 'fade',
            captions: false,
            infiniteLoop: true,
            pager:false,
            autoControls:false,
            auto:true,
            controls:false,
            autoControls:true
        });
        //HEADER ENFANTS
        jQuery('.ond .slide_header_child').bxSlider({
            pager:false,
            autoControls:false,
            auto:false,
            controls:false
        });	

    }
	jQuery('.ond .btn-top').bind('click', function() {
		jQuery('html, body').stop().animate({ scrollTop: 0}, 400);
		return false;
	});

	jQuery('.ond .btn-menu').bind('click', function() {
		if( jQuery(this).hasClass('active') ){
			jQuery('.nav-primary ul').stop().animate({ height: 0 }, 400);
		}
		else {
			jQuery('.nav-primary ul').stop().animate({ height: jQuery('.nav-primary ul.menu > li').size() * jQuery('.nav-primary ul.menu > li').outerHeight(true) }, 400, function() { jQuery(this).css({ height : 'auto'})});
		}

		jQuery(this).toggleClass('active');
		return false;
	});

	
    // SOUS MENU PRINCIPAL
    jQuery('.ond .menu .expanded>a').bind('click', function(e) {

		if(jQuery('.menu .expanded').hasClass('active')) 
		{
			jQuery('.menu .expanded .submenu').animate({ height : 0 });
			jQuery('.menu .expanded').removeClass('active');
			jQuery('.ariane').animate({"margin-top":0});
		}
		
        if( jQuery(window).width() < 640 )
        {
            totalHeight=0;	
            if( jQuery(this).parent().find('.submenu').height() > 0 ) 
            {
                jQuery(this).parent().find('.submenu').stop().animate({ height : 0 });
            }
            else
            {
                jQuery(this).parent().find('.submenu ul li').each(function(){
                    totalHeight=totalHeight+jQuery(this).outerHeight();
                });
                jQuery(this).parent().find('.submenu').stop().animate({ height : totalHeight});
            }
        }
        else

        {      
            if(jQuery(this).parent().find('ul li').size()%6 ==0)
				totalHeight = Math.round(jQuery(this).parent().find('ul li').size()/6);
			else
				totalHeight = Math.floor((jQuery(this).parent().find('ul li').size()/6))+1;
			
			
            if(totalHeight==0)
                totalHeight=1;
            heightChildren = jQuery(this).parent().find('ul li').outerHeight()+5;
	     
            if(jQuery(this).parent().find('.submenu').height()>0) 
            {
                jQuery(this).parent('li').removeClass('active');
                jQuery(this).parent().stop().find('.submenu').animate({height:0});
                jQuery('.ariane').animate({"margin-top":0});
            }

            else 
			{ 
                jQuery(this).parent('li').addClass('active');
                jQuery(this).parent().stop().find('.submenu').animate({height:totalHeight*heightChildren});
                jQuery('.ariane').animate({"margin-top":totalHeight*heightChildren});
                
            }              
        }
        
            return false;
    });
    
    jQuery('.ond .menu-boutique li a').bind('click', function(e) {
        jQuery('.menu-boutique li a.active').removeClass('active');
        var selected = jQuery(this).attr('class');
        
        jQuery('.categorie:not(.'+selected+')').slideUp();
        jQuery('.categorie.'+selected).slideDown();
        
        jQuery(this).addClass('active');
        return false;
    });
    
    // Affiche le contenu de la première catégorie (qui a la classe active au chargement de la page)
    if(jQuery('.ond .menu-boutique').length > 0)
    {
        var active = $('ul.menu-boutique li a.active');
        var categoryToDisplay = active.parent().attr('class');
        $('.categorie.' + categoryToDisplay).css('display', 'block');
        /*
    	jQuery('.produit a').colorbox({
	    	ajax:true,
		height:'auto',
		title:false,
		transition:'fade',
        className: 'produits-box',
	    onComplete:function(){resizeColorBox();}
	    });
        
        jQuery('.btn-comment').colorbox({
	    	ajax:true,
		height:'auto',
		title:false,
		transition:'fade',
        className: 'comment-box',
	    onComplete:function(){resizeColorBox();}
	    });
		
		// Resize Colorbox when resizing window or changing mobile device orientation
		jQuery(window).resize(resizeColorBox);
        */
    }
    
       
        
        
     init();
     jQuery(window).resize(function()
    {
        jQuery('.nav-primary ul li').removeClass('active');
        init();
    });
});

/* Colorbox resize function */
var resizeTimer;
function resizeColorBox()
{
    if (resizeTimer) clearTimeout(resizeTimer);
    resizeTimer = setTimeout(function() {
            if (jQuery('#cboxOverlay').is(':visible') && jQuery(window).width()<=640) {
                    jQuery.colorbox.resize({width:'90%'}); 
            }
            else {
                    jQuery.colorbox.resize({width:'620px'}); 
            }

    }, 300)
}

function init()
{
    if( jQuery(window).width() > 640 )
    {
        if(jQuery('body').hasClass('home'))
        {
            heightHeader =156+((195/665)*(jQuery(window).width()-955));
            minMargin =22;
            maxMargin =52;
        }
        else
        {
            heightHeader =74+((136/665)*(jQuery(window).width()-955));
            minMargin =11;
            maxMargin =30;
        }
        
        jQuery('.home .header .logo').height(heightHeader);
        if(jQuery(window).width() < 955)
                jQuery('.header .logo a img').height(heightHeader*0.7).css({'margin-top':minMargin});
        else
        {
            if(jQuery(window).width() > 1620)
                jQuery(' .header .logo a img').height(heightHeader*0.7).css({'margin-top':maxMargin});
            else
                jQuery(' .header .logo a img').height(heightHeader*0.7).css({'margin-top':heightHeader*0.15});
        }
    }
    else
    {
        if(jQuery('body').hasClass('home'))
            heightHeader =55+(128/460)*(jQuery(window).width()-200);
        else
            heightHeader =38+(92/460)*(jQuery(window).width()-200);
        jQuery(' .header .logo').height(heightHeader).find('a img').height(heightHeader*0.8).css('margin-top',heightHeader*0.1);
    }
    
    
    if( jQuery(window).width() > 960 )
        jQuery('.header .bx-wrapper .bx-controls-auto').css('right',(jQuery(window).width()-960)/2 +12)
    else
        jQuery('.header .bx-wrapper .bx-controls-auto').css('right',10);
 

    
    if(jQuery(window).width()>=640 && !jQuery('.portlet-asset-publisher').hasClass('all-news'))
    {
        jQuery('.new').hover(
        function(){
            jQuery(this).find('img').stop(false,true).animate({opacity: 0.1},250);
            jQuery(this).find('.entry-header h2,.entry-content,.entry-meta').stop(false,true).fadeIn(250);
        },
        function(){
            jQuery(this).find('img').stop(false,true).animate({opacity: 1},250);
            jQuery(this).find('.entry-header h2,.entry-content,.entry-meta').stop(false,true).fadeOut(250);
        });
        
    }
    
    
    jQuery('.images .diapo ul,.images .diapo ul li').height((jQuery('.diapo').width()*4)/6);


    var boutiqueTitle = jQuery('.produit h3 a');
    boutiqueTitle.each(function() {
        $(this).text(truncate($(this)));
    });
}

function truncate(jQueryElement){
    var stringToTruncate = jQueryElement.text();
    if (!!stringToTruncate && stringToTruncate.length > 50)
        return stringToTruncate.substring(0,50)+'...';
    else
        return stringToTruncate;
};