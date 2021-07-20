var namespace = '_eu_strasbourg_portlet_council_CouncilBOPortlet_';

// Permet de gérer l'affichage du selecteur pour otherProcurationMode
var customSelect = document.getElementsByClassName("modeSelect");
var autre = document.getElementsByClassName("inputMode");
var autreInput = document.querySelectorAll('input[name$=autre');

Array.prototype.forEach.call(customSelect, function(el, i){
    el.onchange = function(){
        var valueSelector = el.value;
        if (valueSelector == 4) {
            autreInput[i].required = true;
            autre[i].style.display = "block";
        } else {
            autreInput[i].required = false;
            autre[i].style.display = "none";
        }
    }
        var valueSelector = el.value;
        if (valueSelector == 4) {
            autreInput[i].required = true;
            autre[i].style.display = "block";
        } else {
            autreInput[i].required = false;
            autre[i].style.display = "none";
        }
});

// Variables communes
var allValidateButtons = document.getElementsByClassName("saveButton");
var allEditButtons = document.getElementsByClassName("editButton");
var allCheckAbsent = document.getElementsByClassName("inputAbsent");
var allResetButtons = document.getElementsByClassName("resetButton");
var allCloseButtons = document.getElementsByClassName("closeButton");

// Permet de passer des paramètre au bouton save
var hiddenOfficialId = document.getElementById(namespace+"officalIdHidden");
var saveValue = document.getElementById(namespace+"actionHidden");
    Array.prototype.forEach.call(allValidateButtons, function(el, i) {
        el.addEventListener("click", function(element) {
            hiddenOfficialId.value = element.currentTarget.attributes["data-official-id"].value;
            saveValue.value = element.currentTarget.attributes["action"].value;
        }, false);
});

// Permet de passer des paramètre au bouton close
var procurationId = document.getElementById(namespace+"procurationIdHidden");
    Array.prototype.forEach.call(allCloseButtons, function(el, i) {
        el.addEventListener("click", function(element) {
            procurationId.value = element.currentTarget.attributes["procuration-id"].value;
        }, false);
});

// Permet de enabled les champ pour la saisie d'une ligne
Array.prototype.forEach.call(allEditButtons, function(el, i) {
    el.addEventListener("click", function() {
        var officialId = $(this).attr("name").replace(namespace,'').replace("-editButton",'');
        $("select[name=" + namespace + officialId + "-modeSelect]").prop('disabled', false);
        $("select[name=" + namespace + officialId + "-presentialSelect]").prop('disabled', false);
        $("input[name=" + namespace + "" + officialId + "-officialVoters]").prop('disabled', false);
        $("input[name=" + namespace + officialId + "-autre]").prop('disabled', false);
        $("div[name="+ officialId + "-checkAbsent]")[0].style.display="block";
        $("button[name="+ officialId + "-saveButton]")[0].style.display="inline-block";
        $("button[name="+ officialId + "-resetButton]")[0].style.display="inline-block";
        $("button[name="+ officialId + "-closeButton]")[0].style.display="none";
        $("button[name="+ officialId + "-editButton]")[0].style.display="none";
    }, false);
});

// Permet d'afficher l'image d'absence et de cacher/afficher les différents boutons d'action
Array.prototype.forEach.call(allCheckAbsent, function(el, i) {
   var officialId = el.name.replace(namespace,'').replace("-inputAbsent",'');
   var isAbsent = el.value;
   if (isAbsent == "true") {
       $("div[name="+ officialId + "-checkAbsent]")[0].style.display="block";
       $("button[name="+ officialId + "-closeButton]")[0].style.display="inline-block";
       $("button[name="+ officialId + "-editButton]")[0].style.display="none";
       $("button[name="+ officialId + "-saveButton]")[0].style.display="none";
       $("button[name="+ officialId + "-resetButton]")[0].style.display="none";
       var select = $("select[name=" + namespace + officialId + "-modeSelect]")[0].selectedIndex;
       if (select == 4) {
        $("div[name=" + officialId + "-selectMode]")[0].style.display="none";
       }
   } else {
         $("button[name="+ officialId + "-editButton]")[0].style.display="inline-block";
         $("button[name="+ officialId + "-closeButton]")[0].style.display="none";
         $("button[name="+ officialId + "-saveButton]")[0].style.display="none";
         $("button[name="+ officialId + "-resetButton]")[0].style.display="none";
   }
});

