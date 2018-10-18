<p>
	Un nouvel enregistrement sur le formulaire "${formName}" a été réalisé le ${date} à ${time}. Voici le récapitulatif des données saisies : 
</p>
<p><strong>Nom</strong> : ${lastName}</p>
<p><strong>Prénom</strong> : ${firstName}</p>
<p><strong>Mail</strong> : ${emailFrom}</p>
<#if phone?has_content>
	<p><strong>Téléphone</strong> : ${phone}</p>
</#if>
<#if object?has_content>
	<p><strong>Objet</strong> : ${object}</p>
</#if>
<p><strong>Message</strong> :</p>
<p>${content}</p>