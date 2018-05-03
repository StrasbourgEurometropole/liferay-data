<#if report.globalErrorCause?has_content>
	L'import du fichier "${report.filename!"no_filename"}" du prestataire "${report.provider!"no_provider"}" n'a pas pu être fait pour la raison suivante :
	
	${report.globalErrorCause}
<#else>
	L'import du fichier "${report.filename!"no_filename"}" du prestataire "${report.provider!"no_provider"}" a été réalisé avec succès le ${report.endDate?date} à ${report.endDate?time}.
	
	${report.errorEventsCount} événement<#if (report.errorEventsCount > 1)>s</#if> en erreur
	
	<#list report.errorEventsLines as event>
		- ${event.entityExternalId} - ${event.entityName} : ${event.log}
	</#list>
		
	${report.errorManifestationsCount} manifestation<#if (report.errorManifestationsCount > 1)>s</#if> en erreur
	
	<#list report.errorManifestationsLines as manif>
		- ${manif.entityExternalId} - ${manif.entityName} : ${manif.log}
	</#list>
	
	${report.newEventsCount} événement<#if (report.newEventsCount > 1)>s</#if> créé<#if (report.newEventsCount > 1)>s</#if>
	
	<#list report.newEventsLines as event>
		- ${event.entityExternalId} - ${event.entityName}
	</#list>
	
	${report.modifiedEventsCount} événement<#if (report.modifiedEventsCount > 1)>s</#if> modifié<#if (report.modifiedEventsCount > 1)>s</#if>
	
	<#list report.modifiedEventsLines as event>
		- ${event.entityExternalId} - ${event.entityName}
	</#list>

	${report.newManifestationsCount} manifestation<#if (report.newManifestationsCount > 1)>s</#if> créée<#if (report.newManifestationsCount > 1)>s</#if>
	
	<#list report.newManifestationsLines as manif>
		- ${manif.entityExternalId} - ${manif.entityName}
	</#list>
	
	${report.modifiedManifestationsCount} manifestation<#if (report.modifiedManifestationsCount > 1)>s</#if> modifiée<#if (report.modifiedManifestationsCount > 1)>s</#if>
	
	<#list report.modifiedManifestationsLines as manif>
		- ${manif.entityExternalId} - ${manif.entityName}
	</#list>
</#if>
