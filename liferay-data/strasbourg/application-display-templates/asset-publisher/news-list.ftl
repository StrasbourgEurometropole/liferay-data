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
                        <#if curEntry?has_content && curEntry.getAssetRenderer()?has_content && curEntry.getAssetRenderer().getArticle()?has_content>
                            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                            <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                            <#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
                            <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                            <#assign imageURL ="" />
                            <#if thumbnail?has_content>
                                <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(thumbnail) />
                            </#if>
                            <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                            <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                            <#assign id = curEntry.getAssetRenderer().getArticle().getArticleId() />
                            <#if curEntry.tagNames?seq_contains("euromag") || curEntry.tagNames?seq_contains("villemag") || curEntry.tagNames?seq_contains("webmag")>
                                <div class="seu-wi-item seu-actu seu-mag seu-has-picture">
                            <#else>
                                <div class="seu-wi-item seu-actu seu-has-picture">
                            </#if>
                                <a href="${viewURL}" class="seu-link" title="${title}" style="background-color:white">
                                    <#if curEntry.tagNames?seq_contains("euromag") || curEntry.tagNames?seq_contains("villemag") || curEntry.tagNames?seq_contains("webmag")>
                                        <div class="seu-picture" style="background-image: url(${imageURL})">
                                        </div>
                                        <div class="seu-mag-logo">
                                            <div class="seu-mag-text">Mag'</div>
                                            <div class="seu-mag-picto"></div>
                                        </div>
                                    </#if>
                                    <div class="seu-text">
                                        <div class="seu-title dotme" data-dot="3" style="word-wrap: break-word;">${title}</div>
                                        <div class="seu-lead dotme" data-dot="3" style="word-wrap: break-word;">${chapo}</div>
                                    </div>
                                    <#if !(curEntry.tagNames?seq_contains("euromag") || curEntry.tagNames?seq_contains("villemag") || curEntry.tagNames?seq_contains("webmag"))>
                                        <div>
                                            <div class="seu-picture" style="background-image: url(${imageURL})"></div>
                                        </div>
                                    </#if>
                                </a>
                                 <a href="#" class="seu-add-favorites" 
                                     data-type="6" 
                                     data-title="${title}"
                                     data-url="${viewURL}"
                                     data-group-id=${themeDisplay.scopeGroupId} 
                                     data-id="${id}">
                                    <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
                                </a>
                            </div>
                        </#if>
                    </#list>
                </div>
            </div>
            <div class="seu-media-bottom">
                <ul class="seu-pagination unstyled">
                    <li class="seu-pagin-prev disabled seu-pagin-item">
                        <button class="seu-btn-square seu-bordered seu-core" data-action="prev" title="<@liferay_ui.message key="next" />">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text"><@liferay_ui.message key="previous" /></span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </button>
                    </li>
                    <li class="seu-is-active seu-pagin-item">
                        <button data-page="1" title="<@liferay_ui.message key="eu.go-to-page" /> 1">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text">1</span>
                            </span>
                        </button>
                    </li>
                    <li class="seu-pagin-next seu-pagin-item">
                        <button class="seu-btn-square seu-bordered seu-core" title="<@liferay_ui.message key="next" />" data-action="next">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text"><@liferay_ui.message key="next" /></span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </button>
                    </li>
                </ul>
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