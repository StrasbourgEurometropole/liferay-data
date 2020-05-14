<!-- Oeuvres à la une -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<#if entries?has_content>
  <#list entries as curEntry>
    <#assign artwork = curEntry.getAssetRenderer().getArtwork() />
		<#assign detailURL = homeURL + "oeuvre-musees-strasbourg/-/entity/id/" + artwork.artworkId />
    <div class="featured-artwork ${artwork.sourceCSSClass}">
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
