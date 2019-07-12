<#assign originalLocale = locale>
<#setting locale = localeUtil.getDefault()>
<#assign displaydate = .vars['reserved-article-display-date'].data>
<#assign displaydate = displaydate?date("EEE, d MMM yyyy HH:mm:ss Z")>
<#setting date_format = "d MMMM yyyy">
<#setting locale = locale />

<#assign imageUrl = ""/>
<!-- image -->
<#if image.getData()?has_content>
    <#assign imageUrl = image.getData() />
</#if>
<script>
    title = '${title.getData()?html?js_string}';
    description = '${text.getData()?replace("<[^>]*>", "", "r")?html?js_string}';
    imageUrl = '${imageUrl}';
</script>

<!-- Détail actualité -->
<div class="container mns-actu-detail mns-fck">
    <div class="mns-top-detail-img">
        <img src="${image.data}" alt="${title.data}" />
    </div>
    <span class="publication"><@liferay_ui.message key="eu.published-on" /> ${displaydate}</span>
    <div class="mns-detail-actu-content col-xs-12">
        <div class="mns-title">
            <h1>${title.data}</h1>
        </div>
        <span class="mns-line"></span>
        ${text.data}
    </div>
</div>
