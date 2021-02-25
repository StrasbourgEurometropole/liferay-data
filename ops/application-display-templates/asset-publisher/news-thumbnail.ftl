<!-- VIGNETTE ACTUALITE -->

<#-- Chargement de la variable de localisation -->
<#setting locale = locale />

<#-- Récupération de DateHelper pour le format date -->
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

<#-- Récupération de l'XML du contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />

<#-- Champs concernant une actualité -->
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
<#assign imageURL ="" />
<#if image?has_content>
    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
</#if>

<#-- Récupération de la Typologie -->
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
<#assign typologiesList = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "typologie") />

<#-- Création de l'URL de détail -->
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<a href="${detailURL}" class="searchable-item">

    <figure class="fit-cover">
        <img src="${imageURL}" width="1200" height="590" alt="Article image" />
    </figure>

    <div>
        <div class="ops-cats">
            <#list typologiesList as typology>
                <span class="ops-cat">${typology.getTitle(locale)}</span>
            </#list>
        </div>
        <h3>${title}</h3>
        <span class="ops-basic-link"><@liferay_ui.message key="eu.ops.learn.more" /></span>
    </div>

    <script style="display: none" type="text/javascript">
        $('document').ready(function(){
            // Gestion dynamique des classes vis-à-vis de la position de l'élément
            var item = $("a[href='${detailURL}']");
            var itemIndex = $(".searchable-item").index(item);

            if (itemIndex == 0) {
                item.addClass("ops-card-article-highlight");
                $('img', item).attr({'width':1200, 'height':590});
                // Le premier titre est à mettre H2
                $('h3', item).replaceWith(function () {
                    return "<h2>" + $(this).html() + "</h2>";
                });
            } else if (itemIndex < 3) {
                item.addClass("ops-col-50");
                $('img', item).attr({'width':580, 'height':265});
            } else {
                item.addClass("ops-col-25");
                $('img', item).attr({'width':270, 'height':176});
            }
        });
    </script>

</a>