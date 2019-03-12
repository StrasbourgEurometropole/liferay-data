<!-- DOCUMENTS A TELECHARGER -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign FileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="ops-bloc-download ops-bloc-texte ops-bloc-large">
	<div class="ops-col-wrapper ops-bloc-small">
		<h2 class="ops-big-title"><span>Documents<br>à télécharger</span></h2>
		<div class="ops-col-50 ops-aligncenter">
			<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. <a href="#"><strong>Excepteur sint occaecat cupidatat non
						proident</strong></a>,sunt in culpa qui</p>
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