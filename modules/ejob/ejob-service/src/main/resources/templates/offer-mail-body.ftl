<#if headerImage?has_content>
	<img src="${headerImage}">
</#if>

<p>Bonjour,<br><br>

<h2 class="seu-result-title">L’Eurométropole de Strasbourg est actuellement à la recherche de "
    <a class="seu-result-content" href="http://www.strasbourg.eu/offre/-/entity/id/${content.getOfferId()}">
      <#if content.getPost()?length &lt; 190>
         ${content.getPost()}
      <#else>
         ${content.getPost()?substring(0,189)} ...
      </#if>
    </a> ".
</h2>

Auriez-vous la possibilité de diffuser les offres d’emploi ci-jointes ?<br><br>

Merci de bien vouloir prendre note qu’aucune candidature ne sera acceptée par mail.<br><br>

Ne seront prises en compte que les candidatures de personne ayant postulé sur le site de l‘Eurométropole de Strasbourg.<br><br>

Veuillez agréer, Madame, Monsieur, l’expression de mes salutations les meilleures.</p>

<br>
<#if footerText?has_content>
	<p>${footerText}</p>
</#if>

<style>
    .seu-result-content {
        display: inline-block;
        text-decoration: none;
        flex-grow: 1;
    }
    .seu-result-content:hover, .seu-result-content:focus {
        text-decoration: underline;
    }
    .seu-result-title {
         font: 400 14px "MontSerrat", arial;
         text-transform: uppercase;
         color: #505050;
         margin-bottom: 25px;
         line-height: 1.2;
         position: relative;
    }

</style>
