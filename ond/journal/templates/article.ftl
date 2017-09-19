
<div class="article-detail">
    <#if illustration.getAttribute('alt')?has_content>
	   <h1 class="article-title ${illustration.getAttribute('alt')}">${title.getData()}</h1>
    <#else>
       <h1 class="article-title ">${title.getData()}</h1>
    </#if>
    <div class="article-image">
        <div class="imgcontainer">
            <#if illustration.getAttribute('alt')?has_content && illustration.getAttribute('title')?has_content >
                <a title="${illustration.getAttribute('title')} © ${illustration.getAttribute('alt')}" href="${illustration.getData()}" class="magnific-popup">
            <#else>
                <a href="${illustration.getData()}" class="magnific-popup">
            </#if>
            <#if illustration.getAttribute('alt')?has_content && illustration.getAttribute('title')?has_content >
                <img src="${illustration.getData()}" alt="${illustration.getAttribute('alt')}" title="${illustration.getAttribute('title')}" />
            <#else>
                <img src="${illustration.getData()}"  />
            </#if>
			</a>
						
            <div class="copyright-caption">
                <#if illustration.getAttribute('alt')?has_content >
                    &copy;&nbsp;${illustration.getAttribute('alt')}&nbsp;
                </#if>		
            </div>								
        </div>
    </div>
	<div class="article-catcher">${catcher.getData()}</div>
	<div class="article-clear"></div>
	<div class="article-texte">
		${text.getData()}
	</div>
</div>