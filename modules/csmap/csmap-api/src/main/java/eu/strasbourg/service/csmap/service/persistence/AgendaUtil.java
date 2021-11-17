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

package eu.strasbourg.service.csmap.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.csmap.model.Agenda;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the agenda service. This utility wraps <code>eu.strasbourg.service.csmap.service.persistence.impl.AgendaPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AgendaPersistence
 * @generated
 */
@ProviderType
public class AgendaUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Agenda agenda) {
		getPersistence().clearCache(agenda);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Agenda> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Agenda> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Agenda> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Agenda> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Agenda update(Agenda agenda) {
		return getPersistence().update(agenda);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Agenda update(Agenda agenda, ServiceContext serviceContext) {
		return getPersistence().update(agenda, serviceContext);
	}

	/**
	 * Returns all the agendas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching agendas
	 */
	public static List<Agenda> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the agendas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of matching agendas
	 */
	public static List<Agenda> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the agendas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agendas
	 */
	public static List<Agenda> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the agendas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching agendas
	 */
	public static List<Agenda> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Agenda> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public static Agenda findByUuid_First(
			String uuid, OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public static Agenda fetchByUuid_First(
		String uuid, OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public static Agenda findByUuid_Last(
			String uuid, OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public static Agenda fetchByUuid_Last(
		String uuid, OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the agendas before and after the current agenda in the ordered set where uuid = &#63;.
	 *
	 * @param agendaId the primary key of the current agenda
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public static Agenda[] findByUuid_PrevAndNext(
			long agendaId, String uuid,
			OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByUuid_PrevAndNext(
			agendaId, uuid, orderByComparator);
	}

	/**
	 * Removes all the agendas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of agendas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching agendas
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the agendas where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @return the matching agendas
	 */
	public static List<Agenda> findByIsPrincipal(Boolean isPrincipal) {
		return getPersistence().findByIsPrincipal(isPrincipal);
	}

	/**
	 * Returns a range of all the agendas where isPrincipal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of matching agendas
	 */
	public static List<Agenda> findByIsPrincipal(
		Boolean isPrincipal, int start, int end) {

		return getPersistence().findByIsPrincipal(isPrincipal, start, end);
	}

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agendas
	 */
	public static List<Agenda> findByIsPrincipal(
		Boolean isPrincipal, int start, int end,
		OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().findByIsPrincipal(
			isPrincipal, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching agendas
	 */
	public static List<Agenda> findByIsPrincipal(
		Boolean isPrincipal, int start, int end,
		OrderByComparator<Agenda> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByIsPrincipal(
			isPrincipal, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public static Agenda findByIsPrincipal_First(
			Boolean isPrincipal, OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByIsPrincipal_First(
			isPrincipal, orderByComparator);
	}

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public static Agenda fetchByIsPrincipal_First(
		Boolean isPrincipal, OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().fetchByIsPrincipal_First(
			isPrincipal, orderByComparator);
	}

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public static Agenda findByIsPrincipal_Last(
			Boolean isPrincipal, OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByIsPrincipal_Last(
			isPrincipal, orderByComparator);
	}

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public static Agenda fetchByIsPrincipal_Last(
		Boolean isPrincipal, OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().fetchByIsPrincipal_Last(
			isPrincipal, orderByComparator);
	}

	/**
	 * Returns the agendas before and after the current agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param agendaId the primary key of the current agenda
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public static Agenda[] findByIsPrincipal_PrevAndNext(
			long agendaId, Boolean isPrincipal,
			OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByIsPrincipal_PrevAndNext(
			agendaId, isPrincipal, orderByComparator);
	}

	/**
	 * Removes all the agendas where isPrincipal = &#63; from the database.
	 *
	 * @param isPrincipal the is principal
	 */
	public static void removeByIsPrincipal(Boolean isPrincipal) {
		getPersistence().removeByIsPrincipal(isPrincipal);
	}

	/**
	 * Returns the number of agendas where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @return the number of matching agendas
	 */
	public static int countByIsPrincipal(Boolean isPrincipal) {
		return getPersistence().countByIsPrincipal(isPrincipal);
	}

	/**
	 * Returns all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @return the matching agendas
	 */
	public static List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive) {

		return getPersistence().findByIsPrincipalAndIsActive(
			isPrincipal, isActive);
	}

	/**
	 * Returns a range of all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of matching agendas
	 */
	public static List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive, int start, int end) {

		return getPersistence().findByIsPrincipalAndIsActive(
			isPrincipal, isActive, start, end);
	}

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agendas
	 */
	public static List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive, int start, int end,
		OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().findByIsPrincipalAndIsActive(
			isPrincipal, isActive, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching agendas
	 */
	public static List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive, int start, int end,
		OrderByComparator<Agenda> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByIsPrincipalAndIsActive(
			isPrincipal, isActive, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public static Agenda findByIsPrincipalAndIsActive_First(
			Boolean isPrincipal, Boolean isActive,
			OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByIsPrincipalAndIsActive_First(
			isPrincipal, isActive, orderByComparator);
	}

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public static Agenda fetchByIsPrincipalAndIsActive_First(
		Boolean isPrincipal, Boolean isActive,
		OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().fetchByIsPrincipalAndIsActive_First(
			isPrincipal, isActive, orderByComparator);
	}

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public static Agenda findByIsPrincipalAndIsActive_Last(
			Boolean isPrincipal, Boolean isActive,
			OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByIsPrincipalAndIsActive_Last(
			isPrincipal, isActive, orderByComparator);
	}

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public static Agenda fetchByIsPrincipalAndIsActive_Last(
		Boolean isPrincipal, Boolean isActive,
		OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().fetchByIsPrincipalAndIsActive_Last(
			isPrincipal, isActive, orderByComparator);
	}

	/**
	 * Returns the agendas before and after the current agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param agendaId the primary key of the current agenda
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public static Agenda[] findByIsPrincipalAndIsActive_PrevAndNext(
			long agendaId, Boolean isPrincipal, Boolean isActive,
			OrderByComparator<Agenda> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByIsPrincipalAndIsActive_PrevAndNext(
			agendaId, isPrincipal, isActive, orderByComparator);
	}

	/**
	 * Removes all the agendas where isPrincipal = &#63; and isActive = &#63; from the database.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 */
	public static void removeByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive) {

		getPersistence().removeByIsPrincipalAndIsActive(isPrincipal, isActive);
	}

	/**
	 * Returns the number of agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @return the number of matching agendas
	 */
	public static int countByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive) {

		return getPersistence().countByIsPrincipalAndIsActive(
			isPrincipal, isActive);
	}

	/**
	 * Caches the agenda in the entity cache if it is enabled.
	 *
	 * @param agenda the agenda
	 */
	public static void cacheResult(Agenda agenda) {
		getPersistence().cacheResult(agenda);
	}

	/**
	 * Caches the agendas in the entity cache if it is enabled.
	 *
	 * @param agendas the agendas
	 */
	public static void cacheResult(List<Agenda> agendas) {
		getPersistence().cacheResult(agendas);
	}

	/**
	 * Creates a new agenda with the primary key. Does not add the agenda to the database.
	 *
	 * @param agendaId the primary key for the new agenda
	 * @return the new agenda
	 */
	public static Agenda create(long agendaId) {
		return getPersistence().create(agendaId);
	}

	/**
	 * Removes the agenda with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param agendaId the primary key of the agenda
	 * @return the agenda that was removed
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public static Agenda remove(long agendaId)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().remove(agendaId);
	}

	public static Agenda updateImpl(Agenda agenda) {
		return getPersistence().updateImpl(agenda);
	}

	/**
	 * Returns the agenda with the primary key or throws a <code>NoSuchAgendaException</code> if it could not be found.
	 *
	 * @param agendaId the primary key of the agenda
	 * @return the agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public static Agenda findByPrimaryKey(long agendaId)
		throws eu.strasbourg.service.csmap.exception.NoSuchAgendaException {

		return getPersistence().findByPrimaryKey(agendaId);
	}

	/**
	 * Returns the agenda with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param agendaId the primary key of the agenda
	 * @return the agenda, or <code>null</code> if a agenda with the primary key could not be found
	 */
	public static Agenda fetchByPrimaryKey(long agendaId) {
		return getPersistence().fetchByPrimaryKey(agendaId);
	}

	/**
	 * Returns all the agendas.
	 *
	 * @return the agendas
	 */
	public static List<Agenda> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of agendas
	 */
	public static List<Agenda> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of agendas
	 */
	public static List<Agenda> findAll(
		int start, int end, OrderByComparator<Agenda> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of agendas
	 */
	public static List<Agenda> findAll(
		int start, int end, OrderByComparator<Agenda> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the agendas from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of agendas.
	 *
	 * @return the number of agendas
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AgendaPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AgendaPersistence, AgendaPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AgendaPersistence.class);

		ServiceTracker<AgendaPersistence, AgendaPersistence> serviceTracker =
			new ServiceTracker<AgendaPersistence, AgendaPersistence>(
				bundle.getBundleContext(), AgendaPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}