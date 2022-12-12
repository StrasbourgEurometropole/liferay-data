// surcharge de la méthode engage
/*tarteaucitron.engage = function (id) {
    "use strict";
    var html = '',
        r = Math.floor(Math.random() * 100000),
        engage = tarteaucitron.services[id].name + ' ' + tarteaucitron.lang.fallback;

    if (tarteaucitron.lang['engage-' + id] !== undefined) {
        engage = tarteaucitron.lang['engage-' + id];
    }

    html += '<div class="tac_activate tac_activate_' + id + '">';
    html += '   <div class="tac_float">';
    html += '      ' + engage;
    html += '      <button type="button" class="tarteaucitronAllow" id="Eng' + r + 'ed' + id + '">';
    html += '          <span class="tarteaucitronCheck"></span> ' + tarteaucitron.lang.allow;
    html += '       </button>';
    html += '   </div>';
    html += '</div>';

    return html;
}*/

// Définition de la langue par défaut
window.tarteaucitronForceLanguage = document.documentElement.lang === undefined ? "en" : document.documentElement.lang.slice(0,2);

// Définition des textes de tarteaucitron
switch (window.tarteaucitronForceLanguage) {
    case 'fr':
        var tarteaucitronCustomText = {
            "alertBigPrivacy": "Nous utilisons des cookies sur ce site pour notamment améliorer votre expérience d’utilisation, fournir des services via des contenus animés et interactifs de réseaux sociaux et vidéos, des messages de communication personnalisés ou encore effectuer des analyses de performance. Vous pouvez à tout moment faire part de vos choix :",
            "acceptAll" : "ACCEPTER TOUS LES COOKIES",
            "allowAll" : "ACCEPTER TOUS LES COOKIES",
            "denyAll" : "REFUSER TOUS LES COOKIES",
            "personalize" : "En savoir plus et personnaliser",
            "privacyUrl" : "En savoir plus sur la gestion des cookies",
            "allow" : "Accepter",
            "deny" : "Refuser",
        };
        break;
    case 'de':
        var tarteaucitronCustomText = {
            "alertBigPrivacy": "Diese Website verwendet Cookies, um die Benutzererfahrung zu gewährleisten, Dienstleistungen über animierte und interaktive soziale Netzwerke und Videos, personalisierte Informationen und Benachrichtigungen bereitzustellen, oder Leistungsanalysen durchzuführen.  Sie können jederzeit Ihre Wünsche bezüglich Cookies äußern und ändern, und zwar auf folgende Weise:",
            "acceptAll" : "Cookies zulassen",
            "allowAll" : "Cookies zulassen",
            "denyAll" : "Cookies ablehnen",
            "personalize" : "Mehr Informationen & Cookies-Einstellungen",
            "privacyUrl" : "Mehr Informationen über Cookies-Einstellungen",
            "allow" : "Zulassen",
            "deny" : "Ablehnen",
        };
        break;
    case 'en':
    default:
        var tarteaucitronCustomText = {
            "alertBigPrivacy": "We use cookies on this website to give you the best possible experience, provide services via animated and interactive social network and video content, personalized communication messages, and for performance analysis. You can select/change/modify your cookie settings at any time:",
            "acceptAll" : "ACCEPT ALL COOKIES",
            "allowAll" : "ACCEPT ALL COOKIES",
            "denyAll" : "DECLINE ALL COOKIES",
            "personalize" : "Learn more & settings",
            "privacyUrl" : "Learn more about cookie management",
            "allow" : "Accept",
            "deny" : "Decline",
        };
}

