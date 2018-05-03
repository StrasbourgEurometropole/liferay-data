var bounds;
var map;


th_maps.addThemes('default', [
    {
        "featureType": "all",
        "elementType": "labels.text.fill",
        "stylers": [
            {
                "saturation": 36
            },
            {
                "color": "#000000"
            },
            {
                "lightness": 40
            }
        ]
    },
    {
        "featureType": "all",
        "elementType": "labels.text.stroke",
        "stylers": [
            {
                "visibility": "on"
            },
            {
                "color": "#ffffff"
            },
            {
                "lightness": 16
            }
        ]
    },
    {
        "featureType": "all",
        "elementType": "labels.icon",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#fefefe"
            },
            {
                "lightness": 20
            }
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#fefefe"
            },
            {
                "lightness": 17
            },
            {
                "weight": 1.2
            }
        ]
    },
    {
        "featureType": "landscape",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#f5f5f5"
            },
            {
                "lightness": 20
            }
        ]
    },
    {
        "featureType": "poi",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#f5f5f5"
            },
            {
                "lightness": 21
            }
        ]
    },
    {
        "featureType": "poi.park",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#dedede"
            },
            {
                "lightness": 21
            }
        ]
    },
    {
        "featureType": "road",
        "elementType": "all",
        "stylers": [
            {
                "visibility": "on"
            },
            {
                "hue": "#ffed00"
            }
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.fill",
        "stylers": [
            {
                "color": "#ffffff"
            },
            {
                "lightness": 17
            }
        ]
    },
    {
        "featureType": "road.highway",
        "elementType": "geometry.stroke",
        "stylers": [
            {
                "color": "#ffffff"
            },
            {
                "lightness": 29
            },
            {
                "weight": 0.2
            }
        ]
    },
    {
        "featureType": "road.arterial",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#ffffff"
            },
            {
                "lightness": 18
            }
        ]
    },
    {
        "featureType": "road.local",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#ffffff"
            },
            {
                "lightness": 16
            }
        ]
    },
    {
        "featureType": "transit",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#f2f2f2"
            },
            {
                "lightness": 19
            }
        ]
    },
    {
        "featureType": "water",
        "elementType": "geometry",
        "stylers": [
            {
                "color": "#e9e9e9"
            },
            {
                "lightness": 17
            }
        ]
    }
]);


var GetPosition = document.getElementById('cible');

var zoomInButton = document.getElementById('pro-plus');
var zoomOutButton = document.getElementById('pro-moins');


/* MAP POUR LE LISTING DE LA PAGE EVENEMENT */
function callbackMapListingEvent(macarte) {

    var bounds = new google.maps.LatLngBounds();

    $('.pro-bloc-listing-event > a').each(function () {
        var geo = {lat: $(this).data('lat'), lng: $(this).data('lng')};
        marker = th_maps.createMarker(macarte, geo, 'event');
        bounds.extend(geo);

        th_maps.createInfoWindow('<a target="_blank" href="' + $(this).attr('href') + '" id="map-inte-container">' +
            '<div class="map-inte-content">' +
            '<div class="map-inte-header"><span class="pro-time">Publiée le <time datetime="2018-01-10">' + $('time', this).text() + '</time></span><p>' + $('p', this).text() + '</p></div>' +
            '<div class="map-inte-content-text"><h3>' + $('h3', this).text() + '</h3>' +
            '<span class="pro-btn-yellow">En savoir plus</span>' +
            '</div></div></a>', marker, 260);
    });

    th_maps.defaultOptions.zoomControlOptions = google.maps.ControlPosition.RIGHT_TOP;
    th_maps.defaultOptions.mapTypeId = google.maps.MapTypeId.ROADMAP;

}




/* MAP POUR LES PAGES STANDARDS ET PROJET */
function callbackCartePage(macarte){
    // ---------  Google map Zoom Button  --------- //
    google.maps.event.addDomListener(zoomInButton, 'click', function (e) {
        e.preventDefault();
        macarte.setZoom(macarte.getZoom() + 1);
    });

    google.maps.event.addDomListener(zoomOutButton, 'click', function (e) {
        e.preventDefault();
        macarte.setZoom(macarte.getZoom() - 1);
    });
}




