<#if headerImage?has_content>
	<img src="${headerImage}">
</#if>

<p>
	Madame, Monsieur,<br><br>
	
	Votre envoi sur le formulaire "${formName}" a bien été reçu, vous receverez une réponse dès que le service concerné aura traité votre demande.
	<br><br>
	Pour rappel, voici le message que vous nous avez envoyé :
	<br><br>
	<ul>
		<li style="font-style: italic;">${content}</li>
	</ul>
</p>

<#if footerImage?has_content>
	<img src="${headerImage}">
</#if>
