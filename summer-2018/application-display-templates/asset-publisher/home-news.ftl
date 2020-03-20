<!-- Actualités (accueil) -->
<#setting locale = locale />
<#setting date_format="d MMMM yyyy">
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>
<div class="container mns-section-actualites">
    <div class="col-xs-12" style="text-align: center;">
        <div  class="mns-portlet-title">
            <h1><@liferay_ui.message key="eu.news" /></h1>
        </div>
    </div>
    <div class="row" data-egalize=".mns-bloc-actu > a">
        <#if entries?has_content>
            <#list entries as curEntry>
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                <#assign imageURL ="" />
                <#if image?has_content>
                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                </#if>
                <#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
                <#assign publishDate = curEntry.getPublishDate() />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                <div class="col-sm-4">
                    <article class="mns-bloc-actu">
                        <a href="${viewURL}">
                            <figure>
                                <img src="${imageURL}" alt="${title}" width="450" height="300" />
                            </figure>
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
        <div class="col-xs-12 mns-center">
            <span><a href="${homeURL}news" class="link align-center"><@liferay_ui.message key="eu.all-news" /></a></span>
        </div>
    </div>
</div>