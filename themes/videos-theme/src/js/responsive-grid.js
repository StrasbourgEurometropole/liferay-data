(function ($, window) {
  var adjustGrid = function () {
    if (window.innerWidth > 768) {
      $('.portlet-asset-publisher .video-grid').each(function () {
        $('.video-thumbnail', this).eq(1).removeClass('more');
        $('.video-thumbnail', this).eq(2).removeClass('more');
      });
    }
    if (window.innerWidth <= 768 && window.innerWidth > 480) {
      $('.portlet-asset-publisher .video-grid').each(function () {
        $('.video-thumbnail', this).eq(1).removeClass('more');
        $('.video-thumbnail', this).eq(2).addClass('more');
      });
    }
    if (window.innerWidth < 480) {
      $('.portlet-asset-publisher .video-grid').each(function () {
        $('.video-thumbnail', this).eq(1).addClass('more');
        $('.video-thumbnail', this).eq(2).addClass('more');
      });
    }
  };

  adjustGrid();
  $(window).resize(adjustGrid);

}) (jQuery, window);