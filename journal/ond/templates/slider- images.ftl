<div class="images">
  <h2 class="title"><@liferay_ui["message"] key="eu.last-image" /></h2>
  <div class="diapo">
    <ul class="slider_home">

      <#if images?has_content && images.getSiblings()?has_content>
        <#list images.getSiblings() as image>
          <@liferay_portlet.renderURL var="viewURL" portletName="eu_strasbourg_portlet_entity_detail_EntityDetailPortlet" windowState="normal">
            <#if image.getData()?has_content>
              <@liferay_portlet.param name="classPK" value="${image.getData()}" />
            </#if>
          </@liferay_portlet.renderURL>
          <#assign file = fileEntryHelper.getFileEntryByRelativeURL(image.getData()) />
          <#assign fileTitle = FileEntryHelper.getStructureFieldValue(file.getFileEntryId(), "title", locale)!file.getTitle() />
          <li>
            <a href="${viewURL}">
              <#if image.getAttribute('alt')?has_content>
                <img src="${image.getData()}" alt="© ${image.getAttribute('alt')} " />
              <#else>
                <img src="${image.getData()}" />
              </#if>
            </a>
            <div class="headband">
              <span>
                  <a href="${viewURL}">${fileTitle}</a>
              </span>
              <#if image.getAttribute('alt')?has_content>
                <span class="copyright">
                    Copyright : ${image.getAttribute('alt')}
                </span>
              </#if>
            </div>
          </li>
        </#list>
      </#if>

    </ul>
    <a href="/web/ond/medias/visuels" class="btn-more" title="<@liferay_ui["message"] key="eu.see-all-galleries" />"><@liferay_ui["message"] key="eu.see-all-galleries" /></a>
  </div>
  <div class="clearfix"></div>
</div>