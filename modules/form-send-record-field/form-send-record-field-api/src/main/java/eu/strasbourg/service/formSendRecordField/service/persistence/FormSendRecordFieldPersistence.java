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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.formSendRecordField.exception.NoSuchFormSendRecordFieldException;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;

/**
 * The persistence interface for the form send record field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angélique Zunino
 * @see eu.strasbourg.service.formSendRecordField.service.persistence.impl.FormSendRecordFieldPersistenceImpl
 * @see FormSendRecordFieldUtil
 * @generated
 */
@ProviderType
public interface FormSendRecordFieldPersistence extends BasePersistence<FormSendRecordField> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FormSendRecordFieldUtil} to access the form send record field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the form send record fields where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the form send record fields where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the form send record fields where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns an ordered range of all the form send record fields where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first form send record field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the first form send record field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the last form send record field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the last form send record field in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the form send record fields before and after the current form send record field in the ordered set where uuid = &#63;.
	*
	* @param formSendRecordFieldId the primary key of the current form send record field
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next form send record field
	* @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	*/
	public FormSendRecordField[] findByUuid_PrevAndNext(
		long formSendRecordFieldId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Removes all the form send record fields where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of form send record fields where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching form send record fields
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the form send record field where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFormSendRecordFieldException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the form send record field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the form send record field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the form send record field where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the form send record field that was removed
	*/
	public FormSendRecordField removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the number of form send record fields where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching form send record fields
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the form send record fields where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the form send record fields where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the form send record fields where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns an ordered range of all the form send record fields where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<FormSendRecordField> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the first form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the last form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the last form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

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
	public FormSendRecordField[] findByUuid_C_PrevAndNext(
		long formSendRecordFieldId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Removes all the form send record fields where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of form send record fields where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching form send record fields
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the form send record fields where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByGroupId(long groupId);

	/**
	* Returns a range of all the form send record fields where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the form send record fields where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns an ordered range of all the form send record fields where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first form send record field in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the first form send record field in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the last form send record field in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the last form send record field in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the form send record fields before and after the current form send record field in the ordered set where groupId = &#63;.
	*
	* @param formSendRecordFieldId the primary key of the current form send record field
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next form send record field
	* @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	*/
	public FormSendRecordField[] findByGroupId_PrevAndNext(
		long formSendRecordFieldId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Removes all the form send record fields where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of form send record fields where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching form send record fields
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the form send record fields where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @return the matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status);

	/**
	* Returns a range of all the form send record fields where assetEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status, int start, int end);

	/**
	* Returns an ordered range of all the form send record fields where assetEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns an ordered range of all the form send record fields where assetEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<FormSendRecordField> findByAssetEntryId(
		long assetEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByAssetEntryId_First(long assetEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the first form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByAssetEntryId_First(long assetEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the last form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByAssetEntryId_Last(long assetEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the last form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByAssetEntryId_Last(long assetEntryId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

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
	public FormSendRecordField[] findByAssetEntryId_PrevAndNext(
		long formSendRecordFieldId, long assetEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Removes all the form send record fields where assetEntryId = &#63; and status = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	*/
	public void removeByAssetEntryId(long assetEntryId, int status);

	/**
	* Returns the number of form send record fields where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @return the number of matching form send record fields
	*/
	public int countByAssetEntryId(long assetEntryId, int status);

	/**
	* Returns all the form send record fields where contentId = &#63;.
	*
	* @param contentId the content ID
	* @return the matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByContentId(long contentId);

	/**
	* Returns a range of all the form send record fields where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentId the content ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByContentId(long contentId,
		int start, int end);

	/**
	* Returns an ordered range of all the form send record fields where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentId the content ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByContentId(long contentId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns an ordered range of all the form send record fields where contentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentId the content ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByContentId(long contentId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first form send record field in the ordered set where contentId = &#63;.
	*
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByContentId_First(long contentId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the first form send record field in the ordered set where contentId = &#63;.
	*
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByContentId_First(long contentId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the last form send record field in the ordered set where contentId = &#63;.
	*
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByContentId_Last(long contentId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the last form send record field in the ordered set where contentId = &#63;.
	*
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByContentId_Last(long contentId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the form send record fields before and after the current form send record field in the ordered set where contentId = &#63;.
	*
	* @param formSendRecordFieldId the primary key of the current form send record field
	* @param contentId the content ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next form send record field
	* @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	*/
	public FormSendRecordField[] findByContentId_PrevAndNext(
		long formSendRecordFieldId, long contentId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Removes all the form send record fields where contentId = &#63; from the database.
	*
	* @param contentId the content ID
	*/
	public void removeByContentId(long contentId);

	/**
	* Returns the number of form send record fields where contentId = &#63;.
	*
	* @param contentId the content ID
	* @return the number of matching form send record fields
	*/
	public int countByContentId(long contentId);

	/**
	* Returns all the form send record fields where contentId = &#63; and instanceId = &#63;.
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @return the matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, java.lang.String instanceId);

	/**
	* Returns a range of all the form send record fields where contentId = &#63; and instanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, java.lang.String instanceId, int start, int end);

	/**
	* Returns an ordered range of all the form send record fields where contentId = &#63; and instanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching form send record fields
	*/
	public java.util.List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, java.lang.String instanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns an ordered range of all the form send record fields where contentId = &#63; and instanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public java.util.List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, java.lang.String instanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByContentAndInstanceId_First(
		long contentId, java.lang.String instanceId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the first form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByContentAndInstanceId_First(
		long contentId, java.lang.String instanceId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns the last form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field
	* @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	*/
	public FormSendRecordField findByContentAndInstanceId_Last(long contentId,
		java.lang.String instanceId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the last form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	*/
	public FormSendRecordField fetchByContentAndInstanceId_Last(
		long contentId, java.lang.String instanceId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

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
	public FormSendRecordField[] findByContentAndInstanceId_PrevAndNext(
		long formSendRecordFieldId, long contentId,
		java.lang.String instanceId,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Removes all the form send record fields where contentId = &#63; and instanceId = &#63; from the database.
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	*/
	public void removeByContentAndInstanceId(long contentId,
		java.lang.String instanceId);

	/**
	* Returns the number of form send record fields where contentId = &#63; and instanceId = &#63;.
	*
	* @param contentId the content ID
	* @param instanceId the instance ID
	* @return the number of matching form send record fields
	*/
	public int countByContentAndInstanceId(long contentId,
		java.lang.String instanceId);

	/**
	* Caches the form send record field in the entity cache if it is enabled.
	*
	* @param formSendRecordField the form send record field
	*/
	public void cacheResult(FormSendRecordField formSendRecordField);

	/**
	* Caches the form send record fields in the entity cache if it is enabled.
	*
	* @param formSendRecordFields the form send record fields
	*/
	public void cacheResult(
		java.util.List<FormSendRecordField> formSendRecordFields);

	/**
	* Creates a new form send record field with the primary key. Does not add the form send record field to the database.
	*
	* @param formSendRecordFieldId the primary key for the new form send record field
	* @return the new form send record field
	*/
	public FormSendRecordField create(long formSendRecordFieldId);

	/**
	* Removes the form send record field with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param formSendRecordFieldId the primary key of the form send record field
	* @return the form send record field that was removed
	* @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	*/
	public FormSendRecordField remove(long formSendRecordFieldId)
		throws NoSuchFormSendRecordFieldException;

	public FormSendRecordField updateImpl(
		FormSendRecordField formSendRecordField);

	/**
	* Returns the form send record field with the primary key or throws a {@link NoSuchFormSendRecordFieldException} if it could not be found.
	*
	* @param formSendRecordFieldId the primary key of the form send record field
	* @return the form send record field
	* @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	*/
	public FormSendRecordField findByPrimaryKey(long formSendRecordFieldId)
		throws NoSuchFormSendRecordFieldException;

	/**
	* Returns the form send record field with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param formSendRecordFieldId the primary key of the form send record field
	* @return the form send record field, or <code>null</code> if a form send record field with the primary key could not be found
	*/
	public FormSendRecordField fetchByPrimaryKey(long formSendRecordFieldId);

	@Override
	public java.util.Map<java.io.Serializable, FormSendRecordField> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the form send record fields.
	*
	* @return the form send record fields
	*/
	public java.util.List<FormSendRecordField> findAll();

	/**
	* Returns a range of all the form send record fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @return the range of form send record fields
	*/
	public java.util.List<FormSendRecordField> findAll(int start, int end);

	/**
	* Returns an ordered range of all the form send record fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of form send record fields
	*/
	public java.util.List<FormSendRecordField> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator);

	/**
	* Returns an ordered range of all the form send record fields.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of form send record fields
	* @param end the upper bound of the range of form send record fields (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of form send record fields
	*/
	public java.util.List<FormSendRecordField> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the form send record fields from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of form send record fields.
	*
	* @return the number of form send record fields
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}