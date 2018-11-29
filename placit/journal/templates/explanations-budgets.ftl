<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign httpServletRequest = serviceContext.getRequest()>

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = httpServletRequest.getSession().getAttribute("publik_logged_in")!false />
<#assign hasUserPactSign = httpServletRequest.getSession().getAttribute("has_pact_signed")!false />
<#assign isUserBanned = httpServletRequest.getSession().getAttribute("is_banish")!false />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="pro-page-budget-participatif">

    <section class="container">
        <div class="row">

            <div class="col-xs-12 pro-wrapper-title">
                <h1>${rightTitle.getData()}</h1>
            </div>

            <div class="pro-wrapper-content">

                <div class="col-sm-6">
                    <div class="pro-preambule">
                        <h2 class="pro-title">${leftTitle.getData()}</h2>
                        ${leftTitle.getChild("leftText").getData()}
                    </div>
                </div>

                <div class="col-sm-6 pro-annexes">
                    <div>
                        <h3>Annexes à télécharger</h3>
                        <ul>
                            <#list rightTitle.getChild("files").getSiblings() as file>

                                <#if file.getData()?has_content >

                                    <#assign fileEntry = fileEntryHelper.getFileEntryByRelativeURL(file.getData()) />
                                    <#assign title = fileEntryHelper.getFileTitle(fileEntry.getFileEntryId(), locale) />
                                    <#assign size = fileEntryHelper.getReadableFileEntrySize(fileEntry.getFileEntryId(), locale) />

                                    <li>
                                        <a href="${file.getData()}" download title="${title}">
                                            ${title}
                                            <span class="pro-poids">Poids ${size}</span>
                                        </a>
                                    </li>

                                </#if>

                            </#list>
                        </ul>

                       <div class="pro-wrapper-btn">
							<#if hasUserPactSign && hasUserPactSign && !isUserBanned>
								<a class="pro-btn-yellow deposit-button" data-target="#modalBudget" data-toggle="modal" id="buttonDeposer">Soumettre un projet</a>
							<#elseif !hasUserPactSign>
								<a class="pro-btn-yellow deposit-button" name="#Pact-sign">Soumettre un projet</a>
							<#elseif isUserBanned>
								<a class="pro-btn-yellow deposit-button" name="#IsBanned">Soumettre un projet</a>
							</#if>
							<a href="/projets-budget-participatif" class="pro-btn-transparent">Voir la liste des projets</a>										
						</div>

                    </div>

                </div>
            </div>
        </div>
    </section>

    <section class="container pro-menu-budget-participatif">
        <div class="aligncenter">
            <h2>${underTitle.getData()}</h2>
        </div>

        <div class="row pro-menu-budget-1">
            <div class="col-sm-6 pro-budget-img">
                <figure role="group">
                    <img src="/o/plateforme-citoyenne-theme/images/medias/img-budget-montage-projet.jpg" width="387" height="387" alt="Image Montage et Dépôt de projet" />
                </figure>
                <span class="pro-number">1</span>
                <span class="pro-circle"><span class="icon-ico-analyse"></span></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent1").getData()}
            </div>
        </div>

        <div class="row pro-menu-budget-2">
            <div class="col-sm-6 pro-budget-img">
                <span class="pro-number">2</span>
                <span class="pro-circle"><span class="icon-ico-vote"></span></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent2").getData()}
            </div>
        </div>

        <div class="row pro-menu-budget-3">
            <div class="col-sm-6 pro-budget-img">
                <span class="pro-number">3</span>
                <span class="pro-circle"><span class="icon-ico-montage-projet"></span></span>
                <span class="pro-circle-left"></span>
                <span class="pro-circle-right"></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent3").getData()}
            </div>
        </div>

        <div class="row pro-menu-budget-4">
            <div class="col-sm-6 pro-budget-img">
                <span class="pro-number">4</span>
                <span class="pro-circle"><span class="icon-ico-laureat"></span></span>
                <span class="pro-circle-left"></span>
                <span class="pro-circle-right"></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent4").getData()}
            </div>
        </div>
    </section>

    <div class="pro-bloc-prefooter pro-sticky-bar">
		<div class="container">
			<div class="col-xs-12 aligncenter pro-wrapper-btn">
				<div class="pro-bloc-prefooter pro-sticky-bar">
					<div class="container">
						<div class="col-xs-12 aligncenter pro-wrapper-btn">
                            <#if hasUserPactSign && hasUserPactSign && !isUserBanned>
                                <a class="pro-btn-yellow deposit-button" data-target="#modalBudget" data-toggle="modal" id="buttonDeposer">Soumettre un projet</a>
                            <#elseif !hasUserPactSign>
                                <a class="pro-btn-yellow deposit-button" name="#Pact-sign">Soumettre un projet</a>
                            <#elseif isUserBanned>
                                <a class="pro-btn-yellow deposit-button" name="#IsBanned">Soumettre un projet</a>
                            </#if>
                            <a href="/projets-budget-participatif" class="pro-btn-transparent">Voir la liste des projets</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>