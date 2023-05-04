<!-- DETAIL D'UN EVENEMENT -->

<#-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div class="ops-page-detail-concert">

    <header class="ops-header-concert" data-vheight="100 - 100">
        <figure class="fit-cover">
            <#if entry.imageURL?has_content>
                <img src="${entry.imageURL}" width="1600" height="900" alt="${entry.getTitle(locale)}"/>
            </#if>
        </figure>

        <div class="ops-content-wrapper ops-caption-concert">
            <span class="ops-typologie">${entry.getLabelTypologies(locale)}</span>
            <h1>${entry.getTitle(locale)}</h1>

            <div class="ops-cta-concert">
            <#if entry.subscriptionURL?has_content>
                <a href="${entry.subscriptionURL}"><@liferay_ui.message key="eu.ops.buy.a.subscription" /></a>
            </#if>
            <#if entry.bookingURL?has_content>
                <a href="${entry.bookingURL}" class="ops-ticket"><@liferay_ui.message key="eu.ops.buy.my.ticket" /></a>
            </#if>
            </div>

            <nav class="ops-scrollto">
                <ul>
                    <li><a href="#ops-representations" class="ops-active"><@liferay_ui.message key="eu.ops.representation" /></a></li>
                    <li><a href="#ops-description"><@liferay_ui.message key="eu.ops.description" /></a></li>
                    <li id="distribution-link"><a href="#ops-distribution"><@liferay_ui.message key="eu.ops.distribution" /></a></li>
                    <li><a href="#ops-a-voir"><@liferay_ui.message key="eu.ops.to.see" /></a></li>
                    <li id="audio-link"><a href="#ops-audio"><@liferay_ui.message key="eu.ops.audio" /></a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- BLOCS - LES PROCHAINS CONCERTS -->
    <div id="ops-representations" class="ops-bloc-slider-concerts">
        <div class="ops-content-wrapper">

            <div class="slick-carousel slick-cards-slider hide">

                <#-- Parcours des dates de l'event -->
                <#list entry.getCurrentAndFuturePeriods() as period>
                    <div class="ops-item">
                        <time><span>${period.getDisplay(locale, false, true)}</span></time>
                        <div class="ops-horaires">${period.getTimeDetail(locale)}</div>
                        <h3>${entry.getTitle(locale)}</h3>
                        <span class="ops-typologie">${entry.getThemeLabel(locale)}</span>
                    </div>
                </#list>
                
            </div>

        </div>
    </div>

    <!-- BLOC - INFOS DU CONCERT -->
    <div id="ops-description" class="ops-bloc-infos-concert">
        <div class="ops-content-wrapper">

            <!-- Programme -->
            <#if entry.getProgram(locale)?has_content>
                <div class="ops-col-33">
                    <span class="ops-title-infos"><@liferay_ui.message key="eu.ops.program" /></span>
                    ${entry.getProgram(locale)}
                </div>
            </#if>

            <!-- Distribution -->
            <#if entry.getDistribution(locale)?has_content>
                <div class="ops-col-33">
                    <span class="ops-title-infos"><@liferay_ui.message key="eu.ops.distribution" /></span>
                    ${entry.getDistribution(locale)}
                </div>
            </#if>

            <!-- Adresse p-->
            <div class="ops-col-33">
                <span class="ops-title-infos"><@liferay_ui.message key="eu.ops.place" /></span>
                <address><#if entry.getPlaceAlias(locale)?has_content>${entry.getPlaceAlias(locale)}<#else><@liferay_ui.message key="eu.ops.strasbourg.national.opera.of.rhine" /></#if></address>
            </div>

        </div>
    </div>


    <!-- BLOC DESCRIPTION -->
    <div class="ops-bloc-description">
        <div class="ops-content-wrapper">
            ${entry.getDescription(locale)}
        </div>
    </div>

    <#-- Recuperation des suggéstions de l'event -->
    <#assign suggestions = entry.getSuggestions(request, 10, null, "typologie") />
	
	<#if suggestions?size gt 0 >
        <!-- BLOC - SLIDER CARDS - NOS PROCHAINS CONCERTS -->
        <div id="ops-a-voir" class="ops-bloc-slider-cards">
            <div class="ops-content-wrapper ops-content-wrapper-large">
                <div>
                    <h3 class="ops-title-line"><span><@liferay_ui.message key="eu.ops.this.may.interest.you" /></span></h3>
                </div>

                <div class="slick-carousel slick-cards-slider">

                    <#list suggestions as suggestion>
                        <div class="ops-item">
                            <a href="${homeURL}detail-evenement/-/entity/id/${suggestion.eventId}/${suggestion.getNormalizedTitle(locale)}" class="ops-card ops-card-concert">
                                <div>
                                    <time><span>${suggestion.getEventScheduleDisplay(locale, false, true)}</span></time>
                                    <div class="ops-next-date"></div>
                                    <h3>${suggestion.getTitle(locale)}</h3>
                                    <div class="ops-img">
                                        <figure class="fit-cover">
                                            <#if suggestion.imageURL?has_content>
                                                <img src="${suggestion.imageURL}" width="330" height="170" alt="Nom du concert"/>
                                            </#if>
                                        </figure>
                                        <span class="ops-typologie">${suggestion.getLabelTypologies(locale)}</span>
                                    </div>
                                    <div class="ops-content">
                                        <span class="ops-songs"><strong>${suggestion.getComposer()}</strong></span>
                                        <span class="ops-names">${suggestion.getDistribution()}</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </#list>
                </div>

                <div class="ops-link-bottom">
                    <a href="${homeURL}agenda" class="ops-btn"><@liferay_ui.message key="eu.ops.consult.full.agenda" /></a>
                </div>
            </div>
        </div>
    </#if>

