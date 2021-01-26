<!-- SLIDER VIDEOS SANS MARGE -->

<#if entries?size != 0 >

	<!-- Recuperation de la localisation de l'utilisateur -->
	<#setting locale = locale />

	<!-- Recuperation du créateur de la pétition -->
	<#assign UserLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService")/>
	<#assign user = UserLocalService.getUser(entry.userId) />

	<!-- Recuperation de l'URL de "base" du site -->
	<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
		<#assign homeURL = "/web${layout.group.friendlyURL}/" />
	<#else>
		<#assign homeURL = "/" />
	</#if>
	 
	 <section class="pro-pad-40 pro-bloc-select">
		<div class="container">

			<div class="pro-intro">
				<h2>Les vidéos</h2>
				<a href="${homeURL}videos" class="pro-btn">Tout voir</a>
			</div>

			<div class="row">
			
				<!-- Parcours des entites de l'asset publisher -->
	            <#list entries as curEntry>
				
				<!-- Recuperation de l'entite -->
	            <#assign entry = curEntry.getAssetRenderer().getVideo() />
			
				<div class="col-md-3 col-sm-6 col-xs-12">
					<div class="pro-card-video" data-linkall=".pro-link-all">
						<div class="pro-header">
							<figure class="fit-cover" role="group">
								<img alt="${entry.getTitle(locale)}?imagePreview=1" loading="lazy" width="280" height="175" src="${entry.getImageURL()}">
							</figure>
							<span class="icon-ico-lecteur"></span>
						</div>
						<div class="pro-meta-avis">
							<div class="pro-avis">
								<a href="#pro-avis-like-pro" class="pro-like" title="Mettre j'aime à cette vidéo">${entry.getNbLikes()}</a>
								<a href="#pro-avis-dislike-pro" class="pro-dislike" title="Mettre je n'aime pas à cette vidéo">${entry.getNbDislikes()}</a>
							</div>
							<span class="pro-view"></span>
						</div>
						<a href="${homeURL + 'detail-video/-/entity/id/' + entry.getVideoId()}" title="Vers la page Titre de la vidéo" class="pro-link-all"><h3>${entry.getTitle(locale)}</h3></a>
					</div>
				</div>
				
				</#list>
			</div>
		</div>
	</section>

<#else>
    <div style="height:25px"><div>
</#if>