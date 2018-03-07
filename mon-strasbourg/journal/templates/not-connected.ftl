<div class="seu-container" id="welcome-page">
	<h1>${title.data}</h1>
	<div class="video-container"><iframe src='${video.data}' frameborder='0' webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe></div>
	<section class="create-account-section">
		<h2>${createTitle.data}</h2>
		<div class="rte">
			${createText.data}
		</div>
			<a href="#" class="btn-square--filled--core">
				<span class="flexbox">
					<span class="btn-text">${createLabel.data}</span>
					<span class="btn-arrow"></span>
				</span>
			</a>
	</section>
	<section class="description-section">
		<h2>${descriptionTitle.data}</h2>
		<div class="rte">
			${descriptionText.data}
		</div>
	</section>
	<#list featureTitle.siblings as feature>
		<section class="feature">
			<img src="${feature.featureImage.data}">
			<div class="feature-info">
				<h3>${feature.data}</h3>
				<div class="rte">
					${feature.featureText.data}
				</div>
			</div>
		</section>
	</#list>
</div>
<script>
	$(function() {
		$('.btn-square--filled--core').attr('href', window.loginURL);
	});
</script>