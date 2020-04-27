
/* Sélection des input hidden */
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
var frontProcuOne= document.getElementById("form-procu-1");
var frontProcuTwo= document.getElementById("form-procu-2");
var frontTitleProcuOne= document.getElementById("title-procu-1");
var frontTitleProcuTwo= document.getElementById("title-procu-2");
var frontErrorVoteMessage= document.getElementById("error-vote-message");
// Résultats vote
var frontResultatVote = document.getElementById("resultat-vote");
var frontResultatStatut = document.getElementById("statut-delib");
var frontResultatGeneral = document.getElementById("resultat-general");
var frontGeneralPour = document.getElementById("general-pour");
var frontGeneralContre = document.getElementById("general-contre");
var frontGeneralAbstention = document.getElementById("general-abstention");
var frontResultatSpecifique = document.getElementById("resultat-specifique");
var frontSpecifiqueListePour = document.getElementById("liste-pour");
var frontSpecifiqueListeContre = document.getElementById("liste-contre");
var frontSpecifiqueListeAbstention = document.getElementById("liste-abstention");
var frontSpecifiqueEncartPour = document.getElementById("encart-pour");
var frontSpecifiqueEncartContre = document.getElementById("encart-contre");
var frontSpecifiqueEncartAbstention = document.getElementById("encart-abstention");

/* On peut direct cacher le refresh si présentation Skype*/
if(useSkypeView) {
   frontDelibRefresh.style.display = "none";
}
