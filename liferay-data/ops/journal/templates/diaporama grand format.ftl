<!-- BLOC DIAPORAMA GRAND FORMAT-->
<div class="ops-bloc-diaporama ops-bloc ops-bloc-large" data-vheight="100 - 80">
	<div class="slick-carousel slick-basic-slider">
		<#list title.getSiblings() as cur_title>
			<div class="ops-item">
				<figure class="fit-cover">
					<img src="${cur_title.getChild("image").getData()}" width="1600" height="900" alt="Nom de l'image"/>
				</figure>
				<div class="ops-caption ops-bloc-texte">
					<h2>${cur_title.getData()}</h2>
					<p>${cur_title.getChild("description").getData()}</p>				
					<#if cur_title.getChild("linkURL").getData()?has_content && cur_title.getChild("linkLabel").getData()?has_content>
						<a href="${cur_title.getChild("linkURL").getData()}" class="ops-btn" <#if cur_title.getChild("openInaNewTab").getData() == "true">target="_blank"</#if> >${cur_title.getChild("linkLabel").getData()}</a>
					</#if>
				</div>
			</div>	
		</#list>		
	</div>

	<div class="ops-slick-nav"></div>
</div>