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

package eu.strasbourg.service.gtfs.service;

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

import eu.strasbourg.service.gtfs.model.ImportHistoric;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for ImportHistoric. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see ImportHistoricLocalServiceUtil
 * @see eu.strasbourg.service.gtfs.service.base.ImportHistoricLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.ImportHistoricLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ImportHistoricLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImportHistoricLocalServiceUtil} to access the import historic local service. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.ImportHistoricLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the import historic to the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoric the import historic
	* @return the import historic that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ImportHistoric addImportHistoric(ImportHistoric importHistoric);

	/**
	* Crée une entree d'import vide avec une PK, non ajouté à la base de donnée
	*/
	public ImportHistoric createImportHistoric(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new import historic with the primary key. Does not add the import historic to the database.
	*
	* @param importHistoricId the primary key for the new import historic
	* @return the new import historic
	*/
	public ImportHistoric createImportHistoric(long importHistoricId);

	/**
	* Deletes the import historic from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoric the import historic
	* @return the import historic that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ImportHistoric deleteImportHistoric(ImportHistoric importHistoric);

	/**
	* Deletes the import historic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoricId the primary key of the import historic
	* @return the import historic that was removed
	* @throws PortalException if a import historic with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ImportHistoric deleteImportHistoric(long importHistoricId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistoric fetchImportHistoric(long importHistoricId);

	/**
	* Returns the import historic matching the UUID and group.
	*
	* @param uuid the import historic's UUID
	* @param groupId the primary key of the group
	* @return the matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistoric fetchImportHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the import historic with the primary key.
	*
	* @param importHistoricId the primary key of the import historic
	* @return the import historic
	* @throws PortalException if a import historic with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistoric getImportHistoric(long importHistoricId)
		throws PortalException;

	/**
	* Returns the import historic matching the UUID and group.
	*
	* @param uuid the import historic's UUID
	* @param groupId the primary key of the group
	* @return the matching import historic
	* @throws PortalException if a matching import historic could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistoric getImportHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime l'entree d'import
	*/
	public ImportHistoric removeImportHistoric(long importHistoricId)
		throws PortalException;

	/**
	* Updates the import historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importHistoric the import historic
	* @return the import historic that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ImportHistoric updateImportHistoric(ImportHistoric importHistoric);

	/**
	* Met à jour une entree d'import et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public ImportHistoric updateImportHistoric(ImportHistoric importHistoric,
		ServiceContext sc) throws PortalException;

	/**
	* Met à jour le statut de l'entree d'import par le framework workflow
	*/
	public ImportHistoric updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Returns the number of import historics.
	*
	* @return the number of import historics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getImportHistoricsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Renvoie la liste des vocabulaires rattachés à un projet
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne tous les projets d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportHistoric> getByGroupId(long groupId);

	/**
	* Returns a range of all the import historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @return the range of import historics
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportHistoric> getImportHistorics(int start, int end);

	/**
	* Returns all the import historics matching the UUID and company.
	*
	* @param uuid the UUID of the import historics
	* @param companyId the primary key of the company
	* @return the matching import historics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportHistoric> getImportHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of import historics matching the UUID and company.
	*
	* @param uuid the UUID of the import historics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching import historics, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportHistoric> getImportHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ImportHistoric> orderByComparator);

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
	* Met à jour le statut de l'entree d'import "manuellement" (pas via le workflow)
	*/
	public void updateStatus(ImportHistoric importHistoric, int status)
		throws PortalException;
}