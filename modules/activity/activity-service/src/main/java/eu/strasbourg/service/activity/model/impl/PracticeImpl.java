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
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.service.AssociationLocalServiceUtil;
import eu.strasbourg.service.activity.service.PracticeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The extended model implementation for the Practice service. Represents a row in the &quot;activity_Practice&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.activity.model.Practice} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class PracticeImpl extends PracticeBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a practice model instance should use the {@link eu.strasbourg.service.activity.model.Practice} interface instead.
	 */
	public PracticeImpl() {
	}

	/**
	 * Retourne la version live de cette entité
	 */
	@Override
	public Practice getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Practice livePractice = PracticeLocalServiceUtil
				.fetchPracticeByUuidAndGroupId(this.getUuid(),
						liveGroupId);
		return livePractice;
	}

	/**
	 * Retourne l'AssetEntry rattaché à cette entité
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Practice.class.getName(),
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
	 * Retourne les activités de l'association
	 */
	@Override
	public Association getAssociation() {
		return AssociationLocalServiceUtil.fetchAssociation(this.getAssociationId());
	}

	/**
	 * Retourne la pratique de l'association
	 */
	@Override
	public AssetCategory getPractice() {
		AssetCategory practice = null;
		List<AssetCategory> categories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PRACTICE);
		if(!categories.isEmpty())
			practice = categories.get(0);
		return practice;
	}

	/**
	 * Retourne les publics de l'association
	 */
	@Override
	public List<AssetCategory> getPublics() {
		List<AssetCategory> publicList = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PRACTICE_PUBLIC);
		return publicList;
	}

	/**
	 * Retourne les quartiers de l'association
	 */
	@Override
	public List<AssetCategory> getDistricts() {
		List<AssetCategory> categories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.TERRITORY);
		return categories;
	}

	/**
	 * Retourne l'accessibilité de l'association
	 */
	@Override
	public List<AssetCategory> getAccessibilities() {
		List<AssetCategory> categories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.ACCESSIBILITY);
		return categories;
	}
}