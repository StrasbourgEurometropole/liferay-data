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

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Period service. Represents a row in the &quot;place_Period&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see PeriodModel
 * @see eu.strasbourg.service.place.model.impl.PeriodImpl
 * @see eu.strasbourg.service.place.model.impl.PeriodModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.place.model.impl.PeriodImpl")
@ProviderType
public interface Period extends PeriodModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.place.model.impl.PeriodImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Period, Long> PERIOD_ID_ACCESSOR = new Accessor<Period, Long>() {
			@Override
			public Long get(Period period) {
				return period.getPeriodId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Period> getTypeClass() {
				return Period.class;
			}
		};

	/**
	* Retourne les Slots de la période
	*/
	public java.util.List<eu.strasbourg.service.place.model.Slot> getSlots();

	/**
	* Retourne la version JSON de la période
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}