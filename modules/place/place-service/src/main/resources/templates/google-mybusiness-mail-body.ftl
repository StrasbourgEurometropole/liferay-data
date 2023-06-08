<body style="margin:30px">

	<#if googleMyBusinessHistoric.result == 0>
		<p>La synchronisation lancée à ${googleMyBusinessHistoric.startDate?time} n'a pas pu être faite pour la raison suivante :</p>

		<i style="margin-left:30px">${googleMyBusinessHistoric.errorDescription}</i>

		<p>Voici l'erreur associée à ce problème :</p>

		<i style="margin-left:30px">${googleMyBusinessHistoric.errorStackTrace}</i>

		<p>Voici les opérations qui ont été effectuées avant d'être annulées :</p>
	<#elseif googleMyBusinessHistoric.result == 2>
		<p>La synchronisation lancée à ${googleMyBusinessHistoric.startDate?time} s'est terminé avec des erreurs à ${googleMyBusinessHistoric.finishDate?time}</p>

		<p>Voici les opérations qui ont été effectuées :</p>
	<#else>
		<p>La synchronisation lancée à ${googleMyBusinessHistoric.startDate?time} a été effectuée avec succès en se terminant à ${googleMyBusinessHistoric.finishDate?time}</p>

		<p>Voici les opérations qui ont été effectuées :</p>
	</#if>

	<div style="margin-left:30px">
		<i>${googleMyBusinessHistoric.operations}</i>
	</div>

</body>