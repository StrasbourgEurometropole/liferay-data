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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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

import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for FormSendRecordFieldSignalement. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementLocalServiceUtil
 * @see eu.strasbourg.service.formSendRecordField.service.base.FormSendRecordFieldSignalementLocalServiceBaseImpl
 * @see eu.strasbourg.service.formSendRecordField.service.impl.FormSendRecordFieldSignalementLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface FormSendRecordFieldSignalementLocalService
	extends BaseLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FormSendRecordFieldSignalementLocalServiceUtil} to access the form send record field signalement local service. Add custom service methods to {@link eu.strasbourg.service.formSendRecordField.service.impl.FormSendRecordFieldSignalementLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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
	* Adds the form send record field signalement to the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldSignalement the form send record field signalement
	* @return the form send record field signalement that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public FormSendRecordFieldSignalement addFormSendRecordFieldSignalement(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement);

	/**
	* Permet de creer un signalement sans le persister.
	*
	* @param sc le serviceContext
	* @return le signalement.
	* @throws PortalException l'exception.
	*/
	public FormSendRecordFieldSignalement createFormSendRecordFieldSignalement(
		ServiceContext sc) throws PortalException;

	/**
	* Creates a new form send record field signalement with the primary key. Does not add the form send record field signalement to the database.
	*
	* @param signalementId the primary key for the new form send record field signalement
	* @return the new form send record field signalement
	*/
	public FormSendRecordFieldSignalement createFormSendRecordFieldSignalement(
		long signalementId);

	/**
	* Deletes the form send record field signalement from the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldSignalement the form send record field signalement
	* @return the form send record field signalement that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public FormSendRecordFieldSignalement deleteFormSendRecordFieldSignalement(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement);

	/**
	* Deletes the form send record field signalement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signalementId the primary key of the form send record field signalement
	* @return the form send record field signalement that was removed
	* @throws PortalException if a form send record field signalement with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public FormSendRecordFieldSignalement deleteFormSendRecordFieldSignalement(
		long signalementId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FormSendRecordFieldSignalement fetchFormSendRecordFieldSignalement(
		long signalementId);

	/**
	* Returns the form send record field signalement matching the UUID and group.
	*
	* @param uuid the form send record field signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FormSendRecordFieldSignalement fetchFormSendRecordFieldSignalementByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the form send record field signalement with the primary key.
	*
	* @param signalementId the primary key of the form send record field signalement
	* @return the form send record field signalement
	* @throws PortalException if a form send record field signalement with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FormSendRecordFieldSignalement getFormSendRecordFieldSignalement(
		long signalementId) throws PortalException;

	/**
	* Returns the form send record field signalement matching the UUID and group.
	*
	* @param uuid the form send record field signalement's UUID
	* @param groupId the primary key of the group
	* @return the matching form send record field signalement
	* @throws PortalException if a matching form send record field signalement could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FormSendRecordFieldSignalement getFormSendRecordFieldSignalementByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the form send record field signalement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldSignalement the form send record field signalement
	* @return the form send record field signalement that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public FormSendRecordFieldSignalement updateFormSendRecordFieldSignalement(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement);

	public FormSendRecordFieldSignalement updateFormSendRecordFieldSignalement(
		FormSendRecordFieldSignalement signalement, ServiceContext sc)
		throws PortalException;

	/**
	* Returns the number of form send record field signalements.
	*
	* @return the number of form send record field signalements
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFormSendRecordFieldSignalementsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<FormSendRecordFieldSignalement> findByFormSendRecordFieldId(
		long formSendRecordFieldId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FormSendRecordFieldSignalement> getByGroupId(long groupId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FormSendRecordFieldSignalement> getFormSendRecordFieldSignalements(
		int start, int end);

	/**
	* Returns all the form send record field signalements matching the UUID and company.
	*
	* @param uuid the UUID of the form send record field signalements
	* @param companyId the primary key of the company
	* @return the matching form send record field signalements, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FormSendRecordFieldSignalement> getFormSendRecordFieldSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FormSendRecordFieldSignalement> getFormSendRecordFieldSignalementsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator);

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
}