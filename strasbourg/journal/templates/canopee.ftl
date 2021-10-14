<!-- Canopée -->
<#setting locale = locale />
<#setting number_format="00000">

<main id="canopee" class="seu-container" style="margin-bottom: 50px">
    <div class="arbres">
        <p class="number">${arbres.getData()}</p>
        <p class="label"><@liferay_ui.message key="eu.canopee.arbres" /></p>
    </div>
    <div class="ecole">
        <p class="number">${ecole.getData()?number}</p>
        <p class="label"><@liferay_ui.message key="eu.canopee.ecole" /></p>
    </div>
    <div class="demineralisee">
        <p class="number">${demineralisee.getData()?number}</p>
        <p class="label"><@liferay_ui.message key="eu.canopee.demineralise" /></p>
    </div>
</main>