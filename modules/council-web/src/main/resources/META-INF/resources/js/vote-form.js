
/** Lors de l'envoie du formulaire **/
$("#sendVotes").click(function(event){
    event.preventDefault();
    var response = validateFormSubmitBudget();
    if (response){
        var sessionId = $("#" + namespace + "sessionId").val();
        var deliberationId = $("#" + namespace + "deliberationId").val();
        var officialId = $("#" + namespace + "officialId").val();
        var officialVote = $("#" + namespace + "officialVote").val();
        var officialProcurationId_1 = $("#" + namespace + "officialProcurationId_1").val();
        var officialProcurationVote_1 = $("#" + namespace + "officialProcurationVote_1").val();
        var officialProcurationId_2 = $("#" + namespace + "officialProcurationId_2").val();
        var officialProcurationVote_2 = $("#" + namespace + "officialProcurationVote_2").val();

        AUI().use('aui-io-request', function(A) {
            try {
                A.io.request('${submitVotesURL}', {
                    method : 'POST',
                    dataType: 'json',
                    data:{
                        <portlet:namespace/>sessionId:sessionId,
                        <portlet:namespace/>deliberationId:deliberationId,
                        <portlet:namespace/>officialId:officialId,
                        <portlet:namespace/>officialVote:officialVote,
                        <portlet:namespace/>officialProcurationId_1:officialProcurationId_1,
                        <portlet:namespace/>officialProcurationVote_1:officialProcurationVote_1,
                        <portlet:namespace/>officialProcurationId_2:officialProcurationId_2,
                        <portlet:namespace/>officialProcurationVote_2:officialProcurationVote_2
                    },
                    on: {
                        complete: function(e) {
                            var data = JSON.parse(e.details[1].responseText);
                            if (data.result) {
                                // Retour du serveResource avec succes
                                resetFormValues();
                            } else {
                                // Retour du serveResource avec erreur
                                alert(data.message);
                            }
                        }
                    }
                });
            }
            catch(error) {
                if (!(error instanceof TypeError)) {
                    console.log(error);
                } else {
                    console.log("petite erreur sans importance")
                }
            }
        });
    }
});

/** Réinitialiser le formulaire de vote **/
function resetFormValues() {
    return true;
}