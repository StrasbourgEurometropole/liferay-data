<!-- Vignette Vidéo -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>

<h2 class="subtitle video"><@liferay_ui.message key='eu.last-videos' /></h2>
<#if entries?has_content>
	<div class="content agenda galeries video">
		<div class="lfr-search-container ">
			<div summary class="list-evt">
                <#list entries as curEntry>
                    <#assign video = curEntry.getAssetRenderer().getVideo() />
					<#assign detailURL = homeURL + "detail-video/-/entity/id/" + video.videoId />

					<div class="event portlet-video-item" headers="pihe_col-1">
						<script type="text/javascript">
							/*<![CDATA[*/
							function _CusVideoFOPortlet_WAR_Cusallhook_themeSearch(b){
								var a=document.getElementById("_CusVideoFOPortlet_WAR_Cusallhook_videoTheme");
								for(i=0;i<a.length;i++){
									if(a.options[i].value==b){
										a.selectedIndex=i
									}
								}
								submitForm(document._CusVideoFOPortlet_WAR_Cusallhook_fm1)
							};
							/*]]>*/
						</script>
						<div class="entry-category">
							<#if video.themes?has_content>
								<p class="gray">
									<#list video.themes as theme>
									    ${theme.getTitle(locale)} 
									</#list>
								</p>
							</#if>
						</div>
						<div class="entry-image">
							<a href="${detailURL}" title="${video.getTitle(locale)}"><img src="${video.getImageURL()}" alt="${video.getTitle(locale)}"> </a>
						</div>
						<div class="entry-header">
							<h2><a href="${detailURL}" title="${video.getTitle(locale)}">${video.getTitle(locale)}</a></h2>
						</div>
						<footer class="entry-meta">
							<time>${video.getPublicationDate()?string("dd/MM/yyyy")}</time>
							<a href="${detailURL}" title="<@liferay_ui.message key='eu.read-video' />" class="btn-more"><@liferay_ui.message key='eu.read-video' /></a>
							<div class="clearfix"></div>
						</footer>
					</div>
                </#list>
			</div>
		</div>
	</div>
</#if>									