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

package eu.strasbourg.service.gtfs.service.base;

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

import eu.strasbourg.service.gtfs.model.Stop;
import eu.strasbourg.service.gtfs.service.StopLocalService;
import eu.strasbourg.service.gtfs.service.persistence.AgencyPersistence;
import eu.strasbourg.service.gtfs.service.persistence.ArretPersistence;
import eu.strasbourg.service.gtfs.service.persistence.CalendarDatePersistence;
import eu.strasbourg.service.gtfs.service.persistence.CalendarPersistence;
import eu.strasbourg.service.gtfs.service.persistence.DirectionPersistence;
import eu.strasbourg.service.gtfs.service.persistence.ImportHistoricPersistence;
import eu.strasbourg.service.gtfs.service.persistence.LignePersistence;
import eu.strasbourg.service.gtfs.service.persistence.RoutePersistence;
import eu.strasbourg.service.gtfs.service.persistence.StopPersistence;
import eu.strasbourg.service.gtfs.service.persistence.StopTimePersistence;
import eu.strasbourg.service.gtfs.service.persistence.TripPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the stop local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link eu.strasbourg.service.gtfs.service.impl.StopLocalServiceImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.impl.StopLocalServiceImpl
 * @see eu.strasbourg.service.gtfs.service.StopLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class StopLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements StopLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.StopLocalServiceUtil} to access the stop local service.
	 */

	/**
	 * Adds the stop to the database. Also notifies the appropriate model listeners.
	 *
	 * @param stop the stop
	 * @return the stop that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Stop addStop(Stop stop) {
		stop.setNew(true);

		return stopPersistence.update(stop);
	}

	/**
	 * Creates a new stop with the primary key. Does not add the stop to the database.
	 *
	 * @param id the primary key for the new stop
	 * @return the new stop
	 */
	@Override
	public Stop createStop(long id) {
		return stopPersistence.create(id);
	}

	/**
	 * Deletes the stop with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the stop
	 * @return the stop that was removed
	 * @throws PortalException if a stop with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Stop deleteStop(long id) throws PortalException {
		return stopPersistence.remove(id);
	}

	/**
	 * Deletes the stop from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stop the stop
	 * @return the stop that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Stop deleteStop(Stop stop) {
		return stopPersistence.remove(stop);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Stop.class,
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
		return stopPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return stopPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return stopPersistence.findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return stopPersistence.countWithDynamicQuery(dynamicQuery);
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
		return stopPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Stop fetchStop(long id) {
		return stopPersistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the stop with the primary key.
	 *
	 * @param id the primary key of the stop
	 * @return the stop
	 * @throws PortalException if a stop with the primary key could not be found
	 */
	@Override
	public Stop getStop(long id) throws PortalException {
		return stopPersistence.findByPrimaryKey(id);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(stopLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Stop.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("id");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(stopLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Stop.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("id");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(stopLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Stop.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("id");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return stopLocalService.deleteStop((Stop)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return stopPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the stops.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of stops
	 * @param end the upper bound of the range of stops (not inclusive)
	 * @return the range of stops
	 */
	@Override
	public List<Stop> getStops(int start, int end) {
		return stopPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of stops.
	 *
	 * @return the number of stops
	 */
	@Override
	public int getStopsCount() {
		return stopPersistence.countAll();
	}

	/**
	 * Updates the stop in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param stop the stop
	 * @return the stop that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Stop updateStop(Stop stop) {
		return stopPersistence.update(stop);
	}

	/**
	 * Returns the agency local service.
	 *
	 * @return the agency local service
	 */
	public eu.strasbourg.service.gtfs.service.AgencyLocalService getAgencyLocalService() {
		return agencyLocalService;
	}

	/**
	 * Sets the agency local service.
	 *
	 * @param agencyLocalService the agency local service
	 */
	public void setAgencyLocalService(
		eu.strasbourg.service.gtfs.service.AgencyLocalService agencyLocalService) {
		this.agencyLocalService = agencyLocalService;
	}

	/**
	 * Returns the agency persistence.
	 *
	 * @return the agency persistence
	 */
	public AgencyPersistence getAgencyPersistence() {
		return agencyPersistence;
	}

	/**
	 * Sets the agency persistence.
	 *
	 * @param agencyPersistence the agency persistence
	 */
	public void setAgencyPersistence(AgencyPersistence agencyPersistence) {
		this.agencyPersistence = agencyPersistence;
	}

	/**
	 * Returns the arret local service.
	 *
	 * @return the arret local service
	 */
	public eu.strasbourg.service.gtfs.service.ArretLocalService getArretLocalService() {
		return arretLocalService;
	}

	/**
	 * Sets the arret local service.
	 *
	 * @param arretLocalService the arret local service
	 */
	public void setArretLocalService(
		eu.strasbourg.service.gtfs.service.ArretLocalService arretLocalService) {
		this.arretLocalService = arretLocalService;
	}

	/**
	 * Returns the arret persistence.
	 *
	 * @return the arret persistence
	 */
	public ArretPersistence getArretPersistence() {
		return arretPersistence;
	}

	/**
	 * Sets the arret persistence.
	 *
	 * @param arretPersistence the arret persistence
	 */
	public void setArretPersistence(ArretPersistence arretPersistence) {
		this.arretPersistence = arretPersistence;
	}

	/**
	 * Returns the calendar local service.
	 *
	 * @return the calendar local service
	 */
	public eu.strasbourg.service.gtfs.service.CalendarLocalService getCalendarLocalService() {
		return calendarLocalService;
	}

	/**
	 * Sets the calendar local service.
	 *
	 * @param calendarLocalService the calendar local service
	 */
	public void setCalendarLocalService(
		eu.strasbourg.service.gtfs.service.CalendarLocalService calendarLocalService) {
		this.calendarLocalService = calendarLocalService;
	}

	/**
	 * Returns the calendar persistence.
	 *
	 * @return the calendar persistence
	 */
	public CalendarPersistence getCalendarPersistence() {
		return calendarPersistence;
	}

	/**
	 * Sets the calendar persistence.
	 *
	 * @param calendarPersistence the calendar persistence
	 */
	public void setCalendarPersistence(CalendarPersistence calendarPersistence) {
		this.calendarPersistence = calendarPersistence;
	}

	/**
	 * Returns the calendar date local service.
	 *
	 * @return the calendar date local service
	 */
	public eu.strasbourg.service.gtfs.service.CalendarDateLocalService getCalendarDateLocalService() {
		return calendarDateLocalService;
	}

	/**
	 * Sets the calendar date local service.
	 *
	 * @param calendarDateLocalService the calendar date local service
	 */
	public void setCalendarDateLocalService(
		eu.strasbourg.service.gtfs.service.CalendarDateLocalService calendarDateLocalService) {
		this.calendarDateLocalService = calendarDateLocalService;
	}

	/**
	 * Returns the calendar date persistence.
	 *
	 * @return the calendar date persistence
	 */
	public CalendarDatePersistence getCalendarDatePersistence() {
		return calendarDatePersistence;
	}

	/**
	 * Sets the calendar date persistence.
	 *
	 * @param calendarDatePersistence the calendar date persistence
	 */
	public void setCalendarDatePersistence(
		CalendarDatePersistence calendarDatePersistence) {
		this.calendarDatePersistence = calendarDatePersistence;
	}

	/**
	 * Returns the direction local service.
	 *
	 * @return the direction local service
	 */
	public eu.strasbourg.service.gtfs.service.DirectionLocalService getDirectionLocalService() {
		return directionLocalService;
	}

	/**
	 * Sets the direction local service.
	 *
	 * @param directionLocalService the direction local service
	 */
	public void setDirectionLocalService(
		eu.strasbourg.service.gtfs.service.DirectionLocalService directionLocalService) {
		this.directionLocalService = directionLocalService;
	}

	/**
	 * Returns the direction persistence.
	 *
	 * @return the direction persistence
	 */
	public DirectionPersistence getDirectionPersistence() {
		return directionPersistence;
	}

	/**
	 * Sets the direction persistence.
	 *
	 * @param directionPersistence the direction persistence
	 */
	public void setDirectionPersistence(
		DirectionPersistence directionPersistence) {
		this.directionPersistence = directionPersistence;
	}

	/**
	 * Returns the import historic local service.
	 *
	 * @return the import historic local service
	 */
	public eu.strasbourg.service.gtfs.service.ImportHistoricLocalService getImportHistoricLocalService() {
		return importHistoricLocalService;
	}

	/**
	 * Sets the import historic local service.
	 *
	 * @param importHistoricLocalService the import historic local service
	 */
	public void setImportHistoricLocalService(
		eu.strasbourg.service.gtfs.service.ImportHistoricLocalService importHistoricLocalService) {
		this.importHistoricLocalService = importHistoricLocalService;
	}

	/**
	 * Returns the import historic persistence.
	 *
	 * @return the import historic persistence
	 */
	public ImportHistoricPersistence getImportHistoricPersistence() {
		return importHistoricPersistence;
	}

	/**
	 * Sets the import historic persistence.
	 *
	 * @param importHistoricPersistence the import historic persistence
	 */
	public void setImportHistoricPersistence(
		ImportHistoricPersistence importHistoricPersistence) {
		this.importHistoricPersistence = importHistoricPersistence;
	}

	/**
	 * Returns the ligne local service.
	 *
	 * @return the ligne local service
	 */
	public eu.strasbourg.service.gtfs.service.LigneLocalService getLigneLocalService() {
		return ligneLocalService;
	}

	/**
	 * Sets the ligne local service.
	 *
	 * @param ligneLocalService the ligne local service
	 */
	public void setLigneLocalService(
		eu.strasbourg.service.gtfs.service.LigneLocalService ligneLocalService) {
		this.ligneLocalService = ligneLocalService;
	}

	/**
	 * Returns the ligne persistence.
	 *
	 * @return the ligne persistence
	 */
	public LignePersistence getLignePersistence() {
		return lignePersistence;
	}

	/**
	 * Sets the ligne persistence.
	 *
	 * @param lignePersistence the ligne persistence
	 */
	public void setLignePersistence(LignePersistence lignePersistence) {
		this.lignePersistence = lignePersistence;
	}

	/**
	 * Returns the route local service.
	 *
	 * @return the route local service
	 */
	public eu.strasbourg.service.gtfs.service.RouteLocalService getRouteLocalService() {
		return routeLocalService;
	}

	/**
	 * Sets the route local service.
	 *
	 * @param routeLocalService the route local service
	 */
	public void setRouteLocalService(
		eu.strasbourg.service.gtfs.service.RouteLocalService routeLocalService) {
		this.routeLocalService = routeLocalService;
	}

	/**
	 * Returns the route persistence.
	 *
	 * @return the route persistence
	 */
	public RoutePersistence getRoutePersistence() {
		return routePersistence;
	}

	/**
	 * Sets the route persistence.
	 *
	 * @param routePersistence the route persistence
	 */
	public void setRoutePersistence(RoutePersistence routePersistence) {
		this.routePersistence = routePersistence;
	}

	/**
	 * Returns the stop local service.
	 *
	 * @return the stop local service
	 */
	public StopLocalService getStopLocalService() {
		return stopLocalService;
	}

	/**
	 * Sets the stop local service.
	 *
	 * @param stopLocalService the stop local service
	 */
	public void setStopLocalService(StopLocalService stopLocalService) {
		this.stopLocalService = stopLocalService;
	}

	/**
	 * Returns the stop persistence.
	 *
	 * @return the stop persistence
	 */
	public StopPersistence getStopPersistence() {
		return stopPersistence;
	}

	/**
	 * Sets the stop persistence.
	 *
	 * @param stopPersistence the stop persistence
	 */
	public void setStopPersistence(StopPersistence stopPersistence) {
		this.stopPersistence = stopPersistence;
	}

	/**
	 * Returns the stop time local service.
	 *
	 * @return the stop time local service
	 */
	public eu.strasbourg.service.gtfs.service.StopTimeLocalService getStopTimeLocalService() {
		return stopTimeLocalService;
	}

	/**
	 * Sets the stop time local service.
	 *
	 * @param stopTimeLocalService the stop time local service
	 */
	public void setStopTimeLocalService(
		eu.strasbourg.service.gtfs.service.StopTimeLocalService stopTimeLocalService) {
		this.stopTimeLocalService = stopTimeLocalService;
	}

	/**
	 * Returns the stop time persistence.
	 *
	 * @return the stop time persistence
	 */
	public StopTimePersistence getStopTimePersistence() {
		return stopTimePersistence;
	}

	/**
	 * Sets the stop time persistence.
	 *
	 * @param stopTimePersistence the stop time persistence
	 */
	public void setStopTimePersistence(StopTimePersistence stopTimePersistence) {
		this.stopTimePersistence = stopTimePersistence;
	}

	/**
	 * Returns the trip local service.
	 *
	 * @return the trip local service
	 */
	public eu.strasbourg.service.gtfs.service.TripLocalService getTripLocalService() {
		return tripLocalService;
	}

	/**
	 * Sets the trip local service.
	 *
	 * @param tripLocalService the trip local service
	 */
	public void setTripLocalService(
		eu.strasbourg.service.gtfs.service.TripLocalService tripLocalService) {
		this.tripLocalService = tripLocalService;
	}

	/**
	 * Returns the trip persistence.
	 *
	 * @return the trip persistence
	 */
	public TripPersistence getTripPersistence() {
		return tripPersistence;
	}

	/**
	 * Sets the trip persistence.
	 *
	 * @param tripPersistence the trip persistence
	 */
	public void setTripPersistence(TripPersistence tripPersistence) {
		this.tripPersistence = tripPersistence;
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
		persistedModelLocalServiceRegistry.register("eu.strasbourg.service.gtfs.model.Stop",
			stopLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"eu.strasbourg.service.gtfs.model.Stop");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return StopLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Stop.class;
	}

	protected String getModelClassName() {
		return Stop.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = stopPersistence.getDataSource();

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

	@BeanReference(type = eu.strasbourg.service.gtfs.service.AgencyLocalService.class)
	protected eu.strasbourg.service.gtfs.service.AgencyLocalService agencyLocalService;
	@BeanReference(type = AgencyPersistence.class)
	protected AgencyPersistence agencyPersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.ArretLocalService.class)
	protected eu.strasbourg.service.gtfs.service.ArretLocalService arretLocalService;
	@BeanReference(type = ArretPersistence.class)
	protected ArretPersistence arretPersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.CalendarLocalService.class)
	protected eu.strasbourg.service.gtfs.service.CalendarLocalService calendarLocalService;
	@BeanReference(type = CalendarPersistence.class)
	protected CalendarPersistence calendarPersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.CalendarDateLocalService.class)
	protected eu.strasbourg.service.gtfs.service.CalendarDateLocalService calendarDateLocalService;
	@BeanReference(type = CalendarDatePersistence.class)
	protected CalendarDatePersistence calendarDatePersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.DirectionLocalService.class)
	protected eu.strasbourg.service.gtfs.service.DirectionLocalService directionLocalService;
	@BeanReference(type = DirectionPersistence.class)
	protected DirectionPersistence directionPersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.ImportHistoricLocalService.class)
	protected eu.strasbourg.service.gtfs.service.ImportHistoricLocalService importHistoricLocalService;
	@BeanReference(type = ImportHistoricPersistence.class)
	protected ImportHistoricPersistence importHistoricPersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.LigneLocalService.class)
	protected eu.strasbourg.service.gtfs.service.LigneLocalService ligneLocalService;
	@BeanReference(type = LignePersistence.class)
	protected LignePersistence lignePersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.RouteLocalService.class)
	protected eu.strasbourg.service.gtfs.service.RouteLocalService routeLocalService;
	@BeanReference(type = RoutePersistence.class)
	protected RoutePersistence routePersistence;
	@BeanReference(type = StopLocalService.class)
	protected StopLocalService stopLocalService;
	@BeanReference(type = StopPersistence.class)
	protected StopPersistence stopPersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.StopTimeLocalService.class)
	protected eu.strasbourg.service.gtfs.service.StopTimeLocalService stopTimeLocalService;
	@BeanReference(type = StopTimePersistence.class)
	protected StopTimePersistence stopTimePersistence;
	@BeanReference(type = eu.strasbourg.service.gtfs.service.TripLocalService.class)
	protected eu.strasbourg.service.gtfs.service.TripLocalService tripLocalService;
	@BeanReference(type = TripPersistence.class)
	protected TripPersistence tripPersistence;
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