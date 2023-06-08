<!-- Détail offre -->
<#setting locale = locale />

<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<#assign portletHelper = serviceLocator.findService("eu.strasbourg.utils.api.PortletHelperService") />
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />

<#if portletHelper.isUserAuthorizedToConsultOffer(entry.typePublication.getName())> 
    <div class="seu-page-offer">
        <main class="seu-container">
            <h1>${entry.getPost(locale)}</h1>

            <div class="seu-flexbox">

                <div class="seu-offer-container rte">
                    <!-- Informations -->
                    <div class="seu-gray-box">
                        <div id="publicationId">
                            <h3><@liferay_ui.message key="eu.offer-publication-id" /></h3>
                            <p>${entry.getPublicationId()}</p>
                        </div>
                        <div id="direction">
                            <h3><@liferay_ui.message key="eu.offer-direction" /></h3>
                            <#if entry.direction ??>
                                <p>${entry.direction.getTitle(locale)}</p>
                            </#if>
                        </div>
                        <#if entry.service?? && entry.service?has_content>
                            <div id="service">
                                <h3><@liferay_ui.message key="eu.offer-service" /></h3>
                                <p>${entry.service.getTitle(locale)}</p>
                            </div>
                        </#if>
                        <#if entry.offerCategories?? && entry.offerCategories?has_content && entry.typeRecrutement.getTitle(locale)!="Stage" && entry.typeRecrutement.getTitle(locale)!="Stage collège">
                            <div id="filiereCategorie">
                                <h3><@liferay_ui.message key="eu.offer-filiere-categorie" /></h3>
                                <p>
                                    <#list entry.offerCategories as category>
                                        ${category.getTitle(locale)}<#sep>, </#sep>
                                    </#list>
                                </p>
                            </div>
                        </#if>
                        <div id="typeRecrutement">
                            <h3><@liferay_ui.message key="eu.offer-type-recrutement" /></h3>
                            <p>${entry.typeRecrutement.getTitle(locale)}<br />
                            ${entry.getPermanentDescription(locale)}</p>
                        </div>
                        <#if entry.getIsFullTime()?? && entry.getIsFullTime()?has_content && entry.typeRecrutement.getTitle(locale)!="Stage" && entry.typeRecrutement.getTitle(locale)!="Stage collège">
                            <div id="isFullTime">
                                <h3><@liferay_ui.message key="eu.offer-is-full-time" /></h3>
                                <p>
                                <#if entry.getIsFullTime()>
                                    <@liferay_ui.message key="eu.offer-full-time-true" />
                                <#else>
                                    <@liferay_ui.message key="eu.offer-full-time-false" />
                                </#if>  
                                - ${entry.getFullTimeDescription(locale)}
                                </p>
                            </div>                   
                        </#if>
                        <#assign gradeRanges = entry.gradeRanges /> 
                        <#if gradeRanges?? && gradeRanges?has_content && entry.typeRecrutement.getTitle(locale)!="Stage" && entry.typeRecrutement.getTitle(locale)!="Stage collège">
                            <div id="grade">
                                <h3><@liferay_ui.message key="eu.offer-grade" /></h3>
                                <p>
                                    <#list gradeRanges as gradeRange>
                                        ${gradeRange[2].getTitle(locale)}
                                        <#if gradeRange[3]??>
                                            <@liferay_ui.message key="eu.to" /> ${gradeRange[3].getTitle(locale)}<#sep>, </#sep>
                                        </#if>
                                    </#list>
                                </p>
                            </div>
                        </#if>
                        <div id="limitDate">
                            <h3><@liferay_ui.message key="eu.offer-limit-date" /></h3>
                            <p>${entry.getLimitDate()?datetime?string("dd/MM/yyyy")}</p>
                        </div>
                        <#if entry.getDuration(locale)?has_content>
                            <div id="dureeContrat">
                                <h3><@liferay_ui.message key="eu.offer-duree-contrat" /></h3>
                                <p>${entry.getDuration(locale)}</p>
                            </div> 
                        </#if> 
                        <#if entry.niveauEtude?? && entry.niveauEtude?has_content && entry.typeRecrutement.getTitle(locale)=="Stage">
                            <div id="niveauEtude">
                                <h3><@liferay_ui.message key="eu.offer-niveau-etude" /></h3>
                                <p>${entry.niveauEtude.getTitle(locale)}</p>
                            </div>
                        </#if>

				        <#if entry.contact?? && entry.contact?has_content && entry.typePublication?? && entry.typePublication?has_content && (entry.typePublication.getTitle(locale)=="Interne uniquement" || (entry.typePublication.getTitle(locale)=="Interne et externe" && portletHelper.isUserAuthorizedToConsultInternOffer()))>
                            <div id="contactRH">
                                <h3><@liferay_ui.message key="eu.offer-contact-RH" /></h3>
                                <p>${entry.contact}</p>
                            </div>
                        </#if>
                    </div>  
            
                    <!-- Introduction  -->
                    <div id="offerIntroduction">
                        ${entry.getIntroduction(locale)}
                    </div>

                    <!-- Missions activités -->
                    <div id="offerActivities">
                        <h2><@liferay_ui.message key="eu.offer-activities" /></h2>
                        ${entry.getActivities(locale)}
                    </div>

                    <!-- Profil -->
                    <div id="offerProfil">
                        <h2><@liferay_ui.message key="eu.offer-profil" /></h2>
                        ${entry.getProfil(locale)}
                    </div>

                    <!-- Avantages -->
                    <#if entry.getAvantages(locale)?has_content && entry.typeRecrutement.getTitle(locale)!="Stage" && entry.typeRecrutement.getTitle(locale)!="Stage collège">
                        <div id="offerAvantages">
                            <h3><@liferay_ui.message key="eu.offer-avantages" /></h3>
                            ${entry.getAvantages(locale)}
                        </div>
                    </#if>

                    <!-- Condition -->
                    <#if entry.getConditions(locale)?has_content>
                        <div id="offerConditions">
                            <h3><@liferay_ui.message key="eu.offer-conditions" /></h3>
                            ${entry.getConditions(locale)}
                        </div>
                    </#if>

                    <!-- Téléchargement PDF -->
                    <@liferay_portlet.actionURL var="exportPDF" name="export">
                        <@liferay_portlet.param name="type" value="offer" />
                        <@liferay_portlet.param name="entityId" value="${entry.getOfferId()}" />
                    </@liferay_portlet.actionURL>
                    <div class="seu-wi-link-group"> 
                        <a class="seu-wi seu-media" href="${exportPDF}" target="_blank" title="<@liferay_ui.message key="eu.offer-telecharger-pdf" />"> 
                            <div class="seu-media-container"> 
                                <div class="seu-media-left"> 
                                    <div class="seu-media-picto"></div> 
                                </div> 
                                <div class="seu-media-right"> 
                                    <div class="seu-media-text"> 
                                        <div class="seu-media-title"><@liferay_ui.message key="eu.offer-telecharger-pdf" /></div> 
                                    </div> 
                                    <div class="seu-link-group-arrow"></div> 
                                </div> 
                            </div> 
                        </a> 
                    </div>

                    <#if .now < entry.publicationEndDate?datetime>
                        <!-- Candidater -->
                        <button type="button" class="seu-btn-square seu-filled seu-core" id="candidater">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text">
                                    <a><@liferay_ui.message key="eu.offer-candidater" /></a>
                                </span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </button>
                    </#if>
                </div>  
            </div>  
        </main>
    </div>

    <#assign StrasbourgPropsUtil = serviceLocator.findService("eu.strasbourg.utils.api.StrasbourgPropsUtilService") />
    
    <script>
        document.getElementById("candidater").onclick = function(e){
            // on vérifie si c'est une offre de stage de collège
            if("${entry.typeRecrutement.getTitle(locale)}" == "Stage collège")
                window.location = "${StrasbourgPropsUtil.getPublikApiBase()}${StrasbourgPropsUtil.getEJobURLOfferStageCollegeApply()}?refposte=${entry.publicationId}&libposte=${entry.getPost(locale)?js_string}";
            else{
                // on vérifie que l'utilisateur est connecté
                if(window.publikInternalId != undefined){
                    window.location = "${StrasbourgPropsUtil.getPublikApiBase()}${StrasbourgPropsUtil.getEJobURLOfferApply()}?refposte=${entry.publicationId}&libposte=${entry.getPost(locale)?js_string}";
                }else{
                    window.createPopin(Liferay.Language.get('log-in-to-apply'),function() {
                        window.location = window.location + ((window.location.href.indexOf("?") > -1)? '&' : '?') + 'auth=publik';
                    },undefined,Liferay.Language.get('eu.login'), Liferay.Language.get('eu.cancel'));
                }
            }
        };
    </script>
<#else>
    <p><@liferay_ui.message key="eu.offer-not-visible" /></p>
</#if>