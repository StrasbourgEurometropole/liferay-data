<#setting locale = locale />

<script>
    description = '${text.data?replace("<[^>]*>", "", "r")?html?js_string}';
</script>

<!-- Zone Introduction -->
<div class="small-container mns-home-intro">
    <h1 class="title">${title.data}</h1>
    <p>${text.data}</p>
</div>