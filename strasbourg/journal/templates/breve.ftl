<!-- Webmag - breve -->
<#setting locale = locale />
<#-- Récupération de DateHelper pour le format date -->
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

<#assign imageUrl = ""/>
<!-- image -->
<#if image.getData()?has_content>
    <#assign imageUrl = image.getData() />
</#if>
<script>
    title = '${title.getData()?html?js_string}';
    description = '${chapo.getData()?replace("<[^>]*>", "", "r")?html?js_string}';
    imageUrl = '${imageUrl}';
</script>

<main class="seu-container" style="margin-bottom: 50px">
    <div class="detail-line">
        <div class="filler"></div>
        <p class="seu-published">
            <@liferay_ui.message key="eu.published-on" /> 
            ${dateHelperService.displayShortDate(dateHelperService.convertStringToDate(.vars['reserved-article-display-date'].getData(), "EEE, dd MMM yyyy hh:mm:ss Z"), locale)} 
           - <@liferay_ui.message key="eu.modified-on" /> 
            ${dateHelperService.displayShortDate(dateHelperService.convertStringToDate(.vars['reserved-article-modified-date'].getData(), "EEE, dd MMM yyyy hh:mm:ss Z"), locale)} 
        </p>
    </div>
    <h1>
        ${title.getData()}
    </h1>
    <div class="hat">
        <div>
            ${chapo.getData()}
        </div>
    </div>
    <div class="rte">
        ${content.getData()}
    </div>
</main>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
</style>