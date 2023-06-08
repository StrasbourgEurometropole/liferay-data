<!-- Liste de contenus webs -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<div class="seu-container">
    <div class="rte">
        <h2>
            ${portletHelper.getPortletTitle('list', renderRequest)}
        </h2>
    </div>
    <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
        <a href="${viewURL}"
            title="${title}"  
            class="seu-btn-square seu-bordered seu-core">
            <span class="seu-btn-text"> 
                <#if title?has_content>
                    ${title}
                <#else>
                    ${curEntry.getTitle(locale)}
                </#if>
            </span>
            <span class="seu-btn-arrow"></span>
        </a>
        <#sep><br></#sep>
    </#list>
</div>