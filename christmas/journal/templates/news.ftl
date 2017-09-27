<#assign originalLocale = locale>
<#setting locale = localeUtil.getDefault()>
<#assign displaydate = .vars['reserved-article-display-date'].data>
<#assign displaydate = displaydate?date("EEE, d MMM yyyy HH:mm:ss Z")>
<#setting date_format = "d MMMM yyyy">
<#setting locale = locale />

<!-- Détail actualité -->
<div class="small-container mns-actu-detail mns-fck">
    <div class="mns-top-detail-img">
        <img src="${image.data}" alt="${title.data}" width="930" height="620" />
    </div>
    <div class="mns-detail-actu-content col-sm-9 col-sm-offset-3 col-xs-12">
        <h1>${title.data}</h1>
        <span class="publication">Publiée le ${displaydate}</span>
        <span class="mns-line"></span>
        ${text.data}
    </div>
</div>

<style>
    #mns-global .mns-header-single h1,
    #mns-global .mns-header-standard h1 {
        display: none;
    }
</style>
<script>
    $('.mns-header-standard').attr('class', 'mns-header-single');
</script>