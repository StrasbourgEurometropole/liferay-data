<#setting locale = locale />
<#setting date_format = "d MMMM yyyy">
<div class="container">
    <div class="row">
        <div class="small-container">
            <div class="row mns-p-wrapper-list-actu" data-egalize=".mns-bloc-actu > a">
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
                    <div class="col-sm-6 col-xs-12">
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
            </div>
        </div>
    </div>
</div>