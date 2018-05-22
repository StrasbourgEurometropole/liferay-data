<!-- Vignette actualité -->

<!-- Chargement de la variable de localisation -->
<#setting locale = locale />

<#-- Récupération des champs du contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
<#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
<#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />

<#-- Récupération des catégories "Type d'actualité de l'entité -->
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
<#assign newsTypes = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "type d'actualite") />

<#-- Création de l'URL de détail -->
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.getClassNameId()}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.getArticleId()}" />
  <@liferay_portlet.param name="userTargetTitle" value="${title}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<#-- Template de la vignette -->
<div class="col-md-6 col-sm-12 col-xs-12">
    <a href="detail-article.html" title="Lien vers la page (nom de la page)" class="pro-bloc-actu pro-bloc-actu-large">
        <div class="img">
            <figure role="group">
                <img src='assets/images/medias/img-projet-large.jpg' alt="Image agenda" width="592" height="342" class="fit-cover"/>
            </figure>
            <span>Nom du quartier</span>
        </div>
        <div class="content">
            <span class="publication">Publiée le 04 décembre 2017</span>
            <h3>Titre de l'actualité</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore...</p>
            <span class="link">Lire la suite</span>
        </div>
    </a>
</div>