<div class="pro-bloc-bref">
    <h3>En bref</h3>
    <ul>
        <li><span class="pro-euros">€</span> <strong>Budget : </strong>${entry.budget}</li>
        <li><span class="icon-ico-label"></span> <strong>Label : </strong>${entry.label}</li>
        <li><span class="icon-ico-time"></span> <strong>Durée : </strong>${entry.duration}</li>
        <li><span class="icon-ico-partenaire"></span> <strong>Les partenaires : </strong>${entry.partners}</li>
    </ul>
 </div>
 
 <aside class="col-sm-4-to-move">
    <div class="pro-event-comming">
        <a href="#pro-link-participation" title="Vers les participations de la page"><strong>5</strong> Participation(s) en cours</a>
        <a href="#pro-link-evenement" title="Vers les événements de la page"><strong>3</strong> Évènement(s) à venir</a>
    </div>
    <div class="pro-contact">
        <h4>Contact</h4>
        <p>
            <strong>Nom du contact</strong><br>
            Contact ligne 1<br>
            Contact ligne 2
        </p>
        <a href="tel:0000000000" title="Numéro de téléphone : 00 00 00 00 00">00 00 00 00 00</a>
    </div>
</aside>
 
 <style>
 .pro-page-detail.pro-page-detail-projet section>.pro-wrapper{
     left : 0px;
 }
 
 .pro-page-detail.pro-page-detail-projet aside{
     margin-top : 123px;
 }
 </style>
 <script>
    $(document).ready(function() {
        $(".col-sm-4-to-move").contents().appendTo(".col-sm-4");
});

</script>