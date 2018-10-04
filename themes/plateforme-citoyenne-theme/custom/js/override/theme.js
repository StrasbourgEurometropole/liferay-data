//méthode permettant de confirmer la fermeture de la popup en ouvrant une nouvelle popup.
$("#closingButton").click(function(event){
   event.preventDefault();
   var temp = $(document.activeElement).parent().parent().parent().parent();
   var zindex = $(".fade.in").css("z-index");
   $("#modalQuitPetition").modal("show");
   $("#modalQuitPetition").css('z-index',zindex+1)
   $("#buttonConfirmQuit").click(function(event){
        $("#modalQuitPetition").modal("hide");
        temp.modal('hide');
   });
});
$("#closingButton2").click(function(event){
   event.preventDefault();
   var temp = $(document.activeElement).parent().parent().parent().parent();
   var zindex = $(".fade.in").css("z-index");
   $("#modalQuitPetition").modal("show");
   $("#modalQuitPetition").css('z-index',zindex+1)
   $("#buttonConfirmQuit").click(function(event){
        $("#modalQuitPetition").modal("hide");
        temp.modal('hide');
   });
});