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

import eu.strasbourg.service.project.model.Initiative;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Initiative. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see InitiativeLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface InitiativeLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InitiativeLocalServiceUtil} to access the initiative local service. Add custom service methods to <code>eu.strasbourg.service.project.service.impl.InitiativeLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the initiative to the database. Also notifies the appropriate model listeners.
	 *
	 * @param initiative the initiative
	 * @return the initiative that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Initiative addInitiative(Initiative initiative);

	/**
	 * Creates a new initiative with the primary key. Does not add the initiative to the database.
	 *
	 * @param initiativeId the primary key for the new initiative
	 * @return the new initiative
	 */
	@Transactional(enabled = false)
	public Initiative createInitiative(long initiativeId);

	/**
	 * Crée une initiative vide avec une PK, non ajouté à la base de donnée
	 */
	public Initiative createInitiative(ServiceContext sc)
		throws PortalException;

	/**
	 * Deletes the initiative from the database. Also notifies the appropriate model listeners.
	 *
	 * @param initiative the initiative
	 * @return the initiative that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Initiative deleteInitiative(Initiative initiative);

	/**
	 * Deletes the initiative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param initiativeId the primary key of the initiative
	 * @return the initiative that was removed
	 * @throws PortalException if a initiative with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Initiative deleteInitiative(long initiativeId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Initiative fetchInitiative(long initiativeId);

	/**
	 * Returns the initiative matching the UUID and group.
	 *
	 * @param uuid the initiative's UUID
	 * @param groupId the primary key of the group
	 * @return the matching initiative, or <code>null</code> if a matching initiative could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Initiative fetchInitiativeByUuidAndGroupId(
		String uuid, long groupId);

	/**
	 * Recherche par mot clés
	 */
	public List<Initiative> findByKeyword(
		String keyword, long groupId, int start, int end);

	/**
	 * Recherche par mot clés (compte)
	 */
	public long findByKeywordCount(String keyword, long groupId);

	public List<Initiative> findByPublikUserId(String publikUserId);

	public List<Initiative> findHelpedByPublikUserId(String publikUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Retourne toutes les initiatives d'un groupe
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getByPublikUserID(String publikId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the initiative with the primary key.
	 *
	 * @param initiativeId the primary key of the initiative
	 * @return the initiative
	 * @throws PortalException if a initiative with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Initiative getInitiative(long initiativeId) throws PortalException;

	/**
	 * Returns the initiative matching the UUID and group.
	 *
	 * @param uuid the initiative's UUID
	 * @param groupId the primary key of the group
	 * @return the matching initiative
	 * @throws PortalException if a matching initiative could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Initiative getInitiativeByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the initiatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of initiatives
	 * @param end the upper bound of the range of initiatives (not inclusive)
	 * @return the range of initiatives
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getInitiatives(int start, int end);

	/**
	 * Returns all the initiatives matching the UUID and company.
	 *
	 * @param uuid the UUID of the initiatives
	 * @param companyId the primary key of the company
	 * @return the matching initiatives, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getInitiativesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of initiatives matching the UUID and company.
	 *
	 * @param uuid the UUID of the initiatives
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of initiatives
	 * @param end the upper bound of the range of initiatives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching initiatives, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getInitiativesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Initiative> orderByComparator);

	/**
	 * Returns the number of initiatives.
	 *
	 * @return the number of initiatives
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getInitiativesCount();

	/**
	 * Recuperer le nombre voulu d'initiatives les plus commentes
	 *
	 * @param groupId ID du site
	 * @param delta Nombre de resultats max voulu
	 * @return Liste d'initiatives les plus commentes triee.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getMostCommented(long groupId, int delta);

	/**
	 * Recuperer le nombre voulu d'initiatives les plus soutenus
	 *
	 * @param groupId ID du site
	 * @param delta Nombre de resultats max voulu
	 * @return Liste d'initiatives les plus aidé triee.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getMostHelped(long groupId, int delta);

	/**
	 * Recuperer le nombre voulu d'initiatives les plus soutenus
	 *
	 * @param groupId ID du site
	 * @param delta Nombre de resultats max voulu
	 * @return Liste d'initiatives les plus aidé triee.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getMostLiked(long groupId, int delta);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Retourne toutes les initiatives publiées d'un groupe
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getPublishedByGroupId(long groupId);

	/**
	 * Methode permettant de recuperer une liste d'initiatives triee par nombre de commentaires
	 *
	 * @param groupId ID du site
	 * @return Liste des initiatives triee par nombre de commentaires
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getSortedByNbComments(long groupId);

	/**
	 * Methode permettant de recuperer une liste d'initiatives triee par nombre de soutiens
	 *
	 * @param groupId ID du site
	 * @return Liste des budgets participatifs triee par nombre de soutiens
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getSortedByNbHelps(long groupId);

	/**
	 * Methode permettant de recuperer une liste d'initiatives triee par nombre de soutiens
	 *
	 * @param groupId ID du site
	 * @return Liste d'initiatives triee par nombre de soutiens
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Initiative> getSortedByNbLikes(long groupId);

	/**
	 * Supprime une initiative
	 */
	public Initiative removeInitiative(long initiativeId)
		throws PortalException;

	/**
	 * Updates the initiative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param initiative the initiative
	 * @return the initiative that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Initiative updateInitiative(Initiative initiative);

	/**
	 * Met à jour une initiative et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	public Initiative updateInitiative(Initiative initiative, ServiceContext sc)
		throws PortalException;

	/**
	 * Met à jour le statut de l'initiative "manuellement" (pas via le workflow)
	 */
	public void updateStatus(Initiative initiative, int status)
		throws PortalException;

	/**
	 * Met à jour le statut de l'initiative par le framework workflow
	 */
	public Initiative updateStatus(
			long userId, long entryId, int status, ServiceContext sc,
			Map<String, Serializable> workflowContext)
		throws PortalException;

}