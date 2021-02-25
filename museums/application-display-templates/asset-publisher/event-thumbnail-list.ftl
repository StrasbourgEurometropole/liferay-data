<!-- Liste de vignettes événements -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<div class="search-asset-portlet">
  <div class="search-asset-results">
    <#list entries as curEntry>
      <#assign entry = curEntry.getAssetRenderer().getEvent() />
      <#assign detailURL = homeURL + "evenement-des-musees-de-strasbourg/-/entity/id/" + entry.eventId />

      <!-- Event : ${entry.getTitle(locale)} -->
      <div class="entity-thumbnail event-tumbnail">
        <div class="entity-thumbnail-image">
          <a href="${detailURL}">
            <img src="${entry.getImageURL()}" />
          </a>
        </div>
        <div class="entity-thumbnail-info">
          <div class="entity-thumbnail-parent-title">
            ${entry.getEventScheduleDisplay(locale)}
          </div>
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