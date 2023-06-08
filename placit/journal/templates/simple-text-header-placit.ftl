<!-- BLOC SIMPLE TEXTE A ATTACHER LE STRUCTUREE TEXTE SIMPLE-->
<div class="pro-bloc-texte-header pt-4">
<div class="container">
<div class="row pro-bloc pro-bloc-texte ${selectAlignment.getData()}">
    <div class="col-md-8">
        <h2>${title.getData()}</h2>
        <p>${text.getData()}</p>
        <#if linkUrl.getData() != "">
        	<a href="${linkUrl.getData()}" class="pro-btn-yellow" <#if linkUrl.getData() != "">title="${linkLabel.getData()}"</#if>>En savoir plus</a>
        </#if>
    </div>
</div>
</div>
</div>