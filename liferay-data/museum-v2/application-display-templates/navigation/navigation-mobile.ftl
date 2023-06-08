<ul class="access-by-public container" role="navigation">
    <#if entries?has_content>
    	<#list entries as navigationEntry>
            <li>
                <a aria-label="${navigationEntry.getName()}" href="${navigationEntry.getURL()}" title="${navigationEntry.getName()}">
                    ${navigationEntry.getName()}
                </a>
            </li>
    	</#list>
    </#if>
</ul>