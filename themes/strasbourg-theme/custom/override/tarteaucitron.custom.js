// Définition des textes de tarteaucitron
switch (window.tarteaucitronForceLanguage) {
    case 'fr':
        var tarteaucitronCustomText = {
            "privacyUrl" : "Pour plus d’informations, nous vous invitons à consulter notre politique de gestion des cookies et autres traceurs",
            "disclaimer" : "La Ville et l’Eurométropole de Strasbourg utilisent des cookies sur leur site internet Strasbourg.eu afin d’améliorer l’expérience d’utilisation, fournir des services via des contenus animés et interactifs de réseaux sociaux et vidéos, des messages de communication personnalisés ou effectuer des analyses d’audience.</br></br>Des traceurs provenant de tiers sont également utilisés sur ce site. Via des cookies, ces tiers collecteront et utiliseront vos données relatives à l’adresse IP, au terminal ou navigateur utilisé, et aux activités effectuées sur le site pour des finalités qui leur sont propres.</br></br>Cette fenêtre vous permet de donner ou retirer votre consentement, soit globalement (en cliquant sur le bouton \"Accepter tous les cookies\" ou \"Refuser tous les cookies\") soit par finalité (en cliquant sur le lien \"En savoir plus et personnaliser\").</br></br>Nous conservons votre choix pendant 13 mois. Vous pouvez changer d’avis à tout moment en cliquant sur \"Gestion des cookies\" en pied de page du site Strasbourg.eu.",
        };
        tarteaucitron.parameters.privacyUrl="https://www.strasbourg.eu/politique-cookies";
        break;
    case 'de':
        var tarteaucitronCustomText = {
            "privacyUrl" : "Mehr erfahren anhand unserer Verwaltungsrichtlinie für Cookies und andere Tracer",
            "disclaimer" : "Die Stadt und die Eurometropole Straßburg verwenden Cookies auf ihren Websites, um das Nutzererlebnis zu verbessern, Dienste über animierte und interaktive Inhalte aus sozialen Netzwerken und Videos sowie personalisierte Kommunikationsbotschaften anzubieten oder Datenverkehrsanalysen durchzuführen.</br>Auf dieser Website werden auch Tracer von Drittanbietern verwendet. Über Cookies sammeln und nutzen diese Dritten Ihre Daten bezüglich der IP-Adresse, des verwendeten Endgeräts oder Browsers und der auf der Website ausgeübten Aktivitäten für ihre eigenen Zwecke.</br></br>In diesem Fenster können Sie Ihre Zustimmung entweder global (durch Klicken auf die Schaltfläche „Cookies Zulassen“ oder „Cookies ablehnen“) oder zweckgebunden (durch Klicken auf den Link „Mehr Informationen & Cookies-Einstellungen“) erteilen oder widerrufen.</br>Wir bewahren Ihre Auswahl 13 Monate lang auf. Sie können Ihre Meinung jederzeit ändern, indem Sie auf „Cookies-Einstellungen“ in der Fußzeile der Website klicken.",
        };
        tarteaucitron.parameters.privacyUrl="https://int.strasbourg.eu/de/cookie-politik";
        break;
    case 'en':
    default:
        var tarteaucitronCustomText = {
            "privacyUrl" : "For more information, please consult our managing cookies and other trackers policy",
            "disclaimer" : "The City and the Eurometropolis of Strasbourg use cookies on their websites to improve the user experience, provide services via animated and interactive social media content and videos, personalised communication messages or conduct audience analyses.</br>Third-party trackers are also used on this website. Via cookies, these third parties will collect and use your data regarding the IP address, device or browser used and the activities carried out on the site for their own purposes.</br>This window allows you to give or withdraw your consent, either entirely (by clicking the “Accept all cookies” or “Decline all cookies” button) or depending on the purpose (by clicking the “Learn more & settings” link).</br>We store your choice for 13 months. You can change your mind at any time by clicking “Cookie settings” at the bottom of the website's page.",
        };
        tarteaucitron.parameters.privacyUrl="https://int.strasbourg.eu/en/cookie-policy";
}


// Service Google Analytics (universal)
// tarteaucitron.user.analyticsUa = 'UA-33301756-13'; -> français
// tarteaucitron.user.analyticsUa = 'UA-36152381-1'; -> int
tarteaucitron.user.analyticsMore = function () {  };
(tarteaucitron.job = tarteaucitron.job || []).push('analytics');

// Service Youtube
//<div class="youtube_player" videoID="video_id" width="width" height="height" theme="theme (dark | light)" rel="rel (1 | 0)" controls="controls (1 | 0)" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" mute="mute (0 | 1)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('youtube');

// Service Daylimotion
//<div class="dailymotion_player" videoID="video_id" width="width" height="height" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" embedType="embedType (video | playlist)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('dailymotion');

// Service reCAPTCHA
(tarteaucitron.job = tarteaucitron.job || []).push('recaptcha_ems');

(tarteaucitron.job = tarteaucitron.job || []).push('soundcloud');
(tarteaucitron.job = tarteaucitron.job || []).push('calameo');
(tarteaucitron.job = tarteaucitron.job || []).push('vimeo');