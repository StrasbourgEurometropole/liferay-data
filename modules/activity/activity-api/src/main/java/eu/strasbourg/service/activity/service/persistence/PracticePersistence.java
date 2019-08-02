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

package eu.strasbourg.service.activity.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.activity.exception.NoSuchPracticeException;
import eu.strasbourg.service.activity.model.Practice;

/**
 * The persistence interface for the practice service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.persistence.impl.PracticePersistenceImpl
 * @see PracticeUtil
 * @generated
 */
@ProviderType
public interface PracticePersistence extends BasePersistence<Practice> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PracticeUtil} to access the practice persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the practices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching practices
	*/
	public java.util.List<Practice> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the practices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public java.util.List<Practice> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the practices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns an ordered range of all the practices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the first practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the last practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the last practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the practices before and after the current practice in the ordered set where uuid = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public Practice[] findByUuid_PrevAndNext(long practiceId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Removes all the practices where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of practices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching practices
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the practice where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPracticeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPracticeException;

	/**
	* Returns the practice where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the practice where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the practice where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the practice that was removed
	*/
	public Practice removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPracticeException;

	/**
	* Returns the number of practices where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching practices
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the practices where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching practices
	*/
	public java.util.List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the practices where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public java.util.List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the practices where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns an ordered range of all the practices where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the first practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the last practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the last practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the practices before and after the current practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public Practice[] findByUuid_C_PrevAndNext(long practiceId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Removes all the practices where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of practices where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching practices
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the practices where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the matching practices
	*/
	public java.util.List<Practice> findByAssociation(long associationId);

	/**
	* Returns a range of all the practices where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public java.util.List<Practice> findByAssociation(long associationId,
		int start, int end);

	/**
	* Returns an ordered range of all the practices where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByAssociation(long associationId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns an ordered range of all the practices where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByAssociation(long associationId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByAssociation_First(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the first practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByAssociation_First(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the last practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByAssociation_Last(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the last practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByAssociation_Last(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the practices before and after the current practice in the ordered set where associationId = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public Practice[] findByAssociation_PrevAndNext(long practiceId,
		long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Removes all the practices where associationId = &#63; from the database.
	*
	* @param associationId the association ID
	*/
	public void removeByAssociation(long associationId);

	/**
	* Returns the number of practices where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the number of matching practices
	*/
	public int countByAssociation(long associationId);

	/**
	* Returns all the practices where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching practices
	*/
	public java.util.List<Practice> findByGroupId(long groupId);

	/**
	* Returns a range of all the practices where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public java.util.List<Practice> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the practices where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns an ordered range of all the practices where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public java.util.List<Practice> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the first practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the last practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public Practice findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Returns the last practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public Practice fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns the practices before and after the current practice in the ordered set where groupId = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public Practice[] findByGroupId_PrevAndNext(long practiceId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator)
		throws NoSuchPracticeException;

	/**
	* Removes all the practices where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of practices where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching practices
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the practice in the entity cache if it is enabled.
	*
	* @param practice the practice
	*/
	public void cacheResult(Practice practice);

	/**
	* Caches the practices in the entity cache if it is enabled.
	*
	* @param practices the practices
	*/
	public void cacheResult(java.util.List<Practice> practices);

	/**
	* Creates a new practice with the primary key. Does not add the practice to the database.
	*
	* @param practiceId the primary key for the new practice
	* @return the new practice
	*/
	public Practice create(long practiceId);

	/**
	* Removes the practice with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param practiceId the primary key of the practice
	* @return the practice that was removed
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public Practice remove(long practiceId) throws NoSuchPracticeException;

	public Practice updateImpl(Practice practice);

	/**
	* Returns the practice with the primary key or throws a {@link NoSuchPracticeException} if it could not be found.
	*
	* @param practiceId the primary key of the practice
	* @return the practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public Practice findByPrimaryKey(long practiceId)
		throws NoSuchPracticeException;

	/**
	* Returns the practice with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param practiceId the primary key of the practice
	* @return the practice, or <code>null</code> if a practice with the primary key could not be found
	*/
	public Practice fetchByPrimaryKey(long practiceId);

	@Override
	public java.util.Map<java.io.Serializable, Practice> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the practices.
	*
	* @return the practices
	*/
	public java.util.List<Practice> findAll();

	/**
	* Returns a range of all the practices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of practices
	*/
	public java.util.List<Practice> findAll(int start, int end);

	/**
	* Returns an ordered range of all the practices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of practices
	*/
	public java.util.List<Practice> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator);

	/**
	* Returns an ordered range of all the practices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of practices
	*/
	public java.util.List<Practice> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the practices from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of practices.
	*
	* @return the number of practices
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}