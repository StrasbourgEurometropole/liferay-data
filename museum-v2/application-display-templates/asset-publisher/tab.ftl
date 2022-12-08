<!-- Onglet -->
<#setting locale = locale />
<section id="tab" class="container margin-top margin-bottom">
  <div class="tabs">
    <#if entries?has_content>
      <#assign x = 0>
      <#list entries as curEntry>
        <div class="tab <#if x == 0>active</#if>" data-entry-id="${curEntry.getClassPK()}">
            <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
            <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
            ${title}
            <#assign x++>
        </div>
      </#list>
    </#if>
  </div>
  <div class="contents">
    <#if entries?has_content>
      <#assign x = 0>
      <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
        <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />

        <div class="content <#if x == 0>active</#if>" data-entry-id="${curEntry.getClassPK()}">
          <div class="title">
            ${title}
          </div>
          <div class="description">
            ${content}
          </div>
        </div>
        
        <#assign x++>
      </#list>
    </#if>
  </div>
</section>