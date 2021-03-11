$('.pro-bloc-prefooter .pro-ico').on('click',function(e){
    e.preventDefault();
    $('.pro-bloc-prefooter .pro-ico').removeClass('active');
    $(this).addClass('active').siblings().removeClass('active');
});