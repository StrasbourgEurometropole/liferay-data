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
(tarteaucitron.job = tarteaucitron.job || []).push('recaptcha');

// Service custom : Creacast
(tarteaucitron.job = tarteaucitron.job || []).push('iframecreacast');