/**
 * Lors du chargement de la page
 */
$(document).ready(function() {
	// Compte et affichage du nombre d'éléments affichés dans les resultats de la recherche
	var numItems = $('.ops-bloc-masonry .ops-card').length
	$(".ops-numbers-results strong").text(numItems);
});