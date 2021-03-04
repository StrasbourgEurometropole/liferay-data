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
            <h2><@liferay_ui.message key="eu.help.help-proposals" /></h2> 
            <#if isUserloggedIn >
                <a id="buttonSubmitHelpProposal" href="" class="pro-btn" data-toggle="modal" data-target="#modalSubmitHelpProposal"><@liferay_ui.message key="eu.help.proposed" /></a>
            <#else>
                <a name="#Need-connexion" href="" id="buttonSubmitHelpProposal" class="pro-btn" ><@liferay_ui.message key="eu.help.proposed" /></a>
            </#if>
        </div>
        <div>
            <div class="owl-carousel owl-opacify owl-theme owl-cards">
                <#if isUserloggedIn >
                    <p><@liferay_ui.message key="eu.help.my.help.proposal.empty" /></p>
                    <p><@liferay_ui.message key="eu.help.soon.available" /></p>
                <#else>
                    <p>
                        <@liferay_ui.message key="eu.help.must.connected.line.1" />
                        <a href="?auth=publik"  ><@liferay_ui.message key="eu.help.signin" /></a>
                    </p>
                    <p>
                        <@liferay_ui.message key="eu.help.must.connected.line.2" /> 
                        <a href="?auth=publik"  ><@liferay_ui.message key="eu.help.create-account" /></a>
                    </p>
                </#if>
                                
            </div>
        </div>
    </div>

</section>