// Initialisation de tarteaucitron
tarteaucitron.init({
    "privacyUrl": "https://www.strasbourg.eu/donnees-personnelles", /* Privacy policy url */

    "hashtag": "#tarteaucitron", /* Open the panel with this hashtag */
    "cookieName": "tarteaucitron", /* Cookie name */

    "orientation": "bottom", /* Banner position (top - bottom) */
    
    "showAlertSmall": false, /* Show the small banner on bottom right */
    "cookieslist": false, /* Show the cookie list */
    
    "closePopup": false, /* Show a close X on the banner */

    "showIcon": false, /* Show cookie icon to manage cookies */
    "iconPosition": "BottomRight", /* BottomRight, BottomLeft, TopRight and TopLeft */

    "adblocker": false, /* Show a Warning if an adblocker is detected */
    
    "DenyAllCta" : true, /* Show the deny all button */
    "AcceptAllCta" : true, /* Show the accept all button when highPrivacy on */
    "highPrivacy": true, /* HIGHLY RECOMMANDED Disable auto consent */
                     
    "handleBrowserDNTRequest": false, /* If Do Not Track == 1, disallow all */

    "removeCredit": true, /* Remove credit link */
    "moreInfoLink": false, /* Show more info link */

    "useExternalCss": false, /* If false, the tarteaucitron.css file will be loaded */
    "useExternalJs": false, /* If false, the tarteaucitron.js file will be loaded */

    //"cookieDomain": ".my-multisite-domaine.fr", /* Shared cookie for multisite */
                    
    "readmoreLink": "", /* Change the default readmore link */

    "mandatory": true, /* Show a message about mandatory cookies */

});

