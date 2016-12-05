<!-- Détail oeuvre (ADT) - ${entry.getTitle(locale)} -->

<div class="entity-detail artwork-detail">
  <div class="entity-images">
    <div class="entity-images-main">
      <img src="${entry.getImageURL()}" class="lightbox" data-title="${entry.getImageLegendAndCopyright(locale)}">
      <#if entry.getImageLegendAndCopyright(locale)?has_content>
        <div class="entity-images-main-copyright">
            ${entry.getImageLegendAndCopyright(locale)}
        </div>
      </#if>
    </div>
    <div class="entity-images-carousel">
      <div class="entity-images-carousel-buttons">
        <div class="entity-images-carousel-previous">
          
        </div>
        <div class="entity-images-carousel-next">
          
        </div>
      </div>
      <div class="owl-carousel">
        <div class="entity-images-carousel-item">
          <div class="entity-images-carousel-item-image">
            <img src="${entry.getImageURL()}" class="lightbox">
          </div>
        </div>
        <#list entry.imagesURLs as imageURL>
          <div class="entity-images-carousel-item">
            <div class="entity-images-carousel-item-image">
              <img src="${imageURL}" class="lightbox" data-title="${entry.getImagesLegendsAndCopyrights(locale)[imageURL?index]}" />
            </div>
          </div>
        </#list>
      </div>
    </div>
  </div>
  <div class="entity-info">
    <div class="entity-title">
      <h1>${entry.getTitle(locale)}</h1>
    </div>
    <div class="artwork-artist">
      ${entry.getArtistName(locale)}
    </div>
    <div class="artwork-date">
      ${entry.getCreationYear(locale)}
    </div>
    <div class="artwork-technical-information">
      ${entry.getTechnicalInformation(locale)}
    </div>
    <#if entry.getOrigin(locale)?has_content>
      <div class="artwork-origin">
        <@liferay_ui["message"] key="eu.artwork.origin" /> : ${entry.getOrigin(locale)}
      </div>
    </#if>
    <#if entry.getSources()?has_content>
      <div class="artwork-sources">
        <#list entry.getSources() as source>
          <div class="artwork-source">
            ${source.getTitle(locale)}
          </div>
        </#list>
      </div>
    </#if>
    <#if entry.getPublishedArtworkCollections()?has_content>
      <div class="artwork-collections">
        <#list entry.getPublishedArtworkCollections() as collection>
          <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
            <@liferay_portlet.param name="classPK" value="${collection.getCollectionId()}" />
            <@liferay_portlet.param name="returnURL" value="${currentURL}" />
          </@liferay_portlet.renderURL>
          <div class="artwork-collection">
            <a href="${detailURL}">${collection.getTitle(locale)}</a>
          </div>
        </#list>
      </div>
    </#if>
    <#if entry.getNoticeLink(locale)?has_content>
      <div class="artwork-notice">
        <a href="${entry.getNoticeLink(locale)}"><@liferay_ui["message"] key="eu.artwork.see-notice" /></a>
      </div>
    </#if>
    <div class="entity-description">
      ${entry.getDescription(locale)}
    </div>
  </div>
</div>
