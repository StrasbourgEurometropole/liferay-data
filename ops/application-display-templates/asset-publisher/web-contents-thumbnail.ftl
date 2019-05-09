<!-- VIGNETTE DE WEB CONTENTS POUR LA RECHERCHE GLOBALE -->

<#-- Chargement de la variable de localisation -->
<#setting locale = locale />

<#-- Récupération de l'XML du contenu web -->
<#assign docXml = saxReaderUtil.read(entry.getContentByLocale(locale)) />

<#-- Champs concernant une actualité -->
<#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")/>
<#if !title?has_content><#assign title = entry.getTitle(locale) /></#if>
<#assign thumbnail = docXml.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()") />

<#-- Champs concernant un membre -->
<#assign name = docXml.valueOf("//dynamic-element[@name='Name']/dynamic-content/text()")/>
<#assign post = docXml.valueOf("//dynamic-element[@name='post']/dynamic-content/text()")/>
<#assign bigImage = docXml.valueOf("//dynamic-element[@name='bigImage']/dynamic-content/text()")/>

<#-- Champs concernant une offre -->
<#assign postName = docXml.valueOf("//dynamic-element[@name='postName']/dynamic-content/text()")/>
<#assign contestDate = docXml.valueOf("//dynamic-element[@name='date']/dynamic-content/text()")/>

<#-- Création de l'URL de détail -->
<#assign layoutHelper = serviceLocator.findService("eu.strasbourg.utils.api.LayoutHelperService") />
<#assign detailURL = layoutHelper.getJournalArticleLayoutURL(entry.groupId, entry.articleId, themeDisplay) />

<!-- VIGNETTE ACTUALITE -->
<#if title?has_content && thumbnail?has_content && !contestDate?has_content>
    <a href="${detailURL}" class="ops-card ops-card-article">
        <div>
            <#if thumbnail?has_content>
                <figure class="fit-cover">
                    <img src="${thumbnail}" width="390" height="360" alt="Image article"/>
                </figure>
            </#if>
            <div class="ops-content-card-actu">
                <div class="ops-meta-card-article">
                    <div class="ops-cats">
                        <span class="ops-cat"><@liferay_ui.message key="eu.ops.in.picture" /></span>
                    </div>
                    <span class="ops-date-article">
                        <@liferay_ui.message key="eu.ops.published.on" /> <time datetime="${entry.getModifiedDate()?datetime?string('yyyy-MM-dd')}">${entry.getModifiedDate()?datetime?string("dd.MM.yyyy")}</time>
                    </span>
                </div>
                <h3>${title}</h3>
                <span class="ops-link"><@liferay_ui.message key="eu.ops.discover" /></span>
            </div>
        </div>
    </a>

<!-- VIGNETTE MEMBRE -->
<#elseif name?has_content && post?has_content >
    <#-- Si pas de page de detail, alors vignette non cliquable-->
    <#if detailURL?has_content>
        <a href="${detailURL}" class="ops-card ops-card-std">
    <#else>
        <div class="ops-card ops-card-std">
    </#if>
            <div>
                <#if bigImage?has_content>
                    <figure class="fit-cover">
                        <img src="${bigImage}" width="390" height="560" alt="Image membre"/>
                    </figure>
                <#else>
                    <div class="ops-no-photo"></div>
                </#if>
                <h3>
                    <strong>${post}</strong>
                    <br>
                    ${name}
                </h3>
            </div>
        </a>
    <#if detailURL?has_content>
        </a>
    <#else>
        </div>
    </#if>

<#-- VIGNETTE OFFRE -->
<#elseif title?has_content && contestDate?has_content>
    <a href="${detailURL}" class="ops-card ops-card-emploi">
        <div>
            <span class="ops-date-concours">
                <@liferay_ui.message key="eu.ops.contest.date" /> : <time datetime="${contestDate}">${contestDate?date.xs?string("dd.MM.yyyy")}</time>
            </span>
            <h3>${title}</h3>
            <span class="ops-link"><@liferay_ui.message key="eu.ops.learn.more" /></span>
        </div>
    </a>
</#if>