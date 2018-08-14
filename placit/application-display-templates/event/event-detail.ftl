<!-- DETAIL D'UN EVENEMENT -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation des coordonnées GPS -->
<#assign eventPlaceMercatorX = 0 />
<#assign eventPlaceMercatorY = 0 />

<#assign eventPlaceMercators = entry.getMercators() />

<#if eventPlaceMercators?size == 2 >
    <#assign eventPlaceMercatorX = eventPlaceMercators[0] />
    <#assign eventPlaceMercatorY = eventPlaceMercators[1] />
</#if>

<div class="pro-page-detail">

    <div class="container">

        <div class="col-lg-11 col-lg-offset-1">

            <article>
                <header>
                    <#if entry.firstStartDate?has_content>
                        <span class="pro-time">Début le<time datetime="2018-01-10"> ${entry.firstStartDate?string("dd MMMM yyyy à hh'H'mm")}</time></span>
                    </#if>
                    <p>À : 
                        <#if entry.getPlaceAlias(locale)?has_content>${entry.getPlaceAlias(locale)},</#if>
                        <#if entry.getPlaceAddress(locale)?has_content>${entry.getPlaceAddress(locale)},</#if>
                        <#if entry.getPlaceZipCode()?has_content>${entry.getPlaceZipCode()}</#if>
                        <#if entry.getPlaceCity(locale)?has_content>${entry.getPlaceCity(locale)}</#if>
                    </p>
                    <h1>>${entry.getTitle(locale)}</h1>
                    <div class="pro-meta">
                        <#if entry.getTerritoryLabel(locale)?has_content>
                            <span>${entry.getTerritoryLabel(locale)}</span>
                        </#if>
                        <#if entry.getThemeLabel(locale)?has_content>
                            <span>${entry.getThemeLabel(locale)}</span>
                        </#if>
                        <span>${entry.getNbApprovedComments()} commentaire(s)</span>
                    </div>

                    <div id="breadcrumb">
                        <span>
                            <span>
                                <a href="${homeURL}">Accueil</a>
                                <a href="${homeURL}agenda">Agenda</a>
                                <span class="breadcrumb_last">${entry.getTitle(locale)}</span>
                            </span>
                        </span>
                    </div>
                </header>


                <div class="row pro-container-detail-event">

                    <div class="col-sm-8">
                        <div class="pro-main-img">
                            <figure role="group">
                                <#if entry.imageURL?has_content>
                                    <img src='${entry.imageURL}' alt="Image agenda" width="880" height="593" class="fit-cover" alt="${entry.getTitle(locale)}"/>
                                </#if>
                            </figure>
                        </div>
                        <div class="row pro-bloc pro-bloc-texte">
                            ${entry.getDescription(locale)}
                        </div>

                        <div class="row pro-small-detail-bloc">
                            <#if entry.hasAnyAccessForDisabled() || entry.getAccessForDisabled(locale)?has_content >
                                <div class="col-sm-6">
                                    <h4>Services aux handicapés</h4>
                                    <ul>
                                        <#if entry.accessForBlind>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-oeil"></span><span class="tooltiptext">Handicap oeil</span></li>
                                        </#if>
                                        <#if entry.accessForDeficient>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-visage"></span><span class="tooltiptext">Handicap visage</span></li>
                                        </#if>
                                        <#if entry.accessForDeaf>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-oreille"></span><span class="tooltiptext">Handicap oreille</span></li>
                                        </#if>    
                                        <#if entry.accessForWheelchair>
                                            <li class="pro-tooltip"><span class="icon-ico-handicap-moteur"></span><span class="tooltiptext">Handicap moteur</span></li>
                                        </#if>
                                    </ul>
                                </div>
                            </#if>
                            <#if entry.getAccess(locale)?has_content >    
                                <div class="col-sm-6">
                                    <h4>Accès</h4>
                                    <p>${entry.getAccess(locale)}</p>
                                </div>
                            </#if>
                        </div>

                    </div>

                    <!-- Fiche de l'entité -->
                    <aside class="col-sm-4">

                        <!-- Bloc : map -->
                        <#if eventPlaceMercators?size == 2 >
                            <div class="bloc-iframe leaflet-map" id="mapid" ></div>
                        </#if>

                        <!-- Bloc : compteur de participants -->
                        <div class="pro-compteur">
                            <span class="pro-compt">${entry.getNbEventParticipationsLabel()}</span>
                            <p>Citoyens(nes) participent à l’événement</p>
                            <#if entry.isFinished() >
                                <a class="pro-btn-action">
                                    Événement terminé
                                </a>
                            <#elseif request.session.getAttribute("has_pact_signed")!false >
                                <a href="#Participe" class="pro-btn-action"
                                    data-eventid="${entry.eventId}"
                                    data-groupid="${entry.groupId}"
                                    title="Je participe">
                                    Je participe
                                </a>
                            <#else>
                                <a class="pro-btn-action" name="#Pact-sign">
                                    Je participe
                                </a>
                            </#if>
                        </div>

                        <!-- Bloc : contact -->
                        <div class="pro-contact">
                            <h4>Contact</h4>
                            <p>
                                <#if entry.promoter?has_content><strong>${entry.promoter}</strong><br></#if>
                                <#if entry.email?has_content>${entry.email}<br></#if>
                                <#if entry.websiteURL?has_content>${entry.websiteURL}</#if>
                            </p>
                            <a href="tel:${entry.phone}" title="Numéro de téléphone : ${entry.phone}">${entry.phone}</a>
                        </div>

                        <!-- Bloc : reservation -->
                        <#if entry.bookingURL?has_content>
                            <div class="pro-ticket">
                                <#if entry.getBookingDescription(locale)?has_content>${entry.getBookingDescription(locale)}</#if>
                                <a href="${entry.bookingURL}" target="_blank" class="pro-btn-ticket" title="Réservation d'un billet">Reserver un billet</a>
                            </div>
                        </#if>

                    </aside>
                </div>
            </article>

        </div>
    </div>

    <@liferay_portlet["runtime"]
    portletProviderAction=portletProviderAction.VIEW
    portletName="com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet" 
    instanceId="event${entry.eventId}"/>

</div>

<script>

    var eventMercatorX = ${eventPlaceMercatorX};
    var eventMercatorY = ${eventPlaceMercatorY};

    $(document).ready(function() {

        // Gestion de la carte interactive
        // Notes : voir dans le theme placit "override/custom.js"
        if (eventMercatorX && eventMercatorX.length != 0) {

            //Création de la carte au centre de strasbourg
            leafletMap = getLeafletMap()

            // Définition des marqueurs
            var eventIcon = getMarkerIcon('event');

            // Ajout du marqueur sur la map
            var marker = L.marker([eventMercatorY, eventMercatorX], {icon: eventIcon}).addTo(leafletMap);
        }

    });

    // Gestion de la participation ou non d'un utilisateur à un événement
    $(document).ready(function() {
        $('.pro-slider-event>.container>div').each(function() {
            $(this).addClass("col-lg-10 col-lg-offset-1");
        });

        var eventid = ${entry.eventId};

        // Recherche si l'utilisateur participe a l'evenement
        Liferay.Service(
            '/agenda.eventparticipation/is-user-participates',
            {
                eventId: eventid
            },
            function(obj) {
                // En cas de succès, on effectue la modification des éléments visuels
                // selon la réponse et le type de l'élément
                if (obj.hasOwnProperty('success')) {
                    if (obj['success'] == 'true') {
                        $("[href='#Participe']").toggleClass('active');
                    }
                }
            }
        );

    });

</script>