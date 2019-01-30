<!-- Webmag - Rencontre/Portrait -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<ul class="unstyled hp-rencontres__list">
    <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
        <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
        <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
        <#assign id = curEntry.getAssetRenderer().getArticle().getArticleId() />
        <li class="hp-rencontres__card">
            <div class="hp-rencontres__card-picture" style="background-image: url(${image});"></div>
            <div class="hp-rencontres__card-text">
                <h3 class="hp-rencontres__card-title" data-dot="3">${title}</h3>
                <div class="hp-rencontres__card-lead" data-dot="3">${chapo}</div>
                <a href="${viewURL}" class="a-btn-main h-inverted icon-right transparent-dark unstyled hp-rencontres__card-link">
                    <span class="flexbox"><i class="mag mag-arrow-right"></i>
                        <span class="btn-text">En savoir plus</span>
                    </span>
                </a>
                <a href="${homeURL}webmag-portraits" class="a-btn-main h-inverted icon-right transparent-dark unstyled hp-rencontres__card-more">
                    <span class="flexbox"><i class="mag mag-arrow-right"></i>
                        <span class="btn-text">Tous les portraits</span>
                    </span>
                </a>
            </div>
        </li>
    </#list>
</ul>