// Dropdown langues
$('.language-menu > a').on('click', function(e) {
  e.preventDefault();
  $('.language-menu').toggleClass('open');
});

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

// slider news
let swiperNews;
const enableSwiperNews = function() {
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
};

// liste collections
let listCollectionsMasonry;
const enableListCollectionsMasonry = function() {
  listCollectionsMasonry = new Masonry('#listCollections', {
    itemSelector: '.oeuvre-thumbnail',
    gutter: 40,
    percentPosition: true
  });
};

// slider entete des musées
let swiperHeaderMuseum;
const enableSwiperHEaderMuseum = function() {
  swiperHeaderMuseum = new Swiper('#headerMuseum .swiper', {
    // Optional parameters
    slidesPerView: 1,
    loop: true,
    autoplay: {
      delay: 4000,
      disableOnInteraction: true
    },

    // Navigation arrows
    navigation: {
      nextEl: '#headerMuseum .swiper-button-next',
      prevEl: '#headerMuseum .swiper-button-prev',
    },

  });
};


// vérifie si on est en mode smartphone/tablette portrait ou desktop/tablette paysage
const breakpointTabletLandscape = window.matchMedia( '(min-width:992px)' );
const tabletLandscapeOrDesktopChecker = function() {
  if ( breakpointTabletLandscape.matches === true ) {
    // On est en mode DESKTOP ou tablette paysage
    enableListCollectionsMasonry();

    if ( swiperNews !== undefined )
      swiperNews.destroy( true, true );

      enableSwiperHEaderMuseum();
    return;
  } else {
    // On est en mode mobile ou tablette portrait
    if (listCollectionsMasonry !== undefined)
      listCollectionsMasonry.destroy();
      
    enableSwiperNews();

    if ( swiperHeaderMuseum !== undefined )
      swiperHeaderMuseum.destroy( true, true );
    return;
  }
};

// keep an eye on viewport size changes
breakpointTabletLandscape.addListener(tabletLandscapeOrDesktopChecker);
// kickstart
window.onload = tabletLandscapeOrDesktopChecker();
