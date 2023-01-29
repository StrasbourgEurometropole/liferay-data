<#setting locale = localeUtil.getDefault() />
<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")>
<#assign journalArticle = journalArticleLocalService.getArticle(groupId, .vars['reserved-article-id'].data)>
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', journalArticle.getResourcePrimKey()) >
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<section id="news-detail">
    <div class="content col-md-6">
        <h1 class="news-title">
            ${title.getData()}
        </h1>
        <#assign displayDate = .vars['reserved-article-display-date'].getData()?date("EEE, dd MMM yyyy hh:mm:ss Z")/>
        <#assign modifiedDate = .vars['reserved-article-modified-date'].getData()?date("EEE, dd MMM yyyy hh:mm:ss Z")/>
        <div class="news-dates">
            <date><@liferay_ui["message"] key="eu.published-on" /> ${displayDate?string["dd MMMM yyyy"]}</date>
            <date><@liferay_ui["message"] key="eu.updated-on" /> ${modifiedDate?string["dd MMMM yyyy"]}</date>
        </div>
        <div class="news-museums">
            <#assign newsMuseums = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "musees") />
            <#if newsMuseums?has_content>
                <#list newsMuseums as category>
                    ${category.getTitle(locale)}
                    <#sep>,&nbsp;</#sep>
                </#list>
            </#if>
        </div>
        <div class="news-types">
            <#assign newsThematics = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "thematique") />
            <#if newsThematics?has_content>
                <#list newsThematics as category>
                    ${category.getTitle(locale)}
                    <#sep><span>/&nbsp;</span></#sep>
                </#list>
            </#if>
        </div>
        <#if (image.getData())?? && image.getData() != "">
            <div class="image-with-copyright-on-hover">
                <img alt="${image.getAttribute("alt")?html}" title="${image.getAttribute("alt")?html}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" />
                <#if image.getAttribute("alt")?has_content>
                    <div class="copyright"><span>C</span><span>${image.getAttribute("alt")}</span></div>
                </#if>
            </div>
        </#if>
        <div class="news-chapo">
            ${chapo.getData()}
        </div>
        <div class="news-content">
            ${content.getData()}
        </div>
    </div>
</section>
<style>
.search-asset-portlet, .page-header {
    display: none !important;
}
section#header {
    display: none !important;
}
</style>