<#setting locale = locale />
<div class="seu-quicklinks">
<#if linkLabel.getSiblings()?has_content>
  <#list linkLabel.getSiblings() as cur_linkLabel>
      <a href="${cur_linkLabel.getChildren()[0].getFriendlyUrl()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()}">
          <span class="seu-flexbox">
            <span class="seu-btn-text">${cur_linkLabel.getData()}</span>
            <span class="seu-btn-arrow"></span>
          </span>
        </a> 
  </#list>
</#if>
</div>
<style>
    #seu-banner {
        background-image: url(${backgroundImage.getData()}) !important;
    }
</style>