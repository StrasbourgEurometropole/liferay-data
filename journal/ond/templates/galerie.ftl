<div class="portlet-body">
	<script>
	/*<![CDATA[*/
	$(function(){
	    $(".asset-header-div .column-title a").each(
	        function(){
	            $(this).text(add3Dots($(this).text(),69));
	        }
	    );
	});
	/*]]>*/</script>
	<div class="portlet">
		<div class="asset-header-image-page">
			<div>
              <span class="title-with-picto-span last-${mainTitle.getData()}">${mainTitle.getData()}</span>
			</div>
			<div class="asset-header-div asset-header-image-div results-grid">
				<div class="column-image">
					<table>
						<tbody>
							<tr>
								<td>
									<a class="teasing" title="${imageTitle.getData()}" href="${imageLink.getData()}">
              							<#if image.getAttribute('alt')?has_content>
											<img src="${image.getData()}" alt="© ${image.getAttribute('alt')} " title="${imageTitle.getData()}" />
              							<#else>
											<img src="${image.getData()}" title="${imageTitle.getData()}" />
              							</#if>
									</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="column-footer">
					<div class="column-title">
						<a title="${imageTitle.getData()}" href="${imageLink.getData()}" class="">${imageTitle.getData()}</a>
					</div>
					<div class="column-bottom">
						<a class="image-link btn-more" title="${buttonText.getData()}" href="${buttonLink.getData()}">${buttonText.getData()}</a>
						<div class="clearer">&nbsp;</div>
					</div>
				</div>
				<div class="clearer">&nbsp;</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
</div>