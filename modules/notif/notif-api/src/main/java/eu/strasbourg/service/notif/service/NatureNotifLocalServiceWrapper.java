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

package eu.strasbourg.service.notif.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NatureNotifLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NatureNotifLocalService
 * @generated
 */
@ProviderType
public class NatureNotifLocalServiceWrapper
	implements NatureNotifLocalService,
			   ServiceWrapper<NatureNotifLocalService> {

	public NatureNotifLocalServiceWrapper(
		NatureNotifLocalService natureNotifLocalService) {

		_natureNotifLocalService = natureNotifLocalService;
	}

	/**
	 * Adds the nature notif to the database. Also notifies the appropriate model listeners.
	 *
	 * @param natureNotif the nature notif
	 * @return the nature notif that was added
	 */
	@Override
	public eu.strasbourg.service.notif.model.NatureNotif addNatureNotif(
		eu.strasbourg.service.notif.model.NatureNotif natureNotif) {

		return _natureNotifLocalService.addNatureNotif(natureNotif);
	}

	/**
	 * Crée une nature vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.notif.model.NatureNotif createNature() {
		return _natureNotifLocalService.createNature();
	}

	/**
	 * Creates a new nature notif with the primary key. Does not add the nature notif to the database.
	 *
	 * @param natureId the primary key for the new nature notif
	 * @return the new nature notif
	 */
	@Override
	public eu.strasbourg.service.notif.model.NatureNotif createNatureNotif(
		long natureId) {

		return _natureNotifLocalService.createNatureNotif(natureId);
	}

	/**
	 * Deletes the nature notif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif that was removed
	 * @throws PortalException if a nature notif with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notif.model.NatureNotif deleteNatureNotif(
			long natureId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _natureNotifLocalService.deleteNatureNotif(natureId);
	}

	/**
	 * Deletes the nature notif from the database. Also notifies the appropriate model listeners.
	 *
	 * @param natureNotif the nature notif
	 * @return the nature notif that was removed
	 */
	@Override
	public eu.strasbourg.service.notif.model.NatureNotif deleteNatureNotif(
		eu.strasbourg.service.notif.model.NatureNotif natureNotif) {

		return _natureNotifLocalService.deleteNatureNotif(natureNotif);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _natureNotifLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _natureNotifLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _natureNotifLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notif.model.impl.NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _natureNotifLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notif.model.impl.NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _natureNotifLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _natureNotifLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _natureNotifLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.notif.model.NatureNotif fetchNatureNotif(
		long natureId) {

		return _natureNotifLocalService.fetchNatureNotif(natureId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _natureNotifLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<eu.strasbourg.service.notif.model.NatureNotif>
		getByServiceid(long serviceId) {

		return _natureNotifLocalService.getByServiceid(serviceId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _natureNotifLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the nature notif with the primary key.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif
	 * @throws PortalException if a nature notif with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notif.model.NatureNotif getNatureNotif(
			long natureId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _natureNotifLocalService.getNatureNotif(natureId);
	}

	/**
	 * Returns a range of all the nature notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notif.model.impl.NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @return the range of nature notifs
	 */
	@Override
	public java.util.List<eu.strasbourg.service.notif.model.NatureNotif>
		getNatureNotifs(int start, int end) {

		return _natureNotifLocalService.getNatureNotifs(start, end);
	}

	/**
	 * Returns the number of nature notifs.
	 *
	 * @return the number of nature notifs
	 */
	@Override
	public int getNatureNotifsCount() {
		return _natureNotifLocalService.getNatureNotifsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _natureNotifLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _natureNotifLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the nature notif in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param natureNotif the nature notif
	 * @return the nature notif that was updated
	 */
	@Override
	public eu.strasbourg.service.notif.model.NatureNotif updateNatureNotif(
		eu.strasbourg.service.notif.model.NatureNotif natureNotif) {

		return _natureNotifLocalService.updateNatureNotif(natureNotif);
	}

	@Override
	public NatureNotifLocalService getWrappedService() {
		return _natureNotifLocalService;
	}

	@Override
	public void setWrappedService(
		NatureNotifLocalService natureNotifLocalService) {

		_natureNotifLocalService = natureNotifLocalService;
	}

	private NatureNotifLocalService _natureNotifLocalService;

}