$(window).on( "load",function() {
    if((themeDisplay.getLayoutRelativeURL().indexOf("objets-trouves") > 0) && getCookie('has-consent-objtp-rule') == null) {
     createPopinObjtpRule();
    }
});






function destroyPopinObjtpRule(){
    $('#rubricPopup')[0].style.display ='none';
    $('#rubricPopup').remove().off('clickrubricpopup');
    $('.seu').off('click.rubricPopup').removeClass('overlayed');
}
function createPopinObjtpRule(){
    
    var template =$('#rubricPopup')
    $('#rubricPopup')[0].style.display ='';
    $('body').append(template);
    $('.seu').addClass('overlayed');


    $('#rubricPopup .deny').on('click.rubricPopup', function(e){       
        destroyPopinObjtpRule();
        window.history.back();
    });
    $('#rubricPopup .confirm').on('click.rubricPopup', function(){
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