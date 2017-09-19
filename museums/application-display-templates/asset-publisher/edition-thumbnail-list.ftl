<!-- Liste de vignettes éditions -->
<#setting locale = locale />
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#assign entry = curEntry.getAssetRenderer().getEdition() />
      <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
        <@liferay_portlet.param name="classPK" value="${entry.getEditionId()}" />
        <@liferay_portlet.param name="returnURL" value="${currentURL}" />
      </@liferay_portlet.renderURL>

      <!-- Edition : ${entry.getTitle(locale)} -->
      <div class="entity-thumbnail edition-tumbnail">
        <div class="entity-thumbnail-image">
          <a href="${detailURL}">
            <img src="${entry.getImageURL()}" />
          </a>
        </div>
        <div class="entity-thumbnail-info">
          <#if entry.getPublishedEditionGalleries()?has_content>
            <div class="entity-thumbnail-parent-title">
              <#list entry.getPublishedEditionGalleries() as gallery>
                ${gallery.getTitle(locale)}&nbsp;
              </#list>
            </div>
          </#if>
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