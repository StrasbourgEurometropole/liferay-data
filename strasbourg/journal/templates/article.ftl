<#setting locale = locale />
<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />
<#-- <@liferay_util["html-top"]>
    <meta property="og:title" content="${title.getData()?html}" />
    <meta property="og:description" content="${chapo.getData()?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${currentUrl}" />
    <#assign layout = themeDisplay.getLayout() />
    <#if layout.expandoBridge.getAttribute('image')?has_content>
        <#assign imageUrl = themeDisplay.getPortalURL() + layout.expandoBridge.getAttribute('image') />
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

<main class="seu-container" style="margin-bottom: 50px">
    <a href="#" class="add-favorites"
            data-type="7" 
            data-title="${title.getData()}" 
            data-url="${currentUrl}" 
            data-group-id=${themeDisplay.scopeGroupId}
            data-id="${.vars['reserved-article-id'].data}">
            <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <h1>${title.getData()}</h1>
    <div class="hat">
        <div>
            ${chapo.getData()}
        </div>
    </div>
    <div class="rte">
        ${content.getData()}
    </div>
</main>