<%@ include file="/init.jsp"%>
<c:set var="virtualParticiperHostName" value="${dc.getVirtualParticiperHostName()}"/>
<c:if test="${empty virtualParticiperHostName}">
	<c:set var="homeParticiperURL" value="/web/participer/"/>
</c:if>
<c:if test="${not empty virtualParticiperHostName}">
	<c:set var="homeParticiperURL" value="https://${virtualParticiperHostName}/"/>
</c:if>

<portlet:actionURL var="saveProfilURL" name="saveProfil">
	<portlet:param name="redirectURL" value="${redirectURL}" />
	<portlet:param name="cmd" value="saveProfil" />
</portlet:actionURL>

<section id="placeit" class="home-section">
    <%-- Récupère le fait de plier ou déplier ce widget dans la config de la personnalisation --%>
    <c:set value="${isFolded}" var="isFolded" />
    <div class="buttons">
        <%-- Vérifie si ce widget peut être plié dans la config de la personnalisation --%>
        <c:if test="${showRetractableButton}">
            <button class="${isFolded?'retractable-folded-wi':'retractable-unfolded-wi'}" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
        <%-- Vérifie si ce widget peut être masqué dans la config de la personnalisation --%>
        <c:if test="${showDeleteButton}">
            <button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
        </c:if>
    </div>

    <h2><liferay-ui:message key="dashboard.widget.title" /></h2>

    <div class="detail" ${isFolded?'style="display: none;"':''}>

        <c:if test="${!hasUserSigned}">
            <div class="rte">
                <p><liferay-ui:message key="dashboard.widget.content" /></p>
            </div>

            <div class="form-group">
                <div class="content" align="center">
                    <a href="${homeParticiperURL}" target="_blank" class="btn-square--bordered--core" title="Consultez la plateforme (<liferay-ui:message key="eu.new-window" />)">
                        <span class="flexbox">
                            <span class="btn-text"><liferay-ui:message key="dashboard.widget.goto" /></span>
                            <span class="btn-arrow"></span>
                        </span>
                    </a>
                </div>
            </div>
        </c:if>

        <c:if test="${hasUserSigned}">

            <div class="pro-bloc-dashboard">

                <div class="projects-events">
                    <div class="projects">
                        <h3><liferay-ui:message key="dashboard.widget.project" /></h3>
                        <span class="pro-number">${projectFollowedsCount}</span>
                        <div class="form-group">
                            <div class="content" align="center">
                                <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-projet" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.project.goto" />(<liferay-ui:message key="eu.new-window" />)" >
                                    <span class="flexbox">
                                        <span class="btn-text">
                                            <liferay-ui:message key="dashboard.widget.project.goto" />
                                        </span>
                                        <span class="btn-arrow"></span>
                                    </span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="events">
                        <h3><liferay-ui:message key="dashboard.widget.event" /></h3>
                        <span class="pro-number">${eventCount}</span>
                        <div class="form-group">
                            <div class="content" align="center">
                                <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-event" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.event.goto" />(<liferay-ui:message key="eu.new-window" />)" >
                                    <span class="flexbox">
                                        <span class="btn-text">
                                            <liferay-ui:message key="dashboard.widget.event.goto" />
                                        </span>
                                        <span class="btn-arrow"></span>
                                    </span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="petitions">
                    <h3><liferay-ui:message key="dashboard.widget.petition" /></h3>
                    <span class="pro-number">${petitionSignedCount}</span>
                    <div class="form-group">
                        <div class="content" align="center">
                            <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-petition-signe" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.petition.signed" />(<liferay-ui:message key="eu.new-window" />)" >
                                <span class="flexbox">
                                    <span class="btn-text">
                                        <liferay-ui:message key="dashboard.widget.petition.signed" />
                                    </span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                    <span class="pro-number">${petitionsFiledCount}</span>
                    <div class="form-group">
                        <div class="content" align="center">
                            <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-petition-depose" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.petition.filed" />(<liferay-ui:message key="eu.new-window" />)" >
                                <span class="flexbox">
                                    <span class="btn-text">
                                        <liferay-ui:message key="dashboard.widget.petition.filed" />
                                    </span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="initiatives">
                    <h3><liferay-ui:message key="dashboard.widget.initiative" /></h3>
                    <span class="pro-number">${initiativeFiledsCount}</span>
                    <div class="form-group">
                        <div class="content" align="center">
                            <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-initiative-signe" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.initiative.signed" />(<liferay-ui:message key="eu.new-window" />)" >
                                <span class="flexbox">
                                    <span class="btn-text">
                                        <liferay-ui:message key="dashboard.widget.initiative.signed" />
                                    </span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                    <span class="pro-number">${initiativeAidesCount}</span>
                    <div class="form-group">
                        <div class="content" align="center">
                            <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-initiative-aide" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.initiative.aidees" />(<liferay-ui:message key="eu.new-window" />)" >
                                <span class="flexbox">
                                    <span class="btn-text">
                                        <liferay-ui:message key="dashboard.widget.initiative.aidees" />
                                    </span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                </div>

                <div class="budget">
                    <h3><liferay-ui:message key="dashboard.widget.budget" /></h3>
                    <span class="pro-number">${budgetFiled.size()}</span>
                    <div class="form-group">
                        <div class="content" align="center">
                            <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-projet-soumis" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.budget.filed" />(<liferay-ui:message key="eu.new-window" />)" >
                                <span class="flexbox">
                                    <span class="btn-text">
                                        <liferay-ui:message key="dashboard.widget.budget.filed" />
                                    </span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                    <span class="pro-number">${budgetVoted.size()}</span>
                    <div class="form-group">
                        <div class="content" align="center">
                            <a href="${homeParticiperURL}mon-activite-citoyenne#pro-link-listing-projet-vote" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="dashboard.widget.budget.voted" />(<liferay-ui:message key="eu.new-window" />)" >
                                <span class="flexbox">
                                    <span class="btn-text">
                                        <liferay-ui:message key="dashboard.widget.budget.voted" />
                                    </span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                    <div class="budget-reste">
                        <span><liferay-ui:message key="dashboard.widget.budget.reliquat-x" arguments="${voteLeft}" /></span>
                    </div>
                </div>


                <div class="acces-site">
                    <div class="form-group">
                        <div class="content" align="center">
                            <a href="${homeParticiperURL}" target="_blank" class="btn-square--bordered--core" title="Consultez la plateforme (<liferay-ui:message key="eu.new-window" />)">
                                <span class="flexbox">
                                    <span class="btn-text"><liferay-ui:message key="dashboard.widget.goto" /></span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</section>