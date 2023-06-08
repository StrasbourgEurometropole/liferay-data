<!-- Recuperation de la localisation de l'utilisateur -->

<!-- Ce template ne doit pas etre mis en cache, ne pas cocher la check box 'Pouvant etre mis en cache' -->

<#setting locale = locale />

<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext()>
<#assign httpServletRequest = serviceContext.getRequest()>

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = httpServletRequest.getSession().getAttribute("publik_logged_in")!false />
<#assign hasUserPactSign = httpServletRequest.getSession().getAttribute("has_pact_signed")!false />
<#assign isUserBanned = httpServletRequest.getSession().getAttribute("is_banish")!false />



<#assign BudgetPhaseLocalService = serviceLocator.findService("eu.strasbourg.service.project.service.BudgetPhaseLocalService")/>
<#assign activePhase = BudgetPhaseLocalService.getActivePhase(themeDisplay.scopeGroupId) />  

<#assign AssetEntryService = serviceLocator.findService("com.liferay.asset.kernel.service.AssetEntryLocalService")/>
<#assign AssetEntryAssetCategoryRelLocalService = serviceLocator.findService("com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService")/>
<#assign LayoutLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")/>
<#assign assetEntryAssetCategoryRels = AssetEntryAssetCategoryRelLocalService.getAssetEntryAssetCategoryRelsByAssetCategoryId(activePhase.getPhaseCategory().getCategoryId())  />

<#-- Récupération de la page de listing de BP qui correspond à la phase active. Chaque page de listing est configurée avec la catégorie qui correspond à la phase -->
<#list assetEntryAssetCategoryRels as assetEntryAssetCategoryRel>
    <#assign asset = AssetEntryService.getAssetEntry(assetEntryAssetCategoryRel.getAssetEntryId()) />
    <#if asset.getClassName() == "com.liferay.portal.kernel.model.Layout">
        <#assign abc = LayoutLocalService.getLayout(asset.getClassPK())/>
        <#assign pageListing = abc.getFriendlyURL()/>
        <#break>
    </#if>
</#list>

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign homeURL2 = "/web${layout.group.friendlyURL}" />

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="pro-page-budget-participatif">

    <section class="container">
        <div>

            <div class="col-xs-12 pro-wrapper-title">
                <h1>${rightTitle.getData()}</h1>
            </div>

            <div class="row pro-wrapper-content">

                <div class="col-md-6">
                    <div class="pro-preambule">
                        <h2 class="pro-title">${leftTitle.getData()}</h2>
                        ${leftTitle.getChild("leftText").getData()}
                    </div>
                </div>

                <div class="col-md-6 pro-annexes">
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
							<#if isUserloggedIn && hasUserPactSign && !isUserBanned>
								<a class="pro-btn-yellow deposit-button" data-target="#modalBudget" data-toggle="modal" id="buttonDeposer">Soumettre un projet</a>
							<#elseif !hasUserPactSign>
								<a class="pro-btn-yellow deposit-button" name="#Pact-sign">Soumettre un projet</a>
							<#elseif isUserBanned>
								<a class="pro-btn-yellow deposit-button" name="#IsBanned">Soumettre un projet</a>
							</#if>
                            <#if pageListing??>
                                <a href="${homeURL2}${pageListing}" class="pro-btn-transparent">Voir la liste des projets</a>
                            </#if>
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
                            <#if isUserloggedIn && hasUserPactSign && !isUserBanned>
                                <a class="pro-btn-yellow deposit-button" data-target="#modalBudget" data-toggle="modal" id="buttonDeposer">Soumettre un projet</a>
                            <#elseif !hasUserPactSign>
                                <a class="pro-btn-yellow deposit-button" name="#Pact-sign">Soumettre un projet</a>
                            <#elseif isUserBanned>
                                <a class="pro-btn-yellow deposit-button" name="#IsBanned">Soumettre un projet</a>
                            </#if>
                            <#if pageListing??>
                                    <a href="${homeURL2}${pageListing}" class="pro-btn-transparent">Voir la liste des projets</a>
                            </#if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>