<!-- Détail association -->
<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>
<#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
<#assign utils = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:description":'${entry.getPresentation(locale)?replace("<[^>]*>", "", "r")?html}'
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<div class="seu-page-association">
    <main class="seu-container">
        <h1>${entry.getName(locale)}</h1>
        
        <div class="seu-flexbox">

            <div class="seu-container-left">
        

                <!-- Présentation -->
                <#if entry.getPresentation(locale)?has_content>
                    <div class="seu-wi--collapsing seu-first-opened">
                        <button class="seu-toggle-collapse">
                            <h2 class="description"><span><@liferay_ui.message key="eu.presentation" /></span></h2>
                        </button>
                        <div class="seu-collapsing-box">
                            <div class="rte">
                                ${entry.getPresentation(locale)}
                            </div>
                        </div>
                    </div>
                </#if>

                <!-- Pratiques -->
                <div class="seu-wi--collapsing  seu-first-opened">
                    <button class="seu-toggle-collapse">
                        <h2 class="file"><span><@liferay_ui.message key="eu.association.practices" /></span></h2>
                    </button>
                    <div class="seu-collapsing-box">
                        <#assign domaine = '' />
                        <#list entry.practicesCategories as practiceCategories>
                            <#if domaine != practiceCategories.getDomaine(locale)>
                                <#if domaine?has_content>
                                    </div>
                                </#if>
                                <#assign domaine = practiceCategories.getDomaine(locale) />
                                <h3>${practiceCategories.getDomaine(locale)}</h3>
                                <div class="seu-wi seu-wi-practices"><br>
                            </#if>
                            <div class="seu-content" style="margin-bottom: 20px">
                                <div class="title">${practiceCategories.getPratique(locale)}</div>
                                <div class="rte" style="margin-top: -5px; margin-bottom: 10px;">
                                    ${domaine}
                                    <#if practiceCategories.getSous_specialite(locale)?has_content>
                                        > ${practiceCategories.getSpecialite(locale)}
                                        <#if practiceCategories.getSous_sous_specialite(locale)?has_content>
                                            > ${practiceCategories.getSous_specialite(locale)}
                                        </#if>
                                    </#if>
                                </div>
                                <ul class="category-list" style="margin-bottom: 10px;">
                                    <li>
                                        <span><@liferay_ui.message key="eu.association.public" /></span>
                                        <span>${practiceCategories.getPublics(locale)}</span>
                                    </li>
                                    <li>
                                        <span><@liferay_ui.message key="eu.association.district" /></span>
                                        <span>${practiceCategories.getDistricts(locale)}</span>
                                    </li>
                                    <#if practiceCategories.getAccessibilities(locale)?has_content>
                                        <li>
                                            <span><@liferay_ui.message key="eu.association.accessibility" /></span>
                                            <span>${practiceCategories.getAccessibilities(locale)}</span>
                                        </li>
                                    </#if>
                                </ul>
                            </div>
                        </#list>
                        <#if domaine?has_content>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>


            <div class="seu-container-right">
                <div class="seu-location-infos">
                    <h3><@liferay_ui.message key="eu.place.address-details" /></h3>
                    <div class="rte">
                        <#if entry.phone?has_content>
                            <p>
                                <@liferay_ui.message key="phone" /> : ${entry.phone}
                            </p>
                        </#if>
                        <#if entry.getSiteURL(locale)?has_content>
                            <p>
                                <a href="${entry.getSiteURL(locale)}" class="seu-external" title="<@liferay_ui.message key="eu.website" /> (<@liferay_ui.message key="eu.new-window" />)" target="_blank"><@liferay_ui.message key="eu.website" /></a>
                            </p>
                        </#if>
                        <#if entry.mail?has_content>
                            <p>
                                <a href="mailto:${entry.mail}" class="seu-external" title="${entry.mail}"><@liferay_ui.message key="to-contact" /></a>
                            </p>
                        </#if>
                        <#if entry.getFacebookURL(locale)?has_content>
                            <p>
                                <a href="${entry.getFacebookURL(locale)}" class="seu-external" title="<@liferay_ui.message key="facebook" /> (<@liferay_ui.message key="eu.new-window" />)" target="_blank"><@liferay_ui.message key="facebook" /></a>
                            </p>
                        </#if>
                    </div>
                </div>
            </div>
        </div>  
    </main>
</div>

<style>

</style>