<#if importHistoric.result == 1>
	<#assign result = 'Succès' />
<#else>
	<#assign result = 'Erreur' />
</#if>

<#if importHistoric.userName?has_content && importHistoric.userName != "Serveur">
	<#assign importer = 'import manuel : ' + importHistoric.userName />
<#else>
	<#assign importer = 'import automatique' />
</#if>

[${environment}] Journal d'import CTS - ${importer} - ${result}