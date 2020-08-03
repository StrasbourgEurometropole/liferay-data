<!-- Liste de vignettes oeuvres et contenus webs -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#if curEntry.getAssetRenderer().getClassName() == 'eu.strasbourg.service.artwork.model.Artwork'>
        <#assign entry = curEntry.getAssetRenderer().getArtwork() />
        <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
        <#assign entryURL = assetPublisherTemplateHelperService.createRenderUrlMusee("oeuvre",entry.getSources()[0].getTitle(locale)) />
        <#assign detailURL = homeURL + entryURL + "/-/entity/id/" + entry.artworkId />

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
        <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
        <#assign imageURL ="" />
        <#if image?has_content>
            <#assign imageURL = assetPublisherTemplateHelperService.getDocumentUrl(image) />
        </#if>
        <#assign currentURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry) />
        <#assign viewURL = curEntry.getAssetRenderer().getURLViewInContext(renderRequest, renderResponse, currentURL) />
        <div class="entity-thumbnail">
          <div class="entity-thumbnail-image">
            <a href="${viewURL}">
              <img src="${imageURL}">
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
