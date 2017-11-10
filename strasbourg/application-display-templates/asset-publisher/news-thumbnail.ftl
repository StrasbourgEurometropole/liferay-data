<!-- Vignette contenu web -->

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

<#-- Template -->
<div class="wi-search-result wi-edition-thumbnail">
    <div class="seu-result-left seu-result-thumbnail">
        <a href="${detailURLFilter}" title="${title}">
            <div class="thumbnail-background" style="background-image: url(${thumbnail});"></div>
        </a>
    </div>
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURLFilter}" title="${title}">
            <h2 class="seu-result-title">${title}</h2>
            <div class="seu-result-catcher">
                ${chapo?replace("<[^>]*>", "", "r")[0..*100]}...
            </div>
            <div class="seu-result-category">
                <#list newsTypes as type>
                    ${type.getTitle(locale)}<#sep>, </#sep>
                </#list>
            </div>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
                ${entry.getModifiedDate()?datetime?string("dd/MM/yyyy")}
            </div>
            <div class="seu-result-infos-bottom"> 
            </div>
        </div>
    </div>

</div>
