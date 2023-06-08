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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Slot service. Represents a row in
 * the &quot;place_Slot&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.Slot} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class SlotImpl extends SlotBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a slot model
	 * instance should use the {@link eu.strasbourg.service.place.model.Slot}
	 * interface instead.
	 */
	public SlotImpl() {
	}

	/**
	 * Retourne la version JSON des horaires
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject slotJSON = JSONFactoryUtil.createJSONObject();

		slotJSON.put("dayOfWeek", this.getDayOfWeek());
		slotJSON.put("startHour", this.getStartHour());
		slotJSON.put("endHour", this.getEndHour());
		
		return slotJSON;
	}
}