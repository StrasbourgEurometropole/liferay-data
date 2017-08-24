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

package eu.strasbourg.service.oidc.service.impl;

import javax.portlet.PortletSession;

import eu.strasbourg.service.oidc.service.base.PublikUserLocalServiceBaseImpl;

/**
 * The implementation of the publik user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.oidc.service.PublikUserLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PublikUserLocalServiceBaseImpl
 * @see eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil
 */
public class PublikUserLocalServiceImpl extends PublikUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil} to access the publik user local service.
	 */
	
	public void logUserIn(PortletSession session, String internalPublikId) {
		
	}
}