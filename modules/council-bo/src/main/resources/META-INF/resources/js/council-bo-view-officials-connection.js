/** Récupération des éléments à gérer */
var frontUnconnectedNumber = document.getElementById("unconnected-number");
var frontListUnconnected = document.getElementById("unconnected-list");
var frontAbsentsNumber = document.getElementById("absents-number");
var frontListAbsents = document.getElementById("absents-list");
var frontConnectedNumber = document.getElementById("connected-number");
var frontListConnected = document.getElementById("connected-list");
var frontCouncilSessionTitle = document.getElementById("councilTitle");

/**
 * Récupération des données de connection et refresh de l'affichage
 */
function refreshConnectionInformations(councilSessionId) {
	Liferay.Service(
		'/council.official/get-official-by-connexion-status',
		{
			councilSessionId: councilSessionId,
			groupId: currentGroupId
		},
		function(obj) {
			clearLists();
			if (obj.unconnected){
				changeHeaderNumber(obj.unconnected.length, frontUnconnectedNumber);
				feedOfficialList(obj.unconnected, frontListUnconnected);
				changeHeaderNumber(obj.absents.length, frontAbsentsNumber);
				feedOfficialList(obj.absents, frontListAbsents);
				changeHeaderNumber(obj.connected.length, frontConnectedNumber);
				feedOfficialList(obj.connected, frontListConnected);
				displayTitle(obj.councilSessionTitle, frontCouncilSessionTitle);
			} else {
				changeHeaderNumber(0, frontUnconnectedNumber);
				changeHeaderNumber(0, frontAbsentsNumber);
				changeHeaderNumber(0, frontConnectedNumber);
			}
		}
    );
}

/** 
 * Supprimer toutes les entrées des listes
 */
function clearLists() {
	frontListUnconnected.textContent = '';
	frontListAbsents.textContent = '';
	frontListConnected.textContent = '';
	frontCouncilSessionTitle.textContent='';
}

/** 
 * Ajoute une liste d'official à la liste donnée 
 */
function feedOfficialList(officials, element) {
	var sortedOfficials = officials.sort(sortByProperty('fullName'));
	for(var i = 0; i < sortedOfficials.length; i++) {
	    var official = sortedOfficials[i];

        var hasInfoLastActivity = Date.parse(official.lastActivity);
        if (isNaN(hasInfoLastActivity) == false) {
            var date = new Date(hasInfoLastActivity);
	        var datestring = ("0" + date.getDate()).slice(-2) + "/" + ("0"+(date.getMonth()+1)).slice(-2) + "/" +
            date.getFullYear() + " " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2);
            element.innerHTML +=
                '<div class="row" id="official-' + official.officialId + '">'
                    + '<div class="col-md-3">' + official.fullName + '</div>'
                    + '<div class="col-md-4">' + datestring + '</div>'
                    + '<div class="col-md-5">' + official.lastSingInDeviceInfo + '</div>'
                + '</div>';
        } else {
	          element.innerHTML +=
                            '<div class="row" id="official-' + official.officialId + '">'
                                + '<div class="col-md-3">' + official.fullName + '</div>'
                                + '<div class="col-md-5">' + official.lastSingInDeviceInfo + '</div>'
                            + '</div>';
	    }
	}
}

/**
 * Affiche le titre du conseil
 */
function displayTitle(title, element) {
	    element.innerHTML +=
			'<div id="councilTitle">'
			+ title
			+ '</div>';
}

/** 
 * Change le nomber du header d'une section donnée
 */
function changeHeaderNumber(newNumber, element) {
	element.innerHTML = newNumber;
}

/**
 * Generic array sorting
 *
 * @param property
 * @returns {Function}
 */
var sortByProperty = function (property) {
    return function (x, y) {
        return ((x[property] === y[property]) ? 0 : ((x[property] > y[property]) ? 1 : -1));
    };
};

/** 
 * Appel au refresh de l'affichage des connections toutes les 10 sec
 * @note Appel direct avec le setInterval pour éviter d'attendre 10 sec avant le premier affichage
 */
refreshConnectionInformations(currentCouncilSessionId);

window.setInterval(function(){
	refreshConnectionInformations(currentCouncilSessionId);
}, 10000);
