<%@ include file="/council-init.jsp"%>

<!-- STATUT A AFFICHER DES QUE REJET/ADOPTE/COMMUNIQUE  (ELU ET SKYPE-->
<div class="resultat-vote" id="resultat-vote">
    <h3>Le point est : <span class="statut-delib" id="statut-delib"></span></h3>
</div>

<!-- RESULTAT A AFFICHER POUR L'ELU -->
<div class="resultat-general" id="resultat-general">
    <div class="resultat pour" id="general-pour">
        <span>Pour</span>
        <span></span>
    </div>
    <div class="resultat contre" id="general-contre">
        <span>Contre</span>
        <span></span>
    </div>
    <div class="resultat abstention" id="general-abstention">
        <span>Abstention</span>
        <span></span>
    </div>
</div>

<!-- RESULTAT A AFFICHER POUR PRESENTATION SKYPE-->
<div class="resultat-specifique" id="resultat-specifique">
    <div class="resultat">
        <div class="encart pour" id="encart-pour">
            <span>Pour</span>
            <span></span>
        </div>
        <div class="liste" id="liste-pour">

        </div>
    </div>
    <div class="resultat">
        <div class="encart contre" id="encart-contre">
            <span>Contre</span>
            <span></span>
        </div>
        <div class="liste" id="liste-contre">

        </div>
    </div>
    <div class="resultat">
        <div class="encart abstention" id="encart-abstention">
            <span>Abstention</span>
            <span></span>
        </div>
        <div class="liste" id="liste-abstention">

        </div>
    </div>
</div>