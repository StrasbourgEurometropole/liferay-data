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

package eu.strasbourg.service.gtfs.model.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;

import com.liferay.portal.kernel.template.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

/**
 * The extended model implementation for the ImportHistoric service. Represents a row in the &quot;gtfs_ImportHistoric&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.model.ImportHistoric} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class ImportHistoricImpl extends ImportHistoricBaseImpl {
	
	public final static Log log = LogFactoryUtil.getLog(ImportHistoricImpl.class);

	private static final long serialVersionUID = 5893961641581179554L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a import historic model instance should use the {@link eu.strasbourg.service.gtfs.model.ImportHistoric} interface instead.
	 */
	public ImportHistoricImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(ImportHistoric.class.getName(),
			this.getImportHistoricId());
	}
	
	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
	}
	
	/**
	 * Renvoie le label affichable du resultat de l'import
	 * @return
	 */
	@Override
	public String getResultLabel() {
		return this.getResult() == 1 ? "Succes" : "Echec";
	}
	
	/**
	 * Ajout d'une ligne dans le resultat de l'import
	 * @return
	 */
	@Override
	public void addNewOperation(String operation) {
		this.setOperations(
				this.getOperations() +
				"<p>" + operation + "</p>"
		);
		log.info(operation);
	}
	
	/**
	 * Envoi du mail d'import
	 */
	@Override
	public void sendMail() {
		// Properties de l'environnement courrant 
		String environment = StrasbourgPropsUtil.getEnvironment();
		// Properties de l'adresse de reception
		String mailAddresses = StrasbourgPropsUtil.getGTFSImportReportMail();
		
		Map<String, Object> context = new HashMap<>();
		context.put("importHistoric", this);
		context.put("environment", environment);

		StringWriter out = new StringWriter();

		try {
			// Chargement du template contenant le sujet du mail
			TemplateResource templateResourceSubject = new URLTemplateResource(
					"0",
					Objects.requireNonNull(this.getClass().getClassLoader()
							.getResource("/templates/" + "import-notification-mail-subject.ftl")));
			Template subjectTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceSubject, false);

			// Traitement du template sujet
			subjectTemplate.putAll(context);
			subjectTemplate.processTemplate(out);
			String mailSubject = out.toString();

			//Chargement du template contenant le corps du mail
			TemplateResource templateResourceBody = new URLTemplateResource("0",
					Objects.requireNonNull(this.getClass().getClassLoader()
							.getResource("/templates/" + "import-notification-mail-body.ftl")));
			Template bodyTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

			// Traitement du template corps
			out = new StringWriter();
			bodyTemplate.putAll(context);
			bodyTemplate.processTemplate(out);
			String mailBody = out.toString();


			// Properties de l'adresse d'envoie
			String adminEmailFromAddress = PrefsPropsUtil.getString(
					PortalUtil.getDefaultCompanyId(),
					PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
			
			// Envoie
			MailHelper.sendMailWithHTML(
					adminEmailFromAddress,
					mailAddresses,
					mailSubject, mailBody);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
}