<aside class="pro-bloc-aside-chiffre">
    <span class="pro-title-aside">${Titre.getData()}</span>
    <p>${Description.getData()}</p>
    <ul class="pro-list-chiffres">
        <li><span class="pro-nb" id="valReponses"></span><strong>Propositions</strong></li>
        <li><span class="pro-nb" id="valReponsesVille"></span><strong>Réponses</strong></li>
    </ul>
</aside>

<script>
$("#valReponses").text($("#nbReponses").val());
$("#valReponsesVille").text($("#nbReponsesVille").val())
</script>