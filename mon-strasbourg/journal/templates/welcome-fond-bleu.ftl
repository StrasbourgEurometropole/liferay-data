<!-- Welcome - fond bleu -->

<section class="bloc-bleu" >
    <div class="bloc-content ${icon.getData()}">
        <p class="title">${title.getData()}</p>
        <p>${content.getData()}</p>
    </div>
    <#if image.getData()?? && image.getData() != "">
        <div class="bloc-fond-image">
            <div class="image" style="background-image: url('${image.getData()}');">
            </div>
        </div>
    </#if>
</section>