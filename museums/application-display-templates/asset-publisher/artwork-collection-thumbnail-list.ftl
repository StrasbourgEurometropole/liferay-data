<!-- Liste de vignettes collection d'oeuvres -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#assign entry = curEntry.getAssetRenderer().getCollection() />
      <#assign assetPublisherTemplateHelperService = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService")/>
      <#assign entryURL = assetPublisherTemplateHelperService.createRenderUrlMusee("collection",entry.getSources()[0].getTitle(locale)) />
      <#assign detailURL = homeURL + entryURL + "/-/entity/id/" + entry.collectionId />

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