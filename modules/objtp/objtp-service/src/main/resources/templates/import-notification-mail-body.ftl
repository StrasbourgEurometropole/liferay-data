<#if report.globalErrorCauseObjectCategory?has_content>
	L'import des catégories d'objets trouvés n'a pas pu être fait pour la raison suivante :
	
	${report.globalErrorCauseObjectCategory}
</#if>
<#if report.globalErrorCauseFoundObject?has_content>
	L'import des objets trouvés n'a pas pu être fait pour la raison suivante :
	
	${report.globalErrorCauseFoundObject}
</#if>
<#if !report.globalErrorCauseObjectCategory?has_content>

	L'import des catégories d'objets trouvés a été réalisé avec succès le ${report.endDate?date} à ${report.endDate?time}.

	${report.errorObjectCategoryCount} catégorie<#if (report.errorObjectCategoryCount > 1)>s</#if> d'objets trouvés en erreur

<#list report.reportLinesObjectCategory as category>
		- ${category.number} : ${category.logs}
</#list>

	${report.totalObjectCategoryCount - report.errorObjectCategoryCount} catégorie<#if ((report.totalObjectCategoryCount - report.errorObjectCategoryCount) > 1)>s</#if> d'objets trouvés créée<#if ((report.totalObjectCategoryCount - report.errorObjectCategoryCount)  > 1)>s</#if>

</#if>

<#if !report.globalErrorCauseFoundObject?has_content>

	L'import des objets trouvés a été réalisé avec succès le ${report.endDate?date} à ${report.endDate?time}.

	${report.errorFoundObjectCount} objet<#if (report.errorFoundObjectCount > 1)>s</#if> trouvés en erreur

<#list report.reportLinesFoundObject as object>
		- ${object.number} : ${object.logs}
</#list>

	${report.foundObjectNoImageCount} objet<#if (report.foundObjectNoImageCount > 1)>s</#if> sans image

<#list report.reportLinesNoImage as object>
		- ${object.number} : ${object.logs}
</#list>

	${report.totalFoundObjectCount - report.errorFoundObjectCount} objet<#if ((report.totalFoundObjectCount - report.errorFoundObjectCount) > 1)>s</#if> trouvés créé<#if ((report.totalFoundObjectCount - report.errorFoundObjectCount)  > 1)>s</#if>

</#if>