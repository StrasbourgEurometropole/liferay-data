<#setting locale = locale />

<#assign journalArticleLocalService = serviceLocator.findService("com.liferay.journal.service.JournalArticleLocalService")>
<#assign journalArticle = journalArticleLocalService.getArticle(groupId, .vars['reserved-article-id'].data)>
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', journalArticle.getResourcePrimKey()) >
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
<#assign newsTypes = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "type d'actualite") />

<div class="mns-page-experience">
    <header class="container mns-top-experience">
        <div class="small-container">
            <div class="row">
                <h1>${title.data}</h1>
                <div class="mns-meta">
                    <div class="mns-wrap-tag">
                        <span>Magique</span>
                        <span>100% Noël</span>
                        <span>2 jours</span>
                    </div>
                    <span class="mns-location">${lieu.data}</span>
                    <figure class="fit-cover">
                        <img src="${image.data}" width="60" height="60" alt="Nom de l'auteur de l'expérience">
                    </figure>
                </div>
                <div class="mns-bloc-texte">
                    ${text.data}
                </div>
            </div>
        </div>
    </header>
</div>