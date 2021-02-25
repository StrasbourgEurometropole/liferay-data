<!-- Détail arret -->
<#setting locale = locale />

<script>
    title = '${entry.title?html?js_string}';
</script>

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") /> 

<@liferay_util["body-top"]>
    <script>
        window.title = "${entry.title}";
        window.x = ${entry.getLongitude()};
        window.y = ${entry.getLatitude()};
    </script>
</@>
<link rel="stylesheet" href="/o/strasbourg-theme/css/leaflet.css" />

<@liferay_util["html-bottom"]>
    <script src="/o/strasbourg-theme/js/leaflet.js"></script>
    <script src="/o/strasbourg-theme/js/map.js"></script>
</@>

<div class="seu-page-arret">
    <main class="seu-container">
        <a href="#" class="add-favorites"
            data-type="14" 
            data-title="${entry.title}" 
            data-url="${themeDisplay.getPortalURL()}${homeURL}arret/-/entity/id/${entry.arretId}" 
            data-id="${entry.arretId}">
            <span><@liferay_ui.message key="eu.add-to-favorite" /></span>
        </a>
        <h1>${entry.typeText} - ${entry.title}</h1>
        
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
        

                <!-- Ancre vers la zone d'alerte -->
                <#assign alerts = entry.alertsActives />
                <#if alerts?size != 0>
                    <a href="#alert" title="<@liferay_ui.message key='eu.see-perturbation' />" class="ancrage-alerte" onClick="$('#alert button').click();">
                        <@liferay_ui.message key="eu.see-perturbation" />
                    </a>
                </#if>
                
                <!-- Prochains passages -->
                <div class="seu-wi--collapsing <#if !renderRequest.getAttribute("fromContactForm")?has_content>seu-first-opened</#if>">
                    <button class="seu-toggle-collapse">
                        <h2 class="schedule"><span><@liferay_ui.message key="eu.arret.next-bus-stop" /></span></h2>
                    </button>
                    <div class="seu-collapsing-box">
                        <div class="seu-wi seu-wi-schedules">
                            <div class="tab-content tabbed">
                                <ul class="schedule-list"> 
                                    <#if entry.code == "999">
                                        <li><@liferay_ui.message key="eu.no-real-time-for-stop" /></li>
                                    </#if>
                                    <#if entry.code != "999">
                                        <#assign arretRealTime = entry.arretRealTime/>
                                        <#if arretRealTime?size == 0>
                                            <li><@liferay_ui.message key="eu.no-visit-found" /></li>
                                        </#if>
                                        <#if arretRealTime?size != 0>
                                            <#assign ligneService = serviceLocator.findService("eu.strasbourg.service.gtfs.service.LigneService") />
                                            <#assign ligneColors = ligneService.getLigneColorsFreemarker() />
                                            <#list arretRealTime as realTime>
                                                <#if realTime?counter gt 12>
                                                    <#break>
                                                </#if>
                                                <#if ligneColors[realTime.MonitoredVehicleJourney.LineRef]??>
                                                    <#assign colors = ligneColors[realTime.MonitoredVehicleJourney.LineRef] />
                                                </#if>
                                                <#if !ligneColors[realTime.MonitoredVehicleJourney.LineRef]??>
                                                    <#assign colors = "" />
                                                </#if>
                                                <#assign backgroundColor = (colors?has_content && colors[0]?has_content)?then(colors[0],"eeeeee") />
                                                <#assign textColor = (colors?has_content && colors[1]?has_content)?then(colors[1],"000000") />
                                                <li style="display:block;">
                                                    <div class="row tram-destination">
                                                        <p class="tram-destination-letter">
                                                            <span class="transport-letters-icon" style="background:#${backgroundColor}; color:#${textColor};">
                                                                    ${realTime.MonitoredVehicleJourney.PublishedLineName}
                                                            </span>
                                                        </p>
                                                        <div class="tram-destination-name">
                                                            <p data-dot="2" style="font-size: inherit;">
                                                                ${realTime.MonitoredVehicleJourney.DestinationName}
                                                            </p>
                                                        </div>
                                                        <p class="tram-destination-schedule">
                                                            <strong>
                                                                    ${realTime.MonitoredVehicleJourney.MonitoredCall.ExpectedDepartureTime?datetime.xs?string["HH:mm"]}
                                                            </strong>
                                                        </p>
                                                    </div>
                                                </li>
                                            </#list>
                                        </#if>
                                    </#if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Fiche horaires de l'arret -->
                <!--    <div class="seu-wi--collapsing">
                        <button class="seu-toggle-collapse">
                            <h2 class="media"><span><@liferay_ui.message key="eu.arret.schedule" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                        </div>
                    </div>
                -->

                <!-- Alertes -->
                <#if alerts?size != 0>
                    <div class="seu-wi--collapsing" id="alert">
                        <button class="seu-toggle-collapse">
                            <h2 class="geoloc"><span><@liferay_ui.message key="eu.arret.alert" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <#list alerts as alert>
                                <div class="rte">
                                    <h3>
                                        <#if alert.startDate?date == alert.endDate?date>
                                            <@liferay_ui.message key="eu.event.the" /> ${alert.startDate?date?string.short?replace('/', '.')}
                                        <#else>
                                            <@liferay_ui.message key="eu.event.from-date" /> ${alert.startDate?date?string.short?replace('/', '.')} <@liferay_ui.message key="eu.event.to" /> ${alert.endDate?date?string.short?replace('/', '.')}
                                        </#if>
                                    </h3>
                                    <h4>${alert.getLigneAndDirection(locale)}</h4>
                                    <p>${alert.getPerturbation(locale)}</p>
                                </div>
                            </#list>
                        </div>
                    </div>
                </#if>
            </div>


            <div class="seu-container-right">
                <div class="seu-location-infos">

                    <h3><@liferay_ui.message key="eu.arret.code" /> ${entry.code}</h3>
                    <div class="rte">
                        <p>
                            <@liferay_ui.message key="eu.arret.info" />
                        </p>
                        <p>
                            <a href="https://www.cts-strasbourg.eu/${locale?keep_before('_')}" class="seu-external" title="<@liferay_ui.message key="eu.arret.cts" /> (<@liferay_ui.message key="eu.new-window" />)" target="_blank"><@liferay_ui.message key="eu.arret.cts" /></a>
                        </p>
                    </div>
                </div>
            </div>
        </div>  
    </main>
</div>