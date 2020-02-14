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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for FormSendRecordFieldSignalement. This utility wraps
 * <code>eu.strasbourg.service.formSendRecordField.service.impl.FormSendRecordFieldSignalementLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementLocalService
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSignalementLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.formSendRecordField.service.impl.FormSendRecordFieldSignalementLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the form send record field signalement to the database. Also notifies the appropriate model listeners.
	 *
	 * @param formSendRecordFieldSignalement the form send record field signalement
	 * @return the form send record field signalement that was added
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement addFormSendRecordFieldSignalement(
			eu.strasbourg.service.formSendRecordField.model.
				FormSendRecordFieldSignalement formSendRecordFieldSignalement) {

		return getService().addFormSendRecordFieldSignalement(
			formSendRecordFieldSignalement);
	}

	/**
	 * Creates a new form send record field signalement with the primary key. Does not add the form send record field signalement to the database.
	 *
	 * @param signalementId the primary key for the new form send record field signalement
	 * @return the new form send record field signalement
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement createFormSendRecordFieldSignalement(
			long signalementId) {

		return getService().createFormSendRecordFieldSignalement(signalementId);
	}

	/**
	 * Permet de creer un signalement sans le persister.
	 *
	 * @param sc le serviceContext
	 * @return le signalement.
	 * @throws PortalException l'exception.
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement createFormSendRecordFieldSignalement(
				com.liferay.portal.kernel.service.ServiceContext sc)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createFormSendRecordFieldSignalement(sc);
	}

	/**
	 * Deletes the form send record field signalement from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formSendRecordFieldSignalement the form send record field signalement
	 * @return the form send record field signalement that was removed
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement deleteFormSendRecordFieldSignalement(
			eu.strasbourg.service.formSendRecordField.model.
				FormSendRecordFieldSignalement formSendRecordFieldSignalement) {

		return getService().deleteFormSendRecordFieldSignalement(
			formSendRecordFieldSignalement);
	}

	/**
	 * Deletes the form send record field signalement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement that was removed
	 * @throws PortalException if a form send record field signalement with the primary key could not be found
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement deleteFormSendRecordFieldSignalement(
				long signalementId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteFormSendRecordFieldSignalement(signalementId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement fetchFormSendRecordFieldSignalement(
			long signalementId) {

		return getService().fetchFormSendRecordFieldSignalement(signalementId);
	}

	/**
	 * Returns the form send record field signalement matching the UUID and group.
	 *
	 * @param uuid the form send record field signalement's UUID
	 * @param groupId the primary key of the group
	 * @return the matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement
			fetchFormSendRecordFieldSignalementByUuidAndGroupId(
				String uuid, long groupId) {

		return getService().fetchFormSendRecordFieldSignalementByUuidAndGroupId(
			uuid, groupId);
	}

	public static java.util.List
		<eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement> findByFormSendRecordFieldId(
				long formSendRecordFieldId) {

		return getService().findByFormSendRecordFieldId(formSendRecordFieldId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement> getByGroupId(long groupId) {

		return getService().getByGroupId(groupId);
	}

	/**
	 * Retourne tous les signalementsd'une réponse à un formulaire d'un utilisateur
	 */
	public static java.util.List
		<eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement> getByPublikId(String publikId) {

		return getService().getByPublikId(publikId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	 * Returns the form send record field signalement with the primary key.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement
	 * @throws PortalException if a form send record field signalement with the primary key could not be found
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement getFormSendRecordFieldSignalement(
				long signalementId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFormSendRecordFieldSignalement(signalementId);
	}

	/**
	 * Returns the form send record field signalement matching the UUID and group.
	 *
	 * @param uuid the form send record field signalement's UUID
	 * @param groupId the primary key of the group
	 * @return the matching form send record field signalement
	 * @throws PortalException if a matching form send record field signalement could not be found
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement
				getFormSendRecordFieldSignalementByUuidAndGroupId(
					String uuid, long groupId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getFormSendRecordFieldSignalementByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the form send record field signalements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of form send record field signalements
	 */
	public static java.util.List
		<eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement> getFormSendRecordFieldSignalements(
				int start, int end) {

		return getService().getFormSendRecordFieldSignalements(start, end);
	}

	/**
	 * Returns all the form send record field signalements matching the UUID and company.
	 *
	 * @param uuid the UUID of the form send record field signalements
	 * @param companyId the primary key of the company
	 * @return the matching form send record field signalements, or an empty list if no matches were found
	 */
	public static java.util.List
		<eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement>
				getFormSendRecordFieldSignalementsByUuidAndCompanyId(
					String uuid, long companyId) {

		return getService().
			getFormSendRecordFieldSignalementsByUuidAndCompanyId(
				uuid, companyId);
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
	public static java.util.List
		<eu.strasbourg.service.formSendRecordField.model.
			FormSendRecordFieldSignalement>
				getFormSendRecordFieldSignalementsByUuidAndCompanyId(
					String uuid, long companyId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<eu.strasbourg.service.formSendRecordField.model.
							FormSendRecordFieldSignalement> orderByComparator) {

		return getService().
			getFormSendRecordFieldSignalementsByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of form send record field signalements.
	 *
	 * @return the number of form send record field signalements
	 */
	public static int getFormSendRecordFieldSignalementsCount() {
		return getService().getFormSendRecordFieldSignalementsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the form send record field signalement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param formSendRecordFieldSignalement the form send record field signalement
	 * @return the form send record field signalement that was updated
	 */
	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement updateFormSendRecordFieldSignalement(
			eu.strasbourg.service.formSendRecordField.model.
				FormSendRecordFieldSignalement formSendRecordFieldSignalement) {

		return getService().updateFormSendRecordFieldSignalement(
			formSendRecordFieldSignalement);
	}

	public static eu.strasbourg.service.formSendRecordField.model.
		FormSendRecordFieldSignalement updateFormSendRecordFieldSignalement(
				eu.strasbourg.service.formSendRecordField.model.
					FormSendRecordFieldSignalement signalement,
				com.liferay.portal.kernel.service.ServiceContext sc)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateFormSendRecordFieldSignalement(
			signalement, sc);
	}

	public static FormSendRecordFieldSignalementLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<FormSendRecordFieldSignalementLocalService,
		 FormSendRecordFieldSignalementLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			FormSendRecordFieldSignalementLocalService.class);

		ServiceTracker
			<FormSendRecordFieldSignalementLocalService,
			 FormSendRecordFieldSignalementLocalService> serviceTracker =
				new ServiceTracker
					<FormSendRecordFieldSignalementLocalService,
					 FormSendRecordFieldSignalementLocalService>(
						 bundle.getBundleContext(),
						 FormSendRecordFieldSignalementLocalService.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}