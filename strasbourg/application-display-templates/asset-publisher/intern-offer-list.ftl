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

        <#if offer.typePublication?? && offer.typePublication.getTitle(locale)=="Interne uniquement">
            <div class="wi-search-result wi-search-offer">
                <div class="seu-result-right">
                    <a class="seu-result-content" href="${homeURL}offre/-/entity/id/${offer.getOfferId()}">
                        <h2 class="seu-result-title">${offer.getPost(locale)}</h2>
                        <div class="seu-result-catcher">${offer.direction.getTitle(locale)}
                            <#if offer.service??>
                            / ${offer.service.getTitle(locale)}
                            </#if>
                        </div>
                        <#assign gradeRanges = offer.gradeRanges />
                        <#if gradeRanges??>
                            <div class="seu-result-grade">
                                <#list gradeRanges as gradeRange>
                                    ${gradeRange[2].getTitle(locale)}
                                    <#if gradeRange[3]??>
                                        <@liferay_ui.message key="eu.to" /> ${gradeRange[3].getTitle(locale)}<#sep>, </#sep>
                                    </#if>
                                </#list>
                            </div>
                        </#if>
                    </a>
                    <div class="seu-result-infos">
                        <div class="seu-result-infos-top">
                            <@liferay_ui.message key="eu.offer-limit-date" />
                        </div>
                        <div class="seu-result-infos-bottom">
                            ${offer.getLimitDate()?datetime?string("dd/MM/yyyy")}
                        </div>
                    </div>
                </div>
            </div>
        </#if>
    </#if>
</#list>
<br><br>