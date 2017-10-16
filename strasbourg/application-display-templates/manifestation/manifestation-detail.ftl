<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Détail manifestation -->
<main class="seu-container">
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
</main>
