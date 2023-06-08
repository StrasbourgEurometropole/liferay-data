<#setting locale = locale />

<div class="offer-banner seu-quicklinks row">
<#if linkLabel.getSiblings()?has_content>
    <#list linkLabel.getSiblings() as cur_linkLabel>
        <#if cur_linkLabel.getChildren()[0].getFriendlyUrl()?has_content>
            <a href="${cur_linkLabel.getChildren()[0].getFriendlyUrl()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()}">        
        <#else>
            <#if cur_linkLabel.getChildren()[1].getChildren()[0]?has_content && cur_linkLabel.getChildren()[1].getChildren()[0].getData()?has_content >
                <a href="${cur_linkLabel.getChildren()[1].getData()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()}" >     
            <#else>
                <a href="${cur_linkLabel.getChildren()[1].getData()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
            </#if>
        </#if>
        
          <span class="seu-flexbox">
            <span class="seu-btn-text">${cur_linkLabel.getData()}</span>
            <span class="seu-btn-arrow"></span>
          </span>
        </a> 
  </#list>
</#if>
</div>
<style>
    .seu-quicklinks{
        justify-content: center;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 60px;
    }
    
    .seu-quicklinks .seu-quicklink {
        margin: 0 5px 10px;
    }

    .seu-quicklink:first-child{
        background-color: #fbb52d !important;
    }

    .seu-quicklink:nth-child(2){
        grid-column: 2/4;
    }
</style>