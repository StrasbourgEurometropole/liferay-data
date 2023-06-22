<!-- SLIDER BUDGET PARTICIPATIF SANS MARGE -->

<#if entries?size != 0 >

    <#-- Recuperation de la localisation de l'utilisateur -->
    <#setting locale = locale />

    <!-- Recuperation de l'URL de "base" du site -->
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL = "/" />
    </#if>

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
<#assign homeURL2 = "/web${layout.group.friendlyURL}" />

    <section id="pro-link-budget-participatif" class="pro-bloc-slider pro-slider-event">
        <div class="container">

            <div>
                <h2><@liferay_ui.message key="eu.budgetParticipatif" /></h2>
                <#if pageListing?? >
                    <div class="pro-wrapper">
                        <a href="${homeURL2}${pageListing}" class="pro-btn pro-btn-white">Voir tous les projets</a>
                    </div>
                </#if>
            </div>

            <div>
                <div class="owl-carousel owl-opacify owl-theme owl-cards">

                    <#-- Parcours des entites de l'asset publisher -->
                    <#list entries as curEntry>

                        <#-- Recuperation de l'entite -->
                        <#assign entry = curEntry.getAssetRenderer().getBudgetParticipatif() />

                        <#-- Recuperation de la couleur hexa correspondant au type de la participation -->
                        <#assign statusColor = entry.getBudgetParticipatifStatusCategoryColor() />

                        <#assign imageURL = entry.getAuthorImageURL() />
                        
                        <#if entry.isNotDoable()>
                            <#assign classFaisable = "pro-theme-non-faisable"/>
                        <#else>
                            <#assign classFaisable = "pro-theme-faisable" />
                        </#if>

                        <div class="item pro-bloc-card-budget ${classFaisable}" data-linkall="a">
                            <#if entry.getImageURL()?has_content>
                                    <figure role="group" class="fit-cover">
                                        <img src="${entry.getImageURL()}?imagePreview=1" loading="lazy" width="155" height="200" alt="Image budget participatif"/>
                                    </figure>
                            </#if>
                            <div class="pro-header-budget">
                                <#if imageURL?has_content >
                                    <figure role="group">
                                        <img src="${imageURL}?imagePreview=1" loading="lazy" width="40" height="40" alt="Image du budget participatif"/>
                                    </figure>
                                </#if>
                                <p>Projet déposé par :</p>
                                <p><strong>${entry.getAuthor()?html}</strong></p>
                                <div class="pro-info-top-right">
                                    <span class="pro-encart-theme encart-budget">
                                        ${entry.getBudgetParticipatifStatusTitle(locale)}
                                    </span>
                                </div>
                            </div>
                            <div class="pro-content-budget">
                                <a href="${homeURL}detail-budget-participatif/-/entity/id/${entry.budgetParticipatifId}" title="lien de la page de détail">
                                    <h3>${entry.title?html}</h3>
                                </a>
                                <p>Projet adressée à <u>la ville de Strasbourg</u></p>
                                <span class="pro-time">
                                    Publiée le <time datetime="${entry.createDate?date?string['dd/MM/yyyy']}">${entry.createDate?date?string['dd/MM/yyyy']}</time>
                                </span>
                            </div>
                                                                                
                            <div class="pro-footer-budget">
                                <p>
                                    <#if entry.isNotDoable()>
                                        Ce projet a été étudié et déclaré "${entry.getBudgetParticipatifStatusTitle(locale)}"
                                    <#else>
                                        <strong>${entry.getNbSupports()}</strong> Citoyens-nes 
                                        <#if entry.hasBeenVoted() >
                                            ont soutenus ce projet
                                        <#else>
                                            soutiennent ce projet
                                        </#if>
                                    </#if>                                  
                                </p>
                            </div>
                        </div>

                    </#list>

                </div>
            </div>
            
        </div>
    </section>

<#else>
    <div style="height:25px"><div>
</#if>