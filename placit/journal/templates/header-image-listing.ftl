<header class="pro-header-small">
    <figure class="fit-cover" role="group">
        <img src="${image.getData()}" width="1600" height="255" alt="${image.getAttribute("alt")}"/>
    </figure>
    <div class="container caption">
        <h1>${title.getData()}</h1>
        <#if description.getData()?? >
	        <div class="pro-description">
	            <p>${description.getData()}</p>
	        </div>
	    </#if>
    </div>
</header>