// Ajout des services supplémentaires
tarteaucitron.services.iframelivechatcreacast = {
    "key": "iframelivechatcreacast",
    "type": "other",
    "name": "Livechat",
    "uri": "",
    "needConsent": true,
    "cookies": ['ssm_au_d', 'PHPSESSID'],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tac_iframelivechatcreacast'], function (x) {
            var width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                frameborder = x.getAttribute("frameborder"),
                scrolling = x.getAttribute("scrolling"),
                url = x.getAttribute("data-url");
            return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="' + frameborder + '" scrolling="' + scrolling + '" allowtransparency allowfullscreen></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'iframelivechatcreacast';
        tarteaucitron.fallback(['tac_iframelivechatcreacast'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

tarteaucitron.services.iframevideoscreacast = {
    "key": "iframevideoscreacast",
    "type": "video",
    "name": "Creacast Vidéo",
    "uri": "",
    "needConsent": true,
    "cookies": ['__utm*'],
    "js": function () {
      "use strict";
      tarteaucitron.fallback(['tac_iframevideoscreacast'], function (x) {
        var width = x.getAttribute("width"),
            height = x.getAttribute("height"),
            frameborder = x.getAttribute("frameborder"),
            scrolling = x.getAttribute("scrolling"),
            url = x.getAttribute("data-url");
  
          if (url === undefined) {
              return "";
          }

          return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="' + frameborder + '" scrolling="' + scrolling + '" allowtransparency allowfullscreen></iframe>';
      });
    },
    "fallback": function () {
      "use strict";
      var id = 'iframevideoscreacast';
      tarteaucitron.fallback(['tac_iframevideoscreacast'], function (elem) {
          elem.style.width = elem.getAttribute('width') + 'px';
          elem.style.height = elem.getAttribute('height') + 'px';
          return tarteaucitron.engage(id);
      });
    }
};

tarteaucitron.services.iframepublicationsfacebook = {
    "key": "iframepublicationsfacebook",
    "type": "api",
    "name": "Publications Facebook",
    "uri": "",
    "needConsent": true,
    "cookies": ['__utm*'],
    "js": function () {
      "use strict";

      if (tarteaucitron.user.appId === undefined) {
          return;
      }
      tarteaucitron.fallback(['tac_iframepublicationsfacebook'], '');
      tarteaucitron.addScript('https://connect.facebook.net/fr_FR/sdk.js#xfbml=1&version=v3.3&appId=' + tarteaucitron.user.appId + '&autoLogAppEvents=1','','',true,"crossorigin","anonymous",false);
      if (tarteaucitron.isAjax === true) {
          if (typeof FB !== "undefined") {
              FB.XFBML.parse();
          }
      }
    },
    "fallback": function () {
      "use strict";
      var id = 'iframepublicationsfacebook';
      tarteaucitron.fallback(['tac_iframepublicationsfacebook'], function (elem) {
          return tarteaucitron.engage(id);
      });
    }
};

// recaptchaEMS
tarteaucitron.services.recaptcha_ems = {
    "key": "recaptcha_ems",
    "type": "api",
    "name": "reCAPTCHA EMS",
    "uri": "https://policies.google.com/privacy",
    "needConsent": true,
    "cookies": ['nid'],
    "js": function () {
        "use strict";
        window.tacRecaptchaOnLoad = tarteaucitron.user.recaptchaOnLoad || function() {};
        
        tarteaucitron.fallback(['g-recaptcha'], function (x) {
            tarteaucitron.user.hasRecaptcha = true;
            return '';
        });  

        if(tarteaucitron.user.hasRecaptcha){
            if (tarteaucitron.user.recaptchaapi === undefined) {
                tarteaucitron.addScript('https://www.google.com/recaptcha/api.js?onload=tacRecaptchaOnLoad');
            } else {
                tarteaucitron.addScript('https://www.google.com/recaptcha/api.js?onload=tacRecaptchaOnLoad&render=' + tarteaucitron.user.recaptchaapi);
            } 
        }     
    },
    "fallback": function () {
        "use strict";
        var id = 'recaptcha_ems';
        tarteaucitron.fallback(['g-recaptcha'], tarteaucitron.engage(id));
    }
};

tarteaucitron.services.iframevideosfacebook = {
    "key": "iframevideosfacebook",
    "type": "video",
    "name": "Vidéos Facebook",
    "uri": "",
    "needConsent": true,
    "cookies": ['fr','presence','c_user','xs','x-referer','spin','wd','m_pixel_ratio','ssm_au_c','ssm_au_c','sb','datr'],
    "js": function () {
      "use strict";
      tarteaucitron.fallback(['tac_iframevideosfacebook'], function (x) {
        var width = x.getAttribute("width"),
            height = x.getAttribute("height"),
            frameborder = x.getAttribute("frameborder"),
            allow = x.getAttribute("allow"),
            allowfullscreen = x.getAttribute("allowfullscreen"),
            scrolling = x.getAttribute("scrolling"),
            url = x.getAttribute("data-url");
  
          if (url === undefined) {
              return "";
          }

          return '<iframe allow="' + allow + '" allowfullscreen="' + allowfullscreen + '" scrolling="' + scrolling + '" src="' + url + '" style="border:none;overflow:hidden" width="' + width + '" height="' + height + '" frameborder="' + frameborder + '"></iframe>';
      });
    },
    "fallback": function () {
      "use strict";
      var id = 'iframevideosfacebook';
      tarteaucitron.fallback(['tac_iframevideosfacebook'], function (elem) {
          return tarteaucitron.engage(id);
      });
    }
};


// genially
tarteaucitron.services.genially = {
    "key": "genially",
    "type": "api",
    "name": "genially",
    "uri": "https://www.genial.ly/cookies",
    "needConsent": true,
    "cookies": ['_gat', '_ga', '_gid'],
    "js": function () {
        "use strict";

        tarteaucitron.fallback(['tac_genially'], function (x) {
            var frame_title = tarteaucitron.fixSelfXSS(x.getAttribute("title") || 'genially iframe'),
                width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                geniallyid = x.getAttribute("geniallyid"),
                allowfullscreen = x.getAttribute("allowfullscreen");

            return '<div style="position: relative; padding-bottom: 109.00%; padding-top: 0; height: 0;"><iframe style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;" title="' + frame_title + '" src="https://view.genial.ly/' + geniallyid + '" width="' + width + '" height="' + height + '" scrolling="auto" allowtransparency ' + (allowfullscreen == '0' ? '' : ' webkitallowfullscreen mozallowfullscreen allowfullscreen') + '></iframe></div>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'genially';
        tarteaucitron.fallback(['tac_genially'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

// load custom css
var scripts = document.getElementsByTagName('script'),
path = scripts[scripts.length - 1].src.split('?')[0],
cdn = path.split('/').slice(0, -2).join('/') + '/',
customCSSLink = document.createElement('link');
customCSSLink.rel = 'stylesheet';
customCSSLink.type = 'text/css';
customCSSLink.href = cdn + 'css/tarteaucitron.css';
document.getElementsByTagName('head')[0].appendChild(customCSSLink);