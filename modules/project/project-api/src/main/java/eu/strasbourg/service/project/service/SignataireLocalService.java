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
import eu.strasbourg.service.project.model.Signataire;

import javax.portlet.PortletException;
import java.io.Serializable;
import java.util.List;

/**
 * Provides the local service interface for Signataire. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see SignataireLocalServiceUtil
 * @see eu.strasbourg.service.project.service.base.SignataireLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.SignataireLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SignataireLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SignataireLocalServiceUtil} to access the signataire local service. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.SignataireLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the signataire to the database. Also notifies the appropriate model listeners.
	*
	* @param signataire the signataire
	* @return the signataire that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Signataire addSignataire(Signataire signataire);

	/**
	* méthode de creation de signataire.
	*
	* @param sc le context.
	* @return le signataire crée.
	*/
	public Signataire createSignataire(ServiceContext sc);

	/**
	* Creates a new signataire with the primary key. Does not add the signataire to the database.
	*
	* @param signataireId the primary key for the new signataire
	* @return the new signataire
	*/
	public Signataire createSignataire(long signataireId);

	/**
	* Deletes the signataire from the database. Also notifies the appropriate model listeners.
	*
	* @param signataire the signataire
	* @return the signataire that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Signataire deleteSignataire(Signataire signataire);

	/**
	* Deletes the signataire with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire that was removed
	* @throws PortalException if a signataire with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Signataire deleteSignataire(long signataireId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signataire fetchSignataire(long signataireId);

	/**
	* Returns the signataire matching the UUID and group.
	*
	* @param uuid the signataire's UUID
	* @param groupId the primary key of the group
	* @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signataire fetchSignataireByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the signataire with the primary key.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire
	* @throws PortalException if a signataire with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signataire getSignataire(long signataireId)
		throws PortalException;

	/**
	* Returns the signataire matching the UUID and group.
	*
	* @param uuid the signataire's UUID
	* @param groupId the primary key of the group
	* @return the matching signataire
	* @throws PortalException if a matching signataire could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Signataire getSignataireByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the signataire in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param signataire the signataire
	* @return the signataire that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Signataire updateSignataire(Signataire signataire);

	/**
	* méthode permettant de compter le nombre de faux signataire
	*
	* @param petitionId la pétition concernée
	* @return le nombre de signataires
	*/
	public int countFakeSignataireByPetition(long petitionId);

	/**
	* méthode permettant de récuperer les signataires par l'identifiant de la pétition.
	*
	* @param petitionId l'identifiant de la pétition.
	* @return la liste des signataires.
	*/
	public int countSignataireByPetitionId(long petitionId);

	/**
	* Returns the number of signataires.
	*
	* @return the number of signataires
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSignatairesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<Signataire> findSignatairesByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName)
		throws PortletException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signataire> getSignataires(int start, int end);

	/**
	* méthode permettant de récuperer les signataires par l'identifiant de la pétition.
	*
	* @param petitionId l'identifiant de la pétition.
	* @return la liste des signataires.
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signataire> getSignatairesByPetitionId(long petitionId);

	/**
	* Returns all the signataires matching the UUID and company.
	*
	* @param uuid the UUID of the signataires
	* @param companyId the primary key of the company
	* @return the matching signataires, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signataire> getSignatairesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Signataire> getSignatairesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<Signataire> orderByComparator);

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

	/**
	* méthode de création de faux signataires
	*
	* @param petitionId la pétition concernée
	* @param nombreCreation le nombre de creation souhaité.
	*/
	public void createFakeSignataire(long petitionId, int nombreCreation);
}