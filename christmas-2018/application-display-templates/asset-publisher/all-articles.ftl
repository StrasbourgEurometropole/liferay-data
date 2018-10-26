<#setting locale = locale />

<!-- Liste articles -->
<div class="container mns-triple-actu-bloc">
    <#if entries?has_content>
        <div class="row" data-egalize=".mns-bloc-actu > a">
            <#list entries as curEntry>
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                <#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
                <#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                <div class="col-sm-4">
                    <div class="mns-bloc-actu">
                        <a href="${viewURL}" style="min-height: 673px;" >
                            <figure class="mns-bloc-top-img">
                                <#if image?has_content>
                                    <img src="${image}" alt="${title}" width="370" height="250" />
                                </#if>
                            </figure>
                            <div class="mns-bloc-content-actu">
                                <h4>${title}</h4>
                                <p>${text?replace("<[^>]*>", "", "r")[0..*250]}...</p>
                                <span class="link"><@liferay_ui.message key="read-more" /></span>
                            </div>
                        </a>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</div>