/*
* Gestion des likes/dislikes
*/
$(document).on("click", "[href$='-approuv'], [href$='like-pro']", function(e) {

    e.preventDefault();
    e.stopPropagation();

    // Sauvegarde de l'élément
    var element = $(this);

    // L'élément a t-il un affiche du compteur en texte ou en span ?
    var counterType = $(this).attr('href').endsWith('-approuv') ? "span" : "text" ;

    // Récupération des attributs du like
    var title = $(this).data("title");
    var isdislike = $(this).data("isdislike");
    var typeid = $(this).data("typeid");
    var entityid = $(this).data("entityid");
    var entitygroupid = $(this).data("entitygroupid") ? $(this).data("entitygroupid") : 0;

    // Appel au service remote Liferay
    Liferay.Service(
        '/like.like/add-like-link',
        {
            title: title,
            isDislike: isdislike,
            typeId: typeid,
            entityId: entityid,
            entityGroupId: entitygroupid
        },
        function(obj) {
            // En cas de succès, on effectue la modification des éléments visuels
            // selon la réponse et le type de l'élément
            if (obj.hasOwnProperty('success')) {
                switch(obj['success']) {
                    case "like added":
                        element.toggleClass('active');
                        if (counterType === "span") {
                            element.find('strong').text(+parseInt(element.text()) + 1);
                        } else {
                            element.text(+parseInt(element.text()) + 1);
                        }
                        break;
                    case "dislike added":
                        element.toggleClass('active');
                        if (counterType === "span") {
                            element.find('strong').text(+parseInt(element.text()) + 1);
                        } else {
                            element.text(+parseInt(element.text()) + 1);
                        }
                        break;
                    case "like mind changed added":
                        element.toggleClass('active');
                        if (counterType === "span") {
                            element.find('strong').text(+parseInt(element.text()) + 1);
                            element.siblings().first().find('strong').text(+parseInt(element.siblings().first().text()) - 1);
                        } else {
                            element.text(+parseInt(element.text()) + 1);
                            element.siblings().first().text(+parseInt(element.siblings().first().text()) - 1);
                        }
                        break;
                    case "dislike mind changed added":
                        element.toggleClass('active');
                        if (counterType === "span") {
                            element.find('strong').text(+parseInt(element.text()) + 1);
                            element.siblings().first().find('strong').text(+parseInt(element.siblings().first().text()) - 1);
                        } else {
                            element.text(+parseInt(element.text()) + 1);
                            element.siblings().first().text(+parseInt(element.siblings().first().text()) - 1);
                        }
                        break;
                    case "like deleted":
                        element.toggleClass('active');
                        if (counterType === "span") {
                            element.find('strong').text(+parseInt(element.text()) - 1);
                        } else {
                            element.text(+parseInt(element.text()) - 1);
                        }
                        break;
                    case "dislike deleted":
                        element.toggleClass('active');
                        if (counterType === "span") {
                            element.find('strong').text(+parseInt(element.text()) - 1);
                        } else {
                            element.text(+parseInt(element.text()) - 1);
                        }
                        break;
                }

            }
            // Sinon on affiche un message d'erreur
            else if (obj.hasOwnProperty('error')) {
                if (obj['error'] == 'notConnected') {
                    // Si l'utilisateur n'est pas connecté
                    e.preventDefault();
                    $("#myModal").modal();
                } else if (obj['error'] == 'isBanned') {
                    // Si l'utilisateur est banni
                    alert("Vous ne pouvez plus juger, veuillez contacter l'administrateur du site.");
                } else {
                    // Autre erreur
                    alert('Une erreur est survenue.');
                }
            }
        }
    );

});

