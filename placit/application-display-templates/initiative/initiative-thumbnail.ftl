<!-- VIGNETTE INITIATIVE -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation du type de l'initiative -->
<#assign participationType = entry.getTypeCategory().getTitle(locale) />

<!-- Recuperation des thématiques de l'initiative -->
<#if entry.getThematicCategories()??>
    <#assign initiativeThematics = entry.getThematicCategories() />
</#if>

<!-- Recuperation du projet de l'initiative -->
<#if entry.getProjectsCategory()??>
    <#assign initiativeProjects = entry.getProjectsCategory() />
</#if>

<div class="item pro-bloc-card-initiative pro-theme-floraison" data-linkall="a">
	<div class="wrapper-card-initiative">
		<figure role="group" class="fit-cover">
			<#if entry.getImageURL()?has_content>
                    <img src="${entry.getImageURL()}" width="240" height="250" alt="Image initiative"/>
			</#if>
		</figure>
		<div>
			<div class="pro-header-initiative">
				<figure role="group">					
					<#if entry.getImageURL()?has_content>
						<img src="${entry.getImageURL()}" width="40" height="40" alt="Image de l'auteur"/>
					</#if>
				</figure>
				<p>Initiative publiée par :</p>
				<p><strong>${entry.getAuthor()}</strong></p>
			</div>
			<div class="pro-content-initiative">
				<div class="pro-wrapper-meta">
					<!-- Type de la participation -->
					<#if participationType??><div class="pro-statut"><span>${participationType}</span></div></#if>
									
					<div class="pro-meta">
						<!-- Liste des quartiers  -->
						<span>${entry.getDistrictLabel(locale)}</span>

						<!-- Liste des thématiques -->
						<#if initiativeThematics?? >
							<#list initiativeThematics as initiativeThematic >
								<span>${initiativeThematic.getTitle(locale)}</span>
							</#list>
						</#if>

						<!-- Liste des projets -->						
						<#if initiativeProjects?? >
							<#list initiativeProjects as initiativeProject >
								<span>${initiativeProject.getTitle(locale)}</span>
							</#list>
						</#if>
					</div>
				</div>								
				<a href="${homeURL}detail-initiative/-/entity/id/${entry.initiativeId}" title="Détail ''">
					<h3>${entry.title}</h3>
				</a>				
				<span class="pro-time">
                Publiée le <time datetime="${entry.publicationDate?string['dd/MM/yyyy']}">${entry.publicationDate?date?string['dd/MM/yyyy']}</time>
				</span>
				
			</div>
		</div>
	</div>
	<div class="pro-footer-initiative">
		<div class="pro-avis">
			<span>${entry.getNbHelpInitiative()}</span>
		</div>
		<p>Citoyens-nes soutiennent cette initiative</p>
	</div>
</div>