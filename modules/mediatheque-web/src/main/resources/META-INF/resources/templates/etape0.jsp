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
        <!-- Etape 0 - erreur technique -->
        <div class="error">
            <c:if test="${not empty error}">
                ${error}
            </c:if>

            <c:if test="${empty error}">
                ${dc.errorText}
            </c:if>
        </div>

        <div align="center">
            <a href="${dc.mediathequeURL}" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="site-mediatheques" /> (<liferay-ui:message key="eu.new-window" />)">
                <span class="flexbox">
                    <span class="btn-text"><liferay-ui:message key="site-mediatheques" /></span>
                    <span class="btn-arrow"></span>
                </span>
            </a>
        </div>
    </div>
</section>