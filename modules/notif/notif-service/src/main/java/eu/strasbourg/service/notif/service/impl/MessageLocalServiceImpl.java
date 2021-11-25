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

import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.service.base.MessageLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the message local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.notif.service.MessageLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalServiceBaseImpl
 */
public class MessageLocalServiceImpl extends MessageLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.notif.service.MessageLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.notif.service.MessageLocalServiceUtil</code>.
	 */

	/**
	 * Crée un message vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Message createMessage() {
		long pk = counterLocalService.increment();
		return messagePersistence.create(pk);
	}

	@Override
	public List<Message> getByServiceId(long serviceId) {
		return messagePersistence.findByServiceId(serviceId);
	}
}