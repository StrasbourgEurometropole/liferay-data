<!-- DETAIL D'UNE PROPOSITION D'AIDE -->

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation des types d'aide -->
<#if entry.getHelpProposalTypeCategories()??>
    <#assign helpTypes = entry.getHelpProposalTypeCategories() />
</#if>

<#-- Récupération de l'ID de l'utilisateur -->
<#assign userID = request.session.getAttribute("publik_internal_id")!"" />

<#-- Vérifie si l'utilisateur est celui qui a créé la proposition -->
<#-- <#assign isUserHelping = entry.isUserHelping(userID) /> -->

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />


<#-- Récupération des liens médias de l'entité -->
<#assign imageURL = entry.getImageURL() />

<#assign imageUrlOG = ""/>
<!-- vignette -->
<#if entry.imageURL?has_content>
    <#assign imageUrlOG=themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.title?html}",
"og:description":'${entry.description?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrlOG}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<div class="pro-page-detail pro-page-detail-help">
	<div class="container">
		<div class="col-lg-11 col-lg-offset-1">
			<article>
				<header>
					<div class="pro-header-help pro-theme-actif">
						<h1>${entry.title}</h1>
						<div class="pro-wrapper-meta">
							<#if entry.getActivitStatusCategory()?has_content>
								<div class="pro-statut" ><span style="background : #${entry.getActivityStatusClass()};">${entry.getActivitStatusCategory().getTitle(locale)}</span></div>
							</#if>
							<div class="pro-meta">
							
								<!-- Liste des quartiers  -->
								<span>${entry.getLocalisationLabel(locale)}</span>

								<!-- Liste des types -->
								<#if helpTypes?? >
									<#list helpTypes as helpType >
										<span>${helpType.getTitle(locale)}</span>
									</#list>
								</#if>
							</div>
						</div>
						<div class="pro-header-auteur">
							<p>Aide publiée le ${entry.createDate?date?string['dd/MM/yyyy']} par :</p>
							<p><strong>${entry.getAuthorLabel()}</strong></p>
						</div>
					</div>

					<div id="breadcrumb">
					<span>
						<span>
							<a href="${homeURL}">Accueil</a>
						<span class="breadcrumb_last">${entry.title}</span>
						</span>
					</span>
					</div>
				</header>

				<div class="row pro-container-detail-help">
					<div class="col-sm-8">

						<div class="pro-main-img">
							<figure>
								<#if entry.getImageURL()?has_content>
									<img src='${entry.getImageURL()}' alt="Photo d’illustration de ${entry.getTitle(locale)?html}" width="880" height="593" class="fit-cover"/>
									<figcaption>${entry.getImageCopyright(locale)}</figcaption>
								</#if>
							</figure>
						</div>
						<div class="pro-bloc-texte">
							<h2>Description</h2>
							<p>${entry.getDescription()}</p>
						</div>
					</div>

					<aside class="col-sm-4" style="diplay:none">
						
						<#-- <div class="pro-wrapper-links">
						
							<#if isUserloggedIn && isUserHelping >
								<a href="#" class="pro-btn-yellow" id="buttonSubmitHelpProposal" data-toggle="modal" data-target="#modalSubmitHelpProposal">Modifier mon annonce</a>
                                <a href="#" class="pro-btn-yellow" id="buttonDesactivateHelp" data-toggle="modal" data-target="#modalDesactivateHelp">Désactiver mon annonce</a>   
							<#else>
								<#if isUserloggedIn >
									<a href="#" class="pro-btn-yellow" id="buttonSubmitHelpRequest" data-toggle="modal" data-target="#modalSubmitHelpRequest">Faire une demande d'aide</a><br>
								<#else>
									<a name="#Need-connexion" href="" class="pro-btn-yellow" id="buttonNeedHelp">Faire une demande d'aide</a>
								</#if>
							</#if>

						</div> -->
					</aside>
				</div>
			</article>

		</div>
	</div>
	
</div>