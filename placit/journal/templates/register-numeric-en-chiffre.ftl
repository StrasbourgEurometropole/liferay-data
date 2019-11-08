<aside class="pro-bloc-aside-chiffre">
    <span class="pro-title-aside">${Titre.getData()}</span>
    <p>${Description.getData()}</p>
    <ul class="pro-list-chiffres">
        <li><span class="pro-nb" id="valContributions"></span><strong>Contribution(s)</strong></li>
        <li><span class="pro-nb" id="valReponses"></span><strong>Réponses (uniquement par courrier/mail)</strong></li>
        <li><span class="pro-nb" id="valReponsesVille"></span><strong>Réponses Villes</strong></li>
    </ul>
</aside>

<script>
$("#valContributions").text(parseInt($("#nbContributions").val()) + parseInt($("#nbContributionsCourrier").val()));
$("#valReponses").text(parseInt($("#nbReponses").val()) + parseInt($("#nbReponsesCourrier").val()));
$("#valReponsesVille").text(parseInt($("#nbReponsesVille").val()) + parseInt($("#nbReponsesVilleCourrier").val()));
</script>