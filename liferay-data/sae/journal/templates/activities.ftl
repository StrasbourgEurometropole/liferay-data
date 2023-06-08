<section id="new-activities">
	<a href="${link1.getData()}" class="item">
		<div class="background" style="background-image:url(${image1.getData()});" role="img" aria-label=""></div>
		<div class="block-content">
			<div>
				<span class="titre">${title1.getData()}</span> 
				<#if text1?? && text1.getData()?has_content>
					<div class="chapo"><p>${text1.getData()}</p></div>
				</#if>
			</div> 
		</div> 
	</a> 
	<a href="${link2.getData()}" class="item"> 
		<div class="background" style="background-image:url(${image2.getData()});" role="img" aria-label=""></div>
		<div class="block-content">
			<div>
				<span class="titre">${title2.getData()}</span>
				<#if text2?? && text2.getData()?has_content>
					<div class="chapo"><p>${text2.getData()}</p></div>
				</#if>
			</div>
		</div>
	</a> 
	<a href="${link3.getData()}" class="item"> 
		<div class="background" style="background-image:url(${image3.getData()});" role="img" aria-label=""></div>
		<div class="block-content">
			<div>  
				<span class="titre">${title3.getData()}</span> 
				<#if text3?? && text3.getData()?has_content>
					<div class="chapo"><p>${text3.getData()}</p></div>
				</#if>
			</div> 
		</div> 
	</a> 
</section>