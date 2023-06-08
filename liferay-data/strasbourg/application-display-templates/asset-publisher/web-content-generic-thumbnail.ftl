<!-- Vignette contenu web -->

<#setting locale = locale />

<!-- Vignette contenu web - moteur de recherche général-->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
<#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />

<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<#-- Récupération des catégories "Type d'actualité de l'entité -->
<#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
<#assign newsTypes = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "type d'actualite") />

<#-- Récupération de DateHelper pour le format date -->
<#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.getClassNameId()}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.getArticleId()}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>


<#if detailURL?contains("/-/")>
    <#assign favoriteType = 6 />
<#else>
    <#assign favoriteType = 7 />
</#if>


<div class="wi-search-result wi-search-generic wi-search-cw">
    <div class="seu-result-left">
        <div class="seu-result-icon"></div>
    </div>
    <div class="seu-result-right">
        <a class="seu-result-content" href="${detailURLFilter}">
            <h2 class="seu-result-title">${title}</h2>
            <div class="seu-result-catcher">${chapo?replace("<[^>]*>", "", "r")[0..*100]}...</div>
            <#if newsTypes?has_content>
                <div class="seu-result-category">
                    <#list newsTypes as type>
                        ${type.getTitle(locale)}<#sep>, </#sep>
                    </#list>
                </div>
            </#if>
        </a>
        <div class="seu-result-infos">
            <div class="seu-result-infos-top">
                ${dateHelperService.displayShortDate(entry.getModifiedDate()?date, locale)}
            </div>
            <div class="seu-result-infos-bottom">
                <a href="#" class="seu-add-favorites"
                data-type="${favoriteType}" 
                data-title="${entry.getTitle(locale)}" 
                data-url="${detailURL}"
                data-group-id=${themeDisplay.scopeGroupId}
                data-id="${entry.getArticleId()}">
                    <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
                </a>
            </div>
        </div>
    </div>

</div>
