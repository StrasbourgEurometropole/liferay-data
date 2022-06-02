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

import eu.strasbourg.service.place.exception.NoSuchCsmapCacheJsonException;
import eu.strasbourg.service.place.model.CsmapCacheJson;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the csmap cache json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see CsmapCacheJsonUtil
 * @generated
 */
@ProviderType
public interface CsmapCacheJsonPersistence
	extends BasePersistence<CsmapCacheJson> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CsmapCacheJsonUtil} to access the csmap cache json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CsmapCacheJson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(String uuid);

	/**
	 * Returns a range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[] findByUuid_PrevAndNext(
			String sigId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching csmap cache jsons
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the csmap cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findBysigId(String sigId);

	/**
	 * Returns a range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where sigId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param sigId the sig ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findBysigId(
		String sigId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findBysigId_First(
			String sigId,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchBysigId_First(
		String sigId,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findBysigId_Last(
			String sigId,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchBysigId_Last(
		String sigId,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Removes all the csmap cache jsons where sigId = &#63; from the database.
	 *
	 * @param sigId the sig ID
	 */
	public void removeBysigId(String sigId);

	/**
	 * Returns the number of csmap cache jsons where sigId = &#63;.
	 *
	 * @param sigId the sig ID
	 * @return the number of matching csmap cache jsons
	 */
	public int countBysigId(String sigId);

	/**
	 * Returns all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive);

	/**
	 * Returns a range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createPlace, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndIsActive_First(
			Date createPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndIsActive_First(
		Date createPlace, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndIsActive_Last(
			Date createPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndIsActive_Last(
		Date createPlace, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			String sigId, Date createPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where createPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 */
	public void removeByCreatedDateAndIsActive(
		Date createPlace, boolean isActive);

	/**
	 * Returns the number of csmap cache jsons where createPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public int countByCreatedDateAndIsActive(
		Date createPlace, boolean isActive);

	/**
	 * Returns all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive);

	/**
	 * Returns a range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive, int start,
			int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createPlace, Date modifiedPlace, boolean isActive, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndModifiedDateAndIsActive_First(
			Date createPlace, Date modifiedPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndModifiedDateAndIsActive_First(
		Date createPlace, Date modifiedPlace, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createPlace, Date modifiedPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndModifiedDateAndIsActive_Last(
		Date createPlace, Date modifiedPlace, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[]
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				String sigId, Date createPlace, Date modifiedPlace,
				boolean isActive,
				com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
					orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	public void removeByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive);

	/**
	 * Returns the number of csmap cache jsons where createPlace &lt; &#63; and modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param createPlace the create place
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public int countByCreatedDateAndModifiedDateAndIsActive(
		Date createPlace, Date modifiedPlace, boolean isActive);

	/**
	 * Returns all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive);

	/**
	 * Returns a range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByModifiedDateAndIsActive_First(
			Date modifiedPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedPlace, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedPlace, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param sigId the primary key of the current csmap cache json
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			String sigId, Date modifiedPlace, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 */
	public void removeByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive);

	/**
	 * Returns the number of csmap cache jsons where modifiedPlace &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedPlace the modified place
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public int countByModifiedDateAndIsActive(
		Date modifiedPlace, boolean isActive);

	/**
	 * Caches the csmap cache json in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJson the csmap cache json
	 */
	public void cacheResult(CsmapCacheJson csmapCacheJson);

	/**
	 * Caches the csmap cache jsons in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJsons the csmap cache jsons
	 */
	public void cacheResult(java.util.List<CsmapCacheJson> csmapCacheJsons);

	/**
	 * Creates a new csmap cache json with the primary key. Does not add the csmap cache json to the database.
	 *
	 * @param sigId the primary key for the new csmap cache json
	 * @return the new csmap cache json
	 */
	public CsmapCacheJson create(String sigId);

	/**
	 * Removes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson remove(String sigId)
		throws NoSuchCsmapCacheJsonException;

	public CsmapCacheJson updateImpl(CsmapCacheJson csmapCacheJson);

	/**
	 * Returns the csmap cache json with the primary key or throws a <code>NoSuchCsmapCacheJsonException</code> if it could not be found.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson findByPrimaryKey(String sigId)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the csmap cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json, or <code>null</code> if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson fetchByPrimaryKey(String sigId);

	/**
	 * Returns all the csmap cache jsons.
	 *
	 * @return the csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll();

	/**
	 * Returns a range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the csmap cache jsons from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of csmap cache jsons.
	 *
	 * @return the number of csmap cache jsons
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}