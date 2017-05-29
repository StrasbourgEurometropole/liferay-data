<!-- Détail Vidéo -->
<#setting locale = locale />
<div class="details">
	<h2 class="subtitle" >${entry.getTitle(locale)}</h2> 
	<!-- video -->
	<div class="video-detail-player">
	    ${entry.getPlayer(locale)}
	</div>
	<div class="video-informations">
		<div class="clearer">&nbsp;</div>
		<p class="gray">
		    <@liferay_ui.message key='eu.published' />
		    <time>
		        ${entry.getPublicationDate()?string("dd/MM/yyyy")} 
		    </time>
     		- Reportage
     	</p>
		<#if entry.themes?has_content>
			<p class="gray">
				<#list entry.themes as theme>
				    ${theme.getTitle(locale)} 
				</#list>
			</p>
		</#if> 
		<div class="description ck-editor-content">
			${entry.getDescription(locale)}
		</div>
	</div>
</div>
<div class="button-gray button-gray-bottom-details">
	<div class="left">&nbsp;</div>
	<div class="middle">
		<a href="${themeDisplay.pathFriendlyURLPublic}${themeDisplay.getLayout().getGroup().getFriendlyURL()}/medias/videos"><@liferay_ui.message key='eu.video.all-videos' /></a>
	</div>
	<div class="right">&nbsp;</div>
</div>