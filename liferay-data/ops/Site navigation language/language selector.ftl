<#if entries?has_content>
 	<#list entries as entry>
 		<#if !entry.isDisabled()>
 			<@liferay_aui["a"]
 				cssClass="ops-language-entry-short-text"
 				href=entry.getURL()
 				label=entry.getShortDisplayName()?upper_case
 				lang=entry.getW3cLanguageId()
 			/>
 		</#if>
 	</#list>
 </#if>