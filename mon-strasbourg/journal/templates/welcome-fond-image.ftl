<!-- Welcome - fond image -->

<section class="bloc-image" 
    <#if image.getData()?? && image.getData() != "">
    	style="background-image: url('${image.getData()}'); "
    </#if>
>
    <div class="bloc-content ${icon.getData()}">
        <p class="title">${title.getData()}</p>
        <p>${content.getData()}</p>
    </div>
</section>