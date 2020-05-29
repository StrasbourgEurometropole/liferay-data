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
                <#assign text = text?replace("<[^>]*>", "", "r")[0..*150] />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                <div class="col-sm-4">
                    <div class="mns-bloc-actu">
                        <a href="${viewURL}" style="min-height: 673px;" >
                            <div class="mns-bloc-content-actu">
                                <h4>${title}</h4>
                                <p>${text?keep_before_last(" ")}...</p>
                                <span class="link"><@liferay_ui.message key="read-more" /></span>
                            </div>
                        </a>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</div>