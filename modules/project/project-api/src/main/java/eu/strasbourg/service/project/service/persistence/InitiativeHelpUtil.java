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

package eu.strasbourg.service.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.project.model.InitiativeHelp;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the initiative help service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.InitiativeHelpPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeHelpPersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.InitiativeHelpPersistenceImpl
 * @generated
 */
@ProviderType
public class InitiativeHelpUtil {
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
	public static void clearCache(InitiativeHelp initiativeHelp) {
		getPersistence().clearCache(initiativeHelp);
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
	public static List<InitiativeHelp> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<InitiativeHelp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<InitiativeHelp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static InitiativeHelp update(InitiativeHelp initiativeHelp) {
		return getPersistence().update(initiativeHelp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static InitiativeHelp update(InitiativeHelp initiativeHelp,
		ServiceContext serviceContext) {
		return getPersistence().update(initiativeHelp, serviceContext);
	}

	/**
	* Returns all the initiative helps where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching initiative helps
	*/
	public static List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the initiative helps where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of matching initiative helps
	*/
	public static List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId, int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the initiative helps where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiative helps
	*/
	public static List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the initiative helps where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiative helps
	*/
	public static List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public static InitiativeHelp findByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public static InitiativeHelp fetchByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public static InitiativeHelp findByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public static InitiativeHelp fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the initiative helps before and after the current initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param initiativeHelpId the primary key of the current initiative help
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative help
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public static InitiativeHelp[] findByPublikUserId_PrevAndNext(
		long initiativeHelpId, java.lang.String publikUserId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(initiativeHelpId,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the initiative helps where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of initiative helps where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching initiative helps
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Returns all the initiative helps where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @return the matching initiative helps
	*/
	public static List<InitiativeHelp> findByinitiativeId(long initiativeId) {
		return getPersistence().findByinitiativeId(initiativeId);
	}

	/**
	* Returns a range of all the initiative helps where initiativeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param initiativeId the initiative ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of matching initiative helps
	*/
	public static List<InitiativeHelp> findByinitiativeId(long initiativeId,
		int start, int end) {
		return getPersistence().findByinitiativeId(initiativeId, start, end);
	}

	/**
	* Returns an ordered range of all the initiative helps where initiativeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param initiativeId the initiative ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiative helps
	*/
	public static List<InitiativeHelp> findByinitiativeId(long initiativeId,
		int start, int end, OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence()
				   .findByinitiativeId(initiativeId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the initiative helps where initiativeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param initiativeId the initiative ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiative helps
	*/
	public static List<InitiativeHelp> findByinitiativeId(long initiativeId,
		int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByinitiativeId(initiativeId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public static InitiativeHelp findByinitiativeId_First(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .findByinitiativeId_First(initiativeId, orderByComparator);
	}

	/**
	* Returns the first initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public static InitiativeHelp fetchByinitiativeId_First(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence()
				   .fetchByinitiativeId_First(initiativeId, orderByComparator);
	}

	/**
	* Returns the last initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public static InitiativeHelp findByinitiativeId_Last(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .findByinitiativeId_Last(initiativeId, orderByComparator);
	}

	/**
	* Returns the last initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public static InitiativeHelp fetchByinitiativeId_Last(long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence()
				   .fetchByinitiativeId_Last(initiativeId, orderByComparator);
	}

	/**
	* Returns the initiative helps before and after the current initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeHelpId the primary key of the current initiative help
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative help
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public static InitiativeHelp[] findByinitiativeId_PrevAndNext(
		long initiativeHelpId, long initiativeId,
		OrderByComparator<InitiativeHelp> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .findByinitiativeId_PrevAndNext(initiativeHelpId,
			initiativeId, orderByComparator);
	}

	/**
	* Removes all the initiative helps where initiativeId = &#63; from the database.
	*
	* @param initiativeId the initiative ID
	*/
	public static void removeByinitiativeId(long initiativeId) {
		getPersistence().removeByinitiativeId(initiativeId);
	}

	/**
	* Returns the number of initiative helps where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @return the number of matching initiative helps
	*/
	public static int countByinitiativeId(long initiativeId) {
		return getPersistence().countByinitiativeId(initiativeId);
	}

	/**
	* Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or throws a {@link NoSuchInitiativeHelpException} if it could not be found.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public static InitiativeHelp findByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .findByPublikUserIdAndInitiativeId(publikUserId, initiativeId);
	}

	/**
	* Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public static InitiativeHelp fetchByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId) {
		return getPersistence()
				   .fetchByPublikUserIdAndInitiativeId(publikUserId,
			initiativeId);
	}

	/**
	* Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public static InitiativeHelp fetchByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByPublikUserIdAndInitiativeId(publikUserId,
			initiativeId, retrieveFromCache);
	}

	/**
	* Removes the initiative help where publikUserId = &#63; and initiativeId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the initiative help that was removed
	*/
	public static InitiativeHelp removeByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence()
				   .removeByPublikUserIdAndInitiativeId(publikUserId,
			initiativeId);
	}

	/**
	* Returns the number of initiative helps where publikUserId = &#63; and initiativeId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the number of matching initiative helps
	*/
	public static int countByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId) {
		return getPersistence()
				   .countByPublikUserIdAndInitiativeId(publikUserId,
			initiativeId);
	}

	/**
	* Caches the initiative help in the entity cache if it is enabled.
	*
	* @param initiativeHelp the initiative help
	*/
	public static void cacheResult(InitiativeHelp initiativeHelp) {
		getPersistence().cacheResult(initiativeHelp);
	}

	/**
	* Caches the initiative helps in the entity cache if it is enabled.
	*
	* @param initiativeHelps the initiative helps
	*/
	public static void cacheResult(List<InitiativeHelp> initiativeHelps) {
		getPersistence().cacheResult(initiativeHelps);
	}

	/**
	* Creates a new initiative help with the primary key. Does not add the initiative help to the database.
	*
	* @param initiativeHelpId the primary key for the new initiative help
	* @return the new initiative help
	*/
	public static InitiativeHelp create(long initiativeHelpId) {
		return getPersistence().create(initiativeHelpId);
	}

	/**
	* Removes the initiative help with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help that was removed
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public static InitiativeHelp remove(long initiativeHelpId)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence().remove(initiativeHelpId);
	}

	public static InitiativeHelp updateImpl(InitiativeHelp initiativeHelp) {
		return getPersistence().updateImpl(initiativeHelp);
	}

	/**
	* Returns the initiative help with the primary key or throws a {@link NoSuchInitiativeHelpException} if it could not be found.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public static InitiativeHelp findByPrimaryKey(long initiativeHelpId)
		throws eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException {
		return getPersistence().findByPrimaryKey(initiativeHelpId);
	}

	/**
	* Returns the initiative help with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help, or <code>null</code> if a initiative help with the primary key could not be found
	*/
	public static InitiativeHelp fetchByPrimaryKey(long initiativeHelpId) {
		return getPersistence().fetchByPrimaryKey(initiativeHelpId);
	}

	public static java.util.Map<java.io.Serializable, InitiativeHelp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the initiative helps.
	*
	* @return the initiative helps
	*/
	public static List<InitiativeHelp> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the initiative helps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of initiative helps
	*/
	public static List<InitiativeHelp> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the initiative helps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of initiative helps
	*/
	public static List<InitiativeHelp> findAll(int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the initiative helps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of initiative helps
	*/
	public static List<InitiativeHelp> findAll(int start, int end,
		OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the initiative helps from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of initiative helps.
	*
	* @return the number of initiative helps
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static InitiativeHelpPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<InitiativeHelpPersistence, InitiativeHelpPersistence> _serviceTracker =
		ServiceTrackerFactory.open(InitiativeHelpPersistence.class);
}