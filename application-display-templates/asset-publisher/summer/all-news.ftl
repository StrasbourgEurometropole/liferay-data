<!-- Toutes les actualités -->
<#setting locale = locale />
<#setting date_format="dd/MM/yyyy">
<#if entries?has_content>
    <section class="news">
        <div class="list-news">
            <h1 class="title">
                <@liferay_ui["message"] key="category.news" />
            </h1>    
            <#list entries as curEntry>
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                <#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
                <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
                <#assign publishDate = curEntry.getPublishDate() />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                <article style="padding:0px;" class="new"> 
                    <div class="entry-header"> 
                        <a href="${viewURL}" title="${title}">
                            <img src="${image}">
                        </a> 
                        <h2 style="text-align: left;">
                            <a style="text-transform: none;" href="${viewURL}" title="${title}">
                                ${title}
                            </a>
                        </h2>
                    </div>
                    <div class="entry-content">
                        <a href="${viewURL}" title="">
                            ${catcher}
                        </a>
                    </div>
                    <footer class="entry-meta">
                        <time>${publishDate?date}</time>
                        <a href="${viewURL}" title="<@liferay_ui["message"] key="learn-more" />" class="btn-more">
                            <@liferay_ui["message"] key="learn-more" />
                        </a>
                        <div class="clearfix">
                        </div>
                    </footer>
                </article>
            </#list>   
            <div class="clearfix"></div>
            <a href="/web/ete/actualites" class="btn-news" title="Toutes les actualités"><@liferay_ui["message"] key="eu.all-news" /></a>
        </div>
    </section>
</#if>
