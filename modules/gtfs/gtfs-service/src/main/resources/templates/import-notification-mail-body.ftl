<#if importHistoric.result == 0>
	L'import du flux GTFS lancé à ${importHistoric.startDate?time} n'a pas pu être fait pour la raison suivante :
	
	"<i>${importHistoric.errorDescription}"</i>
	
	Voici l'erreur associé à ce problème :
	
	"<i>${importHistoric.errorStackTrace}"</i>
	
	Voici les opérations qui ont été éffectuées avant d'être annulées :
<#else>
	L'import du flux GTFS lancé à ${importHistoric.startDate?time} a été effectué avec succès en se terminant à ${importHistoric.finishDate?time}
	
	Voici les opérations qui ont été éffectuées :
</#if>
	
"<i>${importHistoric.operations}"</i>