<#setting locale = locale />

<!-- Slider d'images -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<div class="mns-great-event-slider-full mns-p50">
    <div class="owl-carousel owl-theme" id="owl-full">
        <#if image?has_content && image.getSiblings()?has_content>
            <#list image.getSiblings() as curImage>
                <div class="item">
                    <figure>
                        <img src="${curImage.data}" alt="${curImage.children[0].data}" width="1600" height="1000" />
                    </figure>
                    <div class="caption">
                        <h1>${curImage.children[0].data}</h1>
                        <h2>${curImage.children[1].data}</h2>
                        <p>${curImage.children[2].data[0..*110]}<#if (curImage.children[2].data?length > 110)>...</#if></p>
                        <a href="${curImage.children[3].data}">
                            <span class="basic-link"><@liferay_ui.message key="eu.read-next" /></span>
                        </a>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>