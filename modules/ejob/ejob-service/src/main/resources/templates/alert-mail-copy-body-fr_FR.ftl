<#if headerImage?has_content>
	<img src="${headerImage}">
</#if>



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

<p>
	Cordialement,<br><br>
	La Ville et l'Eurométropole de Strasbourg<br>
	
	<a href="http://www.strasbourg.eu">www.strasbourg.eu</a>
</p>

<#if footerImage?has_content>
	<img src="${footerImage}">
</#if>
