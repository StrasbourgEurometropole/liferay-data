<!-- Détail collection d'oeuvres (ADT) - ${entry.getTitle(locale)} -->

<div class="entity-detail artwork-collection-detail">
  <div class="entity-images">
    <div class="entity-images-main">
      <img src="${entry.getImageURL()}" class="lightbox">
      <#if entry.getImageCopyright(locale)?has_content>
        <div class="entity-images-main-copyright">
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
  <h3 class="artwork-collection-artworks-title"><@liferay_ui["message"] key="eu.artwork.collection-artworks" /></h3>
  <div class="artwork-collection-artworks">
    <#list entry.getPublishedArtworks() as artwork>
      <#assign targetFriendlyURL = renderRequest.getAttribute("targetFriendlyURL")!"" />
      <div class="artwork-collection-artwork ${artwork.getSourceCSSClass()}">
        <div class="artwork-collection-artwork-image">
          <a href="${targetFriendlyURL}/-/entity/id/${artwork.getArtworkId()}">
            <img src="${artwork.getImageURL()}">
          </a>
        </div>
        <div class="artwork-collection-artwork-info">
          <#if artwork.getSources()?has_content>
            <div class="artwork-collection-artwork-source">
                ${artwork.getSources()[0].getTitle(locale)}
            </div>
          </#if>
          <div class="artwork-collection-artwork-title">
            <a href="${targetFriendlyURL}/-/entity/id/${artwork.getArtworkId()}">
              <h4>${artwork.getTitle(locale)}</h4>
            </a>
          </div>
        </div>
      </div>
    </#list>
  </div>
</#if>
