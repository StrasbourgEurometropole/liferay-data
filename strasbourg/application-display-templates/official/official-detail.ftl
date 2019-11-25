<!-- Détail élu -->
<#setting locale = locale />

<#assign fonction = "" />
<#if entry.fonctionCity?has_content>
    <#assign fonction = languageUtil.get(locale, "eu.official.city-of-strasbourg") + " : " + entry.getName(entry.fonctionCity, locale) />
</#if>
<#if entry.fonctionEurometropole?has_content>
    <#if fonction?has_content>
        <#assign fonction = fonction + "; " />
    </#if>
    <#assign fonction = fonction + languageUtil.get(locale, "eu.official.eurometropole-of-strasbourg") + " : " + entry.getName(entry.fonctionEurometropole, locale) />
    <#if entry.fonctionTown?has_content>
        <#assign fonction = fonction + ", " + entry.getName(entry.fonctionTown, locale) + " " + languageUtil.get(locale, "eu.official.of-the-city-of") + " " + entry.getTown().getTitle(locale)/>
    </#if>
</#if>

<#assign imageUrl = ""/>
<!-- image -->
<#if entry.imageURL?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.lastName?html} - ${entry.firstName?html}",
"og:description":'${fonction?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

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