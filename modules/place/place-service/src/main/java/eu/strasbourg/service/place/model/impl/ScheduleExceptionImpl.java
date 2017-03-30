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

import java.text.DateFormat;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.utils.JSONHelper;

/**
 * The extended model implementation for the ScheduleException service.
 * Represents a row in the &quot;place_ScheduleException&quot; database table,
 * with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.ScheduleException}
 * interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class ScheduleExceptionImpl extends ScheduleExceptionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a schedule
	 * exception model instance should use the {@link
	 * eu.strasbourg.service.place.model.ScheduleException} interface instead.
	 */
	public ScheduleExceptionImpl() {
	}

	/**
	 * Retourne la version JSON des exceptions
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject scheduleExceptionJSON = JSONFactoryUtil.createJSONObject();

		scheduleExceptionJSON.put("description",
				JSONHelper.getJSONFromI18nMap(this.getCommentMap()));
		DateFormat dateFormat = DateFormatFactoryUtil
				.getSimpleDateFormat("yyyy-MM-dd");
		scheduleExceptionJSON.put("startDate",
				dateFormat.format(this.getStartDate()));
		scheduleExceptionJSON.put("endDate",
				dateFormat.format(this.getEndDate()));
		scheduleExceptionJSON.put("closed", this.getClosed());
		if (!this.getClosed()) {
			if (Validator.isNotNull(this.getStartHour())) {
				scheduleExceptionJSON.put("startHour", this.getStartHour());
			}
			if (Validator.isNotNull(this.getEndHour())) {
				scheduleExceptionJSON.put("endHour", this.getEndHour());
			}
		}

		return scheduleExceptionJSON;
	}
}