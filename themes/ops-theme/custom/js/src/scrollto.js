// Scroll To Element
$('.ops-scrollto a').click(function (e) {
    e.preventDefault();
    $(this).parents('nav').find('a').removeClass('ops-active');
    $(this).addClass('ops-active');
    var dest = $(this).attr('href');
    var finalPos = $(dest).offset().top;
    $('html,body').animate({scrollTop:finalPos - 100},300);  
});