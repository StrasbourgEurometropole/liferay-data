<section id="schedule" class="section schedule-section align-center" style="padding-top: 90px">
  <div class="container">
    <span class="icon section-icon icon-office-21"></span>
    <h3>${title.getData()}</h3>
    <p class="text-alt">${subtitle.getData()}</p>
    <p class="text-alt"><a href="/agenda">Voir tous les événements</a></p>
    <br />
    <br />
    <!-- Schedule start -->
    <div class="schedule">
      <!-- Navigation by day start -->
      <ul class="nav nav-schedule">
        <#if day.getSiblings()?has_content>
          <#list day.getSiblings() as cur_day>
            <#if cur_day?index == 0>
              <li class="active">
            <#else>
              <li>
            </#if>
                <a href="#" data-activate-day="${cur_day?index + 1}">
                <h5 class="highlight">${cur_day.getData()}</h5>
                <p class="text-alt">${cur_day.getChildren()[0].getData()}</p>
              </a>
            </li>
          </#list>
        </#if>
      </ul>
    </div>
</section>