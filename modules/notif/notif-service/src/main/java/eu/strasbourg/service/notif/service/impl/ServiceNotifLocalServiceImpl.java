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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.notif.exception.NoSuchServiceNotifException;
import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.base.ServiceNotifLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the service notif local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.notif.service.ServiceNotifLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceNotifLocalServiceBaseImpl
 */
public class ServiceNotifLocalServiceImpl
	extends ServiceNotifLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.notif.service.ServiceNotifLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.notif.service.ServiceNotifLocalServiceUtil</code>.
	 */

	/**
	 * Crée un service vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public ServiceNotif createService() {
		long pk = counterLocalService.increment();
		return serviceNotifPersistence.create(pk);
	}

	/**
	 * Supprime un service
	 */
	@Override
	public void removeService(long serviceId) {

		try {
			// on supprime les natures associées
			List<NatureNotif> natures = natureNotifPersistence.findByServiceId(serviceId);
			for(NatureNotif nature: natures){
				natureNotifPersistence.remove(nature);
			}

			// on supprime les messages associés
			List<Message> messages = messagePersistence.findByServiceId(serviceId);
			for(Message message: messages){
				messagePersistence.remove(message);
			}
			
			serviceNotifPersistence.remove(serviceId);
		} catch (Exception e) {
			_log.info(e.getMessage() + " : " + serviceId);
		}
	}

	@Override
	public List<ServiceNotif> getByOrganisationIds(long[] organisationIds) {
		return serviceNotifPersistence.findByOrganisationIds(organisationIds);
	}

	@Override
	public ServiceNotif getByTopic(String topic) throws NoSuchServiceNotifException {
		return serviceNotifPersistence.findByTopic(topic);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}