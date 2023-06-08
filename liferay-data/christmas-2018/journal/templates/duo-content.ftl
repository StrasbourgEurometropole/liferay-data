<#setting locale = locale />

<!-- Détail article -->
<div class="container mns-wrapper-duo-content">
	<#list right.getSiblings() as row>
    <div class="row">        
    	<#if row.data =='true'>
    	<div class="col-md-6 col-xs-12 msn-duo-left order-2">        
            <h2>${row.children[0].getData()}</h2>
            ${row.children[1].getData()}
        </div>
    	<div class="col-md-6 col-xs-12 msn-duo-right">
            <figure class="mns-wrapper-img">
                <img src="${row.children[2].getData()}" alt="image responsive" >
            </figure>
        </div>
        <#else>
        <div class="col-md-6 col-xs-12 msn-duo-left order-2">
            <figure class="mns-wrapper-img">
                <img src="${row.children[2].getData()}" alt="image responsive" >
            </figure>
        </div>
        <div class="col-md-6 col-xs-12 msn-duo-right">        
            <h2>${row.children[0].getData()}</h2>
            ${row.children[1].getData()}
        </div>
        </#if>
    </div>
    </#list>
</div>