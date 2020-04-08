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

package eu.strasbourg.service.gtfs.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import eu.strasbourg.service.gtfs.model.Alert;
import eu.strasbourg.service.gtfs.model.Direction;
import eu.strasbourg.service.gtfs.service.base.AlertLocalServiceBaseImpl;

import java.io.IOException;
import java.util.List;

/**
 * The implementation of the alert local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.AlertLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see AlertLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.AlertLocalServiceUtil
 */
public class AlertLocalServiceImpl extends AlertLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil} to access the direction local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(DirectionLocalServiceImpl.class);

	/**
	 * Crée une entree avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Alert createAlert(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();

		Alert alert = this.alertLocalService.createAlert(pk);

		alert.setGroupId(sc.getScopeGroupId());

		return alert;
	}

	/**
	 * Met à jour une entree et l'enregistre en base de données
	 * @throws PortalException
	 * @throws IOException
	 */
	@Override
	public Alert updateAlert(Alert alert, ServiceContext sc) throws PortalException {
		alert = this.alertLocalService.updateAlert(alert);

		return alert;
	}

	/**
	 * Met à jour les entree donnees
	 * @throws IOException
	 */
	@Override
	public void updateAlerts(List<Alert> alerts, ServiceContext sc) throws PortalException {
		for (Alert alert : alerts) {
			this.updateAlert(alert, sc);
		}
	}

	/**
	 * Supprime l'entree
	 */
	@Override
	public Alert removeAlert(long alertId) throws PortalException {
		// Supprime l'entree
		Alert alert = this.alertPersistence.remove(alertId);

		return alert;
	}

	/**
	 * Supprime les entrees
	 */
	@Override
	public void removeAlerts(List<Alert> alerts) throws PortalException {
		for (Alert alert : alerts) {
			this.removeAlert(alert.getAlertId());
		}
	}

	/**
	 * Retourne toutes les entrees d'un groupe
	 */
	@Override
	public List<Alert> getByGroupId(long groupId) {
		return this.alertPersistence.findByGroupId(groupId);
	}

	/**
	 * Retourne toutes les alertes d'un arret
	 */
	@Override
	public List<Alert> getByArretId(long arretId) {
		return this.alertPersistence.findByArretId(arretId);
	}

	/**
	 * Supprime les entree correspondants au arretId donnee
	 */
	@Override
	public List<Alert> removeByArretId(long arretId) throws PortalException {
		List<Alert> removedAlerts = this.getByArretId(arretId);

		this.alertPersistence.removeByArretId(arretId);

		return removedAlerts;
	}

	/**
	 * Retourne la liste de toutes les alertes
	 */
	@Override
	public List<Alert> getAll() {
		return this.alertPersistence.findAll();
	}
}