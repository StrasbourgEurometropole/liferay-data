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

package eu.strasbourg.service.formSendRecordField.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the form send record field signalement service. This utility wraps <code>eu.strasbourg.service.formSendRecordField.service.persistence.impl.FormSendRecordFieldSignalementPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementPersistence
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSignalementUtil {

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
	public static void clearCache(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {

		getPersistence().clearCache(formSendRecordFieldSignalement);
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
	public static Map<Serializable, FormSendRecordFieldSignalement>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FormSendRecordFieldSignalement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FormSendRecordFieldSignalement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FormSendRecordFieldSignalement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FormSendRecordFieldSignalement update(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {

		return getPersistence().update(formSendRecordFieldSignalement);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FormSendRecordFieldSignalement update(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement,
		ServiceContext serviceContext) {

		return getPersistence().update(
			formSendRecordFieldSignalement, serviceContext);
	}

	/**
	 * Returns all the form send record field signalements where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the form send record field signalements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByUuid_First(
			String uuid,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByUuid_First(
		String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByUuid_Last(
			String uuid,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByUuid_Last(
		String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement[] findByUuid_PrevAndNext(
			long signalementId, String uuid,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByUuid_PrevAndNext(
			signalementId, uuid, orderByComparator);
	}

	/**
	 * Removes all the form send record field signalements where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of form send record field signalements where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching form send record field signalements
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the form send record field signalement where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFormSendRecordFieldSignalementException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByUUID_G(
			String uuid, long groupId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the form send record field signalement where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the form send record field signalement where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the form send record field signalement where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the form send record field signalement that was removed
	 */
	public static FormSendRecordFieldSignalement removeByUUID_G(
			String uuid, long groupId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of form send record field signalements where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching form send record field signalements
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement[] findByUuid_C_PrevAndNext(
			long signalementId, String uuid, long companyId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByUuid_C_PrevAndNext(
			signalementId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the form send record field signalements where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching form send record field signalements
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the form send record field signalements where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByGroupId(
		long groupId) {

		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the form send record field signalements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByGroupId_First(
			long groupId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByGroupId_First(
		long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByGroupId_Last(
			long groupId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByGroupId_Last(
		long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement[] findByGroupId_PrevAndNext(
			long signalementId, long groupId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByGroupId_PrevAndNext(
			signalementId, groupId, orderByComparator);
	}

	/**
	 * Removes all the form send record field signalements where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of form send record field signalements where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching form send record field signalements
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the form send record field signalements where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByPublikId(
		String publikId) {

		return getPersistence().findByPublikId(publikId);
	}

	/**
	 * Returns a range of all the form send record field signalements where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByPublikId(
		String publikId, int start, int end) {

		return getPersistence().findByPublikId(publikId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByPublikId_First(
			String publikId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByPublikId_First(
		String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement findByPublikId_Last(
			String publikId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByPublikId_Last(
		String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().fetchByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement[] findByPublikId_PrevAndNext(
			long signalementId, String publikId,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByPublikId_PrevAndNext(
			signalementId, publikId, orderByComparator);
	}

	/**
	 * Removes all the form send record field signalements where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public static void removeByPublikId(String publikId) {
		getPersistence().removeByPublikId(publikId);
	}

	/**
	 * Returns the number of form send record field signalements where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching form send record field signalements
	 */
	public static int countByPublikId(String publikId) {
		return getPersistence().countByPublikId(publikId);
	}

	/**
	 * Returns all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @return the matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement>
		findByFormSendRecordFieldId(long formSendRecordFieldId) {

		return getPersistence().findByFormSendRecordFieldId(
			formSendRecordFieldId);
	}

	/**
	 * Returns a range of all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement>
		findByFormSendRecordFieldId(
			long formSendRecordFieldId, int start, int end) {

		return getPersistence().findByFormSendRecordFieldId(
			formSendRecordFieldId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement>
		findByFormSendRecordFieldId(
			long formSendRecordFieldId, int start, int end,
			OrderByComparator<FormSendRecordFieldSignalement>
				orderByComparator) {

		return getPersistence().findByFormSendRecordFieldId(
			formSendRecordFieldId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement>
		findByFormSendRecordFieldId(
			long formSendRecordFieldId, int start, int end,
			OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
			boolean retrieveFromCache) {

		return getPersistence().findByFormSendRecordFieldId(
			formSendRecordFieldId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement
			findByFormSendRecordFieldId_First(
				long formSendRecordFieldId,
				OrderByComparator<FormSendRecordFieldSignalement>
					orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByFormSendRecordFieldId_First(
			formSendRecordFieldId, orderByComparator);
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement
		fetchByFormSendRecordFieldId_First(
			long formSendRecordFieldId,
			OrderByComparator<FormSendRecordFieldSignalement>
				orderByComparator) {

		return getPersistence().fetchByFormSendRecordFieldId_First(
			formSendRecordFieldId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement
			findByFormSendRecordFieldId_Last(
				long formSendRecordFieldId,
				OrderByComparator<FormSendRecordFieldSignalement>
					orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByFormSendRecordFieldId_Last(
			formSendRecordFieldId, orderByComparator);
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	public static FormSendRecordFieldSignalement
		fetchByFormSendRecordFieldId_Last(
			long formSendRecordFieldId,
			OrderByComparator<FormSendRecordFieldSignalement>
				orderByComparator) {

		return getPersistence().fetchByFormSendRecordFieldId_Last(
			formSendRecordFieldId, orderByComparator);
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement[]
			findByFormSendRecordFieldId_PrevAndNext(
				long signalementId, long formSendRecordFieldId,
				OrderByComparator<FormSendRecordFieldSignalement>
					orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByFormSendRecordFieldId_PrevAndNext(
			signalementId, formSendRecordFieldId, orderByComparator);
	}

	/**
	 * Removes all the form send record field signalements where formSendRecordFieldId = &#63; from the database.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 */
	public static void removeByFormSendRecordFieldId(
		long formSendRecordFieldId) {

		getPersistence().removeByFormSendRecordFieldId(formSendRecordFieldId);
	}

	/**
	 * Returns the number of form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @return the number of matching form send record field signalements
	 */
	public static int countByFormSendRecordFieldId(long formSendRecordFieldId) {
		return getPersistence().countByFormSendRecordFieldId(
			formSendRecordFieldId);
	}

	/**
	 * Caches the form send record field signalement in the entity cache if it is enabled.
	 *
	 * @param formSendRecordFieldSignalement the form send record field signalement
	 */
	public static void cacheResult(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {

		getPersistence().cacheResult(formSendRecordFieldSignalement);
	}

	/**
	 * Caches the form send record field signalements in the entity cache if it is enabled.
	 *
	 * @param formSendRecordFieldSignalements the form send record field signalements
	 */
	public static void cacheResult(
		List<FormSendRecordFieldSignalement> formSendRecordFieldSignalements) {

		getPersistence().cacheResult(formSendRecordFieldSignalements);
	}

	/**
	 * Creates a new form send record field signalement with the primary key. Does not add the form send record field signalement to the database.
	 *
	 * @param signalementId the primary key for the new form send record field signalement
	 * @return the new form send record field signalement
	 */
	public static FormSendRecordFieldSignalement create(long signalementId) {
		return getPersistence().create(signalementId);
	}

	/**
	 * Removes the form send record field signalement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement that was removed
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement remove(long signalementId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().remove(signalementId);
	}

	public static FormSendRecordFieldSignalement updateImpl(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {

		return getPersistence().updateImpl(formSendRecordFieldSignalement);
	}

	/**
	 * Returns the form send record field signalement with the primary key or throws a <code>NoSuchFormSendRecordFieldSignalementException</code> if it could not be found.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement findByPrimaryKey(
			long signalementId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldSignalementException {

		return getPersistence().findByPrimaryKey(signalementId);
	}

	/**
	 * Returns the form send record field signalement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement, or <code>null</code> if a form send record field signalement with the primary key could not be found
	 */
	public static FormSendRecordFieldSignalement fetchByPrimaryKey(
		long signalementId) {

		return getPersistence().fetchByPrimaryKey(signalementId);
	}

	/**
	 * Returns all the form send record field signalements.
	 *
	 * @return the form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the form send record field signalements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findAll(
		int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldSignalementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of form send record field signalements
	 */
	public static List<FormSendRecordFieldSignalement> findAll(
		int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the form send record field signalements from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of form send record field signalements.
	 *
	 * @return the number of form send record field signalements
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FormSendRecordFieldSignalementPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<FormSendRecordFieldSignalementPersistence,
		 FormSendRecordFieldSignalementPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			FormSendRecordFieldSignalementPersistence.class);

		ServiceTracker
			<FormSendRecordFieldSignalementPersistence,
			 FormSendRecordFieldSignalementPersistence> serviceTracker =
				new ServiceTracker
					<FormSendRecordFieldSignalementPersistence,
					 FormSendRecordFieldSignalementPersistence>(
						 bundle.getBundleContext(),
						 FormSendRecordFieldSignalementPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}