<!-- BLOC COLONNES - 3 COLONNES -->
<div class="ops-col-wrapper ops-bloc ops-bloc-small ops-bloc-texte ops-bloc-colonne" data-egalize="> * > *">
	
	<#-- Bloc gauche -->
	<div class="ops-col-33">

		<#if linkURLLeft.getData()?has_content && linkLabelLeft.getData()?has_content>
			<a href="${linkURLLeft.getData()}" <#if openInaNewTabLeft.getData() == "true">target="_blank"</#if>>
		<#else>
			<div>
		</#if>

			<figure class="fit-cover">
				<img src="${imageLeft.getData()}" width="293" height="190" alt="Image"/>
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

	<#-- Bloc milieu -->
	<div class="ops-col-33">

		<#if linkURLMiddle.getData()?has_content && linkLabelMiddle.getData()?has_content>
            <a href="${linkURLMiddle.getData()}" <#if openInaNewTabMiddle.getData() == "true">target="_blank"</#if>>
		<#else>
            <div>
        </#if>

			<figure class="fit-cover">
				<img src="${imageMiddle.getData()}" width="293" height="190" alt="Image"/>
			</figure>
			<div class="ops-bloc-content-actu">
				<h3>${titleMiddle.getData()}</h3>
				<p>${descriptionMiddle.getData()}</p>
				<#if linkURLMiddle.getData()?has_content && linkLabelMiddle.getData()?has_content>
					<span class="ops-basic-link">${linkLabelMiddle.getData()}</span>
				</#if>
			</div>

		<#if linkURLMiddle.getData()?has_content && linkLabelMiddle.getData()?has_content>
            </a>
		<#else>
            </div>
        </#if>

	</div>

	<#-- Bloc droit -->
	<div class="ops-col-33">

		<#if linkURLRight.getData()?has_content && linkLabelRight.getData()?has_content>
            <a href="${linkURLRight.getData()}" <#if openInaNewTabRight.getData() == "true">target="_blank"</#if>>
		<#else>
            <div>
        </#if>

			<figure class="fit-cover">
				<img src="${imageRight.getData()}" width="293" height="190" alt="Image"/>
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