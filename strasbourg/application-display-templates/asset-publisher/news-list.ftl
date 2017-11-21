<!-- Liste d'actualités -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<div class="seu-container">
    <div class="seu-wi seu-wi-agenda seu-type--actu">
        <div class="seu-container">
            <h2 class="seu-section-title">
                <span class="seu-title">${portletHelper.getPortletTitle('eu.news', renderRequest)}</span>
            </h2>
            <div class="seu-wi-content">
                <div class="seu-wi-grid">
                    <#list entries as curEntry>
                        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                        <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                        <#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
                        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                        <div class="seu-wi-item seu-actu seu-has-picture">
                            <a href="${viewURL}" class="seu-link" title="${title}">
                                <div class="seu-text">
                                    <div class="seu-title dotme" data-dot="3" style="word-wrap: break-word;">${title}</div>
                                    <div class="seu-lead dotme" data-dot="3" style="word-wrap: break-word;">${chapo}</div>
                                </div>
                                <div>
                                    <div class="seu-picture" style="background-image: url(${thumbnail})"></div>
                                </div>
                            </a>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="seu-btn-line">
                <a href="${homeURL}actualite" class="seu-btn-square seu-bordered seu-core" title="<@liferay_ui.message key="eu.all-news" />">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text"><@liferay_ui.message key="eu.all-news" /></span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </a>
            </div>
        </div>
    </div>
</div>