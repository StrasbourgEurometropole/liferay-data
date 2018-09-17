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
 * Provides the local service utility for Signataire. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.SignataireLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see SignataireLocalService
 * @see eu.strasbourg.service.project.service.base.SignataireLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.SignataireLocalServiceImpl
 * @generated
 */
@ProviderType
public class SignataireLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.SignataireLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
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
	* Adds the signataire to the database. Also notifies the appropriate model listeners.
	*
	* @param signataire the signataire
	* @return the signataire that was added
	*/
	public static eu.strasbourg.service.project.model.Signataire addSignataire(
		eu.strasbourg.service.project.model.Signataire signataire) {
		return getService().addSignataire(signataire);
	}

	/**
	* méthode de creation de signataire.
	*
	* @param sc le context.
	* @return le signataire crée.
	*/
	public static eu.strasbourg.service.project.model.Signataire createSignataire(
		com.liferay.portal.kernel.service.ServiceContext sc) {
		return getService().createSignataire(sc);
	}

	/**
	* Creates a new signataire with the primary key. Does not add the signataire to the database.
	*
	* @param signataireId the primary key for the new signataire
	* @return the new signataire
	*/
	public static eu.strasbourg.service.project.model.Signataire createSignataire(
		long signataireId) {
		return getService().createSignataire(signataireId);
	}

	/**
	* Deletes the signataire from the database. Also notifies the appropriate model listeners.
	*
	* @param signataire the signataire
	* @return the signataire that was removed
	*/
	public static eu.strasbourg.service.project.model.Signataire deleteSignataire(
		eu.strasbourg.service.project.model.Signataire signataire) {
		return getService().deleteSignataire(signataire);
	}

	/**
	* Deletes the signataire with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire that was removed
	* @throws PortalException if a signataire with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Signataire deleteSignataire(
		long signataireId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSignataire(signataireId);
	}

	public static eu.strasbourg.service.project.model.Signataire fetchSignataire(
		long signataireId) {
		return getService().fetchSignataire(signataireId);
	}

	/**
	* Returns the signataire matching the UUID and group.
	*
	* @param uuid the signataire's UUID
	* @param groupId the primary key of the group
	* @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static eu.strasbourg.service.project.model.Signataire fetchSignataireByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchSignataireByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the signataire with the primary key.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire
	* @throws PortalException if a signataire with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Signataire getSignataire(
		long signataireId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSignataire(signataireId);
	}

	/**
	* Returns the signataire matching the UUID and group.
	*
	* @param uuid the signataire's UUID
	* @param groupId the primary key of the group
	* @return the matching signataire
	* @throws PortalException if a matching signataire could not be found
	*/
	public static eu.strasbourg.service.project.model.Signataire getSignataireByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSignataireByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the signataire in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param signataire the signataire
	* @return the signataire that was updated
	*/
	public static eu.strasbourg.service.project.model.Signataire updateSignataire(
		eu.strasbourg.service.project.model.Signataire signataire) {
		return getService().updateSignataire(signataire);
	}

	/**
	* méthode permettant de compter le nombre de faux signataire
	*
	* @param petitionId la pétition concernée
	* @return le nombre de signataires
	*/
	public static int countFakeSignataireByPetition(long petitionId) {
		return getService().countFakeSignataireByPetition(petitionId);
	}

	/**
	* méthode permettant de récuperer les signataires par l'identifiant de la pétition.
	*
	* @param petitionId l'identifiant de la pétition.
	* @return la liste des signataires.
	*/
	public static int countSignataireByPetitionId(long petitionId) {
		return getService().countSignataireByPetitionId(petitionId);
	}

	/**
	* Returns the number of signataires.
	*
	* @return the number of signataires
	*/
	public static int getSignatairesCount() {
		return getService().getSignatairesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<eu.strasbourg.service.project.model.Signataire> findSignatairesByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName)
		throws javax.portlet.PortletException {
		return getService()
				   .findSignatairesByPetitionIdAndSignataireName(petitionId,
			signataireName);
	}

	/**
	* Returns a range of all the signataires.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of signataires
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Signataire> getSignataires(
		int start, int end) {
		return getService().getSignataires(start, end);
	}

	/**
	* méthode permettant de récuperer les signataires par l'identifiant de la pétition.
	*
	* @param petitionId l'identifiant de la pétition.
	* @return la liste des signataires.
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Signataire> getSignatairesByPetitionId(
		long petitionId) {
		return getService().getSignatairesByPetitionId(petitionId);
	}

	/**
	* Returns all the signataires matching the UUID and company.
	*
	* @param uuid the UUID of the signataires
	* @param companyId the primary key of the company
	* @return the matching signataires, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Signataire> getSignatairesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getSignatairesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of signataires matching the UUID and company.
	*
	* @param uuid the UUID of the signataires
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching signataires, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Signataire> getSignatairesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Signataire> orderByComparator) {
		return getService()
				   .getSignatairesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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
	* méthode de création de faux signataires
	*
	* @param petitionId la pétition concernée
	* @param nombreCreation le nombre de creation souhaité.
	*/
	public static void createFakeSignataire(long petitionId, int nombreCreation) {
		getService().createFakeSignataire(petitionId, nombreCreation);
	}

	public static void removeSignataire(long signataireId) {
		getService().removeSignataire(signataireId);
	}

	public static SignataireLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SignataireLocalService, SignataireLocalService> _serviceTracker =
		ServiceTrackerFactory.open(SignataireLocalService.class);
}