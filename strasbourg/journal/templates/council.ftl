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

<#if VideoUrl.getData()?has_content>
    <div class="seu-container">
        <div id="iframe-wrapper">
            <iframe  scrolling="yes" height="1000px" frameborder="0" allowfullscreen  src="${VideoUrl.getData()}"></iframe>
        </div>
    </div>
</#if>