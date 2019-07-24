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

package eu.strasbourg.service.activity.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.model.AssociationActivity;
import eu.strasbourg.service.activity.service.AssociationActivityLocalServiceUtil;
import eu.strasbourg.service.activity.service.AssociationLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.util.List;
import java.util.Locale;

/**
 * The extended model implementation for the AssociationActivity service. Represents a row in the &quot;activity_AssociationActivity&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.activity.model.AssociationActivity} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AssociationActivityImpl extends AssociationActivityBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a association activity model instance should use the {@link eu.strasbourg.service.activity.model.AssociationActivity} interface instead.
	 */
	public AssociationActivityImpl() {
	}

	/**
	 * Retourne la version live de cette entité
	 */
	@Override
	public AssociationActivity getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		AssociationActivity liveAssociation = AssociationActivityLocalServiceUtil
				.fetchAssociationActivityByUuidAndGroupId(this.getUuid(),
						liveGroupId);
		return liveAssociation;
	}

	/**
	 * Retourne l'AssetEntry rattaché à cette entité
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(AssociationActivity.class.getName(),
				this.getPrimaryKey());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cette entité (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
				.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne l'association de l'activité
	 */
	@Override
	public Association getAssociation() {
		try {
			return AssociationLocalServiceUtil.getAssociation(this.getAssociationId());
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retourne le label de l'activité de l'association
	 */
	@Override
	public String getActivityLabel(Locale locale) {
		String activity = "";
		List<AssetCategory> categories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.ACTIVITY);
		if(!categories.isEmpty())
			activity = categories.get(0).getTitle(locale);
		return activity;
	}
}