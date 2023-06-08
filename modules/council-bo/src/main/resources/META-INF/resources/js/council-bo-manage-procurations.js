var namespace = '_eu_strasbourg_portlet_council_CouncilBOPortlet_';

// Permet de gérer l'affichage du selecteur pour otherProcurationMode
var customSelect = document.getElementsByClassName("modeSelect");
var autre = document.getElementsByClassName("inputMode");
var autreInput = document.querySelectorAll('input[name$=autre');

Array.prototype.forEach.call(customSelect, function(el, i){
    el.onchange = function(){
        var valueSelector = el.value;
        autre[i].value='';
        if (valueSelector == 4) {
            autreInput[i].required = true;
            autre[i].style.display = "block";
            autre[i].setAttribute('required', true);
        } else {
            autreInput[i].required = false;
            autre[i].style.display = "none";
            autre[i].value='';
        }
    }
        var valueSelector = el.value;

        if (valueSelector == 4) {
            autreInput[i].required = true;
            autre[i].style.display = "block";
        } else {
            autreInput[i].required = false;
            autre[i].style.display = "none";
            autre[i].value='';
        }
});

// Variables communes
var allValidateButtons = document.getElementsByClassName("saveButton");
var allEditButtons = document.getElementsByClassName("editButton");
var allCheckAbsent = document.getElementsByClassName("inputAbsent");
var allResetButtons = document.getElementsByClassName("resetButton");
var allCloseButtons = document.getElementsByClassName("closeButton");

// Permet de fermer la div d'erreur
var closeErrorButton = document.getElementById("closeMessageError");
closeErrorButton.addEventListener("click", function(element) {
    $("div[name=" + "errorDiv]")[0].style.display="none";
});

// Permet de fermer la div de warn
var closeWarnButton = document.getElementById("closeMessageWarn");
closeWarnButton.addEventListener("click", function(element) {
    $("div[name=" + "warnDiv]")[0].style.display="none";
});

// Permet de enabled les champ pour la saisie d'une ligne
Array.prototype.forEach.call(allEditButtons, function(el, i) {
    el.addEventListener("click", function() {
        var editValue =  document.getElementById(namespace+"editHidden");
        if(editValue.value=="false"){
            clearInterval(refreshCount);
            document.getElementById("refreshTimer").innerHTML = document.getElementById("refreshTimer").innerHTML + " Rafraichissement automatique mis en pause"
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
            editValue.value=true;
        } else {
            window.alert("Une \u00E9dition de ligne est d\u00E9j\u00E0 en cours");
        }
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
        refreshCount = setInterval(refreshTab, 1000);
        document.getElementById(namespace+"editHidden").value=false;
        var officialId = $(this).attr("name").replace(namespace,'').replace("-resetButton",'');
        $("select[name=" + namespace + officialId + "-modeSelect]")[0].selectedIndex = 0;
        $("select[name=" + namespace + officialId + "-presentialSelect]")[0].selectedIndex = 0;
        $("input[name=" + namespace + officialId + "-officialVoters]")[0].value='';
        $("input[name=" + namespace + officialId + "-autre]")[0].value='';
        $("div[name="+ officialId + "-checkAbsent]")[0].style.display="none";
        $("div[name=" + officialId + "-inputMode]")[0].style.display="none";
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
    var typeCouncilSession = document.getElementById(namespace+"typeCouncilSessionHidden").value;
    var options = {
        preventBadQueries : false,
        minChars: 2,
        type : "POST",
        serviceUrl : "/api/jsonws/council.official/get-official-by-full-name-and-type/",
        params : {
            fullName : '[fullName]',
            type : typeCouncilSession,
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
    jQuery('.official-autocomplete-input-wrapper-procurations').each(function() {
    	$('.autocomplete-shown', this).autocomplete(options);
    });
 });

