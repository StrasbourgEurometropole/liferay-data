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

package eu.strasbourg.service.project.model.impl;

import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;

/**
 * The extended model base implementation for the PlacitPlace service. Represents a row in the &quot;project_PlacitPlace&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PlacitPlaceImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see PlacitPlaceImpl
 * @see PlacitPlace
 * @generated
 */
public abstract class PlacitPlaceBaseImpl
	extends PlacitPlaceModelImpl implements PlacitPlace {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a placit place model instance should use the <code>PlacitPlace</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			PlacitPlaceLocalServiceUtil.addPlacitPlace(this);
		}
		else {
			PlacitPlaceLocalServiceUtil.updatePlacitPlace(this);
		}
	}

}