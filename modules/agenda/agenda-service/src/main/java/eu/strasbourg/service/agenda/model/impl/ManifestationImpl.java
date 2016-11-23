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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The extended model implementation for the Manifestation service. Represents
 * a row in the &quot;event_Manifestation&quot; database table, with each
 * column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.agenda.model.Manifestation}
 * interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class ManifestationImpl extends ManifestationBaseImpl {

	private static final long serialVersionUID = -2751235155292359761L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a event
	 * manifestation model instance should use the {@link
	 * eu.strasbourg.service.agenda.model.Manifestation} interface instead.
	 */
	public ManifestationImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattachée cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil
			.fetchEntry(Manifestation.class.getName(), this.getManifestationId());
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
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	 * 
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(Locale locale) {
		return FileEntryHelper.getImageCopyright(this.getImageId(), locale);
	}

	/**
	 * Renvoie la liste des éditions de la galerie
	 */
	@Override
	public List<Event> getEvents() {
		return EventLocalServiceUtil
			.getManifestationEvents(this.getManifestationId());
	}

	/**
	 * Renvoie la liste des ids des éditions de la galerie sous forme de String
	 */
	@Override
	public String getEventsIds() {
		List<Event> events = this.getEvents();
		String ids = "";
		for (Event event : events) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += event.getEventId();
		}
		return ids;
	}

	/**
	 * Renvoie la liste des éditions publiées de la galerie
	 */
	@Override
	public List<Event> getPublishedEvents() {
		List<Event> events = this.getEvents();
		List<Event> result = new ArrayList<Event>();
		for (Event event : events) {
			if (event.isApproved()) {
				result.add(event);
			}
		}
		return result;
	}

	/**
	 * Renvoie la version live de la galerie d'édition, si elle existe
	 */
	@Override
	public Manifestation getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Manifestation liveManifestation = ManifestationLocalServiceUtil
			.fetchManifestationByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveManifestation;
	}
}