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

import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the form send record field service. This utility wraps <code>eu.strasbourg.service.formSendRecordField.service.persistence.impl.FormSendRecordFieldPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldPersistence
 * @generated
 */
@ProviderType
public class FormSendRecordFieldUtil {

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
	public static void clearCache(FormSendRecordField formSendRecordField) {
		getPersistence().clearCache(formSendRecordField);
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
	public static Map<Serializable, FormSendRecordField> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FormSendRecordField> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FormSendRecordField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FormSendRecordField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FormSendRecordField update(
		FormSendRecordField formSendRecordField) {

		return getPersistence().update(formSendRecordField);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FormSendRecordField update(
		FormSendRecordField formSendRecordField,
		ServiceContext serviceContext) {

		return getPersistence().update(formSendRecordField, serviceContext);
	}

	/**
	 * Returns all the form send record fields where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the form send record fields where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @return the range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record fields where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record fields where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByUuid_First(
			String uuid,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByUuid_First(
		String uuid, OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByUuid_Last(
			String uuid,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByUuid_Last(
		String uuid, OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField[] findByUuid_PrevAndNext(
			long formSendRecordFieldId, String uuid,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByUuid_PrevAndNext(
			formSendRecordFieldId, uuid, orderByComparator);
	}

	/**
	 * Removes all the form send record fields where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of form send record fields where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching form send record fields
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the form send record field where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFormSendRecordFieldException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the form send record field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the form send record field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the form send record field where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the form send record field that was removed
	 */
	public static FormSendRecordField removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of form send record fields where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching form send record fields
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the form send record fields where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the form send record fields where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @return the range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record fields where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record fields where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField[] findByUuid_C_PrevAndNext(
			long formSendRecordFieldId, String uuid, long companyId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByUuid_C_PrevAndNext(
			formSendRecordFieldId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the form send record fields where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of form send record fields where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching form send record fields
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the form send record fields where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching form send record fields
	 */
	public static List<FormSendRecordField> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the form send record fields where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @return the range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record fields where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record fields where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByGroupId_First(
			long groupId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByGroupId_First(
		long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByGroupId_Last(
			long groupId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByGroupId_Last(
		long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField[] findByGroupId_PrevAndNext(
			long formSendRecordFieldId, long groupId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByGroupId_PrevAndNext(
			formSendRecordFieldId, groupId, orderByComparator);
	}

	/**
	 * Removes all the form send record fields where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of form send record fields where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching form send record fields
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the form send record fields where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @return the matching form send record fields
	 */
	public static List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status) {

		return getPersistence().findByAssetEntryId(assetEntryId, status);
	}

	/**
	 * Returns a range of all the form send record fields where assetEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @return the range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status, int start, int end) {

		return getPersistence().findByAssetEntryId(
			assetEntryId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record fields where assetEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findByAssetEntryId(
			assetEntryId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record fields where assetEntryId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByAssetEntryId(
			assetEntryId, status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByAssetEntryId_First(
			long assetEntryId, int status,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByAssetEntryId_First(
			assetEntryId, status, orderByComparator);
	}

	/**
	 * Returns the first form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByAssetEntryId_First(
		long assetEntryId, int status,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByAssetEntryId_First(
			assetEntryId, status, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByAssetEntryId_Last(
			long assetEntryId, int status,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByAssetEntryId_Last(
			assetEntryId, status, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByAssetEntryId_Last(
		long assetEntryId, int status,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByAssetEntryId_Last(
			assetEntryId, status, orderByComparator);
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField[] findByAssetEntryId_PrevAndNext(
			long formSendRecordFieldId, long assetEntryId, int status,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByAssetEntryId_PrevAndNext(
			formSendRecordFieldId, assetEntryId, status, orderByComparator);
	}

	/**
	 * Removes all the form send record fields where assetEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 */
	public static void removeByAssetEntryId(long assetEntryId, int status) {
		getPersistence().removeByAssetEntryId(assetEntryId, status);
	}

	/**
	 * Returns the number of form send record fields where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @return the number of matching form send record fields
	 */
	public static int countByAssetEntryId(long assetEntryId, int status) {
		return getPersistence().countByAssetEntryId(assetEntryId, status);
	}

	/**
	 * Returns all the form send record fields where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @return the matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentId(long contentId) {
		return getPersistence().findByContentId(contentId);
	}

	/**
	 * Returns a range of all the form send record fields where contentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentId the content ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @return the range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentId(
		long contentId, int start, int end) {

		return getPersistence().findByContentId(contentId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record fields where contentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentId the content ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentId(
		long contentId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findByContentId(
			contentId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record fields where contentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentId the content ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentId(
		long contentId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByContentId(
			contentId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByContentId_First(
			long contentId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByContentId_First(
			contentId, orderByComparator);
	}

	/**
	 * Returns the first form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByContentId_First(
		long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByContentId_First(
			contentId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByContentId_Last(
			long contentId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByContentId_Last(
			contentId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByContentId_Last(
		long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByContentId_Last(
			contentId, orderByComparator);
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField[] findByContentId_PrevAndNext(
			long formSendRecordFieldId, long contentId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByContentId_PrevAndNext(
			formSendRecordFieldId, contentId, orderByComparator);
	}

	/**
	 * Removes all the form send record fields where contentId = &#63; from the database.
	 *
	 * @param contentId the content ID
	 */
	public static void removeByContentId(long contentId) {
		getPersistence().removeByContentId(contentId);
	}

	/**
	 * Returns the number of form send record fields where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @return the number of matching form send record fields
	 */
	public static int countByContentId(long contentId) {
		return getPersistence().countByContentId(contentId);
	}

	/**
	 * Returns all the form send record fields where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @return the matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId) {

		return getPersistence().findByContentAndInstanceId(
			contentId, instanceId);
	}

	/**
	 * Returns a range of all the form send record fields where contentId = &#63; and instanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @return the range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId, int start, int end) {

		return getPersistence().findByContentAndInstanceId(
			contentId, instanceId, start, end);
	}

	/**
	 * Returns an ordered range of all the form send record fields where contentId = &#63; and instanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findByContentAndInstanceId(
			contentId, instanceId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record fields where contentId = &#63; and instanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record fields
	 */
	public static List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByContentAndInstanceId(
			contentId, instanceId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	 * Returns the first form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByContentAndInstanceId_First(
			long contentId, String instanceId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByContentAndInstanceId_First(
			contentId, instanceId, orderByComparator);
	}

	/**
	 * Returns the first form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByContentAndInstanceId_First(
		long contentId, String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByContentAndInstanceId_First(
			contentId, instanceId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	public static FormSendRecordField findByContentAndInstanceId_Last(
			long contentId, String instanceId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByContentAndInstanceId_Last(
			contentId, instanceId, orderByComparator);
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	public static FormSendRecordField fetchByContentAndInstanceId_Last(
		long contentId, String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().fetchByContentAndInstanceId_Last(
			contentId, instanceId, orderByComparator);
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField[] findByContentAndInstanceId_PrevAndNext(
			long formSendRecordFieldId, long contentId, String instanceId,
			OrderByComparator<FormSendRecordField> orderByComparator)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByContentAndInstanceId_PrevAndNext(
			formSendRecordFieldId, contentId, instanceId, orderByComparator);
	}

	/**
	 * Removes all the form send record fields where contentId = &#63; and instanceId = &#63; from the database.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 */
	public static void removeByContentAndInstanceId(
		long contentId, String instanceId) {

		getPersistence().removeByContentAndInstanceId(contentId, instanceId);
	}

	/**
	 * Returns the number of form send record fields where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @return the number of matching form send record fields
	 */
	public static int countByContentAndInstanceId(
		long contentId, String instanceId) {

		return getPersistence().countByContentAndInstanceId(
			contentId, instanceId);
	}

	/**
	 * Caches the form send record field in the entity cache if it is enabled.
	 *
	 * @param formSendRecordField the form send record field
	 */
	public static void cacheResult(FormSendRecordField formSendRecordField) {
		getPersistence().cacheResult(formSendRecordField);
	}

	/**
	 * Caches the form send record fields in the entity cache if it is enabled.
	 *
	 * @param formSendRecordFields the form send record fields
	 */
	public static void cacheResult(
		List<FormSendRecordField> formSendRecordFields) {

		getPersistence().cacheResult(formSendRecordFields);
	}

	/**
	 * Creates a new form send record field with the primary key. Does not add the form send record field to the database.
	 *
	 * @param formSendRecordFieldId the primary key for the new form send record field
	 * @return the new form send record field
	 */
	public static FormSendRecordField create(long formSendRecordFieldId) {
		return getPersistence().create(formSendRecordFieldId);
	}

	/**
	 * Removes the form send record field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formSendRecordFieldId the primary key of the form send record field
	 * @return the form send record field that was removed
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField remove(long formSendRecordFieldId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().remove(formSendRecordFieldId);
	}

	public static FormSendRecordField updateImpl(
		FormSendRecordField formSendRecordField) {

		return getPersistence().updateImpl(formSendRecordField);
	}

	/**
	 * Returns the form send record field with the primary key or throws a <code>NoSuchFormSendRecordFieldException</code> if it could not be found.
	 *
	 * @param formSendRecordFieldId the primary key of the form send record field
	 * @return the form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField findByPrimaryKey(
			long formSendRecordFieldId)
		throws eu.strasbourg.service.formSendRecordField.exception.
			NoSuchFormSendRecordFieldException {

		return getPersistence().findByPrimaryKey(formSendRecordFieldId);
	}

	/**
	 * Returns the form send record field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formSendRecordFieldId the primary key of the form send record field
	 * @return the form send record field, or <code>null</code> if a form send record field with the primary key could not be found
	 */
	public static FormSendRecordField fetchByPrimaryKey(
		long formSendRecordFieldId) {

		return getPersistence().fetchByPrimaryKey(formSendRecordFieldId);
	}

	/**
	 * Returns all the form send record fields.
	 *
	 * @return the form send record fields
	 */
	public static List<FormSendRecordField> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the form send record fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @return the range of form send record fields
	 */
	public static List<FormSendRecordField> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the form send record fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form send record fields
	 */
	public static List<FormSendRecordField> findAll(
		int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the form send record fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FormSendRecordFieldModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record fields
	 * @param end the upper bound of the range of form send record fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of form send record fields
	 */
	public static List<FormSendRecordField> findAll(
		int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the form send record fields from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of form send record fields.
	 *
	 * @return the number of form send record fields
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FormSendRecordFieldPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<FormSendRecordFieldPersistence, FormSendRecordFieldPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			FormSendRecordFieldPersistence.class);

		ServiceTracker
			<FormSendRecordFieldPersistence, FormSendRecordFieldPersistence>
				serviceTracker =
					new ServiceTracker
						<FormSendRecordFieldPersistence,
						 FormSendRecordFieldPersistence>(
							 bundle.getBundleContext(),
							 FormSendRecordFieldPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}