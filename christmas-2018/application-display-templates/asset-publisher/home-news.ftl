<!-- Actualités (accueil) -->
<#setting locale = locale />
<#setting date_format="d MMMM yyyy">
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>
<div class="container mns-section-actualites">
    <div class="col-xs-12">
        <h2><@liferay_ui.message key="eu.the-news" /></h2>
    </div>
    <div class="small-container">
        <div class="row" data-egalize=".mns-bloc-actu > a">
            <#if entries?has_content>
                <#list entries as curEntry>
                    <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                    <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                    <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                    <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                    <#assign imageURL ="" />
                    <#if image?has_content>
                        <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                    </#if>
                    <#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
                    <#assign publishDate = curEntry.getPublishDate() />
                    <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                    <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                    <div class="col-sm-6">
                        <article class="mns-bloc-actu">
                            <a href="${viewURL}">
                                <img src="${imageURL}" alt="${title}" width="450" height="300" />
                                <div class="mns-bloc-content-actu">
                                    <span class="publication"><@liferay_ui.message key="eu.published-on" /> ${publishDate?date}</span>
                                    <h1>${title}</h1>
                                    <p>${text?replace("<[^>]*>", "", "r")[0..*100]}...</p>
                                    <span class="basic-link"><@liferay_ui.message key="eu.read-next" /></span>
                                </div>
                            </a>
                        </article>
                    </div>
                </#list>
            </#if>
            <div class="col-xs-12 mns-right">
                <span><a href="${homeURL}news" class="link align-right"><@liferay_ui.message key="eu.see-all-news" /></a></span>
            </div>
        </div>
    </div>
</div>