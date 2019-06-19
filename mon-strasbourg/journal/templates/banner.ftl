<#setting locale = locale />

<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<div class="smag-quicklinks">
<#if linkLabel.getSiblings()?has_content>
    <#list linkLabel.getSiblings() as cur_linkLabel>
        <#if cur_linkLabel.getChildren()[0].getFriendlyUrl()?has_content>
                <a href="${cur_linkLabel.getChildren()[0].getFriendlyUrl()}" class="smag-quicklink btn-square filled second" title="${cur_linkLabel.getData()}">        
        <#else>
            <#if cur_linkLabel.getChildren()[1].getChildren()[0]?has_content && cur_linkLabel.getChildren()[1].getChildren()[0].getData()?has_content >
                <a href="${cur_linkLabel.getChildren()[1].getData()}" class="smag-quicklink btn-square filled second" title="${cur_linkLabel.getData()}" >     
            <#else>
                <a href="${cur_linkLabel.getChildren()[1].getData()}" class="smag-quicklink btn-square filled second" title="${cur_linkLabel.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
            </#if>
        </#if>
        
          <span class="flexbox">
            <span class="btn-text">${cur_linkLabel.getData()}</span>
            <span class="btn-arrow"></span>
          </span>
        </a> 
  </#list>
</#if>
</div>
<style>
    .bg-banner {
        background-image: url(${backgroundImage.getData()}) !important;
    }
</style>