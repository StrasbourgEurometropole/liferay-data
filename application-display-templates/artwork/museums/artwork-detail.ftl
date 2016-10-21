<!-- Détail oeuvre (ADT) - ${entry.getTitle(locale)} -->

<div class="artwork-detail">
  <div class="artwork-images">
    <div class="artwork-images-main">
      <img src="${entry.getImageURL()}" class="lightbox">
      <#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />
      <#assign copyright = FileEntryHelper.getImageCopyright(entry.getImageId(), locale) /> 
      <#if copyright?has_content>
        <div class="artwork-images-main-copyright">
            ${copyright}
        </div>
      </#if>
    </div>
    <div class="artwork-images-carousel">
      <div class="artwork-images-carousel-buttons">
        <div class="artwork-images-carousel-previous">
          
        </div>
        <div class="artwork-images-carousel-next">
          
        </div>
      </div>
      <div class="owl-carousel">
        <div class="artwork-images-carousel-item">
          <div class="artwork-images-carousel-item-image">
            <img src="${entry.getImageURL()}" class="lightbox">
          </div>
        </div>
        <#list entry.getImagesURLs() as imageURL>
          <div class="artwork-images-carousel-item">
            <div class="artwork-images-carousel-item-image">
              <img src="${imageURL}" class="lightbox">
            </div>
          </div>
        </#list>
      </div>
    </div>
  </div>
  <div class="artwork-info">
    <div class="artwork-title">
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
    <#if entry.getArtworkCollections()?has_content>
    <#assign targetFriendlyURL = renderRequest.getAttribute("targetFriendlyURL")!"" />
      <div class="artwork-collections">
        <#list entry.getArtworkCollections() as collection>
          <div class="artwork-collection">
            <a href="${targetFriendlyURL}/-/entity/id/${collection.getCollectionId()}">${collection.getTitle(locale)}</a>
          </div>
        </#list>
      </div>
    </#if>
    <#if entry.getNoticeLink(locale)?has_content>
      <div class="artwork-notice">
        <a href="${entry.getNoticeLink(locale)}"><@liferay_ui["message"] key="eu.artwork.see-notice" /></a>
      </div>
    </#if>
    <div class="artwork-description">
      ${entry.getDescription(locale)}
    </div>
  </div>
</div>
