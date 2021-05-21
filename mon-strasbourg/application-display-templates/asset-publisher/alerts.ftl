<#if entries?has_content>
    <section id="alert-slider">
        <#assign PortletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
        <h2>${PortletHelper.getPortletTitle('Alerte', renderRequest)}</h2>
        <ul class="unstyled alert-list">
            <#list entries as curEntry>
                <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
                <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                <#assign url = docXml.valueOf("//dynamic-element[@name='URL']/dynamic-content/text()") />
                <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
                <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
                <li class="alert-item">
                    <#if url=="">
                        <a href="${viewURL}">
                    </#if>
                    <#if url!="">
                        <a href="${url}" target="_blank">
                    </#if>
                        <div class="alert-title">${title}</div>
                        <div class="alert-lead">${chapo}</div>
                    </a>
                </li>
            </#list>
        </ul>
    </section>  
</#if>

<style>
    @media only screen and (max-width: 767px){
        #alert-slider .alert-lead {
            display:none;
        }
    }
</style>