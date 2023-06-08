<!-- BLOC IMAGE PARALLAX -->

<section class="pro-bloc-parallax pro-bloc-texte" style="background-image: url(${image.getData()});">
    <div class="container caption ${selectAlignment.getData()}">
        <div class="col-xs-12">
            <h2 class="pro-big-title">
            	${titleLine1.getData()}
            	<#if titleLine2.getData() != "" >
                    <br>
                    ${titleLine2.getData()}
                </#if>
            </h2>
            <p>${text.getData()}</p>
            <#if linkUrl.getData() != "" >
            	<a href="${linkUrl.getData()}" class="pro-btn-yellow" title="${linkLabel.getData()}">En savoir plus</a>
            </#if>
        </div>
    </div>
</section>