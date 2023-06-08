<!-- Onglet exposition -->
<#setting locale = locale />
<div class="exhibition">
  <div class="exhibition-tabs">
    <#if entries?has_content>
      <#assign x = 0>
      <#list entries as curEntry><a href="#" class="exhibition-tab <#if x == 0>active</#if>" data-entry-id="${curEntry.getClassPK()}">

        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />

        ${title}

        <#assign x++>
      </a></#list>
    </#if>
  </div>
  <div class="exhibition-tabs-contents">
    <#if entries?has_content>
      <#assign x = 0>
      <#list entries as curEntry>
        <#assign docXml = saxReaderUtil.read(curEntry.getAssetRenderer().getArticle().getContentByLocale(locale)) />
        <#assign title = docXml.valueOf("//dynamic-element[@name='title']/dynamic-content/text()") />
        <#assign content = docXml.valueOf("//dynamic-element[@name='content']/dynamic-content/text()") />

        <div class="exhibition-tab-content <#if x == 0>active</#if>" data-entry-id="${curEntry.getClassPK()}">
          <div class="exhibition-tab-content-title">
            ${title}
          </div>
          ${content}
        </div>
        
        <#assign x++>
      </#list>
    </#if>
  </div>
</div>
