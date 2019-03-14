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

import java.util.Locale;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.utils.DateHelper;

/**
 * The extended model implementation for the EventPeriod service. Represents a row in the &quot;event_EventPeriod&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.model.EventPeriod} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class EventPeriodImpl extends EventPeriodBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6986209394295845493L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a event period model instance should use the {@link eu.strasbourg.service.agenda.model.EventPeriod} interface instead.
	 */
	public EventPeriodImpl() {
	}
	
	@Override
	public String getDisplay(Locale locale) {
		return DateHelper.displayPeriod(this.getStartDate(), this.getEndDate(), locale, true, false);
	}
}