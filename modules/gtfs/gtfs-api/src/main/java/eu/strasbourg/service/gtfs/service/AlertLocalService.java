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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.Alert;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Alert. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see AlertLocalServiceUtil
 * @see eu.strasbourg.service.gtfs.service.base.AlertLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.AlertLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AlertLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AlertLocalServiceUtil} to access the alert local service. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.AlertLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the alert to the database. Also notifies the appropriate model listeners.
	*
	* @param alert the alert
	* @return the alert that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Alert addAlert(Alert alert);

	/**
	* Crée une entree avec une PK, non ajouté à la base de donnée
	*/
	public Alert createAlert(ServiceContext sc) throws PortalException;

	/**
	* Creates a new alert with the primary key. Does not add the alert to the database.
	*
	* @param alertId the primary key for the new alert
	* @return the new alert
	*/
	public Alert createAlert(long alertId);

	/**
	* Deletes the alert from the database. Also notifies the appropriate model listeners.
	*
	* @param alert the alert
	* @return the alert that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Alert deleteAlert(Alert alert);

	/**
	* Deletes the alert with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param alertId the primary key of the alert
	* @return the alert that was removed
	* @throws PortalException if a alert with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Alert deleteAlert(long alertId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Alert fetchAlert(long alertId);

	/**
	* Returns the alert matching the UUID and group.
	*
	* @param uuid the alert's UUID
	* @param groupId the primary key of the group
	* @return the matching alert, or <code>null</code> if a matching alert could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Alert fetchAlertByUuidAndGroupId(java.lang.String uuid, long groupId);

	/**
	* Returns the alert with the primary key.
	*
	* @param alertId the primary key of the alert
	* @return the alert
	* @throws PortalException if a alert with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Alert getAlert(long alertId) throws PortalException;

	/**
	* Returns the alert matching the UUID and group.
	*
	* @param uuid the alert's UUID
	* @param groupId the primary key of the group
	* @return the matching alert
	* @throws PortalException if a matching alert could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Alert getAlertByUuidAndGroupId(java.lang.String uuid, long groupId)
		throws PortalException;

	/**
	* Supprime l'entree
	*/
	public Alert removeAlert(long alertId) throws PortalException;

	/**
	* Updates the alert in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param alert the alert
	* @return the alert that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Alert updateAlert(Alert alert);

	/**
	* Met à jour une entree et l'enregistre en base de données
	*
	* @throws PortalException
	* @throws IOException
	*/
	public Alert updateAlert(Alert alert, ServiceContext sc)
		throws PortalException;

	/**
	* Returns the number of alerts.
	*
	* @return the number of alerts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAlertsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the alerts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of alerts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Alert> getAlerts(int start, int end);

	/**
	* Returns all the alerts matching the UUID and company.
	*
	* @param uuid the UUID of the alerts
	* @param companyId the primary key of the company
	* @return the matching alerts, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Alert> getAlertsByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of alerts matching the UUID and company.
	*
	* @param uuid the UUID of the alerts
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching alerts, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Alert> getAlertsByUuidAndCompanyId(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Alert> orderByComparator);

	/**
	* Retourne la liste de toutes les alertes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Alert> getAll();

	/**
	* Retourne toutes les alertes d'un arret
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Alert> getByArretId(long arretId);

	/**
	* Retourne toutes les entrees d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Alert> getByGroupId(long groupId);

	/**
	* Supprime les entree correspondants au arretId donnee
	*/
	public List<Alert> removeByArretId(long arretId) throws PortalException;

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Supprime les entrees
	*/
	public void removeAlerts(List<Alert> alerts) throws PortalException;

	/**
	* Met à jour les entree donnees
	*
	* @throws IOException
	*/
	public void updateAlerts(List<Alert> alerts, ServiceContext sc)
		throws PortalException;
}