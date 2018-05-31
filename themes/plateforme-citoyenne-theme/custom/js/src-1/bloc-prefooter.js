$('.pro-bloc-prefooter .pro-ico').on('click',function(e){
    e.preventDefault();
    $('.pro-bloc-prefooter .pro-ico').removeClass('active');
    $(this).addClass('active').siblings().removeClass('active');
});


$('.pro-bloc-prefooter .pro-signature-pacte > a').on('click',function(e){
    e.preventDefault();
    $(this).toggleClass('active');
    if($(this).hasClass('active')){
        $('h3',this).text('Vous avez adhéré au pacte');
        $('span',this).css('display','none');
        if($(this).hasClass('pro-disabled')){
            $('h3',this).text('Signer');
            $('span',this).css('display','block');
        }
    }
    else if($(this).hasClass('pro-disabled')){
        $('h3',this).text('Signer');
        $('span',this).css('display','block');
    }
    else{
        $('h3',this).text('Signer');
        $('span',this).css('display','block');
    }
});