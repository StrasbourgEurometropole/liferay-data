<#assign originalLocale = locale>
<#setting locale = localeUtil.getDefault()>
<#assign displaydate = .vars['reserved-article-display-date'].data>
<#assign displaydate = displaydate?date("EEE, d MMM yyyy HH:mm:ss Z")>
<#setting date_format = "d MMMM yyyy">
<#setting locale = locale />

<!-- Détail actualité -->
<div class="container mns-actu-detail mns-fck">
    <div class="mns-top-detail-img">
        <img src="${image.data}" alt="${title.data}" width="930" height="620" />
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
