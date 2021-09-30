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

package eu.strasbourg.service.notif.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.base.NatureNotifLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the nature notif local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.notif.service.NatureNotifLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NatureNotifLocalServiceBaseImpl
 */
public class NatureNotifLocalServiceImpl
	extends NatureNotifLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.notif.service.NatureNotifLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.notif.service.NatureNotifLocalServiceUtil</code>.
	 */

	/**
	 * Crée une nature vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public NatureNotif createNature() {
		long pk = counterLocalService.increment();
		return natureNotifPersistence.create(pk);
	}

	@Override
	public List<NatureNotif> getByServiceid(long serviceId) {
		return natureNotifPersistence.findByServiceId(serviceId);
	}
}