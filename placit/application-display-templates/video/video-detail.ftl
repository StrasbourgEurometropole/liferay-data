<!-- DETAIL D'UNE VIDEO -->

<!-- Recuperation de l'URL de "base" du site -->
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostnames?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<!-- Recuperation de la source URL de la vidéo -->
<#assign videoURL = entry.getSource(locale) />
<!-- Recuperation du site cible de la vidéo -->
<#assign site = entry.getSiteVideo(videoURL) />
<!-- Recuperation de l'id de la vidéo -->
<#assign videoId = entry.getVideoId(site, videoURL) />
<!-- Recuperation du nombre de vue de la vidéo -->
<#assign nbViews = entry.getNbViews(site,videoId) />

<#assign imageUrl = ""/>
<!-- vignette -->
<#if entry.imageURL?has_content>
    <#assign imageUrl=themeDisplay.getPortalURL() + entry.imageURL?replace('@', "")?replace('cdn_hostroot_path', "") />
</#if>

<#-- Liste des infos a partager -->
<#assign openGraph = {
"og:title":"${entry.getTitle(locale)?html}",
"og:description":'${entry.getDescription(locale)?replace("<[^>]*>", "", "r")?html}', 
"og:image":"${imageUrl}"
} />
<#-- partage de la configuration open graph dans la request -->
${request.setAttribute("LIFERAY_SHARED_OPENGRAPH", openGraph)}

