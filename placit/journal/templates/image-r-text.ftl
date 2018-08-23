<!-- BLOC IMAGE A DROITE + TEXTE -->

<section class="container">

    <div class="pro-bloc-txt-img pro-bloc pro-bloc-texte clearfix">
        <div>
            <div class="col-sm-6">
                <figure class="fit-cover" role="group">
                    <img src="${image.getData()}" width="590" height="753" alt="${image.getAttribute("alt")}"/>
                    <figcaption>${image.children[0].data}</figcaption>
                </figure>
            </div>
            
            <div class="col-sm-6">
                <h2 class="pro-big-title">
                    ${titleLine1.getData()}
                    <#if titleLine2.getData()?has_content >
                        <br>
                        ${titleLine2.getData()}
                    </#if>
                 </h2>
                <p>${text.getData()}</p>
                <#if linkUrl.getData()?has_content >
                    <a href="${linkUrl.getData()}" class="pro-btn-yellow" title="${linkLabel.getData()}">En savoir plus</a>
                </#if>
            </div>
        </div>
    </div>
    
</section>