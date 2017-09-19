<!-- Liste de vignettes événements -->
<#setting locale = locale />
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#assign entry = curEntry.getAssetRenderer().getEvent() />
      <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
        <@liferay_portlet.param name="classPK" value="${entry.getEventId()}" />
        <@liferay_portlet.param name="returnURL" value="${currentURL}" />
      </@liferay_portlet.renderURL>

      <!-- Event : ${entry.getTitle(locale)} -->
      <div class="entity-thumbnail event-tumbnail">
        <div class="entity-thumbnail-image">
          <a href="${detailURL}">
            <img src="${entry.getImageURL()}" />
          </a>
        </div>
        <div class="entity-thumbnail-info">
          <div class="entity-thumbnail-parent-title">
            ${entry.getEventScheduleDisplay(locale)}
          </div>
          <div class="entity-thumbnail-title">
            <a href="${detailURL}">
              <h4>${entry.getTitle(locale)}</h4>
            </a>
          </div>
        </div>
      </div>
    </#list>
  </div>
</div>