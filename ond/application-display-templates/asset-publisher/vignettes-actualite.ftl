<!-- Toutes les actualités -->
<#setting locale = locale />
<#setting date_format="dd/MM/yyyy">
<#if entries?has_content>
    <div class="all-news">
        <h1 class="all-news-title">
            <@liferay_ui["message"] key="eu.news" />
        </h1>
        <div>
            <#list entries as curEntry>
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                <#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
                <#assign illustration = docXml.valueOf("//dynamic-element[@name='illustration']/dynamic-content/text()") />
                <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
                <#assign imageURL ="" />
                <#if illustration?has_content>
                    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(illustration) />
                </#if>
                <#assign publishDate = curEntry.getPublishDate() />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                
                <article style="padding:0px;" class="new">
                    <div class="entry-header">
                        <a href="${viewURL}" title="${title}">
                            <img src="${imageURL}">
                        </a>
                        <h2 style="text-align: left;">
                            <a style="text-transform: none;" href="${viewURL}" title="${title}">${title}</a>
                        </h2>
                    </div>
                    <div class="entry-content">
                        <a href="${viewURL}" >${catcher}</a>
                    </div>
                    <footer class="entry-meta">
                    <time>
                    ${publishDate?date}
                    </time>
                    <a href="${viewURL}" title="<@liferay_ui.message key='learn-more' />" class="btn-more"><@liferay_ui.message key='learn-more' /></a>
                    <div class="clearfix"></div>
                    </footer>
                </article>
            </#list>
           <script>
            /*<![CDATA[*/
            $(function(){
                $(".entry-header h2 a").each(
                    function(){
                        $(this).text(add3Dots($(this).text(),65));
                    }
                );
                $(".entry-content a").each(
                    function(){
                        $(this).text(add3Dots($(this).text(), 85));
                    }
                );
            });
            /*]]>*/</script>
            <div class="clearfix"></div>
            <a href="/web/ond/actualites" class="btn-news" title="<@liferay_ui.message key='eu.all-news' />"><@liferay_ui.message key='eu.all-news' /></a>
            
        </div>
    </div>
</#if>