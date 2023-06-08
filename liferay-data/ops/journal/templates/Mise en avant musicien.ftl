<div class="ops-bloc-highlight-people" id="ops-distribution">
	<#list name.getSiblings() as cur_title>
		<div class="ops-people">
			<figure class="fit-cover">
				<img src="${cur_title.getChild("image").getData()}" width="1600" height="475" alt="Nom du musicien"/>
			</figure>
			<div class="ops-caption-text">
				<span class="ops-function">${cur_title.getChild("fonction").getData()}</span>
				<h2>${cur_title.getData()}</h2>
				<p>${cur_title.getChild("description").getData()}</p>
				<#if cur_title.getChild("linkURL").getData()?has_content && cur_title.getChild("linkLabel").getData()?has_content>
					<a href="${cur_title.getChild("linkURL").getData()}" class="ops-btn"  <#if cur_title.getChild("openInaNewTab").getData() == "true">target="_blank"</#if>>${cur_title.getChild("linkLabel").getData()}</a>
				</#if>
			</div>
		</div>
	</#list>
</div>