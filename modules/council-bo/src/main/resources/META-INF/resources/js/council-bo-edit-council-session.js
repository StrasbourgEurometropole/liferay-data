var namespace = '_eu_strasbourg_portlet_council_CouncilBOPortlet_';

/** Lors de la sélection d'un type de session **/
$('#' + namespace + 'council-type').change(function() {
    hideAllOfficials();
    showSelectedTypeOfficials(this.value);
});

/** Lors du chargement de la page **/
$(document).ready(function() {
    hideAllOfficials();
    showSelectedTypeOfficials($('#' + namespace + 'council-type').val());
});

/** Cache tous les élus **/
function hideAllOfficials() {
    $('tr[data-council-types]').hide();
}

/** Affiche les élus du type sélectionné **/
function showSelectedTypeOfficials(type) {
    $('tr[data-council-types*=' + type + ']').show();
}

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
    var typeCouncilSession = document.getElementById(namespace+"typeCouncilSessionHidden").value;
    var options = {
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
    jQuery('.official-autocomplete-input-wrapper').each(function() {
    	$('.autocomplete-shown', this).autocomplete(options);
    });
 });