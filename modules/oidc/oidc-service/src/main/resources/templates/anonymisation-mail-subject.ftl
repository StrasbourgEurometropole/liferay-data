<#if anonymisationHistoric.result == 1>
	<#assign result = 'Succès' />
<#else>
	<#assign result = 'Erreur' />
</#if>

<#if anonymisationHistoric.userName?has_content >
	<#assign anonymisation = 'anonymisation manuel : ' + anonymisationHistoric.userName />
<#else>
	<#assign anonymisation = 'anonymisation automatique' />
</#if>

[${environment}] Journal d'anonymisation - ${anonymisation} - ${result}