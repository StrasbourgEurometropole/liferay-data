<!-- Détail offre -->
<#setting locale = locale />

<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)} 

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") /> 

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
                    <div id="direction">
                        <h3><@liferay_ui.message key="eu.offer-direction" /></h3>
                        <p>${entry.offerDirection.getName()}</p>
                    </div>
                    <div id="Service">
                        <h3><@liferay_ui.message key="eu.offer-service" /></h3>
                        <p>${entry.offerService.getName()}</p>
                    </div>
                    <div id="Grade">
                        <h3><@liferay_ui.message key="eu.offer-grade" /></h3>
                        <p>${entry.offerGrade.getName()}</p>
                    </div>
                    <div id="limitDate">
                        <h3><@liferay_ui.message key="eu.offer-limit-date" /></h3>
                        <p>${entry.getLimitDate()?date}</p>
                    </div>
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
                <div id="offerAvantages">
                    <h2><@liferay_ui.message key="eu.offer-avantages" /></h2>
                    ${entry.getAvantages(locale)}
                </div>

                <!-- Téléchargement PDF -->
                <div class="seu-wi-link-group"> 
                    <a class="seu-wi seu-media" href="/pdf" target="_blank" title="Télécharger l'offre d'emploi en PDF"> 
                        <div class="seu-media-container"> 
                            <div class="seu-media-left"> 
                                <div class="seu-media-picto"></div> 
                            </div> 
                            <div class="seu-media-right"> 
                                <div class="seu-media-text"> 
                                    <div class="seu-media-title">Télécharger l'offre d'emploi en PDF</div> 
                                </div> 
                                <div class="seu-link-group-arrow"></div> 
                            </div> 
                        </div> 
                    </a> 
                </div>

                <!-- Candidater -->
                <a href="/candidater" class="seu-quicklink seu-btn-square seu-filled seu-second">
                    <span>Candidater</span>
                    <span class="seu-btn-arrow"></span>
                </a>
                
                <div class="seu-wi-duo">
                    <div class="columns-2">
                        <div class="portlet-layout row">
                            <div class="col-md-6 portlet-column portlet-column-first">
                                <div class="portlet-dropzone portlet-column-content portlet-column-content-first"></div>
                            </div>
                            <div class="col-md-6 portlet-column portlet-column-last">
                                <div class="portlet-dropzone portlet-column-content portlet-column-content-last"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="seu-wi seu-wi-quote offer-contact"> 
                    <div class="seu-container"> 
                        <h2 class="seu-section-title">
                            <span class="seu-title">Candidature spontané</span>
                            <div class="rte">
                                <div>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus</div>
                                <br>
                                <!-- Candidater -->
                                <a href="/candidater" class="seu-quicklink seu-btn-square seu-filled seu-second">
                                    <span>Candidater</span>
                                    <span class="seu-btn-arrow"></span>
                                </a>
                            </div>
                        </h2>
                    </div>
                </div>
            </div>  
        </div>  
    </main>
</div>