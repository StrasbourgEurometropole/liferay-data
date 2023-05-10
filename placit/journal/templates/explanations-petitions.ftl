<!-- Recuperation de la localisation de l'utilisateur -->
<#setting locale = locale />

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation du gestionnaire de fichiers Liferay -->
<#assign fileEntryHelper = serviceLocator.findService("eu.strasbourg.utils.api.FileEntryHelperService") />

<div class="pro-page-budget-participatif">

    <section class="container">
        <div >

            <div class="col-xs-12 pro-wrapper-title">
                <h1>${rightTitle.getData()}</h1>
            </div>

            <div class="row pro-wrapper-content">
                <div class="col-xs-12">
                    <div id="breadcrumb">
                        <span>
                            <span>
                                <a href="${homeURL}">Accueil</a>
                            </span>
                        </span>
                    </div>
                </div>

                <div class="col-sm-6">
                    <div class="pro-preambule">
                        <h2 class="pro-title">${leftTitle.getData()}</h2>
                        ${leftTitle.getChild("leftText").getData()}
                    </div>
                </div>

                <div class="col-sm-6 pro-annexes">
                    <div>
                        <h3>Annexes à télécharger</h3>
                        <ul>
                            <#list rightTitle.getChild("files").getSiblings() as file>

                                <#if file.getData()?has_content >

                                    <#assign fileEntry = fileEntryHelper.getFileEntryByRelativeURL(file.getData()) />    
                                    <#assign title = fileEntryHelper.getFileTitle(fileEntry.getFileEntryId(), locale) />
                                    <#assign size = fileEntryHelper.getReadableFileEntrySize(fileEntry.getFileEntryId(), locale) />

                                    <li>
                                        <a href="${file.getData()}" download title="${title}">
                                            ${title}
                                            <span class="pro-poids">Poids ${size}</span>
                                        </a>
                                    </li>

                                </#if>

                            </#list>
                        </ul>

                        <#if rightTitle.getChild("linksContent").getData() !="" >
                            <div class="pro-wrapper-btn">
                                ${rightTitle.getChild("linksContent").getData()}
                            </div>
                        </#if>

                    </div>

                </div>
            </div>
        </div>
    </section>

    <section class="container pro-menu-budget-participatif">
        <div class="aligncenter">
            <h2>${underTitle.getData()}</h2>
        </div>

        <div class="row pro-menu-budget-1">
            <div class="col-sm-6 pro-budget-img">
                <figure role="group">
                    <img src="/o/plateforme-citoyenne-theme/images/medias/img-budget-montage-projet.jpg" width="387" height="387" alt="Image Montage et Dépôt de projet" />
                </figure>
                <span class="pro-number">1</span>
                <span class="pro-circle"><span class="icon-ico-analyse"></span></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent1").getData()}
            </div>
        </div>

        <div class="row pro-menu-budget-2">
            <div class="col-sm-6 pro-budget-img">
                <span class="pro-number">2</span>
                <span class="pro-circle"><span class="icon-ico-vote"></span></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent2").getData()}
            </div>
        </div>

        <div class="row pro-menu-budget-3">
            <div class="col-sm-6 pro-budget-img">
                <span class="pro-number">3</span>
                <span class="pro-circle"><span class="icon-ico-montage-projet"></span></span>
                <span class="pro-circle-left"></span>
                <span class="pro-circle-right"></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent3").getData()}
            </div>
        </div>

        <div class="row pro-menu-budget-4">
            <div class="col-sm-6 pro-budget-img">
                <span class="pro-number">4</span>
                <span class="pro-circle"><span class="icon-ico-laureat"></span></span>
                <span class="pro-circle-left"></span>
                <span class="pro-circle-right"></span>
            </div>
            <div class="col-sm-6 pro-budget-txt">
                ${underTitle.getChild("underContent4").getData()}
            </div>
        </div>
    </section>


    <#if prefooterContent.getData() !="" >
        <div class="pro-bloc-prefooter pro-sticky-bar">
            <div class="container">
                <div class="col-xs-12 aligncenter pro-wrapper-btn">
                     ${prefooterContent.getData()}
                </div>
            </div>
        </div>
    </#if>

</div>