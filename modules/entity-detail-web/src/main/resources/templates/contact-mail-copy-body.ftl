<#if headerImage?has_content>
	<img src="${headerImage}">
</#if>



<p>Bonjour,<br><br>

Vous venez de soumettre des informations sur le formulaire de contact<#if type == "Event"> de l’événement ${title}<#elseif type == "Place"> du lieu ${title}</#if>. Vous trouverez ci-dessous la liste des informations saisies.</p>
<br>
<p><strong>Nom</strong> : ${lastName}</p>
<p><strong>Prénom</strong> : ${firstName}</p>
<p><strong>Mail</strong> : ${emailFrom}</p>
<p><strong>Message</strong> :</p>
<p>${content}</p>

<p>Date et heure de soumission du formulaire : ${date} ${time}</p>

<p>Votre demande sera traitée dans les meilleurs délais.</p>

<p>
	Cordialement,<br><br>
	La Ville et l'Eurométropole de Strasbourg<br>
	
	<a href="http://www.strasbourg.eu">www.strasbourg.eu</a>
</p>

<#if footerImage?has_content>
	<img src="${footerImage}">
</#if>
