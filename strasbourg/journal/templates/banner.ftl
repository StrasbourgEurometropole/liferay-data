<#setting locale = locale />
<@liferay_util["html-top"]>
    <meta property="og:title" content="Strasbourg.eu" />
    <meta property="og:description" content="Bienvenue sur le site de Strasbourg et de l'Eurométropole" />
    <meta property="og:url" content="${homeURL}" />
    <meta property="og:image" />
    <meta property="og:image:height" />
    <meta property="og:image:width" />
</@>

<#assign imageUrl = themeDisplay.getPortalURL() + backgroundImage.getData() />

<script> 
    function getMeta(url, callback) {
        var img = new Image();
        img.src = url;
        img.onload = function() { callback(this.width, this.height); }
    }
    
    getMeta("${imageUrl}", function(width, height) { 
        document.querySelector('[property="og:image"]').setAttribute("content", "${imageUrl}");
        document.querySelector('[property="og:image:width"]').setAttribute("content", width);
        document.querySelector('[property="og:image:height"]').setAttribute("content", height);
    });

</script>
<div class="seu-quicklinks">
<#if linkLabel.getSiblings()?has_content>
    <#list linkLabel.getSiblings() as cur_linkLabel>
        <#if cur_linkLabel.getChildren()[0].getFriendlyUrl()?has_content>
            <a href="${cur_linkLabel.getChildren()[0].getFriendlyUrl()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()}">        
        <#else>
            <a href="${cur_linkLabel.getChildren()[1].getData()}" class="seu-quicklink seu-btn-square seu-filled seu-second" title="${cur_linkLabel.getData()} (<@liferay_ui.message key="eu.new-window" />)" target="_blank">
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
    #seu-banner {
        background-image: url(${backgroundImage.getData()}) !important;
    }
</style>