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

import eu.strasbourg.service.help.exception.NoSuchHelpProposalException;
import eu.strasbourg.service.help.model.HelpProposal;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the help proposal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpProposalUtil
 * @generated
 */
@ProviderType
public interface HelpProposalPersistence extends BasePersistence<HelpProposal> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HelpProposalUtil} to access the help proposal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the help proposals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid(String uuid);

	/**
	 * Returns a range of all the help proposals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where uuid = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public HelpProposal[] findByUuid_PrevAndNext(
			long helpProposalId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Removes all the help proposals where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of help proposals where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching help proposals
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the help proposal where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchHelpProposalException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByUUID_G(String uuid, long groupId)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the help proposal where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the help proposal where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the help proposal where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the help proposal that was removed
	 */
	public HelpProposal removeByUUID_G(String uuid, long groupId)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the number of help proposals where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching help proposals
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the first help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the last help proposal in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

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
	public HelpProposal[] findByUuid_C_PrevAndNext(
			long helpProposalId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Removes all the help proposals where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of help proposals where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching help proposals
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the help proposals where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching help proposals
	 */
	public java.util.List<HelpProposal> findByGroupId(long groupId);

	/**
	 * Returns a range of all the help proposals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the help proposals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help proposals where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the first help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns the last help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the last help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where groupId = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public HelpProposal[] findByGroupId_PrevAndNext(
			long helpProposalId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Removes all the help proposals where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of help proposals where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching help proposals
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the help proposals where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching help proposals
	 */
	public java.util.List<HelpProposal> findBypublikId(String publikId);

	/**
	 * Returns a range of all the help proposals where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of matching help proposals
	 */
	public java.util.List<HelpProposal> findBypublikId(
		String publikId, int start, int end);

	/**
	 * Returns an ordered range of all the help proposals where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findBypublikId(
		String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help proposals where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching help proposals
	 */
	public java.util.List<HelpProposal> findBypublikId(
		String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findBypublikId_First(
			String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the first help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchBypublikId_First(
		String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns the last help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findBypublikId_Last(
			String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the last help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchBypublikId_Last(
		String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns the help proposals before and after the current help proposal in the ordered set where publikId = &#63;.
	 *
	 * @param helpProposalId the primary key of the current help proposal
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public HelpProposal[] findBypublikId_PrevAndNext(
			long helpProposalId, String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
				orderByComparator)
		throws NoSuchHelpProposalException;

	/**
	 * Removes all the help proposals where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public void removeBypublikId(String publikId);

	/**
	 * Returns the number of help proposals where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching help proposals
	 */
	public int countBypublikId(String publikId);

	/**
	 * Returns the help proposal where publikId = &#63; and helpProposalId = &#63; or throws a <code>NoSuchHelpProposalException</code> if it could not be found.
	 *
	 * @param publikId the publik ID
	 * @param helpProposalId the help proposal ID
	 * @return the matching help proposal
	 * @throws NoSuchHelpProposalException if a matching help proposal could not be found
	 */
	public HelpProposal findByPublikIdAndHelpProposalId(
			String publikId, long helpProposalId)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the help proposal where publikId = &#63; and helpProposalId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikId the publik ID
	 * @param helpProposalId the help proposal ID
	 * @return the matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByPublikIdAndHelpProposalId(
		String publikId, long helpProposalId);

	/**
	 * Returns the help proposal where publikId = &#63; and helpProposalId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikId the publik ID
	 * @param helpProposalId the help proposal ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	public HelpProposal fetchByPublikIdAndHelpProposalId(
		String publikId, long helpProposalId, boolean useFinderCache);

	/**
	 * Removes the help proposal where publikId = &#63; and helpProposalId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 * @param helpProposalId the help proposal ID
	 * @return the help proposal that was removed
	 */
	public HelpProposal removeByPublikIdAndHelpProposalId(
			String publikId, long helpProposalId)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the number of help proposals where publikId = &#63; and helpProposalId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param helpProposalId the help proposal ID
	 * @return the number of matching help proposals
	 */
	public int countByPublikIdAndHelpProposalId(
		String publikId, long helpProposalId);

	/**
	 * Caches the help proposal in the entity cache if it is enabled.
	 *
	 * @param helpProposal the help proposal
	 */
	public void cacheResult(HelpProposal helpProposal);

	/**
	 * Caches the help proposals in the entity cache if it is enabled.
	 *
	 * @param helpProposals the help proposals
	 */
	public void cacheResult(java.util.List<HelpProposal> helpProposals);

	/**
	 * Creates a new help proposal with the primary key. Does not add the help proposal to the database.
	 *
	 * @param helpProposalId the primary key for the new help proposal
	 * @return the new help proposal
	 */
	public HelpProposal create(long helpProposalId);

	/**
	 * Removes the help proposal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal that was removed
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public HelpProposal remove(long helpProposalId)
		throws NoSuchHelpProposalException;

	public HelpProposal updateImpl(HelpProposal helpProposal);

	/**
	 * Returns the help proposal with the primary key or throws a <code>NoSuchHelpProposalException</code> if it could not be found.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal
	 * @throws NoSuchHelpProposalException if a help proposal with the primary key could not be found
	 */
	public HelpProposal findByPrimaryKey(long helpProposalId)
		throws NoSuchHelpProposalException;

	/**
	 * Returns the help proposal with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal, or <code>null</code> if a help proposal with the primary key could not be found
	 */
	public HelpProposal fetchByPrimaryKey(long helpProposalId);

	/**
	 * Returns all the help proposals.
	 *
	 * @return the help proposals
	 */
	public java.util.List<HelpProposal> findAll();

	/**
	 * Returns a range of all the help proposals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of help proposals
	 */
	public java.util.List<HelpProposal> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the help proposals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help proposals
	 */
	public java.util.List<HelpProposal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator);

	/**
	 * Returns an ordered range of all the help proposals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HelpProposalModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of help proposals
	 */
	public java.util.List<HelpProposal> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HelpProposal>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the help proposals from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of help proposals.
	 *
	 * @return the number of help proposals
	 */
	public int countAll();

}