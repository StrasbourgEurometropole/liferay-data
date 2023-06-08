<!-- Vignette fichier -->
<#setting locale = locale />
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
<#assign detailURL = FileEntryHelper.getFileEntryURL(entry.getFileEntryId()) />

<@liferay_portlet.actionURL var="detailURLFilter">
  <@liferay_portlet.param name="userTargetClassId" value="0" />
  <@liferay_portlet.param name="userTargetClassPK" value="${entry.fileEntryId}" />
  <@liferay_portlet.param name="userTargetTitle" value="${entry.getTitle()}" />
  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
</@liferay_portlet.actionURL>

<div class="entity-thumbnail news-tumbnail">
  <div class="entity-thumbnail-image">
    <a href="${detailURLFilter}" download>
      <img src="">
    </a>
  </div>
  <div class="entity-thumbnail-info">
    <div class="entity-thumbnail-title">
      <a href="${detailURLFilter}" download>
        <h4>${entry.getTitle()} </h4><div class="file-type-and-size">${entry.getExtension()?upper_case} — ${FileEntryHelper.getReadableFileEntrySize(entry.getFileEntryId(), locale)}</div>
      </a>
    </div>
  </div>
</div>
