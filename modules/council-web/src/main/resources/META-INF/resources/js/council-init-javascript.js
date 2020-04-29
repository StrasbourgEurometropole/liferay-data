
/* Sélection des input hidden */
var hiddenDelibId = document.getElementById("deliberationId");
var hiddenDelibStatut = document.getElementById("stage");
var hiddenOfficialAbsent = document.getElementById("absent");

/* Sélection des différents éléments*/
//message
var frontMessage = document.getElementById("front-message");
var frontMessageWrapper = document.getElementById("front-message-wrapper");
var frontNoDelib = document.getElementById("no-delib");
var frontAbsentRefresh= document.getElementById("absent-refresh");
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

if(useSkypeView) {
    /* On peut direct cacher le refresh si présentation Skype*/
   frontDelibRefresh.style.display = "none";
   var description = frontDelibDescription.getElementsByTagName("div")[0];
   description.classList.add("skype-description");
}