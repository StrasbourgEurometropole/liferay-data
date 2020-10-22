<#if headerImage?has_content>
    <img src="${headerImage} style="min-width: 500px; max-width:800px;">
    <br><br>
</#if>
<table border="0" cellpadding="10" cellspacing="0" style="min-width: 500px; max-width:800px;">
    <tbody>
        <tr>
            <td style="min-width: 500px; max-width:800px;">
                Bonjour,<br><br>
                <h2 style="line-height: 1.2; min-width: 500px; max-width:800px;" class="seu-result-title">
                    L’Eurométropole de Strasbourg est actuellement à la recherche de "
                    <a class="seu-result-content" href="${urlOffer}${content.getOfferId()}">
                      <font style="text-transform: uppercase; color: #505050;" >
                          <#if content.getPost(locale)?length &lt; 190>
                             ${content.getPost(locale)}
                          <#else>
                             ${content.getPost(locale)?substring(0,189)} ...
                          </#if>
                      </font>
                    </a> ".
                </h2><br><br>
                Auriez-vous la possibilité de diffuser les offres d’emploi ci-jointes ?<br><br>
                Merci de bien vouloir prendre note qu’aucune candidature ne sera acceptée par mail.<br><br>
                Ne seront prises en compte que les candidatures de personne ayant postulé sur le site de l‘Eurométropole de Strasbourg.<br><br>
                Veuillez agréer, Madame, Monsieur, l’expression de mes salutations les meilleures.
                <#if footerText?has_content>
                    <br><br><br><br>
                    ${footerText}
                </#if>
            </td>
        </tr>
    </tbody>
</table>

<style>
    .seu-result-title {
         font: 400 14px "MontSerrat", arial;
    }

</style>
