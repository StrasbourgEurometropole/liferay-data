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

package eu.strasbourg.service.ejob.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.ejob.exception.NoSuchOfferException;
import eu.strasbourg.service.ejob.model.Offer;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the offer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferUtil
 * @generated
 */
@ProviderType
public interface OfferPersistence extends BasePersistence<Offer> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferUtil} to access the offer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Offer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the offers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching offers
	 */
	public java.util.List<Offer> findByUuid(String uuid);

	/**
	 * Returns a range of all the offers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	public java.util.List<Offer> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the offers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the offers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the first offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the last offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the last offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the offers before and after the current offer in the ordered set where uuid = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	public Offer[] findByUuid_PrevAndNext(
			long offerId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Removes all the offers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of offers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching offers
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the offer where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchOfferException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByUUID_G(String uuid, long groupId)
		throws NoSuchOfferException;

	/**
	 * Returns the offer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the offer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the offer where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the offer that was removed
	 */
	public Offer removeByUUID_G(String uuid, long groupId)
		throws NoSuchOfferException;

	/**
	 * Returns the number of offers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching offers
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching offers
	 */
	public java.util.List<Offer> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	public java.util.List<Offer> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the first offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the last offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the last offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the offers before and after the current offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	public Offer[] findByUuid_C_PrevAndNext(
			long offerId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Removes all the offers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of offers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching offers
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the offer where publicationId = &#63; or throws a <code>NoSuchOfferException</code> if it could not be found.
	 *
	 * @param publicationId the publication ID
	 * @return the matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByPublicationId(String publicationId)
		throws NoSuchOfferException;

	/**
	 * Returns the offer where publicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publicationId the publication ID
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByPublicationId(String publicationId);

	/**
	 * Returns the offer where publicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publicationId the publication ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByPublicationId(
		String publicationId, boolean retrieveFromCache);

	/**
	 * Removes the offer where publicationId = &#63; from the database.
	 *
	 * @param publicationId the publication ID
	 * @return the offer that was removed
	 */
	public Offer removeByPublicationId(String publicationId)
		throws NoSuchOfferException;

	/**
	 * Returns the number of offers where publicationId = &#63;.
	 *
	 * @param publicationId the publication ID
	 * @return the number of matching offers
	 */
	public int countByPublicationId(String publicationId);

	/**
	 * Returns all the offers where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @return the matching offers
	 */
	public java.util.List<Offer> findByPublicationStartDate(
		Date publicationStartDate);

	/**
	 * Returns a range of all the offers where publicationStartDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationStartDate the publication start date
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	public java.util.List<Offer> findByPublicationStartDate(
		Date publicationStartDate, int start, int end);

	/**
	 * Returns an ordered range of all the offers where publicationStartDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationStartDate the publication start date
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByPublicationStartDate(
		Date publicationStartDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the offers where publicationStartDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publicationStartDate the publication start date
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByPublicationStartDate(
		Date publicationStartDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByPublicationStartDate_First(
			Date publicationStartDate,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the first offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByPublicationStartDate_First(
		Date publicationStartDate,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the last offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByPublicationStartDate_Last(
			Date publicationStartDate,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the last offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByPublicationStartDate_Last(
		Date publicationStartDate,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the offers before and after the current offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	public Offer[] findByPublicationStartDate_PrevAndNext(
			long offerId, Date publicationStartDate,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Removes all the offers where publicationStartDate = &#63; from the database.
	 *
	 * @param publicationStartDate the publication start date
	 */
	public void removeByPublicationStartDate(Date publicationStartDate);

	/**
	 * Returns the number of offers where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @return the number of matching offers
	 */
	public int countByPublicationStartDate(Date publicationStartDate);

	/**
	 * Returns all the offers where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @return the matching offers
	 */
	public java.util.List<Offer> findByExport(int isExported);

	/**
	 * Returns a range of all the offers where isExported = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isExported the is exported
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	public java.util.List<Offer> findByExport(
		int isExported, int start, int end);

	/**
	 * Returns an ordered range of all the offers where isExported = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isExported the is exported
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByExport(
		int isExported, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the offers where isExported = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param isExported the is exported
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching offers
	 */
	public java.util.List<Offer> findByExport(
		int isExported, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByExport_First(
			int isExported,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the first offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByExport_First(
		int isExported,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the last offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	public Offer findByExport_Last(
			int isExported,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Returns the last offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	public Offer fetchByExport_Last(
		int isExported,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns the offers before and after the current offer in the ordered set where isExported = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	public Offer[] findByExport_PrevAndNext(
			long offerId, int isExported,
			com.liferay.portal.kernel.util.OrderByComparator<Offer>
				orderByComparator)
		throws NoSuchOfferException;

	/**
	 * Removes all the offers where isExported = &#63; from the database.
	 *
	 * @param isExported the is exported
	 */
	public void removeByExport(int isExported);

	/**
	 * Returns the number of offers where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @return the number of matching offers
	 */
	public int countByExport(int isExported);

	/**
	 * Caches the offer in the entity cache if it is enabled.
	 *
	 * @param offer the offer
	 */
	public void cacheResult(Offer offer);

	/**
	 * Caches the offers in the entity cache if it is enabled.
	 *
	 * @param offers the offers
	 */
	public void cacheResult(java.util.List<Offer> offers);

	/**
	 * Creates a new offer with the primary key. Does not add the offer to the database.
	 *
	 * @param offerId the primary key for the new offer
	 * @return the new offer
	 */
	public Offer create(long offerId);

	/**
	 * Removes the offer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer that was removed
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	public Offer remove(long offerId) throws NoSuchOfferException;

	public Offer updateImpl(Offer offer);

	/**
	 * Returns the offer with the primary key or throws a <code>NoSuchOfferException</code> if it could not be found.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	public Offer findByPrimaryKey(long offerId) throws NoSuchOfferException;

	/**
	 * Returns the offer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer, or <code>null</code> if a offer with the primary key could not be found
	 */
	public Offer fetchByPrimaryKey(long offerId);

	/**
	 * Returns all the offers.
	 *
	 * @return the offers
	 */
	public java.util.List<Offer> findAll();

	/**
	 * Returns a range of all the offers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of offers
	 */
	public java.util.List<Offer> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the offers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offers
	 */
	public java.util.List<Offer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator);

	/**
	 * Returns an ordered range of all the offers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of offers
	 */
	public java.util.List<Offer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Offer>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the offers from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of offers.
	 *
	 * @return the number of offers
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}