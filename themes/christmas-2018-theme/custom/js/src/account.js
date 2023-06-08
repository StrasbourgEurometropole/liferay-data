(function ($) {
    

    $(document).ready(function(){
        $('.mns-top-header #trigger-account-menu').on('click', function(){
            $(this).next('#account-menu').toggle();
        })
        
        .parents('.nav-account').on('mouseenter', function(){
            $(this).find('#account-menu').show();
        })
        .on('mouseleave', function(){
            $(this).find('#account-menu').hide();
        })
    });

}(jQuery)); 