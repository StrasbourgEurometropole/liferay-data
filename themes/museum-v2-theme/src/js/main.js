
// Dropdown langues
(function($) {
    $('.language-menu > a').on('click', function(e) {
        e.preventDefault();
        $('.language-menu').toggleClass('open');
    });
})(jQuery);

// slider "En bref"
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

  // slider events
  const swiperEvents = new Swiper('#events .swiper', {
      // Optional parameters
      slidesPerView: "auto",
      spaceBetween: 30,
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
        }
      }
    });