<%@ include file="/family-space-init.jsp" %>

<section id="family-space">
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
        <!-- contenu web -->
        <liferay-portlet:runtime
            portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
            instanceId="family-space" />
        <!-- Etape 1 -->
        <c:forEach items="${dc.familyspace.families}" var="family" varStatus="count">
            <div name="${family.idFamily}" class="family">
                <c:if test="${count.index gt 1}" >
                    <div class="btn-more"><liferay-ui:message key="family-x" arguments="${family.idFamily}" /></div>
                    <div class="btn-minus hide"><liferay-ui:message key="family-x" arguments="${family.idFamily}" /></div>
                </c:if>
                <c:if test="${!(count.index gt 1)}" >
                    <div class="btn-more hide"><liferay-ui:message key="family-x" arguments="${family.idFamily}" /></div>
                    <div class="btn-minus"><liferay-ui:message key="family-x" arguments="${family.idFamily}" /></div>
                </c:if>
            </div>
            <div id="family${family.idFamily}" <c:if test="${count.index gt 1}" > class="hide" </c:if> style="margin-bottom: 40px;">
                <c:if test="${fn:length(family.persons) == 0}">
                    <!-- Accès espace famille -->
                    <div class="form-group">
                        <div class="content" style="text-align: center;">
                            <p style="margin-bottom: 30px";>
                                <liferay-ui:message key="no-child" />
                            </p>
                            <a href="${dc.familySpaceURL}" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="manage-family-space" /> (<liferay-ui:message key="eu.new-window" />)">
                                <span class="flexbox">
                                    <span class="btn-text"><liferay-ui:message key="manage-family-space" /></span>
                                    <span class="btn-arrow"></span>
                                </span>
                            </a>
                        </div>
                    </div>
                </c:if>
                <c:forEach items="${family.persons}" var="person" varStatus="count">
                    <div class="webform-layout-box">
                        <!-- Enfant -->
                        <div class="form-group">
                            <!-- Nom -->
                            <div class="title">
                                <label><liferay-ui:message key="child" /></label>
                            </div>
                            <div class="content">
                                <p>${person.firstName} ${person.lastName}</p>
                            </div>
                        </div>

                        <!-- Repas -->
                        <c:if test="${person.hasLunchBooked}">
                            <fmt:parseDate value="${person.firstBookingDate}" pattern="yyyy-MM-dd" var="firstBookingDate" type="both" />
                            <fmt:formatDate value="${firstBookingDate}" type="date" var="newFirstBookingDate" pattern="dd/MM/yyyy" />
                            <fmt:parseDate value="${person.lastBookingDate}" pattern="yyyy-MM-dd" var="lastBookingDate" type="both" />
                            <fmt:formatDate value="${lastBookingDate}" type="date" var="newLastBookingDate" pattern="dd/MM/yyyy" />
                            <c:set value="${newFirstBookingDate}-${newLastBookingDate}" var="dates"/>
                            <div class="form-group">
                                <div class="title">
                                    <label><liferay-ui:message key="lunch" /></label>
                                </div>
                                <div class="content">
                                    <p><liferay-ui:message key="lunch-x-y" arguments="${fn:split( dates ,'-')}" /></p>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${person.hasLunchBooked}">
                        <!-- Une alerte est définie pour avertir que le dernier jour réservé est proche. Le message devra être affiché 12 jours avant le dernier jour réservé -->
                        <c:set var="dateAlert" value="${dc.today.plusDays(13)}" />
                        <c:if test="${dateAlert.isAfter(person.lastBookingDate)}">
                            <div class="warning">
                                <strong><liferay-ui:message key="warning" /></strong><br>
                                <liferay-ui:message key="expire-validity-text-x" arguments="${newLastBookingDate}" />
                            </div>
                        </c:if>
                    </c:if>
                </c:forEach>

            </div>
        </c:forEach>
        <div class="webform-layout-box">
            <!-- Lier un compte -->
            <div class="form-group">
                <div class="content">
                    <a href="${dc.linkAccountURL}" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="link-another-account" /> (<liferay-ui:message key="eu.new-window" />)">
                        <span class="flexbox">
                            <span class="btn-text"><liferay-ui:message key="link-another-account" /></span>
                            <span class="btn-arrow"></span>
                        </span>
                    </a>
                </div>
            </div>

            <!-- Acces espace famille -->
            <div class="form-group">
                <div class="content" style="text-align: right;">
                    <a href="${dc.familySpaceURL}" class="btn-square--bordered--core" target="_blank" title="<liferay-ui:message key="access-family-space" /> (<liferay-ui:message key="eu.new-window" />)">
                        <span class="flexbox">
                            <span class="btn-text"><liferay-ui:message key="access-family-space" /></span>
                            <span class="btn-arrow"></span>
                        </span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>