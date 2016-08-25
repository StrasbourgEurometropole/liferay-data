<#if entries?has_content>
    <div class="news-carousel">
        <h3 class="news-carousel-title">Notre <span>Actualité</span></h3>
        <div class="owl-carousel">
          <#list entries as curEntry>
            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContent()) />
                <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
                <#assign chapo = docXml.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()") />
                <#assign image = docXml.valueOf("//dynamic-element[@name='image']/dynamic-content/text()") />
                <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />
                <#assign publishDate = curEntry.getPublishDate() />
                
            <div class="item"> 
                <div class="item-image">
                    <a href="#">
                        <img src="${image}" >
                    </a>
                </div>
                <div class="item-info">
                    <div class="item-date">
                        <date>Publié le ${publishDate?date}</date>
                    </div>
                    <div class="item-title">
                        <h4><a href="#">${title}</a></h4>
                    </div>
                    <div class="item-content">
                        ${chapo}
                    </div>
                    </div>
            </div>
          </#list>
        </div>
    </div>
</#if>