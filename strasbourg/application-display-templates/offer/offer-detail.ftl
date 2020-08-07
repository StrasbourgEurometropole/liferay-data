<!-- Détail lieu -->
<#setting locale = locale />

<#assign imageUrl = ""/>
<!-- 1ère image au dessus de l'adresse -->
<#if entry.imagesURLs?first?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + entry.imagesURLs?first?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>
<!-- bannière -->
<#if entry.imageURL?has_content>
    <#assign imageUrl = themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getAlias(locale)?html}",
"og:description":'${entry.getPresentation(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
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

            <div class="seu-container-left">
                <!-- Informations -->
                <div class="seu-gray-box">
                    <div id="publicationId">
                        <h3><@liferay_ui.message key="eu.publicationId" /></h3>
                        <p>${entry.getPublicationId()}</p>
                    </div>
                    <div id="direction">
                        <h3><@liferay_ui.message key="eu.direction" /></h3>
                        <p>${entry.getDirection()}</p>
                    </div>
                    <div id="Service">
                        <h3><@liferay_ui.message key="eu.service" /></h3>
                        <p>${entry.getService()}</p>
                    </div>
                    <div id="Grade">
                        <h3><@liferay_ui.message key="eu.grade" /></h3>
                        <p>${entry.getGrade()}</p>
                    </div>
                    <div id="limitDate">
                        <h3><@liferay_ui.message key="eu.limitDate" /></h3>
                        <p>${entry.getLimitDate()}</p>
                    </div>
                </div>  
        
                <!-- Introduction  -->
                <div id="introduction ">
                    ${entry.getIntroduction()}
                </div>

                <!-- Missions activités -->
                <div id="activities ">
                    <h2><@liferay_ui.message key="eu.activities" /></h2>
                    ${entry.getActivities()}
                </div>

                <!-- Profil -->
                <div id="profil ">
                    <h2><@liferay_ui.message key="eu.profil" /></h2>
                    ${entry.getProfil()}
                </div>

                <!-- Condition -->
                <div id="conditions ">
                    <h2><@liferay_ui.message key="eu.conditions" /></h2>
                    ${entry.getConditions()}
                </div>

                <!-- Avantages -->
                <div id="avantages ">
                    <h2><@liferay_ui.message key="eu.avantages" /></h2>
                    ${entry.getAvantages()}
                </div>

                <!-- Téléchargement PDF -->

                <!-- Candidater -->
                <button>
                    Candidater
                </button>
            </div>  
        </div>  
    </main>
</div>

<style>
    .lfr-alert-container {
        position: static;
        padding: 40px;
        margin-bottom: 25px;
        background: #EF5350;
    }
    
    .lfr-alert-container .lfr-alert-wrapper {
        margin-bottom: 0;
        padding: 0;
        height: auto !important;
    }
    
    .lfr-alert-container .lfr-alert-wrapper + .lfr-alert-wrapper {
        margin-top: 15px;
    }
    
    .lfr-alert-container .alert-danger {
        background: none;
        border: none;
        margin: 0;
        padding: 0;
        color: white;
        line-height: 25px
    }
    
    .lfr-alert-container .alert-danger button,
    .lfr-alert-container .alert-danger .lead {
        display: none;
    }
    
    .mail-success {
        background: #4CAF50;
        color: white;
        padding: 40px;
    }

    @media only screen and (max-width: 767px){
        #aroundme .aroundme__ui__group {
            height: calc(100% - 20px); 
        }
    }
</style>
<#if entry.imageURL?has_content>
    <script>
        if ($('.region-banner').length == 0) {
            var bannerHtml = '<div class="region-banner"></div>';
            $('.region-post-header').addClass('has-banner').prepend(bannerHtml);
        }
    </script>
    <style>
        .region-banner {
            background-image: url(${entry.imageURL}) !important;
        }
    </style>
</#if>