<#setting locale = locale />
<#setting date_format="d MMMM yyyy">

<#if entry?has_content>
    <#-- Récupération des champs du contenu web -->
    <#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
    <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
    <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
    <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
    <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
    <#assign imageURL ="" />
    <#if image?has_content>
        <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
    </#if>
    <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
    <#assign publishDate = entry.getModifiedDate() />

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
                    
    <#-- Récupération des catégories "musées" de l'entité -->
    <#assign assetEntryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService") />
    <#assign asset = assetEntryLocalService.getEntry('com.liferay.journal.model.JournalArticle', entry.resourcePrimKey) >
    <#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
    <#assign newsMuseums = assetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "musees") />
    
    <a href="${detailURLFilter}" aria-label="${title?html}" title="${title?html}" class="news-thumbnail" style="background-image: url(${imageURL})">
        <div class="info">
            <div class="date">
                <date><@liferay_ui["message"] key="eu.published-on" /> ${publishDate?date}</date>
            </div>
            <div class="museums">
                <#if newsMuseums?first?has_content>
                    <#assign nbMusees = 0 />
                    <#assign musees = ""/>
                    <#list newsMuseums as category>
                        <#assign nbMusees++ />
                        <#assign musees>
                            ${musees}  <li>${category.getTitle(locale)}</li>
                        </#assign>
                    </#list>
                    <span>
                        ${newsMuseums?first.getTitle(locale)}
                        <#if nbMusees gt 2>
                            <@liferay_ui["message"] key="eu.museum.and-x" arguments="${nbMusees - 1}"/>
                        <#elseif nbMusees gt 1>
                            <@liferay_ui["message"] key="eu.museum.and"/>
                        </#if>
                    </span>
                    <ul class="list-museums">
                        ${musees}
                    </ul>
                </#if>
            </div>
            <p class="title">
                <span>${title}</span>
            </p>
        </div>
    </a>
</#if>