var bounds;
var map;

// On initialise la Map 
function initMap() {
  bounds = new google.maps.LatLngBounds();
  var center = {lat: 48.584, lng: 7.747};
  map = new google.maps.Map(document.getElementById('mns-map'), {
    zoom: 16,
    center: center,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    styles: mapTheme,
    zoomControl: true,
    zoomControlOptions: {
        position: google.maps.ControlPosition.LEFT_BOTTOM
    }
  });

  // Appel de la fonction pour la création des Markers. 
  addMarker(map,48.590,7.738,"images/img-map.jpg","Place Kléber","Nom du marché de Noël","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore Lorem ipsum dolor sit amet, consecteturt, consec demui adipisicing elit, sed re et dolore Lorem ipsum dolor sit amet, conseeturt, consec demui adipisicindo eiusmod.");
  addMarker(map,48.580,7.738,"images/img-map.jpg","Cathédrale","Nom du marché de Noël","Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et" +
      " dolore Lorem ipsum dolor sit amet, consecteturt, consec demui adipisicing elit, sed re et dolore Lorem ipsum dolor sit amet, conseeturt, consec demui adipisicindo eiusmod.");
  
  // Exemple : cette fonction ne remplit pas tous les paramètres. Le Marker se créé bien sur la Map mais il ne dispose pas d'une pop-in. 
  addMarker(map,48.585,7.740,"images/img-map.jpg","Cathédrale","Nom du marché de Noël");

  map.fitBounds(bounds);
}


// La fonction addMarker permet la création d'un nouveau Marker sur la carte avec sa pop-in au click sur le marker. 
// Le paramètre map correspond au à la création de la map dans le DOM. Inutile de la changer. 
// Les paramètres latitude et longitude correspondent aux coordonnées du Marker. 
// Le paramètre img correspond à l'image dans la fenêtre.
// Le paramètre lieu correspond à l'endroit où se déroule le marché de Noël, présent en bas de l'image sur la pop-in. 
// Le paramètre title correpond au titre du marché de Noël dans la pop-in.
// Le paramètre desc correpond à la description du marché de Noël dans la pop-in.

function addMarker(map,latitude,longitude,img,lieu,title,desc) {

  var marker = new google.maps.Marker({
    position: {lat: latitude, lng: longitude},
    map: map,
    icon: image = { url: 'images/ico/ico-marker-map.png', scaledSize: new google.maps.Size(36, 45)}
  });

  // On affiche la Fenêtre seulement si tous les paramètres sont remplis.
  if (map && latitude && longitude && img && lieu && title && desc){

  // HTML de la fenêtre 
  var content = '<div id="mns-map-container">' +
  '<div class="mns-map-content">' +
  '<div class="mns-map-header"><img src="'+img+'" alt="image map" height="115" width="83">' +
  '<div class="mns-location"><span class="icon-ico-map-marker"></span><p>'+lieu+'</p></div></div>' +
  '<div class="mns-map-content-text"><span class="mns-title">'+title+'</span>' +
  '<p>'+desc+'</p></div>' +
  '</div>' +
  '<div class="mns-map-bottom-gradient"></div>' +
  '</div>';

  // Création de la fenêtre
  var infowindow = new google.maps.InfoWindow({
    content: content,
    maxWidth: 315
  });

  // Ouvrir la fenêtre
  google.maps.event.addListener(marker, 'click', function() {
    infowindow.open(map,marker);
  });

  // Fermer la Fenêtre 
  google.maps.event.addListener(map, 'click', function() {
    infowindow.close();
  });

  // Custom Window 
  google.maps.event.addListener(infowindow, 'domready', function() {

    var iwOuter = $('.gm-style-iw');
    var iwBackground = iwOuter.prev();

    // Removes background shadow DIV
    iwBackground.children(':nth-child(2)').css({'display' : 'none'});

    // Removes white background DIV
    iwBackground.children(':nth-child(4)').css({'display' : 'none'});

    // Déplacement de la Window 
    iwOuter.parent().parent().css({left: '11px'});

    iwOuter.parent().children(':nth-child(1)').css({'display' : 'none'});

    // Moves the shadow of the arrow 76px to the left margin.
    iwBackground.children(':nth-child(1)').attr('style', function(i,s){ return s + 'left: 160px !important; top: 395px !important; z-index: -1;'});

    // Moves the arrow 76px to the left margin.
    iwBackground.children(':nth-child(3)').attr('style', function(i,s){ return s + 'left: 160px !important; top: 395px !important; z-index: -1;'});

    // Changes the desired tail shadow color.
    iwBackground.children(':nth-child(3)').find('div').children().css({'z-index' : '1'});

    // Reference to the div that groups the close button elements.
    var iwCloseBtn = iwOuter.next();

    // Apply the desired effect to the close button
    iwCloseBtn.css({opacity: '0.4', right: '50px', top: '20px'});

    // If the content of infowindow not exceed the set maximum height, then the gradient is removed.
    if($('.iw-content').height() < 140){
      $('.iw-bottom-gradient').css({display: 'none'});
    }

    // The API automatically applies 0.7 opacity to the button after the mouseout event. This function reverses this event to the desired value.
    iwCloseBtn.mouseout(function(){
      $(this).css({opacity: '1'});
    });
  });

}

bounds.extend(new google.maps.LatLng(latitude,longitude));

return marker;

}

// Thème de la MAP 
var mapTheme = 
  [
    {
      "featureType": "all",
      "elementType": "labels.text.fill",
      "stylers": [
      {
        "saturation": 36
      },
      {
        "color": "#333333"
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
      "featureType": "road.highway.controlled_access",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#b1aaa5"
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
      "featureType": "road.arterial",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#b1aaa5"
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
      "featureType": "road.local",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "color": "#e7e1dd"
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
    },
    {
      "featureType": "water",
      "elementType": "geometry.fill",
      "stylers": [
      {
        "saturation": "12"
      },
      {
        "color": "#e2e0f0"
      }
      ]
    }
    ];

