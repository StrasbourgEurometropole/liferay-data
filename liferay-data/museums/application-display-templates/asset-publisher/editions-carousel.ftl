<!-- Carousel éditions -->
<#setting locale = locale />
<#if !themeDisplay.scopeGroup.publicLayoutSet.virtualHostname?has_content || themeDisplay.scopeGroup.isStagingGroup()>
   <#assign homeURL = "/web${layout.group.friendlyURL}/" />
<#else>
   <#assign homeURL = "/" />
</#if>
<#if entries?has_content>
    <div class="items-carousel editions-carousel">
        <h3 class="items-carousel-title">
            <@liferay_ui["message"] key="eu.editions" />
            <#assign PortalUtil = staticUtil["com.liferay.portal.kernel.util.PortalUtil"] />
            <!-- Le lien vers la page de toutes les éditions est défini comme étant la page "/videos", modifier la ligne ci-dessous si besoin -->
            <a href="${PortalUtil.getGroupFriendlyURL(themeDisplay.getLayoutSet(), themeDisplay)}/editions"><@liferay_ui.message key="eu.edition.all-editions" /></a>    
        </h3>
        <div class="owl-carousel">
          <#list entries as curEntry>
            <#assign edition = curEntry.getAssetRenderer().getEdition() />
            <#assign viewURL = homeURL + "detail-edition/-/entity/id/" + edition.editionId />
            <div class="item"> 
                <div class="item-image">
                    <a href="${viewURL}">
                        <img src="${edition.getImageURL()}" >
                    </a>
                </div>
                <div class="item-info">
                    <div class="item-title">
                        <h4><a href="${viewURL}">${edition.getTitle(locale)}</a></h4>
                    </div>
                    <#if (edition.getFileType(locale)?length > 0)>
                        <div class="item-type-and-size">
                            ${edition.getFileType(locale)} — ${edition.getFileSize(locale)}
                        </div>
                    </#if>
                    <div class="item-download-link">
                        <a href="${edition.getFileDownloadURL(locale)}" download>
                            <@liferay_ui["message"] key="download" />
                        </a>
                    </div>
                </div>
            </div>
          </#list>
        </div>
    </div>
</#if>
