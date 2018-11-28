<%@ include file="/entity-detail-init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true"
	var="configurationRenderURL" />

<aui:form action="${configurationActionURL}" method="post" name="fm">

    <aui:input name="cmd" type="hidden"
        value="update" />

    <aui:input name="redirect" type="hidden"
        value="${configurationRenderURL}" />

    <div class="portlet-configuration-body-content">
        <div class="container-fluid-1280">

            <aui:fieldset-group markupView="lexicon">

                <aui:fieldset>
                    <aui:select label="asset-type" name="className">
                        <c:forEach var="currentClassName" items="${classNames}" varStatus="cnStatus">
                            <aui:option value="${currentClassName}" selected="${currentClassName eq className}">${classNamesLabels[cnStatus.index]}</aui:option>
                        </c:forEach>
                    </aui:select>
                </aui:fieldset>

                <div id="asset-and-adt-selection">
                    <c:if test="${not empty className}">
                        <%@include file="/entity-detail-configuration-selectors.jsp" %>
                    </c:if>
                </div>

            </aui:fieldset-group>
        </div>
    </div>

    <aui:button-row>
        <aui:button type="submit"></aui:button>
    </aui:button-row>
</aui:form>
<liferay-portlet:actionURL portletConfiguration="true"
	var="classNameChangeURL" windowState="exclusive" />
<script>
	jQuery(function() {
		jQuery('#${renderResponse.namespace}className').on('change', function() {
			var className = jQuery('#${renderResponse.namespace}className')
					.val();
			jQuery.ajax({
				type : "POST",
				url : '${classNameChangeURL}'
						+ '&${renderResponse.namespace}cmd=classname-change'
						+ '&${renderResponse.namespace}className='
						+ className,
				dataType : "html",
				success : function(result) {
					jQuery('#asset-and-adt-selection').html(result);
				}
			});
		});
	});
</script>