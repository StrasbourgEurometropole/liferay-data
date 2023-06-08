<!-- Détail Vidéo -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>

<div class="container"> 
	<h2 class="video-title">${entry.getTitle(locale)}</h2> 
	<div class="video-player-wrapper"> 
		<div class="video-player">
			${entry.getPlayer(locale)}
		</div>
	</div> 
	<div class="grid-2-1"> 
		<div class="infos"> 
			<div class="meta"> 
				<div class="channels"> 
					<span> 
						${entry.getChannelsLabel(locale)}
					</span> 
				</div> 
				<div class="provider"> 
					<span>
						<#list entry.getProviders() as provider>
							<#if provider?counter gt 1>
								-
							</#if>
							<a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/${entry.getCategoryURL(provider)}">${provider.getTitle(locale)}</a>
						 </#list>
					</span> 
				</div> 
				<div class="date"> 
					<span>${entry.getPublicationDate()?string("dd/MM/yyyy")}</span> 
				</div> 
			</div> 
			<div class="description"> 
				<h3><@liferay.language key="description" /></h3> 
				${entry.getDescription(locale)} 
			</div> 
			<#if entry.getCopyright(locale) != "">
				<div class="source"> 
					<strong><@liferay.language key="credit" /></strong>&nbsp;${entry.getCopyright(locale)}
				</div> 
			</#if>
		</div> 
		<div class="other-videos"> 
			<div class="suggestions-wrapper"> 
				<h3><@liferay.language key="suggestions" /></h3> 
				<div class="suggestions"> 
					<div class="video-grid"> 
						<div class="category"> 
							<#list entry.getSuggestions(locale) as suggestion >
 
								<div class="video-thumbnail"> 
									<a href="${homeURL}video/-/entity/id/${suggestion.videoId}">
										<img src="${suggestion.getImageURL()}" alt="${suggestion.getTitle(locale)}">
									</a> 
									<div class="meta"> 
										<div class="title"> 
											<a href="${homeURL}video/-/entity/id/${suggestion.videoId}">
												<span class="trimTo70">${suggestion.getTitle(locale)}</span> 
											</a> 
										</div> 
										<div class="provider">
											<#list suggestion.getProviders() as provider>
												<#if provider?counter gt 1>
													-
												</#if>
												${provider.getTitle(locale)}
											 </#list>
						  				</div> 
										<div class="date">${suggestion.getPublicationDate()?string("dd/MM/yyyy")}</div> 
									</div> 
								</div> 
							</#list>
						</div> 
					</div> 
				</div> 
			</div> 
		</div> 
	</div> 
</div>
