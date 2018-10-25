<#setting locale = locale />
<div class="container mns-content-list-actu">
    <div class="row" data-egalize=".mns-bloc-actu > a">
        <#list entries as curEntry>
            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
            <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
            <#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
            <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
            <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
            <div class="col-sm-6">
                <div class="mns-bloc-actu">
                    <a href="${viewURL}" style="min-height: 673px;" >
                        <figure class="mns-bloc-top-img">
                            <img src="${image}" alt="${title}" width="570" height="380" />
                        </figure>
                        <div class="mns-bloc-content-actu">
                            <h3>${title}</h3>
                            <p>${text?replace("<[^>]*>", "", "r")[0..*250]}...</p>
                            <span class="link"><@liferay_ui.message key="eu.discover" /></span>
                        </div>
                    </a>
                </div>
            </div>
        </#list>
    </div>
</div>