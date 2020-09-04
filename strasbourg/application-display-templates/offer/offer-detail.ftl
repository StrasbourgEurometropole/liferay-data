<!-- Détail offre -->
<#setting locale = locale />

<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<div class="seu-page-offer">
    <main class="seu-container">
        <h1>${entry.getPost()}</h1>
        
        <div class="seu-flexbox">

            <div class="seu-offer-container">
                <!-- Informations -->
                <div class="seu-gray-box">
                    <div id="publicationId">
                        <h3><@liferay_ui.message key="eu.offer-publication-id" /></h3>
                        <p>${entry.getPublicationId()}</p>
                    </div>
                    <#if entry.getPostNumber()?? && entry.offerTypeRecrutement.getTitle(locale)=="Stage">
                        <div id="postNumber">
                            <h3><@liferay_ui.message key="eu.offer-post-number" /></h3>
                            <p>${entry.getPostNumber()}</p>
                        </div>
                    </#if>
                    <div id="typeRecrutement">
                        <h3><@liferay_ui.message key="eu.offer-type-recrutement" /></h3>
                        <p>${entry.offerTypeRecrutement.getTitle(locale)}</p>
                    </div>
                    <#if entry.getPermanentDescription()?? && entry.offerTypeRecrutement.getTitle(locale)!="Stage">
                        <div id="permanentDescription">
                            <h3><@liferay_ui.message key="eu.offer-permanent-description" /></h3>
                            <p>${entry.getPermanentDescription(locale)}</p>
                        </div>
                    </#if>
                    <div id="dureeContrat">
                        <h3><@liferay_ui.message key="eu.offer-duree-contrat" /></h3>
                        <p>${entry.getDuration(locale)}</p>
                    </div>  
                    <div id="direction">
                        <h3><@liferay_ui.message key="eu.offer-direction" /></h3>
                        <p>${entry.offerDirection.getTitle(locale)}</p>
                    </div>
                    <#if entry.offerService??>
                        <div id="service">
                            <h3><@liferay_ui.message key="eu.offer-service" /></h3>
                            <p>${entry.offerService.getTitle(locale)}</p>
                        </div>
                    </#if>
                    <#if entry.getIsFullTime()?? && entry.offerTypeRecrutement.getTitle(locale)!="Stage">
                        <div id="isFullTime">
                            <h3><@liferay_ui.message key="eu.offer-is-full-time" /></h3>
                            <#if entry.getIsFullTime()>
                                <p><@liferay_ui.message key="eu.offer-full-time-true" /></p>
                            <#else>
                                <p><@liferay_ui.message key="eu.offer-full-time-false" /></p>
                            </#if>  
                        </div>
                        <#if entry.getFullTimeDescription(locale)?? && entry.getIsFullTime()>
                            <div id="fullTimeDescription">
                                <h3><@liferay_ui.message key="eu.offer-full-time-description" /></h3>
                                <p>${entry.getFullTimeDescription(locale)}</p>
                            </div>
                        </#if>                    
                    </#if>  
                    <#if entry.offerFiliere?? && entry.offerTypeRecrutement.getTitle(locale)!="Stage">
                        <div id="filiere">
                            <h3><@liferay_ui.message key="eu.offer-filiere" /></h3>
                            <p>${entry.offerFiliere.getTitle(locale)}</p>
                        </div>
                    </#if>
                    <#if entry.offerFiliereCategorie?? && entry.offerTypeRecrutement.getTitle(locale)!="Stage">
                        <div id="filiereCategorie">
                            <h3><@liferay_ui.message key="eu.offer-filiere-categorie" /></h3>
                            <p>${entry.offerFiliereCategorie.getTitle(locale)}</p>
                        </div>
                    </#if>
                    <#if entry.offerGrade?? && entry.offerTypeRecrutement.getTitle(locale)!="Stage">
                        <div id="grade">
                            <h3><@liferay_ui.message key="eu.offer-grade" /></h3>
                            <p>${entry.offerGrade.getTitle(locale)}</p>
                        </div>
                    </#if>
                    <#if entry.offerNiveauEtude?? && entry.offerTypeRecrutement.getTitle(locale)=="Stage">
                        <div id="niveauEtude">
                            <h3><@liferay_ui.message key="eu.offer-niveau-etude" /></h3>
                            <p>${entry.offerNiveauEtude.getTitle(locale)}</p>
                        </div>
                    </#if>
                    <div id="limitDate">
                        <h3><@liferay_ui.message key="eu.offer-limit-date" /></h3>
                        <p>${entry.getLimitDate()?datetime?string("dd/MM/yyyy")}</p>
                    </div>
                    <#if entry.offerFamille??>
                        <div id="familleMetier">
                            <h3><@liferay_ui.message key="eu.offer-famille-metier" /></h3>
                            <p>${entry.offerFamille.getTitle(locale)}</p>
                        </div>
                    </#if>
                    <#if entry.offerContact?? && entry.typePublication.getTitle(locale)=="Interne uniquement">
                        <div id="nomRE">
                            <h3><@liferay_ui.message key="eu.offer-nom-RE" /></h3>
                            <p>${entry.offerContact.getTitle(locale)}</p>
                        </div>
                    </#if>
                    <#if entry.getContact()??>
                        <#if entry.offerTypeRecrutement.getTitle(locale)=="Stage" || entry.typePublication.getTitle(locale)=="Interne uniquement">
                            <div id="nomRRH">
                                <h3><@liferay_ui.message key="eu.offer-nom-RRH" /></h3>
                                <p>${entry.getContact()}</p>
                            </div>
                        </#if>
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

                <!-- Condition -->
                <div id="offerConditions">
                    <h2><@liferay_ui.message key="eu.offer-conditions" /></h2>
                    ${entry.getConditions(locale)}
                </div>

                <!-- Avantages -->
                <#if entry.avantages?? && entry.offerTypeRecrutement.getTitle(locale)!="Stage">
                    <div id="offerAvantages">
                        <h2><@liferay_ui.message key="eu.offer-avantages" /></h2>
                        ${entry.getAvantages(locale)}
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

                <!-- Candidater -->
                <button type="button" class="seu-btn-square seu-filled seu-core" id="candidater">
                    <span class="seu-flexbox">
                        <span class="seu-btn-text">
                            <a><@liferay_ui.message key="eu.offer-candidater" /></a>
                        </span>
                        <span class="seu-btn-arrow"></span>
                    </span>
                </button>
            </div>  
        </div>  
    </main>
</div>

<#assign StrasbourgPropsUtil = serviceLocator.findService("eu.strasbourg.utils.api.StrasbourgPropsUtilService") />
    
<script>
    document.getElementById("candidater").onclick = function(e){
        // on vérifie que l'utilisateur est connecté
        if(window.publikInternalId != undefined){
            window.location = "${StrasbourgPropsUtil.getPublikApiBase()}${StrasbourgPropsUtil.getEJobURLOfferApply()}?refposte=${entry.publicationId}&libposte=${entry.getPost(locale)}";
        }else{
            window.createPopin(Liferay.Language.get('log-in-to-apply'),function() {
                window.location = window.loginURL;
            },undefined,Liferay.Language.get('eu.login'), Liferay.Language.get('eu.cancel'));
        }
    };
</script>