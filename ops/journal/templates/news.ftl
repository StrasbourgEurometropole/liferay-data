<!-- DETAIL ACTUALITE -->

<#-- Chargement de la variable de localisation -->
<#setting locale = locale />

<#-- Récupération de DateHelper pour le format date -->
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

<#-- Récupération de l'id du webcontent -->
<#assign journalArticleId = .vars['reserved-article-id'].data>
<#assign journalArticleResourceLocalServiceUtil = staticUtil["com.liferay.journal.service.JournalArticleResourceLocalServiceUtil"]>
<#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>

<#-- Récupération des catégories associées -->
<#assign articleResourcePK = journalArticleResourceLocalServiceUtil.getArticleResourcePrimKey(groupId, journalArticleId)/>
<#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",articleResourcePK) >

<#-- Récupération des dates inhérentes à l'article -->
<#assign publishedDate = dateHelperService.displayShortDate(dateHelperService.convertStringToDate(.vars['reserved-article-display-date'].getData(), "EEE, dd MMM yyyy hh:mm:ss Z"), locale)>

<div class="ops-page-article">

    <article class="ops-content-article">

        <!-- Header de l'article -->
        <header class="ops-content-wrapper ops-bloc ops-bloc-small">
            <div class="ops-cats">
                <#list categoryList as category>
                   <span class="ops-cat"> ${category.getTitle(locale)} </span>
                </#list>
            </div>
            <h1>${title.getData()}</h1>
            <span class="ops-date-article">
                <@liferay_ui.message key="eu.ops.published.on" /> 
                <time datetime="${publishedDate}">
                    ${publishedDate}
                </time>
            </span>
        </header>

        <!-- Image à la Une -->
        <div class="ops-content-wrapper ops-bloc-texte ops-image-une">
            <figure class="fit-cover">
                <img src="${image.getData()}" width="1200" height="550" alt="Image article"/>
                <figcaption>${image.getAttribute("alt")}</figcaption>
            </figure>
        </div>

        <!-- Contenu de l'article Wysiwyg -->
        <div class="ops-content-wrapper ops-bloc ops-bloc-small ops-bloc-texte ops-bloc-wysiwyg">
            ${content.getData()}
        </div>

    </article>

</div>

<style>
    /* Cache de la recherche d'asset quand une news est visible */
    .search-asset-portlet, .page-header {
        display: none !important;
    }

    /* Cache de la barre de retour avec titre affichée automatiquement pour les web contents */
    .portlet-body > div > div.h2 {
        display: none;
    }
</style>