<#setting locale = locale />

<#if entry?has_content>
    <#-- Récupération des champs du contenu web -->
    <#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
    <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
    <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
    
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
                    
    <#-- Récupération de la structure -->
    <#assign classNameLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.ClassNameLocalService") />
    <#assign classNameId = classNameLocalService.getClassNameId("com.liferay.journal.model.JournalArticle") />
    <#assign ddmStructureLocalService = serviceLocator.findService("com.liferay.dynamic.data.mapping.service.DDMStructureLocalService") />
    <#assign structure = ddmStructureLocalService.getStructure(groupId, classNameId, entry.DDMStructureKey) />
    
    <a href="${detailURLFilter}" aria-label="${title?html}" title="${title?html}" class="thumbnail-general">
        <div class="title">
            ${title}
            <div class="type">${structure.getName(locale)}</div>
        </div>
        <div class="content">${chapo}</div>
        <span class="basic-link"><@liferay_ui["message"] key="eu.museum.read-next"/></span>
    </a>
</#if>