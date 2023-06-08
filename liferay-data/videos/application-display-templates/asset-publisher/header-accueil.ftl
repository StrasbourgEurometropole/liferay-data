<!-- Header accueil -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>
<div class="video-wrapper ">
	<div id="featured-video-container"></div>
	</c:if>
	<div class="cover">
		<div class="strasbourg-bar">
			<div class="container">
				<a href="//www.strasbourg.eu" target="_blank" title="Strasbourg.eu (<@liferay.language key='eu.new-window' />)"> 
					<img class="strasbourg-logo" src="/o/videos-theme/images/logo/logo_strasbourg.png" />
				</a>
			</div>
		</div>
		<div class="site-title-row">
			<div class="container">
				<h1 class="site-title">
					<a href="#" class="logo">
						<img src="/o/videos-theme/images/logo/logo_video.png" />
					</a> 
					<a href="#">
						<span class="site-subtitle"><@liferay.language key='eu.videos-theme.description-page' /></span>
					</a>
					<div class="action mobile-only">
						<img src="/o/videos-theme/images/ui/search.png" title="Search" alt="Search" id="search-toggle" />
						<a id="nav-toggle" href="#"><span></span></a>
					</div>
					<div class="search-box">
						<form class="search-form">
							<input type="text" name="search-input" class="search-input" id="search-input" placeholder="Rechercher">
							<button class="arrow-box" id="search" type="submit" role="button">OK</button> 
						</form>
					</div>
				</h1>
			</div>
		</div>
		<div class="video-data">
			<div class="text">
				<h2 class="video-title">
					<span> <a href="#"></a>
					</span>
				</h2>
				<h3 class="video-meta small-hidden tiny-hidden">
					<span class="channels">
					</span><br> <span class="date"><date></date>
					</span> <br> <span class="provider">
					</span><br>
				</h3>
			</div>
			<div class="video-play-button btn"><@liferay.language key="eu.videos-theme.see-video" /></div>
		</div>
		<div class="video-carousel-controls">
			<ul>
			</ul>
		</div>
		<div class="video-carousel-arrows">
			<div class="container">
				<div id="previous-video" class="btn arrow left"></div>
				<div id="next-video" class="btn arrow right"></div>
			</div>
		</div>
		<div class="owl-carousel">
			<#if entries?has_content>
			    <#list entries as curEntry>
			        <#assign video = curEntry.getAssetRenderer().getVideo() />
					<div class="owl-video-tumbnail">
						<a href="${homeURL}video/-/entity/id/${video.getVideoId()}" style="display: inline;">
							<img src="${video.getImageURL()}" title="${video.getTitle(locale)}" />
						</a>
						<div class="owl-video-cover">
							<a href="${homeURL}video/-/entity/id/${video.getVideoId()}" style="display: block; width: 100%; height: 100%;"></a>
							<div class="owl-video-text">
								<h2 class="video-title">
									<span>
										<a href="${homeURL}video/-/entity/id/${video.getVideoId()}" style="display: inline;">
											${video.getTitle(locale)}
										</a>
									</span>
								</h2>
								<div class="date">
									<span>${video.getPublicationDate()?string("dd/MM/yyyy")}</span>
								</div>
								<div class="provider">
									<span>${video.getProvidersLabel(locale)}</span>
								</div>
							</div>
						</div>
					</div>
			    </#list>
			</#if>
		</div>
	</div>
</div>

<#if entries?has_content>
    <#assign videos = "[" />
    <#list entries as curEntry>
        <#assign video = curEntry.getAssetRenderer().getVideo() />
		<#if curEntry?counter gt 1>
    		<#assign videos = videos + "," />
		</#if>
    	<#assign videos = videos + "{" />
    	<#assign videos = videos + "'source': '${video.getSource(locale)}'," />
    	<#assign videos = videos + "'player': '${video.getPlayerHeaderVideo(locale)}'," />
    	<#assign videos = videos + "'title': '${video.getTitle(locale)?js_string}'," />
    	<#assign videos = videos + "'publicationDate': '${video.getPublicationDate()?string(\"dd/MM/yyyy\")}'," />
    	<#assign videos = videos + "'channels': '${video.getChannelsLabel(locale)?js_string}'," />
    	<#assign videos = videos + "'provider': '${video.getProvidersLabel(locale)}'" />
    	<#assign videos = videos + "}" />
    </#list>
    <#assign videos = videos + "]" />
</#if>
<script>
	var videos = ${videos};
</script>