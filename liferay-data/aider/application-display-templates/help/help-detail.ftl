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
<#assign isUserHelping = entry.isUserHelping(userID) />

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
						<h1>${entry.getTitle(locale)}</h1>
						<div class="pro-wrapper-meta">
							<#if entry.getActivityStatusCategory()?has_content>
								<div class="pro-statut" ><span style="background : #${entry.getActivityStatusColor()};">${entry.getActivityStatusCategory().getTitle(locale)}</span></div>
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
							<p><@liferay_ui.message key="eu.help.published-x" arguments="${entry.createDate?date?string['dd/MM/yyyy']}" /></p>
							<p><strong>${entry.getAuthorLabel()}</strong></p>
						</div>
						<#if entry.getSpokenLanguages(locale)?has_content >
							<div class="pro-header-language">
								<p><@liferay_ui.message key="eu.help.another-languages" /> : ${entry.getSpokenLanguages(locale)}</p>
							</div>
						</#if>
					</div>
					<p>Pour bénéficier des aides proposées, la mise en relation avec les contributeurs sera disponible à partir du 12/03/2021.</p>

					<div id="breadcrumb">
					<span>
						<span>
							<a href="${homeURL}"><@liferay_ui.message key="home" /></a>
						<span class="breadcrumb_last">${entry.getTitle(locale)}</span>
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
							<h2><@liferay_ui.message key="eu.help.description" /></h2>
							<p>${entry.getDescription(locale)?replace("\n", "<br>")}</p>
						</div>
					</div>

					<aside class="col-sm-4" style="diplay:none">
						
						<div class="pro-wrapper-links">
							
							<#-- Si l'auteur est l'utilisateur courant -->
							<#if isUserloggedIn && isUserHelping> 

								<#-- Si proposition d'aide activée -->
								<#if entry.isActive()>
									<a href="#showModalEditHelpProposal" class="pro-btn-yellow" id="buttonEditHelpProposal" data-toggle="modal" data-target="#modalEditHelpProposal">
										<@liferay_ui.message key="eu.help.update" />
									</a>
									<a href="#" class="pro-btn-yellow" id="buttonDesactivateHelp" data-toggle="modal" data-target="#modalDesactivateHelp">
										<@liferay_ui.message key="eu.help.desactivate" />
									</a> 
								<#-- Alors proposition d'aide désactivée -->
								<#else>
									<a name="#inactive-help-proposal" href="" class="pro-btn-yellow pro-btn-disabled">
										<@liferay_ui.message key="eu.help.update" />
									</a>
									<a name="#inactive-help-proposal" href="" class="pro-btn-yellow pro-btn-disabled">
										<@liferay_ui.message key="eu.help.desactivate" />
									</a> 
								</#if>

							<#-- Si l'utilisateur courant est connecté -->
							<#elseif isUserloggedIn>
								
								<#-- Si proposition d'aide activée -->
								<#if entry.isActive()>
									<a href="#" class="pro-btn-yellow" id="buttonSubmitHelpRequest" data-toggle="modal" data-target="#modalSubmitHelpRequest">
										<@liferay_ui.message key="eu.help.do-request" />
									</a>
								<#-- Alors proposition d'aide désactivée -->
								<#else>
									<a name="#inactive-help-proposal" href="" class="pro-btn-yellow pro-btn-disabled">
										<@liferay_ui.message key="eu.help.do-request" />
									</a>
								</#if>
								<br>

							<#-- Sinon demande de connexion -->
							<#else>

								<#-- Si proposition d'aide activée -->
								<#if entry.isActive()>
									<a name="#Need-connexion" href="" class="pro-btn-yellow" id="buttonNeedHelp">
										<@liferay_ui.message key="eu.help.do-request" />
									</a>
								<#-- Alors proposition d'aide désactivée -->
								<#else>
									<a name="#inactive-help-proposal" href="" class="pro-btn-yellow pro-btn-disabled">
										<@liferay_ui.message key="eu.help.do-request" />
									</a>
								</#if>

							</#if>

						</div>
					</aside>
				</div>
			</article>

		</div>
	</div>
	
</div>