<div class="container pro-homepage-video">
    <div class="pro-bloc-video">
        <div class="mask-video">
            <figure class="o80" role="group">
                <img alt="${image.getAttribute("alt")}" width="1170" height="600" src="${image.getData()}"/>
            </figure>
            <div class="caption">
                <#if subtitle.getData()?has_content >
                <div class="pro-bloc-display"><span class="pro-surtitre">${subtitle.getData()}</span></div>
                </#if>
                <h2>
                    ${title.getData()}
                    <#if title_line_2.getData()?has_content >
                    <br> ${title_line_2.getData()}
                    </#if>
                </h2>
                <div class="wrapper-btn">
                    <a href="#play" class="pro-btn-video" title="Lire la vidéo"><span class="icon-ico-lecteur"></span>Voir
                        la vidéo</a>
                    <a href="/web/participer/videos" title="Lien vers la page toutes nos vidéos">Toutes nos vidéos</a>
                </div>
            </div>
        </div>
        <div class="embed-container" data-urlvideo="${video_link.getData()}"></div>
        <p class="legend-video">${video_description.getData()}</p>
    </div>
</div>