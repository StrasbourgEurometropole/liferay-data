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

import eu.strasbourg.service.place.exception.NoSuchPublicHolidayException;
import eu.strasbourg.service.place.model.PublicHoliday;

/**
 * The persistence interface for the public holiday service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.persistence.impl.PublicHolidayPersistenceImpl
 * @see PublicHolidayUtil
 * @generated
 */
@ProviderType
public interface PublicHolidayPersistence extends BasePersistence<PublicHoliday> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PublicHolidayUtil} to access the public holiday persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the public holidaies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the public holidaies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @return the range of matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the public holidaies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator);

	/**
	* Returns an ordered range of all the public holidaies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first public holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching public holiday
	* @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	*/
	public PublicHoliday findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException;

	/**
	* Returns the first public holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching public holiday, or <code>null</code> if a matching public holiday could not be found
	*/
	public PublicHoliday fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator);

	/**
	* Returns the last public holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching public holiday
	* @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	*/
	public PublicHoliday findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException;

	/**
	* Returns the last public holiday in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching public holiday, or <code>null</code> if a matching public holiday could not be found
	*/
	public PublicHoliday fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator);

	/**
	* Returns the public holidaies before and after the current public holiday in the ordered set where uuid = &#63;.
	*
	* @param publicHolidayId the primary key of the current public holiday
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next public holiday
	* @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	*/
	public PublicHoliday[] findByUuid_PrevAndNext(long publicHolidayId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException;

	/**
	* Removes all the public holidaies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of public holidaies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching public holidaies
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the public holidaies where recurrent = &#63;.
	*
	* @param recurrent the recurrent
	* @return the matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByRecurrent(boolean recurrent);

	/**
	* Returns a range of all the public holidaies where recurrent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param recurrent the recurrent
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @return the range of matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByRecurrent(boolean recurrent,
		int start, int end);

	/**
	* Returns an ordered range of all the public holidaies where recurrent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param recurrent the recurrent
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByRecurrent(boolean recurrent,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator);

	/**
	* Returns an ordered range of all the public holidaies where recurrent = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param recurrent the recurrent
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching public holidaies
	*/
	public java.util.List<PublicHoliday> findByRecurrent(boolean recurrent,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first public holiday in the ordered set where recurrent = &#63;.
	*
	* @param recurrent the recurrent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching public holiday
	* @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	*/
	public PublicHoliday findByRecurrent_First(boolean recurrent,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException;

	/**
	* Returns the first public holiday in the ordered set where recurrent = &#63;.
	*
	* @param recurrent the recurrent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching public holiday, or <code>null</code> if a matching public holiday could not be found
	*/
	public PublicHoliday fetchByRecurrent_First(boolean recurrent,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator);

	/**
	* Returns the last public holiday in the ordered set where recurrent = &#63;.
	*
	* @param recurrent the recurrent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching public holiday
	* @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	*/
	public PublicHoliday findByRecurrent_Last(boolean recurrent,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException;

	/**
	* Returns the last public holiday in the ordered set where recurrent = &#63;.
	*
	* @param recurrent the recurrent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching public holiday, or <code>null</code> if a matching public holiday could not be found
	*/
	public PublicHoliday fetchByRecurrent_Last(boolean recurrent,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator);

	/**
	* Returns the public holidaies before and after the current public holiday in the ordered set where recurrent = &#63;.
	*
	* @param publicHolidayId the primary key of the current public holiday
	* @param recurrent the recurrent
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next public holiday
	* @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	*/
	public PublicHoliday[] findByRecurrent_PrevAndNext(long publicHolidayId,
		boolean recurrent,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator)
		throws NoSuchPublicHolidayException;

	/**
	* Removes all the public holidaies where recurrent = &#63; from the database.
	*
	* @param recurrent the recurrent
	*/
	public void removeByRecurrent(boolean recurrent);

	/**
	* Returns the number of public holidaies where recurrent = &#63;.
	*
	* @param recurrent the recurrent
	* @return the number of matching public holidaies
	*/
	public int countByRecurrent(boolean recurrent);

	/**
	* Caches the public holiday in the entity cache if it is enabled.
	*
	* @param publicHoliday the public holiday
	*/
	public void cacheResult(PublicHoliday publicHoliday);

	/**
	* Caches the public holidaies in the entity cache if it is enabled.
	*
	* @param publicHolidaies the public holidaies
	*/
	public void cacheResult(java.util.List<PublicHoliday> publicHolidaies);

	/**
	* Creates a new public holiday with the primary key. Does not add the public holiday to the database.
	*
	* @param publicHolidayId the primary key for the new public holiday
	* @return the new public holiday
	*/
	public PublicHoliday create(long publicHolidayId);

	/**
	* Removes the public holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publicHolidayId the primary key of the public holiday
	* @return the public holiday that was removed
	* @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	*/
	public PublicHoliday remove(long publicHolidayId)
		throws NoSuchPublicHolidayException;

	public PublicHoliday updateImpl(PublicHoliday publicHoliday);

	/**
	* Returns the public holiday with the primary key or throws a {@link NoSuchPublicHolidayException} if it could not be found.
	*
	* @param publicHolidayId the primary key of the public holiday
	* @return the public holiday
	* @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	*/
	public PublicHoliday findByPrimaryKey(long publicHolidayId)
		throws NoSuchPublicHolidayException;

	/**
	* Returns the public holiday with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param publicHolidayId the primary key of the public holiday
	* @return the public holiday, or <code>null</code> if a public holiday with the primary key could not be found
	*/
	public PublicHoliday fetchByPrimaryKey(long publicHolidayId);

	@Override
	public java.util.Map<java.io.Serializable, PublicHoliday> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the public holidaies.
	*
	* @return the public holidaies
	*/
	public java.util.List<PublicHoliday> findAll();

	/**
	* Returns a range of all the public holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @return the range of public holidaies
	*/
	public java.util.List<PublicHoliday> findAll(int start, int end);

	/**
	* Returns an ordered range of all the public holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of public holidaies
	*/
	public java.util.List<PublicHoliday> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator);

	/**
	* Returns an ordered range of all the public holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublicHolidayModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of public holidaies
	* @param end the upper bound of the range of public holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of public holidaies
	*/
	public java.util.List<PublicHoliday> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublicHoliday> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the public holidaies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of public holidaies.
	*
	* @return the number of public holidaies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}