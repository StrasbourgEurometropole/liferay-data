<!-- Welcome - fond image -->

<section class="bloc-image" 
    <#if image.getData()?? && image.getData() != "">
    	style="background-image: url('${image.getData()}'); "
    </#if>
>
    <div class="bloc-content">
        <div class="bloc-text ${classCSS.getData()}">
            <#if !classCSS.getData()?has_content > 
                <div class="item-picto" style="background-image:url(${illustration.getData()})"></div>
            </#if>
            <p class="title">${title.getData()}</p>
            <p>${content.getData()}</p>
        </div>
    </div>
</section>