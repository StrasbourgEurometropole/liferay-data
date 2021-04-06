<!-- Conseils municipaux et eurométropolitains -->
<#setting locale = locale />

<style>
    #iframe-wrapper {
        padding-bottom: 20px;
    }
    
    #iframe-wrapper iframe {
        width: 100%;
    }
</style>

<#if videoUrl??>
    <#if videoUrl.getData()?has_content>
        <div class="seu-container">
            <div id="iframe-wrapper">
                <div class="tac_iframecreacast" allowfullscreen="allowfullscreen" scrolling="yes" data-url="${videoUrl.getData()}" width="100%" height="1000px" frameborder="0"> </div>
            </div>
        </div>
    </#if>
</#if>