<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>
<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
            
<!-- Liste d'événements -->
<div class="seu-wi seu-wi-agenda">
    <div class="seu-container">
        <h2 class="seu-section-title">
            <span class="seu-title">${portletHelper.getPortletTitle('agenda', renderRequest)}</span>
        </h2>
        <div class="seu-wi-content">
            <div class="seu-wi-grid">
                <#list entries as curEntry>
                    <#if (curEntry?index >= 4)>
                        <#break>
                    </#if>
                    <#assign entry = curEntry.getAssetRenderer().getEvent() />
                    <div class="seu-wi-item seu-has-ville">
                        <a href="${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}" class="seu-link" title="${entry.getTitle(locale)}">
                            <#if entry.firstStartDate?has_content>
                                <div class="seu-date">
                                    <div class="seu-date-sup">
                                        <#if entry.firstStartDate?date == entry.lastEndDate?date>
                                            <span class="seu-date-prefix"><@liferay_ui.message key="eu.event.the" /></span>
                                        <#else>
                                            <span class="seu-date-prefix"><@liferay_ui.message key="eu.event.from-the" /></span>
                                        </#if>
                                        <span class="seu-date-start"></span>
                                        <span class="seu-date-suffix"></span>
                                    </div>
                                    <div class="seu-date-end">${entry.firstStartDate?date?string['dd.MM']}</div>
                                </div>
                            </#if>
                            <div class="seu-title dotme" data-dot="3" style="word-wrap: break-word;">${entry.getTitle(locale)}</div>
                            <div class="seu-ville">
                                ${entry.getPlaceAlias(locale)} - ${entry.getPlaceCity(locale)}
                            </div>
                            <div class="item-categories" data-dot="3" style="word-wrap: break-word;">${entry.getTypeLabel(locale)}</div>
                            
                        </a>
                        <a href="#" class="seu-add-favorites" 
                            data-type="2" 
                            data-title="${entry.getTitle(locale)}" 
                            data-url="${themeDisplay.getPortalURL()}${homeURL}evenement/-/entity/id/${entry.eventId}/${entry.getNormalizedTitle(locale)}" 
                            data-id="${entry.eventId}">
                            <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
                        </a>
                    </div>
                </#list>
            </div>
        </div>
        <#if agendaURL?has_content && agendaURLText?has_content>
            <div class="seu-btn-line">
                <a href="${agendaURL}" class="seu-btn-square seu-bordered seu-core" title="${agendaURLText}">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text">${agendaURLText}</span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </a>
            </div>
        <#else>
            <div class="seu-btn-line">
                <a href="${homeURL}agenda" class="seu-btn-square seu-bordered seu-grey" title="<@liferay_ui.message key="eu.all-events" />">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text"><@liferay_ui.message key="eu.all-events" /></span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </a>
            </div>
        </#if>
    </div>
</div>