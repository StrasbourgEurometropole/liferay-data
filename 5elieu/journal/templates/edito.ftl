<!-- Présentation -->
<#setting locale = locale />
<section id=presentation class="section align-center">
  <div class="container">
    <h3>${title.getData()}</h3>
    <p class="text-alt">
      ${subtitle.getData()}
    </p>
    <br />
    <div class="col-sm-6">
      <article>
        ${leftText.getData()}
      </article>
    </div>
    <div class="col-sm-6">
      <article>
        ${rightText.getData()}
      </article>
    </div>
  </div>
</section>