<main id="content">
    <header>
        <div class="container pro-homepage-video">
            <div class="pro-bloc-video">
                <div class="mask-video">
                    <figure class="o80" role="group">
                        <img alt="${entry.getTitle(locale)}" width="1170" height="600" src="${entry.imageURL}">
                        <figcaption>${entry.getCopyright(locale)}</figcaption>
                    </figure>
                    <a href="#play" class="btn-ytbe" title="Lire la vidéo">
                        <span class="pro-btn-video"><span class="icon-ico-lecteur"></span>Voir la vidéo</span>
                    </a>
                </div>
                <div class="embed-container" data-urlvideo="${entry.getEmbedURL(site, videoURL)}"></div>
            </div>
        </div>
    </header>

    <div class="container pro-bloc-content-video">
        <div class="col-lg-10 col-lg-offset-1">

            <div id="breadcrumb">
            <span>
                <span>
                    <a href="${homeURL}">Accueil</a>
                    <a href="${homeURL}videos">Les vidéos</a>
                    <span class="breadcrumb_last">${entry.getTitle(locale)}</span>
                </span>
            </span>
            </div>

            <div class="row">
                <div class="col-sm-8 pro-bloc-texte">
                    <span class="pro-time">Publiée le <time datetime="2018-01-10">${entry.getPublicationDate()?string("dd/MM/yyyy")}</time></span>
                    <h1>${entry.getTitle(locale)}</h1>
                    ${entry.getDescription(locale)}
                </div>

                <aside class="col-sm-4">
                    <div class="pro-meta-avis">
                        <div class="pro-avis">
                            <a href="#pro-avis-like-pro" class="pro-like"
                                data-typeid="3" 
                                data-isdislike="false"
                                data-title='${entry.getTitle(locale)}' 
                                data-entityid="${entry.videoId}"
                                data-entitygroupid="${entry.groupId}">
                                ${entry.nbLikes}
                            </a>
                            <a href="#pro-avis-dislike-pro" class="pro-dislike"
                                data-typeid="3" 
                                data-isdislike="true"
                                data-title='${entry.getTitle(locale)}'
                                data-entityid="${entry.videoId}"
                                data-entitygroupid="${entry.groupId}">
                                ${entry.nbDislikes}
                            </a>
                        </div>
                        <#if nbViews != "" >
                            <span class="pro-view">
                                ${nbViews?number} vues
                            </span>
                        </#if>
                    </div>
                    <!-- Recuperation des thématiques de la vidéo -->
                    <#assign thematicCategories = entry.thematicCategories />
                    <!-- Liste des thématiques de la vidéo -->
                    <#if thematicCategories?has_content >
                        <div class="pro-item">
                            <p><strong>Thématiques : </strong></p>
                            <#list thematicCategories as thematicCategory >
                                <p>
                                    ${thematicCategory.getTitle(locale)}<#sep>, </#sep>
                                </p>
                            </#list>
                        </div>
                    </#if>
                    <!-- Recuperation des quartiers de la vidéo -->
                    <#assign districtCategories = entry.districtCategories />
                    <!-- Liste des quartiers de la participation -->
                    <#if districtCategories?has_content >
                        <div class="pro-item">
                            <p><strong>Quartier(s) : </strong></p>
                            <#list districtCategories as districtCategory >
                                <p>
                                    ${districtCategory.getTitle(locale)}<#sep>, </#sep>
                                </p>
                            </#list>
                        </div>
                    </#if>
                    <#if entry.projectCategory??>
                        <div class="pro-item">
                            <p><strong>Projet : </strong></p>
                            <!-- Recuperation du projet de la vidéo -->
                            <#assign projectCategory = entry.projectCategory />
                            <p>${projectCategory.getTitle(locale)}</p>
                        </div>
                    </#if>
                </aside>
            </div>
        </div>
    </div>


    <!-- Recuperation des thématiques de la vidéo -->
    <#assign suggestions = entry.getSuggestions(locale, 4) />
    <!-- Liste des thématiques de la vidéo -->
    <#if suggestions?size gt 0 >
        <!-- BLOC ACTU DES QUARTIERS -->
        <section class="pro-pad-40 pro-bloc-select">
            <div class="container">
                <div class="pro-intro">
                    <h2>Cela pourrait vous interesser</h2>
                    <a href="${homeURL}videos" class="pro-btn">Voir Toutes les vidéos</a>
                </div>

                <div class="row">
                    <#list suggestions as suggestion >
                        <!-- Recuperation de la source URL de la vidéo -->
                        <#assign videoURL = suggestion.getSource(locale) />
                        <!-- Recuperation du site cible de la vidéo -->
                        <#assign site = suggestion.getSiteVideo(videoURL) />
                        <!-- Recuperation de l'id de la vidéo -->
                        <#assign videoId = suggestion.getVideoId(site, videoURL) />
                        <!-- Recuperation du nombre de vue de la vidéo -->
                        <#assign nbViews = suggestion.getNbViews(site,videoId) />

                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="pro-card-video" data-linkall=".pro-link-all">
                                <div class="pro-header">
                                    <figure class="fit-cover" role="group">
                                        <img alt="${suggestion.getTitle(locale)}" width="280" height="175" src="${suggestion.imageURL}">
                                    </figure>
                                    <span class="icon-ico-lecteur"></span>
                                </div>
                                <div class="pro-meta-avis">
                                    <div class="pro-avis">
                                        <a href="#pro-avis-like-pro" class="pro-like"
                                            data-typeid="3" 
                                            data-isdislike="false"
                                            data-title='${suggestion.getTitle(locale)}' 
                                            data-entityid="${suggestion.videoId}"
                                            data-entitygroupid="${suggestion.groupId}">
                                            ${suggestion.nbLikes}
                                        </a>
                                        <a href="#pro-avis-dislike-pro" class="pro-dislike"
                                            data-typeid="3" 
                                            data-isdislike="true"
                                            data-title='${suggestion.getTitle(locale)}'
                                            data-entityid="${suggestion.videoId}"
                                            data-entitygroupid="${suggestion.groupId}">
                                            ${suggestion.nbDislikes}
                                        </a>
                                    </div>
                                    <#if nbViews != "" >
                                        <span class="pro-view">
                                            ${nbViews?number} vues
                                        </span>
                                    </#if>
                                </div>
                                <a href="${homeURL}detail-video/-/entity/id/${suggestion.videoId}" title="Vers la page ${suggestion.getTitle(locale)}" class="pro-link-all"><h3>${suggestion.getTitle(locale)}</h3></a>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </section>
    </#if>
</main>