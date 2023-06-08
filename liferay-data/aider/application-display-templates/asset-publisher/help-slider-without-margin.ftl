<!-- SLIDER DES PROPOSITION D'AIDE SANS MARGE -->

<#-- Récupération du contexte de navigation de l'utilisateur -->
<#assign isUserloggedIn = request.session.getAttribute("publik_logged_in")!false />

<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<section class="pro-bloc-slider pro-slider-event">

    <div class="container">
        <div>
            <#if entries?has_content >
                <h2><@liferay_ui.message key="eu.help.help-proposals" /> (${entries?size})</h2>
            <#else>
                <h2><@liferay_ui.message key="eu.help.help-proposals" /></h2>
            </#if>
            <#if isUserloggedIn >
                <a id="buttonSubmitHelpProposal" href="" class="pro-btn" data-toggle="modal" data-target="#modalSubmitHelpProposal"><@liferay_ui.message key="eu.help.proposed" /></a>
            <#else>
                <a name="#Need-connexion" href="" id="buttonSubmitHelpProposal" class="pro-btn" ><@liferay_ui.message key="eu.help.proposed" /></a>
            </#if>
        </div>
        <div>
            <div class="owl-carousel owl-opacify owl-theme owl-cards" >
                <#if !isUserloggedIn>
                    <p>
                        <@liferay_ui.message key="eu.help.must.connected.line.1" />
                        <a href="?auth=publik"  ><@liferay_ui.message key="eu.help.signin" /></a>
                    </p>
                    <p>
                        <@liferay_ui.message key="eu.help.must.connected.line.2" /> 
                        <a href="?auth=publik"  ><@liferay_ui.message key="eu.help.create-account" /></a>
                    </p>
                <#else>
                    <#if !entries?has_content >
                        <p><@liferay_ui.message key="eu.help.my.help.proposal.empty" /></p>
                    </#if>
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
                                    <#if !entry.isActive() >
                                        <div class="pro-statut">
                                            <span style="background : #${entry.getActivityStatusColor()};">
                                                ${entry.getActivityStatusTitle(locale)}
                                            </span>
                                        </div>
                                    </#if> 
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
                                            <div class="description"><p>${entry.getDescription(locale)?replace("\n", "<br>")}</p></div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="pro-footer-help "> 
                                <p>
                                    <span class="publication-date" ><@liferay_ui.message key="eu.help.publication-help" /> <time datetime="${entry.getCreatedDateFr()}">${entry.getCreatedDateFr()}</time></span>
                                    <#if entry.getModifiedByUserDateFr()?has_content>
                                        - 
                                        <span ><@liferay_ui.message key="eu.help.update-help" /> <time datetime="${entry.getModifiedByUserDateFr()}">${entry.getModifiedByUserDateFr()}</time></span>
                                    </#if>
                                </p>  
                            </div>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>
    </div>
</section>