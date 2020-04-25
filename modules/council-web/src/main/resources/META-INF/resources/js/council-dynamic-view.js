$(document).ready(function(){
    Liferay.Service(
      '/council.deliberation/get-user-front',
      {
        officialId: officialConnectedId
      },
      function(obj) {


        var deliberationJSON = obj.deliberation;

        // On modifie uniquement si l'id de la délib ou son statut a changé
        if(hiddenDelibId.value != deliberationJSON.deliberationId || hiddenDelibStatut.value != deliberationJSON.stage) {
            console.log("true");

            hiddenDelibId.value = deliberationJSON.deliberationId;
            hiddenDelibStatut.value = deliberationJSON.stage;

            if(deliberationJSON.stage == "Affichage en cours") {

                frontResultatVote.hide();
                frontResultatGeneral.hide();
                frontResultatSpecifique.hide();
                frontConfirmationVote.hide();
                frontVoteForm.hide();
                frontVoteButtonSubmit.hide();
                frontVoteEnCours.hide();

                var title = frontDelibTitle.document.getElementsByTagName("h2")[0];
                title.innerText = obj.session.title +' Point '+deliberationJSON.order;

                var description = frontDelibDescription.document.getElementsByTagName("div")[0];
                description.innerText = deliberationJSON.title;

            } else if (deliberationJSON.stage == "Vote ouvert") {
                var officialVote = obj.official.vote;

                if(useSkypeView) {
                   frontVoteEnCours.show();
                }

                //Procurations

                if(officialVote) {

                }

            } else if(deliberationJSON.stage == "Adopté" || deliberationJSON.stage == "Rejeté" || deliberationJSON.stage == "Communiqué" ) {
                frontVoteEnCours.hide();
            }
        }
      }
    );
});