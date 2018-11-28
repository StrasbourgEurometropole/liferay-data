<%@ include file="/rubric-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="<%=true%>"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%=true%>"
	var="configurationRenderURL" />

<aui:form action="<%=configurationActionURL%>" method="post" name="fm">

    <aui:input name="cmd" type="hidden"
        value="update" />

    <aui:input name="redirect" type="hidden"
        value="<%=configurationRenderURL%>" />

    <div class="portlet-configuration-body-content">
        <div class="container-fluid-1280">
            <aui:fieldset-group markupView="lexicon">

                <aui:fieldset>
                    <div class="display-template">
                        <liferay-ddm:template-selector
                            className="<%= Layout.class.getName() %>"
                            displayStyle="${displayStyle}"
                            displayStyleGroupId="${displayStyleGroupId}"
                            refreshURL="${refreshURL}"
                            showEmptyOption="<%= true %>"
                        />
                    </div>
                </aui:fieldset>

            </aui:fieldset-group>
        </div>
    </div>

    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>