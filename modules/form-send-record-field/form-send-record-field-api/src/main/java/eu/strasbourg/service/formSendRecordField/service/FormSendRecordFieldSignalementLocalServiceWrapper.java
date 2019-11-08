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

package eu.strasbourg.service.formSendRecordField.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FormSendRecordFieldSignalementLocalService}.
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementLocalService
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSignalementLocalServiceWrapper
	implements FormSendRecordFieldSignalementLocalService,
		ServiceWrapper<FormSendRecordFieldSignalementLocalService> {
	public FormSendRecordFieldSignalementLocalServiceWrapper(
		FormSendRecordFieldSignalementLocalService formSendRecordFieldSignalementLocalService) {
		_formSendRecordFieldSignalementLocalService = formSendRecordFieldSignalementLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _formSendRecordFieldSignalementLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _formSendRecordFieldSignalementLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _formSendRecordFieldSignalementLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _formSendRecordFieldSignalementLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldSignalementLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldSignalementLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the form send record field signalement to the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldSignalement the form send record field signalement
	* @return the form send record field signalement that was added
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement addFormSendRecordFieldSignalement(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		return _formSendRecordFieldSignalementLocalService.addFormSendRecordFieldSignalement(formSendRecordFieldSignalement);
	}

	/**
	* Permet de creer un signalement sans le persister.
	*
	* @param sc le serviceContext
	* @return le signalement.
	* @throws PortalException l'exception.
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement createFormSendRecordFieldSignalement(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldSignalementLocalService.createFormSendRecordFieldSignalement(sc);
	}

	/**
	* Creates a new form send record field signalement with the primary key. Does not add the form send record field signalement to the database.
	*
	* @param signalementId the primary key for the new form send record field signalement
	* @return the new form send record field signalement
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement createFormSendRecordFieldSignalement(
		long signalementId) {
		return _formSendRecordFieldSignalementLocalService.createFormSendRecordFieldSignalement(signalementId);
	}

	/**
	* Deletes the form send record field signalement from the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldSignalement the form send record field signalement
	* @return the form send record field signalement that was removed
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement deleteFormSendRecordFieldSignalement(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		return _formSendRecordFieldSignalementLocalService.deleteFormSendRecordFieldSignalement(formSendRecordFieldSignalement);
	}

	/**
	* Deletes the form send record field signalement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signalementId the primary key of the form send record field signalement
	* @return the form send record field signalement that was removed
	* @throws PortalException if a form send record field signalement with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement deleteFormSendRecordFieldSignalement(
		long signalementId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldSignalementLocalService.deleteFormSendRecordFieldSignalement(signalementId);
	}

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement fetchFormSendRecordFieldSignalement(
		long signalementId) {
		return _formSendRecordFieldSignalementLocalService.fetchFormSendRecordFieldSignalement(signalementId);
	}

	/**
	* Returns the form send record field signalement matching the UUID and group.
	*
	* @param uuid the form send record field signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement fetchFormSendRecordFieldSignalementByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _formSendRecordFieldSignalementLocalService.fetchFormSendRecordFieldSignalementByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the form send record field signalement with the primary key.
	*
	* @param signalementId the primary key of the form send record field signalement
	* @return the form send record field signalement
	* @throws PortalException if a form send record field signalement with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement getFormSendRecordFieldSignalement(
		long signalementId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldSignalementLocalService.getFormSendRecordFieldSignalement(signalementId);
	}

	/**
	* Returns the form send record field signalement matching the UUID and group.
	*
	* @param uuid the form send record field signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching form send record field signalement
	* @throws PortalException if a matching form send record field signalement could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement getFormSendRecordFieldSignalementByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldSignalementLocalService.getFormSendRecordFieldSignalementByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the form send record field signalement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldSignalement the form send record field signalement
	* @return the form send record field signalement that was updated
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement updateFormSendRecordFieldSignalement(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		return _formSendRecordFieldSignalementLocalService.updateFormSendRecordFieldSignalement(formSendRecordFieldSignalement);
	}

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement updateFormSendRecordFieldSignalement(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement signalement,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldSignalementLocalService.updateFormSendRecordFieldSignalement(signalement,
			sc);
	}

	/**
	* Returns the number of form send record field signalements.
	*
	* @return the number of form send record field signalements
	*/
	@Override
	public int getFormSendRecordFieldSignalementsCount() {
		return _formSendRecordFieldSignalementLocalService.getFormSendRecordFieldSignalementsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _formSendRecordFieldSignalementLocalService.getOSGiServiceIdentifier();
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
		return _formSendRecordFieldSignalementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _formSendRecordFieldSignalementLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _formSendRecordFieldSignalementLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> findByFormSendRecordFieldId(
		long formSendRecordFieldId) {
		return _formSendRecordFieldSignalementLocalService.findByFormSendRecordFieldId(formSendRecordFieldId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> getByGroupId(
		long groupId) {
		return _formSendRecordFieldSignalementLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the form send record field signalements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of form send record field signalements
	* @param end the upper bound of the range of form send record field signalements (not inclusive)
	* @return the range of form send record field signalements
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> getFormSendRecordFieldSignalements(
		int start, int end) {
		return _formSendRecordFieldSignalementLocalService.getFormSendRecordFieldSignalements(start,
			end);
	}

	/**
	* Returns all the form send record field signalements matching the UUID and company.
	*
	* @param uuid the UUID of the form send record field signalements
	* @param companyId the primary key of the company
	* @return the matching form send record field signalements, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> getFormSendRecordFieldSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _formSendRecordFieldSignalementLocalService.getFormSendRecordFieldSignalementsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of form send record field signalements matching the UUID and company.
	*
	* @param uuid the UUID of the form send record field signalements
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of form send record field signalements
	* @param end the upper bound of the range of form send record field signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching form send record field signalements, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> getFormSendRecordFieldSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement> orderByComparator) {
		return _formSendRecordFieldSignalementLocalService.getFormSendRecordFieldSignalementsByUuidAndCompanyId(uuid,
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
		return _formSendRecordFieldSignalementLocalService.dynamicQueryCount(dynamicQuery);
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
		return _formSendRecordFieldSignalementLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public FormSendRecordFieldSignalementLocalService getWrappedService() {
		return _formSendRecordFieldSignalementLocalService;
	}

	@Override
	public void setWrappedService(
		FormSendRecordFieldSignalementLocalService formSendRecordFieldSignalementLocalService) {
		_formSendRecordFieldSignalementLocalService = formSendRecordFieldSignalementLocalService;
	}

	private FormSendRecordFieldSignalementLocalService _formSendRecordFieldSignalementLocalService;
}