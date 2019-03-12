<!-- Welcome - fond blanc -->

<section class="bloc-blanc" >
            <#list image.getSiblings() as cur_image>
                <#if cur_image.getData()?? && cur_image.getData() != "">
                    <#if cur_image?is_first >
                        <div class="bloc-fond-image ${(image.getSiblings()?size gt 1)?then('multiple','')}">
                    </#if>
                    <div class="image" style="background-image:url(${cur_image.getData()});"></div>
                    <#if cur_image?is_last >
                        </div>
                    </#if>
                </#if>
            </#list>
    <div class="bloc-content ${icon.getData()}">
        <p class="title">${title.getData()}</p>
        <p>${content.getData()}</p>
    </div>
</section>