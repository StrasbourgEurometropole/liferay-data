<!-- Testimoniaux-->
<#setting locale = locale />
<section id="testimonials" class="section align-center" style="padding-top: 25px;">
  <div class="container">
    <span class="icon section-icon icon-chat-messages-04"></span>
    <h3>${title.getData()}</h3>
    <p class="text-alt" style="margin-bottom: 80px;">${subtitle.getData()}</p>
    <div class="row">
      <#list name.getSiblings() as cur_name>
        <div class="col-sm-4">
          <div class="testimonial">
            <article class="text-box">
              ${cur_name.getChildren()[0].getData()}
            </article>
            <div class="author-block">
              <div class="photo-container" style="background-image: url(${cur_name.getChildren()[2].getData()})"></div>
              <strong class="name">${cur_name.getData()}</strong>
              <small class="text-alt company">${cur_name.getChildren()[1].getData()}</small>
            </div>
          </div>
        </div>
      </#list>
    </div>
  </div>
</section>