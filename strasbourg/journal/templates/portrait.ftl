<#setting locale = locale />

<#--
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
        <#if panoramique.getData()?has_content>
            <#assign imageUrl = themeDisplay.getPortalURL() + panoramique.getData() />
        </#if>
    </#if>
    <#if imageUrl?has_content>
        <#assign imageUrl = imageUrl?replace('https:','http:') />
        <#assign AssetPublisherTemplateHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService") />
        <#assign taille = AssetPublisherTemplateHelper.getImageWidthHeight(imageUrl) />
        <meta property="og:image" content="${imageUrl}"/>
        <meta property="og:image:width" content="${taille?keep_before(',')}"/>
        <meta property="og:image:height" content="${taille?keep_after(',')}"/>
    </#if>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${chapo.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <#assign imageUrl = 'http://www.touch-as-strasbourg.com/media/uploaded/sites/10468/partenaire/57a9e032cf93f_eurometropole.png' />
    <meta property="og:image:width" content="500"/>
    <meta property="og:image:height" content="300"/>
</@>
-->

<main class="smag" style="padding-top: 105px !important;">
    <div class="smag-container">
        <div class="detail-line" style="justify-content: normal">
            <div class="filler"></div>
            <p class="seu-published">
            <@liferay_ui.message key="eu.published-on" /> ${.vars['reserved-article-display-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")} 
            - <@liferay_ui.message key="eu.modified-on" /> ${.vars['reserved-article-modified-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")}
            </p>
        </div>
        <h1 style="width: 100%">
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
    </div>
</main>
<#if panoramique.getData()?has_content>
    <style>
        .bg-banner {
            background-image: url(${panoramique.getData()}) !important;
        }
    </style>
</#if>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
</style>