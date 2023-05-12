<div class="web-content" id="museum-news">
    <div class="back-button"><a href="javascript:history.back()"><@liferay_ui.message key="back" /></a></div>
    <h1 class="web-content-title">
        ${title.getData()}
    </h1>
    <div class="web-content-chapo">
        ${chapo.getData()}
    </div>
    <div class="web-content-text">
        ${content.getData()}
    </div>
</div>

<script>
    var museumNews = document.getElementById("museum-news");
    var el = museumNews.parentNode;
    while(!el.classList.contains('portlet-boundary')){
        el = el.parentNode;
    }
    //el is now your parent
    Array.prototype.filter.call(el.parentNode.children, function(child){
        if (child !== el) {
            // Success!
            child.style.display='none';
        } 
    });
</script>