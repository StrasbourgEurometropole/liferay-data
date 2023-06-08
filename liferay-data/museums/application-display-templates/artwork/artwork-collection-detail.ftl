<!-- Détail collection d'oeuvres -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
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
      
      <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
      <#assign entryURL = assetPublisherTemplateHelperService.createRenderUrlMusee("oeuvre",entry.getSources()[0].getTitle(locale)) />
      <#assign detailURL = homeURL + entryURL + "/-/entity/id/" + artwork.artworkId />
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
