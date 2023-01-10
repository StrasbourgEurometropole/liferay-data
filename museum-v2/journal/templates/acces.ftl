<div class="access-by-public container" role="navigation">
	<#if label.getSiblings()?has_content>
    	<#list label.getSiblings() as cur_label>
            <#if cur_label.link.activeTab?has_content && cur_label.link.activeTab.getData()?has_content >
	            <a class="button1" aria-label="${cur_label.getData()}" href="${cur_label.link.getData()}" title="${cur_label.getData()}">  
            <#else>
                <a class="button1" href="${cur_label.link.getData()}" aria-label="${cur_label.getData()}" title="${cur_label.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
            </#if>
    		    ${cur_label.getData()}
                </a>
    	</#list>
    </#if>
</div>