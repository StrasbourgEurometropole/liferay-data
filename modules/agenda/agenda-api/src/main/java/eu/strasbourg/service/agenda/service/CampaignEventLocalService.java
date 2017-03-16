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

package eu.strasbourg.service.agenda.service;

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

import eu.strasbourg.service.agenda.model.CampaignEvent;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CampaignEvent. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author BenjaminBini
 * @see CampaignEventLocalServiceUtil
 * @see eu.strasbourg.service.agenda.service.base.CampaignEventLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.CampaignEventLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface CampaignEventLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CampaignEventLocalServiceUtil} to access the campaign event local service. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.CampaignEventLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the campaign event to the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CampaignEvent addCampaignEvent(CampaignEvent campaignEvent);

	/**
	* Crée une campaignEvent vide avec une PK, non ajouté à la base de donnée
	*/
	public CampaignEvent createCampaignEvent(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new campaign event with the primary key. Does not add the campaign event to the database.
	*
	* @param campaignEventId the primary key for the new campaign event
	* @return the new campaign event
	*/
	public CampaignEvent createCampaignEvent(long campaignEventId);

	/**
	* Deletes the campaign event from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public CampaignEvent deleteCampaignEvent(CampaignEvent campaignEvent);

	/**
	* Deletes the campaign event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event that was removed
	* @throws PortalException if a campaign event with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public CampaignEvent deleteCampaignEvent(long campaignEventId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CampaignEvent fetchCampaignEvent(long campaignEventId);

	/**
	* Returns the campaign event matching the UUID and group.
	*
	* @param uuid the campaign event's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CampaignEvent fetchCampaignEventByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the campaign event with the primary key.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event
	* @throws PortalException if a campaign event with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CampaignEvent getCampaignEvent(long campaignEventId)
		throws PortalException;

	/**
	* Returns the campaign event matching the UUID and group.
	*
	* @param uuid the campaign event's UUID
	* @param groupId the primary key of the group
	* @return the matching campaign event
	* @throws PortalException if a matching campaign event could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CampaignEvent getCampaignEventByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime une campaignEvent
	*/
	public CampaignEvent removeCampaignEvent(long campaignEventId)
		throws PortalException;

	/**
	* Updates the campaign event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param campaignEvent the campaign event
	* @return the campaign event that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public CampaignEvent updateCampaignEvent(CampaignEvent campaignEvent);

	/**
	* Returns the number of campaign events.
	*
	* @return the number of campaign events
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCampaignEventsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne les événements d'une campagne
	*/
	public List<CampaignEvent> findByCampaignId(long campaignId);

	/**
	* Lance une recherche par mots-clés, thème et status
	*/
	public List<CampaignEvent> findByKeywordThemeAndStatus(
		java.lang.String keyword, long themeId, int status, long userId,
		long groupId, int start, int end);

	/**
	* Returns a range of all the campaign events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @return the range of campaign events
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CampaignEvent> getCampaignEvents(int start, int end);

	/**
	* Returns all the campaign events matching the UUID and company.
	*
	* @param uuid the UUID of the campaign events
	* @param companyId the primary key of the company
	* @return the matching campaign events, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CampaignEvent> getCampaignEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of campaign events matching the UUID and company.
	*
	* @param uuid the UUID of the campaign events
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching campaign events, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CampaignEvent> getCampaignEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator);

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
	* Compte de la recherche par mots-clés, thème et status
	*/
	public long findByKeywordThemeAndStatusCount(java.lang.String keyword,
		long themeId, int status, long userId, long groupId);
}