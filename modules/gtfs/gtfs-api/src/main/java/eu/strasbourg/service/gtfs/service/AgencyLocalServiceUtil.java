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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Agency. This utility wraps
 * {@link eu.strasbourg.service.gtfs.service.impl.AgencyLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see AgencyLocalService
 * @see eu.strasbourg.service.gtfs.service.base.AgencyLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.AgencyLocalServiceImpl
 * @generated
 */
@ProviderType
public class AgencyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.AgencyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the agency to the database. Also notifies the appropriate model listeners.
	*
	* @param agency the agency
	* @return the agency that was added
	*/
	public static eu.strasbourg.service.gtfs.model.Agency addAgency(
		eu.strasbourg.service.gtfs.model.Agency agency) {
		return getService().addAgency(agency);
	}

	/**
	* Crée une agence vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.gtfs.model.Agency createAgency(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createAgency(sc);
	}

	/**
	* Creates a new agency with the primary key. Does not add the agency to the database.
	*
	* @param id the primary key for the new agency
	* @return the new agency
	*/
	public static eu.strasbourg.service.gtfs.model.Agency createAgency(long id) {
		return getService().createAgency(id);
	}

	/**
	* Crée une agence à partir d'une entrée GTFS
	*/
	public static eu.strasbourg.service.gtfs.model.Agency createAgencyFromGTFS(
		eu.strasbourg.utils.models.AgencyGTFS entry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createAgencyFromGTFS(entry);
	}

	/**
	* Deletes the agency from the database. Also notifies the appropriate model listeners.
	*
	* @param agency the agency
	* @return the agency that was removed
	*/
	public static eu.strasbourg.service.gtfs.model.Agency deleteAgency(
		eu.strasbourg.service.gtfs.model.Agency agency) {
		return getService().deleteAgency(agency);
	}

	/**
	* Deletes the agency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the agency
	* @return the agency that was removed
	* @throws PortalException if a agency with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Agency deleteAgency(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAgency(id);
	}

	public static eu.strasbourg.service.gtfs.model.Agency fetchAgency(long id) {
		return getService().fetchAgency(id);
	}

	/**
	* Returns the agency with the primary key.
	*
	* @param id the primary key of the agency
	* @return the agency
	* @throws PortalException if a agency with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Agency getAgency(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAgency(id);
	}

	/**
	* Supprime une agence
	*/
	public static eu.strasbourg.service.gtfs.model.Agency removeAgency(
		long agencyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeAgency(agencyId);
	}

	/**
	* Updates the agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param agency the agency
	* @return the agency that was updated
	*/
	public static eu.strasbourg.service.gtfs.model.Agency updateAgency(
		eu.strasbourg.service.gtfs.model.Agency agency) {
		return getService().updateAgency(agency);
	}

	/**
	* Met à jour une agence et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public static eu.strasbourg.service.gtfs.model.Agency updateAgency(
		eu.strasbourg.service.gtfs.model.Agency agency,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateAgency(agency, sc);
	}

	/**
	* Returns the number of agencies.
	*
	* @return the number of agencies
	*/
	public static int getAgenciesCount() {
		return getService().getAgenciesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the agencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @return the range of agencies
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Agency> getAgencies(
		int start, int end) {
		return getService().getAgencies(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Import des agences sous le format de données GTFS
	*/
	public static void importFromGTFS(
		java.util.Map<java.lang.String, eu.strasbourg.utils.models.AgencyGTFS> data)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().importFromGTFS(data);
	}

	/**
	* Supprime toutes les agences
	*/
	public static void removeAllAgencies()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().removeAllAgencies();
	}

	public static AgencyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AgencyLocalService, AgencyLocalService> _serviceTracker =
		ServiceTrackerFactory.open(AgencyLocalService.class);
}