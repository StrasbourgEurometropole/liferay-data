<%@ include file="/mediatheque-init.jsp" %>

<section id="mediatheque">
    <%-- Récupère le fait de plier ou déplier ce widget dans la config de la personnalisation --%>
    <c:set value="${dc.isFolded()}" var="isFolded" />
    <div class="buttons">
        <%-- Vérifie si ce widget peut être plié dans la config de la personnalisation --%>
        <c:if test="${dc.showRetractableButton()}">
            <button class="${isFolded?'retractable-folded-wi':'retractable-unfolded-wi'}" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
        <%-- Vérifie si ce widget peut être masqué dans la config de la personnalisation --%>
        <c:if test="${dc.showDeleteButton()}">
            <button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
    </div>

    <h2>${title}</h2>
    <div class="detail" ${dc.isFolded()?'style="display: none;"':''}>
		<!-- Etape 2 attente d'activation -->
		<p align="center">
			<liferay-ui:message key="activate-text-x" arguments="${dc.transformEmail}" />
		</p>
	
		<liferay-portlet:renderURL varImpl="linkURL" />
		<aui:form name="fm" action="${linkURL}#mediatheque" class="generic-form toValidate">
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
    </div>
</section>