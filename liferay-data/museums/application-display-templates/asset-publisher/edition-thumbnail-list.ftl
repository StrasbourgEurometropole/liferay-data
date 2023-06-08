<!-- Liste de vignettes éditions -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#assign entry = curEntry.getAssetRenderer().getEdition() />
      <#assign detailURL = homeURL + "detail-edition/-/entity/id/" + entry.editionId />

      <!-- Edition : ${entry.getTitle(locale)} -->
      <div class="entity-thumbnail edition-tumbnail">
        <div class="entity-thumbnail-image">
          <a href="${detailURL}">
            <img src="${entry.getImageURL()}" />
          </a>
        </div>
        <div class="entity-thumbnail-info">
          <#if entry.getPublishedEditionGalleries()?has_content>
            <div class="entity-thumbnail-parent-title">
              <#list entry.getPublishedEditionGalleries() as gallery>
                ${gallery.getTitle(locale)}&nbsp;
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
    </#list>
  </div>
</div>