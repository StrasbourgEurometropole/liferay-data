<#setting locale = locale />

<script>
    description = '${chapo.getData()?replace("<[^>]*>", "", "r")?html?js_string}';
</script>

<main class="seu-container" style="margin-bottom: 50px">
    <h1>${title.getData()}</h1>
    <div class="hat">
        <div>
            ${chapo.getData()}
        </div>
    </div>
    <div class="rte">
        ${content.getData()}
    </div>
</main>
