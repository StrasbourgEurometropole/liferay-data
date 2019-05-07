<!-- VIGNETTE DE WEB CONTENTS POUR LA RECHERCHE GLOBALE -->

<!-- Chargement de la variable de localisation -->
<#setting locale = locale />

<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
    <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
    <#assign homeURL = "/" />
</#if>

<#-- Récupération des champs du contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>

<#-- Champs concernant une actualité -->
<#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />

<#-- Champs concernant un membre -->
<#assign name = docXml.valueOf("//dynamic-element[@name='name']/dynamic-content/text()")/>
<#assign post = docXml.valueOf("//dynamic-element[@name='post']/dynamic-content/text()")/>

<#-- Champs concernant une offre -->
<#assign postName = docXml.valueOf("//dynamic-element[@name='postName']/dynamic-content/text()")/>
<#assign date = docXml.valueOf("//dynamic-element[@name='date']/dynamic-content/text()")/>

<#-- Création de l'URL de détail -->
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<#-- Affichage d'une actualité -->
<a href="${detailURL}" class="ops-card ops-card-article">
    <div>
        <figure class="fit-cover">
            <img src="assets/images/medias/txt-img-1.jpg" width="390" height="360" alt="Nom de l'image de l'article"/>
        </figure>
        <div class="ops-content-card-actu">
            <div class="ops-meta-card-article">
                <div class="ops-cats">
                    <span class="ops-cat">En image</span>
                </div>
                <span class="ops-date-article">Publié le <time>${entry.getModifiedDate()?datetime?string("dd.MM.yyyy")}</time></span>
            </div>
            <h3>${title}</h3>
            <span class="ops-link">Découvrir</span>
        </div>
    </div>
</a>

<#-- Affichage d'un membre-->
<a href="${detailURL}" class="ops-card ops-card-std">
    <div>
        <figure class="fit-cover">
            <img src="assets/images/medias/txt-img-2.jpg" width="390" height="560" alt="Image page standard"/>
        </figure>
        <h3>${name}</h3>
    </div>
</a>

<#-- Affichage d'une offre-->
<a href="${detailURL}" class="ops-card ops-card-emploi">
    <div>
        <span class="ops-date-concours">Date du concours : <time datetime="2019-01-10">10.01.2019</time></span>
        <h3>${title}</h3>
        <span class="ops-link">En savoir plus</span>
    </div>
</a>