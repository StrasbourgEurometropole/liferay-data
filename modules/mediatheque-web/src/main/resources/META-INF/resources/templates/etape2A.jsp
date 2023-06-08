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
		<!-- Etape 2 carte déjà associée à un compte -->
		<p align="center">
			<liferay-ui:message key="associate-text" /><br>
			<a href="${dc.contactURL}" target="_blank" title="<liferay-ui:message key="contact-mediatheque" /> (<liferay-ui:message key="eu.new-window" />)">
				<liferay-ui:message key="contact-mediatheque" />
			</a>
		</p>
    </div>
</section>