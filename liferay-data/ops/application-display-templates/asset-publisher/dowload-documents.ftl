<!-- DOCUMENTS A TELECHARGER -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<#-- récupération de l'instance id de l'asset publisher pour generer un instance id unique pour le web content display -->
    <#assign portletDisplay = themeDisplay.getPortletDisplay()/>
    <#assign portletid = portletDisplay.getId()/> 

<div class="ops-bloc-download ops-bloc-texte ops-bloc-large">
	<div class="ops-col-wrapper ops-bloc-small">
		<h2 class="ops-big-title"><span><@liferay_ui.message key="eu.ops.documents.to.download" /></span></h2>
		<div class="ops-col-50 ops-aligncenter">
		<p>
			<@liferay_portlet["runtime"]
				portletProviderAction=portletProviderAction.VIEW
				portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
				instanceId="separateur_${portletid}"/>
		</p>
		</div>
		<div class="ops-col-50">
			<ul class="ops-items-download">
				<!-- Parcours des entites de l'asset publisher -->
                <#list entries as curEntry>
				
					<#assign file = curEntry.getAssetRenderer().getAssetObject() />
                    <#assign fileUrl = FileEntryHelper.getFileEntryURL(file.getFileEntryId()) >
					
					<li>
						<a target="_blank" href="${fileUrl}" download>
							<span class="ops-title">${file.getTitle()}</span>
							<span class="icon-ico-download"></span>
						</a>
					</li>

				</#list>
			</ul>
		</div>
	</div>
</div>