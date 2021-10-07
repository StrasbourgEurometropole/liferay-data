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

package eu.strasbourg.service.notif.model.impl;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.notif.model.Message;
import eu.strasbourg.service.notif.model.NatureNotif;
import eu.strasbourg.service.notif.service.MessageLocalServiceUtil;
import eu.strasbourg.service.notif.service.NatureNotifLocalServiceUtil;

import java.util.List;

/**
 * The extended model implementation for the ServiceNotif service. Represents a row in the &quot;notif_ServiceNotif&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.notif.model.ServiceNotif<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ServiceNotifImpl extends ServiceNotifBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a service notif model instance should use the {@link eu.strasbourg.service.notif.model.ServiceNotif} interface instead.
	 */
	public ServiceNotifImpl() {
	}

	/**
	 * Retourne les Natures du service
	 */
	@Override
	public List<NatureNotif> getNatures() {
		return NatureNotifLocalServiceUtil.getByServiceId(this.getServiceId());
	}

	/**
	 * Retourne les Messages du service
	 */
	@Override
	public List<Message> getMessages() {
		return MessageLocalServiceUtil.getByServiceId(this.getServiceId());
	}

}