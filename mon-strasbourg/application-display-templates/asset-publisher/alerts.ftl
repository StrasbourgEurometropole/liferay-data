<#if entries?has_content>
    <section id="alert-slider">
        <h2>Alerte</h2>
        <ul class="unstyled alert-list">
            <#list entries as curEntry>
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
    
                <li class="alert-item">
                    <a href="${viewURL}">
                        <div class="alert-title">${title}</div>
                        <div class="alert-lead">${chapo}</div>
                    </a>
                </li>
            </#list>
        </ul>
    </section>  
</#if>