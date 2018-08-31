<%@ include file="/family-space-init.jsp" %>

<section id="family-space">
	<c:if test="${dc.showDeleteButton()}">
		<button class="delete-wi" data-portlet-id="${themeDisplay.portletDisplay.id}"></button>
	</c:if>
    <h2>${title}</h2>
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
                    <fmt:parseDate value="${person.firstBookingDate}" pattern="yyyy-MM-dd" var="firstBookingDate" type="both" />
                    <fmt:formatDate value="${firstBookingDate}" type="date" var="newFirstBookingDate" pattern="dd/MM/yyyy" />
                    <fmt:parseDate value="${person.lastBookingDate}" pattern="yyyy-MM-dd" var="lastBookingDate" type="both" />
                    <fmt:formatDate value="${lastBookingDate}" type="date" var="newLastBookingDate" pattern="dd/MM/yyyy" />
                    <c:set var="dateAlert" value="${dc.today.plusMonths(7)}" />
                    <c:if test="${dateAlert.isAfter(person.lastBookingDate)}">
                        <div class="warning">
                            <strong><liferay-ui:message key="warning" /></strong><br>
                            <liferay-ui:message key="expire-validity-text-x" arguments="${newLastBookingDate}" />
                        </div>
                    </c:if>
                </c:if>
            </c:forEach>

			<!-- Réserver des repas -->
            <aui:form name="fm" action="${dc.addLunchURL}" class="generic-form toValidate">
                <aui:input type="hidden" name="idFamily" value="${family.idFamily}" />
                <div class="form-group">
                    <div class="content" align="center">
                        <button type="submit" class="btn-square--filled--core">
                            <span class="flexbox">
                                <span class="btn-text"><liferay-ui:message key="book-more-lunches" /></span>
                                <span class="btn-arrow"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </aui:form>
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
</section>