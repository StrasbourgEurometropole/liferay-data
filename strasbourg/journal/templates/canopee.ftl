<!-- Canopée -->
<#setting locale = locale />
<#setting number_format="00000">

<main id="canopee" class="seu-container" style="margin-bottom: 50px">
    <#if arbres?? && arbres.getData()?has_content && arbres.getData()!='0'>
        <div class="arbres">
            <p class="number">${arbres.getData()?number}</p>
            <p class="label"><@liferay_ui.message key="eu.canopee.arbres" /></p>
        </div>
    </#if>
    <#if ecole?? && ecole.getData()?has_content && ecole.getData()!='0'>
        <div class="ecole">
            <p class="number">${ecole.getData()?number}</p>
            <p class="label"><@liferay_ui.message key="eu.canopee.ecole" /></p>
        </div>
    </#if>
    <#if demineralisee?? && demineralisee.getData()?has_content && demineralisee.getData()!='0'>
        <div class="demineralisee">
            <p class="number">${demineralisee.getData()?number}</p>
            <p class="label"><@liferay_ui.message key="eu.canopee.demineralise" /></p>
        </div>
    </#if>
</main>