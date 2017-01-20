<#setting locale = locale />
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#if curEntry.getAssetRenderer().getClassName() == 'eu.strasbourg.service.artwork.model.Artwork'>
        <#assign entry = curEntry.getAssetRenderer().getArtwork() />
        <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
          <@liferay_portlet.param name="classPK" value="${entry.getArtworkId()}" />
          <@liferay_portlet.param name="returnURL" value="${currentURL}" />
        </@liferay_portlet.renderURL>

        <!-- Oeuvre : ${entry.getTitle(locale)} -->
        <div class="entity-thumbnail artwork-thumbnail ${entry.getSourceCSSClass()}">
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
            <#if entry.getArtworkCollections()?has_content>
              <div class="entity-thumbnail-parent-title">
                <#list entry.getArtworkCollections() as collection>
                  ${collection.getTitle(locale)}&nbsp;
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
      <#else>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
        <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
        <div class="entity-thumbnail">
          <div class="entity-thumbnail-image">
            <a href="${viewURL}">
              <img src="${image}">
            </a>
          </div>
          <div class="entity-thumbnail-info">
            <div class="entity-thumbnail-title">
              <a href="${viewURL}">
                <h4>${title}</h4>
              </a>
            </div>
          </div>
        </div>
      </#if>
    </#list>
  </div>
</div>
