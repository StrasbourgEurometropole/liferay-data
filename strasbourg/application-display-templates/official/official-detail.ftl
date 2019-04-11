<!-- Détail élu -->
<#setting locale = locale />

<#assign serviceContext = staticUtil["com.liferay.portal.kernel.service.ServiceContextThreadLocal"].getServiceContext() />
<#assign themeDisplay = serviceContext.getThemeDisplay() />
<#assign currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent() />

<#-- <@liferay_util["html-top"]>
    <meta property="og:title" content="${entry.firstName} ${entry.lastName}" />
    <meta property="og:description" content="" />
    <meta property="og:url" content="${currentUrl}" />
    <#if entry.imageURL?has_content>
        <meta property="og:image" content="${themeDisplay.getPortalURL()}${entry.imageURL}" />
    </#if>
    <#if !entry.imageURL?has_content>
        <#assign layout = themeDisplay.getLayout() />
        <#if layout.expandoBridge.getAttribute('image')?has_content>
            <#assign imageUrl = themeDisplay.getPortalURL() + layout.expandoBridge.getAttribute('image') />
        </#if>
    </#if>
    <#if imageUrl?has_content>
        <#assign imageUrl = imageUrl?replace('https:','http:') />
        <#assign AssetPublisherTemplateHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetPublisherTemplateHelperService") />
        <#assign taille = AssetPublisherTemplateHelper.getImageWidthHeight(imageUrl) />
        <meta property="og:image" content="${imageUrl}"/>
        <meta property="og:image:width" content="${taille?keep_before(',')}"/>
        <meta property="og:image:height" content="${taille?keep_after(',')}"/>
    </#if>

    <meta property="og:title" content="${entry.getTitle(locale)?html} - ${entry.getEventScheduleDisplay(locale)}" />
    <meta property="og:description" content="${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}" />
    <meta property="og:url" content="${themeDisplay.getPortalURL()}${homeURL}evenement/-/entity/id/${entry.eventId}" />
    <#assign imageUrl = 'http://www.touch-as-strasbourg.com/media/uploaded/sites/10468/partenaire/57a9e032cf93f_eurometropole.png' />
    <meta property="og:image:width" content="500"/>
    <meta property="og:image:height" content="300"/>
</@>
-->

<div class="seu-container official-detail">
    <h1>${entry.firstName} ${entry.lastName}</h1>
    <div class="rte">
        <div class="official-portrait">
            <img title="${entry.firstName} ${entry.lastName}" src="${entry.imageURL}" />
        </div>
        <#if entry.fonctionCity?has_content>
            <h2><@liferay_ui.message key="eu.official.city-of-strasbourg" /></h2>
            <h3>${entry.getName(entry.fonctionCity, locale)}</h3>
            <#if entry.getThematicDelegation(locale)?has_content>
                <h4><@liferay_ui.message key="eu.official.in-charge-of" /></h4>
                ${entry.getThematicDelegation(locale)}
            </#if>
            <#if entry.districts?has_content>
                <#if entry.getGender() == 1>
                    <h4><@liferay_ui.message key="male-official-of-the-district" /></h4>
                <#else>
                    <h4><@liferay_ui.message key="female-official-of-the-district" /></h4>
                </#if>
                <ul>
                    <#list entry.districts as district>
                        <li>${district.getTitle(locale)}</li>
                    </#list>
                </ul>
            </#if>
            <#if entry.getPoliticalGroupCity()?has_content>
                <h4><@liferay_ui.message key="eu.official.political-group" /></h4>
                ${entry.getPoliticalGroupCity().getTitle(locale)}
            </#if>
            <br>
        </#if>
        <#if entry.fonctionEurometropole?has_content>
            <h2><@liferay_ui.message key="eu.official.eurometropole-of-strasbourg" /></h2>
            <h3>
                ${entry.getName(entry.fonctionEurometropole, locale)}<#if entry.fonctionTown?has_content>, 
                    ${entry.getName(entry.fonctionTown, locale)} 
                    <@liferay_ui.message key="eu.official.of-the-city-of" /> ${entry.getTown().getTitle(locale)}
                </#if>
            </h3>
            <#if entry.getMissions(locale)?has_content>
                <h4><@liferay_ui.message key="eu.official.in-charge-of" /></h4>
                ${entry.getMissions(locale)}
            </#if>
            <#if entry.getPoliticalGroupEurometropole()?has_content>
                <h4><@liferay_ui.message key="eu.official.political-group" /></h4>
                ${entry.getPoliticalGroupEurometropole().getTitle(locale)}
            </#if>
        </#if>
    </div>

    <div class="seu-wi seu-wi-quote official-contact"> 
        <div class="seu-container"> 
            <h2 class="seu-section-title">
                <span class="seu-title">Écrire à un élu</span>
                <div class="rte">
                    <div>Les adresses emails des élus sont composées de la manière suivante : </div>
                    <div>prenom.nom@strasbourg.eu</div>
                    <br>
                    <div>Pour les noms composés :</div>
                    <div>prenom1-prenom2.nom1-nom2@strasbourg.eu</div>
                </div>
            </h2>
        </div>
    </div>
</div>