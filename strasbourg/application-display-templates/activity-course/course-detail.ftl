<!-- Détail cours -->
<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<div class="seu-container">
    <h1>
        ${entry.getName(locale)}
        <div class="seu-event-categories" data-dot="1" style="word-wrap: break-word;">  
            ${entry.getTypesLabels(locale)}
        </div>
    </h1>

    <div class="rte">
        <h2><@liferay_ui.message key="eu.presentation" /></h2>
            
        <strong>
            <@liferay_ui.message key="eu.activity.part-of-activity" /> <a href="${homeURL}activite/-/entity/id/${entry.activityId}" title="${entry.activity.getTitle(locale)}">${entry.activity.getTitle(locale)}</a>
        </strong>

        <#if entry.publics?has_content>
            <h3>
                <#if (entry.getPublics()?size == 1)>
                    <@liferay_ui.message key="eu.activity.public" />
                </#if>
                <#if (entry.getPublics()?size > 1)>
                    <@liferay_ui.message key="eu.activity.publics" />
                </#if>
            </h3>
            ${entry.getPublicsLabel(locale)}
        </#if>
        <#if entry.getArrangements(locale)?has_content>
            <h3>
                <@liferay_ui.message key="eu.activity.arrangements" />
            </h3>
            ${entry.getArrangements(locale)}
        </#if>
        <#if entry.getPrice(locale)?has_content>
            <h3>
                <@liferay_ui.message key="eu.prices" />
            </h3>
            ${entry.getPrice(locale)}
        </#if>
        <#if entry.getOrganizerName(locale)?has_content>
            <h3>
                <@liferay_ui.message key="eu.activity.manager" />
            </h3>
            ${entry.getOrganizerName(locale)}
        </#if>
    </div>

    <#assign agenda = entry.getCourseAgenda(themeDisplay.scopeGroupId, locale) />
    <#if agenda.periods?has_content>
        <div class="rte"><h2><@liferay_ui.message key="eu.activity.places-and-schedules" /></h2></div>
        <div class="row">
            <div class="col-md-9" style="padding: 50px 30px; background-color: #f6f6f6; margin-bottom: 50px;">
                <div class="seu-wi seu-wi-schedules" >
                    <div class="tab-list">
                        <div class="tab-menu-rwd">
                            <#list agenda.periods as period>
                                <button class="tab-toggle <#if period?is_first>current</#if>" data-tab-target="${period?index}">
                                    ${period.periodName}
                                </button>
                            </#list>
                        </div>
                        <#list agenda.periods as period>
                            <button class="tab-toggle <#if period?is_first>current</#if>" data-tab-target="${period?index}">
                                ${period.periodName}
                            </button>
                        </#list>
                    </div>
                    <#list agenda.periods as period>
                        <div class="tab-content <#if period?is_first>tabbed</#if>" data-tab-index="${period?index}">
                            <h3 class="hidden">${period.periodName}</h3>
                            <#list period.places as periodPlace>
                                <div style="margin-bottom: 20px">
                                    <div class="tab-title">${periodPlace.placeName}</div>
                                    <#if periodPlace.placeSigId?has_content>
                                        <div class="rte" style="margin-top: -5px; margin-bottom: 10px;">
                                            <a href="${homeURL}lieu/-/entity/sig/${periodPlace.placeSigId}"><@liferay_ui.message key="eu.activity.see-place-detail" /></a>
                                        </div>
                                    </#if>
                                    <ul class="schedule-list" style="margin-bottom: 10px;">
                                        <#list 0..6 as day>
                                            <#assign schedules = periodPlace.getSchedulesForDay(day) />
                                            <#if schedules?has_content>
                                                <li>
                                                    <span><@liferay_ui.message key="${periodPlace.getDayName(day)}" /></span>
                                                    <span>
                                                        <#list schedules as schedule>
                                                            ${schedule.startTime} - ${schedule.endTime}<#sep><br></#sep>
                                                        </#list>
                                                    </span>
                                                </li>
                                            </#if>
                                        </#list>
                                    </ul>
                                </div>
                            </#list>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    <#else>
        <div class="rte"><h2><@liferay_ui.message key="places" /></h2></div>
        <div class="row seu-wi-schedules">
            <div class="col-md-9" style="padding: 50px 30px; background-color: #f6f6f6; margin-bottom: 50px;">
                <#list entry.getPlaceSIGIds(locale) as sigId>
                    <div class="tab-title">${entry.getPlaceNames(locale)[sigId?index]}</div>
                    <div class="rte" style="margin-top: -5px; margin-bottom: 10px;">
                        <a href="${homeURL}lieu/-/entity/sig/${sigId}"><@liferay_ui.message key="eu.activity.see-place-detail" /></a>
                    </div>
                </#list>
            </div>
        </div>
    </#if>
</div>