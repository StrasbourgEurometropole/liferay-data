<#setting date_format = "dd/MM/yyyy">
<#setting locale = locale />
<div class="portlet-cus-galleries portlet-cus-image-fo">
    <div class="header">
        <h2 class="gallery-title">${title.getData()}</h2>
        <#if mainImage.getData()?? && mainImage.getData() != "">
            <div class="illustration">
              <img alt="${mainImage.getAttribute("alt")}" src="${mainImage.getData()}" />
            </div>
        </#if>
        <div class="general-informations" style="margin-bottom: 20px">
            <div class="gray published">
                <@liferay_ui.message key="eu.published-on" /> : <#setting locale = "fr_FR" />${.vars['reserved-article-display-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')}<#setting locale = locale />
            </div>
            <div class="gray modified">
                <@liferay_ui.message key="eu.modified-on" /> : <#setting locale = "fr_FR" /> ${.vars['reserved-article-modified-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')}<#setting locale = locale />
            </div>
            <div class="gray number">
                <@liferay_ui.message key="eu.image-count" /> : ${images.siblings?size}
            </div>
            <div class="description ck-editor-content">
                <div class="article-text">
                    ${content.getData()}
                </div>
            </div>
        </div>
    </div>
    <div class="clearfix" ></div>
    <div class="content agenda galeries">
        <div class="list-evt">
            <#if images.getSiblings()?has_content>
                <#assign index = 0 />
                <#list images.getSiblings() as image>
                    <#if image.getData()?? && image.getData() != "">
                        <#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
                        <#assign file = fileEntryHelper.getFileEntryByRelativeURL(image.getData()) />
                        <#assign copyright = fileEntryHelper.getImageCopyright(file.getFileEntryId(), locale) />
                        <#assign legend = fileEntryHelper.getImageLegend(file.getFileEntryId(), locale) />
                        <div class="event portlet-visuel-item">
                            <div class="entry-image">
                                <a class="scriptLink openImageLightboxGallery" href="#image${index}" title="${image.getAttribute("alt")}">
                                    <img alt="${image.getAttribute("alt")}" src="${image.getData()}" />
                                </a>
                            </div>
                            <div class="entry-header">
                                <h2>
                                    <a style="word-wrap:break-word;" class="scriptLink openImageLightboxGallery" href="#${index}">
                                        ${file.getTitle()}
                                    </a>
                                </h2>
                            </div>
                            <footer class="entry-meta" >
                                <time>${file.createDate?date}</time>
                                <a class="scriptLink openImageLightboxGallery btn-more" href="#${index}">
                                    <@liferay_ui.message key="eu.see-image" />
                                </a>
                            </footer>
                            <div id="image${index}"
                                class="mfp-hide imageLightboxGallery details portlet-cus-image-fo">
                                <div class="mfp-counter"></div>
                                <div class="image-informations">
                                    <h2 class="gallery-title">
                                        ${file.getTitle()}
                                    </h2>
                                    <img src="${image.getData()}"
                                        alt="${file.getTitle()}" />
                                    <div class="gray">
                                        <@liferay_ui.message key="eu.published-on" />
                                        &nbsp;${file.createDate?date}
                                        <#if copyright??>
                                              &copy; ${copyright}
                                        </#if>
                                    </div>
                                    <div class="description ck-editor-content">
                                        ${legend}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                    <#assign index = index + 1 />
              </#list>
            </#if>
        </div>
    </div>
</div>