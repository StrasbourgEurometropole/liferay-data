<!-- Détail édition -->

<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
  <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
  <#assign homeURL = "/" />
</#if>

<div class="seu-container wi-edition-detail">
    <h1>${entry.getTitle(locale)}</h1>
    <div class="rte">
        <div class="text-center">
            <img src="${entry.imageURL}" title="${entry.getTitle(locale)}" />
        </div>
        <h2><@liferay_ui.message key="description" /></h2>
        ${entry.getDescription(locale)}
        <div class="edition-date">
            <@liferay_ui.message key="eu.published-in" />
            
            <#if entry.getDiffusionDateMonth()?has_content>
                <@liferay_ui.message key="month-${entry.getDiffusionDateMonth()}" />
            </#if>
            ${entry.getDiffusionDateYear()}
        </div>
        <#if entry.getFileDownloadURL(locale)?has_content>
            <div class="seu-wi seu-media seu-wi-download">
                <div class="seu-media-container">
                    <div class="seu-media-left">
                        <div class="seu-media-picto"></div>
                    </div>
                    <div class="seu-media-right">
                        <div class="seu-media-text">
                            <div class="seu-media-title">${entry.getFileTitle(locale)}
                            </div>
                            <#if entry.getFileSize(locale)?has_content && entry.getFileType(locale)?has_content>
                                <p>${entry.getFileType(locale)} - ${entry.getFileSize(locale)}</p>
                            </#if>
                        </div>
                        <a download href="${entry.getFileDownloadURL(locale)}" class="seu-media-download seu-btn-square seu-filled seu-second" title="<@liferay_ui.message key="download" />">
                            <span class="seu-flexbox">
                                <span class="seu-btn-text"><@liferay_ui.message key="download" /></span>
                                <span class="seu-btn-arrow"></span>
                            </span>
                        </a>
                    </div>
                </div>
            </div>
        </#if>
        <#if entry.getURL(locale)?has_content>
            <div class="seu-wi-link-group">
                <a class="seu-wi seu-media" href="${entry.getURL(locale)}" target="_blank" title="<@liferay_ui.message key="eu.edition.read-interractive-version" />">
                    <div class="seu-media-container">
                        <div class="seu-media-left">   
                            <div class="seu-media-picto"></div>
                        </div>
                        <div class="seu-media-right">
                            <div class="seu-media-text"> 
                                <div class="seu-media-title"><@liferay_ui.message key="eu.edition.read-interractive-version" /></div>
                            </div> 
                            <div class="seu-link-group-arrow"></div>
                        </div>
                    </div>
                </a>
            </div>
        </#if>
        <#if entry.getPublishedEditionGalleries()?has_content>
            <#list entry.getPublishedEditionGalleries() as gallery>
                <div class="seu-wi-link-group">
                    <a class="seu-wi seu-media" href="${homeURL}galerie-editions/-/entity/id/${gallery.galleryId}" title="Lien vers la galerie">
                        <div class="seu-media-container">
                            <div class="seu-media-left">   
                                <div class="seu-media-picto"></div>
                            </div>
                            <div class="seu-media-right">
                                <div class="seu-media-text"> 
                                    <div class="seu-media-title">Lien vers la galerie</div>
                                </div> 
                                <div class="seu-link-group-arrow"></div>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
        </#if>
    </div>
</div>