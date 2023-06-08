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
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.council.model.Procuration;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Procuration. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface ProcurationLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcurationLocalServiceUtil} to access the procuration local service. Add custom service methods to <code>eu.strasbourg.service.council.service.impl.ProcurationLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the procuration to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProcurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param procuration the procuration
	 * @return the procuration that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Procuration addProcuration(Procuration procuration);

	/**
	 * Creates a new procuration with the primary key. Does not add the procuration to the database.
	 *
	 * @param procurationId the primary key for the new procuration
	 * @return the new procuration
	 */
	@Transactional(enabled = false)
	public Procuration createProcuration(long procurationId);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	public Procuration createProcuration(ServiceContext sc)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the procuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProcurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param procurationId the primary key of the procuration
	 * @return the procuration that was removed
	 * @throws PortalException if a procuration with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Procuration deleteProcuration(long procurationId)
		throws PortalException;

	/**
	 * Deletes the procuration from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProcurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param procuration the procuration
	 * @return the procuration that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Procuration deleteProcuration(Procuration procuration);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.ProcurationModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.ProcurationModelImpl</code>.
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
	public Procuration fetchProcuration(long procurationId);

	/**
	 * Returns the procuration matching the UUID and group.
	 *
	 * @param uuid the procuration's UUID
	 * @param groupId the primary key of the group
	 * @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Procuration fetchProcurationByUuidAndGroupId(
		String uuid, long groupId);

	/**
	 * Recherche d'une absence à une session
	 */
	public Procuration findAbsenceForCouncilSession(
		long councilSessionId, long officialId);

	/**
	 * Recherche par ID de CouncilSession
	 */
	public List<Procuration> findByCouncilSessionId(long councilSessionId);

	/**
	 * Recherche d'une procuration pour un officiel
	 */
	public List<Procuration> findByCouncilSessionIdAndOfficialUnavailableId(
		long councilSessionId, long officialId);

	/**
	 * Recherche par ID de CouncilSession, élu et mandataire,
	 */
	public Procuration findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
		long councilSessionId, long officialVoter, long officialUnavailableId);

	/**
	 * Recherche par ID de CouncilSession et Mandataire
	 */
	public List<Procuration> findByCouncilSessionIdAndOfficialVotersId(
		long councilSessionId, long officialVoter);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

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
	 * Returns the procuration with the primary key.
	 *
	 * @param procurationId the primary key of the procuration
	 * @return the procuration
	 * @throws PortalException if a procuration with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Procuration getProcuration(long procurationId)
		throws PortalException;

	/**
	 * Returns the procuration matching the UUID and group.
	 *
	 * @param uuid the procuration's UUID
	 * @param groupId the primary key of the group
	 * @return the matching procuration
	 * @throws PortalException if a matching procuration could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Procuration getProcurationByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the procurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @return the range of procurations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Procuration> getProcurations(int start, int end);

	/**
	 * Returns all the procurations matching the UUID and company.
	 *
	 * @param uuid the UUID of the procurations
	 * @param companyId the primary key of the company
	 * @return the matching procurations, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Procuration> getProcurationsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of procurations matching the UUID and company.
	 *
	 * @param uuid the UUID of the procurations
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching procurations, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Procuration> getProcurationsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Procuration> orderByComparator);

	/**
	 * Returns the number of procurations.
	 *
	 * @return the number of procurations
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getProcurationsCount();

	/**
	 * Recherche d'une procuration active
	 * Si une procuration est active, alors l'élu est absent
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isOfficialAbsent(long councilSessionId, long officialId);

	/**
	 * Supprime une entité
	 */
	public Procuration removeProcuration(long procurationId)
		throws PortalException;

	/**
	 * Updates the procuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProcurationLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param procuration the procuration
	 * @return the procuration that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Procuration updateProcuration(Procuration procuration);

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	public Procuration updateProcuration(
			Procuration procuration, ServiceContext sc)
		throws PortalException;

	/**
	 * Met à jour le statut de l'entité par le framework workflow
	 */
	public Procuration updateStatus(
			long userId, long entryId, int status, ServiceContext sc,
			Map<String, Serializable> workflowContext)
		throws PortalException;

	/**
	 * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	 */
	public void updateStatus(Procuration procuration, int status)
		throws PortalException;

}