/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.agenda.model.impl;

import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.*;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.ImportReportLine;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.ImportReportLocalServiceUtil;
import eu.strasbourg.service.agenda.utils.ImportReportLineStatus;
import eu.strasbourg.service.agenda.utils.ImportReportStatus;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;


/**
 * The extended model implementation for the ImportReport service. Represents a
 * row in the &quot;agenda_ImportReport&quot; database table, with each column
 * mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.agenda.model.ImportReport} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class ImportReportImpl extends ImportReportBaseImpl {
	private static final long serialVersionUID = -910651822078087920L;

	private List<ImportReportLine> _lines;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a import
	 * report model instance should use the {@link
	 * eu.strasbourg.service.agenda.model.ImportReport} interface instead.
	 */
	public ImportReportImpl() {
		this.setStatus(ImportReportStatus.SUCCESS);
		this.setStartDate(new Date());
	}

	@Override
	public void globalError(String cause) {
		this.setStatus(ImportReportStatus.FAILURE);
		this.setGlobalErrorCause(cause);
		_log.error(cause);
		this.sendMail();
	}

	@Override
	public void incrementNewEvents() {
		this.setNewEventsCount(this.getNewEventsCount() + 1);
	}

	@Override
	public void incrementNewManifestations() {
		this.setNewManifestationsCount(this.getNewManifestationsCount() + 1);
	}

	@Override
	public void incrementModifiedEvents() {
		this.setModifiedEventsCount(this.getModifiedEventsCount() + 1);
	}

	@Override
	public void incrementModifiedManifestations() {
		this.setModifiedManifestationsCount(
			this.getModifiedManifestationsCount() + 1);
	}

	@Override
	public void incrementErrorEvents() {
		this.setErrorEventsCount(this.getErrorEventsCount() + 1);
	}

	@Override
	public void incrementErrorManifestations() {
		this.setErrorManifestationsCount(
			this.getErrorManifestationsCount() + 1);
	}

	@Override
	public List<ImportReportLine> getLines() {
		if (this._lines == null) {
			this._lines = ImportReportLocalServiceUtil.getReportLines(this);
		}
		return this._lines;
	}

	@Override
	public List<ImportReportLine> getNewManifestationsLines() {
		return this.getLines().stream()
			.filter(l -> l.getType().equals(Manifestation.class.getName())
				&& l.getStatus() == ImportReportLineStatus.SUCCESS_ADD)
			.collect(Collectors.toList());
	}

	@Override
	public List<ImportReportLine> getModifiedManifestationsLines() {
		return this.getLines().stream()
			.filter(l -> l.getType().equals(Manifestation.class.getName())
				&& l.getStatus() == ImportReportLineStatus.SUCCESS_MODIFIED)
			.collect(Collectors.toList());
	}

	@Override
	public List<ImportReportLine> getErrorManifestationsLines() {
		return this.getLines().stream()
			.filter(l -> l.getType().equals(Manifestation.class.getName())
				&& l.getStatus() == ImportReportLineStatus.FAILURE)
			.collect(Collectors.toList());
	}

	@Override
	public List<ImportReportLine> getNewEventsLines() {
		return this.getLines().stream()
			.filter(l -> l.getType().equals(Event.class.getName())
				&& l.getStatus() == ImportReportLineStatus.SUCCESS_ADD)
			.collect(Collectors.toList());
	}

	@Override
	public List<ImportReportLine> getModifiedEventsLines() {
		return this.getLines().stream()
			.filter(l -> l.getType().equals(Event.class.getName())
				&& l.getStatus() == ImportReportLineStatus.SUCCESS_MODIFIED)
			.collect(Collectors.toList());
	}

	@Override
	public List<ImportReportLine> getErrorEventsLines() {
		return this.getLines().stream()
			.filter(l -> l.getType().equals(Event.class.getName())
				&& l.getStatus() == ImportReportLineStatus.FAILURE)
			.collect(Collectors.toList());
	}

	@Override
	public void sendMail() {
		String environment = StrasbourgPropsUtil.getEnvironment();
		String mailAddresses = StrasbourgPropsUtil.getAgendaImportMails();
		String providerSpecificMailAddresses = StrasbourgPropsUtil
			.getAgendaImportMailsForProvider(this.getProvider());

		try {

			StringWriter out = new StringWriter();

			// Initialisation des variables
			Map<String, Object> context = new HashMap<>();
			context.put("report", this);
			context.put("environment", environment);

			// Chargement du template contenant le sujet du mail
			TemplateResource templateResourceSubject = new URLTemplateResource(
					"0",
					Objects.requireNonNull(this.getClass().getClassLoader()
							.getResource("/templates/import-notification-mail-subject.ftl\"")));
			Template subjectTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceSubject, false);

			// Traitement du template sujet
			subjectTemplate.putAll(context);
			subjectTemplate.processTemplate(out);
			String mailSubject = out.toString();

			//Chargement du template contenant le corps du mail
			TemplateResource templateResourceBody = new URLTemplateResource("0",
					Objects.requireNonNull(this.getClass().getClassLoader()
							.getResource("/templates/import-notification-mail-body.ftl")));
			Template bodyTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

			// Traitement du template corps
			out = new StringWriter();
			bodyTemplate.putAll(context);
			bodyTemplate.processTemplate(out);
			String mailBody = out.toString();

			String adminEmailFromAddress = PrefsPropsUtil.getString(
				PortalUtil.getDefaultCompanyId(),
				PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
			MailHelper.sendMailWithPlainText(adminEmailFromAddress,
				mailAddresses + "," + providerSpecificMailAddresses, mailSubject, mailBody);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}