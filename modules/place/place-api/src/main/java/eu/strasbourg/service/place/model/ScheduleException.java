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
 * The extended model interface for the ScheduleException service. Represents a row in the &quot;place_ScheduleException&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see ScheduleExceptionModel
 * @see eu.strasbourg.service.place.model.impl.ScheduleExceptionImpl
 * @see eu.strasbourg.service.place.model.impl.ScheduleExceptionModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.place.model.impl.ScheduleExceptionImpl")
@ProviderType
public interface ScheduleException extends ScheduleExceptionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.place.model.impl.ScheduleExceptionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ScheduleException, Long> EXCEPTION_ID_ACCESSOR = new Accessor<ScheduleException, Long>() {
			@Override
			public Long get(ScheduleException scheduleException) {
				return scheduleException.getExceptionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ScheduleException> getTypeClass() {
				return ScheduleException.class;
			}
		};
}