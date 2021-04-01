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
tarteaucitron.services.iframecreacast = {
    "key": "iframecreacast",
    "type": "video",
    "name": "Creacast",
    "uri": "",
    "needConsent": true,
    "cookies": ['__utm*'],
    "js": function () {
      "use strict";
      tarteaucitron.fallback(['tac_iframecreacast'], function (x) {
          var video_url = x.getAttribute("videoURL"),
              video_frame;
  
          if (video_url === undefined) {
              return "";
          }
          video_frame = '<iframe  scrolling="yes" height="1000px" frameborder="0" allowfullscreen  src="' + video_url + '"></iframe>';
          return video_frame;
      });
    },
    "fallback": function () {
      "use strict";
      var id = 'iframecreacast';
      tarteaucitron.fallback(['tac_iframecreacast'], function (elem) {
          elem.style.width = elem.getAttribute('width') + 'px';
          elem.style.height = elem.getAttribute('height') + 'px';
          return tarteaucitron.engage(id);
      });
    }
};
  (tarteaucitron.job = tarteaucitron.job || []).push('iframecreacast');