<!-- Programme à télécharger -->
<#setting locale = locale />
<section class="section bg-cover overlay bg4 light-text align-center">
  <div class="container">
    <div class="col-sm-12 align-center">
      <a href="${linkURL.getData()}" target="_blank" class="btn btn-outline btn-sm">${downloadLabel.getData()}</a>
    </div>
  </div>
</section>
<style>
    .see .bg4 {
        background-image: url(${backgroundImage.getData()?split('?')[0]});
    }
</style>