/*
* Gestion des participation à un événement
*/
$(document).on("click", "[href='#Participe'], span[name^='#Participe']", function(e) {

    e.preventDefault();
    e.stopPropagation();

    // Sauvegarde de l'élément
    var element = $(this);
    var elementType = $(this).attr('href') === '#Participe' ? 'a' : 'span';

    // Récupération des attributs du like
    var eventid = $(this).data("eventid");
    var groupid = $(this).data("groupid") ? $(this).data("groupid") : 0;

    // Met à jour le compteur de participant
    function updateCounter(num) {
        var stringNum = num.toString();
        var nbDigits = stringNum.length;
        stringNum = "0".repeat(5 - nbDigits) + stringNum;
        for (i = 1; i <= 5; i++) {
            $('.pro-compt span:nth-child('+i+')').text(stringNum[i-1]);
        }
    }

    // Appel au service remote Liferay
    Liferay.Service(
        '/agenda.eventparticipation/add-event-participation-link',
        {
            eventId: eventid,
            groupId: groupid
        },
        function(obj) {
            // En cas de succès, on effectue la modification des éléments visuels
            // selon la réponse et le type de l'élément
            if (obj.hasOwnProperty('success')) {
                switch(obj['success']) {
                    case "participation added":
                        element.toggleClass('active');
                        if (elementType === "a")
                            updateCounter(parseInt($('.pro-compt').text()) + 1);
                        else
                            element.siblings().first().find('strong').text(+parseInt(element.siblings().first().text()) + 1);
                        break;
                    case "participation deleted":                                
                        element.toggleClass('active');
                        if (elementType === "a")
                            updateCounter(parseInt($('.pro-compt').text()) - 1);
                        else
                            element.siblings().first().find('strong').text(+parseInt(element.siblings().first().text()) - 1);
                        break;
                }
            }

            // Sinon on affiche un message d'erreur
            else if (obj.hasOwnProperty('error')) {
                if (obj['error'] == 'notConnected') {
                    // Si l'utilisateur n'est pas connecté
                    e.preventDefault();
                    $("#myModal").modal();
                } else if (obj['error'] == 'isBanned') {
                    // Si l'utilisateur est banni
                    alert("Vous ne pouvez plus participer, veuillez contacter l'administrateur du site.");
                } else {
                    // Autre erreur
                    alert('Une erreur est survenue.');
                }
            }
        }
    );
});


/*
* Demande de signature du pacte
*/
$(document).on("click", "[name='#Pact-sign']", function(e) {
    $("#myModal").modal();
});

/**
* Retourne une map Leaflet configurée pour la plateforme citoyenne
*/
function getLeafletMap() {

    //Création de la carte au centre de strasbourg
    var leafletMap = L.map('mapid', {
        // crs: L.CRS.EPSG4326, //Commenté car casse l'affichage de la carte
        center: [48.573, 7.752],
        maxBounds: [[48.42, 7.52], [48.72, 7.94]],
        minZoom: 13,
        zoom: 13,
        minZoom: 12,
        zoomControl: false,
        attributionControl: false
    });

    // Ajout de la couche couleur 'gct_fond_de_carte_couleur' à la carte
    var wmsLayer = L.tileLayer.wms('http://adict.strasbourg.eu/mapproxy/service?', {
        layers: 'gct_fond_de_carte_couleur'
    }).addTo(leafletMap);

    return leafletMap;
}


/**
* Retourne l'icone de marqueur selon le type de l'entité 
*/
function getMarkerIcon(entityType = "default") {

    switch (entityType) {
        case 'project':
            return new L.Icon({
                iconUrl: '/o/plateforme-citoyenne-theme/images/logos/ico-marker-projet.png',
                iconSize: [75, 95],
                iconAnchor: [37, 78],
                popupAnchor: [1, -78]
            });
        case 'participation':
            return new L.Icon({
                iconUrl: '/o/plateforme-citoyenne-theme/images/logos/ico-marker-participation.png',
                iconSize: [75, 95],
                iconAnchor: [37, 78],
                popupAnchor: [1, -78]
            });
        case 'event':
            return new L.Icon({
                iconUrl: '/o/plateforme-citoyenne-theme/images/logos/ico-marker-event.png',
                iconSize: [75, 95],
                iconAnchor: [37, 78],
                popupAnchor: [1, -78]
            });
        case 'petition':
            return new L.Icon({
                iconUrl: '/o/plateforme-citoyenne-theme/images/logos/ico-marker-petition.png',
                iconSize: [75, 95],
                iconAnchor: [37, 78],
                popupAnchor: [1, -78]
            });
        case 'initiative':
            return new L.Icon({
                iconUrl: '/o/plateforme-citoyenne-theme/images/logos/ico-marker-initiative.png',
                iconSize: [75, 95],
                iconAnchor: [37, 78],
                popupAnchor: [1, -78]
            });
        default:
            return new L.Icon({
                iconUrl: '/o/plateforme-citoyenne-theme/images/logos/ico-marker-map-inte-2x-v2.png',
                iconSize: [75, 95],
                iconAnchor: [37, 78],
                popupAnchor: [1, -78]
            });
    }

    return 

}


