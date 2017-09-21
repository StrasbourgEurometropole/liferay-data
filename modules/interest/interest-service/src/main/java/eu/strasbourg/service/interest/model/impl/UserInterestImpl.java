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

package eu.strasbourg.service.interest.model.impl;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;

/**
 * The extended model implementation for the UserInterest service. Represents a row in the &quot;interest_UserInterest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.interest.model.UserInterest} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class UserInterestImpl extends UserInterestBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a user interest model instance should use the {@link eu.strasbourg.service.interest.model.UserInterest} interface instead.
	 */
	public UserInterestImpl() {
	}
	
	@Override
	public Interest getInterest() {
		return InterestLocalServiceUtil.fetchInterest(this.getInterestId()); 
	}
}