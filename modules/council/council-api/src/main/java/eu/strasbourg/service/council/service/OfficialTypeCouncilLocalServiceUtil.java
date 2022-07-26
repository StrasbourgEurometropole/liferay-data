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

package eu.strasbourg.service.council.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for OfficialTypeCouncil. This utility wraps
 * <code>eu.strasbourg.service.council.service.impl.OfficialTypeCouncilLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncilLocalService
 * @generated
 */
public class OfficialTypeCouncilLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.council.service.impl.OfficialTypeCouncilLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the official type council to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialTypeCouncilLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param officialTypeCouncil the official type council
	 * @return the official type council that was added
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		addOfficialTypeCouncil(
			eu.strasbourg.service.council.model.OfficialTypeCouncil
				officialTypeCouncil) {

		return getService().addOfficialTypeCouncil(officialTypeCouncil);
	}

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		createOfficialTypeCouncil(
			long officialId, long typeId,
			com.liferay.portal.kernel.service.ServiceContext sc) {

		return getService().createOfficialTypeCouncil(officialId, typeId, sc);
	}

	/**
	 * Creates a new official type council with the primary key. Does not add the official type council to the database.
	 *
	 * @param officialTypeCouncilPK the primary key for the new official type council
	 * @return the new official type council
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		createOfficialTypeCouncil(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK) {

		return getService().createOfficialTypeCouncil(officialTypeCouncilPK);
	}

	/**
	 * Deletes the official type council from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialTypeCouncilLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param officialTypeCouncil the official type council
	 * @return the official type council that was removed
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		deleteOfficialTypeCouncil(
			eu.strasbourg.service.council.model.OfficialTypeCouncil
				officialTypeCouncil) {

		return getService().deleteOfficialTypeCouncil(officialTypeCouncil);
	}

	/**
	 * Deletes the official type council with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialTypeCouncilLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council that was removed
	 * @throws PortalException if a official type council with the primary key could not be found
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
			deleteOfficialTypeCouncil(
				eu.strasbourg.service.council.service.persistence.
					OfficialTypeCouncilPK officialTypeCouncilPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteOfficialTypeCouncil(officialTypeCouncilPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl</code>.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		fetchOfficialTypeCouncil(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK) {

		return getService().fetchOfficialTypeCouncil(officialTypeCouncilPK);
	}

	/**
	 * Returns the official type council matching the UUID and group.
	 *
	 * @param uuid the official type council's UUID
	 * @param groupId the primary key of the group
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		fetchOfficialTypeCouncilByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchOfficialTypeCouncilByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Retourne les types de Conseil d'un élu
	 *
	 * @return
	 */
	public static java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			findByOfficialId(long officialId) {

		return getService().findByOfficialId(officialId);
	}

	/**
	 * Retourne la liste des votes d'une délibération
	 */
	public static java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil> findByTypeId(
			long typeId) {

		return getService().findByTypeId(typeId);
	}

	/**
	 * Retourne le vote d'un élu pour une délibération
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		findByTypeIdandOfficialId(long typeId, long officialId) {

		return getService().findByTypeIdandOfficialId(typeId, officialId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the official type council with the primary key.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council
	 * @throws PortalException if a official type council with the primary key could not be found
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
			getOfficialTypeCouncil(
				eu.strasbourg.service.council.service.persistence.
					OfficialTypeCouncilPK officialTypeCouncilPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOfficialTypeCouncil(officialTypeCouncilPK);
	}

	/**
	 * Returns the official type council matching the UUID and group.
	 *
	 * @param uuid the official type council's UUID
	 * @param groupId the primary key of the group
	 * @return the matching official type council
	 * @throws PortalException if a matching official type council could not be found
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
			getOfficialTypeCouncilByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOfficialTypeCouncilByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of official type councils
	 */
	public static java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			getOfficialTypeCouncils(int start, int end) {

		return getService().getOfficialTypeCouncils(start, end);
	}

	/**
	 * Returns all the official type councils matching the UUID and company.
	 *
	 * @param uuid the UUID of the official type councils
	 * @param companyId the primary key of the company
	 * @return the matching official type councils, or an empty list if no matches were found
	 */
	public static java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			getOfficialTypeCouncilsByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().getOfficialTypeCouncilsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of official type councils matching the UUID and company.
	 *
	 * @param uuid the UUID of the official type councils
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching official type councils, or an empty list if no matches were found
	 */
	public static java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			getOfficialTypeCouncilsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<eu.strasbourg.service.council.model.OfficialTypeCouncil>
						orderByComparator) {

		return getService().getOfficialTypeCouncilsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of official type councils.
	 *
	 * @return the number of official type councils
	 */
	public static int getOfficialTypeCouncilsCount() {
		return getService().getOfficialTypeCouncilsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprime une entité
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
			removeOfficialTypeCouncil(long officialId, long typeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().removeOfficialTypeCouncil(officialId, typeId);
	}

	/**
	 * Updates the official type council in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialTypeCouncilLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param officialTypeCouncil the official type council
	 * @return the official type council that was updated
	 */
	public static eu.strasbourg.service.council.model.OfficialTypeCouncil
		updateOfficialTypeCouncil(
			eu.strasbourg.service.council.model.OfficialTypeCouncil
				officialTypeCouncil) {

		return getService().updateOfficialTypeCouncil(officialTypeCouncil);
	}

	public static OfficialTypeCouncilLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<OfficialTypeCouncilLocalService, OfficialTypeCouncilLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			OfficialTypeCouncilLocalService.class);

		ServiceTracker
			<OfficialTypeCouncilLocalService, OfficialTypeCouncilLocalService>
				serviceTracker =
					new ServiceTracker
						<OfficialTypeCouncilLocalService,
						 OfficialTypeCouncilLocalService>(
							 bundle.getBundleContext(),
							 OfficialTypeCouncilLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}