<div class="museum-home">
    <div class="video-container">
        <div id="player"></div>
    </div>
    <div class="cover">
        <div class="cover-image">
            <img src="${image.getData()}" alt="${image.getAttribute('alt')}">
        </div>
        <div class="cover-text">
            <div class="cover-text-1">${firstLine.getData()}</div>
            <div class="cover-text-2">${secondLine.getData()}</div>
            <button class="play"></button>
        </div>
    </div>
    <div class="welcome-text">
        <div class="welcome-text-1">${firstLine.getData()}</div>
        <div class="welcome-text-2">${secondLine.getData()}</div>
    </div>
</div>
<script src="https://api.dmcdn.net/all.js"></script>
<script>
    var source = "${videoSource.getData()}";
    if (source === 'youtube') {
        var tag = document.createElement('script');

        tag.src = "https://www.youtube.com/iframe_api";
        var firstScriptTag = document.getElementsByTagName('script')[0];
        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

        $('.museum-home .video-container').hide();
        var player;
        function onYouTubeIframeAPIReady() {
            $('.museum-home .play').on('click', function() {
                $('.museum-home .video-container').show();
                $('.cover').remove();
                player = new YT.Player('player', {
                    videoId: '${videoId.getData()}',
                    events: {
                        'onReady': onPlayerReady
                    }
                });
            });
        }

        function onPlayerReady(event) {
            event.target.playVideo();
        }
    } else if (source === 'dm') {
        $('.museum-home .video-container').hide();
        $('.museum-home .play').on('click', function() {
            $('.museum-home .video-container').show();
            $('.cover').remove();
            var player = DM.player(document.getElementById('player'), {
                video: '${videoId.getData()}',
                params: {
                    autoplay: true
                }
            });
        });
    }

</script>
