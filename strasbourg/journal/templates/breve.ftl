<!-- Webmag - breve -->

<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${chapo.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <#if image.getData()?has_content>
        <#assign imageUrl = themeDisplay.getPortalURL() + image.getData() />
    </#if>
    <#if !image.getData()?has_content>
        <#assign layout = themeDisplay.getLayout() />
        <#if layout.expandoBridge.getAttribute('image')?has_content>
            <#assign imageUrl = themeDisplay.getPortalURL() + layout.expandoBridge.getAttribute('image') />
        </#if>
    </#if>
    <#if imageUrl?has_content>
        <#assign AssetPublisherTemplateHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService") />
        <#assign taille = AssetPublisherTemplateHelper.getImageWidthHeight(imageUrl) />
        <meta property="og:image" content="${imageUrl}"/>
        <meta property="og:image:width" content="${taille?keep_before(',')}"/>
        <meta property="og:image:height" content="${taille?keep_after(',')}"/>
    </#if>
</@>

<main class="seu-container" style="margin-bottom: 50px">
    <div class="detail-line">
        <div class="filler"></div>
        <p class="seu-published">
           <@liferay_ui.message key="eu.published-on" /> ${.vars['reserved-article-display-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")} 
           - <@liferay_ui.message key="eu.modified-on" /> ${.vars['reserved-article-modified-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")}
        </p>
    </div>
    <h1>
        ${title.getData()}
    </h1>
    <div class="hat">
        <div>
            ${chapo.getData()}
        </div>
    </div>
    <div class="rte">
        ${content.getData()}
    </div>
</main>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
</style>