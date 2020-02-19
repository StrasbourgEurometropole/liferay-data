<aside class="pro-bloc-aside-chiffre">
    <span class="pro-title-aside">${Titre.getData()}</span>
    <p>${Description.getData()}</p>
    <ul class="pro-list-chiffres">
        <li><span class="pro-nb" id="valContributions"></span><strong>Contribution(s)</strong></li>
        <!--<li><span class="pro-nb" id="valReponses"></span><strong>Réponses (uniquement par courrier/mail)</strong></li>-->
        <li><span class="pro-nb" id="valReponsesVille"></span><strong>Réponse(s) Ville</strong></li>
    </ul>
</aside>

<script>
    $("#valContributions").text(
        ($("#nbContributions").length > 0 ? parseInt($("#nbContributions").val()) : 0) + 
        ($("div[data-contribution=1]").length )
    );
    $("#valReponses").text(
        ($("#nbReponses").length > 0 ? parseInt($("#nbReponses").val()) : 0) + 
        ($("input[id^='nbReponsesCourrier']").length > 0 ? parseInt($("input[id^='nbReponsesCourrier']").val()) : 0)
    );
    $("#valReponsesVille").text(
        ($("#nbReponsesVille").length > 0 ? parseInt($("#nbReponsesVille").val()) : 0) + 
        ($("input[id^='nbReponsesVilleCourrier']").length > 0 ? parseInt($("input[id^='nbReponsesVilleCourrier'").val()) : 0)
    );
</script>