<%@ include file="/resid-init.jsp" %>

<section id="resid">
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
        <!-- Etape 0 : webService indispo -->
        <div class="error"><liferay-ui:message key="eu.webservice-indispo" /></div>

        <div class="form-group">
            <div class="content" align="center">
                <a href="${dc.residURL}" target="_blank" class="btn-square--bordered--core" title="<liferay-ui:message key="resid-site"/>(<liferay-ui:message key="eu.new-window"/>)">
                    <span class="flexbox">
                        <span class="btn-text">
                            <liferay-ui:message key="resid-site"/>
                        </span>
                        <span class="btn-arrow"></span>
                    </span>
                </a>
            </div>
        </div>
    </div>
</section>


