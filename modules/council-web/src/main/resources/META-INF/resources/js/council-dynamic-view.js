
window.setInterval(function(){
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

                frontDelibTitle.style.display="flex";

            } else if (deliberationJSON.stage == "Vote ouvert") {
                var officialVote = obj.official.vote;


                frontProcuOne.style.display="none";
                frontProcuTwo.style.display="none";

                if(useSkypeView) {
                   frontVoteEnCours.style.display="block";
                } else {
                    var procurationsJSON = obj.official.procurations;
                    var procurationOne = procurationsJSON[0];
                    var procurationTwo = procurationsJSON[1];

                    if(officialVote) {

                        if(officialVote == "Pour") {
                            document.getElementById('pour').checked= true;
                        } else if (officialVote == "Contre") {
                            document.getElementById('contre').checked= true;
                        }else if (officialVote == "Pour") {
                            document.getElementById('abstention').checked= true;
                        }

                        if(procurationOne) {
                            var procurationOneVote = procurationOne.vote;
                            if(procurationOneVote == "Pour") {
                                document.getElementById('pour1').checked= true;
                            } else if (procurationOneVote == "Contre") {
                                document.getElementById('contre1').checked= true;
                            }else if (procurationOneVote == "Pour") {
                                document.getElementById('abstention1').checked= true;
                            }
                        }

                        if(procurationTwo) {
                            var procurationTwoVote = procurationTwo.vote;
                            if(procurationTwoVote == "Pour") {
                                document.getElementById('pour2').checked= true;
                            } else if (procurationTwoVote == "Contre") {
                                document.getElementById('contre2').checked= true;
                            }else if (procurationTwoVote == "Pour") {
                                document.getElementById('abstention2').checked= true;
                            }
                        }

                        disabledAllInput();
                        frontConfirmationVote.style.display = "block";
                        frontVoteButtonSubmit.style.display = "none";

                    } else {

                        document.getElementById(namespace+"session-id").value = obj.session.councilSessionId;
                        document.getElementById(namespace+"deliberation-id").value = deliberationJSON.deliberationId;

                        if(procurationOne) {
                            document.getElementById(namespace+"official-procuration-id-1").value = procurationOne.officialUnavailableId;
                        }
                        if(procurationTwo) {
                            document.getElementById(namespace+"official-procuration-id-2").value = procurationTwo.officialUnavailableId;
                        }

                        frontVoteButtonSubmit.style.display = "block";
                    }

                    if(procurationOne) {
                        frontTitleProcuOne.getElementsByTagName('span')[0].textContent = procurationOne.fullName;
                        frontProcuOne.style.display="block";
                    }
                    if(procurationTwo) {
                        frontTitleProcuTwo.getElementsByTagName('span')[0].textContent = procurationTwo.fullName;
                        frontProcuTwo.style.display="block";
                    }

                    frontVoteForm.style.display = "flex";
                }
            } else if(deliberationJSON.stage == "Adopté" || deliberationJSON.stage == "Rejeté" || deliberationJSON.stage == "Communiqué" ) {
                // Reset le formulaire pour le prochain vote
                resetFormValues()

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
}, 3000);

function disabledAllInput() {
    document.getElementById('pour').disabled= true;
    document.getElementById('contre').disabled= true;
    document.getElementById('abstention').disabled= true;
    document.getElementById('pour1').disabled= true;
    document.getElementById('contre1').disabled= true;
    document.getElementById('abstention1').disabled= true;
    document.getElementById('pour2').disabled= true;
    document.getElementById('contre2').disabled= true;
    document.getElementById('abstention2').disabled= true;
}