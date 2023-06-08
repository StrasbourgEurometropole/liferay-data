<!-- Welcome - fond bleu -->

<section class="bloc-bleu" >
    <div class="bloc-content">
        <div class="bloc-text ${classCSS.getData()}">
            <#if !classCSS.getData()?has_content > 
                <div class="item-picto" style="background-image:url(${illustration.getData()})"></div>
            </#if>
            <p class="title">${title.getData()}</p>
            <p>${content.getData()}</p>
        </div>
        <#if image.getData()?? && image.getData() != "">
            <div class="bloc-fond-image">
                <div class="image" style="background-image: url('${image.getData()}');">
                </div>
            </div>
        </#if>
    </div>
</section>