<#setting locale = locale />
<div class="small-container mns-triple-actu-bloc p-30" style="padding-bottom: 5px !important">
    <div class="row" data-egalize=".mns-bloc-actu > a" style="margin: 0px !important">
        <#list entries as curEntry>
            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
            <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
            <#assign publishDate = curEntry.getPublishDate() />
            <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
            <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
            <div class="col-sm-4">
                <div class="mns-bloc-actu">
                    <a href="${viewURL}" style="min-height: 201px;">
                        <figure class="mns-bloc-top-img">
                            <img src="${image}" alt="${title}" width="370" height="250">
                        </figure>
                    </a>
                </div>
            </div>
        </#list>
    </div>
</div>