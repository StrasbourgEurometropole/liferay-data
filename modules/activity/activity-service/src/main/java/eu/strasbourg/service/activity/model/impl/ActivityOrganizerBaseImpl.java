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

package eu.strasbourg.service.activity.model.impl;

import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalServiceUtil;

/**
 * The extended model base implementation for the ActivityOrganizer service. Represents a row in the &quot;activity_ActivityOrganizer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ActivityOrganizerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizerImpl
 * @see ActivityOrganizer
 * @generated
 */
public abstract class ActivityOrganizerBaseImpl
	extends ActivityOrganizerModelImpl implements ActivityOrganizer {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a activity organizer model instance should use the <code>ActivityOrganizer</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ActivityOrganizerLocalServiceUtil.addActivityOrganizer(this);
		}
		else {
			ActivityOrganizerLocalServiceUtil.updateActivityOrganizer(this);
		}
	}

}