<!-- Vignette actualité -->

<#setting locale = locale />

<#if entry?has_content>
    <#-- Récupération des champs du contenu web -->
    <#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
    <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
    <#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
    <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
    <#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />
    <#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />
    <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
    <#assign imageURL ="" />
    <#if thumbnail?has_content>
        <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(thumbnail) />
    </#if>
                    
    <#-- Récupération des catégories "Type d'actualité de l'entité -->
    <#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
    <#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

    <#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
    <#assign newsTypes = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "type d'actualite") />

    <#-- Création de l'URL de détail -->
    <#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
    <#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

    <#-- Récupération de DateHelper pour le format date -->
    <#assign dateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.DateHelperService") />

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
                <div style="background-image: url(${imageURL});" class="thumbnail-background" >
                    <#if asset.tagNames?seq_contains("euromag") || asset.tagNames?seq_contains("villemag") || asset.tagNames?seq_contains("webmag")>
                        <div class="mag">MAG'</div>
                        <div class="bg-mag"></div>
                    </#if>
                </div>
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
                    ${dateHelperService.displayShortDate(entry.getModifiedDate()?date, locale)}
                </div>
                <div class="seu-result-infos-bottom">
                     <a href="#" class="seu-add-favorites"
                        data-type="6" 
                        data-title="${title}" 
                        data-url="${detailURL}"
                        data-group-id=${themeDisplay.scopeGroupId}
                        data-id="${entry.getArticleId()}">
                        <span><@liferay_ui.message key='eu.add-to-favorite' /></span>
                    </a>
                </div>
            </div>
        </div>

    </div>
</#if>