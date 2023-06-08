<!-- Vignette activité -->
<#setting locale = locale />

<div style="margin-bottom: 20px; border-bottom: 1px solid grey;" class="activity-detail">
  <h1>Titre : ${entry.getTitle(locale)}</h1>
  <ul>
    <li><a href="/web${layout.group.friendlyURL}${detailPageFriendlyURL}/-/entity/id/${entry.activityId}">Voir détail</a>
  </ul>
  <div class="courses">
    <#if courses?has_content>
      <#assign activityCourses = courses />
    <#else>
      <#assign activityCourses = entry.publishedActivityCourses />
    </#if>
    
    <#list activityCourses as course>
      <ul>
        <li>${course.getName(locale)}</li>
        <ul>
          <li>
            Lieux et horaires :
            <ul>
              <li>Changer de période :
               <ul>
                  <#list periods as period>
                    <li><a href="#" class="switchPeriod" data-period-id="${period.categoryId}">${period.getTitle(locale)}</a>
                  </#list>
                </ul>
              </li>
            </ul>
            <table>
              <tr>
                <td>Lieu</td>
                <td>Lundi</td>
                <td>Mardi</td>
                <td>Mercredi</td>
                <td>Jeudi</td>
                <td>Vendredi</td>
                <td>Samedi</td>
                <td>Dimanche</td>
              </tr>
              <#list course.activityCoursePlaces as coursePlace>
                <tr>
                  <td>${coursePlace.getPlaceAlias(locale)}</td>
                  <#list 0..6 as day>
                    <td>
                      <#list coursePlace.getActivityCourseSchedulesForDay(day) as schedule>
                        <p title="${schedule.getComments(locale)}" data-periods-ids="${schedule.periodsIds}">${schedule.startTime} - ${schedule.endTime}</p>
                      </#list>
                    </td>
                  </#list>
                </tr>
              </#list>
            </table>
          </li>
        </ul>
      </ul>
    </#list>
  </div>
</div>

<style>
  .activity-detail img {
    max-width: 200px;
  }
  .activity-detail td, .activity-detail th {
    border: 1px solid black;
    padding: 10px;
  }
  .activity-detail td:first-child {
    width: 300px;
  }
</style>
<script>
  jQuery('.switchPeriod').on('click', function(e) {
    e.preventDefault();
    var periodId = $(this).data('period-id');
    jQuery('.switchPeriod').css('text-decoration', 'none');
    jQuery('[data-period-id=' + periodId + ']').css('text-decoration', 'underline');
    jQuery('.activity-detail td p').hide();
    jQuery('[data-periods-ids*=' + periodId + ']').show();
  });
  jQuery('.switchPeriod').first().trigger('click');
</script>
