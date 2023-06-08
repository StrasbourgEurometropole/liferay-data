<!-- BLOC IMAGES EN COLONNE -->

<section class="container pro-bloc-colonne pro-bloc-texte">
    <div class="row" data-egalize="> *">

        <#list titleLine1.getSiblings() as cur_title>
            <div class="${selectNbColumns.getData()}">
                <#if cur_title.getChild("linkUrl").getData() !="" >
                    <a href="${cur_title.getChild("linkUrl").getData()}" title="${cur_title.getChild("linkLabel").getData()}">
                </#if>
                    <figure role="group">
                        <img src="${cur_title.getChild("image").getData()}" width="445" height="313" alt="${cur_title.getChild("image").getAttribute("alt")}"/>
                        <figcaption>${cur_title.getChild("image").getChild("copyright").getData()}</figcaption>
                    </figure>
                    <div class="pro-bloc-content-actu">
                        <h3>
                            ${cur_title.getData()}
                            <#if cur_title.getChild("titleLine2").getData() !="" >
                                <br>
                                ${cur_title.getChild("titleLine2").getData()}
                            </#if>
                        </h3>
                        <p>${cur_title.getChild("legend").getData()}</p>
                        <#if cur_title.getChild("linkUrl").getData() !="" >
                            <span class="pro-link">En savoir plus</span>
                        </#if>
                    </div>
                    <#if cur_title.getChild("linkUrl").getData() !="" >
                        </a>
                    </#if>
            </div>
        </#list>

    </div>
</section>