<!-- Webmag - Liste portrait -->
<#setting locale = locale />

<!-- Grille portrait -->
<ul class="portrait__list gridy gridy-d-2-20 gridy-t-2-20">
    <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
        <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
        <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
        <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
        <#assign imageURL ="" />
        <#if image?has_content>
            <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
        </#if>
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
        <#assign id = curEntry.getAssetRenderer().getArticle().getArticleId() />
        <li class="portrait__item gridy__item">
            <div class="portrait__card ${(curEntry?index < 4)?then('highlighted', '')}">
                <div class="portrait__card-picture" style="background-image: url(${imageURL});"></div>
                <div class="portrait__card-text">
                    <h3 class="portrait__card-title" data-dot="3" style="overflow-wrap: break-word;">${title}</h3>
                    <div class="portrait__card-lead" data-dot="3" style="overflow-wrap: break-word;">${chapo}</div>
                    <a href="${viewURL}" class="a-btn-main h-inverted icon-right transparent-dark unstyled portrait__card-link">
                        <span class="flexbox"><i class="mag mag-arrow-right"></i>
                                <span class="btn-text">En savoir plus</span>
                        </span>
                    </a>
                </div>
            </div>
        </li>
        <#if (curEntry?index == 3)>
            </ul>
            <ul class="portrait__list gridy gridy-d-3-20 gridy-t-2-20">
        </#if>
    </#list>
</ul>