<!-- Welcome - vidéo -->
<#assign video = entry.getAssetRenderer().getVideo() />

<section class="bloc-video" id="video">
    <div class="video-container" style="background-image:url('${video.imageURL}');" id="player"></div>
    <div class="video-info">
        <p>Vidéo de présentation</p>
    </div>
</section>

<#assign videoURL  = video.getSource(locale) />
<#assign site  = video.getSiteVideo(videoURL) />
<#assign codeEmbed  = video.getEmbedURL(site, videoURL) />
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
        //event.preventDefault();
        content = "<iframe src=\"${codeEmbed?replace('&muted=1', '')}&autoplay=1\" ";
         if ("${site}" == "youtube") {
            content += "id=\"youtubePlayer\" ";
        } 
        content += "width=\"auto\" height=\"auto\" frameborder=\"0\" allowfullscreen allow=\"accelerometer; autoplay;\"></iframe>";

        videoContainer.html(content);
        playButton.hide();
    };
</script>