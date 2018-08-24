<!-- DETAIL D'UN PROJET -->

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation des entités lies au projet -->
<#assign projectPlaces = entry.getPlacitPlaces() />
<#assign projectEvents = entry.getEvents() />
<#assign projectParticipations = entry.getParticipations() />

<!-- Initialisation des conteneurs de coordonnees GPS -->
<#assign projectPlaceMercators = [] />
<#assign eventPlaceMercators = [] />
<#assign participationPlaceMercators = [] />

<!-- Recuperation des coordonnées de chaque entité liées -->
<#list projectPlaces as place >
    <#assign projectPlaceMercators = projectPlaceMercators + [place.getMercators()] />
</#list>

<#list projectEvents as event >
    <#assign eventPlaceMercators = eventPlaceMercators + [event.getMercators()] />
</#list>

<#list projectParticipations as participation >
    <#list participation.getPlacitPlaces() as place >
        <#assign participationPlaceMercators = participationPlaceMercators + [place.getMercators()] />
    </#list>
    <#list participation.getEvents() as event >
        <#assign eventPlaceMercators = eventPlaceMercators + [event.getMercators()] />
    </#list>
</#list>


<div id="breadcrumb">
    <span>
        <span>
            <a href="${homeURL}">Accueil</a>
            <a href="${homeURL}projets">Les projets</a>
            <span class="breadcrumb_last">${entry.title}</span>
        </span>
    </span>
</div>

<div class="pro-bloc-bref">
    <h3>En bref</h3>
    <ul>
        <#if entry.budget?has_content>
            <li><span class="pro-euros">€</span> <strong>Budget : </strong>${entry.budget}</li>
        </#if>
        <#if entry.label?has_content>
            <li><span class="icon-ico-label"></span> <strong>Label : </strong>${entry.label}</li>
        </#if>
        <#if entry.duration?has_content>
            <li><span class="icon-ico-time"></span> <strong>Durée : </strong>${entry.duration}</li>
        </#if>
        <#if entry.partners?has_content>
            <li><span class="icon-ico-partenaire"></span> <strong>Les partenaires : </strong>${entry.partners}</li>
        </#if>
    </ul>
 </div>

<!-- Fiche de l'entité -->
<aside class="col-sm-4-to-move">

    <!-- Bloc : map -->
    <div class="bloc-iframe leaflet-map" id="mapid" ></div>

    <!-- Bloc : Compteur de suivi -->
    <div class="pro-compteur">
        <span class="pro-compt">${entry.getNbFollowerLabel()}</span>
        <p>Citoyens(nes) suivent ce projet</p>
        <a href="#Suivre" class="pro-btn-action" 
            data-projectid="${entry.projectId}" 
            data-groupid="${entry.groupId}"
            title="Suivre ce projet">
            Suivre ce projet
        </a>
    </div>

    <!-- Bloc : A venir -->
    <div class="pro-event-comming">
        <a href="#pro-link-participation" title="Vers les participations de la page"><strong>${entry.getParticipations()?size}</strong> Participation(s) en cours</a>
        <a href="#pro-link-evenement" title="Vers les événements de la page"><strong>${entry.getEvents()?size}</strong> Évènement(s) à venir</a>
    </div> 

    <!-- Bloc : entités liées -->
    <div class="pro-event-comming">
        <a href="#pro-link-participation" title="Vers les participations de la page"><strong>${projectParticipations?size}</strong> Participation(s) en cours</a>
        <a href="#pro-link-evenement" title="Vers les événements de la page"><strong>${projectEvents?size}</strong> Évènement(s) à venir</a>
    </div>

    <!-- Bloc : contact -->
    <div class="pro-contact">
        <h4>Contact</h4>
        <p>
            <#if entry.contactName?has_content>
                <strong> ${entry.contactName}</strong><br>
                <#if entry.contactLine1?has_content> ${entry.contactLine1} </#if>
                <#if entry.contactLine2?has_content> <br>${entry.contactLine2} </#if>
            <#else>
                <strong>Aucun contact renseigné pour le moment </strong><br>
            </#if>
        </p>
        <a href="tel:${entry.contactPhoneNumber}" title="Numéro de téléphone : ${entry.contactPhoneNumber}">${entry.contactPhoneNumber}</a>
    </div>

</aside>
 
 <style>
 .pro-page-detail.pro-page-detail-projet section>.pro-wrapper{
     left : 0px;
 }
 
 .pro-page-detail.pro-page-detail-projet aside{
     margin-top : 124px;
 }
 .pro-page-detail.pro-page-detail-projet .pro-wrapper .portlet-body>* {
    margin: 0;
    padding: 7px 0;
 }
 .pro-btn-action.active:after{
    opacity: 0;
 }
 </style>
 
 <script>
    var projectPlaceMercators = [
        <#list projectPlaceMercators as placeMercators>
            <#if placeMercators?size == 2>
                [${placeMercators[1]}, ${placeMercators[0]}],
            </#if>
        </#list>
    ];

    var participationPlaceMercators = [
        <#list participationPlaceMercators as placeMercators>
            <#if placeMercators?size == 2>
                [${placeMercators[1]}, ${placeMercators[0]}],
            </#if>
        </#list>
    ];

    var eventPlaceMercators = [
        <#list eventPlaceMercators as placeMercators>
            <#if placeMercators?size == 2>
                [${placeMercators[1]}, ${placeMercators[0]}],
            </#if>
        </#list>
    ];

    $(document).ready(function() {

        // Déplacement du bloc de la fiche entité
        $(".col-sm-4-to-move").contents().appendTo(".col-sm-4");
        $(".portlet-content>.portlet-title-text").hide();

        var projectid = ${entry.projectId};

        // Recherche si l'utilisateur suit le projet
        Liferay.Service(
            '/project.projectfollowed/is-follower',
            {
                projectId: projectid
            },
            function(obj) {
                // En cas de succès, on effectue la modification des éléments visuels
                // selon la réponse et le type de l'élément
                if (obj.hasOwnProperty('success')) {
                    if (obj['success'] == 'true') {
                        $("[href='#Suivre']").toggleClass('active');
                        $("[href='#Suivre']").text("Projet suivi");
                    }
                }
            }
        );

        // Gestion de la carte interactive
        // Notes : voir dans le theme placit "override/custom.js"

        //Création de la carte au centre de strasbourg
        leafletMap = getLeafletMap()

        // Définition des marqueurs
        var projectMarkerIcon = getMarkerIcon('project');
        var participationMarkerIcon = getMarkerIcon('participation');
        var eventMarkerIcon =  getMarkerIcon('event');

        // Ajout des marqueurs sur la map
        var projectMarkers = [];
        var participationMarkers = [];
        var eventMarkers = [];

        for(var i= 0; i < projectPlaceMercators.length; i++) {
            projectMarkers.push(
                L.marker(projectPlaceMercators[i], {icon: projectMarkerIcon}).addTo(leafletMap)
            );
        }
        for(var i= 0; i < participationPlaceMercators.length; i++) {
            participationMarkers.push(
                L.marker(participationPlaceMercators[i], {icon: participationMarkerIcon}).addTo(leafletMap)
            );
        }
        for(var i= 0; i < eventPlaceMercators.length; i++) {
            eventMarkers.push(
                L.marker(eventPlaceMercators[i], {icon: eventMarkerIcon}).addTo(leafletMap)
            );
        }
    });
</script>