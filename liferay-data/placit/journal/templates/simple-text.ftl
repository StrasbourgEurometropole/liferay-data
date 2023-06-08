<!-- BLOC SIMPLE TEXTE -->

<div class="container">
    <div class="pro-bloc pro-bloc-texte ${selectAlignment.getData()}">
        <h2>${title.getData()}</h2>
        <p>${text.getData()}</p>
        <#if linkUrl.getData() != "">
        	<a href="${linkUrl.getData()}" class="pro-btn-yellow" <#if linkUrl.getData() != "">title="${linkLabel.getData()}"</#if>>En savoir plus</a>
        </#if>
    </div>
</div>