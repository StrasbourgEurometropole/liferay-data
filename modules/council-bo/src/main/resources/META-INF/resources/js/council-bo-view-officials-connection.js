/** Récupération des éléments à gérer */
var frontListUnconnected = document.getElementById("unconnected-list");
var frontListAbsents = document.getElementById("absents-list");
var frontListConnected = document.getElementById("connected-list");

/**
 * Récupération des données de connection et refresh de l'affichage
 */
function refreshConnectionInformations() {
	Liferay.Service(
		'/council.official/get-official-by-connexion-status',
		{
			councilSessionId: currentCouncilSessionId,
			groupId: currentGroupId
		},
		function(obj) {
			clearLists();
			if (obj.unconnected)
				feedOfficialList(obj.unconnected, frontListUnconnected);
			if (obj.unconnected)
				feedOfficialList(obj.absents, frontListAbsents);
			if (obj.unconnected)
				feedOfficialList(obj.connected, frontListConnected);
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
}

/** 
 * Ajoute une liste d'official à la liste donnée 
 */
function feedOfficialList(officials, list) {
	var sortedOfficials = officials.sort(sortByProperty('fullName'));
	for(var i = 0; i < sortedOfficials.length; i++) {
	    var official = sortedOfficials[i];
	    list.innerHTML += 
			'<div class="row" id="official-' + official.officialId + '">'
				+ '<div class="col-md-3">' + official.fullName + '</div>'
				+ '<div class="col-md-9">' + official.lastSingInDeviceInfo + '</div>'
			+ '</div>';
	}
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

/**
refreshConnectionInformations();

window.setInterval(function(){
	refreshConnectionInformations();
}, 10000);
*/