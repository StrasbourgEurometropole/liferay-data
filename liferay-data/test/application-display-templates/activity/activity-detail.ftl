<!-- Détail activité -->
<#setting locale = locale />

<div style="margin-bottom: 20px; border-bottom: 1px solid grey;" class="activity-detail">
  <h1>Titre : ${entry.getTitle(locale)}</h1>
  <ul>
    <li>Description : ${entry.getDescription(locale)}</li>
    <li>Image : <img src="${entry.imageURL}"></li>
    <li>Video : 
      <ul>
        <#list entry.videos as video>
          <li>
            ${video.getPlayer(locale)}
          </li>
        </#list>
      </ul>
    </li>
    <li>Visuels :
      <ul>
        <#list entry.imagesURLs as imageURL>
          <li>
            <img src="${imageURL}">
          </li>
        </#list>
      </ul>
    </li>
    <li>Fichiers :
      <ul>
        <#list entry.filesURLs as fileURL>
          <li>
            <a href="${fileURL}">Fichier</a>
          </li>
        </#list>
      </ul>
    </li>
    <li>Types : ${entry.getTypesLabel(locale)}</li>
  </ul>

  <div class="courses">
  <h2>Cours</h2>
    <#list entry.publishedActivityCourses as course>
      <ul>
        <li>Titre : ${course.getName(locale)}</li>
        <li>Activité : ${course.getActivity().getTitle(locale)}</li>
        <li>Modalités : ${course.getArrangements(locale)}</li>
        <li>Prix : ${course.getPrice(locale)}</li>
        <li>Publics : ${course.getPublicsLabel(locale)}</li>
        <li>Types : ${course.getTypesLabels(locale)}</li>
        <li>Organisateur -
          <#if course.service?has_content>
            service EMS : ${course.service.getTitle(locale)}
          </#if>
          <#if course.activityOrganizer?has_content>
            autre service : ${course.activityOrganizer.getName(locale)}
            <ul>
              <li>Coordonées : ${course.activityOrganizer.getContactInformation(locale)}</li>
              <li>Image : <img src="${course.activityOrganizer.imageURL}"></li>
            </ul>
          </#if>
        </li>
        <li>
          Lieux et horaires :
          <ul>
            <li>Changer de période :
              <#assign assetVocabularyHelper = serviceLocator.findService("eu.strasbourg.utils.api.AssetVocabularyHelperService") />
              <ul>
                <#list assetVocabularyHelper.getVocabulary("periode des activites", themeDisplay.scopeGroupId).categories as period>
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
