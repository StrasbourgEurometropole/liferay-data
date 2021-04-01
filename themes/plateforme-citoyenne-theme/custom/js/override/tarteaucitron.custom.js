tarteaucitron.services.iframelivechat = {
    "key": "iframelivechat",
    "type": "other",
    "name": "Livechat",
    "uri": "",
    "needConsent": true,
    "cookies": ['ssm_au_d', 'PHPSESSID'],
    "js": function () {
        "use strict";
        tarteaucitron.fallback(['tac_iframelivechat'], function (x) {
            var width = x.getAttribute("width"),
                height = x.getAttribute("height"),
                url = x.getAttribute("data-url");
            return '<iframe src="' + url + '" width="' + width + '" height="' + height + '" frameborder="0" scrolling="no" allowtransparency allowfullscreen></iframe>';
        });
    },
    "fallback": function () {
        "use strict";
        var id = 'iframelivechat';
        tarteaucitron.fallback(['tac_iframelivechat'], function (elem) {
            elem.style.width = elem.getAttribute('width') + 'px';
            elem.style.height = elem.getAttribute('height') + 'px';
            return tarteaucitron.engage(id);
        });
    }
};

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

// Service custom : Livechat Creacast
(tarteaucitron.job = tarteaucitron.job || []).push('iframelivechat');

// Service Google Analytics (universal)
tarteaucitron.user.analyticsUa = 'UA-33301756-14'; // Ne pas oublier de vider le champ Identifiant Google Analytics dans les paramètres avancés du site
tarteaucitron.user.analyticsMore = function () {  };
(tarteaucitron.job = tarteaucitron.job || []).push('analytics');

// Service Youtube
//<div class="youtube_player" videoID="video_id" width="width" height="height" theme="theme (dark | light)" rel="rel (1 | 0)" controls="controls (1 | 0)" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" mute="mute (0 | 1)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('youtube');

// Service Daylimotion
//<div class="dailymotion_player" videoID="video_id" width="width" height="height" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" embedType="embedType (video | playlist)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('dailymotion');

// Service custom : Creacast
  (tarteaucitron.job = tarteaucitron.job || []).push('iframecreacast');
  