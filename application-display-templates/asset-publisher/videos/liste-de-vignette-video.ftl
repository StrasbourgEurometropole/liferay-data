<!-- Liste de vignette Vidéo -->
<#setting locale = locale />
<#assign AssetCategoryLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryLocalService")>
<#assign AssetCategoryPropertyLocalService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetCategoryPropertyLocalService")>

<#list portletPreferences?keys as prefkey>
    <#if prefkey == "queryValues0">
        <#list portletPreferences[prefkey] as prefvalue>
			<#assign categorie = AssetCategoryLocalService.fetchAssetCategory(prefvalue?number)>
        </#list>
    </#if>
</#list>

<#if entries?has_content>
    <div class="video-grid"> 
        <div class="category"> 
	        <div class="container"> 
		        <h3 class="category-title"><a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/${AssetCategoryPropertyLocalService.getCategoryProperty(categorie.getCategoryId(), "URL").getValue()}"> ${categorie.getTitle(locale)}</a> </h3> 
			    <div class="grid-3"> 
				    <#list entries as curEntry>
				        <#assign video = curEntry.getAssetRenderer().getVideo() />
				        <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
				          <@liferay_portlet.param name="classPK" value="${video.getVideoId()}" />
				          <@liferay_portlet.param name="returnURL" value="${currentURL}" />
				        </@liferay_portlet.renderURL>

				        <div class="video-thumbnail    "> 
					        <a href="${detailURL}">
					        	<img src="${video.getImageURL()}">
					        </a> 
					        <div class="meta"> 
						        <div class="title">
						        	<a href="${detailURL}">${video.getTitle(locale)}</a>
						        </div> 
						        <div class="provider">${video.getProvidersLabel(locale)}</div> 
						        <div class="date">${video.getPublicationDate()?string("dd/MM/yyyy")}</div> 
					        </div> 
				        </div> 
				    </#list>
				</div>
		        <div role="button" class="btn btn-more"></div> 
		        <div class="see-all hidden"> 
		        	<a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/${AssetCategoryPropertyLocalService.getCategoryProperty(categorie.getCategoryId(), "URL").getValue()}"><@liferay.language key="eu.videos-theme.see-all-videos" /></a> 
		        </div> 
	        </div>
        </div>
    </div>
</#if>