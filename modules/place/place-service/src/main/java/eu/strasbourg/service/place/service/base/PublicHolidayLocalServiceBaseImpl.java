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

package eu.strasbourg.service.place.service.base;

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

import eu.strasbourg.service.place.model.PublicHoliday;
import eu.strasbourg.service.place.service.PublicHolidayLocalService;
import eu.strasbourg.service.place.service.persistence.GoogleMyBusinessHistoricPersistence;
import eu.strasbourg.service.place.service.persistence.PeriodPersistence;
import eu.strasbourg.service.place.service.persistence.PlacePersistence;
import eu.strasbourg.service.place.service.persistence.PricePersistence;
import eu.strasbourg.service.place.service.persistence.PublicHolidayPersistence;
import eu.strasbourg.service.place.service.persistence.ScheduleExceptionPersistence;
import eu.strasbourg.service.place.service.persistence.SlotPersistence;
import eu.strasbourg.service.place.service.persistence.SubPlacePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the public holiday local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link eu.strasbourg.service.place.service.impl.PublicHolidayLocalServiceImpl}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.impl.PublicHolidayLocalServiceImpl
 * @see eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class PublicHolidayLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements PublicHolidayLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil} to access the public holiday local service.
	 */

	/**
	 * Adds the public holiday to the database. Also notifies the appropriate model listeners.
	 *
	 * @param publicHoliday the public holiday
	 * @return the public holiday that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PublicHoliday addPublicHoliday(PublicHoliday publicHoliday) {
		publicHoliday.setNew(true);

		return publicHolidayPersistence.update(publicHoliday);
	}

	/**
	 * Creates a new public holiday with the primary key. Does not add the public holiday to the database.
	 *
	 * @param publicHolidayId the primary key for the new public holiday
	 * @return the new public holiday
	 */
	@Override
	public PublicHoliday createPublicHoliday(long publicHolidayId) {
		return publicHolidayPersistence.create(publicHolidayId);
	}

	/**
	 * Deletes the public holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday that was removed
	 * @throws PortalException if a public holiday with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PublicHoliday deletePublicHoliday(long publicHolidayId)
		throws PortalException {
		return publicHolidayPersistence.remove(publicHolidayId);
	}

	/**
	 * Deletes the public holiday from the database. Also notifies the appropriate model listeners.
	 *
	 * @param publicHoliday the public holiday
	 * @return the public holiday that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PublicHoliday deletePublicHoliday(PublicHoliday publicHoliday) {
		return publicHolidayPersistence.remove(publicHoliday);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(PublicHoliday.class,
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
		return publicHolidayPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return publicHolidayPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return publicHolidayPersistence.findWithDynamicQuery(dynamicQuery,
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
		return publicHolidayPersistence.countWithDynamicQuery(dynamicQuery);
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
		return publicHolidayPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public PublicHoliday fetchPublicHoliday(long publicHolidayId) {
		return publicHolidayPersistence.fetchByPrimaryKey(publicHolidayId);
	}

	/**
	 * Returns the public holiday with the primary key.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday
	 * @throws PortalException if a public holiday with the primary key could not be found
	 */
	@Override
	public PublicHoliday getPublicHoliday(long publicHolidayId)
		throws PortalException {
		return publicHolidayPersistence.findByPrimaryKey(publicHolidayId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(publicHolidayLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PublicHoliday.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("publicHolidayId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(publicHolidayLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(PublicHoliday.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"publicHolidayId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(publicHolidayLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(PublicHoliday.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("publicHolidayId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return publicHolidayLocalService.deletePublicHoliday((PublicHoliday)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return publicHolidayPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the public holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @return the range of public holidaies
	 */
	@Override
	public List<PublicHoliday> getPublicHolidaies(int start, int end) {
		return publicHolidayPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of public holidaies.
	 *
	 * @return the number of public holidaies
	 */
	@Override
	public int getPublicHolidaiesCount() {
		return publicHolidayPersistence.countAll();
	}

	/**
	 * Updates the public holiday in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param publicHoliday the public holiday
	 * @return the public holiday that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PublicHoliday updatePublicHoliday(PublicHoliday publicHoliday) {
		return publicHolidayPersistence.update(publicHoliday);
	}

	/**
	 * Returns the google my business historic local service.
	 *
	 * @return the google my business historic local service
	 */
	public eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService getGoogleMyBusinessHistoricLocalService() {
		return googleMyBusinessHistoricLocalService;
	}

	/**
	 * Sets the google my business historic local service.
	 *
	 * @param googleMyBusinessHistoricLocalService the google my business historic local service
	 */
	public void setGoogleMyBusinessHistoricLocalService(
		eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService googleMyBusinessHistoricLocalService) {
		this.googleMyBusinessHistoricLocalService = googleMyBusinessHistoricLocalService;
	}

	/**
	 * Returns the google my business historic persistence.
	 *
	 * @return the google my business historic persistence
	 */
	public GoogleMyBusinessHistoricPersistence getGoogleMyBusinessHistoricPersistence() {
		return googleMyBusinessHistoricPersistence;
	}

	/**
	 * Sets the google my business historic persistence.
	 *
	 * @param googleMyBusinessHistoricPersistence the google my business historic persistence
	 */
	public void setGoogleMyBusinessHistoricPersistence(
		GoogleMyBusinessHistoricPersistence googleMyBusinessHistoricPersistence) {
		this.googleMyBusinessHistoricPersistence = googleMyBusinessHistoricPersistence;
	}

	/**
	 * Returns the period local service.
	 *
	 * @return the period local service
	 */
	public eu.strasbourg.service.place.service.PeriodLocalService getPeriodLocalService() {
		return periodLocalService;
	}

	/**
	 * Sets the period local service.
	 *
	 * @param periodLocalService the period local service
	 */
	public void setPeriodLocalService(
		eu.strasbourg.service.place.service.PeriodLocalService periodLocalService) {
		this.periodLocalService = periodLocalService;
	}

	/**
	 * Returns the period persistence.
	 *
	 * @return the period persistence
	 */
	public PeriodPersistence getPeriodPersistence() {
		return periodPersistence;
	}

	/**
	 * Sets the period persistence.
	 *
	 * @param periodPersistence the period persistence
	 */
	public void setPeriodPersistence(PeriodPersistence periodPersistence) {
		this.periodPersistence = periodPersistence;
	}

	/**
	 * Returns the place local service.
	 *
	 * @return the place local service
	 */
	public eu.strasbourg.service.place.service.PlaceLocalService getPlaceLocalService() {
		return placeLocalService;
	}

	/**
	 * Sets the place local service.
	 *
	 * @param placeLocalService the place local service
	 */
	public void setPlaceLocalService(
		eu.strasbourg.service.place.service.PlaceLocalService placeLocalService) {
		this.placeLocalService = placeLocalService;
	}

	/**
	 * Returns the place persistence.
	 *
	 * @return the place persistence
	 */
	public PlacePersistence getPlacePersistence() {
		return placePersistence;
	}

	/**
	 * Sets the place persistence.
	 *
	 * @param placePersistence the place persistence
	 */
	public void setPlacePersistence(PlacePersistence placePersistence) {
		this.placePersistence = placePersistence;
	}

	/**
	 * Returns the price local service.
	 *
	 * @return the price local service
	 */
	public eu.strasbourg.service.place.service.PriceLocalService getPriceLocalService() {
		return priceLocalService;
	}

	/**
	 * Sets the price local service.
	 *
	 * @param priceLocalService the price local service
	 */
	public void setPriceLocalService(
		eu.strasbourg.service.place.service.PriceLocalService priceLocalService) {
		this.priceLocalService = priceLocalService;
	}

	/**
	 * Returns the price persistence.
	 *
	 * @return the price persistence
	 */
	public PricePersistence getPricePersistence() {
		return pricePersistence;
	}

	/**
	 * Sets the price persistence.
	 *
	 * @param pricePersistence the price persistence
	 */
	public void setPricePersistence(PricePersistence pricePersistence) {
		this.pricePersistence = pricePersistence;
	}

	/**
	 * Returns the public holiday local service.
	 *
	 * @return the public holiday local service
	 */
	public PublicHolidayLocalService getPublicHolidayLocalService() {
		return publicHolidayLocalService;
	}

	/**
	 * Sets the public holiday local service.
	 *
	 * @param publicHolidayLocalService the public holiday local service
	 */
	public void setPublicHolidayLocalService(
		PublicHolidayLocalService publicHolidayLocalService) {
		this.publicHolidayLocalService = publicHolidayLocalService;
	}

	/**
	 * Returns the public holiday persistence.
	 *
	 * @return the public holiday persistence
	 */
	public PublicHolidayPersistence getPublicHolidayPersistence() {
		return publicHolidayPersistence;
	}

	/**
	 * Sets the public holiday persistence.
	 *
	 * @param publicHolidayPersistence the public holiday persistence
	 */
	public void setPublicHolidayPersistence(
		PublicHolidayPersistence publicHolidayPersistence) {
		this.publicHolidayPersistence = publicHolidayPersistence;
	}

	/**
	 * Returns the schedule exception local service.
	 *
	 * @return the schedule exception local service
	 */
	public eu.strasbourg.service.place.service.ScheduleExceptionLocalService getScheduleExceptionLocalService() {
		return scheduleExceptionLocalService;
	}

	/**
	 * Sets the schedule exception local service.
	 *
	 * @param scheduleExceptionLocalService the schedule exception local service
	 */
	public void setScheduleExceptionLocalService(
		eu.strasbourg.service.place.service.ScheduleExceptionLocalService scheduleExceptionLocalService) {
		this.scheduleExceptionLocalService = scheduleExceptionLocalService;
	}

	/**
	 * Returns the schedule exception persistence.
	 *
	 * @return the schedule exception persistence
	 */
	public ScheduleExceptionPersistence getScheduleExceptionPersistence() {
		return scheduleExceptionPersistence;
	}

	/**
	 * Sets the schedule exception persistence.
	 *
	 * @param scheduleExceptionPersistence the schedule exception persistence
	 */
	public void setScheduleExceptionPersistence(
		ScheduleExceptionPersistence scheduleExceptionPersistence) {
		this.scheduleExceptionPersistence = scheduleExceptionPersistence;
	}

	/**
	 * Returns the slot local service.
	 *
	 * @return the slot local service
	 */
	public eu.strasbourg.service.place.service.SlotLocalService getSlotLocalService() {
		return slotLocalService;
	}

	/**
	 * Sets the slot local service.
	 *
	 * @param slotLocalService the slot local service
	 */
	public void setSlotLocalService(
		eu.strasbourg.service.place.service.SlotLocalService slotLocalService) {
		this.slotLocalService = slotLocalService;
	}

	/**
	 * Returns the slot persistence.
	 *
	 * @return the slot persistence
	 */
	public SlotPersistence getSlotPersistence() {
		return slotPersistence;
	}

	/**
	 * Sets the slot persistence.
	 *
	 * @param slotPersistence the slot persistence
	 */
	public void setSlotPersistence(SlotPersistence slotPersistence) {
		this.slotPersistence = slotPersistence;
	}

	/**
	 * Returns the sub place local service.
	 *
	 * @return the sub place local service
	 */
	public eu.strasbourg.service.place.service.SubPlaceLocalService getSubPlaceLocalService() {
		return subPlaceLocalService;
	}

	/**
	 * Sets the sub place local service.
	 *
	 * @param subPlaceLocalService the sub place local service
	 */
	public void setSubPlaceLocalService(
		eu.strasbourg.service.place.service.SubPlaceLocalService subPlaceLocalService) {
		this.subPlaceLocalService = subPlaceLocalService;
	}

	/**
	 * Returns the sub place persistence.
	 *
	 * @return the sub place persistence
	 */
	public SubPlacePersistence getSubPlacePersistence() {
		return subPlacePersistence;
	}

	/**
	 * Sets the sub place persistence.
	 *
	 * @param subPlacePersistence the sub place persistence
	 */
	public void setSubPlacePersistence(SubPlacePersistence subPlacePersistence) {
		this.subPlacePersistence = subPlacePersistence;
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
		persistedModelLocalServiceRegistry.register("eu.strasbourg.service.place.model.PublicHoliday",
			publicHolidayLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"eu.strasbourg.service.place.model.PublicHoliday");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PublicHolidayLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PublicHoliday.class;
	}

	protected String getModelClassName() {
		return PublicHoliday.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = publicHolidayPersistence.getDataSource();

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

	@BeanReference(type = eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService.class)
	protected eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService googleMyBusinessHistoricLocalService;
	@BeanReference(type = GoogleMyBusinessHistoricPersistence.class)
	protected GoogleMyBusinessHistoricPersistence googleMyBusinessHistoricPersistence;
	@BeanReference(type = eu.strasbourg.service.place.service.PeriodLocalService.class)
	protected eu.strasbourg.service.place.service.PeriodLocalService periodLocalService;
	@BeanReference(type = PeriodPersistence.class)
	protected PeriodPersistence periodPersistence;
	@BeanReference(type = eu.strasbourg.service.place.service.PlaceLocalService.class)
	protected eu.strasbourg.service.place.service.PlaceLocalService placeLocalService;
	@BeanReference(type = PlacePersistence.class)
	protected PlacePersistence placePersistence;
	@BeanReference(type = eu.strasbourg.service.place.service.PriceLocalService.class)
	protected eu.strasbourg.service.place.service.PriceLocalService priceLocalService;
	@BeanReference(type = PricePersistence.class)
	protected PricePersistence pricePersistence;
	@BeanReference(type = PublicHolidayLocalService.class)
	protected PublicHolidayLocalService publicHolidayLocalService;
	@BeanReference(type = PublicHolidayPersistence.class)
	protected PublicHolidayPersistence publicHolidayPersistence;
	@BeanReference(type = eu.strasbourg.service.place.service.ScheduleExceptionLocalService.class)
	protected eu.strasbourg.service.place.service.ScheduleExceptionLocalService scheduleExceptionLocalService;
	@BeanReference(type = ScheduleExceptionPersistence.class)
	protected ScheduleExceptionPersistence scheduleExceptionPersistence;
	@BeanReference(type = eu.strasbourg.service.place.service.SlotLocalService.class)
	protected eu.strasbourg.service.place.service.SlotLocalService slotLocalService;
	@BeanReference(type = SlotPersistence.class)
	protected SlotPersistence slotPersistence;
	@BeanReference(type = eu.strasbourg.service.place.service.SubPlaceLocalService.class)
	protected eu.strasbourg.service.place.service.SubPlaceLocalService subPlaceLocalService;
	@BeanReference(type = SubPlacePersistence.class)
	protected SubPlacePersistence subPlacePersistence;
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