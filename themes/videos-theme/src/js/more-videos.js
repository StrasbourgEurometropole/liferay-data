(function ($) {
  jQuery(".search-asset-results > div").addClass("video-thumbnail");

  $('.btn-more').click(function () {
    var heightTarget = $('.video-thumbnail').css('height');
    var marginTarget = $('.video-thumbnail').css('margin-bottom');
    $('.more', $(this).parents('.video-grid')).animate({'height': heightTarget}, 1000, function () {
      $(this).css('overflow', 'inherit');
      $(this).css('margin-bottom', marginTarget);
    });
    $('.see-all', $(this).parents('.video-grid')).removeClass('hidden');
    $('.see-all', $(this).parents('.video-grid')).removeClass('desktop-only');
    $(this).remove();
  });
})(jQuery);
