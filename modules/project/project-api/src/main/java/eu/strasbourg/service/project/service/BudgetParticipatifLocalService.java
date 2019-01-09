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

import eu.strasbourg.service.project.model.BudgetParticipatif;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for BudgetParticipatif. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see BudgetParticipatifLocalServiceUtil
 * @see eu.strasbourg.service.project.service.base.BudgetParticipatifLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.BudgetParticipatifLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface BudgetParticipatifLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BudgetParticipatifLocalServiceUtil} to access the budget participatif local service. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.BudgetParticipatifLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the budget participatif to the database. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatif the budget participatif
	* @return the budget participatif that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public BudgetParticipatif addBudgetParticipatif(
		BudgetParticipatif budgetParticipatif);

	/**
	* Crée une participation vide avec une PK, non ajouté à la base de donnée
	*/
	public BudgetParticipatif createBudgetParticipatif(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new budget participatif with the primary key. Does not add the budget participatif to the database.
	*
	* @param budgetParticipatifId the primary key for the new budget participatif
	* @return the new budget participatif
	*/
	public BudgetParticipatif createBudgetParticipatif(
		long budgetParticipatifId);

	/**
	* Deletes the budget participatif from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatif the budget participatif
	* @return the budget participatif that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public BudgetParticipatif deleteBudgetParticipatif(
		BudgetParticipatif budgetParticipatif);

	/**
	* Deletes the budget participatif with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatifId the primary key of the budget participatif
	* @return the budget participatif that was removed
	* @throws PortalException if a budget participatif with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public BudgetParticipatif deleteBudgetParticipatif(
		long budgetParticipatifId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BudgetParticipatif fetchBudgetParticipatif(long budgetParticipatifId);

	/**
	* Returns the budget participatif matching the UUID and group.
	*
	* @param uuid the budget participatif's UUID
	* @param groupId the primary key of the group
	* @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BudgetParticipatif fetchBudgetParticipatifByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the budget participatif with the primary key.
	*
	* @param budgetParticipatifId the primary key of the budget participatif
	* @return the budget participatif
	* @throws PortalException if a budget participatif with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BudgetParticipatif getBudgetParticipatif(long budgetParticipatifId)
		throws PortalException;

	/**
	* Returns the budget participatif matching the UUID and group.
	*
	* @param uuid the budget participatif's UUID
	* @param groupId the primary key of the group
	* @return the matching budget participatif
	* @throws PortalException if a matching budget participatif could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BudgetParticipatif getBudgetParticipatifByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Méthode de mise à jour d'un budget
	*
	* @param budget le budget
	* @param sc     le service context
	* @return le budget
	* @throws PortalException exception
	*/
	public BudgetParticipatif updateBudgetParticipatif(
		BudgetParticipatif budget, ServiceContext sc) throws PortalException;

	/**
	* Updates the budget participatif in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatif the budget participatif
	* @return the budget participatif that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public BudgetParticipatif updateBudgetParticipatif(
		BudgetParticipatif budgetParticipatif);

	/**
	* mise a jour du status
	*
	* @param userId               l'identifiant de l'utilisateur
	* @param budgetParticipatifId l'identifiant du budget
	* @param status               le status
	* @param serviceContext       le service context
	* @param workflowContext      le context du workflow
	* @return le budget
	* @throws PortalException
	*/
	public BudgetParticipatif updateStatus(long userId,
		long budgetParticipatifId, int status, ServiceContext serviceContext,
		Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Retourne le nombre de budgets participatifs suivis par un utilisateur et une phase donnes
	*/
	public int countBudgetSupportedByPublikUserInPhase(
		java.lang.String publikUserId, long budgetPhaseId);

	/**
	* Returns the number of budget participatifs.
	*
	* @return the number of budget participatifs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBudgetParticipatifsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Recherche par mot clés
	*/
	public List<BudgetParticipatif> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getBudgetParticipatifByPublikUserID(
		java.lang.String publikId);

	/**
	* Returns a range of all the budget participatifs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of budget participatifs
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getBudgetParticipatifs(int start, int end);

	/**
	* Returns all the budget participatifs matching the UUID and company.
	*
	* @param uuid the UUID of the budget participatifs
	* @param companyId the primary key of the company
	* @return the matching budget participatifs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getBudgetParticipatifsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of budget participatifs matching the UUID and company.
	*
	* @param uuid the UUID of the budget participatifs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching budget participatifs, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getBudgetParticipatifsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Retourne tous les budgets participatifs suivis par un utilisateur et une phase donnes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getBudgetSupportedByPublikUserInPhase(
		java.lang.String publikUserId, long budgetPhaseId);

	/**
	* Retourne tous les budgets participatifs d'une phase donnee
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getByBudgetPhase(long budgetPhaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getByPublikUserID(java.lang.String publikId);

	/**
	* Recuperer le nombre voulu des budgets participatifs les plus commentes
	*
	* @param groupId ID du site
	* @param delta Nombre de resultats max voulu
	* @return Liste des budgets participatifs les plus commentes triee.
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getMostCommented(long groupId, int delta);

	/**
	* Recuperer le nombre voulu des budgets participatifs les plus soutenus
	*
	* @param groupId ID du site
	* @param delta Nombre de resultats max voulu
	* @return Liste des budgets participatifs les plus soutenus triee.
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getMostSupported(long groupId, int delta);

	/**
	* Retourne les budgets votes par en utilisateur pour la phase en cours en ne prenant pas en compte les doublons
	* et les brouillons
	*
	* @param publikUserId
	* @param budgetPhaseId
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getPublishedAndVotedByPublikUserInPhase(
		java.lang.String publikUserId, long budgetPhaseId);

	/**
	* Retourne tous les budgets participatifs publies d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getPublishedByGroupId(long groupId);

	/**
	* Recuperer les budgets participatifs "coup de coeur" les plus recents
	*
	* @param groupId ID du site
	* @param delta Nombre de resultats max voulu
	* @return Liste des budgets participatifs coup de coeurs recent
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getRecentIsCrushed(long groupId, int delta);

	/**
	* Methode permettant de recuperer une liste de budgets participatifs trie par nombre de commentaires
	*
	* @param groupId ID du site
	* @return Liste des budgets participatifs triee par nombre de commentaires
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getSortedByNbComments(long groupId);

	/**
	* Methode permettant de recuperer une liste de budgets participatifs trie par nombre de soutiens
	*
	* @param groupId ID du site
	* @return Liste des budgets participatifs triee par nombre de soutiens
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BudgetParticipatif> getSortedByNbSupports(long groupId);

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
	* Recherche par mot clés (compte)
	*/
	public long findByKeywordCount(java.lang.String keyword, long groupId);

	public void removeBudgetParticipatif(long budgetId)
		throws PortalException;

	/**
	* Met à jour le statut du budgetParticipatif "manuellement" (pas via le workflow)
	*/
	public void updateStatus(BudgetParticipatif budgetParticipatif, int status)
		throws PortalException;
}