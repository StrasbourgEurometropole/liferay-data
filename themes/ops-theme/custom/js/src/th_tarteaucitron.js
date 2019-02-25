tarteaucitron.init({
    "hashtag": "#rgpd", /* Ouverture automatique du panel avec le hashtag */
    "highPrivacy": false, /* désactiver le consentement implicite (en naviguant) ? */
    "orientation": "bottom", /* le bandeau doit être en haut (top) ou en bas (bottom) ? */
    "adblocker": false, /* Afficher un message si un adblocker est détecté */
    "showAlertSmall": false, /* afficher le petit bandeau en bas à droite ? */
    "cookieslist": false, /* Afficher la liste des cookies installés ? */
    "removeCredit": false /* supprimer le lien vers la source ? */
});

// Ajouter ici tous les services susceptibles de déposer des cookies (cf https://opt-out.ferank.eu/fr/install/)
// pour les services nécéssitants des modifs du code html, se referer à la classe ThuriaAuCitron

// Google map (initialisé dans th_maps)


// analytics
// tarteaucitron.user.gtagUa = 'UA-117118653-1';
// tarteaucitron.user.gtagMore = function () { /* add here your optionnal gtag() */ };
// (tarteaucitron.job = tarteaucitron.job || []).push('gtag');

// médias
(tarteaucitron.job = tarteaucitron.job || []).push('youtube');
(tarteaucitron.job = tarteaucitron.job || []).push('vimeo');
(tarteaucitron.job = tarteaucitron.job || []).push('dailymotion');



function CitronVideoHtml(id, plateforme, autoplay, mute) {
    return '<div class="tac_player ' + plateforme + '_player" videoID="' + id + '" width="1280px" theme="dark" height="auto" showinfo="0" controls="0" rel="0" autoplay=' + autoplay +'" mute="' + mute + '"></div>';
}