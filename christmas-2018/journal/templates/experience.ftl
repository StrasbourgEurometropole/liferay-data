<#setting locale = locale />


<#assign journalArticleId = .vars['reserved-article-id'].data>
<#assign journalArticleResourceLocalServiceUtil = staticUtil["com.liferay.journal.service.JournalArticleResourceLocalServiceUtil"]>
<#assign assetCategoryLocalServiceUtil = staticUtil["com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil"]>


<#assign articleResourcePK = journalArticleResourceLocalServiceUtil.getArticleResourcePrimKey(groupId, journalArticleId)/>
<#assign categoryList=assetCategoryLocalServiceUtil.getCategories("com.liferay.journal.model.JournalArticle",articleResourcePK) >

<div class="mns-page-experience">
    <header class="container mns-top-experience">
        <div class="small-container">
            <div class="row">
                <h1>${title.data}</h1>
                <div class="mns-meta">
                    <div class="mns-wrap-tag">
                        <#list categoryList as category>
                            <span>${category.getName()}</span>
                        </#list>
                    </div>
                    <span class="mns-location">${lieu.data}</span>
                    <figure class="fit-cover">
                        <img src="${image.data}" width="60" height="60" alt="Nom de l'auteur de l'expérience">
                    </figure>
                </div>
                <div class="mns-bloc-texte">
                    ${text.data}
                </div>
            </div>
        </div>
    </header>
</div>