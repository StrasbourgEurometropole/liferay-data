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

package eu.strasbourg.service.help.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.help.model.HelpProposal;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the help proposal service. This utility wraps <code>eu.strasbourg.service.help.service.persistence.impl.HelpProposalPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpProposalPersistence
 * @generated
 */
@ProviderType
public class HelpProposalUtil {

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
	public static void clearCache(HelpProposal helpProposal) {
		getPersistence().clearCache(helpProposal);
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
	public static Map<Serializable, HelpProposal> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpProposal> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpProposal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpProposal> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpProposal update(HelpProposal helpProposal) {
		return getPersistence().update(helpProposal);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpProposal update(
		HelpProposal helpProposal, ServiceContext serviceContext) {

		return getPersistence().update(helpProposal, serviceContext);
	}

	/**
	 * Returns all the help proposals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching help proposals
	 */
	public static List<HelpProposal> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the help proposals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public static List<HelpProposal> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByUuid_First(
			String uuid, OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByUuid_First(
		String uuid, OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByUuid_Last(
			String uuid, OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByUuid_Last(
		String uuid, OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public static HelpProposal[] findByUuid_PrevAndNext(
			long helpProposalId, String uuid,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByUuid_PrevAndNext(
			helpProposalId, uuid, orderByComparator);
	}

	/**
	 * Removes all the help proposals where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of help proposals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching help proposals
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the help proposal where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchHelpProposalException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the help proposal where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the help proposal where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the help proposal where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the help proposal that was removed
	 */
	public static HelpProposal removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of help proposals where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching help proposals
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching help proposals
	 */
	public static List<HelpProposal> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public static List<HelpProposal> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public static HelpProposal[] findByUuid_C_PrevAndNext(
			long helpProposalId, String uuid, long companyId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByUuid_C_PrevAndNext(
			helpProposalId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the help proposals where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching help proposals
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the help proposals where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching help proposals
	 */
	public static List<HelpProposal> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the help proposals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public static List<HelpProposal> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the help proposals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help proposals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByGroupId_First(
			long groupId, OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByGroupId_First(
		long groupId, OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByGroupId_Last(
			long groupId, OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByGroupId_Last(
		long groupId, OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public static HelpProposal[] findByGroupId_PrevAndNext(
			long helpProposalId, long groupId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByGroupId_PrevAndNext(
			helpProposalId, groupId, orderByComparator);
	}

	/**
	 * Removes all the help proposals where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of help proposals where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching help proposals
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the help proposals where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching help proposals
	 */
	public static List<HelpProposal> findBypublikId(String publikId) {
		return getPersistence().findBypublikId(publikId);
	}

	/**
	 * Returns a range of all the help proposals where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public static List<HelpProposal> findBypublikId(
		String publikId, int start, int end) {

		return getPersistence().findBypublikId(publikId, start, end);
	}

	/**
	 * Returns an ordered range of all the help proposals where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findBypublikId(
		String publikId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().findBypublikId(
			publikId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help proposals where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findBypublikId(
		String publikId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findBypublikId(
			publikId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findBypublikId_First(
			String publikId, OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findBypublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the first help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchBypublikId_First(
		String publikId, OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchBypublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findBypublikId_Last(
			String publikId, OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findBypublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchBypublikId_Last(
		String publikId, OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchBypublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public static HelpProposal[] findBypublikId_PrevAndNext(
			long helpProposalId, String publikId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findBypublikId_PrevAndNext(
			helpProposalId, publikId, orderByComparator);
	}

	/**
	 * Removes all the help proposals where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public static void removeBypublikId(String publikId) {
		getPersistence().removeBypublikId(publikId);
	}

	/**
	 * Returns the number of help proposals where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching help proposals
	 */
	public static int countBypublikId(String publikId) {
		return getPersistence().countBypublikId(publikId);
	}

	/**
	 * Returns all the help proposals where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the matching help proposals
	 */
	public static List<HelpProposal> findByStatusAndGroupId(
		int status, long groupId) {

		return getPersistence().findByStatusAndGroupId(status, groupId);
	}

	/**
	 * Returns a range of all the help proposals where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public static List<HelpProposal> findByStatusAndGroupId(
		int status, long groupId, int start, int end) {

		return getPersistence().findByStatusAndGroupId(
			status, groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the help proposals where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().findByStatusAndGroupId(
			status, groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help proposals where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public static List<HelpProposal> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		OrderByComparator<HelpProposal> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByStatusAndGroupId(
			status, groupId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help proposal in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByStatusAndGroupId_First(
			int status, long groupId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByStatusAndGroupId_First(
			status, groupId, orderByComparator);
	}

	/**
	 * Returns the first help proposal in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByStatusAndGroupId_First(
		int status, long groupId,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByStatusAndGroupId_First(
			status, groupId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public static HelpProposal findByStatusAndGroupId_Last(
			int status, long groupId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByStatusAndGroupId_Last(
			status, groupId, orderByComparator);
	}

	/**
	 * Returns the last help proposal in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public static HelpProposal fetchByStatusAndGroupId_Last(
		int status, long groupId,
		OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().fetchByStatusAndGroupId_Last(
			status, groupId, orderByComparator);
	}

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public static HelpProposal[] findByStatusAndGroupId_PrevAndNext(
			long helpProposalId, int status, long groupId,
			OrderByComparator<HelpProposal> orderByComparator)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByStatusAndGroupId_PrevAndNext(
			helpProposalId, status, groupId, orderByComparator);
	}

	/**
	 * Removes all the help proposals where status = &#63; and groupId = &#63; from the database.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 */
	public static void removeByStatusAndGroupId(int status, long groupId) {
		getPersistence().removeByStatusAndGroupId(status, groupId);
	}

	/**
	 * Returns the number of help proposals where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the number of matching help proposals
	 */
	public static int countByStatusAndGroupId(int status, long groupId) {
		return getPersistence().countByStatusAndGroupId(status, groupId);
	}

	/**
	 * Caches the help proposal in the entity cache if it is enabled.
	 *
	 * @param helpProposal the help proposal
	 */
	public static void cacheResult(HelpProposal helpProposal) {
		getPersistence().cacheResult(helpProposal);
	}

	/**
	 * Caches the help proposals in the entity cache if it is enabled.
	 *
	 * @param helpProposals the help proposals
	 */
	public static void cacheResult(List<HelpProposal> helpProposals) {
		getPersistence().cacheResult(helpProposals);
	}

	/**
	 * Creates a new help proposal with the primary key. Does not add the help proposal to the database.
	 *
	 * @param helpProposalId the primary key for the new help proposal
	 * @return the new help proposal
	 */
	public static HelpProposal create(long helpProposalId) {
		return getPersistence().create(helpProposalId);
	}

	/**
	 * Removes the help proposal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal that was removed
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public static HelpProposal remove(long helpProposalId)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().remove(helpProposalId);
	}

	public static HelpProposal updateImpl(HelpProposal helpProposal) {
		return getPersistence().updateImpl(helpProposal);
	}

	/**
	 * Returns the help proposal with the primary key or throws a <code>NoSuchHelpProposalException</code> if it could not be found.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public static HelpProposal findByPrimaryKey(long helpProposalId)
		throws eu.strasbourg.service.help.exception.
			NoSuchHelpProposalException {

		return getPersistence().findByPrimaryKey(helpProposalId);
	}

	/**
	 * Returns the help proposal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal, or <code>null</code> if a help proposal with the primary key could not be found
	 */
	public static HelpProposal fetchByPrimaryKey(long helpProposalId) {
		return getPersistence().fetchByPrimaryKey(helpProposalId);
	}

	/**
	 * Returns all the help proposals.
	 *
	 * @return the help proposals
	 */
	public static List<HelpProposal> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help proposals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of help proposals
	 */
	public static List<HelpProposal> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help proposals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help proposals
	 */
	public static List<HelpProposal> findAll(
		int start, int end, OrderByComparator<HelpProposal> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help proposals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of help proposals
	 */
	public static List<HelpProposal> findAll(
		int start, int end, OrderByComparator<HelpProposal> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the help proposals from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help proposals.
	 *
	 * @return the number of help proposals
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpProposalPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<HelpProposalPersistence, HelpProposalPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HelpProposalPersistence.class);

		ServiceTracker<HelpProposalPersistence, HelpProposalPersistence>
			serviceTracker =
				new ServiceTracker
					<HelpProposalPersistence, HelpProposalPersistence>(
						bundle.getBundleContext(),
						HelpProposalPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}