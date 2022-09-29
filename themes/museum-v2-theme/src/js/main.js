
// Dropdown langues
(function($) {
    $('.language-menu > a').on('click', function(e) {
        e.preventDefault();
        $('.language-menu').toggleClass('open');
    });
})(jQuery);