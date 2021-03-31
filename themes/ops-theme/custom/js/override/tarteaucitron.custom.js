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
// Service Google Analytics (universal)
tarteaucitron.user.analyticsUa = 'UA-32590640-1'; // Ne pas oublier de vider le champ Identifiant Google Analytics dans les paramètres avancés du site
tarteaucitron.user.analyticsMore = function () {  };
(tarteaucitron.job = tarteaucitron.job || []).push('analytics');

// Service Youtube
//<div class="youtube_player" videoID="video_id" width="width" height="height" theme="theme (dark | light)" rel="rel (1 | 0)" controls="controls (1 | 0)" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" mute="mute (0 | 1)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('youtube');

function CitronVideoHtml(id, plateforme, autoplay, mute) {
    return '<div class="tac_player ' + plateforme + '_player" videoID="' + id + '" width="1280px" theme="dark" height="auto" showinfo="0" controls="0" rel="0" autoplay=' + autoplay +'" mute="' + mute + '"></div>';
}