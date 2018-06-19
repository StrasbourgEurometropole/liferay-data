<!-- DETAIL D'UN PROJET -->

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<div id="breadcrumb">
    <span>
        <span>
            <a href="${homeURL}">Accueil</a>
            <a href="${homeURL}projets">Les projets</a>
            <span class="breadcrumb_last">${entry.title}</span>
        </span>
    </span>
</div>

<div class="pro-bloc-bref">
    <h3>En bref</h3>
    <ul>
        <#if entry.budget?has_content>
            <li><span class="pro-euros">€</span> <strong>Budget : </strong>${entry.budget}</li>
        </#if>
        <#if entry.label?has_content>
            <li><span class="icon-ico-label"></span> <strong>Label : </strong>${entry.label}</li>
        </#if>
        <#if entry.duration?has_content>
            <li><span class="icon-ico-time"></span> <strong>Durée : </strong>${entry.duration}</li>
        </#if>
        <#if entry.partners?has_content>
            <li><span class="icon-ico-partenaire"></span> <strong>Les partenaires : </strong>${entry.partners}</li>
        </#if>
    </ul>
 </div>
 
 <aside class="col-sm-4-to-move">
    <div class="pro-event-comming">
        <a href="#pro-link-participation" title="Vers les participations de la page"><strong>${entry.getParticipations()?size}</strong> Participation(s) en cours</a>
        <a href="#pro-link-evenement" title="Vers les événements de la page"><strong>${entry.getEvents()?size}</strong> Évènement(s) à venir</a>
    </div>
    <div class="pro-contact">
        <h4>Contact</h4>
        <p>
            <#if entry.contactName?has_content>
                <strong> ${entry.contactName}</strong><br>
                <#if entry.contactLine1?has_content> ${entry.contactLine1} </#if>
                <#if entry.contactLine2?has_content> <br>${entry.contactLine2} </#if>
            <#else>
                <strong>Aucun contact renseigné pour le moment </strong><br>
            </#if>
        </p>
        <a href="tel:${entry.contactPhoneNumber}" title="Numéro de téléphone : ${entry.contactPhoneNumber}">${entry.contactPhoneNumber}</a>
    </div>
</aside>
 
 <style>
 .pro-page-detail.pro-page-detail-projet section>.pro-wrapper{
     left : 0px;
 }
 
 .pro-page-detail.pro-page-detail-projet aside{
     margin-top : 124px;
 }
 .pro-page-detail.pro-page-detail-projet .pro-wrapper .portlet-body>* {
    margin: 0;
    padding: 15px 0;
}
 </style>
 
 <script>
    $(document).ready(function() {
        $(".col-sm-4-to-move").contents().appendTo(".col-sm-4");
});
</script>