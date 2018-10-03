<!-- DETAIL D'UN PROJET -->

<#-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#-- Récupération de l'ID de l'utilisateur -->
<#assign userID = request.session.getAttribute("publik_internal_id")!"" />

<#-- Recuperation des entités lies au projet -->
<#assign projectPlaces = entry.getPlacitPlaces() />
<#assign projectEvents = entry.getEvents() />
<#assign projectParticipations = entry.getParticipations() />

<#-- Initialisation des conteneurs de vignettes -->
<#assign projectJSON = entry.toJSON(userID) />
<#assign projectPlacesMercators = [] />
<#assign eventsJSON = [] />
<#assign participationsJSON = [] />

<#-- Recuperation des données JSON de chaque entité liées -->
<#list projectEvents as event >
    <#assign eventsJSON = eventsJSON + [event.toJSON(userID)] />
</#list>

<#list projectParticipations as participation >
    <#assign participationsJSON = participationsJSON + [participation.toJSON(themeDisplay)] />
    <#list participation.getEvents() as event >
         <#assign eventsJSON = eventsJSON + [event.toJSON(userID)] />
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
     
    .col-sm-4 {
    	z-index : 50;
    }
</style>
 
<script>
    // Récupération des entités en JSON à afficher sur la map et ajout des données dynamiques manquantes
    var projectJSON = ${projectJSON};
    projectJSON.link = '${homeURL}' + projectJSON.detailURL;

    var eventsJSON = [
        <#list eventsJSON as eventJSON>
            ${eventJSON},
        </#list>
    ];

    var participationsJSON = [
        <#list participationsJSON as participationJSON>
            ${participationJSON},
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
        leafletMap = getLeafletMap();

        // Définition des marqueurs
        var projectMarkerIcon = getMarkerIcon('project');
        var participationMarkerIcon = getMarkerIcon('participation');
        var eventMarkerIcon =  getMarkerIcon('event');

        // Ajout des marqueurs sur la map
        var projectMarkers = [];
        var participationMarkers = [];
        var eventMarkers = [];
        
        // Centre la carte sur les pins
        var bounds = [];
        var marker;

        for(var i= 0; i < projectJSON.placitPlaces.length; i++) {
            marker = getProjectMarker(
                projectJSON,
                [projectJSON.placitPlaces[i].mercatorY, projectJSON.placitPlaces[i].mercatorX]
            );
            // Ajout des coordonnées du marker dans le bounds
            bounds.push(marker.getLatLng());
            // Ajout du marker dans la map
            projectMarkers.push(marker.addTo(leafletMap));
        }

        for(var i= 0; i < eventsJSON.length; i++) {
            // notes : la participation à l'événement à été ajoutée dans le tableau lors du parcours
            // des évenements, d'où le [0] pour avoir le JSON et le [1] pour la participation à l'évenements
            var eventJSON = eventsJSON[i];
            // Ajout du lien vers le détail (effectué ici pour éviter le double parcours)
            eventJSON.link = '${homeURL}detail-evenement/-/entity/id/' +  eventJSON.id;

            marker = getEventMarker(eventJSON);
            bounds.push(marker.getLatLng());
            eventMarkers.push(marker.addTo(leafletMap));
        }

        for(var i= 0; i < participationsJSON.length; i++) {
            var participationJSON = participationsJSON[i];
            participationJSON.link = '${homeURL}detail-participation/-/entity/id/' + participationJSON.participationId;

            for(var j= 0; j < participationJSON.placitPlaces.length; j++) {
                marker = getParticipationMarker(
                    participationJSON,
                    [participationJSON.placitPlaces[j].mercatorY, participationJSON.placitPlaces[j].mercatorX]
                );
                // Ajout des coordonnées du marker dans le bounds
                bounds.push(marker.getLatLng());
                // Ajout du marker dans la map
                participationMarkers.push(marker.addTo(leafletMap));
            }
        }
            
        leafletMap.fitBounds(bounds);
    });
</script>