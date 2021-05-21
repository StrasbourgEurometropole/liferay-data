<!-- Welcome - vidéo -->
<#assign video = entry.getAssetRenderer().getVideo() />

<section class="bloc-video" id="video">
    <div class="video-content">
        <div class="video-container" id="player">
            ${video.getPlayer(locale)}
        </div>
        <div class="video-info" style="background-image:url('${video.imageURL}');" >
            <p>Vidéo de présentation</p>
        </div>
    </div>
</section>

<script>
    var videoContainer = jQuery('.video-container');
    var seeVideoButton = jQuery('.welcome-menu a[href="#video"]');
    var playButton = jQuery('.video-info');
    playButton.one('click', function(event){
        showVideo(event);
    });
    seeVideoButton.one('click', function(event){
        showVideo(event);
    });


    function showVideo(event) {
        playButton.hide();
    };
</script>