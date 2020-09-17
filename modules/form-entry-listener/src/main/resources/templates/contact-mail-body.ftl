<p>
	Bonjour,<br><br>
	Vous venez de soumettre des informations sur le formulaire "${formName}".<br>
	Vous trouverez ci-dessous la liste des informations saisies.
</p>
<#if content?has_content>
<p><strong>Données</strong> :</p>
<p>${content}</p>
</#if>
<p>
	Date et heure de soumission du formulaire : ${date} ${time}<br>
	Ces informations seront traitées dans les meilleurs délais.<br>
	Cordialement,<br>
	<br>
	La Ville et l'Eurométropole de Strasbourg<br>
	<a href = "http://www.strasbourg.eu/" target = "_blank">www.strasbourg.eu</a><br>
</p>