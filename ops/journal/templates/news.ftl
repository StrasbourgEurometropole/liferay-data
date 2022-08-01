<!-- DETAIL ACTUALITE -->

<#-- Chargement de la variable de localisation -->
<#setting locale = localeUtil.getDefault() />
<#-- Récupération des dates inhérentes à l'article -->
<#assign publishedDate = .vars['reserved-article-display-date'].getData()?date("EEE, dd MMM yyyy hh:mm:ss Z")/>
<#setting locale = locale />
<#-- Récupération de l'id du webcontent -->
<#assign journalArticleId = .vars['reserved-article-id'].data>
<#assign journalArticleResourceLocalServiceUtil = staticUtil["com.liferay.journal.service.JournalArticleResourceLocalServiceUtil"]>
<#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>

<#-- Récupération des catégories associées -->
<#assign articleResourcePK = journalArticleResourceLocalServiceUtil.getArticleResourcePrimKey(groupId, journalArticleId)/>
<#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",articleResourcePK) >

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
                <time datetime="${publishedDate?string.iso}">
                    ${publishedDate?string["dd/MM/yyyy"]}
                </time>
            </span>
        </header>

        <!-- Image à la Une -->
        <div class="ops-content-wrapper ops-bloc-texte ops-image-une">
            <figure class="fit-cover">
                <img src="${image.getData()}" width="1200" height="550" alt="Image article"/>
                <#if image.getChild("copyright").getData()?has_content>
                    <figcaption>${image.getChild("copyright").getData()}</figcaption>
                </#if>
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