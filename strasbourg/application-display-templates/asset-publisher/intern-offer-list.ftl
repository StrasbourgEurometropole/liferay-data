<!-- Liste offre interne -->
<#setting locale = locale />

<#list entries as entry>
    <#if entry?has_content>
        <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
            <#assign homeURL = "/web${layout.group.friendlyURL}/" />
        <#else>
            <#assign homeURL = "/" />
        </#if>
        
        <#assign offer = entry.assetRenderer.offer />

        <#if offer.interneExterne=="Interne">
            <div class="wi-search-result wi-search-offer">
                <div class="seu-result-right">
                    <a class="seu-result-content" href="${homeURL}offre/-/entity/id/${offer.getOfferId()}">
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
    </#if>
</#list>