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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SessionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SessionLocalService
 * @generated
 */
@ProviderType
public class SessionLocalServiceWrapper implements SessionLocalService,
	ServiceWrapper<SessionLocalService> {
	public SessionLocalServiceWrapper(SessionLocalService sessionLocalService) {
		_sessionLocalService = sessionLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _sessionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sessionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _sessionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _sessionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Adds the session to the database. Also notifies the appropriate model listeners.
	*
	* @param session the session
	* @return the session that was added
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session addSession(
		com.liferay.portal.kernel.dao.orm.Session session) {
		return _sessionLocalService.addSession(session);
	}

	/**
	* Creates a new session with the primary key. Does not add the session to the database.
	*
	* @param sessionId the primary key for the new session
	* @return the new session
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session createSession(
		long sessionId) {
		return _sessionLocalService.createSession(sessionId);
	}

	/**
	* Deletes the session from the database. Also notifies the appropriate model listeners.
	*
	* @param session the session
	* @return the session that was removed
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session deleteSession(
		com.liferay.portal.kernel.dao.orm.Session session) {
		return _sessionLocalService.deleteSession(session);
	}

	/**
	* Deletes the session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sessionId the primary key of the session
	* @return the session that was removed
	* @throws PortalException if a session with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session deleteSession(
		long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sessionLocalService.deleteSession(sessionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.Session fetchSession(
		long sessionId) {
		return _sessionLocalService.fetchSession(sessionId);
	}

	/**
	* Returns the session matching the UUID and group.
	*
	* @param uuid the session's UUID
	* @param groupId the primary key of the group
	* @return the matching session, or <code>null</code> if a matching session could not be found
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session fetchSessionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _sessionLocalService.fetchSessionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the session with the primary key.
	*
	* @param sessionId the primary key of the session
	* @return the session
	* @throws PortalException if a session with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session getSession(long sessionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sessionLocalService.getSession(sessionId);
	}

	/**
	* Returns the session matching the UUID and group.
	*
	* @param uuid the session's UUID
	* @param groupId the primary key of the group
	* @return the matching session
	* @throws PortalException if a matching session could not be found
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session getSessionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sessionLocalService.getSessionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param session the session
	* @return the session that was updated
	*/
	@Override
	public com.liferay.portal.kernel.dao.orm.Session updateSession(
		com.liferay.portal.kernel.dao.orm.Session session) {
		return _sessionLocalService.updateSession(session);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sessionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sessionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of sessions.
	*
	* @return the number of sessions
	*/
	@Override
	public int getSessionsCount() {
		return _sessionLocalService.getSessionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _sessionLocalService.getOSGiServiceIdentifier();
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
		return _sessionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _sessionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _sessionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @return the range of sessions
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.dao.orm.Session> getSessions(
		int start, int end) {
		return _sessionLocalService.getSessions(start, end);
	}

	/**
	* Returns all the sessions matching the UUID and company.
	*
	* @param uuid the UUID of the sessions
	* @param companyId the primary key of the company
	* @return the matching sessions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.dao.orm.Session> getSessionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _sessionLocalService.getSessionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of sessions matching the UUID and company.
	*
	* @param uuid the UUID of the sessions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sessions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.portal.kernel.dao.orm.Session> getSessionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return _sessionLocalService.getSessionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _sessionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _sessionLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public SessionLocalService getWrappedService() {
		return _sessionLocalService;
	}

	@Override
	public void setWrappedService(SessionLocalService sessionLocalService) {
		_sessionLocalService = sessionLocalService;
	}

	private SessionLocalService _sessionLocalService;
}