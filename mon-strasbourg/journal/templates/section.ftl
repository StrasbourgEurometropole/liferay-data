<section class="home-section">
	<h2>${title.data}</h2>
	<div class="rte">
		${text.data}
	</div>
	<#if buttonURL.data?has_content>
		<div class="form-group">
			<div class="content" align="center">
				<a href="${buttonURL.data}" target="_blank" class="btn-square--bordered--core" title="${buttonText.data}">
					<span class="flexbox">
						<span class="btn-text">
							${buttonText.data}
						</span>
						<span class="btn-arrow"></span>
					</span>
				</a>
			</div>
		</div>
	</#if>
</section>
