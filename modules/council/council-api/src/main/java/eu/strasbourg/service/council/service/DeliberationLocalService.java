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

package eu.strasbourg.service.council.service;

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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.council.model.Deliberation;

import java.io.Serializable;

import java.util.*;
import java.util.List;

/**
 * Provides the local service interface for Deliberation. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see DeliberationLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DeliberationLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeliberationLocalServiceUtil} to access the deliberation local service. Add custom service methods to <code>eu.strasbourg.service.council.service.impl.DeliberationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the deliberation to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DeliberationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deliberation the deliberation
	 * @return the deliberation that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Deliberation addDeliberation(Deliberation deliberation);

	/**
	 * Creates a new deliberation with the primary key. Does not add the deliberation to the database.
	 *
	 * @param deliberationId the primary key for the new deliberation
	 * @return the new deliberation
	 */
	@Transactional(enabled = false)
	public Deliberation createDeliberation(long deliberationId);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	public Deliberation createDeliberation(ServiceContext sc)
		throws PortalException;

	/**
	 * Deletes the deliberation from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DeliberationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deliberation the deliberation
	 * @return the deliberation that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Deliberation deleteDeliberation(Deliberation deliberation);

	/**
	 * Deletes the deliberation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DeliberationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deliberationId the primary key of the deliberation
	 * @return the deliberation that was removed
	 * @throws PortalException if a deliberation with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Deliberation deleteDeliberation(long deliberationId)
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.DeliberationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.DeliberationModelImpl</code>.
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
	public Deliberation fetchDeliberation(long deliberationId);

	/**
	 * Returns the deliberation matching the UUID and group.
	 *
	 * @param uuid the deliberation's UUID
	 * @param groupId the primary key of the group
	 * @return the matching deliberation, or <code>null</code> if a matching deliberation could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliberation fetchDeliberationByUuidAndGroupId(
		String uuid, long groupId);

	/**
	 * Recherche par ID de CouncilSession
	 */
	public List<Deliberation> findByCouncilSessionId(long councilSessionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the deliberation with the primary key.
	 *
	 * @param deliberationId the primary key of the deliberation
	 * @return the deliberation
	 * @throws PortalException if a deliberation with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliberation getDeliberation(long deliberationId)
		throws PortalException;

	/**
	 * Returns the deliberation matching the UUID and group.
	 *
	 * @param uuid the deliberation's UUID
	 * @param groupId the primary key of the group
	 * @return the matching deliberation
	 * @throws PortalException if a matching deliberation could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Deliberation getDeliberationByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the deliberations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.DeliberationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @return the range of deliberations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliberation> getDeliberations(int start, int end);

	/**
	 * Returns all the deliberations matching the UUID and company.
	 *
	 * @param uuid the UUID of the deliberations
	 * @param companyId the primary key of the company
	 * @return the matching deliberations, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliberation> getDeliberationsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of deliberations matching the UUID and company.
	 *
	 * @param uuid the UUID of the deliberations
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of deliberations
	 * @param end the upper bound of the range of deliberations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching deliberations, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Deliberation> getDeliberationsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Deliberation> orderByComparator);

	/**
	 * Returns the number of deliberations.
	 *
	 * @return the number of deliberations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDeliberationsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Import des délibérations à partir du fichier csv
	 * Création ou mise à jour ou suppression en base de données
	 */
	public String importData(
			List<java.util.Map<String, String>> recordsMapList,
			ServiceContext serviceContext, long councilSessionId,
			ThemeDisplay themeDisplay)
		throws PortalException;

	/**
	 * Supprime une entité
	 */
	public Deliberation removeDeliberation(long deliberationId)
		throws PortalException;

	/**
	 * Updates the deliberation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect DeliberationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param deliberation the deliberation
	 * @return the deliberation that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Deliberation updateDeliberation(Deliberation deliberation);

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	public Deliberation updateDeliberation(
			Deliberation deliberation, ServiceContext sc)
		throws PortalException;

	/**
	 * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	 */
	public void updateStatus(Deliberation deliberation, int status)
		throws PortalException;

	/**
	 * Met à jour le statut de l'entité par le framework workflow
	 */
	public Deliberation updateStatus(
			long userId, long entryId, int status, ServiceContext sc,
			java.util.Map<String, Serializable> workflowContext)
		throws PortalException;

}