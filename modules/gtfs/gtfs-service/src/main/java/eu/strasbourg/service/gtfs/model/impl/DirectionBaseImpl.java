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

package eu.strasbourg.service.gtfs.model.impl;

import aQute.bnd.annotation.ProviderType;

import eu.strasbourg.service.gtfs.model.Direction;
import eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil;

/**
 * The extended model base implementation for the Direction service. Represents a row in the &quot;gtfs_Direction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DirectionImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see DirectionImpl
 * @see Direction
 * @generated
 */
@ProviderType
public abstract class DirectionBaseImpl
	extends DirectionModelImpl implements Direction {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a direction model instance should use the <code>Direction</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			DirectionLocalServiceUtil.addDirection(this);
		}
		else {
			DirectionLocalServiceUtil.updateDirection(this);
		}
	}

}