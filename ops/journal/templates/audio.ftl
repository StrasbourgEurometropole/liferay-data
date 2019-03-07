<!-- BLOC AUDIO -->
<div id="ops-audio" class="ops-col-wrapper ops-bloc ops-bloc-audio" data-egalize=".ops-col-25">
	<div>
		<h3 class="ops-title-line"><span>Audio</span></h3>
	</div>
	<#list title.getSiblings() as cur_title>
		<div class="ops-col-25">
			<div class="ops-embed">
				<iframe width="270" height="270" allow="autoplay"
						src="${cur_title.getChild("linkSoundcloud").getData()}"></iframe>
			</div>
			<h4>${title.getData()}</h4>
			<span class="ops-name">${cur_title.getChild("artistName").getData()}</span>
		</div>
	</#list>
</div>