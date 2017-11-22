<!-- Conseils municipaux et eurométropolitains -->
<#setting locale = locale />

<style>
    #iframe-wrapper {
        position: relative;
        height: 0;
        overflow: hidden;
        padding-bottom: 100%; /* For 16:9 ratio */
    }
    
    #iframe-wrapper iframe {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }
</style>

<#if VideoUrl.getData()?has_content>
    <div class="seu-container">
        <div id="iframe-wrapper">
            <iframe  scrolling="yes" height="1000px" frameborder="0" allowfullscreen  src="${VideoUrl.getData()}"></iframe>
        </div>
    </div>
</#if>