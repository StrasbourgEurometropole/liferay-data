<!-- Toutes les actualités -->
<#setting locale = locale />
<#setting date_format="d MMMM yyyy">
<#if entries?has_content>
    <div class="all-news">
        <h1 class="all-news-title">
            <@liferay_ui["message"] key="eu.news.all-news" />
        </h1>
        <div>
            <#list entries as curEntry>
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                <#assign imageURL ="" />
                <#if image?has_content>
                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
                </#if>
                <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
                <#assign publishDate = curEntry.getPublishDate() />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                <div class="news"> 
                    <div class="news-image">
                        <a href="${viewURL}">
                            <img src="${imageURL}" >
                        </a>
                    </div>
                    <div class="news-info">
                        <div class="news-date">
                            <date><@liferay_ui["message"] key="eu.published-on" /> ${publishDate?date}</date>
                        </div>
                        <div class="news-title">
                            <h4><a href="${viewURL}">${title}</a></h4>
                        </div>
                        <div class="news-content">
                            ${chapo}
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</#if>
