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

package eu.strasbourg.service.place.model.impl;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.service.PeriodLocalServiceUtil;
import eu.strasbourg.service.place.service.ScheduleExceptionLocalServiceUtil;

/**
 * The extended model implementation for the SubPlace service. Represents a row
 * in the &quot;place_SubPlace&quot; database table, with each column mapped to
 * a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.SubPlace} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class SubPlaceImpl extends SubPlaceBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4091307024003680779L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a sub place
	 * model instance should use the {@link
	 * eu.strasbourg.service.place.model.SubPlace} interface instead.
	 */
	public SubPlaceImpl() {
	}

	/**
	 * Retourne les ScheduleExceptions du sous-lieu
	 */
	@Override
	public List<ScheduleException> getScheduleExceptions() {
		return ScheduleExceptionLocalServiceUtil
				.getBySubPlaceId(this.getSubPlaceId());
	}

	/**
	 * Retourne les Periods du sous-lieu
	 */
	@Override
	public List<Period> getPeriods() {
		return PeriodLocalServiceUtil.getBySubPlaceId(this.getSubPlaceId());
	}
}