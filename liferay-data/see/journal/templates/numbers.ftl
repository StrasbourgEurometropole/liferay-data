<!-- Chiffres clés-->
<#setting locale = locale />
<section id="counters" class="section align-center overlay bg-cover bg5 light-text">
  <div class="container">
    <div class="row counters-wrapper">
      <#if number.getSiblings()?has_content>
        <#list number.getSiblings() as cur_number>
          <div class="col-sm-3">
            <div class="counter-block counter-block-no-border">
              <div class="counter-box">
                <div class="counter-content">
                  <span class="count" data-from="0" data-to="${cur_number.getData()}">0</span>
                  <p class="title">${cur_number.getChildren()[0].getData()}</p>
                </div>
              </div>
            </div>
          </div>
        </#list>
      </#if>
    </div>
  </div>
</section>
<style>
  .see .bg5 {
    background-image: url(${backgroundImage.getData()});
  }
</style>