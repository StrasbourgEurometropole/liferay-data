<!-- Détail édition -->
<#setting locale = locale />
<section id="edition-detail" class="margin-top margin-bottom">
    <div  class="content container">
    
        <div class="image-with-copyright-on-hover">
            <img src="${entry.getImageURL()}" class="lightbox" alt="${entry.getTitle(locale)}" title="${entry.getTitle(locale)}">
            <#if entry.getImageCopyright(locale)?has_content>
                <div class="copyright"><span>C</span><span>${entry.getImageCopyright(locale)}</span></div>
            </#if>
        </div>
        
        <div class="edition-info">
            <h1 class="edition-title">
                ${entry.getTitle(locale)}
            </h1>
            
            <h2 class="edition-subtitle">
                ${entry.getSubtitle(locale)}
            </h2>
            
            <div class="edition-date">
                <@liferay_ui["message"] key="eu.edition.diffusion-date" /> : 
                <#if entry.getDiffusionDateMonth()?has_content>
                    ${entry.getDiffusionDateMonth()} /
                </#if> ${entry.getDiffusionDateYear()}
            </div>
            
            <#if entry.getTypes()?has_content>
                <div class="edition-types">
                <#list entry.getTypes() as type>
                    <div class="edition-type">
                        ${type.getTitle(locale)}
                    </div>
                </#list>
                </div>
            </#if>
    
            <#if entry.getSources()?has_content>
                <div class="edition-sources">
                    <#list entry.getSources() as source>
                        <div class="edition-source">
                            ${source.getTitle(locale)}
                        </div>
                    </#list>
                </div>
            </#if>
    
            <#if entry.getPublishedEditionGalleries()?has_content>
                <div class="edition-galleries">
                    <#list entry.getPublishedEditionGalleries() as gallery>
                        <@liferay_portlet.renderURL var="detailURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
                        <@liferay_portlet.param name="classPK" value="${gallery.getGalleryId()}" />
                        <@liferay_portlet.param name="returnURL" value="${currentURL}" />
                        </@liferay_portlet.renderURL>
                        <div class="edition-gallery">
                            <a href="${detailURL}">${gallery.getTitle(locale)}</a>
                        </div>
                    </#list>
                </div>
            </#if>
          
            <div class="edition-description">
                ${entry.getDescription(locale)}
            </div>
          
            <div class="edition-meta">
                <div class="edition-author">
                    ${entry.getAuthor(locale)}
                </div>
                
                <div class="edition-editor">
                    ${entry.getEditor(locale)}
                </div>
                
                <#if entry.getPageNumber()?has_content>
                    <div class="edition-page-number">
                        ${entry.getPageNumber()} <@liferay_ui["message"] key="eu.edition.pages" />
                    </div>
                </#if>
                
                <#if entry.getPictureNumber()?has_content>
                    <div class="edition-picture-number">
                        ${entry.getPictureNumber()} <@liferay_ui["message"] key="eu.edition.pictures" />
                    </div>
                </#if>
                
                <#if entry.getPrice()?has_content>
                    <div class="edition-price">
                        ${entry.getPrice()} €
                    </div>
                </#if>
                
                <#if entry.getISBN()?has_content>
                    <div class="edition-isbn">
                        <@liferay_ui["message"] key="eu.edition.isbn" /> : ${entry.getISBN()}
                    </div>
                </#if>
                
                <div class="edition-exchange-availability">
                    <#if entry.isAvailableForExchange()>
                        <@liferay_ui["message"] key="eu.edition.available-for-exchange" />
                    </#if>
                </div>
                
                <div class="edition-diffusion">
                    ${entry.getDistribution()}
                </div>
                
                <div class="edition-availability">
                    <#if entry.isInStock()>
                        <@liferay_ui["message"] key="eu.edition.in-stock" />
                    <#else>
                        <@liferay_ui["message"] key="eu.edition.out-of-stock" />
                    </#if>
                </div>
            </div>
            
            <#if entry.getFileDownloadURL(locale)?has_content>
                <div class="edition-download">
                    <a href="${entry.getFileDownloadURL(locale)}" download>
                        <@liferay_ui["message"] key="eu.edition.download" /> - ${entry.getFileType(locale)?upper_case} - ${entry.getFileSize(locale)}
                    </a>
                </div>
            </#if>
            
            <#if entry.getURL(locale)?has_content>
                <div class="edition-url">
                    <a href="${entry.getURL(locale)}" title="<@liferay_ui["message"] key="eu.new-window" />" target="_blank">
                        <@liferay_ui["message"] key="eu.edition.interactive-version-link" />
                    </a>
                </div>
            </#if>
        </div>
    </div>
</section>