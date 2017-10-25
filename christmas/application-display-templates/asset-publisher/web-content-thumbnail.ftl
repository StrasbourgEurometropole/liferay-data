<#setting locale = locale />

<!-- Vignette contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign catcher = docXml.valueOf("//dynamic-element[@name='catcher']/dynamic-content/text()") />
<#assign text = docXml.valueOf("//dynamic-element[@name='text']/dynamic-content/text()") />

<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="${entry.getClassNameId()}" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.getArticleId()}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle(locale)}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="mns-item-results">
    <a href="${detailURLFilter}">
        <h3>${title}</h3>
        <#if catcher?has_content>
            <p>${catcher?replace("<[^>]*>", "", "r")[0..*400]}...</p>
        <#else>
            <p>${text?replace("<[^>]*>", "", "r")[0..*400]}...</p>
        </#if>
        <span class="basic-link"><@liferay_ui.message key="eu.discover" /></span>
    </a>
</div>