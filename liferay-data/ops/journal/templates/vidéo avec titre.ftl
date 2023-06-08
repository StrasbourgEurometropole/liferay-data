<!-- BLOC VIDEO AVEC CAPTION -->
<div class="ops-content-wrapper ops-bloc ops-bloc-small ops-bloc-texte ops-bloc-video">
	<div class="ops-infos-videos">
		<div>
			<h3>${title.getData()}</h3>
		</div>
	</div>
	<div class="ops-wrapper-video">
		<div class="mask-video">
			<figure class="fit-cover">
				<img src="${image.getData()}" alt="image couverture vidéo" width="650" height="369">
			</figure>
			<a href="#" class="ops-video-link" aria-label="Lecture de la vidéo">
				<span class="icon-ico-lecture"></span>
			</a>
		</div>
		<div class="embed-container" data-video_id="${videoLink.getData()}" data-video_plateforme="youtube"></div>
	</div>
</div>