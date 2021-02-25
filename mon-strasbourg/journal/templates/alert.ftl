<#setting locale = locale />

<#if URL?has_content && URL.data?has_content >
    <script type="text/javascript">
        window.location = '${URL.data}';
    </script>
</#if>

<p class="seu-published">
   <@liferay_ui.message key="eu.published-on" /> ${.vars['reserved-article-display-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")} 
   - <@liferay_ui.message key="eu.modified-on" /> ${.vars['reserved-article-modified-date'].getData()?date('EEE, dd MMM yyyy hh:mm:ss Z')?string("dd/MM/yyyy")}
</p>
<h1>${title.getData()}</h1>
<div class="hat">
    <div>
        ${chapo.getData()}
    </div>
</div>
<div class="rte">
    ${content.getData()}
</div>

<style>
    .page-depth li:nth-child(2){
    	display: none;
    }
</style>