// Ouverture du menu mobile
$('.ops-burger-menu').click(function () {
    if (!$(this).hasClass('ops-open')) {
        $(this).addClass('ops-open');
        // $('body').css('overflow','hidden');
        $('#ops-header').addClass('ops-menu-open');
    }
    else {
        $(this).removeClass('ops-open');
        $('#ops-header').removeClass('ops-menu-open');
        $(this).addClass('ops-closing');
        // $('body').css('overflow','auto');
        setTimeout(function(){
            $('.ops-burger-menu').removeClass('ops-closing');
        },500);
    }
});



/* Detect the scroll of the page and animate the menu */
$(window).on('scroll', function (e) {
    var st = $(this).scrollTop();

    if (st > 100) {
        $("#ops-wrapper").addClass("is-scrolled");
    }
    else {
        $("#ops-wrapper").removeClass("is-scrolled");
    }
});



// Ouverture - Fermeture de la DropDown Menu en accordéon
var $lienNav = $('.ops-wrapper-nav nav > ul > li.ops-has-sub-menu > a');
$lienNav.each(function(){
    $(this).on('click',function(e){
        e.preventDefault();
        var $otherSubOpened = $('.ops-wrapper-nav nav > ul > li.ops-has-sub-menu.ops-sub-menu-open').first();
        var $ulOpen = $('.ops-wrapper-nav nav > ul > li.ops-has-sub-menu.ops-sub-menu-open > ul').first();
        var $parent = $(this).parents('.ops-has-sub-menu').first();
        if($otherSubOpened[0] != $parent[0]){
            if($otherSubOpened){
                $otherSubOpened.removeClass('ops-sub-menu-open');
                $ulOpen.removeClass('in');
            }
            setTimeout(function(){
                $parent.addClass('ops-sub-menu-open');
            },50);
        }else{
            $otherSubOpened.removeClass('ops-sub-menu-open');
            // $ulOpen.removeClass('in');
            $otherSubOpened = null;
        }
    });
});


// [MOVE MENU TOP] - À 980px et inférieur, on déplace le header-top en bas de la nav wrapper-nav
var HeaderTopPosition = 0;
var $headerLeft = $('.ops-header-left .ops-wrapper-btn');
var $headerRight = $('.ops-header-right .ops-social');
if($headerLeft.length > 0 || $headerRight.length > 0){
    moveTheStickyContainerAside();
    $(window).on('resize',function(){
        moveTheStickyContainerAside();
    });
    $(window).on('orientationchange',moveTheStickyContainerAside());
}

function moveTheStickyContainerAside(){
    if(document.body.clientWidth < 980 && HeaderTopPosition == 0){
        $('.ops-wrapper-nav nav').prepend($headerLeft);
        $('.ops-wrapper-nav nav').append($headerRight);
        HeaderTopPosition = 1;
    }
    else if(document.body.clientWidth > 980 && HeaderTopPosition == 1){
        $('header#ops-header .ops-header-left').append($headerLeft);
        $('header#ops-header .ops-header-right').prepend($headerRight);
        HeaderTopPosition = 0;
    }
}