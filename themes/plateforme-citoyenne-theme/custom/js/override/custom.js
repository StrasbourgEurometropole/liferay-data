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
    e.preventDefault();
    e.stopPropagation();
    $("#myModal").modal();
});

/*
* Gestion des suivi de projets
*/
$(document).on("click", "[href='#Suivre'], span[name^='#Suivre']", function(e) {

    e.preventDefault();
    e.stopPropagation();

    // Sauvegarde de l'élément
    var element = $(this);
    var elementType = $(this).attr('href') === '#Suivre' ? 'a' : 'span';

    // Récupération des attributs du like
    var projectid = $(this).data("projectid");
    var groupid = $(this).data("groupid") ? $(this).data("groupid") : 0;

    // Met à jour le compteur de followers
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
        '/project.projectfollowed/add-follower-link',
        {
            projectId: projectid,
            groupId: groupid
        },
        function(obj) {
            // En cas de succès, on effectue la modification des éléments visuels
            // selon la réponse et le type de l'élément
            if (obj.hasOwnProperty('success')) {
                switch(obj['success']) {
                    case "follower added":
                        element.toggleClass('active');
                        element.text("Projet suivi");
                        if (elementType === "a")
                            updateCounter(parseInt($('.pro-compt').text()) + 1);
                        else
                            element.siblings().first().find('strong').text(+parseInt(element.siblings().first().text()) + 1);
                        break;
                    case "follower deleted":
                        element.toggleClass('active');
                        element.text("Suivre ce projet");
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
                } else {
                    // Autre erreur
                    alert('Une erreur est survenue.');
                }
            }
        }
    );
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
* Retourne le marqueurs de leaflet d'un projet sur la carte intéractive
*/
function getProjectMarker(project, mercators) {


    var projectMarkerIcon = getMarkerIcon("project");
    var marker = L.marker(mercators, {icon: projectMarkerIcon});

    return marker;

}

/**
* Retourne le marqueurs de leaflet d'une particiaption sur la carte intéractive
*/
function getParticipationMarker(participation, mercators) {

    var participationMarkerIcon = getMarkerIcon("participation");
    var marker = L.marker(mercators, {icon: participationMarkerIcon});

    var footerContent = '';

    // Adaptation du footer de la vignette selon le statut de la participation
    switch(participation.statusCode) {
        case "soon_arrived" :
            footerContent = 
                '<div class="pro-footer-participation pro-participation-soon">' + 
                    '<div class="pro-avis"><span class="pro-like">' + participation.nbLikes + '</span><span class="pro-dislike">' + participation.nbDislikes + '</span></div>' + 
                    '<p>Bientôt disponible</p>' +
                '</div>';
            break;
        case "finished" :
            footerContent = 
                '<div class="pro-footer-participation pro-participation-deadline">' + 
                    '<div class="pro-avis"><span class="pro-like">' + participation.nbLikes + '</span><span class="pro-dislike">' + participation.nbDislikes + '</span></div>' + 
                    '<p>Participation terminée, merci de votre participation</p>' +
                '</div>';
            break;
        case "new" :
        case "in_progress" :
        case "soon_finished" :
            footerContent = 
                '<div class="pro-footer-participation">' + 
                    '<span class="pro-form-style">Réagissez...</span>' +
                '</div>';
            break;
    }

    // Création du Hack CSS permettant la colorisation de la vignette selon le type de participation
    var colorHack = 
        '<style style="display: none" >' +
            '.type-color-hexa-' + participation.typeColor + '>div:before {' +
                'background:#'+ participation.typeColor + ' !important;' +
            '}' +
        '</style>';

    marker.bindPopup(
        '<div class="pro-vignette-map-inte">' +
            '<a href="' + participation.link + '" class="item pro-bloc-card-participation type-color-hexa-' + participation.typeColor + '" data-linkall="a">' +
            '<div>' +
                '<div class="pro-header-participation">' + 
                    '<figure><img src="' + participation.imageURL + '" width="40" height="40" alt="Arrière plan page standard"/></figure>' +
                    '<p>Participation publiée par :</p><p><strong>' + participation.author + '</strong></p>' +
                    '<div class="pro-info-top-right"><span class="pro-encart-theme" style="background : #' + participation.typeColor + '">' + participation.typeLabel + '</span></div>' +
                '</div>' +
                '<div class="pro-content-participation">' +
                    '<div class="pro-meta"><span>' + participation.districtsLabel + '</span><span>' + participation.thematicsLabel + '</span>' +
                    '<span>' + participation.statusLabel + '</span><span>' + participation.projectName + '</span></div>' +
                    '<h3>' + participation.title + '</h3>' +
                    '<span class="pro-time">Publiée le <time datetime="2018-01-10">' + participation.createDate + '</time>' +
                    ' / <span class="pro-duree">' + participation.statusDetailLabel + '</span></span></div>' +
                    footerContent +
            '</div></a>' + 
        '</div>' + 
        colorHack
        ,{maxHeight: 310, minWidth: 480, maxWidth: 654}
    );

    return marker;

}

/**
* Retourne le marqueurs de leaflet d'un événement sur la carte intéractive
*/
function getEventMarker(event) {
    //mercators, link, publishDate, place, title, isUserPart, nbPart) {

    var eventMarkerIcon = getMarkerIcon("event");
    var marker = L.marker([event.mercatorY, event.mercatorX], {icon: eventMarkerIcon});

    var activePart = event.isUserPart ? "active" : "";

    marker.bindPopup(
        '<div class="pro-vignette-map-inte">' + 
            '<a href="' + event.link + '" title="lien de la page" class="pro-bloc-card-event"><div>' +
                '<div class="pro-header-event">' +
                    '<span class="pro-ico"><span class="icon-ico-conference"></span></span>' +
                    '<span class="pro-time">Le <time datetime="2018-01-10">' + event.firstDate + '</time></span>' +
                    '<p>À : ' + event.completeAddress + '</p>' +
                    '<h3>' + event.title.fr_FR + '</h3>' +
                '</div>' +
                '<div class="pro-footer-event">' +
                    '<span class="pro-btn-action ' + activePart + '">Je participe</span>' +
                    '<span class="pro-number"><strong>' + event.nbPart + '</strong> Participant(s)</span>' +
                '</div>' +
            '</div></a>' +
        '</div>'
        ,{maxHeight: 280, maxWidth: 654}
    );

    return marker;
}

/**
* Retourne le marqueurs de leaflet d'une initiative sur la carte intéractive
*/
function getInitiativePopUp(mercators, link) {

    var initiativeMarkerIcon = getMarkerIcon("initiative");
    var marker = L.marker(mercators, {icon: initiativeMarkerIcon});

    return marker;

}

/**
* Retourne le marqueurs de leaflet d'une pétition sur la carte intéractive
*/
function getPetitionMarker(mercators, link, author, title, place, publishDate, durationLabel, progress, nbSub, nbGoal) {

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
