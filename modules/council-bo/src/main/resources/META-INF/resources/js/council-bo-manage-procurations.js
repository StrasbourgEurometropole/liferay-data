var namespace = '_eu_strasbourg_portlet_council_CouncilBOPortlet_';

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

var allValidateButtons = document.getElementsByClassName("saveButton");
var hiddenOfficialId = document.getElementById(namespace+"officalIdHidden");
    Array.prototype.forEach.call(allValidateButtons, function(el, i){
        el.addEventListener("click", function(element) {
            hiddenOfficialId.value = element.currentTarget.attributes["data-official-id"].value;
        }, false);
});

var allEditButtons = document.getElementsByClassName("editButton");
    Array.prototype.forEach.call(allEditButtons, function(el, i){
        el.addEventListener("click", function() {
            var officialId = $(this).attr("name").replace(namespace,'').replace("-editButton",'');
            $("select[name=" + namespace + officialId + "-modeSelect]").prop('disabled', false);
            $("select[name=" + namespace + officialId + "-presentialSelect]").prop('disabled', false);
            $("input[name=" + namespace + "" + officialId + "-officialVoters]").prop('disabled', false);
            $("input[name=" + namespace + officialId + "-autre]").prop('disabled', false);
            $("span[name="+ officialId + "-checkAbsent]")[0].style.display="block";
        }, false);
});


var allCheckAbsent = document.getElementsByClassName("inputAbsent");
    Array.prototype.forEach.call(allCheckAbsent, function(el, i){
       var officialId = el.name.replace(namespace,'').replace("-inputAbsent",'');
       var isAbsent = el.value;
       if (isAbsent == "true") {
           $("span[name="+ officialId + "-checkAbsent]")[0].style.display="block";
       }
});



var allResetButtons = document.getElementsByClassName("resetButton");
    Array.prototype.forEach.call(allResetButtons, function(el, i){
        el.addEventListener("click", function() {
            var officialId = $(this).attr("name").replace(namespace,'').replace("-resetButton",'');
            $("select[name=" + namespace + officialId + "-modeSelect]")[0].selectedIndex = 0;
            $("select[name=" + namespace + officialId + "-presentialSelect]")[0].selectedIndex = 0;
            $("input[name=" + namespace + officialId + "-officialVoters]")[0].value='';
            $("input[name=" + namespace + officialId + "-autre]")[0].value='';
            // TODO verifier display none le checkabs
            $("span[name="+ officialId + "-checkAbsent]")[0].style.display="none";
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