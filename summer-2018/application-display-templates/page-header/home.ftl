<#setting locale = locale />
<header class="mns-cover mns-cover-fullimg mns-header" data-fullscreen="85">
    <figure>
        <img alt="" width="1600" height="900" src="${page.expandoBridge.getAttribute('image')}" />
    </figure>
    <div class="video-bg">
        <div class="video-fg">
            <div id="player"></div>            
        </div>
    </div>
    <div class="clicks-preventer">
    </div>
    <div class="container mns-center">
        ${page.getDescription(locale)}
    </div>
</header>
<script src="https://api.dmcdn.net/all.js"></script>
<script>
var player = DM.player(document.getElementById("player"), {
    video: "x54ovnh",
    width: "100%",
    height: "100%",
    params: {
        api: 1,
        autoplay: true,
        mute: true,
        controls: false,
        "endscreen-enable": false
    }
});
player.addEventListener('end', function() {
    player.seek(0);
    player.play();
});
</script>