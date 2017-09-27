<#setting locale = locale />

<!-- Slider d'images -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<div class="mns-slider-full mns-p50">
    <div class="owl-carousel owl-theme" id="owl-full">
        <#if images?has_content && images.getSiblings()?has_content>
            <#list images.getSiblings() as image>
                <div class="item">
                    <figure>
                        <img src="${image.data}" alt="${image.children[0].data}" width="1600" height="1000" />
                    </figure>
                    <div class="caption">
                        <h3>${image.children[0].data}</h3>
                        <p>${image.children[1].data}</p>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>
