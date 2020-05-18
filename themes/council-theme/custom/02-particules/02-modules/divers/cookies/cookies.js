
(function ($) {
   gaProperty = $('[data-site-id]'); // Récupérer le code GA (généré par le CMS, soit dans l'html soit en js)


    // Html qui va populer la div des cookies
    // var cnilBannerAskConsentString = 'En poursuivant votre navigation sur ce site, vous acceptez l\'utilisation de cookies pour réaliser des statistiques de visites anonymes.';
    // // var cnilBannerOptoutString = '<button type="button" class="close" data-dismiss="alert"><span class="sr-only">Fermer</span></button>Votre opposition au dépôt des cookies a bien été prise en compte.';

    // $(document).ready( function() {
    //     $('#cookies .text').html( cnilBannerAskConsentString );
    // });

    // Fermeture du bandeau
    $('#cookies .agree-button').on('click', function(){
        document.cookie = 'hasConsent=true; '+ getCookieExpireDate() +' ; path=/';
        $('#cookies').remove();
    });

    var disableStr = 'ga-disable-' + gaProperty;

    if (document.cookie.indexOf('hasConsent=false') > -1)
        window[disableStr] = true;

    function getCookieExpireDate()
    { 
        var cookieTimeout = 34214400000;
        var date = new Date();
        date.setTime(date.getTime()+cookieTimeout);
        var expires = "; expires="+date.toGMTString();
        return expires;
    }

    function askConsent()
    {
        $('#cookies').show();
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
    
    // function delCookie(name )
    // {
    //     var path = ";path=" + "/";
    //     var expiration = "Thu, 01-Jan-1970 00:00:01 GMT";
    //     var domain=";domain=" + "."+document.location.hostname;
    //     document.cookie = name + "=" + path + domain + ";expires=" + expiration;
    //     domain=document.location.hostname;
    //     domain=";domain=" + "."+domain.substring(domain.lastIndexOf(".", domain.lastIndexOf(".") - 1) + 1);
    //     document.cookie = name + "=" + path + domain + ";expires=" + expiration;
    // }
    
    // function deleteAnalyticsCookies()
    // {
    //     var cookieNames = ["__utma","__utmb","__utmc","__utmz","_ga"]
    //     for (var i=0; i<cookieNames.length; i++)
    //         delCookie(cookieNames[i])
    // }

    // function gaOptout() {
    //     document.cookie = disableStr + '=true;'+ getCookieExpireDate() +' ; path=/';       
    //     document.cookie = 'hasConsent=false;'+ getCookieExpireDate() +' ; path=/';
        
    //     $('#cnil_banner_consent div').fadeOut( function() {
    //         $('#cnil_banner_consent').html( cnilBannerOptoutString );
    //         $('#cnil_banner_consent div').fadeIn();
    //     });
        
    //     window[disableStr] = true;
    //     deleteAnalyticsCookies();
    // }

    var consentCookie =  getCookie('hasConsent');
    if (consentCookie == null)
    {
        var referrer_host = document.referrer.split('/')[2]; 
        if ( referrer_host != document.location.hostname )
        {        
            window[disableStr] = true;
            window[disableStr] = true;
            $(document).ready( function() {
                askConsent();
            });
        }
        else
            document.cookie = 'hasConsent=true; '+ getCookieExpireDate() +' ; path=/'; 
    }
}(jQuery));
