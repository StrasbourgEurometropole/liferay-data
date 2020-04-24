$(document).ready(function(){
    Liferay.Service(
      '/council.deliberation/get-user-front',
      {
        officialId: officialConnectedId
      },
      function(obj) {
        var delibAfficheId = document.getElementById("deliberationId");
        var delibAfficheStatut = document.getElementById("stage");

        var deliberationJSON = obj.deliberation;

        // On modifie uniquement si l'id de la délib ou son statut a changé
        if(delibAfficheId.value != deliberationJSON.deliberationId || delibAfficheStatut.value != deliberationJSON.stage) {
            console.log("true");

            if(deliberationJSON.stage == "Affichage en cours") {

            } else if (deliberationJSON.stage == "Vote ouvert") {
                var officialVote = obj.official.vote;

                   //Procurations

                if(officialVote) {

                }

            } else if(deliberationJSON.stage == "Adopté" || deliberationJSON.stage == "Rejeté" || deliberationJSON.stage == "Communiqué" ) {

            }
        }
      }
    );
});