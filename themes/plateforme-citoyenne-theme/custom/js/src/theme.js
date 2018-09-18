//méthode permettant de confirmer la fermeture de la popup en ouvrant une nouvelle popup.
$("#closingButton").click(function(event){
       event.preventDefault();
       $("#modalQuitPetition").modal("show");
       $("#buttonConfirmQuit").click(function(event){
            $("#modalQuitPetition").modal("hide");
            $("#closingButton").parent(".pro-modal pro-bloc-pcs-form fade").modal("hide");
       });
   });