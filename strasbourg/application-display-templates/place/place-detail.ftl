<!-- Détail lieu -->
<#setting locale = locale />

<#assign imageUrl = ""/>
<!-- 1ère image au dessus de l'adresse -->
<#if entry.imagesURLs?first?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + entry.imagesURLs?first?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>
<!-- bannière -->
<#if entry.imageURL?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getAlias(locale)?html}",
"og:description":'${entry.getPresentation(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") /> 
<#assign EventLocalService = serviceLocator.findService("eu.strasbourg.service.agenda.service.EventLocalService")/>

<@liferay_util["body-top"]>
    <script>
        window.title = "${entry.getAlias(locale)}";
        window.x = ${entry.getMercatorX()};
        window.y = ${entry.getMercatorY()};
    </script>
</@>
<link rel="stylesheet" href="/o/strasbourg-theme/css/leaflet.css" />

<@liferay_util["html-bottom"]>
    <script src="/o/strasbourg-theme/js/leaflet.js"></script>
    <script src="/o/strasbourg-theme/js/map.js"></script>
</@>

<div class="seu-page-lieu">
    <main class="seu-container">
        <a href="#" class="add-favorites"
            data-type="1" 
            data-title="${entry.getAlias(locale)}" 
            data-url="${themeDisplay.getPortalURL()}${homeURL}lieu/-/entity/sig/${entry.getSIGid()}/${entry.getNormalizedAlias(locale)}" 
            data-id="${entry.placeId}">
            <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
        </a>
        <h1>${entry.getAlias(locale)}</h1>
        
        <div class="seu-flexbox">

            <div class="seu-container-left">

                <!-- Cartographie -->
                <div id="aroundme" class="widget" style="margin-bottom: 45px">
                    <div id="aroundme__center" style="height: auto;">
                        <div id="mapid" style="height: 320px" >
                            <div class="aroundme__ui__group" style="z-index: 401">
                                <button class="aroundme__ui aroundme__ui--zoomin"></button>
                                <button class="aroundme__ui aroundme__ui--zoomout"></button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Horaires -->
                <#if entry.periods?has_content || entry.hasURLSchedule>
                    <div class="seu-wi--collapsing <#if !renderRequest.getAttribute("fromContactForm")?has_content>seu-first-opened</#if>">
                        <button class="seu-toggle-collapse">
                            <h2 class="schedule"><span><@liferay_ui.message key="eu.times" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <#if !entry.hasURLSchedule>
                                <div class="seu-wi seu-wi-schedules">
                                    <div class="tab-list">
                                    </div>
                                    <div class="tab-content">
                                        <!-- Jours suivants -->
                                        <h3 class="hidden"><@liferay_ui.message key="eu.place.next-days" /></h3>
                                        <div class="tab-title">${entry.getAlias(locale)}</div>
                                        <ul class="schedule-list">
                                            <#assign daySchedulesMap = entry.getFollowingWeekSchedules(.now, locale) />
                                            <#assign hasException = false />
                                            <#list daySchedulesMap?keys as day>
                                                <!-- Correctif car suite à une modif on n'envoie plus une liste vide mais null, donc erreur freemarker -->
                                                <#if daySchedulesMap[day]?size != 1 || (daySchedulesMap[day]?size == 1 && daySchedulesMap[day][0]?? && daySchedulesMap[day][0]?has_content)>
                                                    <li>
                                                        <span>${day}</span>
                                                        <span>
                                                            <#list daySchedulesMap[day] as schedule>
                                                                <div>
                                                                    <#if schedule.isException() || schedule.isPublicHoliday()>
                                                                        <#assign hasException = true />
                                                                        <#assign hasAnyException = true />
                                                                    <#else>
                                                                        <#assign hasException = false />
                                                                    </#if>
                                                                    <#if schedule.isClosed()>
                                                                        <#if hasException><span class="exception"></#if>
                                                                        <@liferay_ui.message key="eu.closed" />
                                                                        <#if hasException></span></#if>
                                                                    <#elseif schedule.isAlwaysOpen()>
                                                                        <#if hasException><span class="exception"></#if>
                                                                        <@liferay_ui.message key="always-open" />
                                                                        <#if hasException></span></#if>
                                                                    <#else>
                                                                        <#list schedule.openingTimes as openingTime>
                                                                            <div>
                                                                                <#if hasException><span class="exception"></#if>
                                                                                ${openingTime.first} - ${openingTime.second}
                                                                                <#if hasException></span></#if>
                                                                            </div>
                                                                            <#if schedule.comments[openingTime?index]?has_content>
                                                                                <div style="margin-top: -10px;<#if hasException>color: #F44336;</#if>">(${schedule.comments[openingTime?index]})</div>
                                                                            </#if>
                                                                        </#list>
                                                                    </#if>
                                                                </div>
                                                                <#if schedule.isException() || schedule.isPublicHoliday()>
                                                                    </span>
                                                                </#if>
                                                            </#list>
                                                        </span>
                                                    </li>
                                                </#if>
                                            </#list>
                                        </ul>
                                        <!-- Jours suivants pour les sous-lieux -->
                                        <#list entry.publishedSubPlaces as subPlace>
                                            <div class="tab-title">${subPlace.getName(locale)}</div>
                                            <ul class="schedule-list">
                                                <#assign daySchedulesMap = subPlace.getFollowingWeekSchedules(.now, locale) />
                                                <#list daySchedulesMap?keys as day>
                                                    <!-- Correctif car suite à une modif on n'envoie plus une liste vide mais null, donc erreur freemarker -->
                                                    <#if daySchedulesMap[day]?size != 1 || (daySchedulesMap[day]?size == 1 && daySchedulesMap[day][0]?? && daySchedulesMap[day][0]?has_content)>
                                                        <li>
                                                            <span>${day}</span>
                                                            <span>
                                                                <#list daySchedulesMap[day] as schedule>
                                                                    <div>
                                                                        <#if schedule.isException() || schedule.isPublicHoliday()>
                                                                            <#assign hasException = true />
                                                                            <#assign hasAnyException = true />
                                                                        <#else>
                                                                            <#assign hasException = false />
                                                                        </#if>
                                                                        <#if schedule.isClosed()>
                                                                            <#if hasException><span class="exception"></#if>
                                                                            <@liferay_ui.message key="eu.closed" />
                                                                            <#if hasException></span></#if>
                                                                        <#elseif schedule.isAlwaysOpen()>
                                                                            <#if hasException><span class="exception"></#if>
                                                                            <@liferay_ui.message key="always-open" />
                                                                            <#if hasException></span></#if>
                                                                        <#else>
                                                                            <#list schedule.openingTimes as openingTime>
                                                                                <div>
                                                                                    <#if hasException><span class="exception"></#if>
                                                                                    ${openingTime.first} - ${openingTime.second}
                                                                                    <#if hasException></span></#if>
                                                                                </div>
                                                                                <#if schedule.comments[openingTime?index]?has_content>
                                                                                    <div style="margin-top: -10px;<#if hasException>color: #F44336;</#if>">(${schedule.comments[openingTime?index]})</div>
                                                                                </#if>
                                                                            </#list>
                                                                        </#if>
                                                                    </div>
                                                                </#list>
                                                            </span>
                                                        </li>
                                                    </#if>
                                                </#list>
                                            </ul>
                                        </#list>
                                        <#if hasAnyException?has_content && hasAnyException>
                                            <!-- Message pour exceptions -->
                                            <span style="color: #F44336; font-weight: bold; font-size: 1.6rem;"><@liferay_ui.message key="eu.place.look-at-exceptionnal-schedule" /></span>
                                        </#if>
                                    </div>
                                    <!-- Période par défaut -->
                                    <#if entry.defaultPeriod?has_content>
                                        <div class="tab-content">
                                            <h3 class="hidden">${entry.defaultPeriod.getName(locale)}</h3>
                                            <div class="tab-title">${entry.getAlias(locale)}</div>
                                            <ul class="schedule-list">
                                                <#assign weekSchedules = entry.defaultPeriod.getWeekSchedule() />
                                                <#assign day = 0 />
                                                <#list weekSchedules as schedule>
                                                    <li>
                                                        <span><@liferay_ui.message key="jour-semaine${day}" /></span>
                                                        <span>
                                                            <#if schedule.isClosed()>
                                                                <@liferay_ui.message key="eu.closed" />
                                                            <#elseif schedule.isAlwaysOpen()>
                                                                <@liferay_ui.message key="always-open" />
                                                            <#else>
                                                                <#list schedule.openingTimes as openingTime>
                                                                    <div>
                                                                        ${openingTime.first} - ${openingTime.second}
                                                                    </div>
                                                                    <#if schedule.comments[openingTime?index]?has_content>
                                                                        <div style="margin-top: -10px">(${schedule.comments[openingTime?index]})</div>
                                                                    </#if>
                                                                </#list>
                                                            </#if>
                                                        </span>
                                                    </li>
                                                    <#assign day = day + 1 />
                                                </#list>
                                            </ul>
                                            <!-- Période par défaut pour les sous-lieux -->
                                            <#list entry.publishedSubPlaces as subPlace>
                                                <div class="tab-title">${subPlace.getName(locale)}</div>
                                                <ul class="schedule-list">
                                                    <#assign weekSchedules = subPlace.defaultPeriod.getWeekSchedule(subPlace.subPlaceId) />
                                                    <#assign day = 0 />
                                                    <#list weekSchedules as schedule>
                                                        <li>
                                                            <span><@liferay_ui.message key="jour-semaine${day}" /></span>
                                                            <span>
                                                                <#if schedule.isClosed()>
                                                                    <@liferay_ui.message key="eu.closed" />
                                                                <#elseif schedule.isAlwaysOpen()>
                                                                    <@liferay_ui.message key="always-open" />
                                                                <#else>
                                                                    <#list schedule.openingTimes as openingTime>
                                                                        <div>
                                                                            ${openingTime.first} - ${openingTime.second}
                                                                        </div>
                                                                        <#if schedule.comments[openingTime?index]?has_content>
                                                                            <div style="margin-top: -10px">(${schedule.comments[openingTime?index]})</div>
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                            </span>
                                                        </li>
                                                        <#assign day = day + 1 />
                                                    </#list>
                                                </ul>
                                            </#list>
                                        </div>
                                    </#if>
                                    <!-- Autres périodes -->
                                    <#list entry.nonDefaultPeriods as period>
                                        <div class="tab-content">
                                            <h3 class="hidden">${period.getName(locale)} <div>${period.getDisplay(locale)}</div></h3>
                                            <div class="tab-title">${entry.getAlias(locale)}</div>
                                            <ul class="schedule-list">
                                                <#assign weekSchedules = period.getWeekSchedule() />
                                                <#assign day = 0 />
                                                <#list weekSchedules as schedule>
                                                    <li>
                                                        <span><@liferay_ui.message key="jour-semaine${day}" /></span>
                                                        <span>
                                                            <div>
                                                                <#if schedule.isClosed()>
                                                                    <@liferay_ui.message key="eu.closed" />
                                                                <#elseif schedule.isAlwaysOpen()>
                                                                    <@liferay_ui.message key="always-open" />
                                                                <#else>
                                                                    <#list schedule.openingTimes as openingTime>
                                                                        <div>
                                                                            ${openingTime.first} - ${openingTime.second}
                                                                        </div>
                                                                        <#if schedule.comments[openingTime?index]?has_content>
                                                                            <div style="margin-top: -10px">(${schedule.comments[openingTime?index]})</div>
                                                                        </#if>
                                                                    </#list>
                                                                </#if>
                                                            </div>
                                                        </span>
                                                    </li>
                                                    <#assign day = day + 1 />
                                                </#list>
                                            </ul>
                                            <!-- Autres Périodes pour les sous-lieux -->
                                            <#list entry.publishedSubPlaces as subPlace>
                                                <div class="tab-title">${subPlace.getName(locale)}</div>
                                                <ul class="schedule-list">
                                                    <#assign weekSchedules = period.getWeekSchedule(subPlace.subPlaceId) />
                                                    <#assign day = 0 />
                                                    <#list weekSchedules as schedule>
                                                        <li>
                                                            <span><@liferay_ui.message key="jour-semaine${day}" /></span>
                                                            <span>
                                                                <div>
                                                                    <#if schedule.isClosed()>
                                                                        <@liferay_ui.message key="eu.closed" />
                                                                    <#elseif schedule.isAlwaysOpen()>
                                                                        <@liferay_ui.message key="always-open" />
                                                                    <#else>
                                                                        <#list schedule.openingTimes as openingTime>
                                                                            <div>
                                                                                ${openingTime.first} - ${openingTime.second}
                                                                            </div>
                                                                            <#if schedule.comments[openingTime?index]?has_content>
                                                                                <div style="margin-top: -10px">(${schedule.comments[openingTime?index]})</div>
                                                                            </#if>
                                                                        </#list>
                                                                    </#if>
                                                                </div>
                                                            </span>
                                                        </li>
                                                        <#assign day = day + 1 />
                                                    </#list>
                                                </ul>
                                            </#list>
                                        </div>
                                    </#list>
                                </div>
                            </#if>
                            <div class="rte">
                                <#if entry.hasURLSchedule>
                                    <p style="margin-bottom: 20px;">
                                        <a href="${entry.getScheduleLinkURL(locale)}" target="_blank" title="${entry.getScheduleLinkName(locale)} (<@liferay_ui.message key="eu.new-window" />)">
                                            <span class="seu-btn-text">${entry.getScheduleLinkName(locale)}</span>
                                        </a>
                                    </p>
                                </#if>
                                <#if entry.hasScheduleTable()>
                                    <p>
                                        <#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
                                        <#list entry.types as type>
                                            <#if (assetVocabularyHelper.getCategoryProperty(type.categoryId, 'schedule') == 'true')>
                                                <#assign category = type />
                                            </#if>
                                        </#list>
                                        <a href="${homeURL}horaires-lieux/-/schedules/category/${category.categoryId}" class="seu-btn-square--filled--second">
                                            <span class="seu-btn-text"><@liferay_ui.message key="eu.see-all-schedule-of" /> ${category.getTitle(locale)?lower_case}</span>
                                        </a>
                                    </p>
                                </#if>
                                <#if !entry.hasURLSchedule>
                                    <!-- Liste des exceptions -->
                                    <#assign exceptions = entry.getPlaceScheduleExceptionFreeMarker(.now, true, locale) />
                                    <#if exceptions?has_content || (hasAnyException?has_content && hasAnyException)>
                                        <#assign totalExceptionsCount = 0 />
                                        <h3><@liferay_ui.message key="eu.exceptional-closings-openings" /></h3>
                                        <ul class="seu-dates-list">
                                            <#list exceptions as exception>
                                                <#assign totalExceptionsCount++ />
                                                <li>
                                                    <strong>${exception.getPeriodDisplay(locale)}</strong> : 
                                                    <#if exception.isClosed()>
                                                        <@liferay_ui.message key="eu.closed" />
                                                    <#else>
                                                        <#list exception.openingTimes as openingTime>
                                                            ${openingTime.first} - ${openingTime.second}<#sep>, </#sep>
                                                        </#list>
                                                    </#if>
                                                    - ${exception.description}
                                                </li>
                                            </#list>
                                            <#list entry.publishedSubPlaces as subPlace>
                                                <#assign exceptions = subPlace.getSubPlaceScheduleExceptionFreeMarker(.now, true, locale) />
                                                <#if exceptions?has_content>
                                                    <#list exceptions as exception>
                                                        <#assign totalExceptionsCount++ />
                                                        <li>
                                                            <strong>${subPlace.getName(locale)} - ${exception.getPeriodDisplay(locale)}</strong> : 
                                                            <#if exception.isClosed()>
                                                                <@liferay_ui.message key="eu.closed" />
                                                            <#else>
                                                                <#list exception.openingTimes as openingTime>
                                                                    ${openingTime.first} - ${openingTime.second}<#sep>, </#sep>
                                                                </#list>
                                                            </#if>
                                                            - ${exception.description}
                                                        </li>
                                                    </#list>
                                                </#if>
                                            </#list>
                                        </ul>

                                        <#if (totalExceptionsCount > 5)>
                                            <div class="seu-line-left">
                                                <button class="seu-see-more seu-btn-square seu-bordered seu-core">
                                                    <span class="seu-flexbox">
                                                        <span class="seu-btn-text seu-more"><@liferay_ui.message key="eu.see-more" /></span>
                                                        <span class="seu-btn-text seu-less"><@liferay_ui.message key="eu.see-less" /></span>
                                                        <span class="seu-btn-arrow"></span>
                                                    </span>
                                                </button>
                                            </div>
                                        </#if>
                                    </#if>
                                </#if>
                                
                                <#if entry.getExceptionalSchedule(locale)?has_content>
                                    <h3><@liferay_ui.message key="eu.exceptional-schedule" /></h3>
                                    ${entry.getExceptionalSchedule(locale)}
                                </#if>
                            </div>
                        </div>
                    </div>
                </#if>
        

                <!-- Présentation -->
                <#if entry.getPresentation(locale)?has_content>
                    <div class="seu-wi--collapsing <#if !entry.periods?has_content && !renderRequest.getAttribute("fromContactForm")?has_content>seu-first-opened</#if>">
                        <button class="seu-toggle-collapse">
                            <h2 class="description"><span><@liferay_ui.message key="eu.presentation" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getPresentation(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Agenda -->
                <#assign placeEvents = EventLocalService.getCurrentAndFuturePublishedEventsFromPlace(entry.getSIGid()) />
                <#if entry.displayEvents && placeEvents?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="description"><span style="text-transform: uppercase;"><@liferay_ui.message key="agenda" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="seu-agenda-slider-container">
                                <div class="seu-slider">
                                    <#assign i=0 />
                                    <#list placeEvents?sort_by("startDateFirstCurrentAndFuturePeriod") as event>
                                        <#if i == 5>
                                            <#break>
                                        </#if>
                                        <div class="seu-agenda-slider-item seu-has-ville">
                                            <a href="${homeURL}evenement/-/entity/id/${event.eventId}/${event.getNormalizedTitle(locale)}" class="seu-link" title="${event.getTitle(locale)}">
                                                <div class="seu-date">
                                                    <div class="seu-date-sup">
                                                        <#if event.firstStartDate?date == event.lastEndDate?date>
                                                            <span class="seu-date-prefix"><@liferay_ui.message key="eu.event.the" /></span>
                                                        <#else>
                                                            <span class="seu-date-prefix"><@liferay_ui.message key="eu.event.from-the" /></span>
                                                        </#if>
                                                        <span class="seu-date-start"></span>
                                                        <span class="seu-date-suffix"></span>
                                                    </div>
                                                    <div class="seu-date-end">${event.firstStartDate?date?string['dd.MM']}</div>
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
                                        <#assign i++>
                                    </#list>
                                </div>
                                <div class="owl-nav">
                                    <button class="seu-owl-prev">
                                        <span class="seu-picto"></span>
                                    </button>
                                    <button class="seu-owl-next">
                                        <span class="seu-picto"></span>
                                    </button>
                                </div>
                                <div class="seu-btn-line">
                                    <a href="${homeURL}agenda?idSIGPlace=${entry.getSIGid()}" class="seu-btn-square seu-filled seu-second" title="<@liferay_ui.message key="eu.all-events" />">
                                        <span class="seu-flexbox">
                                            <span class="seu-btn-text"><@liferay_ui.message key="eu.all-events" /></span>
                                            <span class="seu-btn-arrow"></span>
                                        </span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Activités -->
                <#assign activityLocalService = serviceLocator.findService("eu.strasbourg.service.activity.service.ActivityLocalService") />
                <#assign placeActivityAgenda = activityLocalService.getPlaceAgenda(entry.getSIGid(), locale) />
                <#if placeActivityAgenda.periods?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="more"><span><@liferay_ui.message key="eu.activity.activities" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="seu-wi seu-wi-schedules">
                                <div class="tab-list">
                                    <div class="tab-menu-rwd">
                                        <#list placeActivityAgenda.periods as period>
                                            <button class="tab-toggle <#if period?is_first>current</#if>" data-tab-target="${period?index}">
                                                ${period.periodName}
                                            </button>
                                        </#list>
                                    </div>
                                    <#list placeActivityAgenda.periods as period>
                                        <button class="tab-toggle <#if period?is_first>current</#if>" data-tab-target="${period?index}">
                                            ${period.periodName}
                                        </button>
                                    </#list>
                                </div>
                                <#list placeActivityAgenda.periods as period>
                                    <div class="tab-content <#if period?is_first>tabbed</#if>" data-tab-index="${period?index}">
                                        <h3 class="hidden">${period.periodName}</h3>
                                        <#list period.courses as course>
                                            <div style="margin-bottom: 20px">
                                                <div class="tab-title">${course.courseName}</div>
                                                <div class="rte" style="margin-top: -5px; margin-bottom: 10px;">
                                                    <a href="${homeURL}cours/-/entity/id/${course.courseId}"><@liferay_ui.message key="eu.see-detail" /></a>
                                                </div>
                                                <ul class="schedule-list" style="margin-bottom: 10px;">
                                                    <#list 0..6 as day>
                                                        <#assign schedules = course.getSchedulesForDay(day) />
                                                        <#if schedules?has_content>
                                                            <li>
                                                                <span><@liferay_ui.message key="${course.getDayName(day)}" /></span>
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
                </#if>

                <!-- Informations complémentaires -->
                <#if entry.getAdditionalInformation(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="more"><span><@liferay_ui.message key="eu.place.additional-information" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getAdditionalInformation(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Médias -->
                <#if entry.documentURLs?has_content || entry.videos?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="media"><span><@liferay_ui.message key="eu.place.medias" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <#list entry.documentURLs as fileURL>
                                <#if fileURL?has_content>
                                    <#assign file = fileEntryHelper.getFileEntryByRelativeURL(fileURL) />
                                    <#assign title = fileEntryHelper.getFileTitle(file.getFileEntryId(), locale) />
                                    <#assign size = fileEntryHelper.getReadableFileEntrySize(file.getFileEntryId(), locale) />
                                    <div class="seu-wi seu-media seu-wi-download">  
                                        <div class="seu-media-container">  
                                            <div class="seu-media-left"><div class="seu-media-picto"></div></div>  
                                            <div class="seu-media-right">  
                                                <div class="seu-media-text">  
                                                    <div class="seu-media-title">${title}</div>  
                                                    <p>${file.getExtension()?upper_case} - ${size}</p>  
                                                </div>  
                                                <a href="${fileURL}" target="_blank" class="seu-media-download seu-btn-square seu-filled seu-second" title="${title} (<@liferay_ui.message key="eu.new-window" />)">  
                                                    <div class="seu-btn-text-editable">
                                                        <span class="seu-flexbox">  
                                                            <span class="seu-btn-text"><@liferay_ui.message key="download" /></span>  
                                                            <span class="seu-btn-arrow">&nbsp;</span>  
                                                        </span>
                                                    </div>  
                                                </a>  
                                            </div>  
                                        </div>  
                                    </div>  
                                </#if>
                            </#list>
                            <#list entry.videos as video>
                                <div class="seu-wi seu-media seu-wi-embed">
                                    <div class="seu-media-container"> 
                                        <div class="seu-media-left">
                                            <div class="seu-media-picto"></div>
                                        </div>  
                                        <div class="seu-media-right"> 
                                            <div class="seu-media-text"> 
                                                <div class="seu-media-title">${video.getTitle(locale)}</div> 
                                                <p class="seu-media-description">${video.getDescription(locale)}</p> 
                                            </div> 
                                        </div> 
                                        <div class="seu-media-bottom"> 
                                            <div class="seu-media-ratio"> 
                                                ${video.getPlayer(locale)}
                                            </div> 
                                        </div> 
                                    </div> 
                                </div>
                            </#list>
                        </div>
                    </div>
                </#if>

                <!-- Accès -->
                <#if entry.getAccess(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="geoloc"><span><@liferay_ui.message key="access" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getAccess(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Accès pour handicapés -->
                <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="handicap"><span><@liferay_ui.message key="eu.access-for-disabled" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <#if entry.hasAnyAccessForDisabled()>
                                <div class="seu-svg-list">
                                    <#if entry.accessForWheelchair>
                                        <div class="seu-picto seu-picto-wheelchair" title="<@liferay_ui.message key='eu.access-for-wheelchair' />"></div>
                                    </#if>

                                    <#if entry.accessForDeaf>
                                        <div class="seu-picto seu-picto-deaf" title="<@liferay_ui.message key='eu.access-for-deaf' />"></div>
                                    </#if>
                                    
                                    <#if entry.accessForBlind>
                                        <div class="seu-picto seu-picto-blind" title="<@liferay_ui.message key='eu.access-for-blind' />"></div>
                                    </#if>

                                    <#if entry.accessForDeficient>
                                        <div class="seu-picto seu-picto-mental" title="<@liferay_ui.message key='eu.access-for-deficient' />"></div>
                                    </#if>
                                    
                                    <#if entry.accessForElder>
                                        <div class="seu-picto seu-picto-pmr" title="<@liferay_ui.message key='eu.access-for-elder' />"></div>
                                    </#if>
                                </div>
                            </#if>
                            <#if entry.getAccessForDisabled(locale)?has_content>
                                <div class="rte">
                                    ${entry.getAccessForDisabled(locale)}
                                </div>
                            </#if>
                        </div>
                    </div>
                </#if>

                <!-- Accès -->
                <#if entry.getServiceAndActivities(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="services"><span><@liferay_ui.message key="eu.services-and-activities" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getServiceAndActivities(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Caractéristiques -->
                <#if entry.getCharacteristics(locale)?has_content>
                    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="caracteristiques"><span><@liferay_ui.message key="eu.features" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getCharacteristics(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Contact -->
                <#if entry.mail?has_content>
                    <div class="seu-wi--collapsing <#if renderRequest.getAttribute("fromContactForm")?has_content && renderRequest.getAttribute("fromContactForm")>seu-first-opened</#if>">
                        <button class="seu-toggle-collapse">
                            <h2 class="contact"><span><@liferay_ui.message key="eu.contact" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box white-box">
                            <div class="rte">
                                <@liferay_portlet.actionURL var="contactURL" name="contact">
                                    <@liferay_portlet.param name="classPK" value="${entry.getPlaceId()}" />
                                    <@liferay_portlet.param name="to" value="${entry.mail}" />
                                    <@liferay_portlet.param name="title" value="${entry.getAlias(locale)}" />
                                    <@liferay_portlet.param name="type" value="Place" />
                                </@liferay_portlet.actionURL>
                                
                                <form id="contactForm" action="${contactURL}#contactForm" name="contactForm" method="post" class="seu-wi seu-wi-contact-form col-md-8">
                                    <@liferay_ui.error key="all-fields-required" message="eu.all-fields-required" targetNode="#contactForm" />
                                    <@liferay_ui.error key="invalid-mail" message="eu.invalid-mail" targetNode="#contactForm" />
                                    <@liferay_ui.error key="recaptcha-error" message="eu.recaptcha-error" targetNode="#contactForm" />

                                    <#if renderRequest.getAttribute("mailSent")?has_content && renderRequest.getAttribute("mailSent")>
                                        <p class="mail-success">
                                           <@liferay_ui.message key="eu.mail-success" />
                                        </p>
                                    </#if>

                                    <div class="inline-group">
                                        <div class="widget widget-required">
                                            <div class="title">
                                                <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName"><@liferay_ui.message key="contact.lastname" /></label>
                                            </div>
                                            <div class="content">
                                                <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_lastName" type="text" placeholder="<@liferay_ui.message key="contact.your-lastname" />" value="${renderRequest.getAttribute("lastName")!""}">
                                            </div>
                                        </div>
                                        <div class="widget widget-required">
                                            <div class="title">
                                                <label for="prenom"><@liferay_ui.message key="contact.firstname" /></label>
                                            </div>
                                            <div class="content">
                                                <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_firstName" type="text" placeholder="<@liferay_ui.message key="contact.your-firstname" />" value="${renderRequest.getAttribute("firstName")!""}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="widget widget-required">
                                        <div class="title">
                                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email"><@liferay_ui.message key="contact.mail" /></label>
                                        </div>
                                        <div class="content">
                                            <input name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" aria-required="true" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_email" type="text" placeholder="<@liferay_ui.message key="contact.your-mail" />" value="${renderRequest.getAttribute("email")!""}">
                                        </div>
                                    </div>
                                    <div class="widget widget-required">
                                        <div class="title">
                                            <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message"><@liferay_ui.message key="contact.request" /></label>
                                        </div>
                                        <div class="content">
                                            <textarea name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_message" placeholder="<@liferay_ui.message key="contact.your-message" />" rows="5">${renderRequest.getAttribute("message")!""}</textarea>
                                        </div>
                                    </div>
                                    <div>
                                        <label for="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail">

                                        <input type="checkbox" class="notification-email"
                                            name="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" id="_eu_strasbourg_portlet_entity_detail_EntityDetailPortlet_notificationEmail" checked>&nbsp;<@liferay_ui.message key="eu.do-you-want-a-notification" />
                                        </label>
                                    </div>
                                    <div style="margin: 20px 0;" class="g-recaptcha" data-sitekey="${propsUtil.get('eu.strasbourg.recaptcha.public')}"></div>
                                    <div style="padding-top: 20px; padding-bottom: 20px;">
                                        <@liferay_ui.message key="contact.default-privacy" />
                                    </div>
                                    <div class="buttons submit">
                                        <div class="SubmitWidget widget submit-button" style="min-width: 150px;">
                                            <div class="content"><button name="submit" value="<@liferay_ui.message key="send" />"><@liferay_ui.message key="send" /></button></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>


            <div class="seu-container-right">
                <#if entry.imagesURLs?has_content>
                    <div class="seu-wi-slideNpop">
                        <div class="slide">
                            <ul class="slider">
                                <#list entry.imagesURLs as imageURL>
                                    <#assign image = fileEntryHelper.getFileEntryByRelativeURL(imageURL) />
                                    <#assign title = fileEntryHelper.getFileTitle(image.getFileEntryId(), locale) />
                                    <#assign legend = fileEntryHelper.getImageLegend(image.getFileEntryId(), locale) />
                                    <#assign copyright = fileEntryHelper.getImageCopyright(image.getFileEntryId(), locale) />
                                    <li style="background-image: url(${imageURL});"  data-title="${title}" data-description="${legend} <#if copyright?has_content>&copy; ${copyright}</#if>">
                                        <img src="${imageURL}" alt="${title}">
                                    </li>
                                </#list>
                            </ul>
                            <div class="owl-nav">
                                <button class="seu-owl-prev disabled">
                                    <span class="seu-picto"></span>
                                </button>
                                <button class="seu-owl-next">
                                    <span class="seu-picto"></span>
                                </button>
                            </div>
                        </div>
                        <div class="pop">
                            <div class="pop-box">
                                <div class="pop-head">
                                    <div class="pop-title dotme">Test de titre</div>
                                    <button class="pop-close"></button>
                                </div>
                                <div class="owl-nav">
                                    <button class="seu-owl-prev disabled">
                                        <span class="seu-picto"></span>
                                    </button>
                                    <button class="seu-owl-next">
                                        <span class="seu-picto"></span>
                                    </button>
                                </div>
                                <div class="fill"></div>
                                <div class="pop-foot">
                                    <div class="pop-description dotme">Test description</div>
                                    <div class="pop-pager">
                                        <div class="pop-current"></div>
                                        <div class="pop-total"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="pop-area">
                                
                            </div>
                        </div>
                    </div>
                </#if>
                <div class="seu-location-infos">
                    <#if entry.isEnabled()>
                        <#assign occupationState = entry.getRealTime() />
                        <div class="seu-crowded-flexbox">
                            <div class="flex-left">
                                <#assign isSwimmingPool = entry.isSwimmingPool() />
                                <#assign isIceRink = entry.isIceRink() />
                                <#assign isMairie = entry.isMairie() />
                                <#assign isParking = entry.isParking() />
                                <#assign isVelhopStation = entry.isVelhopStation() />
                                <#if isSwimmingPool || isIceRink >
                                    <h3><@liferay_ui.message key="live-frequentation" /></h3>
                                <#elseif isMairie>
                                    <h3><@liferay_ui.message key="estimated-time" /></h3>
                                <#elseif isParking>
                                    <h3><@liferay_ui.message key="live-occupation" /></h3>
                                <#elseif isVelhopStation>
                                    <h3><@liferay_ui.message key="live-disponibility" /></h3>
                                </#if>
                            </div>
                            <div class="flex-right">
                                <!-- green orange red black -->
                                <div class="crowded-amount ${occupationState.cssClass}" <#if isMairie> style="font-size: 1.5rem"</#if>>
                                    <#if isSwimmingPool || isIceRink || isMairie>
                                        ${occupationState.occupationLabel}
                                    <#elseif isParking || isVelhopStation>
                                        ${occupationState.available}
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="crowded-caption">
                            <#if isSwimmingPool || isIceRink || isMairie>
                                <@liferay_ui.message key="${occupationState.label}" />
                            <#elseif isParking>
                                <@liferay_ui.message key="eu.place.available-spots" /> ${occupationState.available}
                            <#elseif isVelhopStation>
                                <@liferay_ui.message key="eu.place.available-velhop" /> ${occupationState.available}
                            </#if>
                        </div>
                        <!-- ajout post covid : affichage capacité totale -->
                        <#if isSwimmingPool >
                            <div class="crowded-caption">
                                <@liferay_ui.message key="eu.place.total-capacity" /> ${occupationState.capacity}
                            </div>
                        </#if>
                        <div class="crowded-fyi">    
                            <#if isSwimmingPool>
                                <@liferay_ui.message key="live-occupation-explanation" />
                            <#elseif isMairie>
                                <@liferay_ui.message key="estimated-time-explanation" />
                            <#elseif isParking>
                                <@liferay_ui.message key="eu.place.total-capacity" /> ${occupationState.capacity}
                            <#elseif isIceRink>
                                <@liferay_ui.message key="live-ice-rink-occupation-explanation" />
                            </#if>
                        </div>
                    </#if>

                    <h3><@liferay_ui.message key="eu.place.address-details" /></h3>
                    <div class="rte">
                        <p>
                            <#if entry.addressStreet?has_content>
                                ${entry.addressStreet} <br>
                            </#if>
                            <#if entry.addressComplement?has_content>
                                ${entry.addressComplement} <br>
                            </#if>
                            <#if entry.addressDistribution?has_content>
                                ${entry.addressDistribution} <br>
                            </#if>
                            ${entry.addressZipCode} ${entry.getCity(locale)}
                        </p>
                        <#if entry.phone?has_content>
                            <p>
                                <@liferay_ui.message key="phone" /> : ${entry.phone}
                            </p>
                        </#if>
                        <#if entry.getSiteLabel(locale)?has_content && entry.getSiteURL(locale)?has_content>
                            <p>
                                <a href="${entry.getSiteURL(locale)}" class="seu-external" title="${entry.getSiteLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">${entry.getSiteLabel(locale)}</a>
                            </p>
                        </#if>
                        <#if entry.getFacebookLabel(locale)?has_content && entry.getFacebookURL(locale)?has_content>
                            <p>
                                <a href="${entry.getFacebookURL(locale)}" class="seu-external" title="${entry.getFacebookLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">${entry.getFacebookLabel(locale)}</a>
                            </p>
                        </#if>
                        <#if entry.getInstagramLabel(locale)?has_content && entry.getInstagramURL(locale)?has_content>
                            <p>
                                <a href="${entry.getInstagramURL(locale)}" class="seu-external" title="${entry.getInstagramLabel(locale)} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">${entry.getInstagramLabel(locale)}</a>
                            </p>
                        </#if>
                    </div>
                </div>
            </div>
        </div>  
    </main>
</div>

<style>
    .lfr-alert-container {
        position: static;
        padding: 40px;
        margin-bottom: 25px;
        background: #EF5350;
    }
    
    .lfr-alert-container .lfr-alert-wrapper {
        margin-bottom: 0;
        padding: 0;
        height: auto !important;
    }
    
    .lfr-alert-container .lfr-alert-wrapper + .lfr-alert-wrapper {
        margin-top: 15px;
    }
    
    .lfr-alert-container .alert-danger {
        background: none;
        border: none;
        margin: 0;
        padding: 0;
        color: white;
        line-height: 25px
    }
    
    .lfr-alert-container .alert-danger button,
    .lfr-alert-container .alert-danger .lead {
        display: none;
    }
    
    .mail-success {
        background: #4CAF50;
        color: white;
        padding: 40px;
    }

    @media only screen and (max-width: 767px){
        #aroundme .aroundme__ui__group {
            height: calc(100% - 20px); 
        }
    }
</style>
<#if entry.imageURL?has_content>
    <script>
        if ($('.region-banner').length == 0) {
            var bannerHtml = '<div class="region-banner"></div>';
            $('.region-post-header').addClass('has-banner').prepend(bannerHtml);
        }
    </script>
    <style>
        .region-banner {
            background-image: url(${entry.imageURL}) !important;
        }
    </style>
</#if>