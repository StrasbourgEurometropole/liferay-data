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

package eu.strasbourg.service.activity.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.model.AssetVocabulary;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.activity.model.Association;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Association. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationLocalServiceUtil
 * @see eu.strasbourg.service.activity.service.base.AssociationLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.AssociationLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AssociationLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssociationLocalServiceUtil} to access the association local service. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.AssociationLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Lance une recherche selon le searchContext
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

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
	* Adds the association to the database. Also notifies the appropriate model listeners.
	*
	* @param association the association
	* @return the association that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Association addAssociation(Association association);

	/**
	* Crée une association vide avec une PK, non ajouté à la base de donnée
	*/
	public Association createAssociation(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new association with the primary key. Does not add the association to the database.
	*
	* @param associationId the primary key for the new association
	* @return the new association
	*/
	public Association createAssociation(long associationId);

	/**
	* Deletes the association from the database. Also notifies the appropriate model listeners.
	*
	* @param association the association
	* @return the association that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Association deleteAssociation(Association association);

	/**
	* Deletes the association with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param associationId the primary key of the association
	* @return the association that was removed
	* @throws PortalException if a association with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Association deleteAssociation(long associationId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Association fetchAssociation(long associationId);

	/**
	* Returns the association matching the UUID and group.
	*
	* @param uuid the association's UUID
	* @param groupId the primary key of the group
	* @return the matching association, or <code>null</code> if a matching association could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Association fetchAssociationByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the association with the primary key.
	*
	* @param associationId the primary key of the association
	* @return the association
	* @throws PortalException if a association with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Association getAssociation(long associationId)
		throws PortalException;

	/**
	* Returns the association matching the UUID and group.
	*
	* @param uuid the association's UUID
	* @param groupId the primary key of the group
	* @return the matching association
	* @throws PortalException if a matching association could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Association getAssociationByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Supprime une entité
	*/
	public Association removeAssociation(long associationId)
		throws PortalException;

	/**
	* Updates the association in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param association the association
	* @return the association that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Association updateAssociation(Association association);

	/**
	* Met à jour une association et l'enregistre en base de données
	*/
	public Association updateAssociation(Association association,
		ServiceContext sc) throws PortalException;

	/**
	* Met à jour le statut de l'association par le framework workflow
	*/
	public Association updateStatus(long userId, long entryId, int status)
		throws PortalException;

	/**
	* Returns the number of associations.
	*
	* @return the number of associations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssociationsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Lance une recherche par mots-clés
	*/
	public List<Association> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	/**
	* Returns a range of all the associations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of associations
	* @param end the upper bound of the range of associations (not inclusive)
	* @return the range of associations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Association> getAssociations(int start, int end);

	/**
	* Returns all the associations matching the UUID and company.
	*
	* @param uuid the UUID of the associations
	* @param companyId the primary key of the company
	* @return the matching associations, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Association> getAssociationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of associations matching the UUID and company.
	*
	* @param uuid the UUID of the associations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of associations
	* @param end the upper bound of the range of associations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching associations, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Association> getAssociationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<Association> orderByComparator);

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Association> getByGroupId(long groupId);

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
	* Compte de la recherche par mots-clés
	*/
	public long findByKeywordCount(java.lang.String keyword, long groupId);
}