<#if entries?has_content>
    <div class="items-carousel editions-carousel">
        <h3 class="items-carousel-title">Editions</h3>
        <div class="owl-carousel">
          <#list entries as curEntry>
            <#assign edition = curEntry.getAssetRenderer().getEdition() />
            <#assign viewURL = "" />
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
                            Télécharger
                        </a>
                    </div>
                </div>
            </div>
          </#list>
        </div>
    </div>
</#if>