/* MAP POUR LA CARTE INTERACTIVE */
function callbackCarteInteractive(macarte){

    var bounds = new google.maps.LatLngBounds();

    // ---------  Google map Zoom Button  --------- //
    google.maps.event.addDomListener(zoomInButton, 'click', function (e) {
        e.preventDefault();
        macarte.setZoom(macarte.getZoom() + 1);
    });

    google.maps.event.addDomListener(zoomOutButton, 'click', function (e) {
        e.preventDefault();
        macarte.setZoom(macarte.getZoom() - 1);
    });

    marker1 = th_maps.createMarker(macarte,{lat:48.5891137,lng:7.7514801},'map','projet');
    marker2 = th_maps.createMarker(macarte,{lat:48.5991137,lng:7.7414801},'map','projet');
    marker3 = th_maps.createMarker(macarte,{lat:48.5791137,lng:7.7314801},'map','projet');
    marker4 = th_maps.createMarker(macarte,{lat:48.5775591,lng:7.7606211},'map','projet');
    marker5 = th_maps.createMarker(macarte,{lat:48.5922362,lng:7.7282629},'map','projet');


    contentParticipation = th_maps.createInfoWindow('<div class="pro-vignette-map-inte"><a href="detail-participation.php" title="lien de la page" class="pro-bloc-card-participation' +
        ' pro-theme-concertation"><div>' +
        '<div class="pro-header-participation"><figure><img src="assets/images/medias/comm-sylvie.jpg" width="40" height="40" alt="Arrière plan page standard"/></figure>' +
        '<p>Participation publiée par :</p><p><strong>Ville de Strasbourg</strong></p></div>' +
        '<div class="pro-content-participation"><div class="pro-meta"><span>Quartier</span><span>Thématique</span><span>Type : Information</span><span>Statut</span><span>Nom du projet</span></div>' +
        '<h3>Titre de l’Évènement<br>Sur deux lignes</h3>' +
        '<span class="pro-time">Publiée le <time datetime="2018-01-10">10/04/2018</time> / <span class="pro-duree">Fin dans 11 jours</span></span></div>' +
        '<div class="pro-footer-participation pro-participation-deadline"><div class="pro-avis"><span class="pro-like">1808</span>' +
        '<span class="pro-dislike">404</span></div><p>Participation terminée, merci de votre participation</p>' +
        '</div></div></a></div>',marker1,247);


    contentEvent = th_maps.createInfoWindow('<div class="pro-vignette-map-inte"><a href="page.php" title="lien de la page" class="pro-bloc-card-event"><div>' +
        '<div class="pro-header-event"><span class="pro-ico"><span class="icon-ico-conference"></span></span><span class="pro-time">Publiée le <time datetime="2018-01-10">10 janvier' +
        ' 2018</time></span>' +
        '<p>À : Espace des associations de Strasbourg au centre ville</p><h3>Titre de l’Évènement<br>Sur deux lignes</h3></div>' +
        '<div class="pro-footer-event"><span class="pro-btn-action active">Je participe</span><span class="pro-number"><strong>4537</strong> Participant(s)</span></div>' +
        '</div></a></div>',marker2,247);


    contentArticle = th_maps.createInfoWindow('<div class="pro-vignette-map-inte"><a href="/detail-article.php" title="Lien vers la page (nom de la page)" class="pro-bloc-actu">' +
        '<div class="img"><figure><img src="assets/images/medias/hp-projet-1.jpg" alt="Image agenda" width="360" height="174" class="fit-cover"/></figure></div>' +
        '<div class="content"><span class="publication">Publiée le 04 décembre 2017</span><h3>Titre de l\'actualité<br>sur deux lignes</h3><p>Lorem ipsum dolor sit amet, consectetur...</p><span' +
        ' class="link">Lire la suite</span></div>' +
        '</a></div>',marker3,247);


    contentParticipation2 = th_maps.createInfoWindow('<div class="pro-vignette-map-inte"><a href="detail-participation.php" class="item pro-bloc-card-participation pro-theme-information"' +
        ' data-linkall="a">' +
        '<div><div class="pro-header-participation"><figure><img src="assets/images/medias/comm-mathilde.jpg" width="40" height="40" alt="Arrière plan page standard"/></figure>' +
        '<p>Concertation publiée par :</p><p><strong>Ville de Strasbourg</strong></p></div>' +
        '<div class="pro-content-participation"><h3>Titre de l’Évènement<br>Sur deux lignes</h3><span class="pro-time">Publiée le' +
        ' <time datetime="2018-01-10">10/04/2018</time> / <span class="pro-duree">Fin dans 11 jours</span></span></div>' +
        '<div class="pro-footer-participation"><span class="pro-form-style">Réagissez...</span></div>' +
        '</div></a></div>',marker4,247);


    contentVideo = th_maps.createInfoWindow('<div class="pro-vignette-map-inte"><a href="detail-video.php" class="pro-card-video">' +
        '<div class="pro-header"><figure class="fit-cover"><img alt="" width="280" height="175" src="./assets/images/medias/homepage-instance.jpg"></figure><span' +
        ' class="icon-ico-lecteur"></span></div>' +
        '<div class="pro-meta-avis"><h3>Titre de la vidéo<br>sur deux lignes</h3>'+
        '<div class="pro-avis"><span class="pro-like">0</span><span class="pro-dislike">0</span></div><span class="pro-view">125 vues</span>'+
        '</div></a></div>',marker5,247);



    bounds.extend(marker1.position);
    bounds.extend(marker2.position);
    bounds.extend(marker3.position);
    bounds.extend(marker4.position);
    bounds.extend(marker5.position);
    macarte.fitBounds(bounds);
}


