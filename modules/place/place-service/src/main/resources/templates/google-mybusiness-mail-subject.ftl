<#if googleMyBusinessHistoric.result == 1>
	<#assign result = 'Succès' />
<#else>
	<#assign result = 'Erreur' />
</#if>

<#if googleMyBusinessHistoric.userName?has_content >
	<#assign importer = 'import manuel : ' + googleMyBusinessHistoric.userName />
<#else>
	<#assign importer = 'import automatique' />
</#if>

[${environment}] Journal de synchronisation - ${importer} - ${result}