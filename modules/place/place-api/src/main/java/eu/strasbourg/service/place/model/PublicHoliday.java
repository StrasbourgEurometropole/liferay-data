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
 * The extended model interface for the PublicHoliday service. Represents a row in the &quot;place_PublicHoliday&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see PublicHolidayModel
 * @see eu.strasbourg.service.place.model.impl.PublicHolidayImpl
 * @see eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.place.model.impl.PublicHolidayImpl")
@ProviderType
public interface PublicHoliday extends PublicHolidayModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.place.model.impl.PublicHolidayImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PublicHoliday, Long> PUBLIC_HOLIDAY_ID_ACCESSOR =
		new Accessor<PublicHoliday, Long>() {
			@Override
			public Long get(PublicHoliday publicHoliday) {
				return publicHoliday.getPublicHolidayId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PublicHoliday> getTypeClass() {
				return PublicHoliday.class;
			}
		};
}