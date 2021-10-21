<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign imageUrl = ""/>
<!-- vignette -->
<#if entry.imageURL?has_content>
    <#if !entry.imageURL?contains('http')>
        <#assign imageUrl = themeDisplay.getPortalURL() />
    </#if>
    <#assign imageUrl = imageUrl + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getManifestationScheduleDisplay(locale)} - ${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<!-- Détail manifestation -->
<div class="seu-container">
    <a href="#" class="add-favorites"
        data-type="12" 
        data-title="${entry.getTitle(locale)}" 
        data-url="${themeDisplay.getPortalURL()}${homeURL}manifestation/-/entity/id/${entry.manifestationId}" 
        data-id="${entry.manifestationId}">
        <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
    </a>
    <h1>${entry.getTitle(locale)}</h1>
    <#if entry.imageURL?has_content>
        <div class="seu-event-visu">
            <img src="${entry.imageURL}" alt="${entry.getTitle(locale)}">
        </div>
    </#if>
    <div class="seu-event-categories" data-dot="1">${entry.getTypeLabel(locale)}</div>
    <div class="event-highlight">
        <div class="item-right">
            <div class="item-content">
                <#if entry.startDate?date == entry.endDate?date>
                    <div class="item-date"><@liferay_ui.message key="eu.event.the" /> <strong>${entry.startDate?date?string.short?replace('/', '.')}</strong></div>
                <#else>
                    <div class="item-date"><@liferay_ui.message key="eu.event.from-date" /> <strong>${entry.startDate?date?string.short?replace('/', '.')}</strong> <@liferay_ui.message key="eu.event.to" /> <strong>${entry.endDate?date?string.short?replace('/', '.')}</strong></div>
                </#if>
            </div>
        </div>
    </div>
    <div class="rte">
        <h2><@liferay_ui.message key="eu.presentation" /></h2>
        ${entry.getDescription(locale)}
    </div>
</div>

<#assign events = entry.publishedEvents />
<#if events?has_content>
    <div class="seu-wi seu-wi-agenda" style="background: white;">
        <div class="seu-container">
            <h2 class="seu-section-title">
                <span class="seu-title"><@liferay_ui.message key="eu.event.program" /></span>
            </h2>
            <div class="seu-wi-content">
                <div class="seu-wi-grid">
                    <#list events as event>
                        <div class="seu-wi-item seu-has-ville">
                            <a href="${homeURL}evenement/-/entity/id/${event.eventId}/${event.getNormalizedTitle(locale)}" class="seu-link" title="${event.getTitle(locale)}">
                                <div class="seu-date">
                                    <div class="seu-date-sup">
                                        <#if event.firstStartDate?has_content && event.lastEndDate?has_content>
                                            <#if event.firstStartDate?date == event.lastEndDate?date>
                                                <span class="seu-date-prefix"><@liferay_ui.message key="eu.event.the" /></span>
                                            <#else>
                                                <span class="seu-date-prefix"><@liferay_ui.message key="eu.event.from-the" /></span>
                                            </#if>
                                        </#if>
                                        <span class="seu-date-start"></span>
                                        <span class="seu-date-suffix"></span>
                                    </div>
                                    <#if event.firstStartDate?has_content && event.lastEndDate?has_content>
                                        <div class="seu-date-end">${event.firstStartDate?date?string['dd.MM']}</div>
                                    </#if>
                                </div>
                                <div class="seu-title dotme" data-dot="3" style="word-wrap: break-word;">${event.getTitle(locale)}</div>
                                <div class="seu-ville">
                                    ${event.getPlaceAlias(locale)} 
                                    <#if event.getPlaceAddress(locale)?has_content>
                                        - ${event.getPlaceAddress(locale)}
                                    </#if>
                                    - ${event.placeZipCode} ${event.getPlaceCity(locale)}
                                </div>
                                <div class="seu-lead dotme is-truncated" data-dot="3" style="word-wrap: break-word;">${event.getDescription(locale)?replace("<[^>]*>", "", "r")}</div>
                            </a>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</#if>