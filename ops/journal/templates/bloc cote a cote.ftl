<!-- BLOC SIDE BY SIDE -->
<div class="ops-bloc ops-bloc-texte ops-bloc-side-by-side">
	<h2 class="ops-big-title">${title.getData()}</h2>
	<div class="ops-col-50">
		<div class="ops-top-img">
			<figure class="fit-cover">
				<img src="${imageLeft.getData()}" width="530" height="500" alt="Image side"/>
			</figure>
			<h3>${titleLeft.getData()}</h3>
		</div>
		<div class="ops-content">
			<p>${textLeft.getData()}</p>
		</div>
	</div>
	<div class="ops-col-50">
		<div class="ops-top-img">
			<figure class="fit-cover">
				<img src="${imageRight.getData()}" width="530" height="500" alt="Image side"/>
			</figure>
			<h3>${titleRight.getData()}</h3>
		</div>
		<div class="ops-content">
			<p>${textRight.getData()}</p>
		</div>
	</div>
</div>