<#setting locale = locale />

<#if linkLabel.getSiblings()?has_content>
    <ul class="th-predef-tags">
        <#list linkLabel.getSiblings() as cur_linkLabel>
            <li>
                <#if cur_linkLabel.getChildren()[0].getFriendlyUrl()?has_content>
                    <a href="${cur_linkLabel.getChildren()[0].getFriendlyUrl()}" title="${cur_linkLabel.getData()}">        
                <#else>
                    <#if cur_linkLabel.getChildren()[1].getChildren()[0]?has_content && cur_linkLabel.getChildren()[1].getChildren()[0].getData()?has_content >
                        <a href="${cur_linkLabel.getChildren()[1].getData()}" title="${cur_linkLabel.getData()}" >     
                    <#else>
                        <a href="${cur_linkLabel.getChildren()[1].getData()}" title="${cur_linkLabel.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                    </#if>
                </#if>
                    ${cur_linkLabel.getData()}
                </a> 
            </li>
        </#list>
    </ul>
</#if>