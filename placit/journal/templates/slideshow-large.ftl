<!-- BLOC DIAPORAMA -->

<section class="pro-bloc-image-slider pro-bloc-texte">
    <div class="owl-carousel owl-theme owl-slider">

        <#list titleLine1.getSiblings() as cur_title>
            <div class="item">
                <figure class="fit-cover" role="group">
                    <img src="${cur_title.getChild("image").getData()}" width="1600" height="700" alt="${cur_title.getChild("image").getAttribute("alt")}"/>
                    <figcaption>${cur_title.getChild("image").getChild("copyright").getData()}</figcaption>
                </figure>
                <div class="container caption ${cur_title.getChild("selectAlignment").getData()}">
                    <div class="col-xs-12">
                        <h3>
                            ${cur_title.getData()}
                            <#if cur_title.getChild("titleLine2").getData() !="" >
                                <br>
                                ${cur_title.getChild("titleLine2").getData()}
                            </#if>
                        </h3>
                        <p>${cur_title.getChild("legend").getData()}</p>
                        <#if cur_title.getChild("linkUrl").getData() !="" >
                            <a href="${cur_title.getChild("linkUrl").getData()}" class="pro-btn-yellow" title="${cur_title.getChild("linkLabel").getData()}">En savoir plus</a>
                        </#if>
                    </div>
                </div>
            </div>
        </#list>

    </div>
</section>