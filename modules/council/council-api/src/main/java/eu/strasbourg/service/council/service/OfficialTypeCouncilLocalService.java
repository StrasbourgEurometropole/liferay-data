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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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

import eu.strasbourg.service.council.model.OfficialTypeCouncil;
import eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for OfficialTypeCouncil. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncilLocalServiceUtil
 * @see eu.strasbourg.service.council.service.base.OfficialTypeCouncilLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.impl.OfficialTypeCouncilLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OfficialTypeCouncilLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfficialTypeCouncilLocalServiceUtil} to access the official type council local service. Add custom service methods to {@link eu.strasbourg.service.council.service.impl.OfficialTypeCouncilLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

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
	* Adds the official type council to the database. Also notifies the appropriate model listeners.
	*
	* @param officialTypeCouncil the official type council
	* @return the official type council that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfficialTypeCouncil addOfficialTypeCouncil(
		OfficialTypeCouncil officialTypeCouncil);

	/**
	* Creates a new official type council with the primary key. Does not add the official type council to the database.
	*
	* @param officialTypeCouncilPK the primary key for the new official type council
	* @return the new official type council
	*/
	public OfficialTypeCouncil createOfficialTypeCouncil(
		OfficialTypeCouncilPK officialTypeCouncilPK);

	/**
	* Crée une entité vide avec une PK, non ajouté à la base de donnée
	*/
	public OfficialTypeCouncil createOfficialTypeCouncil(long officialId,
		long typeId, ServiceContext sc);

	/**
	* Deletes the official type council from the database. Also notifies the appropriate model listeners.
	*
	* @param officialTypeCouncil the official type council
	* @return the official type council that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfficialTypeCouncil deleteOfficialTypeCouncil(
		OfficialTypeCouncil officialTypeCouncil);

	/**
	* Deletes the official type council with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param officialTypeCouncilPK the primary key of the official type council
	* @return the official type council that was removed
	* @throws PortalException if a official type council with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfficialTypeCouncil deleteOfficialTypeCouncil(
		OfficialTypeCouncilPK officialTypeCouncilPK) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfficialTypeCouncil fetchOfficialTypeCouncil(
		OfficialTypeCouncilPK officialTypeCouncilPK);

	/**
	* Returns the official type council matching the UUID and group.
	*
	* @param uuid the official type council's UUID
	* @param groupId the primary key of the group
	* @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfficialTypeCouncil fetchOfficialTypeCouncilByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Retourne le vote d'un élu pour une délibération
	*/
	public OfficialTypeCouncil findByTypeIdandOfficialId(long typeId,
		long officialId);

	/**
	* Returns the official type council with the primary key.
	*
	* @param officialTypeCouncilPK the primary key of the official type council
	* @return the official type council
	* @throws PortalException if a official type council with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfficialTypeCouncil getOfficialTypeCouncil(
		OfficialTypeCouncilPK officialTypeCouncilPK) throws PortalException;

	/**
	* Returns the official type council matching the UUID and group.
	*
	* @param uuid the official type council's UUID
	* @param groupId the primary key of the group
	* @return the matching official type council
	* @throws PortalException if a matching official type council could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfficialTypeCouncil getOfficialTypeCouncilByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime une entité
	*/
	public OfficialTypeCouncil removeOfficialTypeCouncil(long officialId,
		long typeId) throws PortalException;

	/**
	* Updates the official type council in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param officialTypeCouncil the official type council
	* @return the official type council that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfficialTypeCouncil updateOfficialTypeCouncil(
		OfficialTypeCouncil officialTypeCouncil);

	/**
	* Returns the number of official type councils.
	*
	* @return the number of official type councils
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfficialTypeCouncilsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne les types de Conseil d'un élu
	*
	* @return
	*/
	public List<OfficialTypeCouncil> findByOfficialId(long officialId);

	/**
	* Retourne la liste des votes d'une délibération
	*/
	public List<OfficialTypeCouncil> findByTypeId(long typeId);

	/**
	* Returns a range of all the official type councils.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of official type councils
	* @param end the upper bound of the range of official type councils (not inclusive)
	* @return the range of official type councils
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfficialTypeCouncil> getOfficialTypeCouncils(int start, int end);

	/**
	* Returns all the official type councils matching the UUID and company.
	*
	* @param uuid the UUID of the official type councils
	* @param companyId the primary key of the company
	* @return the matching official type councils, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfficialTypeCouncil> getOfficialTypeCouncilsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of official type councils matching the UUID and company.
	*
	* @param uuid the UUID of the official type councils
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of official type councils
	* @param end the upper bound of the range of official type councils (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching official type councils, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfficialTypeCouncil> getOfficialTypeCouncilsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator);

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
}