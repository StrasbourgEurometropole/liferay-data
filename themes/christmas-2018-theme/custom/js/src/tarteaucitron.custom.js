// Service Google Analytics (universal)
tarteaucitron.user.analyticsUa = 'UA-19910890-1'; // Ne pas oublier de vider le champ Identifiant Google Analytics dans les paramètres avancés du site
tarteaucitron.user.analyticsMore = function () {  };
(tarteaucitron.job = tarteaucitron.job || []).push('analytics');

// Service Youtube
//<div class="youtube_player" videoID="video_id" width="width" height="height" theme="theme (dark | light)" rel="rel (1 | 0)" controls="controls (1 | 0)" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" mute="mute (0 | 1)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('youtube');

// Service Daylimotion
//<div class="dailymotion_player" videoID="video_id" width="width" height="height" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" embedType="embedType (video | playlist)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('dailymotion');

// Service Facebook Pixel
tarteaucitron.user.facebookpixelId = '1023621684487107'; tarteaucitron.user.facebookpixelMore = function () { /* add here your optionnal facebook pixel function */ };
(tarteaucitron.job = tarteaucitron.job || []).push('facebookpixel');

// Service reCAPTCHA
(tarteaucitron.job = tarteaucitron.job || []).push('recaptcha');