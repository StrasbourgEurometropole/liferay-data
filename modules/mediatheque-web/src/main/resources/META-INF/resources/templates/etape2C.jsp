<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
	<c:if test="${dc.showDeleteButton()}">
		<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
	</c:if>
    <h2><liferay-ui:message key="account-mediatheque" /></h2>
		<!-- Etape 2 attente d'activation -->
		<p align="center">
			<liferay-ui:message key="activate-text-x" arguments="${dc.transformEmail}" />
		</p>
	
		<liferay-portlet:renderURL varImpl="linkURL" />
		<aui:form name="fm" action="${linkURL}" class="generic-form toValidate">
			<!-- Renvoyer le mail -->
			<input type="hidden" id="link" name="<portlet:namespace />link" value="lier" />
			<input type="hidden" id="number" name="<portlet:namespace />number" value="${dc.cardNumber}" />
            <div class="form-group">
				<div class="content" align="center">
       				<button type="submit" class="btn-square--bordered--core">
       					<span class="flexbox">
       						<span class="btn-text"><liferay-ui:message key="send-back" /></span>
       						<span class="btn-arrow"></span>
       					</span>
       				</button>
				</div>
            </div>
		</aui:form>
</section>