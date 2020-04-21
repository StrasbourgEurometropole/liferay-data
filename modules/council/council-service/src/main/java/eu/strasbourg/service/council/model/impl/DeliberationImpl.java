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

package eu.strasbourg.service.council.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.util.List;

/**
 * The extended model implementation for the Deliberation service. Represents a row in the &quot;council_Deliberation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.model.Deliberation} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class DeliberationImpl extends DeliberationBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a deliberation model instance should use the {@link eu.strasbourg.service.council.model.Deliberation} interface instead.
	 */
	public DeliberationImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Deliberation.class.getName(), this.getDeliberationId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
	}

	public CouncilSession getCouncilSession() {
		return CouncilSessionLocalServiceUtil.fetchCouncilSession(this.getCouncilSessionId());
	}

	public boolean IsCree() { return this.getStage().equals(StageDeliberation.get(1).getName());}
	public boolean IsAffichaeEnCours() { return this.getStage().equals(StageDeliberation.get(2).getName());}
	public boolean IsVoteOuvert() { return this.getStage().equals(StageDeliberation.get(3).getName());}
	public boolean IsAdopte() { return this.getStage().equals(StageDeliberation.get(4).getName());}
	public boolean IsRejete() { return this.getStage().equals(StageDeliberation.get(5).getName());}
	public boolean IsCommunique() { return this.getStage().equals(StageDeliberation.get(6).getName());}
	public boolean IsRetire() { return this.getStage().equals(StageDeliberation.get(7).getName());}

}