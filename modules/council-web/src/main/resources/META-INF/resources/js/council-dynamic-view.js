
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

                frontResultatVote.style.display = "none";
                frontResultatGeneral.style.display = "none";
                frontResultatSpecifique.style.display = "none";
                frontConfirmationVote.style.display = "none";
                frontVoteForm.style.display = "none";
                frontVoteEnCours.style.display = "none";

                var title = frontDelibTitle.getElementsByTagName("h2")[0];
                title.textContent = obj.session.title +' Point '+deliberationJSON.order;

                var description = frontDelibDescription.getElementsByTagName("div")[0];
                description.textContent = deliberationJSON.title;

            } else if (deliberationJSON.stage == "Vote ouvert") {
                var officialVote = obj.official.vote;

                if(useSkypeView) {
                   frontVoteEnCours.style.display="block";
                }

                //Procurations

                if(officialVote) {

                }

            } else if(deliberationJSON.stage == "Adopté" || deliberationJSON.stage == "Rejeté" || deliberationJSON.stage == "Communiqué" ) {
                frontVoteEnCours.style.display = "none";
                frontVoteForm.style.display = "none";
                frontConfirmationVote.style.display = "none";

               var votesJSON = deliberationJSON.votes;

                frontResultatStatut.textContent = deliberationJSON.stage

                if(useSkypeView) {
                    frontResultatVote.style.display = "block";
                    frontResultatSpecifique.style.display = "flex";
                    frontResultatGeneral.style.display = "none";
                } else {

                    var countPour = votesJSON.approve.length;
                    var countContre = votesJSON.against.length;
                    var countAbstention = votesJSON.abstention.length;

                    frontGeneralPour.getElementsByTagName('span')[1].textContent = countPour;
                    frontGeneralContre.getElementsByTagName('span')[1].textContent = countContre;
                    frontGeneralAbstention.getElementsByTagName('span')[1].textContent = countAbstention;

                    frontResultatVote.style.display = "block";
                    frontResultatGeneral.style.display = "flex";
                    frontResultatSpecifique.style.display = "none";
                }

            }
        }
      }
    );
});