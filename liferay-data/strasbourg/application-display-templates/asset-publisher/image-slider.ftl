<!-- Slider images -->
<#setting locale = locale />

<#if entries?has_content>
    <#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
    <#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
    <div class="seu-container">
        <div class="rte">
            <h2>
                ${portletHelper.getPortletTitle('gallery_images', renderRequest)}
            </h2>
        </div>
        <div class="seu-slider-int-container">
            <div class="seu-slider-overflow">
                <div class="seu-slider">
                    <#list entries as curEntry>
                        <#assign file = curEntry.getAssetRenderer().getAssetObject() />
                        <#assign imageURL = fileEntryHelper.getFileEntryURL(file.getFileEntryId()) />
                        <#assign title = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                        <#assign legend = fileEntryHelper.getImageLegend(file.getFileEntryId(), locale) />
                        <#assign copyright = fileEntryHelper.getImageCopyright(file.getFileEntryId(), locale) />
                        <div class="seu-item" style="background-image: url(${imageURL});">
                            <div class="seu-text">
                                <#if title?has_content>
                                    <div class="seu-img-title" data-dot="1">${title}</div>
                                </#if>
                                <#if legend?has_content>
                                    <div class="seu-description" data-dot="2">${legend}</div>
                                </#if>
                                <#if copyright?has_content>
                                    <div class="seu-caption">© ${copyright}</div>
                                </#if>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="owl-nav">
                <button class="seu-owl-prev">
                    <span class="seu-picto"></span>
                </button>
                <button class="seu-owl-next">
                    <span class="seu-picto"></span>
                </button>
            </div>
        </div>
    </div>
</#if>