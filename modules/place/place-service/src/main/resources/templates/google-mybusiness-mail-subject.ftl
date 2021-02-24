<#if googleMyBusinessHistoric.result == 1>
	<#assign result = 'Succès' />
<#else>
	<#assign result = 'Erreur' />
</#if>
<#if googleMyBusinessHistoric.userName?has_content && googleMyBusinessHistoric.userName != "Serveur">
	<#assign importer = 'import manuel : ' + googleMyBusinessHistoric.userName />
<#else>
	<#assign importer = 'import automatique' />
</#if>

[${environment}] Journal de synchro GMB - ${importer} - ${result}