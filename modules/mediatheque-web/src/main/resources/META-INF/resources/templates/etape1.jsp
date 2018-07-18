<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
    <h2>${title}</h2>
	<!-- Etape 1 -->
	<c:if test="${not empty dissociation and dissociation == 'ok'}">
		<!-- Message d'information -->
		<div class="information"><liferay-ui:message key="delete-association-text" /></div>
	</c:if>
	<p>
		${dc.demarcheText}
	</p>
	<liferay-portlet:renderURL varImpl="linkURL" />
	<aui:form name="fm" action="${linkURL}" class="generic-form toValidate">

        <div class="webform-layout-box">
			<!-- Numéro de carte -->
            <div class="form-group">
				<div class="title">
					<label for="number"><liferay-ui:message key="number" /><strong style="color:red">*</strong></label>
				</div>
				<div class="content">
					<input type="text" id="number" name="<portlet:namespace />number" 
						value="${number}">
				</div>
            </div>

			<!-- Lier au compte -->
			<input type="hidden" id="link" name="<portlet:namespace />link" value="lier">
            <div class="form-group">
				<div class="content" align="center">
       				<button type="submit" class="btn-square--bordered--core">
       					<span class="flexbox">
       						<span class="btn-text"><liferay-ui:message key="link" /></span>
       						<span class="btn-arrow"></span>
       					</span>
       				</button>
				</div>
            </div>
		</div>
	</aui:form>

	<c:if test="${not empty error}">
		<!-- Message d'erreur -->
		<div class="error">${error}</div>
	</c:if>
</section>