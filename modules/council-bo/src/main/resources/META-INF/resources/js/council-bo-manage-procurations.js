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

/** Lors d'un check/unchecked d'une absence **/
$('input[name$=-isAbsent]').on('change',function(){
    var val = $(this).is(':checked');
    var officialId = $(this).attr("name").replace(namespace,'').replace("-isAbsent",'');;
    if (val)
        $("input[name=" + namespace + "" + officialId + "-officialVoters]").prop('disabled', false);
    else
        $("input[name=" + namespace + "" + officialId + "-officialVoters]").prop('disabled', true);
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