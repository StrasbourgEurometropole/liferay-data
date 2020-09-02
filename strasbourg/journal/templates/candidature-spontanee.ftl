<div class="seu-wi seu-wi-quote offer-contact"> 
    <div class="seu-container"> 
        <h2 class="seu-section-title">
            <span class="seu-title"><@liferay_ui.message key="eu.offer-candidature-spontanee" /></span>
        </h2>
        <div class="rte">
            ${content.getData()}
            <br>
            <!-- Candidater -->
            <button type="button" class="seu-btn-square seu-filled seu-core" id="candidatureSpontanee">
                <span class="seu-flexbox">
                    <span class="seu-btn-text">
                        <a><@liferay_ui.message key="eu.offer-candidater" /></a>
                    </span>
                    <span class="seu-btn-arrow"></span>
                </span>
            </button>
        </div>
    </div>
</div>

<#assign StrasbourgPropsUtil = serviceLocator.findService("eu.strasbourg.utils.api.StrasbourgPropsUtilService") />
    
<script>
    document.getElementById("candidatureSpontanee").onclick = function(e){
        // on vérifie que l'utilisateur est connecté
        if(window.publikInternalId != undefined){
            window.location = "${StrasbourgPropsUtil.getPublikApiBase()}${StrasbourgPropsUtil.getPublikApiApply()}?refposte=0&libposte=candidature%20spontanee";
        }else{
            window.createPopin(Liferay.Language.get('log-in-to-apply'),function() {
                window.location = window.loginURL;
            },undefined,Liferay.Language.get('eu.login'), Liferay.Language.get('eu.cancel'));
        }
    };
</script>