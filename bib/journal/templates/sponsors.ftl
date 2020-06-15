<!-- Partenaires -->
<#setting locale = locale />
<section id="sponsors" class="section align-center">
  <div class="container">
    <span class="icon section-icon icon-documents-bookmarks-12"></span>
    <h3>${title.getData()}</h3>
    <p class="text-alt">${subtitle.getData()}</p>
    <br/>
    <br/>
    <div class="sponsors">
      <#list sponsors.getSiblings() as cur_sponsor>
        <div class="sponsor big"><a href="${cur_sponsor.getChildren()[0].getData()}"><img src="${cur_sponsor.getData()}"></a></div>
      </#list>
    </div>
  </div>
</section>