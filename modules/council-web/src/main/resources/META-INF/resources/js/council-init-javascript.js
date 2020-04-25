
/*Sélection des input hidden*/
var hiddenDelibId = document.getElementById("deliberationId");
var hiddenDelibStatut = document.getElementById("stage");

/* Sélection des différents éléments*/
// Presentation
var frontDelibTitle = document.getElementById("delib-title");
var frontDelibRefresh = document.getElementById("delib-refresh");
var frontDelibDescription = document.getElementById("delib-description");
var frontVoteEnCours = document.getElementById("vote-en-cours");
// Vote
var frontConfirmationVote= document.getElementById("confirmation-vote");
var frontVoteForm= document.getElementById("vote-forms");
var frontVoteButtonSubmit= document.getElementById("vote-button-submit");
// Résultats vote
var frontResultatVote = document.getElementById("resultat-vote");
var frontResultatStatut = document.getElementById("statut-delib");
var frontResultatGeneral = document.getElementById("resultat-general");
var frontGeneralPour = document.getElementById("general-pour");
var frontGeneralContre = document.getElementById("general-contre");
var frontGeneralAbstention = document.getElementById("general-abstention");
var frontResultatSpecifique = document.getElementById("resultat-specifique");

/* On peut direct cacher le refresh si présentation Skype*/
if(useSkypeView) {
   frontDelibRefresh.style.display = "none";
}
