<div class="article-detail">
	<h1 class="article-title ${illustration.getAttribute('alt')}">${title.getData()}</h1>
	<#if illustration.getData()?has_content>
        <div class="article-image">
            <div class="imgcontainer">
				<a title="${illustration.getAttribute('title')} © ${illustration.getAttribute('alt')}" href="${illustration.getData()}" class="magnific-popup">
					<img src="${illustration.getData()}" alt="${illustration.getAttribute('title')}" title="${illustration.getAttribute('title')}" />
				</a>
																				
				<#if illustration.getAttribute('alt')?has_content>
		      		<div class="copyright-caption">&copy;&nbsp;${illustration.getAttribute('alt')}&nbsp;</div>
				</#if>
            </div>
        </div>
	</#if>
	<div class="article-catcher">${catcher.getData()}</div>
	<div class="article-clear"></div>
	<div class="article-texte">
		${text.getData()}
	</div>
</div>