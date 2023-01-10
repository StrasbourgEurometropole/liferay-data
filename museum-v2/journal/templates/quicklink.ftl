<#setting locale = locale />
<div class="quicklinks content container">
    <#if linkLabel.getSiblings()?has_content>
        <#list linkLabel.getSiblings() as cur_linkLabel>
            <#if cur_linkLabel.linkURL.getFriendlyUrl()?has_content>
                <a href="${cur_linkLabel.linkURL.getFriendlyUrl()}" class="button1" aria-label="${cur_linkLabel.getData()?html}" title="${cur_linkLabel.getData()?html}">   
            <#else>
                <#if cur_linkLabel.externalLink.activeTab?has_content && cur_linkLabel.externalLink.activeTab.getData()?has_content >
                    <a href="${cur_linkLabel.externalLink.getData()}" class="button1" aria-label="${cur_linkLabel.getData()?html}" title="${cur_linkLabel.getData()?html}" >     
                <#else>
                    <a href="${cur_linkLabel.externalLink.getData()}" class="button1" aria-label="${cur_linkLabel.getData()?html}" title="${cur_linkLabel.getData()?html} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                </#if>
            </#if>
                ${cur_linkLabel.getData()}
            </a> 
      </#list>
    </#if>
</div>