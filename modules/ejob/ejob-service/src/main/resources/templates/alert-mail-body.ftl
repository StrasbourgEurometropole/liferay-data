<<<<<<< Updated upstream
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
=======
<p>Bonjour,<br><br>

De nouvelles annonces sont disponibles pour l'alerte "${subject}". Vous trouverez ci-dessous la liste des informations saisies.</p>
<br>
<#list content as entry>
    <#if entry?has_content>
        <#assign offer = entry.assetRenderer.offer />

        <div class="wi-search-result wi-search-offer">
            <div class="seu-result-right">
                <a class="seu-result-content">
                    <h2 class="seu-result-title">${offer.getPost()}</h2>
                    <div class="seu-result-catcher">${offer.offerDirection.getTitle(locale)}
                        <#if offer.offerService??>
                        / ${offer.offerService.getTitle(locale)}
                        </#if>
                    </div>
                    <#if offer.offerGrade??>
                        <div class="seu-result-category">${offer.offerGrade.getTitle(locale)}</div>
                    </#if>
                </a>
                <div class="seu-result-infos">
                    <div class="seu-result-infos-top">
                        <@liferay_ui.message key="eu.offer-limit-date" />
                    </div>
                    <div class="seu-result-infos-bottom">
                        ${offer.getLimitDate()?date}
                    </div>
                </div>
            </div>
        </div>
    </#if>
</#list>
>>>>>>> Stashed changes
