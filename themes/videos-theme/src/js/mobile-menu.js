(function ($) {
  /*** 
   * Run this code when the #nav-toggle link has been tapped
   * or clicked
   */
  $('#nav-toggle').on('touchstart click', function (e) {
    e.preventDefault();
    $('#nav-toggle').toggleClass("active");
    var $body = $('body'),
      $page = $('#page'),

    /* Cross browser support for CSS "transition end" event */
      transitionEnd = 'transitionend webkitTransitionEnd otransitionend MSTransitionEnd';

    /* When the toggle menu link is clicked, animation starts */
    $body.addClass('animating');

    /***
     * Determine the direction of the animation and
     * add the correct direction class depending
     * on whether the menu was already visible.
     */
    if ($body.hasClass('menu-visible')) {
      $body.addClass('right');
      $page.off('touchstart click');
    } else {
      $body.addClass('left');
      $page.on('touchstart click', function (e) {
        if (!$body.hasClass('animating')) {
          e.preventDefault();
          $('#nav-toggle').trigger('click');
        }
      });
    }

    /***
     * When the animation (technically a CSS transition)
     * has finished, remove all animating classes and
     * either add or remove the "menu-visible" class 
     * depending whether it was visible or not previously.
     */
    $page.on(transitionEnd, function () {
      $body.removeClass('animating left right').toggleClass('menu-visible');
      $page.off(transitionEnd);
    });
  });
}) (jQuery);