th_maps.onLoad(function () {   


    th_maps.addMarkerIcon('event', {
        url: './assets/images/logos/ico-marker-agenda-2x.png',
        scaledSize: new google.maps.Size(30, 36)
    });

    th_maps.addMarkerIcon('map', {
        url: './assets/images/logos/ico-marker-map-inte-2x.png',
        scaledSize: new google.maps.Size(30, 36)
    });

    th_maps.defaultOptions.zoomControlOptions = google.maps.ControlPosition.LEFT_CENTER;
    th_maps.defaultOptions.mapTypeId = google.maps.MapTypeId.ROADMAP;
});



/* FILTRE POUR ENLEVER LES ELEMENTS PAR DEFAUT SUR LA GOOGLE MAPS SUR LES PAGES DE LISTING */
function filterMapListing(options) {
    options.mapTypeControl = false;
    options.streetViewControl = false;
    options.zoomControl = true;

    options.zoomControlOptions = {position:google.maps.ControlPosition.LEFT_TOP};
    options.mapTypeId = google.maps.MapTypeId.ROADMAP;

    return options;
}


/* FILTRE POUR ENLEVER LES ELEMENTS PAR DEFAUT SUR LA GOOGLE MAPS SUR LA PAGE DE DETAIL SIT ET AGENDA */
function filterMapDetail(options) {
    options.mapTypeControl = false;
    options.streetViewControl = false;
    options.zoomControl = false;
    return options;
}


function generateContentWindow(img, title) {
    var content = '<div id="map-inte-container">' +
        '<div class="map-inte-content">' +
        '<div class="map-inte-header"><figure><img src="' + img + '" width="260" height="160" alt="Titre image" class="fit-cover img-carte-inte" /></figure></div>' +
        '<div class="map-inte-content-text"><h2 class="title-map-inte">' + title + '</h2>' +
        '<a href="#" class="basic-link">En savoir plus</a>' +
        '</div></div></div>';
    return content;
}

function init_thMaps() {
    th_maps.init({
        defaultOptions: {
            zoom: 16,
            center: {lat: 48.5692059, lng: 7.6920547},
            disableDefaultUI: true,
            streetViewControl: false,
            zoomControl: true,
        }
    });
}

init_thMaps();
// document.addEventListener('scroll', init_thMaps);

$('#pro-btn-menu-map').click(function () {
    $('.pro-wrapper-facette-carte').toggleClass('show-menu-map');
    $('.pro-menu-carte-mobile').toggleClass('btn-map-active');
})