<!-- SLIDER DES PROPOSITION D'AIDE SANS MARGE -->

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />

<#if entries?size != 0 >

    <!-- Recuperation de la localisation de l'utilisateur -->
    <#setting locale = locale />

    <!-- Recuperation du créateur de la proposition d'aide -->
    <#assign UserLocalService = serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService")/>
    <#assign user = UserLocalService.getUser(entry.userId) />

    <!-- Recuperation de l'URL de "base" du site -->
    <#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
        <#assign homeURL = "/web${layout.group.friendlyURL}/" />
    <#else>
        <#assign homeURL = "/" />
    </#if>

    <section class="pro-bloc-slider pro-slider-event">

        <div class="container">
            <div>
                <h2><@liferay_ui.message key="eu.help.help-proposals" /> (${entries?size})</h2> 
                <#if isUserloggedIn >
                    <a id="buttonSubmitHelpProposal" href="" class="pro-btn" data-toggle="modal" data-target="#modalSubmitHelpProposal"><@liferay_ui.message key="eu.help.proposed" /></a>
                <#else>
                    <a name="#Need-connexion" href="" id="buttonSubmitHelpProposal" class="pro-btn" ><@liferay_ui.message key="eu.help.proposed" /></a>
                </#if>
            </div>
            <div>
                <div class="owl-carousel owl-opacify owl-theme owl-cards">
                    <#if isUserloggedIn >
                        <!-- Parcours des entites de l'asset publisher -->
                        <#list entries as curEntry>

                            <!-- Recuperation de l'entite -->
                            <#assign entry = curEntry.getAssetRenderer().getHelpProposal() />

                            <div class="item pro-bloc-card-help" data-linkall="a">
                                <div class="wrapper-card-help">
                                    <#if entry.imageURL?has_content>
                                        <figure role="group" class="fit-cover">
                                            <img src="${entry.imageURL}?imagePreview=1" loading="lazy" width="240" height="250" alt="Photo d’illustration de ${entry.getTitle(locale)?html}"/>
                                        </figure>
                                    </#if>
                                    <div>
                                        <div class="pro-header-help">
                                            <p><@liferay_ui.message key="eu.help.help-by" /> <strong>${entry.getAuthorLabel()?html}</strong></p>
                                        </div>
                                        <div class="pro-content-help">
                                            <div class="pro-wrapper-meta">
                                                <div class="pro-meta">
                                                    ${entry.getLocalisationLabel(locale)?has_content?then('<span>'+entry.getLocalisationLabel(locale)+'</span>','')}
                                                    <#if entry.getHelpProposalTypeCategories()?? >
                                                        <span>
                                                            <#list entry.getHelpProposalTypeCategories() as helpType >
                                                                ${helpType.getTitle(locale)}<#sep> - </#sep>
                                                            </#list>
                                                        </span>
                                                    </#if>
                                                </div>
                                            </div>
                                            <a href="${homeURL}detail-aide/-/entity/id/${entry.helpProposalId}" title="lien de la page">
                                                <h3>${entry.getTitle(locale)?html}</h3>
                                                <p>${(entry.getDescription(locale)?replace("<[^>]*>", "", "r")?length>50)?then(entry.getDescription(locale)?replace("<[^>]*>", "", "r")[0..*50]+"...", entry.getDescription(locale))}</p>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="pro-footer-help "> 
                                    <p>
                                        <@liferay_ui.message key="eu.help.publication-help" /> <time datetime="${entry.getPublicationDateFr()}">${entry.getPublicationDateFr()}</time> - 
                                        <@liferay_ui.message key="eu.help.update-help" /> <time datetime="${entry.getPublicationDateFr()}">${entry.getPublicationDateFr()}</time>
                                    </p>   
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <@liferay_ui.message key="eu.help.must-connected" /> <a href="?auth=publik"  ><@liferay_ui.message key="eu.help.create-account" /></a>
                    </#if>
                    
                </div>
            </div>
        </div>

    </section>
    
<#else>
    <div style="height:25px"><div>
</#if>