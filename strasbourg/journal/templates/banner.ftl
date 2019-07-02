<#setting locale = locale />

<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<#-- <@liferay_util["html-top"]>
    <meta property="og:title" content="Strasbourg.eu" />
    <meta property="og:description" content="Bienvenue sur le site de Strasbourg et de l'Eurométropole" />
    <meta property="og:url" content="${currentUrl}" />
    <#if backgroundImage.getData()?has_content>
        <#assign imageUrl = themeDisplay.getPortalURL() + backgroundImage.getData() />
    </#if>
    <#if !backgroundImage.getData()?has_content>
        <#assign imageUrl = themeDisplay.getPortalURL() + '/o/strasbourg-theme/images/test/banner.jpg' />
    </#if>
    <#if imageUrl?has_content>
        <#assign imageUrl = imageUrl?replace('https:','http:') />
        <#assign AssetPublisherTemplateHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService") />
        <#assign taille = AssetPublisherTemplateHelper.getImageWidthHeight(imageUrl) />
        <meta property="og:image" content="${imageUrl}"/>
        <meta property="og:image:width" content="${taille?keep_before(',')}"/>
        <meta property="og:image:height" content="${taille?keep_after(',')}"/>
    </#if>
    <meta property="og:title" content="Strasbourg.eu" />
    <meta property="og:description" content="Bienvenue sur le site de Strasbourg et de l'Eurométropole" />
    <meta property="og:url" content="${currentUrl}" />
    <#assign imageUrl = 'http://www.touch-as-strasbourg.com/media/uploaded/sites/10468/partenaire/57a9e032cf93f_eurometropole.png' />
    <meta property="og:image:width" content="500"/>
    <meta property="og:image:height" content="300"/>
</@>
-->

<div class="seu-quicklinks">
<#if linkLabel.getSiblings()?has_content>
    <#list linkLabel.getSiblings() as cur_linkLabel>
        <#if cur_linkLabel.getChildren()[0].getFriendlyUrl()?has_content>
            <a href="${cur_linkLabel.getChildren()[0].getFriendlyUrl()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()}">        
        <#else>
            <#if cur_linkLabel.getChildren()[1].getChildren()[0]?has_content && cur_linkLabel.getChildren()[1].getChildren()[0].getData()?has_content >
                <a href="${cur_linkLabel.getChildren()[1].getData()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()}" >     
            <#else>
                <a href="${cur_linkLabel.getChildren()[1].getData()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
            </#if>
        </#if>
        
          <span class="seu-flexbox">
            <span class="seu-btn-text">${cur_linkLabel.getData()}</span>
            <span class="seu-btn-arrow"></span>
          </span>
        </a> 
  </#list>
</#if>
</div>
<style>
    #seu-banner {
        background-image: url(${backgroundImage.getData()}) !important;
    }
</style>