<#if entries?has_content>
  <#list entries as curEntry>
    <#assign artwork = curEntry.getAssetRenderer().getArtwork() />
    <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
      <@liferay_portlet.param name="classPK" value="${artwork.getArtworkId()}" />
      <@liferay_portlet.param name="returnURL" value="${currentURL}" />
    </@liferay_portlet.renderURL>
    <div class="featured-artwork ${entry.getSourceCSSClass()}">
      <div class="featured-artwork-image">
        <a href="${detailURL}">
          <img src="${artwork.getImageURL()}" />
        </a>
        <#if artwork.getImageCopyright(locale)?has_content>
          <div class="image-copyright">
              ${artwork.getImageCopyright(locale)}
          </div>
        </#if>
      </div>
      <div class="featured-artwork-detail">
        <#if artwork.getArtworkCollections()?has_content>
          <div class="featured-artwork-collections">
            <#list artwork.getArtworkCollections() as collection>
              ${collection.getTitle(locale)}&nbsp;
            </#list>
          </div>
        </#if>
        <div class="featured-artwork-title">
          <a href="${detailURL}">
            <h4>${artwork.getTitle(locale)}</h4>
          </a>
        </div>
        <div class="featured-artwork-text">
          <div class="featured-artwork-description">
            ${artwork.getDescription(locale)}
          </div>
          <div class="featured-artwork-link">
            <a href="${detailURL}"> <@liferay_ui["message"] key="read-more" /> </a>
          </div>
        </div>
      </div>
    </div>
  </#list>
</#if>
