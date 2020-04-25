
/*Sélection des input hidden*/
var hiddenDelibId = document.getElementById("deliberationId");
var hiddenDelibStatut = document.getElementById("stage");

/* Sélection des différents éléments*/
var frontDelibTitle = document.getElementById("delib-title");
var frontDelibRefresh = document.getElementById("delib-refresh");
var frontDelibDescription = document.getElementById("delib-description");
var frontVoteEnCours = document.getElementById("vote-en-cours");
var frontResultatVote = document.getElementById("resultat-vote");
var frontResultatGeneral = document.getElementById("resultat-general");
var frontResultatSpecifique = document.getElementById("resultat-specifique");
var frontConfirmationVote= document.getElementById("confirmation-vote");
var frontVoteForm= document.getElementById("vote-forms");
var frontVoteButtonSubmit= document.getElementById("vote-button-submit");

/* On peut direct cacher le refresh si présentation Skype*/
if(useSkypeView) {
   frontDelibRefresh.style.display = "none";
}
