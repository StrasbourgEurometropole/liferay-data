<body style="margin:30px">

	<#if anonymisationHistoric.result == 0>
		<p>L'anonymisation lancée à ${anonymisationHistoric.startDate?time} n'a pas pu être faite pour la raison suivante :</p>

		<i style="margin-left:30px">${anonymisationHistoric.errorDescription}</i>

		<p>Voici l'erreur associée à ce problème :</p>

		<i style="margin-left:30px">${anonymisationHistoric.errorStackTrace}</i>

		<p>Voici les opérations qui ont été effectuées avant d'être annulées :</p>
	<#else>
		<p>L'anonymisation lancée à ${anonymisationHistoric.startDate?time} a été effectuée avec succès en se terminant à ${anonymisationHistoric.finishDate?time}</p>

		<p>Voici les opérations qui ont été effectuées :</p>
	</#if>

	<div style="margin-left:30px">
		<i>${anonymisationHistoric.operations}</i>
	</div>

</body>