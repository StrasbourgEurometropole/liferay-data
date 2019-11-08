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
 * Provides a wrapper for {@link FormSendRecordFieldLocalService}.
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldLocalService
 * @generated
 */
@ProviderType
public class FormSendRecordFieldLocalServiceWrapper
	implements FormSendRecordFieldLocalService,
		ServiceWrapper<FormSendRecordFieldLocalService> {
	public FormSendRecordFieldLocalServiceWrapper(
		FormSendRecordFieldLocalService formSendRecordFieldLocalService) {
		_formSendRecordFieldLocalService = formSendRecordFieldLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _formSendRecordFieldLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _formSendRecordFieldLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _formSendRecordFieldLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _formSendRecordFieldLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the form send record field to the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordField the form send record field
	* @return the form send record field that was added
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField addFormSendRecordField(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordField formSendRecordField) {
		return _formSendRecordFieldLocalService.addFormSendRecordField(formSendRecordField);
	}

	/**
	* Crée un formSendRecordField vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField createFormSendRecordField(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.createFormSendRecordField(sc);
	}

	/**
	* Creates a new form send record field with the primary key. Does not add the form send record field to the database.
	*
	* @param formSendRecordFieldId the primary key for the new form send record field
	* @return the new form send record field
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField createFormSendRecordField(
		long formSendRecordFieldId) {
		return _formSendRecordFieldLocalService.createFormSendRecordField(formSendRecordFieldId);
	}

	/**
	* Deletes the form send record field from the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordField the form send record field
	* @return the form send record field that was removed
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField deleteFormSendRecordField(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordField formSendRecordField) {
		return _formSendRecordFieldLocalService.deleteFormSendRecordField(formSendRecordField);
	}

	/**
	* Deletes the form send record field with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldId the primary key of the form send record field
	* @return the form send record field that was removed
	* @throws PortalException if a form send record field with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField deleteFormSendRecordField(
		long formSendRecordFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.deleteFormSendRecordField(formSendRecordFieldId);
	}

	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField fetchFormSendRecordField(
		long formSendRecordFieldId) {
		return _formSendRecordFieldLocalService.fetchFormSendRecordField(formSendRecordFieldId);
	}

	/**
	* Returns the form send record field matching the UUID and group.
	*
	* @param uuid the form send record field's UUID
	* @param groupId the primary key of the group
	* @return the matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField fetchFormSendRecordFieldByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _formSendRecordFieldLocalService.fetchFormSendRecordFieldByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the form send record field with the primary key.
	*
	* @param formSendRecordFieldId the primary key of the form send record field
	* @return the form send record field
	* @throws PortalException if a form send record field with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField getFormSendRecordField(
		long formSendRecordFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.getFormSendRecordField(formSendRecordFieldId);
	}

	/**
	* Returns the form send record field matching the UUID and group.
	*
	* @param uuid the form send record field's UUID
	* @param groupId the primary key of the group
	* @return the matching form send record field
	* @throws PortalException if a matching form send record field could not be found
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField getFormSendRecordFieldByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.getFormSendRecordFieldByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime un lien
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField removeFormSendRecordField(
		long formSendRecordFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.removeFormSendRecordField(formSendRecordFieldId);
	}

	/**
	* Updates the form send record field in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordField the form send record field
	* @return the form send record field that was updated
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField updateFormSendRecordField(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordField formSendRecordField) {
		return _formSendRecordFieldLocalService.updateFormSendRecordField(formSendRecordField);
	}

	/**
	* Met à jour un formSendRecordField et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField updateFormSendRecordField(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordField formSendRecordField,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.updateFormSendRecordField(formSendRecordField,
			sc);
	}

	/**
	* Met à jour le statut du formSendRecordField par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.formSendRecordField.model.FormSendRecordField updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _formSendRecordFieldLocalService.updateStatus(userId, entryId,
			status, sc, workflowContext);
	}

	/**
	* Returns the number of form send record fields.
	*
	* @return the number of form send record fields
	*/
	@Override
	public int getFormSendRecordFieldsCount() {
		return _formSendRecordFieldLocalService.getFormSendRecordFieldsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _formSendRecordFieldLocalService.getOSGiServiceIdentifier();
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
		return _formSendRecordFieldLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _formSendRecordFieldLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _formSendRecordFieldLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Retourne tous les formSendRecordField d'un asset entry
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> getByContentAndInstanceId(
		long contentId, java.lang.String instanceId) {
		return _formSendRecordFieldLocalService.getByContentAndInstanceId(contentId,
			instanceId);
	}

	/**
	* Retourne tous les formSendRecordField d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> getByGroupId(
		long groupId) {
		return _formSendRecordFieldLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the form send record fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of form send record fields
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> getFormSendRecordFields(
		int start, int end) {
		return _formSendRecordFieldLocalService.getFormSendRecordFields(start,
			end);
	}

	/**
	* Returns all the form send record fields matching the UUID and company.
	*
	* @param uuid the UUID of the form send record fields
	* @param companyId the primary key of the company
	* @return the matching form send record fields, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> getFormSendRecordFieldsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _formSendRecordFieldLocalService.getFormSendRecordFieldsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of form send record fields matching the UUID and company.
	*
	* @param uuid the UUID of the form send record fields
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching form send record fields, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> getFormSendRecordFieldsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.formSendRecordField.model.FormSendRecordField> orderByComparator) {
		return _formSendRecordFieldLocalService.getFormSendRecordFieldsByUuidAndCompanyId(uuid,
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
		return _formSendRecordFieldLocalService.dynamicQueryCount(dynamicQuery);
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
		return _formSendRecordFieldLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Met à jour le statut du formSendRecordField "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.formSendRecordField.model.FormSendRecordField formSendRecordField,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_formSendRecordFieldLocalService.updateStatus(formSendRecordField,
			status);
	}

	@Override
	public FormSendRecordFieldLocalService getWrappedService() {
		return _formSendRecordFieldLocalService;
	}

	@Override
	public void setWrappedService(
		FormSendRecordFieldLocalService formSendRecordFieldLocalService) {
		_formSendRecordFieldLocalService = formSendRecordFieldLocalService;
	}

	private FormSendRecordFieldLocalService _formSendRecordFieldLocalService;
}