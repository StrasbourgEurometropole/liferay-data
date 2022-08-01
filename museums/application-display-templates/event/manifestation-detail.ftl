<!-- Détail manifestation -->
<#setting locale = locale />
<div class="event-detail manifestation-detail">
  <div class="event-header">
    <h1 class="event-title">
      ${entry.getTitle(locale)}
    </h1>
    <div class="event-dates">
      ${entry.getManifestationScheduleDisplay(locale)}
    </div>
  </div>
  <div class="event-info">
    <div class="event-60">
      <div class="image-with-copyright-on-hover">
        <img src="${entry.getImageURL()}" class="lightbox">
        <#if entry.getImageCopyright(locale)?has_content>
          <div class="image-copyright">
              ${entry.getImageCopyright(locale)}
          </div>
        </#if>
      </div>
      <#if entry.getDescription(locale)?has_content >
        <div class="event-info-section event-description">
          <h4><@liferay_ui.message key="description" /></h4>
          ${entry.getDescription(locale)}
        </div>
      </#if>

      <!-- Evénements de la manifestation -->
      <#if entry.getPublishedEvents()?has_content>
        <div class="entity-detail event-info-section event-manifestations">
          <h4><@liferay_ui["message"] key="eu.events" /></h4>
          <div class="entity-detail-children">
            <#list entry.getPublishedEvents() as event>
              <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
                <@liferay_portlet.param name="classPK" value="${event.getEventId()}" />
                <@liferay_portlet.param name="title" value="${entry.getNormalizedTitle(locale)}" />
                <@liferay_portlet.param name="returnURL" value="${currentURL}" />
              </@liferay_portlet.renderURL>
              <div class="entity-detail-child">
                <div class="entity-detail-child-image">
                  <a href="${detailURL}">
                    <img src="${event.getImageURL()}">
                  </a>
                </div>
                <div class="entity-detail-child-info">
                  <div class="entity-detail-child-title">
                    <a href="${detailURL}">
                      <h4>${event.getTitle(locale)}</h4>
                    </a>
                  </div>
                </div>
              </div>
            </#list>
          </div>
        </div>
      </#if>
    </div>
</div>
