<#setting locale = locale />
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
<#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
<#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
<#assign imageURL ="" />
<#if image?has_content>
    <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
</#if>

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.getClassNameId()}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.getArticleId()}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="entity-thumbnail news-tumbnail">
  <div class="entity-thumbnail-image">
    <a href="${detailURLFilter}">
      <img src="${imageURL}">
    </a>
  </div>
  <div class="entity-thumbnail-info">
    <div class="entity-thumbnail-title">
      <a href="${detailURLFilter}">
        <h4>${entry.getTitle(locale)}</h4>
      </a>
    </div>
  </div>
</div>
