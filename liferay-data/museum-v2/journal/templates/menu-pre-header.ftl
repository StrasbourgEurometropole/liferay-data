<ul class="accessibility-menu" role="navigation">
	<#if label.getSiblings()?has_content>
    	<#list label.getSiblings() as cur_label>
    	    <li class="access-item">
                <#if cur_label.link.activeTab?has_content && cur_label.link.activeTab.getData()?has_content >
    	            <a aria-label="${cur_label.getData()?html}" href="${cur_label.link.getData()}" title="${cur_label.getData()?html}"  target="_self">    
                <#else>
                    <a href="${cur_label.link.getData()}" aria-label="${cur_label.getData()?html}" title="${cur_label.getData()?html} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
                </#if>
        		    ${cur_label.getData()}
                </a>
            </li>
    	</#list>
    </#if>
	<li class="access-item language">
    	<div class="language-menu">
			<@liferay_portlet["runtime"]
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
				instanceId="langues"
				settingsScope="group" />
    	</div>
	</li>
</ul>