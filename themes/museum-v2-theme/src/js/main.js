(function($) {
  // Dropdown langues
  $('.language-menu > a').on('click', function(e) {
    e.preventDefault();
    $('.language-menu').toggleClass('open');
  });

  // slider "En bref"
  if($('#bref .swiper').length){
    const swiperBref = new Swiper('#bref .swiper', {
      // Optional parameters
      slidesPerView: 1,
      spaceBetween: 30,
      loop: false,

      // If we need pagination
      pagination: {
        el: '#bref .swiper-pagination',
        clickable: true,
      },

      // Navigation arrows
      navigation: {
        nextEl: '#bref .swiper-button-next',
        prevEl: '#bref .swiper-button-prev',
      },

      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 640px
        768: {
          slidesPerView: 2,
          spaceBetween: 40,
        }
      }
    });
  }

  // slider events
  if($('#events .swiper').length){
    const swiperEvents = new Swiper('#events .swiper', {
      // Optional parameters
      slidesPerView: "auto",
      spaceBetween: 30,
      centeredSlides: true,
      loop: false,

      // Navigation arrows
      navigation: {
        nextEl: '#events .swiper-button-next',
        prevEl: '#events .swiper-button-prev',
      },

      // Responsive breakpoints
      breakpoints: {
        // when window width is >= 640px
        768: {
          slidesPerView: 4,
          spaceBetween: 10,
          centeredSlides: false,
        }
      }
    });
  }

  // slider news
  let swiperNews;
  const enableSwiperNews = function() {
    if($('#news .swiper').length){
      swiperNews = new Swiper('#news .swiper', {
        // Optional parameters
        slidesPerView: 1,
        spaceBetween: 30,
        loop: false,

        // Navigation arrows
        navigation: {
          nextEl: '#news .swiper-button-next',
          prevEl: '#news .swiper-button-prev',
        },
      });
    }
  }

  // slider expos
  let swiperExpos;
  const enableSwiperExpos = function() {
    if($('#expo .swiper').length){
      swiperNews = new Swiper('#expo .swiper', {
        // Optional parameters
        slidesPerView: 2,
        spaceBetween: 70,
        loop: false,

        // Navigation arrows
        navigation: {
          nextEl: '#expo .swiper-button-next',
          prevEl: '#expo .swiper-button-prev',
        },
      });
    }
  }

  // liste collections
  let listCollectionsMasonry;
  const enableListCollectionsMasonry = function() {
    if($('#listCollections').length ){
      listCollectionsMasonry = new Masonry('#listCollections', {
        itemSelector: '.oeuvre-thumbnail',
        gutter: '.gutter-sizer',
        percentPosition: true
      });
    }
  };

  // slider entete des musées
  let swiperHeaderMuseum;
  const enableSwiperHeaderMuseum = function() {
    if($('#headerMuseum .swiper').length){
      swiperHeaderMuseum = new Swiper('#headerMuseum .swiper', {
        // Optional parameters
        slidesPerView: 1,
        effect: "fade",
        loop: true,
        autoplay: {
          delay: 10000,
          disableOnInteraction: true
        },

        // Navigation arrows
        navigation: {
          nextEl: '#headerMuseum .swiper-button-next',
          prevEl: '#headerMuseum .swiper-button-prev',
        },

      });
    }
  };
  $(".swiper-button-pause").click(function(){
    if ( swiperHeaderMuseum !== undefined ){
      swiperHeaderMuseum.autoplay.stop();
      $(".swiper-button-pause").css("display", "none");
      $(".swiper-button-play").css("display", "inline-block");
    }
  });

  $(".swiper-button-play").click(function(){
    if ( swiperHeaderMuseum !== undefined ){
      swiperHeaderMuseum.autoplay.start();
      $(".swiper-button-pause").css("display", "inline-block");
      $(".swiper-button-play").css("display", "none");
    }
  });

  // Onglets contacts
  (function($) {
      $(document).ready(function() {
          $('.tab').on('click', function(e) {  
              $('.tab').removeClass('active');
              $(this).addClass('active');
  
              var entryId = $(this).data('entry-id');
              $('.content').removeClass('active');
              $('.content[data-entry-id=' + entryId + ']').addClass('active');
          });
      });
  })(jQuery);

  // Selecteurs
  $('select').each(function(index, element){
    //var placeholder = $(element).find('option[disabled]').text();
    var placeholder = "";
    $(element).select2({
      placeholder: placeholder,
      closeOnSelect: false
    });
  });

  // Cases à cocher et boutons radio
  function setCheckedClass(checkbox) {
      if ($(checkbox).is(':checked')) {
          var name = $(checkbox).attr('name');
          $('.radio [name=' + name + ']').parent('label').removeClass('checked');
          $(checkbox).parent('label').addClass('checked');
      } else {
          $(checkbox).parent('label').removeClass('checked');
      }
  }
  $('input[type=checkbox], input[type=radio]').on('change', function() {
      setCheckedClass(this);
  });
  $('input[type=checkbox], input[type=radio]').each(function() {
      setCheckedClass(this);
  });

  // vérifie si on est en mode smartphone/tablette portrait ou desktop/tablette paysage
  const breakpointTabletLandscape = window.matchMedia( '(min-width:992px)' );
  const tabletLandscapeOrDesktopChecker = function() {
    if ( breakpointTabletLandscape.matches === true ) {
      // On est en mode DESKTOP ou tablette paysage
      if ( swiperNews !== undefined )
        swiperNews.destroy( true, true );
        
      enableSwiperExpos();

      enableSwiperHeaderMuseum();
    } else {
      // On est en mode mobile ou tablette portrait      
      enableSwiperNews();
      
      if ( swiperExpos !== undefined )
        swiperExpos.destroy( true, true );

      if ( swiperHeaderMuseum !== undefined )
        swiperHeaderMuseum.destroy( true, true );
    }
    enableListCollectionsMasonry();
    return;
  };

  // keep an eye on viewport size changes
  breakpointTabletLandscape.addListener(tabletLandscapeOrDesktopChecker);  

  // kickstart
  $(window).on("load",function(){
    tabletLandscapeOrDesktopChecker()
  });
})(jQuery);
