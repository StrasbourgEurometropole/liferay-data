<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<div class="video-wrapper image-wrapper">
	<div class="header-image">
	</div>
	<div class="cover">
		<a href="${homeURL}accueil" class="home-link">
		</a>
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
					<a href="${homeURL}accueil" class="logo">
						<img class="desktop-only hide-tablet" src="/o/videos-theme/images/logo/logo_video_grey.png" />
						<img class="mobile-only show-tablet" src="/o/videos-theme/images/logo/logo_video.png" />
					</a> 
					<a href="${homeURL}accueil">
						<span class="site-subtitle"><@liferay.language key='eu.videos-theme.description-page' /></span>
					</a>
					<div class="action mobile-only">
						<img src="/o/videos-theme/images/ui/search.png" title="Search" alt="Search" id="search-toggle" />
						<a id="nav-toggle" href="accueil"><span></span></a>
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
	</div>
</div>
<style>
	.header-image {
		background-image: url("${image.getData()}");
	}
</style>