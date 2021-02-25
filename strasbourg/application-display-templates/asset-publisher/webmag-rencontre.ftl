<!-- Webmag - Rencontre/Portrait -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>

<ul class="unstyled hp-rencontres__list">
    <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
        <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
        <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
        <#assign imageURL ="" />
        <#if image?has_content>
            <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
        </#if>
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
        <#assign id = curEntry.getAssetRenderer().getArticle().getArticleId() />
        <li class="hp-rencontres__card">
            <a href="${viewURL}">
                <div class="hp-rencontres__card-picture" style="background-image: url(${imageURL});"></div>
            </a>
            <div class="hp-rencontres__card-text">
                <a href="${viewURL}">
                    <h3 class="hp-rencontres__card-title" data-dot="3">${title}</h3>
                    <div class="hp-rencontres__card-lead" data-dot="3">${chapo}</div>
                </a>
                <a href="${homeURL}lactu-portraits" class="a-btn-main h-inverted icon-right transparent-dark unstyled hp-rencontres__card-more">
                    <span class="flexbox"><i class="mag mag-arrow-right"></i>
                        <span class="btn-text">Tous les portraits</span>
                    </span>
                </a>
            </div>
        </li>
    </#list>
</ul>