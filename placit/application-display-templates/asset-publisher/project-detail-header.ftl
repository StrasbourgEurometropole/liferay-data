<#setting locale = locale />
<#assign currEntry = entry.getAssetRenderer().getProject() />


<header>
    <figure class="fit-cover">
        <img src="${currEntry.imageURL}" alt="" width="1600" height="600">
    </figure>
    <div class="container caption">
        <div class="pro-bloc-display"><span class="pro-surtitre">${currEntry.getProjectStatus(locale)}</span></div>
        <h1>${currEntry.title}</h1>
        <div class="pro-bloc-display"><span class="pro-soustitre">Quartier concerné : <strong>${currEntry.getDistrictCategories(locale)}</strong></span></div>
        <div class="pro-bloc-display-desc"><p>${currEntry.description}</p></div>
    </div>
</header>

<style>
.pro-page-detail.pro-page-detail-projet section.portlet{
margin : 0px;
}
</style>