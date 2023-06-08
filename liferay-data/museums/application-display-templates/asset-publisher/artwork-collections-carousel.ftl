<!-- Carousel collection d'oeuvre -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<#if entries?has_content>
    <div class="artwork-collections-carousel">
        <h3 class="artwork-carousel-title">
            <span><@liferay_ui["message"] key="eu.others" /></span><@liferay_ui["message"] key="eu.collections" /></h3>
        <div class="owl-carousel">
          <#list entries as curEntry>
            <#assign collection = curEntry.getAssetRenderer().getCollection() />
            <#assign viewURL = homeURL + "collection-musees-strasbourg/-/entity/id/" + collection.collectionId />
            <div class="artwork-collection"> 
                <div class="artwork-collection-image">
                    <a href="${viewURL}" data-text="<@liferay_ui.message key="learn-more" />">
                        <img src="${collection.getImageURL()}" >
                    </a>
                </div>
                <div class="artwork-collection-title">
                    <h4><a href="${viewURL}">${collection.getTitle(locale)}</a></h4>
                </div>
            </div>
          </#list>
        </div>
    </div>
</#if>
