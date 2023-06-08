<!-- Détail vidéo -->
<#setting locale = locale />
<div class="video-detail">
  <div class="video-detail-player">
     ${entry.getPlayer(locale)}
  </div>
  <div class="video-detail-info">
    <h1 class="video-detail-title">${entry.getTitle(locale)}</h1>
    <#if entry.getCopyright(locale)?has_content>
      <div class="video-copyright">
        ${entry.getCopyright(locale)}
      </div>
    </#if>
    <#if entry.getTranscriptionDownloadURL()?has_content>
      <div class="video-transcription">
        <a href="${entry.getTranscriptionDownloadURL()}" download><@liferay_ui["message"] key="eu.video.transcription" /></a>
      </div>
    </#if>
    <#if entry.getDescription(locale)?has_content>
      <div class="video-description">
         ${entry.getDescription(locale)}
      </div>
    </#if>
    <#if entry.getPublishedVideoGalleries()?has_content>
      <div class="video-galleries">
        <#list entry.getPublishedVideoGalleries() as gallery>
        <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
          <@liferay_portlet.param name="classPK" value="${gallery.getGalleryId()}" />
          <@liferay_portlet.param name="returnURL" value="${currentURL}" />
        </@liferay_portlet.renderURL>
          <div class="video-gallery">
            <a href="${detailURL}">${gallery.getTitle(locale)}</a>
          </div>
        </#list>
      </div>
    </#if>
  </div>
</div>