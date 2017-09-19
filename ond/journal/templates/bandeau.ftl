<ul class="<#if .vars['reserved-article-title'].data == 'bandeau-ond'> slide_header <#else> slide_header_child </#if>">
<#list image.getSiblings() as item>
<li><img src="${item.getData()}" /></li>
</#list>
</ul>