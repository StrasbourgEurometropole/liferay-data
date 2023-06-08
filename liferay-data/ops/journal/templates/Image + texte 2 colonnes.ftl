<!-- BLOC COLONNES - 2 COLONNES -->
<div class="ops-col-wrapper ops-bloc ops-bloc-small ops-bloc-texte ops-bloc-colonne" data-egalize="> * > *">
	
	<#-- Bloc gauche -->
	<div class="ops-col-50">
		<#if linkURLLeft.getData()?has_content && linkLabelLeft.getData()?has_content>
            	<a href="${linkURLLeft.getData()}" <#if openInaNewTabLeft.getData() == "true">target="_blank"</#if>>
		<#else>
            	<div>
        	</#if>

			<figure class="fit-cover">
				<img src="${imageLeft.getData()}" width="460" height="298" alt="Image"/>
			</figure>
			<div class="ops-bloc-content-actu">
				<h3>${titleLeft.getData()}</h3>
				<p>${descriptionLeft.getData()}</p>
				<#if linkURLLeft.getData()?has_content && linkLabelLeft.getData()?has_content>
					<span class="ops-basic-link">${linkLabelLeft.getData()}</span>
				</#if>
			</div>

		<#if linkURLLeft.getData()?has_content && linkLabelLeft.getData()?has_content>
            	</a>
		<#else>
            	</div>
        	</#if>
	</div>

	<#-- Bloc droit -->
	<div class="ops-col-50">
		<#if linkURLRight.getData()?has_content && linkLabelRight.getData()?has_content>
            	<a href="${linkURLRight.getData()}" <#if openInaNewTabRight.getData() == "true">target="_blank"</#if>>
		<#else>
            	<div>
        	</#if>

			<figure class="fit-cover">
				<img src="${imageRight.getData()}" width="460" height="298" alt="Image"/>
			</figure>
			<div class="ops-bloc-content-actu">
				<h3>${titleRight.getData()}</h3>
				<p>${descriptionRight.getData()}</p>
				<#if linkURLRight.getData()?has_content && linkLabelRight.getData()?has_content>
					<span class="ops-basic-link">${linkLabelRight.getData()}</span>
				</#if>
			</div>

		<#if linkURLRight.getData()?has_content && linkLabelRight.getData()?has_content>
            	</a>
		<#else>
            	</div>
        	</#if>
	</div>
</div>