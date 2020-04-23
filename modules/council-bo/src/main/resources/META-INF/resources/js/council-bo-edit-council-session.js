var namespace = '_eu_strasbourg_portlet_council_CouncilBOPortlet_';

/** Définition des types de session **/
let sessionTypes = [
    'municipal',
    'eurometropolitan'
]

/** Lors de la sélection d'un type de session **/
$('input[type=radio][name=' + namespace + 'type]').change(function() {
    hideAllOfficials();
    showSelectedTypeOfficials(this.value);
});

/** Lors du chargement de la page **/
$(document).ready(function() {
    hideAllOfficials();
    showSelectedTypeOfficials($('input[type=radio][name=' + namespace + 'type]:checked').val());
});

/** Cache tous les élus **/
function hideAllOfficials() {
    for (const sessionType of sessionTypes){
        $('tr[data-is-' + sessionType + '="true"]').hide();
    }
}

/** Affiche les élus du type sélectionné **/
function showSelectedTypeOfficials(type) {
    $('tr[data-is-' + type + '="true"]').show();
}