/**
* Retourne le marqueurs de leaflet sur le listing des événements
*/
function getEventListingMarker(mercators, link, publishDate, place, title) {
    var eventMarkerIcon = getMarkerIcon("event");
    var marker = L.marker(mercators, {icon: eventMarkerIcon})

    marker.bindPopup(
        '<a target="_blank" href="' + link + '" id="map-inte-container">' +
            '<div class="map-inte-content">' +
                '<div class="map-inte-header">' +
                    '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + publishDate + '</time></span>' + 
                    '<p>' + place + '</p>' + 
                '</div>' +
                '<div class="map-inte-content-text"><h3>' + title + '</h3>' +
                    '<span class="pro-btn-yellow">En savoir plus</span>' +
                '</div>' + 
            '</div> ' + 
        '</a>'
    );

    return marker;
}

/**
* Retourne le contenu des marqueurs de leaflet sur le listing des événements
*/
function getEventMarker(mercators, link, publishDate, place, title, isUserPart, nbPart) {
    var eventMarkerIcon = getMarkerIcon("event");
    var marker = L.marker(mercators, {icon: eventMarkerIcon})

    var activePart = isUserPart ? "active" : "";

    marker.bindPopup(
        '<div class="pro-vignette-map-inte">' + 
            '<a href="' + link + '" title="lien de la page" class="pro-bloc-card-event"><div>' +
                '<div class="pro-header-event">' +
                    '<span class="pro-ico"><span class="icon-ico-conference"></span></span>' +
                    '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + publishDate + '</time></span>' +
                    '<p>À : ' + place + '</p>' +
                    '<h3>' + title + '</h3>' +
                '</div>' +
                '<div class="pro-footer-event">' +
                    '<span class="pro-btn-action ' + activePart + '">Je participe</span>' +
                    '<span class="pro-number"><strong>' + nbPart + '</strong> Participant(s)</span>' +
                '</div>' +
            '</div></a>' +
        '</div>'
    );

    return marker;
}

/**
* Retourne le contenu des marqueurs de leaflet sur le listing des événements
*/
function getProjectMarker(link) {

    var projectMarkerIcon = getMarkerIcon("project");

    return '';

}

/**
* Retourne le contenu des marqueurs de leaflet sur le listing des événements
*/
function getParticipationPopUp(link) {

    var participationMarkerIcon = getMarkerIcon("participation");

    return '';

}

/**
* Retourne le contenu des marqueurs de leaflet sur le listing des événements
*/
function getInitiativePopUp(link) {

    var initiativeMarkerIcon = getMarkerIcon("initiative");

    return '';

}

/**
* Retourne le contenu des marqueurs de leaflet sur le listing des événements
*/
function getPetitionPopUp(mercators, link, author, title, place, publishDate, durationLabel, progress, nbSub, nbGoal) {

    var petitionMarkerIcon = getMarkerIcon("petition");
    var marker = L.marker(mercators, {icon: petitionMarkerIcon})

    marker.bindPopup(
        '<div class="item pro-bloc-card-petition"><a href="' + link + '">' + 
            '<div class="pro-header-petition">' +
                '<figure role="group"></figure> ' +
                '<p>Pétition publiée par :</p><p><strong>' + author + '</strong></p>' + 
            '</div>' +
            '<div class="pro-content-petition">' + 
                '<h3>' + title + '</h3><p>Pétition adressée à <u>' + place + '</u></p>' + 
                '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + publishDate + '</time> / ' +
                '<span class="pro-duree">' + durationLabel + '</span></span>' + 
            '</div> ' +
            '<div class="pro-footer-petition">' +
                '<div class="pro-progress-bar">' +
                    '<div class="pro-progress-container"><div style="width:' + progress +'%"></div>' + 
                '</div>' + 
                '<p class="pro-txt-progress"><strong>' + nbSub + '</strong> Signataire(s) sur ' + nbGoal + ' nécessaires</p> ' +
            '</div>' +
        '</div></a></div>'
    );

    return marker;

}