</div>

<script>
    $(document).ready(function(){
        if($('*').find("#ops-distribution").length == 0)
            $("#distribution-link").hide();

        if($('*').find("#ops-audio").length == 0)
            $("#audio-link").hide();

        var eventID = ${entry.eventId};

        // Recherche des sessions futures
        Liferay.Service(
            '/agenda.event/get-sessions',
            {
                eventID: eventID
            },
            function(json) {
                if(json != ""){
                    $('#ops-representations .ops-item').each(function(index) {
                        $('#ops-representations .slick-cards-slider').slick('slickRemove', 0);
                    });
                }
                $('#ops-representations .slick-cards-slider').removeClass('hide');

                for(var i = 0; i < json.length; i++) {
                    var session = json[i];

                    // Date de concert
                    var concertDate = new Date(Date.parse(session.sessionDate));

                    // Elements

                        cssClass = "";
                        ticketingElement = '<a href="' + session.link + '" target="_blank"><@liferay_ui.message key="eu.ops.buy.my.ticket" /></a>';
 
                    $('#ops-representations .slick-cards-slider').slick('slickAdd',
                        '<div class="ops-item ' + session.cssClass + '">' +
                            '<time datetime="' + concertDate.getFullYear() + '-' + ('0' + (concertDate.getMonth() + 1)).slice(-2) + '-' + concertDate.getDate() + '">' + 
                                '<span>' + ('0' + concertDate.getDate()).slice(-2) + '/' + ('0' + (concertDate.getMonth() + 1)).slice(-2) + '/' + concertDate.getFullYear() + '</span> ' + 
                            '</time>' +
                            '<div class="ops-horaires">' + ('0' + concertDate.getHours()).slice(-2) + 'h' +  ('0' + concertDate.getMinutes()).slice(-2) + '</div>' +
                            '<h3>' + session.eventName + '</h3>' +
                            '<div class="ops-bottom-card">' + 
                                ticketingElement +
                            '</div>' +
                        '</div>'
                    );
                    
                }

            }
        );
    });
</script>
<#-- Script avant désactivation du nombre de place restante
var eventID = ${entry.eventId};

        // Recherche des sessions futures
        Liferay.Service(
            '/agenda.event/get-sessions',
            {
                eventID: eventID
            },
            function(json) {

                for(var i = 0; i < json.length; i++) {
                    var session = json[i];

                    // Date de concert
                    var concertDate = new Date(Date.parse(session.sessionDate));

                    // Elements
                    var cssClass = "ops-item-concert-complet";
                    var nbSeatElement = "<@liferay_ui.message key='eu.ops.complete' />";
                    var ticketingElement = "<span><@liferay_ui.message key='eu.ops.buy.my.ticket' /></span>";

                    if (session.nbSeat > 0) {
                        cssClass = "";
                        nbSeatElement = '<strong>' + session.nbSeat + '</strong> <@liferay_ui.message key="eu.ops.seats.available" />';
                        ticketingElement = '<a href="' + session.link + '" target="_blank"><@liferay_ui.message key="eu.ops.buy.my.ticket" /></a>';
                    }
 
                    $('#ops-representations .slick-cards-slider').slick('slickAdd',
                        '<div class="ops-item ' + session.cssClass + '">' +
                            '<time datetime="' + concertDate.getFullYear() + '-' + ('0' + (concertDate.getMonth() + 1)).slice(-2) + '-' + concertDate.getDate() + '">' + 
                                '<span>' + ('0' + concertDate.getDate()).slice(-2) + '/' + ('0' + (concertDate.getMonth() + 1)).slice(-2) + '/' + concertDate.getFullYear() + '</span> ' + 
                            '</time>' +
                            '<div class="ops-horaires">' + ('0' + concertDate.getHours()).slice(-2) + 'h' +  ('0' + concertDate.getMinutes()).slice(-2) + '</div>' +
                            '<h3>' + session.eventName + '</h3>' +
                            '<div class="ops-bottom-card">' + 
                                ticketingElement +
                                '<div class="ops-places-dispo">' + nbSeatElement + '</div>' +
                            '</div>' +
                        '</div>'
                    );
                    
                }
            }
        );
        -->