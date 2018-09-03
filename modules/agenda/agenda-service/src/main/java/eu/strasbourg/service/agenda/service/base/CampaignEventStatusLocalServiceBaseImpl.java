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

package eu.strasbourg.service.agenda.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventFinder;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventPersistence;
import eu.strasbourg.service.agenda.service.persistence.CampaignEventStatusPersistence;
import eu.strasbourg.service.agenda.service.persistence.CampaignPersistence;
import eu.strasbourg.service.agenda.service.persistence.EventParticipationPersistence;
import eu.strasbourg.service.agenda.service.persistence.EventPeriodPersistence;
import eu.strasbourg.service.agenda.service.persistence.EventPersistence;
import eu.strasbourg.service.agenda.service.persistence.ImportReportLinePersistence;
import eu.strasbourg.service.agenda.service.persistence.ImportReportPersistence;
import eu.strasbourg.service.agenda.service.persistence.ManifestationPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the campaign event status local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link eu.strasbourg.service.agenda.service.impl.CampaignEventStatusLocalServiceImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.impl.CampaignEventStatusLocalServiceImpl
 * @see eu.strasbourg.service.agenda.service.CampaignEventStatusLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CampaignEventStatusLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CampaignEventStatusLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.CampaignEventStatusLocalServiceUtil} to access the campaign event status local service.
	 */

	/**
	 * Adds the campaign event status to the database. Also notifies the appropriate model listeners.
	 *
	 * @param campaignEventStatus the campaign event status
	 * @return the campaign event status that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CampaignEventStatus addCampaignEventStatus(
		CampaignEventStatus campaignEventStatus) {
		campaignEventStatus.setNew(true);

		return campaignEventStatusPersistence.update(campaignEventStatus);
	}

	/**
	 * Creates a new campaign event status with the primary key. Does not add the campaign event status to the database.
	 *
	 * @param statusId the primary key for the new campaign event status
	 * @return the new campaign event status
	 */
	@Override
	public CampaignEventStatus createCampaignEventStatus(long statusId) {
		return campaignEventStatusPersistence.create(statusId);
	}

	/**
	 * Deletes the campaign event status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param statusId the primary key of the campaign event status
	 * @return the campaign event status that was removed
	 * @throws PortalException if a campaign event status with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CampaignEventStatus deleteCampaignEventStatus(long statusId)
		throws PortalException {
		return campaignEventStatusPersistence.remove(statusId);
	}

	/**
	 * Deletes the campaign event status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param campaignEventStatus the campaign event status
	 * @return the campaign event status that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CampaignEventStatus deleteCampaignEventStatus(
		CampaignEventStatus campaignEventStatus) {
		return campaignEventStatusPersistence.remove(campaignEventStatus);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CampaignEventStatus.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return campaignEventStatusPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return campaignEventStatusPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return campaignEventStatusPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return campaignEventStatusPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return campaignEventStatusPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CampaignEventStatus fetchCampaignEventStatus(long statusId) {
		return campaignEventStatusPersistence.fetchByPrimaryKey(statusId);
	}

	/**
	 * Returns the campaign event status with the primary key.
	 *
	 * @param statusId the primary key of the campaign event status
	 * @return the campaign event status
	 * @throws PortalException if a campaign event status with the primary key could not be found
	 */
	@Override
	public CampaignEventStatus getCampaignEventStatus(long statusId)
		throws PortalException {
		return campaignEventStatusPersistence.findByPrimaryKey(statusId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(campaignEventStatusLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CampaignEventStatus.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("statusId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(campaignEventStatusLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CampaignEventStatus.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("statusId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(campaignEventStatusLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CampaignEventStatus.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("statusId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return campaignEventStatusLocalService.deleteCampaignEventStatus((CampaignEventStatus)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return campaignEventStatusPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the campaign event statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign event statuses
	 * @param end the upper bound of the range of campaign event statuses (not inclusive)
	 * @return the range of campaign event statuses
	 */
	@Override
	public List<CampaignEventStatus> getCampaignEventStatuses(int start, int end) {
		return campaignEventStatusPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of campaign event statuses.
	 *
	 * @return the number of campaign event statuses
	 */
	@Override
	public int getCampaignEventStatusesCount() {
		return campaignEventStatusPersistence.countAll();
	}

	/**
	 * Updates the campaign event status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param campaignEventStatus the campaign event status
	 * @return the campaign event status that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CampaignEventStatus updateCampaignEventStatus(
		CampaignEventStatus campaignEventStatus) {
		return campaignEventStatusPersistence.update(campaignEventStatus);
	}

	/**
	 * Returns the campaign local service.
	 *
	 * @return the campaign local service
	 */
	public eu.strasbourg.service.agenda.service.CampaignLocalService getCampaignLocalService() {
		return campaignLocalService;
	}

	/**
	 * Sets the campaign local service.
	 *
	 * @param campaignLocalService the campaign local service
	 */
	public void setCampaignLocalService(
		eu.strasbourg.service.agenda.service.CampaignLocalService campaignLocalService) {
		this.campaignLocalService = campaignLocalService;
	}

	/**
	 * Returns the campaign persistence.
	 *
	 * @return the campaign persistence
	 */
	public CampaignPersistence getCampaignPersistence() {
		return campaignPersistence;
	}

	/**
	 * Sets the campaign persistence.
	 *
	 * @param campaignPersistence the campaign persistence
	 */
	public void setCampaignPersistence(CampaignPersistence campaignPersistence) {
		this.campaignPersistence = campaignPersistence;
	}

	/**
	 * Returns the campaign event local service.
	 *
	 * @return the campaign event local service
	 */
	public eu.strasbourg.service.agenda.service.CampaignEventLocalService getCampaignEventLocalService() {
		return campaignEventLocalService;
	}

	/**
	 * Sets the campaign event local service.
	 *
	 * @param campaignEventLocalService the campaign event local service
	 */
	public void setCampaignEventLocalService(
		eu.strasbourg.service.agenda.service.CampaignEventLocalService campaignEventLocalService) {
		this.campaignEventLocalService = campaignEventLocalService;
	}

	/**
	 * Returns the campaign event persistence.
	 *
	 * @return the campaign event persistence
	 */
	public CampaignEventPersistence getCampaignEventPersistence() {
		return campaignEventPersistence;
	}

	/**
	 * Sets the campaign event persistence.
	 *
	 * @param campaignEventPersistence the campaign event persistence
	 */
	public void setCampaignEventPersistence(
		CampaignEventPersistence campaignEventPersistence) {
		this.campaignEventPersistence = campaignEventPersistence;
	}

	/**
	 * Returns the campaign event finder.
	 *
	 * @return the campaign event finder
	 */
	public CampaignEventFinder getCampaignEventFinder() {
		return campaignEventFinder;
	}

	/**
	 * Sets the campaign event finder.
	 *
	 * @param campaignEventFinder the campaign event finder
	 */
	public void setCampaignEventFinder(CampaignEventFinder campaignEventFinder) {
		this.campaignEventFinder = campaignEventFinder;
	}

	/**
	 * Returns the campaign event status local service.
	 *
	 * @return the campaign event status local service
	 */
	public CampaignEventStatusLocalService getCampaignEventStatusLocalService() {
		return campaignEventStatusLocalService;
	}

	/**
	 * Sets the campaign event status local service.
	 *
	 * @param campaignEventStatusLocalService the campaign event status local service
	 */
	public void setCampaignEventStatusLocalService(
		CampaignEventStatusLocalService campaignEventStatusLocalService) {
		this.campaignEventStatusLocalService = campaignEventStatusLocalService;
	}

	/**
	 * Returns the campaign event status persistence.
	 *
	 * @return the campaign event status persistence
	 */
	public CampaignEventStatusPersistence getCampaignEventStatusPersistence() {
		return campaignEventStatusPersistence;
	}

	/**
	 * Sets the campaign event status persistence.
	 *
	 * @param campaignEventStatusPersistence the campaign event status persistence
	 */
	public void setCampaignEventStatusPersistence(
		CampaignEventStatusPersistence campaignEventStatusPersistence) {
		this.campaignEventStatusPersistence = campaignEventStatusPersistence;
	}

	/**
	 * Returns the event local service.
	 *
	 * @return the event local service
	 */
	public eu.strasbourg.service.agenda.service.EventLocalService getEventLocalService() {
		return eventLocalService;
	}

	/**
	 * Sets the event local service.
	 *
	 * @param eventLocalService the event local service
	 */
	public void setEventLocalService(
		eu.strasbourg.service.agenda.service.EventLocalService eventLocalService) {
		this.eventLocalService = eventLocalService;
	}

	/**
	 * Returns the event persistence.
	 *
	 * @return the event persistence
	 */
	public EventPersistence getEventPersistence() {
		return eventPersistence;
	}

	/**
	 * Sets the event persistence.
	 *
	 * @param eventPersistence the event persistence
	 */
	public void setEventPersistence(EventPersistence eventPersistence) {
		this.eventPersistence = eventPersistence;
	}

	/**
	 * Returns the event participation local service.
	 *
	 * @return the event participation local service
	 */
	public eu.strasbourg.service.agenda.service.EventParticipationLocalService getEventParticipationLocalService() {
		return eventParticipationLocalService;
	}

	/**
	 * Sets the event participation local service.
	 *
	 * @param eventParticipationLocalService the event participation local service
	 */
	public void setEventParticipationLocalService(
		eu.strasbourg.service.agenda.service.EventParticipationLocalService eventParticipationLocalService) {
		this.eventParticipationLocalService = eventParticipationLocalService;
	}

	/**
	 * Returns the event participation persistence.
	 *
	 * @return the event participation persistence
	 */
	public EventParticipationPersistence getEventParticipationPersistence() {
		return eventParticipationPersistence;
	}

	/**
	 * Sets the event participation persistence.
	 *
	 * @param eventParticipationPersistence the event participation persistence
	 */
	public void setEventParticipationPersistence(
		EventParticipationPersistence eventParticipationPersistence) {
		this.eventParticipationPersistence = eventParticipationPersistence;
	}

	/**
	 * Returns the event period local service.
	 *
	 * @return the event period local service
	 */
	public eu.strasbourg.service.agenda.service.EventPeriodLocalService getEventPeriodLocalService() {
		return eventPeriodLocalService;
	}

	/**
	 * Sets the event period local service.
	 *
	 * @param eventPeriodLocalService the event period local service
	 */
	public void setEventPeriodLocalService(
		eu.strasbourg.service.agenda.service.EventPeriodLocalService eventPeriodLocalService) {
		this.eventPeriodLocalService = eventPeriodLocalService;
	}

	/**
	 * Returns the event period persistence.
	 *
	 * @return the event period persistence
	 */
	public EventPeriodPersistence getEventPeriodPersistence() {
		return eventPeriodPersistence;
	}

	/**
	 * Sets the event period persistence.
	 *
	 * @param eventPeriodPersistence the event period persistence
	 */
	public void setEventPeriodPersistence(
		EventPeriodPersistence eventPeriodPersistence) {
		this.eventPeriodPersistence = eventPeriodPersistence;
	}

	/**
	 * Returns the import report local service.
	 *
	 * @return the import report local service
	 */
	public eu.strasbourg.service.agenda.service.ImportReportLocalService getImportReportLocalService() {
		return importReportLocalService;
	}

	/**
	 * Sets the import report local service.
	 *
	 * @param importReportLocalService the import report local service
	 */
	public void setImportReportLocalService(
		eu.strasbourg.service.agenda.service.ImportReportLocalService importReportLocalService) {
		this.importReportLocalService = importReportLocalService;
	}

	/**
	 * Returns the import report persistence.
	 *
	 * @return the import report persistence
	 */
	public ImportReportPersistence getImportReportPersistence() {
		return importReportPersistence;
	}

	/**
	 * Sets the import report persistence.
	 *
	 * @param importReportPersistence the import report persistence
	 */
	public void setImportReportPersistence(
		ImportReportPersistence importReportPersistence) {
		this.importReportPersistence = importReportPersistence;
	}

	/**
	 * Returns the import report line local service.
	 *
	 * @return the import report line local service
	 */
	public eu.strasbourg.service.agenda.service.ImportReportLineLocalService getImportReportLineLocalService() {
		return importReportLineLocalService;
	}

	/**
	 * Sets the import report line local service.
	 *
	 * @param importReportLineLocalService the import report line local service
	 */
	public void setImportReportLineLocalService(
		eu.strasbourg.service.agenda.service.ImportReportLineLocalService importReportLineLocalService) {
		this.importReportLineLocalService = importReportLineLocalService;
	}

	/**
	 * Returns the import report line persistence.
	 *
	 * @return the import report line persistence
	 */
	public ImportReportLinePersistence getImportReportLinePersistence() {
		return importReportLinePersistence;
	}

	/**
	 * Sets the import report line persistence.
	 *
	 * @param importReportLinePersistence the import report line persistence
	 */
	public void setImportReportLinePersistence(
		ImportReportLinePersistence importReportLinePersistence) {
		this.importReportLinePersistence = importReportLinePersistence;
	}

	/**
	 * Returns the manifestation local service.
	 *
	 * @return the manifestation local service
	 */
	public eu.strasbourg.service.agenda.service.ManifestationLocalService getManifestationLocalService() {
		return manifestationLocalService;
	}

	/**
	 * Sets the manifestation local service.
	 *
	 * @param manifestationLocalService the manifestation local service
	 */
	public void setManifestationLocalService(
		eu.strasbourg.service.agenda.service.ManifestationLocalService manifestationLocalService) {
		this.manifestationLocalService = manifestationLocalService;
	}

	/**
	 * Returns the manifestation persistence.
	 *
	 * @return the manifestation persistence
	 */
	public ManifestationPersistence getManifestationPersistence() {
		return manifestationPersistence;
	}

	/**
	 * Sets the manifestation persistence.
	 *
	 * @param manifestationPersistence the manifestation persistence
	 */
	public void setManifestationPersistence(
		ManifestationPersistence manifestationPersistence) {
		this.manifestationPersistence = manifestationPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("eu.strasbourg.service.agenda.model.CampaignEventStatus",
			campaignEventStatusLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"eu.strasbourg.service.agenda.model.CampaignEventStatus");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CampaignEventStatusLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CampaignEventStatus.class;
	}

	protected String getModelClassName() {
		return CampaignEventStatus.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = campaignEventStatusPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = eu.strasbourg.service.agenda.service.CampaignLocalService.class)
	protected eu.strasbourg.service.agenda.service.CampaignLocalService campaignLocalService;
	@BeanReference(type = CampaignPersistence.class)
	protected CampaignPersistence campaignPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.CampaignEventLocalService.class)
	protected eu.strasbourg.service.agenda.service.CampaignEventLocalService campaignEventLocalService;
	@BeanReference(type = CampaignEventPersistence.class)
	protected CampaignEventPersistence campaignEventPersistence;
	@BeanReference(type = CampaignEventFinder.class)
	protected CampaignEventFinder campaignEventFinder;
	@BeanReference(type = CampaignEventStatusLocalService.class)
	protected CampaignEventStatusLocalService campaignEventStatusLocalService;
	@BeanReference(type = CampaignEventStatusPersistence.class)
	protected CampaignEventStatusPersistence campaignEventStatusPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.EventLocalService.class)
	protected eu.strasbourg.service.agenda.service.EventLocalService eventLocalService;
	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.EventParticipationLocalService.class)
	protected eu.strasbourg.service.agenda.service.EventParticipationLocalService eventParticipationLocalService;
	@BeanReference(type = EventParticipationPersistence.class)
	protected EventParticipationPersistence eventParticipationPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.EventPeriodLocalService.class)
	protected eu.strasbourg.service.agenda.service.EventPeriodLocalService eventPeriodLocalService;
	@BeanReference(type = EventPeriodPersistence.class)
	protected EventPeriodPersistence eventPeriodPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.ImportReportLocalService.class)
	protected eu.strasbourg.service.agenda.service.ImportReportLocalService importReportLocalService;
	@BeanReference(type = ImportReportPersistence.class)
	protected ImportReportPersistence importReportPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.ImportReportLineLocalService.class)
	protected eu.strasbourg.service.agenda.service.ImportReportLineLocalService importReportLineLocalService;
	@BeanReference(type = ImportReportLinePersistence.class)
	protected ImportReportLinePersistence importReportLinePersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.ManifestationLocalService.class)
	protected eu.strasbourg.service.agenda.service.ManifestationLocalService manifestationLocalService;
	@BeanReference(type = ManifestationPersistence.class)
	protected ManifestationPersistence manifestationPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}