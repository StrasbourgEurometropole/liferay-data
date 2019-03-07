<!-- BLOC VIDEO -->

<div class="ops-content-wrapper ops-bloc ops-bloc-small ops-bloc-texte ops-bloc-video">
	<div class="ops-wrapper-video">
		<div class="mask-video">
			<figure class="fit-cover">
				<#if image.getData()?has_content >
                    <img src="${image.getData()}" alt="${image.getAttribute("alt")}" width="960" height="545" />
                </#if>
			</figure>
			<a href="#" class="ops-video-link" aria-label="Lecture de la vidéo">
				<span class="icon-ico-lecture"></span>
			</a>
		</div>
		<div class="embed-container" data-video_id="${videoLink.getData()}" data-video_plateforme="youtube"></div>
	</div>
</div>