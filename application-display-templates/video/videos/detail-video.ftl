<!-- Détail Vidéo -->
<#setting locale = locale />

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
						<#list entry.getChaines() as chaine>
							<#if chaine?counter gt 1>
								-
							</#if>
							<a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/${entry.getCategoryURL(chaine)}">${chaine.getTitle(locale)}</a>
						 </#list>
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
								<#assign fromSearch = renderRequest.getAttribute("fromSearchPortlet")!false >
								<#assign plId = 0 />
								<#if fromSearch>
								  <#assign plId = renderRequest.getAttribute("classNameLayoutId")[entry.getModelClassName()] />
								</#if>

								<@liferay_portlet.renderURL plid=plId var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
								  <@liferay_portlet.param name="classPK" value="${suggestion.getVideoId()}" />
								  <@liferay_portlet.param name="returnURL" value="${currentURL}" />
								</@liferay_portlet.renderURL>

								<@liferay_portlet.actionURL var="detailURLFilter">
								  <@liferay_portlet.param name="userTargetClassId" value="${suggestion.assetEntry.classNameId}" />
								  <@liferay_portlet.param name="userTargetClassPK" value="${suggestion.assetEntry.classPK}" />
								  <@liferay_portlet.param name="userTargetTitle" value="${suggestion.getTitle(locale)}" />
								  <@liferay_portlet.param name="detailURL" value="${detailURL}" />
								  <@liferay_portlet.param name="searchLogId" value="${renderRequest.getAttribute('searchLogId')!0}" />
								</@liferay_portlet.actionURL>
 
								<div class="video-thumbnail"> 
									<a href="${detailURLFilter}" >
										<img src="${suggestion.getImageURL()}" alt="${suggestion.getTitle(locale)}">
									</a> 
									<div class="meta"> 
										<div class="title"> 
											<a href="${detailURLFilter}" >
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