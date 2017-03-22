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

package eu.strasbourg.service.place.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.place.exception.NoSuchScheduleExceptionException;
import eu.strasbourg.service.place.model.ScheduleException;

/**
 * The persistence interface for the schedule exception service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.persistence.impl.ScheduleExceptionPersistenceImpl
 * @see ScheduleExceptionUtil
 * @generated
 */
@ProviderType
public interface ScheduleExceptionPersistence extends BasePersistence<ScheduleException> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ScheduleExceptionUtil} to access the schedule exception persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the schedule exceptions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the schedule exceptions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the schedule exceptions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns an ordered range of all the schedule exceptions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public ScheduleException findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Returns the first schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public ScheduleException fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns the last schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public ScheduleException findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Returns the last schedule exception in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public ScheduleException fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns the schedule exceptions before and after the current schedule exception in the ordered set where uuid = &#63;.
	*
	* @param exceptionId the primary key of the current schedule exception
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public ScheduleException[] findByUuid_PrevAndNext(long exceptionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Removes all the schedule exceptions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of schedule exceptions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching schedule exceptions
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the schedule exceptions where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByPlaceId(long placeId);

	/**
	* Returns a range of all the schedule exceptions where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByPlaceId(long placeId,
		int start, int end);

	/**
	* Returns an ordered range of all the schedule exceptions where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByPlaceId(long placeId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns an ordered range of all the schedule exceptions where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findByPlaceId(long placeId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public ScheduleException findByPlaceId_First(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Returns the first schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public ScheduleException fetchByPlaceId_First(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns the last schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public ScheduleException findByPlaceId_Last(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Returns the last schedule exception in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public ScheduleException fetchByPlaceId_Last(long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns the schedule exceptions before and after the current schedule exception in the ordered set where placeId = &#63;.
	*
	* @param exceptionId the primary key of the current schedule exception
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public ScheduleException[] findByPlaceId_PrevAndNext(long exceptionId,
		long placeId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Removes all the schedule exceptions where placeId = &#63; from the database.
	*
	* @param placeId the place ID
	*/
	public void removeByPlaceId(long placeId);

	/**
	* Returns the number of schedule exceptions where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the number of matching schedule exceptions
	*/
	public int countByPlaceId(long placeId);

	/**
	* Returns all the schedule exceptions where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findBySubPlaceId(long subPlaceId);

	/**
	* Returns a range of all the schedule exceptions where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findBySubPlaceId(long subPlaceId,
		int start, int end);

	/**
	* Returns an ordered range of all the schedule exceptions where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findBySubPlaceId(long subPlaceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns an ordered range of all the schedule exceptions where subPlaceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param subPlaceId the sub place ID
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching schedule exceptions
	*/
	public java.util.List<ScheduleException> findBySubPlaceId(long subPlaceId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public ScheduleException findBySubPlaceId_First(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Returns the first schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public ScheduleException fetchBySubPlaceId_First(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns the last schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception
	* @throws NoSuchScheduleExceptionException if a matching schedule exception could not be found
	*/
	public ScheduleException findBySubPlaceId_Last(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Returns the last schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching schedule exception, or <code>null</code> if a matching schedule exception could not be found
	*/
	public ScheduleException fetchBySubPlaceId_Last(long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns the schedule exceptions before and after the current schedule exception in the ordered set where subPlaceId = &#63;.
	*
	* @param exceptionId the primary key of the current schedule exception
	* @param subPlaceId the sub place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public ScheduleException[] findBySubPlaceId_PrevAndNext(long exceptionId,
		long subPlaceId,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator)
		throws NoSuchScheduleExceptionException;

	/**
	* Removes all the schedule exceptions where subPlaceId = &#63; from the database.
	*
	* @param subPlaceId the sub place ID
	*/
	public void removeBySubPlaceId(long subPlaceId);

	/**
	* Returns the number of schedule exceptions where subPlaceId = &#63;.
	*
	* @param subPlaceId the sub place ID
	* @return the number of matching schedule exceptions
	*/
	public int countBySubPlaceId(long subPlaceId);

	/**
	* Caches the schedule exception in the entity cache if it is enabled.
	*
	* @param scheduleException the schedule exception
	*/
	public void cacheResult(ScheduleException scheduleException);

	/**
	* Caches the schedule exceptions in the entity cache if it is enabled.
	*
	* @param scheduleExceptions the schedule exceptions
	*/
	public void cacheResult(
		java.util.List<ScheduleException> scheduleExceptions);

	/**
	* Creates a new schedule exception with the primary key. Does not add the schedule exception to the database.
	*
	* @param exceptionId the primary key for the new schedule exception
	* @return the new schedule exception
	*/
	public ScheduleException create(long exceptionId);

	/**
	* Removes the schedule exception with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception that was removed
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public ScheduleException remove(long exceptionId)
		throws NoSuchScheduleExceptionException;

	public ScheduleException updateImpl(ScheduleException scheduleException);

	/**
	* Returns the schedule exception with the primary key or throws a {@link NoSuchScheduleExceptionException} if it could not be found.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception
	* @throws NoSuchScheduleExceptionException if a schedule exception with the primary key could not be found
	*/
	public ScheduleException findByPrimaryKey(long exceptionId)
		throws NoSuchScheduleExceptionException;

	/**
	* Returns the schedule exception with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception, or <code>null</code> if a schedule exception with the primary key could not be found
	*/
	public ScheduleException fetchByPrimaryKey(long exceptionId);

	@Override
	public java.util.Map<java.io.Serializable, ScheduleException> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the schedule exceptions.
	*
	* @return the schedule exceptions
	*/
	public java.util.List<ScheduleException> findAll();

	/**
	* Returns a range of all the schedule exceptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of schedule exceptions
	*/
	public java.util.List<ScheduleException> findAll(int start, int end);

	/**
	* Returns an ordered range of all the schedule exceptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of schedule exceptions
	*/
	public java.util.List<ScheduleException> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator);

	/**
	* Returns an ordered range of all the schedule exceptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of schedule exceptions
	*/
	public java.util.List<ScheduleException> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ScheduleException> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the schedule exceptions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of schedule exceptions.
	*
	* @return the number of schedule exceptions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}