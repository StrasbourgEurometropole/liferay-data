<body style="margin:30px">

	<#if googleMyBusinessHistoric.result == 0>
		<p>La synchronisation lancé à ${googleMyBusinessHistoric.startDate?time} n'a pas pu être faite pour la raison suivante :</p>

		<i style="margin-left:30px">${googleMyBusinessHistoric.errorDescription}</i>

		<p>Voici l'erreur associé à ce problème :</p>

		<i style="margin-left:30px">${googleMyBusinessHistoric.errorStackTrace}</i>

		<p>Voici les opérations qui ont été éffectuées avant d'être annulées :</p>
	<#else>
		<p>La synchronisation lancée à ${googleMyBusinessHistoric.startDate?time} a été effectuée avec succès en se terminant à ${googleMyBusinessHistoric.finishDate?time}</p>

		<p>Voici les opérations qui ont été éffectuées :</p>
	</#if>

	<div style="margin-left:30px">
		<i>${googleMyBusinessHistoric.operations}</i>
	</div>

</body>