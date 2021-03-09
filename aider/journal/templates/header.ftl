<header class="pro-header-small col-md-10 col-md-offset-1 col-sm-12">
    <div class="caption col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1 col-sm-12">
        <h1>${title.getData()}</h1>
        <#if description.getData()?? >
	        <div class="pro-description">
	            <p>${description.getData()}</p>
	        </div>
	    </#if>
    </div>
</header>