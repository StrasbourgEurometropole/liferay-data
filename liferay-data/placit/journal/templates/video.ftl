<!-- BLOC VIDEO -->

<div class="container pro-bloc-wrapper-video pro-bloc-texte">
    <div class="pro-bloc-video bloc-standard">
        <div class="mask-video">
            <figure class="o80" role="group">
                <#if image.getData()?has_content >
                    <img src="${image.getData()}" alt="${image.getAttribute("alt")}" width="960" height="600" />
                </#if>
            </figure>
            <a href="#play" class="btn-ytbe" title="Lire la vidéo">
                <span class="pro-btn-video" title="Lire la vidéo"><span class="icon-ico-lecteur"></span>Voir la vidéo</span>
            </a>
        </div>
        <div class="embed-container" data-urlvideo="${videoLink.getData()}"></div>
        <p class="legend-video">${legend.getData()}</p>
    </div>
</div>