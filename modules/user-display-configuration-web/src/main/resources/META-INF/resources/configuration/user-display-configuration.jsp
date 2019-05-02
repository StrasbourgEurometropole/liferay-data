<%@ include file="/user-display-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<div class="container-fluid-1280 main-content-body">
	<aui:form action="${configurationActionURL}" method="post" name="fm">
		<aui:input name="cmd" type="hidden"
			value="update" />
	
		<aui:input name="redirect" type="hidden"
			value="${configurationRenderURL}" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset id="homepage-portlets" label="homepage-portlets" collapsible="true" collapsed="false">
				<c:forEach items="${dc.portletIds}" var="portletId" varStatus="loopStatus">
                    <div class="portlet-display-controls">
                        <div class="portlet-display-mode" data-portlet-id="${portletId}">
                            <label>${dc.portletTitles.get(loopStatus.index)}<span style="font-weight: normal;"> - ${portletId}</span></label>
                            <c:set var="displayStatus" value="${dc.getPortletDisplayStatus(portletId)}" />
                            <aui:input type="radio"
                               name="display_${portletId}"
                               value="on_hidden"
                               label="on-hidden"
                               checked="${displayStatus eq 'on_hidden'}"  />
                            <aui:input type="radio"
                               name="display_${portletId}"
                               value="on_disabled"
                               label="on-disabled"
                               checked="${displayStatus eq 'on_disabled'}"  />
                            <aui:input type="radio"
                               name="display_${portletId}"
                               value="on"
                               label="on"
                               checked="${displayStatus eq 'on'}"  />
                            <aui:input type="radio"
                               name="display_${portletId}"
                               value="off"
                               label="off"
                               checked="${displayStatus eq 'off'}"  />
                            <c:set var="retractableStatus" value="${dc.getPortletRetractableStatus(portletId)}" />
                            <aui:input type="radio"
                               name="retractable_${portletId}"
                               value="no-retractable"
                               label="no-retractable"
                               checked="${retractableStatus eq 'no-retractable'}"  />
                            <aui:input type="radio"
                               name="retractable_${portletId}"
                               value="retractable-unfolded"
                               label="retractable-unfolded"
                               checked="${retractableStatus eq 'retractable-unfolded'}"  />
                            <aui:input type="radio"
                               name="retractable_${portletId}"
                               value="retractable-folded"
                               label="retractable-folded"
                               checked="${retractableStatus eq 'retractable-folded'}"  />
                        </div>
                        <div class="portlet-metadata-inputs" data-portlet-id="${portletId}"
                             <c:if test="${displayStatus eq 'on_hidden'}">style="display:none;"</c:if>
                         >
                            <aui:input type="text" name="${portletId}Title" value="${dc.getPortletDisplayTitle(portletId)}" label="title" />
                            <aui:input type="text" name="${portletId}Description" value="${dc.getPortletDisplayDescription(portletId)}" label="description" />
                        </div>
                    </div>
				</c:forEach>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button type="submit"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<script>
	$('input[id=radio]input[name^=display_]').on('change', function() {
        var portletId = $(this).closest('.portlet-display-mode').data('portletId');
        var divToToggle =  $('.portlet-metadata-inputs[data-portlet-id=' + portletId + ']');
		if ($(this).val() === 'on_hidden') {
            $(divToToggle).hide();
		}  else {
            $(divToToggle).show();
		}
	});
</script>
<style>
    .portlet-display-controls {
        margin: 20px 0;
        border: 1px solid #d4d4d4;
        padding: 10px 20px;
        border-radius: 3px;
    }
</style>