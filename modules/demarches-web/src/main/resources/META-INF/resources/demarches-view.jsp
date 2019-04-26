<%@ include file="/demarches-init.jsp" %>
 <div class="wi-wrapper">
    <section id="demandes">
		<c:if test="${showDeleteButton}">
			<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
		</c:if>
        <h2>${title}</h2>
	   	<c:if test="${not empty error}">
			<div class="error"><liferay-ui:message key="eu.webservice-indispo" /></div>
	   	</c:if>
	   	<c:if test="${empty error}">
	   	    <div class="bloc-demarches">
                <div class="demarches">
                    <div class="upper-title">
                        <label><liferay-ui:message key="steps" /></label>
                    </div>
                    <c:if test="${not empty demarches}">
                        <ul class="demandes-list unstyled">
                            <c:forEach var="demarche" items="${demarches}">
                                <li class="demandes-item">
                                    <a href="${demarche.url}" class="btn-square--bordered--core">
                                        <span class="flexbox"><span class="btn-text">${demarche.name} - ${demarche.status}</span><span class="btn-arrow"></span></span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${empty demarches}">
                        <span class="text-noelt"><liferay-ui:message key="no-step" /></span><br>
                    </c:if>

                    <a href="<c:if test='${not empty toutesLesDemarches}'>${toutesLesDemarches}</c:if><c:if test='${empty toutesLesDemarches}'>#</c:if>" class="btn-square--filled--core">
                        <span class="flexbox"><span class="btn-text"><liferay-ui:message key="all-steps" /></span><span class="btn-arrow"></span></span>
                    </a>
                </div>

                <div class="suivi">
                    <div class="upper-title">
                        <label><liferay-ui:message key="tracking-code" /></label>
                    </div>

                    <form id="formSuivi">
                        <input id="codeSuivi" type="text" placeholder="EX: CNPHNTFB " required aria-required="true"><br>
                        <button data-url-suivi="${urlSuivi}" type="submit" class="btn-square--filled--core">
                            <span class="flexbox">
                                <span class="btn-text"><liferay-ui:message key="submit" /></span>
                                <span class="btn-arrow"></span>
                            </span>
                        </button>
                    </form>
                </div>
            </div>

	   	</c:if>

    </section>
</div>
