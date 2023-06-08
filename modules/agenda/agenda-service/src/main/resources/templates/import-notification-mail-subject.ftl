<#if report.status == 0>
	<#assign result = 'Erreur' />
<#elseif report.status == 1>
	<#assign result = 'Succès' />
<#else>
	<#assign result = 'Réussi avec des erreurs' />
</#if>
[${environment}] Journal d'import des événements - ${report.provider} - ${result}