<p>
	Un nouvel enregistrement sur le formulaire de contact<#if type == "Event"> de l’événement "${title}"<#elseif type == "Place"> du lieu ${title}</#if> a été réalisé le ${date} à ${time}. Voici le récapitulatif des données saisies : 
</p>
<p><strong>Nom</strong> : ${lastName}</p>
<p><strong>Prénom</strong> : ${firstName}</p>
<p><strong>Mail</strong> : ${emailFrom}</p>
<p><strong>Message</strong> :</p>
<p>${content}</p>