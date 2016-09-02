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

package eu.strasbourg.service.edition.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.edition.model.Edition;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the edition service. This utility wraps {@link eu.strasbourg.service.edition.service.persistence.impl.EditionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EditionPersistence
 * @see eu.strasbourg.service.edition.service.persistence.impl.EditionPersistenceImpl
 * @generated
 */
@ProviderType
public class EditionUtil {
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
	public static void clearCache(Edition edition) {
		getPersistence().clearCache(edition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Edition> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Edition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Edition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Edition update(Edition edition) {
		return getPersistence().update(edition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Edition update(Edition edition, ServiceContext serviceContext) {
		return getPersistence().update(edition, serviceContext);
	}

	/**
	* Returns all the editions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching editions
	*/
	public static List<Edition> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the editions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of matching editions
	*/
	public static List<Edition> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the editions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Edition> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the editions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Edition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first edition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByUuid_First(java.lang.String uuid,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first edition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the editions before and after the current edition in the ordered set where uuid = &#63;.
	*
	* @param editionId the primary key of the current edition
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition
	* @throws NoSuchEditionException if a edition with the primary key could not be found
	*/
	public static Edition[] findByUuid_PrevAndNext(long editionId,
		java.lang.String uuid, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(editionId, uuid, orderByComparator);
	}

	/**
	* Removes all the editions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of editions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching editions
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the edition where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEditionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the edition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the edition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the edition where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the edition that was removed
	*/
	public static Edition removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of editions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching editions
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the editions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching editions
	*/
	public static List<Edition> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the editions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of matching editions
	*/
	public static List<Edition> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the editions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the editions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first edition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first edition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the editions before and after the current edition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param editionId the primary key of the current edition
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition
	* @throws NoSuchEditionException if a edition with the primary key could not be found
	*/
	public static Edition[] findByUuid_C_PrevAndNext(long editionId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(editionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the editions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of editions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching editions
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the editions where title = &#63;.
	*
	* @param title the title
	* @return the matching editions
	*/
	public static List<Edition> findByTitle(java.lang.String title) {
		return getPersistence().findByTitle(title);
	}

	/**
	* Returns a range of all the editions where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of matching editions
	*/
	public static List<Edition> findByTitle(java.lang.String title, int start,
		int end) {
		return getPersistence().findByTitle(title, start, end);
	}

	/**
	* Returns an ordered range of all the editions where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByTitle(java.lang.String title, int start,
		int end, OrderByComparator<Edition> orderByComparator) {
		return getPersistence().findByTitle(title, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the editions where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByTitle(java.lang.String title, int start,
		int end, OrderByComparator<Edition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTitle(title, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first edition in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByTitle_First(java.lang.String title,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the first edition in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByTitle_First(java.lang.String title,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence().fetchByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByTitle_Last(java.lang.String title,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByTitle_Last(java.lang.String title,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence().fetchByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the editions before and after the current edition in the ordered set where title = &#63;.
	*
	* @param editionId the primary key of the current edition
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition
	* @throws NoSuchEditionException if a edition with the primary key could not be found
	*/
	public static Edition[] findByTitle_PrevAndNext(long editionId,
		java.lang.String title, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByTitle_PrevAndNext(editionId, title, orderByComparator);
	}

	/**
	* Removes all the editions where title = &#63; from the database.
	*
	* @param title the title
	*/
	public static void removeByTitle(java.lang.String title) {
		getPersistence().removeByTitle(title);
	}

	/**
	* Returns the number of editions where title = &#63;.
	*
	* @param title the title
	* @return the number of matching editions
	*/
	public static int countByTitle(java.lang.String title) {
		return getPersistence().countByTitle(title);
	}

	/**
	* Returns all the editions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching editions
	*/
	public static List<Edition> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the editions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of matching editions
	*/
	public static List<Edition> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the editions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the editions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first edition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByGroupId_First(long groupId,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first edition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByGroupId_First(long groupId,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByGroupId_Last(long groupId,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByGroupId_Last(long groupId,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the editions before and after the current edition in the ordered set where groupId = &#63;.
	*
	* @param editionId the primary key of the current edition
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition
	* @throws NoSuchEditionException if a edition with the primary key could not be found
	*/
	public static Edition[] findByGroupId_PrevAndNext(long editionId,
		long groupId, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(editionId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the editions where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of editions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching editions
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the editions where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the matching editions
	*/
	public static List<Edition> findByGroupIdAndTitle(long groupId,
		java.lang.String title) {
		return getPersistence().findByGroupIdAndTitle(groupId, title);
	}

	/**
	* Returns a range of all the editions where groupId = &#63; and title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param title the title
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of matching editions
	*/
	public static List<Edition> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end) {
		return getPersistence().findByGroupIdAndTitle(groupId, title, start, end);
	}

	/**
	* Returns an ordered range of all the editions where groupId = &#63; and title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param title the title
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .findByGroupIdAndTitle(groupId, title, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the editions where groupId = &#63; and title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param title the title
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching editions
	*/
	public static List<Edition> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupIdAndTitle(groupId, title, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first edition in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByGroupIdAndTitle_First(long groupId,
		java.lang.String title, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByGroupIdAndTitle_First(groupId, title,
			orderByComparator);
	}

	/**
	* Returns the first edition in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByGroupIdAndTitle_First(long groupId,
		java.lang.String title, OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndTitle_First(groupId, title,
			orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition
	* @throws NoSuchEditionException if a matching edition could not be found
	*/
	public static Edition findByGroupIdAndTitle_Last(long groupId,
		java.lang.String title, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByGroupIdAndTitle_Last(groupId, title, orderByComparator);
	}

	/**
	* Returns the last edition in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition, or <code>null</code> if a matching edition could not be found
	*/
	public static Edition fetchByGroupIdAndTitle_Last(long groupId,
		java.lang.String title, OrderByComparator<Edition> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndTitle_Last(groupId, title,
			orderByComparator);
	}

	/**
	* Returns the editions before and after the current edition in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param editionId the primary key of the current edition
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition
	* @throws NoSuchEditionException if a edition with the primary key could not be found
	*/
	public static Edition[] findByGroupIdAndTitle_PrevAndNext(long editionId,
		long groupId, java.lang.String title,
		OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence()
				   .findByGroupIdAndTitle_PrevAndNext(editionId, groupId,
			title, orderByComparator);
	}

	/**
	* Removes all the editions where groupId = &#63; and title = &#63; from the database.
	*
	* @param groupId the group ID
	* @param title the title
	*/
	public static void removeByGroupIdAndTitle(long groupId,
		java.lang.String title) {
		getPersistence().removeByGroupIdAndTitle(groupId, title);
	}

	/**
	* Returns the number of editions where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the number of matching editions
	*/
	public static int countByGroupIdAndTitle(long groupId,
		java.lang.String title) {
		return getPersistence().countByGroupIdAndTitle(groupId, title);
	}

	/**
	* Caches the edition in the entity cache if it is enabled.
	*
	* @param edition the edition
	*/
	public static void cacheResult(Edition edition) {
		getPersistence().cacheResult(edition);
	}

	/**
	* Caches the editions in the entity cache if it is enabled.
	*
	* @param editions the editions
	*/
	public static void cacheResult(List<Edition> editions) {
		getPersistence().cacheResult(editions);
	}

	/**
	* Creates a new edition with the primary key. Does not add the edition to the database.
	*
	* @param editionId the primary key for the new edition
	* @return the new edition
	*/
	public static Edition create(long editionId) {
		return getPersistence().create(editionId);
	}

	/**
	* Removes the edition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param editionId the primary key of the edition
	* @return the edition that was removed
	* @throws NoSuchEditionException if a edition with the primary key could not be found
	*/
	public static Edition remove(long editionId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().remove(editionId);
	}

	public static Edition updateImpl(Edition edition) {
		return getPersistence().updateImpl(edition);
	}

	/**
	* Returns the edition with the primary key or throws a {@link NoSuchEditionException} if it could not be found.
	*
	* @param editionId the primary key of the edition
	* @return the edition
	* @throws NoSuchEditionException if a edition with the primary key could not be found
	*/
	public static Edition findByPrimaryKey(long editionId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {
		return getPersistence().findByPrimaryKey(editionId);
	}

	/**
	* Returns the edition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param editionId the primary key of the edition
	* @return the edition, or <code>null</code> if a edition with the primary key could not be found
	*/
	public static Edition fetchByPrimaryKey(long editionId) {
		return getPersistence().fetchByPrimaryKey(editionId);
	}

	public static java.util.Map<java.io.Serializable, Edition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the editions.
	*
	* @return the editions
	*/
	public static List<Edition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the editions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of editions
	*/
	public static List<Edition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the editions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of editions
	*/
	public static List<Edition> findAll(int start, int end,
		OrderByComparator<Edition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the editions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of editions
	*/
	public static List<Edition> findAll(int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the editions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of editions.
	*
	* @return the number of editions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of edition galleries associated with the edition.
	*
	* @param pk the primary key of the edition
	* @return long[] of the primaryKeys of edition galleries associated with the edition
	*/
	public static long[] getEditionGalleryPrimaryKeys(long pk) {
		return getPersistence().getEditionGalleryPrimaryKeys(pk);
	}

	/**
	* Returns all the edition galleries associated with the edition.
	*
	* @param pk the primary key of the edition
	* @return the edition galleries associated with the edition
	*/
	public static List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries(
		long pk) {
		return getPersistence().getEditionGalleries(pk);
	}

	/**
	* Returns a range of all the edition galleries associated with the edition.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the edition
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @return the range of edition galleries associated with the edition
	*/
	public static List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries(
		long pk, int start, int end) {
		return getPersistence().getEditionGalleries(pk, start, end);
	}

	/**
	* Returns an ordered range of all the edition galleries associated with the edition.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the edition
	* @param start the lower bound of the range of editions
	* @param end the upper bound of the range of editions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of edition galleries associated with the edition
	*/
	public static List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.edition.model.EditionGallery> orderByComparator) {
		return getPersistence()
				   .getEditionGalleries(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of edition galleries associated with the edition.
	*
	* @param pk the primary key of the edition
	* @return the number of edition galleries associated with the edition
	*/
	public static int getEditionGalleriesSize(long pk) {
		return getPersistence().getEditionGalleriesSize(pk);
	}

	/**
	* Returns <code>true</code> if the edition gallery is associated with the edition.
	*
	* @param pk the primary key of the edition
	* @param editionGalleryPK the primary key of the edition gallery
	* @return <code>true</code> if the edition gallery is associated with the edition; <code>false</code> otherwise
	*/
	public static boolean containsEditionGallery(long pk, long editionGalleryPK) {
		return getPersistence().containsEditionGallery(pk, editionGalleryPK);
	}

	/**
	* Returns <code>true</code> if the edition has any edition galleries associated with it.
	*
	* @param pk the primary key of the edition to check for associations with edition galleries
	* @return <code>true</code> if the edition has any edition galleries associated with it; <code>false</code> otherwise
	*/
	public static boolean containsEditionGalleries(long pk) {
		return getPersistence().containsEditionGalleries(pk);
	}

	/**
	* Adds an association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleryPK the primary key of the edition gallery
	*/
	public static void addEditionGallery(long pk, long editionGalleryPK) {
		getPersistence().addEditionGallery(pk, editionGalleryPK);
	}

	/**
	* Adds an association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGallery the edition gallery
	*/
	public static void addEditionGallery(long pk,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		getPersistence().addEditionGallery(pk, editionGallery);
	}

	/**
	* Adds an association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleryPKs the primary keys of the edition galleries
	*/
	public static void addEditionGalleries(long pk, long[] editionGalleryPKs) {
		getPersistence().addEditionGalleries(pk, editionGalleryPKs);
	}

	/**
	* Adds an association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleries the edition galleries
	*/
	public static void addEditionGalleries(long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		getPersistence().addEditionGalleries(pk, editionGalleries);
	}

	/**
	* Clears all associations between the edition and its edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition to clear the associated edition galleries from
	*/
	public static void clearEditionGalleries(long pk) {
		getPersistence().clearEditionGalleries(pk);
	}

	/**
	* Removes the association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleryPK the primary key of the edition gallery
	*/
	public static void removeEditionGallery(long pk, long editionGalleryPK) {
		getPersistence().removeEditionGallery(pk, editionGalleryPK);
	}

	/**
	* Removes the association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGallery the edition gallery
	*/
	public static void removeEditionGallery(long pk,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {
		getPersistence().removeEditionGallery(pk, editionGallery);
	}

	/**
	* Removes the association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleryPKs the primary keys of the edition galleries
	*/
	public static void removeEditionGalleries(long pk, long[] editionGalleryPKs) {
		getPersistence().removeEditionGalleries(pk, editionGalleryPKs);
	}

	/**
	* Removes the association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleries the edition galleries
	*/
	public static void removeEditionGalleries(long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		getPersistence().removeEditionGalleries(pk, editionGalleries);
	}

	/**
	* Sets the edition galleries associated with the edition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleryPKs the primary keys of the edition galleries to be associated with the edition
	*/
	public static void setEditionGalleries(long pk, long[] editionGalleryPKs) {
		getPersistence().setEditionGalleries(pk, editionGalleryPKs);
	}

	/**
	* Sets the edition galleries associated with the edition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition
	* @param editionGalleries the edition galleries to be associated with the edition
	*/
	public static void setEditionGalleries(long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery> editionGalleries) {
		getPersistence().setEditionGalleries(pk, editionGalleries);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EditionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EditionPersistence, EditionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EditionPersistence.class);
}