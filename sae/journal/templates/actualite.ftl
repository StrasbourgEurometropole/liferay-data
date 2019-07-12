<#assign imageUrl = ""/>
<!-- image -->
<#if illustration.getData()?has_content>
    <#assign imageUrl = illustration.getData() />
</#if>
<script>
    title = '${title.getData()?html?js_string}';
    description = '${text.getData()?replace("<[^>]*>", "", "r")?html?js_string}';
    imageUrl = '${imageUrl}';
</script>

<#assign displaydate = .vars['reserved-article-display-date'].data?date('EEE, dd MMM yyyy hh:mm:ss Z')/> 

<style>
.class-inner .page-header {
  background-image: url(${illustration.getData()}) !important;
}
</style>

<div class="container">
	<h1>${title.getData()}</h1>
	<h4>${displaydate?string("dd MMMM yyyy")}</h4>
	<p>${text.getData()}</p>				
</div>