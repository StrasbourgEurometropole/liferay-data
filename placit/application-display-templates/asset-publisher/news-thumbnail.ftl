<!-- Vignette actualité -->

<!-- Chargement de la variable de localisation -->
<#setting locale = locale />


<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />



<#-- Récupération des champs du contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
<#assign resourcePrimKey = docXml.valueOf("//dynamic-element[@name='resourcePrimKey']/dynamic-content/text()") />
<#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
<#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />

<#-- Récupération des catégories "Territoire de l'entité -->
<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
<#assign territoires = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "territoire") />

<#-- Création de l'URL de détail -->
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />
<#-- Template de la vignette -->

<div class="col-md-3 col-sm-6 col-xs-12" style="min-height: 424px;">
	<a href="${detailURL}" title="Lien vers la page (nom de la page)" class="pro-bloc-actu">			
		<div class="img">
			<figure role="group">
				<img src='${thumbnail}' alt="Image" width="360" height="174" class="fit-cover"/>
			</figure>
			<span>
				<#list territoires as t>
					${t.getTitle(locale)}<#sep>, </#sep>
				</#list>
			</span>
		</div>
		<div class="content">
			<span class="publication">Publiée le ${entry.getModifiedDate()?datetime?string("dd MMMM yyyy")}</span>
			<h3>${title}</h3>
			<p>${chapo?replace("<[^>]*>", "", "r")[0..*100]}...</p>
			<span class="link">Lire la suite</span>
		</div>
	</a>
</div>

