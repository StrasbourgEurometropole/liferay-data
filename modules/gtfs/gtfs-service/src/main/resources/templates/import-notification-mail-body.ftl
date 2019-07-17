<body style="margin:30px">

	<#if importHistoric.result == 0>
		<p>L'import du flux GTFS lancé à ${importHistoric.startDate?time} n'a pas pu être fait pour la raison suivante :</p>
		
		<i style="margin-left:30px">${importHistoric.errorDescription}</i>
		
		<p>Voici l'erreur associé à ce problème :</p>
		
		<i style="margin-left:30px">${importHistoric.errorStackTrace}</i>
		
		<p>Voici les opérations qui ont été éffectuées avant d'être annulées :</p>
	<#else>
		<p>L'import du flux GTFS lancé à ${importHistoric.startDate?time} a été effectué avec succès en se terminant à ${importHistoric.finishDate?time}</p>
		
		<p>Voici les opérations qui ont été éffectuées :</p>
	</#if>
	
	<div style="margin-left:30px">
		<i>${importHistoric.operations}</i>
	</div>
	
</body>