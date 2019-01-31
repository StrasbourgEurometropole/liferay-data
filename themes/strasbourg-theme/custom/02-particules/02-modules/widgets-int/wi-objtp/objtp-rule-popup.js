$(window).load(function() {
    if((themeDisplay.getLayoutRelativeURL().indexOf("objets-trouves") > 0) && getCookie('has-consent-objtp-rule') == null) {
     createPopinObjtpRule();
    }
});






function destroyPopinObjtpRule(){
    $('#favConfirm')[0].style.display ='none';
    $('#favConfirm').remove().off('clickfavConfirm');
    $('.seu').off('click.favconfirm').removeClass('overlayed');
}
function createPopinObjtpRule(){
    
    var template =$('#favConfirm')
    $('#favConfirm')[0].style.display ='';
    $('body').append(template);
    $('.seu').addClass('overlayed');


    $('#favConfirm .deny').on('click.favConfirm', function(e){       
        destroyPopinObjtpRule();
        window.history.back();
    });
    $('#favConfirm .confirm').on('click.favConfirm', function(){
        destroyPopinObjtpRule();
        document.cookie = 'has-consent-objtp-rule=true; '+ getCookieExpireDate() +' ; path=/';
    });

}


 function getCookie(NomDuCookie)
{
    if (document.cookie.length > 0)
    {        
        begin = document.cookie.indexOf(NomDuCookie+"=");
        if (begin != -1)
        {
            begin += NomDuCookie.length+1;
            end = document.cookie.indexOf(";", begin);
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(begin, end)); 
        }
    }
    return null;
}

function getCookieExpireDate()
{ 
    var cookieTimeout = 34214400000;
    var date = new Date();
    date.setTime(date.getTime()+cookieTimeout);
    var expires = "; expires="+date.toGMTString();
    return expires;
}