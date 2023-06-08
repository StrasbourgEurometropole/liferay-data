<!-- Détail galerie de vidéos -->
<#setting locale = locale />
<div class="entity-detail edition-gallery-detail">
  <div class="entity-images">
    <div class="entity-images-main image-with-copyright-on-hover">
      <img src="${entry.getImageURL()}" alt="" class="lightbox">
      <#if entry.getImageCopyright(locale)?has_content>
        <div class="entity-images-main-copyright image-copyright">
            ${entry.getImageCopyright(locale)}
        </div>
      </#if>
    </div>
  </div>
  <div class="entity-info">
    <div class="entity-title">
      <h1>${entry.getTitle(locale)}</h1>
    </div>
    <div class="entity-children-count">
      ${entry.getPublishedVideos()?size} 
      <#if (entry.getPublishedVideos()?size > 1)>
        <@liferay_ui["message"] key="eu.video.videos" />
      <#else>
        <@liferay_ui["message"] key="eu.video.video" />
      </#if>
    </div>
    <div class="entity-description">
      ${entry.getDescription(locale)}
    </div>
  </div>
</div>

<!-- Vidéos de la galerie -->
<#if entry.getPublishedVideos()?has_content>
  <h3 class="entity-detail-children-title">
    <#if (entry.getPublishedVideos()?size > 1)>
      <@liferay_ui["message"] key="eu.video.gallery-videos" />
    <#else>
      <@liferay_ui["message"] key="eu.video.gallery-video" />
    </#if>
  </h3>
  <div class="entity-detail-children">
    <#list entry.getPublishedVideos() as video>
      <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
        <@liferay_portlet.param name="classPK" value="${video.getVideoId()}" />
        <@liferay_portlet.param name="returnURL" value="${currentURL}" />
      </@liferay_portlet.renderURL>
      <div class="entity-detail-child">
        <div class="entity-detail-child-image">
          <a href="${detailURL}">
            <img src="${video.getImageURL()}">
          </a>
        </div>
        <div class="entity-detail-child-info">
          <div class="entity-detail-child-title">
            <a href="${detailURL}">
              <h4>${video.getTitle(locale)}</h4>
            </a>
          </div>
        </div>
      </div>
    </#list>
  </div>
</#if>
