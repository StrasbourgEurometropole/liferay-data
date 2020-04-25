
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

            // A partir du moment où on a des infos sur la delib, on peut l'afficher
            if(deliberationJSON.order && deliberationJSON.title) {
                var title = frontDelibTitle.getElementsByTagName("h2")[0];
                title.textContent = obj.session.title +' Point '+deliberationJSON.order;

                var description = frontDelibDescription.getElementsByTagName("div")[0];
                description.textContent = deliberationJSON.title;

                frontDelibTitle.style.display = "flex";
                frontDelibRefresh.style.display = "block";
            }


            if(deliberationJSON.stage == "Affichage en cours") {

                frontResultatVote.style.display = "none";
                frontResultatGeneral.style.display = "none";
                frontResultatSpecifique.style.display = "none";
                frontConfirmationVote.style.display = "none";
                frontVoteForm.style.display = "none";
                frontVoteEnCours.style.display = "none";

            } else if (deliberationJSON.stage == "Vote ouvert") {
                var officialVote = obj.official.vote;

                if(useSkypeView) {
                   frontVoteEnCours.style.display="block";
                }

                var procurationsJSON = obj.official.procurations;
                var procurationOne = procurationsJSON[0];
                var procurationTwo = procurationsJSON[1];

                if(officialVote) {

                    frontVoteButtonSubmit.style.display = "none";
                } else {
                    frontVoteButtonSubmit.style.display = "block";
                }

                frontVoteForm.style.display = "flex";

            } else if(deliberationJSON.stage == "Adopté" || deliberationJSON.stage == "Rejeté" || deliberationJSON.stage == "Communiqué" ) {
                frontVoteEnCours.style.display = "none";
                frontVoteForm.style.display = "none";
                frontConfirmationVote.style.display = "none";

                var votesJSON = deliberationJSON.votes;

                var countPour = votesJSON.approve.length;
                var countContre = votesJSON.against.length;
                var countAbstention = votesJSON.abstention.length;

                frontResultatStatut.textContent = deliberationJSON.stage

                // VUE AGENT DES RESULTATS
                if(useSkypeView) {

                    var listePour ='';
                    var listeContre ='';
                    var listeAbstention ='';

                    for (var i = 0; i < votesJSON.approve.length; i++) {
                        if(i == countPour - 1) {
                            listePour += votesJSON.approve[i];
                        } else {
                            listePour += votesJSON.approve[i] +', ';
                        }
                    };
                   for (var j = 0; j < votesJSON.against.length; j++) {
                        if(j == countContre - 1) {
                            listeContre += votesJSON.against[j];
                        } else {
                            listeContre += votesJSON.against[j] +', ';
                        }
                    };
                    for (var k = 0; k < votesJSON.abstention.length; k++) {
                        if(k == countAbstention - 1) {
                            listeAbstention += votesJSON.abstention[k];
                        } else {
                            listeAbstention += votesJSON.abstention[k] +', ';
                        }
                    };

                    frontSpecifiqueEncartPour.getElementsByTagName('span')[1].textContent = countPour;
                    frontSpecifiqueEncartContre.getElementsByTagName('span')[1].textContent = countContre;
                    frontSpecifiqueEncartAbstention.getElementsByTagName('span')[1].textContent = countAbstention;

                    frontSpecifiqueListePour.textContent = listePour;
                    frontSpecifiqueListeContre.textContent = listeContre;
                    frontSpecifiqueListeAbstention.textContent = listeAbstention;

                    // On affiche les résultats uniquement si ADOPTE ou REJETE et s'il y a des votes
                    if(deliberationJSON.stage != "Communiqué" && (countPour > 0 || countContre > 0 || countAbstention > 0)) {
                        frontResultatSpecifique.style.display = "flex";
                    }
                    frontResultatGeneral.style.display = "none";
                }
                // VUE ELU DES RESULTATS
                else {

                    frontGeneralPour.getElementsByTagName('span')[1].textContent = countPour;
                    frontGeneralContre.getElementsByTagName('span')[1].textContent = countContre;
                    frontGeneralAbstention.getElementsByTagName('span')[1].textContent = countAbstention;

                    frontResultatVote.style.display = "block";
                    // On affiche les résultats uniquement si ADOPTE ou REJETE et s'il y a des votes
                    if(deliberationJSON.stage != "Communiqué" && (countPour > 0 || countContre > 0 || countAbstention > 0)) {
                        frontResultatGeneral.style.display = "flex";
                    }
                    frontResultatSpecifique.style.display = "none";
                }

                // On affiche le statut du résultat qu'importe le reste
                frontResultatVote.style.display = "block";
            }
        }
      }
    );
});