<!-- Entête de page -->
<#setting locale = locale />
<#if page?has_content>
	<div id="infos">
		<div id="titleDiv">
		    <h1>${page.getName(locale)}</h1>
		</div>
        <#if layout.expandoBridge.getAttribute('image')?has_content>
    		<div id="rubricHeaderImgDiv">
    		    <img src="${layout.expandoBridge.getAttribute('image')}" alt="${page.getName(locale)}">
    		</div>
        </#if>
	    <div id="descriptionDiv">${page.getDescription(locale)}</div>
	</div>
</#if>