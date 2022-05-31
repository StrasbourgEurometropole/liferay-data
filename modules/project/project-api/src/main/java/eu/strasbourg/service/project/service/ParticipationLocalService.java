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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.project.model.Participation;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Participation. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see ParticipationLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ParticipationLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ParticipationLocalServiceUtil} to access the participation local service. Add custom service methods to <code>eu.strasbourg.service.project.service.impl.ParticipationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the participation to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParticipationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param participation the participation
	 * @return the participation that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Participation addParticipation(Participation participation);

	/**
	 * Creates a new participation with the primary key. Does not add the participation to the database.
	 *
	 * @param participationId the primary key for the new participation
	 * @return the new participation
	 */
	@Transactional(enabled = false)
	public Participation createParticipation(long participationId);

	/**
	 * Crée une participation vide avec une PK, non ajouté à la base de donnée
	 */
	public Participation createParticipation(ServiceContext sc)
		throws PortalException;

	/**
	 * Deletes the participation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParticipationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param participationId the primary key of the participation
	 * @return the participation that was removed
	 * @throws PortalException if a participation with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Participation deleteParticipation(long participationId)
		throws PortalException;

	/**
	 * Deletes the participation from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParticipationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param participation the participation
	 * @return the participation that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Participation deleteParticipation(Participation participation);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.ParticipationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.ParticipationModelImpl</code>.
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
	public Participation fetchParticipation(long participationId);

	/**
	 * Returns the participation matching the UUID and group.
	 *
	 * @param uuid the participation's UUID
	 * @param groupId the primary key of the group
	 * @return the matching participation, or <code>null</code> if a matching participation could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Participation fetchParticipationByUuidAndGroupId(
		String uuid, long groupId);

	/**
	 * Recherche par mot clés
	 */
	public List<Participation> findByKeyword(
		String keyword, long groupId, int start, int end);

	/**
	 * Recherche par mot clés (compte)
	 */
	public long findByKeywordCount(String keyword, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Renvoie la liste des vocabulaires rattachés à une participation
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	 * Retourne toutes les participations d'un groupe
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Participation> getByGroupId(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * méthode permettant de récupérer les 3 dernieres participations de la liste.
	 *
	 * @param groupId le grouptId
	 * @return la liste de participation.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Participation> getLessCommented(long groupId);

	/**
	 * méthode permettant de récupérer les 3 premières participations de la liste.
	 *
	 * @param groupId le grouptId
	 * @return la liste de participation.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Participation> getMostCommented(long groupId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * Returns the participation with the primary key.
	 *
	 * @param participationId the primary key of the participation
	 * @return the participation
	 * @throws PortalException if a participation with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Participation getParticipation(long participationId)
		throws PortalException;

	/**
	 * Returns the participation matching the UUID and group.
	 *
	 * @param uuid the participation's UUID
	 * @param groupId the primary key of the group
	 * @return the matching participation
	 * @throws PortalException if a matching participation could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Participation getParticipationByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the participations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @return the range of participations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Participation> getParticipations(int start, int end);

	/**
	 * Returns all the participations matching the UUID and company.
	 *
	 * @param uuid the UUID of the participations
	 * @param companyId the primary key of the company
	 * @return the matching participations, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Participation> getParticipationsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of participations matching the UUID and company.
	 *
	 * @param uuid the UUID of the participations
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching participations, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Participation> getParticipationsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Participation> orderByComparator);

	/**
	 * Returns the number of participations.
	 *
	 * @return the number of participations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getParticipationsCount();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Retourne toutes les participation publiees d'un groupe
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Participation> getPublishedByGroupId(long groupId);

	/**
	 * Supprime une participation
	 */
	public Participation removeParticipation(long participationId)
		throws PortalException;

	/**
	 * Met a jour le statut de toutes les participations
	 *
	 * @throws PortalException
	 */
	public void updateAllParticipationsStatus() throws PortalException;

	/**
	 * Updates the participation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ParticipationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param participation the participation
	 * @return the participation that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Participation updateParticipation(Participation participation);

	/**
	 * Met à jour une participation et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	public Participation updateParticipation(
			Participation participation, ServiceContext sc)
		throws PortalException;

	/**
	 * Met à jour le statut de la participation par le framework workflow
	 */
	public Participation updateStatus(
			long userId, long entryId, int status, ServiceContext sc,
			Map<String, Serializable> workflowContext)
		throws PortalException;

	/**
	 * Met à jour le statut de la participation "manuellement" (pas via le workflow)
	 */
	public void updateStatus(Participation participation, int status)
		throws PortalException;

}