<!-- Liste de vignettes collection d'oeuvres -->
<#setting locale = locale />
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#assign entry = curEntry.getAssetRenderer().getCollection() />
      <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
        <@liferay_portlet.param name="classPK" value="${entry.getCollectionId()}" />
        <@liferay_portlet.param name="returnURL" value="${currentURL}" />
      </@liferay_portlet.renderURL>

      <!-- Collection d'oeuvre : ${entry.getTitle(locale)} -->
      <div class="entity-thumbnail artwork-collection-thumbnail ${entry.getSourceCSSClass()}">
        <div class="entity-thumbnail-image">
          <a href="${detailURL}">
            <img src="${entry.getImageURL()}" />
          </a>
        </div>
        <div class="entity-thumbnail-info">
          <#if entry.getSources()?has_content>
            <div class="entity-thumbnail-source">
                ${entry.getSources()[0].getTitle(locale)}
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
