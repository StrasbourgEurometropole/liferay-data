<!-- Rubriques -->
<#setting locale = locale />
<#if entries?has_content>
	<#list entries as currentPage>

		<div class="rubric"> 
			<h3 id="rubricTitle"> 
				<a href="${themeDisplay.pathFriendlyURLPublic}${currentPage.group.friendlyURL}${currentPage.friendlyURL}">${currentPage.getName(locale)}</a> 
			</h3> 
			<div class="rubricThumbnail"> 
				<a href="${themeDisplay.pathFriendlyURLPublic}${currentPage.group.friendlyURL}${currentPage.friendlyURL}"> 
					<img src="${currentPage.getExpandoBridge().getAttribute('image')}" alt="${currentPage.getName(locale)}"> 
				</a> 
			</div> 
		</div>
		
	</#list>
</#if>