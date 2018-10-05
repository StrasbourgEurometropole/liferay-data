<#setting locale = locale />

<!-- Détail article -->
<div class="container mns-wrapper-duo-content">
	<#list right.getSiblings() as row>
    <div class="row">        
    	<#if row.data =='true'>
    	<div class="col-md-6 col-xs-12 msn-duo-left">        
            <h2>${row.children[0].data}</h2>
            ${row.children[1].data}
        </div>
    	<div class="col-md-6 col-xs-12 msn-duo-right">
            <figure class="mns-wrapper-img">
                <img src="${row.children[2].data}" alt="image responsive" width="563" height="768">
            </figure>
        </div>
        <#else>
        <div class="col-md-6 col-xs-12 msn-duo-left">
            <figure class="mns-wrapper-img">
                <img src="${row.children[2].data}" alt="image responsive" width="563" height="768">
            </figure>
        </div>
        <div class="col-md-6 col-xs-12 msn-duo-right">        
            <h2>${row.children[0].data}</h2>
            ${row.children[1].data}
        </div>
        </#if>
    </div>
    </#list>
</div>