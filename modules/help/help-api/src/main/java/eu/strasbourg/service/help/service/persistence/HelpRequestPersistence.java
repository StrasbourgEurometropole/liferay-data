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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.help.exception.NoSuchHelpRequestException;
import eu.strasbourg.service.help.model.HelpRequest;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequestUtil
 * @generated
 */
@ProviderType
public interface HelpRequestPersistence extends BasePersistence<HelpRequest> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpRequestUtil} to access the help request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the help requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid(String uuid);

	/**
	 * Returns a range of all the help requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the first help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the last help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the last help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the help requests before and after the current help request in the ordered set where uuid = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public HelpRequest[] findByUuid_PrevAndNext(
			long helpRequestId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Removes all the help requests where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of help requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching help requests
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the help request where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchHelpRequestException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByUUID_G(String uuid, long groupId)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the help request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the help request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the help request where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the help request that was removed
	 */
	public HelpRequest removeByUUID_G(String uuid, long groupId)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the number of help requests where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching help requests
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the first help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the last help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the last help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the help requests before and after the current help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public HelpRequest[] findByUuid_C_PrevAndNext(
			long helpRequestId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Removes all the help requests where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching help requests
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the help requests where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching help requests
	 */
	public java.util.List<HelpRequest> findByPublikId(String publikId);

	/**
	 * Returns a range of all the help requests where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public java.util.List<HelpRequest> findByPublikId(
		String publikId, int start, int end);

	/**
	 * Returns an ordered range of all the help requests where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByPublikId(
		String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help requests where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByPublikId(
		String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByPublikId_First(
			String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the first help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByPublikId_First(
		String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the last help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByPublikId_Last(
			String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the last help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByPublikId_Last(
		String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the help requests before and after the current help request in the ordered set where publikId = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public HelpRequest[] findByPublikId_PrevAndNext(
			long helpRequestId, String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Removes all the help requests where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public void removeByPublikId(String publikId);

	/**
	 * Returns the number of help requests where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching help requests
	 */
	public int countByPublikId(String publikId);

	/**
	 * Returns all the help requests where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @return the matching help requests
	 */
	public java.util.List<HelpRequest> findByHelpProposalId(
		long helpProposalId);

	/**
	 * Returns a range of all the help requests where helpProposalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param helpProposalId the help proposal ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public java.util.List<HelpRequest> findByHelpProposalId(
		long helpProposalId, int start, int end);

	/**
	 * Returns an ordered range of all the help requests where helpProposalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param helpProposalId the help proposal ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByHelpProposalId(
		long helpProposalId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help requests where helpProposalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param helpProposalId the help proposal ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help requests
	 */
	public java.util.List<HelpRequest> findByHelpProposalId(
		long helpProposalId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByHelpProposalId_First(
			long helpProposalId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the first help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByHelpProposalId_First(
		long helpProposalId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the last help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public HelpRequest findByHelpProposalId_Last(
			long helpProposalId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the last help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public HelpRequest fetchByHelpProposalId_Last(
		long helpProposalId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns the help requests before and after the current help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public HelpRequest[] findByHelpProposalId_PrevAndNext(
			long helpRequestId, long helpProposalId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
				orderByComparator)
		throws NoSuchHelpRequestException;

	/**
	 * Removes all the help requests where helpProposalId = &#63; from the database.
	 *
	 * @param helpProposalId the help proposal ID
	 */
	public void removeByHelpProposalId(long helpProposalId);

	/**
	 * Returns the number of help requests where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @return the number of matching help requests
	 */
	public int countByHelpProposalId(long helpProposalId);

	/**
	 * Caches the help request in the entity cache if it is enabled.
	 *
	 * @param helpRequest the help request
	 */
	public void cacheResult(HelpRequest helpRequest);

	/**
	 * Caches the help requests in the entity cache if it is enabled.
	 *
	 * @param helpRequests the help requests
	 */
	public void cacheResult(java.util.List<HelpRequest> helpRequests);

	/**
	 * Creates a new help request with the primary key. Does not add the help request to the database.
	 *
	 * @param helpRequestId the primary key for the new help request
	 * @return the new help request
	 */
	public HelpRequest create(long helpRequestId);

	/**
	 * Removes the help request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request that was removed
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public HelpRequest remove(long helpRequestId)
		throws NoSuchHelpRequestException;

	public HelpRequest updateImpl(HelpRequest helpRequest);

	/**
	 * Returns the help request with the primary key or throws a <code>NoSuchHelpRequestException</code> if it could not be found.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public HelpRequest findByPrimaryKey(long helpRequestId)
		throws NoSuchHelpRequestException;

	/**
	 * Returns the help request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request, or <code>null</code> if a help request with the primary key could not be found
	 */
	public HelpRequest fetchByPrimaryKey(long helpRequestId);

	/**
	 * Returns all the help requests.
	 *
	 * @return the help requests
	 */
	public java.util.List<HelpRequest> findAll();

	/**
	 * Returns a range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of help requests
	 */
	public java.util.List<HelpRequest> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help requests
	 */
	public java.util.List<HelpRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help requests
	 */
	public java.util.List<HelpRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help requests from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help requests.
	 *
	 * @return the number of help requests
	 */
	public int countAll();

}