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

package eu.strasbourg.service.comment.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SignalementLocalService}.
 *
 * @author Romain Vergnais
 * @see SignalementLocalService
 * @generated
 */
@ProviderType
public class SignalementLocalServiceWrapper implements SignalementLocalService,
	ServiceWrapper<SignalementLocalService> {
	public SignalementLocalServiceWrapper(
		SignalementLocalService signalementLocalService) {
		_signalementLocalService = signalementLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _signalementLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _signalementLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _signalementLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _signalementLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _signalementLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _signalementLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the signalement to the database. Also notifies the appropriate model listeners.
	*
	* @param signalement the signalement
	* @return the signalement that was added
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement addSignalement(
		eu.strasbourg.service.comment.model.Signalement signalement) {
		return _signalementLocalService.addSignalement(signalement);
	}

	/**
	* Permet de creer un signalement sans le persister.
	*
	* @param sc le serviceContext
	* @return le signalement.
	* @throws PortalException l'exception.
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement createSignalement(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _signalementLocalService.createSignalement(sc);
	}

	/**
	* Creates a new signalement with the primary key. Does not add the signalement to the database.
	*
	* @param signalementId the primary key for the new signalement
	* @return the new signalement
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement createSignalement(
		long signalementId) {
		return _signalementLocalService.createSignalement(signalementId);
	}

	/**
	* Deletes the signalement from the database. Also notifies the appropriate model listeners.
	*
	* @param signalement the signalement
	* @return the signalement that was removed
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement deleteSignalement(
		eu.strasbourg.service.comment.model.Signalement signalement) {
		return _signalementLocalService.deleteSignalement(signalement);
	}

	/**
	* Deletes the signalement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signalementId the primary key of the signalement
	* @return the signalement that was removed
	* @throws PortalException if a signalement with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement deleteSignalement(
		long signalementId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _signalementLocalService.deleteSignalement(signalementId);
	}

	@Override
	public eu.strasbourg.service.comment.model.Signalement fetchSignalement(
		long signalementId) {
		return _signalementLocalService.fetchSignalement(signalementId);
	}

	/**
	* Returns the signalement matching the UUID and group.
	*
	* @param uuid the signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement fetchSignalementByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _signalementLocalService.fetchSignalementByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the signalement with the primary key.
	*
	* @param signalementId the primary key of the signalement
	* @return the signalement
	* @throws PortalException if a signalement with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement getSignalement(
		long signalementId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _signalementLocalService.getSignalement(signalementId);
	}

	/**
	* Returns the signalement matching the UUID and group.
	*
	* @param uuid the signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching signalement
	* @throws PortalException if a matching signalement could not be found
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement getSignalementByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _signalementLocalService.getSignalementByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the signalement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param signalement the signalement
	* @return the signalement that was updated
	*/
	@Override
	public eu.strasbourg.service.comment.model.Signalement updateSignalement(
		eu.strasbourg.service.comment.model.Signalement signalement) {
		return _signalementLocalService.updateSignalement(signalement);
	}

	@Override
	public eu.strasbourg.service.comment.model.Signalement updateSignalement(
		eu.strasbourg.service.comment.model.Signalement signalement,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _signalementLocalService.updateSignalement(signalement, sc);
	}

	/**
	* Returns the number of signalements.
	*
	* @return the number of signalements
	*/
	@Override
	public int getSignalementsCount() {
		return _signalementLocalService.getSignalementsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _signalementLocalService.getOSGiServiceIdentifier();
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
		return _signalementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.comment.model.impl.SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _signalementLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.comment.model.impl.SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _signalementLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Signalement> findByCommentId(
		long commentId) {
		return _signalementLocalService.findByCommentId(commentId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Signalement> getByGroupId(
		long groupId) {
		return _signalementLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the signalements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.comment.model.impl.SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @return the range of signalements
	*/
	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Signalement> getSignalements(
		int start, int end) {
		return _signalementLocalService.getSignalements(start, end);
	}

	/**
	* Returns all the signalements matching the UUID and company.
	*
	* @param uuid the UUID of the signalements
	* @param companyId the primary key of the company
	* @return the matching signalements, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Signalement> getSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _signalementLocalService.getSignalementsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of signalements matching the UUID and company.
	*
	* @param uuid the UUID of the signalements
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching signalements, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.comment.model.Signalement> getSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.comment.model.Signalement> orderByComparator) {
		return _signalementLocalService.getSignalementsByUuidAndCompanyId(uuid,
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
		return _signalementLocalService.dynamicQueryCount(dynamicQuery);
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
		return _signalementLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public SignalementLocalService getWrappedService() {
		return _signalementLocalService;
	}

	@Override
	public void setWrappedService(
		SignalementLocalService signalementLocalService) {
		_signalementLocalService = signalementLocalService;
	}

	private SignalementLocalService _signalementLocalService;
}