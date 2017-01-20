<!-- Détail collection d'oeuvres -->
<#setting locale = locale />
<div class="entity-detail artwork-collection-detail">
  <div class="entity-images">
    <div class="entity-images-main image-with-copyright-on-hover">
      <img src="${entry.getImageURL()}" class="lightbox">
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
    <#if entry.getSources()?has_content>
      <div class="collection-sources">
        <#list entry.getSources() as source>
          <div class="collection-source">
            ${source.getTitle(locale)}
          </div>
        </#list>
      </div>
    </#if>
    <#if entry.getContributors(locale)?has_content>
      <div class="collection-contributors">
         <@liferay_ui["message"] key="eu.artwork.contributors" /> : ${entry.getContributors(locale)}
      </div>
    </#if>
    <div class="entity-description">
      ${entry.getDescription(locale)}
    </div>
  </div>
</div>
<!-- Oeuvres de la collection -->
<#if entry.getPublishedArtworks()?has_content>
  <h3 class="entity-detail-children-title"><@liferay_ui["message"] key="eu.artwork.collection-artworks" /></h3>
  <div class="entity-detail-children artwork-collection-artworks">
    <#list entry.getPublishedArtworks() as artwork>
      <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
          <@liferay_portlet.param name="classPK" value="${artwork.getArtworkId()}" />
          <@liferay_portlet.param name="returnURL" value="${currentURL}" />
      </@liferay_portlet.renderURL>
      <div class="entity-detail-child artwork-collection-artwork ${artwork.getSourceCSSClass()}">
        <div class="entity-detail-child-image">
          <a href="${detailURL}">
            <img src="${artwork.getImageURL()}">
          </a>
        </div>
        <div class="entity-detail-child-info">
          <#if artwork.getSources()?has_content>
            <div class="entity-detail-child-source">
                ${artwork.getSources()[0].getTitle(locale)}
            </div>
          </#if>
          <div class="entity-detail-child-title">
            <a href="${detailURL}">
              <h4>${artwork.getTitle(locale)}</h4>
            </a>
          </div>
        </div>
      </div>
    </#list>
  </div>
</#if>
