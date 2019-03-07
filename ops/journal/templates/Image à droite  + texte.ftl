<!-- BLOC TXT-IMG - IMAGE DROITE ET TEXTE GAUCHE -->
<div class="ops-col-wrapper ops-bloc ops-bloc-texte ops-bloc-txt-img ops-bloc-align-right">
	<div class="ops-col-50">
		<figure class="fit-cover">
			<img src="${image.getData()}" width="580" height="540" alt="Image gauche"/>
			<figcaption>${image.getAttribute("alt")}</figcaption>
		</figure>
	</div>
	<div class="ops-col-50">
		<span class="ops-surtitre">${subTitle.getData()}</span>
		<h2>${title.getData()}</h2>
		<p>${text.getData()}</p>
		<#if linkURL.getData()?has_content && linkLabel.getData()?has_content>
			<a href="${linkURL.getData()}" class="ops-btn" <#if openInaNewTab.getData() == "true">target="_blank"</#if> >${linkLabel.getData()}</a>
		</#if>
	</div>
</div>