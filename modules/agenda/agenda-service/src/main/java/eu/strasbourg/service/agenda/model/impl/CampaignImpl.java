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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.service.CampaignEventLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

/**
 * The extended model implementation for the Campaign service. Represents a row
 * in the &quot;agenda_Campaign&quot; database table, with each column mapped to
 * a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.agenda.model.Campaign} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class CampaignImpl extends CampaignBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6864209581805430810L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a campaign
	 * model instance should use the {@link
	 * eu.strasbourg.service.agenda.model.Campaign} interface instead.
	 */
	public CampaignImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Campaign.class.getName(),
			this.getCampaignId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
			.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne les themes de la campagne
	 */
	@Override
	public List<AssetCategory> getThemes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), "theme agenda");
	}

	/**
	 * Retourne true si l'utilisateur passé en paramètre est manager de la
	 * campagne
	 */
	@Override
	public boolean isManagedByUser(long userId) {
		if (userId <= 0) {
			return false;
		}
		for (String userIdString : this.getManagersIds().split(",")) {
			if (userIdString.equals(String.valueOf(userId))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<User> getManagers() {
		List<User> managers = new ArrayList<User>();
		for (String managerIdString : this.getManagersIds().split(",")) {
			Long userId = GetterUtil.getLong(managerIdString);
			if (userId > 0) {
				User user = UserLocalServiceUtil.fetchUser(userId);
				if (user != null) {
					managers.add(user);
				}
			}
		}
		return managers;
	}

	/**
	 * Retourne la liste des événements de la campagne
	 */
	@Override
	public List<CampaignEvent> getEvents() {
		return CampaignEventLocalServiceUtil
			.findByCampaignId(this.getCampaignId());
	}

	/**
	 * Génère l'export et place le fichier dans le dossier d'import des
	 * événements
	 */
	@Override
	public void export() {
		JSONObject json = this.generateExport();
		String jsonString = json.toJSONString();
		String provider = FriendlyURLNormalizerUtil
			.normalize(this.getTitleCurrentValue());
		String fullPath = StrasbourgPropsUtil.getAgendaImportDirectory() + "/"
			+ provider + ".json";
		File file = new File(fullPath);
		try (PrintWriter printWriter = new PrintWriter(file)) {
			printWriter.print(jsonString);
		} catch (FileNotFoundException e) {
			log.error(e);
		}
	}

	/**
	 * Génère l'export JSON
	 */
	public JSONObject generateExport() {
		JSONObject json = JSONFactoryUtil.createJSONObject();

		// Provider
		String provider = FriendlyURLNormalizerUtil
			.normalize(this.getTitleCurrentValue());
		json.put("provider", provider);

		// Manifestations (vide)
		JSONArray jsonManifestations = JSONFactoryUtil.createJSONArray();
		json.put("manifestations", jsonManifestations);

		// Evénements
		JSONArray jsonEvents = JSONFactoryUtil.createJSONArray();
		for (CampaignEvent event : this.getEvents().stream()
			.filter(e -> e.getStatus() == WorkflowConstants.STATUS_APPROVED)
			.collect(Collectors.toList())) {
			jsonEvents.put(event.toJSON());
		}
		json.put("events", jsonEvents);

		return json;
	}

	Log log = LogFactoryUtil.getLog(this.getClass());
}
