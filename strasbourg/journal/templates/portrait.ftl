<#setting locale = locale />

<#-- Récupération de DateHelper pour le format date -->
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

<#assign imageUrl = ""/>
<!-- image -->
<#if image.getData()?has_content>
    <#assign imageUrl = image.getData() />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${title.getData()?html}",
"og:description":'${chapo.getData()?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<main class="smag" style="padding-top: 105px !important;">
    <div class="smag-container">
        <div class="detail-line" style="justify-content: normal">
            <div class="filler"></div>
            <p class="seu-published">
            <@liferay_ui.message key="eu.published-on" />
            ${dateHelperService.displayShortDate(dateHelperService.convertStringToDate(.vars['reserved-article-display-date'].getData(), "EEE, dd MMM yyyy hh:mm:ss Z"), locale)}  
            - <@liferay_ui.message key="eu.modified-on" /> 
            ${dateHelperService.displayShortDate(dateHelperService.convertStringToDate(.vars['reserved-article-modified-date'].getData(), "EEE, dd MMM yyyy hh:mm:ss Z"), locale)} 
            </p>
        </div>
        <h1 style="width: 100%">
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
    </div>
</main>
<#if panoramique.getData()?has_content>
    <style>
        .bg-banner {
            background-image: url(${panoramique.getData()}) !important;
        }
    </style>
</#if>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
</style>

<script>
    $('.bg-banner .banner__title').text("${title.getData()?js_string}");
    $('.bg-banner .banner__description').hide();
</script>