// Permet de reset les champs de la ligne et de les disable
Array.prototype.forEach.call(allResetButtons, function(el, i){
    el.addEventListener("click", function() {
        var officialId = $(this).attr("name").replace(namespace,'').replace("-resetButton",'');
        $("select[name=" + namespace + officialId + "-modeSelect]")[0].selectedIndex = 0;
        $("select[name=" + namespace + officialId + "-presentialSelect]")[0].selectedIndex = 0;
        $("input[name=" + namespace + officialId + "-officialVoters]")[0].value='';
        $("input[name=" + namespace + officialId + "-autre]")[0].value='';
        $("div[name="+ officialId + "-checkAbsent]")[0].style.display="none";

        $("select[name=" + namespace + officialId + "-modeSelect]").prop('disabled', true);
        $("select[name=" + namespace + officialId + "-presentialSelect]").prop('disabled', true);
        $("input[name=" + namespace + "" + officialId + "-officialVoters]").prop('disabled', true);
        $("input[name=" + namespace + officialId + "-autre]").prop('disabled', true);
        $("button[name="+ officialId + "-saveButton]")[0].style.display="none";
        $("button[name="+ officialId + "-resetButton]")[0].style.display="none";
        $("button[name="+ officialId + "-closeButton]")[0].style.display="none";
        $("button[name="+ officialId + "-editButton]")[0].style.display="inline-block";
    }, false);
});

jQuery(function() {
    /** Autocomplete des élus */
    var options = {
        type : "POST",
        serviceUrl : "/api/jsonws/council.official/get-official-by-full-name-and-type/",
        params : {
            fullName : '[fullName]',
            type : "",
            removedOfficialId : 0,
            groupId : currentGroupId,
            p_auth: Liferay.authToken
        },
        paramName : 'fullName',
        transformResult : function(response) {
            return {
                suggestions : jQuery.map(
                    JSON.parse(response), function(dataItem) {
                        return {
                            value : dataItem.fullName,
                            data : dataItem.officialId
                        };
                    })
            };
        },
        onSelect : function(suggestion) {
            $(this).val(suggestion.value);
            $(this).parent().siblings().val(suggestion.data);
        }
    };
    jQuery('.official-autocomplete-input-wrapper').each(function() {
    	$('.autocomplete-shown', this).autocomplete(options);
    });
 });

 var refreshCount = setInterval(function() {

     var timeleft = document.getElementById("refreshTimerValue").innerHTML-1000;

     // Calculating the days, hours, minutes and seconds left
     var minutes = Math.floor(timeleft / (1000 * 60));
     var seconds = Math.floor((timeleft % (1000 * 60)) / 1000);

     // Result is output to the specific element
     document.getElementById("refreshTimer").innerHTML = minutes + ":" + seconds;
     document.getElementById("refreshTimerValue").innerHTML = timeleft;

     // Display the message when countdown is over
     if (timeleft == 0) {
        document.getElementById("refreshTimerValue").innerHTML = 5000;
        Liferay.Service(
          '/council.procuration/find-associated-procuration-json',
          {
            councilSessionId: document.getElementById(namespace+"councilIdHidden").value
          },
          function(obj) {
            displayInfos(obj);
          }
        );
     }
 }, 1000);

 function displayInfos(obj) {
    Array.prototype.forEach.call(obj.official, function(official, i){
            var officialId = official.officialId;
            if(official.hasProcuration==true){
                $("select[name=" + namespace + officialId + "-modeSelect]")[0].selectedIndex = official.procurationMode;
                $("select[name=" + namespace + officialId + "-presentialSelect]")[0].selectedIndex = official.presential;
                $("input[name=" + namespace + officialId + "-officialVoters]")[0].value=official.officialVoter;
                $("input[name=" + namespace + officialId + "-autre]")[0].value=official.otherProcurationMode;
                $("span[name="+ officialId + "-checkAbsent]")[0].style.display="block";
            } else {
                $("select[name=" + namespace + officialId + "-modeSelect]")[0].selectedIndex = 0;
                $("select[name=" + namespace + officialId + "-presentialSelect]")[0].selectedIndex = 0;
                $("input[name=" + namespace + officialId + "-officialVoters]")[0].value='';
                $("input[name=" + namespace + officialId + "-autre]")[0].value='';
                $("span[name="+ officialId + "-checkAbsent]")[0].style.display="none";
            }
    });
 }