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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AgendaExport service. Represents a row in the &quot;agenda_AgendaExport&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see AgendaExportModel
 * @see eu.strasbourg.service.agenda.model.impl.AgendaExportImpl
 * @see eu.strasbourg.service.agenda.model.impl.AgendaExportModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.AgendaExportImpl")
@ProviderType
public interface AgendaExport extends AgendaExportModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.AgendaExportImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AgendaExport, Long> AGENDA_EXPORT_ID_ACCESSOR = new Accessor<AgendaExport, Long>() {
			@Override
			public Long get(AgendaExport agendaExport) {
				return agendaExport.getAgendaExportId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AgendaExport> getTypeClass() {
				return AgendaExport.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne la liste des périodes auxquelles l'événement à lieu (classées par
	* date de début croissante)
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExportPeriod> getAgendaExportPeriods();

	/**
	* Retourne la liste des périodes ou en initialise une si la liste est vide
	* Les périodes sont triées par ordre croissant
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExportPeriod> getOrCreateAgendaExportPeriods()
		throws com.liferay.portal.kernel.exception.PortalException;
}