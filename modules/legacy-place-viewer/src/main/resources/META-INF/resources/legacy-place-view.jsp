<%@ include file="/legacy-place-init.jsp"%>

<c:if test="${not empty place}">
	<div class="place-detail">
		<div class="place-header">
			<h1 class="place-title">${place.alias}</h1>
			<div class="place-address">${place.street} - ${place.zipCode}
				${place.city}</div>
			<c:if test="${not empty place.phone}">
				<div class="place-phone">
					<liferay-ui:message key="phone" />
					: ${place.phone}
				</div>
			</c:if>
			<c:if test="${not empty place.email or not empty place.facebookURL}">
				<div class="place-links">
					<c:if test="${not empty place.email}">
						<a href="#contact-form-section"><liferay-ui:message key="contact-the-museum" /></a>
					</c:if>
					<c:if test="${not empty place.facebookURL}">
						<a href="${place.facebookURL}"
							title="${place.facebookName} (<liferay-ui:message key="eu.new-window" />)"
							target="_blank"> ${place.facebookName} </a>	
					</c:if>
				</div>
			</c:if>
		</div>
		<div class="place-info">
			<div class="place-60">
				<%--
					<c:if test="${not empty place.presentation}">
						<div class="place-info-section">
							<h4>
								<liferay-ui:message key="eu.presentation" />
							</h4>
							${place.presentation}
						</div>
					</c:if>
				 --%>
				<c:if test="${not empty place.servicesAndActivities}">
					<div class="place-info-section">
						<h4>
							<liferay-ui:message key="eu.services-and-activities" />
						</h4>
						${place.servicesAndActivities}
					</div>
				</c:if>
				<c:if test="${not empty place.features}">
					<div class="place-info-section">
						<h4>
							<liferay-ui:message key="eu.features" />
						</h4>
						${place.features}
					</div>
				</c:if>
				<c:if test="${not empty place.moreInformation}">
					<div class="place-info-section">
						<h4>
							<liferay-ui:message key="eu.more-information" />
						</h4>
						${place.moreInformation}
					</div>
				</c:if>
				<c:if test="${not empty place.access}">
					<div class="place-info-section">
						<h4>
							<liferay-ui:message key="access" />
						</h4>
						${place.access}
					</div>
				</c:if>
				<c:if
					test="${not empty place.accessForDisabled or place.hasAnyAccessForDisabled()}">
					<div class="place-info-section">
						<h4>
							<liferay-ui:message key="eu.access-for-disabled" />
						</h4>
						<c:if test="${place.hasAnyAccessForDisabled()}">
							<div class="access-for-disabled-icons">
								<c:if test="${place.accessForWheelchair}">
									<img src="/o/agendaweb/images/access-for-wheelchair.png"
										title="<liferay-ui:message key="eu.access-for-wheelchair" />"
										alt="<liferay-ui:message key="access-for-wheelchair" />">
								</c:if>
								<c:if test="${place.accessForBlind}">
									<img src="/o/agendaweb/images/access-for-blind.png"
										title="<liferay-ui:message key="eu.access-for-blind" />"
										alt="<liferay-ui:message key="eu.access-for-blind" />">
								</c:if>
								<c:if test="${place.accessForDeaf}">
									<img src="/o/agendaweb/images/access-for-deaf.png"
										title="<liferay-ui:message key="eu.access-for-deaf" />"
										alt="<liferay-ui:message key="access-for-deaf" />">
								</c:if>
								<c:if test="${place.accessForElder}">
									<img src="/o/agendaweb/images/access-for-elder.png"
										title="<liferay-ui:message key="eu.access-for-elder" />"
										alt="<liferay-ui:message key="access-for-elder" />">
								</c:if>
								<c:if test="${place.accessForDeficient}">
									<img src="/o/agendaweb/images/access-for-deficient.png"
										title="<liferay-ui:message key="eu.access-for-deficient" />"
										alt="<liferay-ui:message key="access-for-deficient" />">
								</c:if>
							</div>
						</c:if>
						${place.accessForDisabled}
					</div>
				</c:if>
				<c:if test="${not empty place.email}">
					<div class="place-info-section" id="contact-form-section">
						<liferay-portlet:actionURL var="contactURL" name="contact">
							<liferay-portlet:param name="to" value="${place.email}" />
							<liferay-portlet:param name="subject"
								value="Formulaire de contact - Lieux - ${place.alias}" />
						</liferay-portlet:actionURL>
						<h4>
							<liferay-ui:message key="contact" />
						</h4>
						<liferay-ui:success key="mail-success" message="eu.mail-success" />
						<liferay-ui:error key="all-fields-required"
							message="eu.all-fields-required" />
						<liferay-ui:error key="invalid-mail" message="eu.invalid-mail" />
						<liferay-ui:error key="recaptcha-error"
							message="eu.recaptcha-error" />
						<p class="error-message all-fields-required-message"
							style="display: none;">
							<liferay-ui:message key="eu.all-fields-required" />
						</p>
						<p class="error-message invalid-mail-message"
							style="display: none;">
							<liferay-ui:message key="eu.invalid-mail" />
						</p>

						<c:if test="${mailSent}">
							<p class="mail-success">
								<liferay-ui:message key="eu.mail-success" />
							</p>
						</c:if>
						<form action="${contactURL}" name="contactForm" method="post"
							class="contact-form">
							<div>
								<aui:input type="text" name="firstName"
									label="first-name-required" cssClass="first-name"
									value="${firstName}" />
							</div>
							<div>
								<aui:input type="text" name="lastName"
									label="last-name-required" cssClass="last-name"
									value="${lastName}" />
							</div>
							<div>
								<aui:input type="text" name="email" label="email-required"
									cssClass="email" value="${email}" />
							</div>
							<div>
								<aui:input type="textarea" name="message"
									label="message-required" cssClass="message" value="${message}" />
							</div>
							<div class="g-recaptcha"
								data-sitekey="${recaptchaSecret}"></div>
							<div>
								<aui:input type="checkbox" label="eu.do-you-want-a-notification"
									name="notificationEmail" value="true" />
							</div>
							<p>
								<input type="submit" value="<liferay-ui:message key="send" />">
							</p>
							<p>
								* :
								<liferay-ui:message key="eu.required-field" />
							</p>
							<p class="privacy-policy">
								<label><liferay-ui:message key="eu.privacy-policy" /></label>
								<liferay-portlet:runtime
									portletName="com_liferay_journal_content_web_portlet_JournalContentPortlet"
									settingsScope="group" instanceId="entityDetail" />
							</p>
						</form>
					</div>
				</c:if>
			</div>
			<div class="place-40">
				<c:if test="${not empty place.nextDaysSchedule}">
					<div class="place-info-section">
						<h4>
							<liferay-ui:message key="eu.times" />
						</h4>
						<div class="place-schedule">
							<ul>
								<c:forEach var="scheduleEntry" items="${place.nextDaysSchedule}">
									<li class="schedule">
										<div class="schedule-day">${scheduleEntry.key}</div>
										<div class="schedule-time">${scheduleEntry.value}</div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${not empty place.exceptionalSchedule}">
							<strong><liferay-ui:message key="eu.exceptional-schedule" /></strong>
							${place.exceptionalSchedule}
						</c:if>
						<c:if test="${not empty place.exceptionalOpenings}">
							<strong><liferay-ui:message key="eu.exceptional-openings" /></strong>
							<ul>
								<c:forEach var="opening" items="${place.exceptionalOpenings}">
									<li>${opening.key} ${opening.value}</li>
								</c:forEach>
							</ul>
						</c:if>
						<c:if test="${not empty place.exceptionalClosings}">
							<strong><liferay-ui:message key="eu.exceptional-closings" /></strong>
							<ul>
								<c:forEach var="closing" items="${place.exceptionalClosings}">
									<li>${closing.key} ${closing.value}</li>
								</c:forEach>
							</ul>
						</c:if>
					</div>
				</c:if>
				<c:if
					test="${not empty place.website or not empty place.facebookURL}">
					<div class="place-info-section place-website">
						<h4>
							<liferay-ui:message key="eu.website" />
						</h4>
						<ul>
							<c:if test="${not empty place.website}">
								<li><a href="${place.website}"
									title="${place.websiteName} (<liferay-ui:message key="eu.new-window" />)"
									target="_blank"> ${place.websiteName} </a></li>
							</c:if>
							<c:if test="${not empty place.facebookURL}">
								<li><a href="${place.facebookURL}"
									title="${place.facebookName} (<liferay-ui:message key="eu.new-window" />)"
									target="_blank"> ${place.facebookName} </a></li>
							</c:if>
						</ul>
					</div>
				</c:if>
			</div>
		</div>

	</div>
</c:if>
<c:if test="${empty place}">
	Veuillez s&eacute;lectionner un lieu dans la configuration du portlet. Merci.
</c:if>