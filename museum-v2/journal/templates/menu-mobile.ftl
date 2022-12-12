<#setting locale = locale />
<div class="menu">
    <#if linkLabel.getSiblings()?has_content>
        <#list linkLabel.getSiblings() as cur_linkLabel>
            <#if cur_linkLabel.linkURL.getFriendlyUrl()?has_content>
                <a href="${cur_linkLabel.linkURL.getFriendlyUrl()}" aria-label="${cur_linkLabel.getData()}" title="${cur_linkLabel.getData()}">   
            <#else>
                <#if cur_linkLabel.externalLink.activeTab?has_content && cur_linkLabel.externalLink.activeTab.getData()?has_content >
                    <a href="${cur_linkLabel.externalLink.getData()}" aria-label="${cur_linkLabel.getData()}" title="${cur_linkLabel.getData()}" >     
                <#else>
                    <a href="${cur_linkLabel.externalLink.getData()}" aria-label="${cur_linkLabel.getData()}" title="${cur_linkLabel.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                </#if>
            </#if>
                ${cur_linkLabel.SVG.getData()}
                ${cur_linkLabel.getData()}
            </a> 
      </#list>
    </#if>
</div>