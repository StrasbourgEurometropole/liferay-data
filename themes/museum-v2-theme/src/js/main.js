
// Dropdown langues
(function($) {
    $('.language-menu > a').on('click', function(e) {
        e.preventDefault();
        $('.language-menu').toggleClass('open');
    });
})(jQuery);

// caroussel "En bref"
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