// Service Youtube
//<div class="youtube_player" videoID="video_id" width="width" height="height" theme="theme (dark | light)" rel="rel (1 | 0)" controls="controls (1 | 0)" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" mute="mute (0 | 1)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('youtube');

// Service Viméo
// <div class="vimeo_player" videoID="video_id" width="width" height="height"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('vimeo');

// Service Daylimotion
//<div class="dailymotion_player" videoID="video_id" width="width" height="height" showinfo="showinfo (1 | 0)" autoplay="autoplay (0 | 1)" embedType="embedType (video | playlist)"></div>
(tarteaucitron.job = tarteaucitron.job || []).push('dailymotion');

function CitronVideoHtml(id, plateforme, autoplay, mute) {
    return '<div class="tac_player ' + plateforme + '_player" videoID="' + id + '" width="1280px" theme="dark" height="auto" showinfo="0" controls="0" rel="0" autoplay=' + autoplay +'" mute="' + mute + '"></div>';
}