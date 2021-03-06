<!-- BLOC ACTUALITE GRAND FORMAT -->

<header class="ops-fullpage-header" data-vheight="100">	
	<div class="slick-carousel slick-basic-slider">
		<#list title.getSiblings() as cur_title>
			<div class="ops-item">
				<figure class="fit-cover">
					<img src="${cur_title.getChild("backgroundImage").getData()}" width="1600" height="900" alt="Orchestre"/>
				</figure>
				<div class="ops-content-wrapper ops-content-wrapper-large ops-caption">
					<span class="ops-typologie">${cur_title.getChild("typologie").getData()}</span>
					<h1>${cur_title.getData()}</h1>
					<p>${cur_title.getChild("description").getData()}</p>
					<#if cur_title.getChild("linkURL").getData()?has_content && cur_title.getChild("linkLabel").getData()?has_content>
						<a href="${cur_title.getChild("linkURL").getData()}" class="ops-btn" <#if cur_title.getChild("openInaNewTab").getData() == "true">target="_blank"</#if> >${cur_title.getChild("linkLabel").getData()}</a>
					</#if>
				</div>
			</div>
		</#list>
	</div>		
	<div class="ops-slick-nav"></div>
</header>