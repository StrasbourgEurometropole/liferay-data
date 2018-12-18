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

package eu.strasbourg.service.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for InitiativeHelp. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.InitiativeHelpLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see InitiativeHelpLocalService
 * @see eu.strasbourg.service.project.service.base.InitiativeHelpLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.InitiativeHelpLocalServiceImpl
 * @generated
 */
@ProviderType
public class InitiativeHelpLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.InitiativeHelpLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the initiative help to the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeHelp the initiative help
	* @return the initiative help that was added
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp addInitiativeHelp(
		eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {
		return getService().addInitiativeHelp(initiativeHelp);
	}

	/**
	* Methode de creation d'une initiative
	*
	* @param sc Le contexte de la requete.
	* @return L'aide cree.
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp createInitiativeHelp(
		com.liferay.portal.kernel.service.ServiceContext sc) {
		return getService().createInitiativeHelp(sc);
	}

	/**
	* Creates a new initiative help with the primary key. Does not add the initiative help to the database.
	*
	* @param initiativeHelpId the primary key for the new initiative help
	* @return the new initiative help
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp createInitiativeHelp(
		long initiativeHelpId) {
		return getService().createInitiativeHelp(initiativeHelpId);
	}

	/**
	* Deletes the initiative help from the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeHelp the initiative help
	* @return the initiative help that was removed
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp deleteInitiativeHelp(
		eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {
		return getService().deleteInitiativeHelp(initiativeHelp);
	}

	/**
	* Deletes the initiative help with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help that was removed
	* @throws PortalException if a initiative help with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp deleteInitiativeHelp(
		long initiativeHelpId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteInitiativeHelp(initiativeHelpId);
	}

	public static eu.strasbourg.service.project.model.InitiativeHelp fetchInitiativeHelp(
		long initiativeHelpId) {
		return getService().fetchInitiativeHelp(initiativeHelpId);
	}

	/**
	* Returns the initiative help matching the UUID and group.
	*
	* @param uuid the initiative help's UUID
	* @param groupId the primary key of the group
	* @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp fetchInitiativeHelpByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchInitiativeHelpByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Retourne l'aide proposee par un utilisateur pour une initiative donnee
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp getByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId) {
		return getService()
				   .getByPublikUserIdAndInitiativeId(publikUserId, initiativeId);
	}

	/**
	* Returns the initiative help with the primary key.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help
	* @throws PortalException if a initiative help with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp getInitiativeHelp(
		long initiativeHelpId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getInitiativeHelp(initiativeHelpId);
	}

	/**
	* Returns the initiative help matching the UUID and group.
	*
	* @param uuid the initiative help's UUID
	* @param groupId the primary key of the group
	* @return the matching initiative help
	* @throws PortalException if a matching initiative help could not be found
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp getInitiativeHelpByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getInitiativeHelpByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprimer un soutien donne
	*
	* @param initiativeHelpId Id de l'aide inititative
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp removeInitiativeHelp(
		long initiativeHelpId) {
		return getService().removeInitiativeHelp(initiativeHelpId);
	}

	/**
	* Updates the initiative help in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param initiativeHelp the initiative help
	* @return the initiative help that was updated
	*/
	public static eu.strasbourg.service.project.model.InitiativeHelp updateInitiativeHelp(
		eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {
		return getService().updateInitiativeHelp(initiativeHelp);
	}

	/**
	* Returns the number of initiative helps.
	*
	* @return the number of initiative helps
	*/
	public static int getInitiativeHelpsCount() {
		return getService().getInitiativeHelpsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne la liste des aides d'une initiative
	*/
	public static java.util.List<eu.strasbourg.service.project.model.InitiativeHelp> getByInitiativeId(
		long initiativeId) {
		return getService().getByInitiativeId(initiativeId);
	}

	/**
	* Returns a range of all the initiative helps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of initiative helps
	*/
	public static java.util.List<eu.strasbourg.service.project.model.InitiativeHelp> getInitiativeHelps(
		int start, int end) {
		return getService().getInitiativeHelps(start, end);
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

	public static InitiativeHelpLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<InitiativeHelpLocalService, InitiativeHelpLocalService> _serviceTracker =
		ServiceTrackerFactory.open(InitiativeHelpLocalService.class);
}