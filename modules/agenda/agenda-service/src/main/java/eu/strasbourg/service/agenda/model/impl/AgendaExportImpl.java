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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.service.AgendaExportLocalServiceUtil;
import eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalServiceUtil;
import eu.strasbourg.service.agenda.service.AgendaExportService;
import eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The extended model implementation for the AgendaExport service. Represents a row in the &quot;agenda_AgendaExport&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.model.AgendaExport} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class AgendaExportImpl extends AgendaExportBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a agenda export model instance should use the {@link eu.strasbourg.service.agenda.model.AgendaExport} interface instead.
	 */

	private List<AgendaExportPeriod> agendaExportPeriods;

	public AgendaExportImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(AgendaExport.class.getName(),
			this.getAgendaExportId());
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
	 * Retourne la liste des périodes auxquelles l'événement à lieu (classées par
	 * date de début croissante)
	 */
	@Override
	public List<AgendaExportPeriod> getAgendaExportPeriods() {
		List<AgendaExportPeriod> periods = AgendaExportPeriodLocalServiceUtil.getByAgendaExportId(this.getAgendaExportId());
		List<AgendaExportPeriod> sortedPeriods = new ArrayList<>(periods);
		sortedPeriods.sort((p1, p2) -> p1.getStartDate().compareTo(p2.getStartDate()));
		return sortedPeriods;
	}
}