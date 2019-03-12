<#if entries?has_content>
	<#list entries as curLanguage>
		${curLanguage.longDisplayName}
	</#list>
</#if>