<#setting locale = locale />

<!-- Slider d'images -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<div class="mns-slider-full mns-p50">
    <div class="owl-carousel owl-theme" id="owl-full">
        <#if image?has_content && image.getSiblings()?has_content>
            <#list image.getSiblings() as curImage>
                <div class="item">
                    <figure class="fit-cover">
                        <img src="${curImage.getData()}" alt="${curImage.children[0].getData()}" width="1600" height="1000" />
                    </figure>
                    <div class="caption">
                        <h3>${curImage.children[0].data}</h3>
                        <p>${curImage.children[1].data}</p>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>