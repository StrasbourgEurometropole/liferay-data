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
 * The extended model interface for the AgendaExportPeriod service. Represents a row in the &quot;agenda_AgendaExportPeriod&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see AgendaExportPeriodModel
 * @see eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodImpl
 * @see eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodImpl")
@ProviderType
public interface AgendaExportPeriod extends AgendaExportPeriodModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AgendaExportPeriod, Long> AGENDA_EXPORT_PERIOD_ID_ACCESSOR =
		new Accessor<AgendaExportPeriod, Long>() {
			@Override
			public Long get(AgendaExportPeriod agendaExportPeriod) {
				return agendaExportPeriod.getAgendaExportPeriodId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AgendaExportPeriod> getTypeClass() {
				return AgendaExportPeriod.class;
			}
		};
}