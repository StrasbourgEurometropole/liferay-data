<#if backgroundvideo?? && backgroundvideo.data?has_content>
  <video class="parameter-video" autoplay muted loop id="herovideo">
  </video>
</#if>


<style>
.page-header {
  background-image: url(${backgroundpicture.getData()}) !important;
}
</style>

<#if backgroundvideo?? && backgroundvideo.data?has_content>
    <script type="text/javascript">
    
        $(document).ready(function() {
    
            var breakpoint = 1199;
    
            loadVideo();
    
            // Play video when page resizes
            $(window).resize(function() {
                loadVideo();
            });
    
            function loadVideo() {
                var video = document.getElementById('herovideo');
    
                // Remove existing source tags for mobile
                if ($(document).width() < breakpoint + 1) {
                    while (video.firstChild) {
                        video.removeChild(video.firstChild);
                    }
                }
    
                // Add source tags if not already present
                if ($(document).width() > breakpoint) {
                    if (document.querySelectorAll("#herovideo > source").length < 1) {
                        var source1 = document.createElement('source');
    
                        source1.setAttribute('src', '${backgroundvideo.getData()}');
                        source1.setAttribute('type', 'video/mp4');
    
                        video.appendChild(source1);
                    }
    
                    // Play the video
                    $('#herovideo')[0].play();
                }
            }
        });
    </script>
</#if>