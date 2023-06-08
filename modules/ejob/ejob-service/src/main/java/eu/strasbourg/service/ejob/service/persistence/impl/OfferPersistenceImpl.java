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

package eu.strasbourg.service.ejob.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.ejob.exception.NoSuchOfferException;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.model.impl.OfferImpl;
import eu.strasbourg.service.ejob.model.impl.OfferModelImpl;
import eu.strasbourg.service.ejob.service.persistence.OfferPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the offer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OfferPersistenceImpl
	extends BasePersistenceImpl<Offer> implements OfferPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>OfferUtil</code> to access the offer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		OfferImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the offers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching offers
	 */
	@Override
	public List<Offer> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	@Override
	public List<Offer> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Offer> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Offer> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Offer> list = null;

		if (useFinderCache) {
			list = (List<Offer>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Offer offer : list) {
					if (!uuid.equals(offer.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_OFFER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OfferModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<Offer>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByUuid_First(
			String uuid, OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByUuid_First(uuid, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the first offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByUuid_First(
		String uuid, OrderByComparator<Offer> orderByComparator) {

		List<Offer> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByUuid_Last(
			String uuid, OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByUuid_Last(uuid, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the last offer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByUuid_Last(
		String uuid, OrderByComparator<Offer> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Offer> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offers before and after the current offer in the ordered set where uuid = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer[] findByUuid_PrevAndNext(
			long offerId, String uuid,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		uuid = Objects.toString(uuid, "");

		Offer offer = findByPrimaryKey(offerId);

		Session session = null;

		try {
			session = openSession();

			Offer[] array = new OfferImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, offer, uuid, orderByComparator, true);

			array[1] = offer;

			array[2] = getByUuid_PrevAndNext(
				session, offer, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Offer getByUuid_PrevAndNext(
		Session session, Offer offer, String uuid,
		OrderByComparator<Offer> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_OFFER_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(OfferModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(offer)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Offer> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Offer offer :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(offer);
		}
	}

	/**
	 * Returns the number of offers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching offers
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "offer.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(offer.uuid IS NULL OR offer.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the offer where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchOfferException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByUUID_G(String uuid, long groupId)
		throws NoSuchOfferException {

		Offer offer = fetchByUUID_G(uuid, groupId);

		if (offer == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchOfferException(sb.toString());
		}

		return offer;
	}

	/**
	 * Returns the offer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the offer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof Offer) {
			Offer offer = (Offer)result;

			if (!Objects.equals(uuid, offer.getUuid()) ||
				(groupId != offer.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_OFFER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<Offer> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					Offer offer = list.get(0);

					result = offer;

					cacheResult(offer);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByUUID_G, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Offer)result;
		}
	}

	/**
	 * Removes the offer where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the offer that was removed
	 */
	@Override
	public Offer removeByUUID_G(String uuid, long groupId)
		throws NoSuchOfferException {

		Offer offer = findByUUID_G(uuid, groupId);

		return remove(offer);
	}

	/**
	 * Returns the number of offers where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching offers
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_OFFER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"offer.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(offer.uuid IS NULL OR offer.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"offer.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching offers
	 */
	@Override
	public List<Offer> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	@Override
	public List<Offer> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Offer> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Offer> orderByComparator, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<Offer> list = null;

		if (useFinderCache) {
			list = (List<Offer>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Offer offer : list) {
					if (!uuid.equals(offer.getUuid()) ||
						(companyId != offer.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_OFFER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OfferModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<Offer>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the first offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Offer> orderByComparator) {

		List<Offer> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the last offer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Offer> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Offer> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public Offer[] findByUuid_C_PrevAndNext(
			long offerId, String uuid, long companyId,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		uuid = Objects.toString(uuid, "");

		Offer offer = findByPrimaryKey(offerId);

		Session session = null;

		try {
			session = openSession();

			Offer[] array = new OfferImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, offer, uuid, companyId, orderByComparator, true);

			array[1] = offer;

			array[2] = getByUuid_C_PrevAndNext(
				session, offer, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Offer getByUuid_C_PrevAndNext(
		Session session, Offer offer, String uuid, long companyId,
		OrderByComparator<Offer> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_OFFER_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(OfferModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(offer)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Offer> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Offer offer :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(offer);
		}
	}

	/**
	 * Returns the number of offers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching offers
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_OFFER_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"offer.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(offer.uuid IS NULL OR offer.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"offer.companyId = ?";

	private FinderPath _finderPathFetchByPublicationId;
	private FinderPath _finderPathCountByPublicationId;

	/**
	 * Returns the offer where publicationId = &#63; or throws a <code>NoSuchOfferException</code> if it could not be found.
	 *
	 * @param publicationId the publication ID
	 * @return the matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByPublicationId(String publicationId)
		throws NoSuchOfferException {

		Offer offer = fetchByPublicationId(publicationId);

		if (offer == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("publicationId=");
			sb.append(publicationId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchOfferException(sb.toString());
		}

		return offer;
	}

	/**
	 * Returns the offer where publicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publicationId the publication ID
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByPublicationId(String publicationId) {
		return fetchByPublicationId(publicationId, true);
	}

	/**
	 * Returns the offer where publicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publicationId the publication ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByPublicationId(
		String publicationId, boolean useFinderCache) {

		publicationId = Objects.toString(publicationId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {publicationId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByPublicationId, finderArgs, this);
		}

		if (result instanceof Offer) {
			Offer offer = (Offer)result;

			if (!Objects.equals(publicationId, offer.getPublicationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_OFFER_WHERE);

			boolean bindPublicationId = false;

			if (publicationId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLICATIONID_PUBLICATIONID_3);
			}
			else {
				bindPublicationId = true;

				sb.append(_FINDER_COLUMN_PUBLICATIONID_PUBLICATIONID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublicationId) {
					queryPos.add(publicationId);
				}

				List<Offer> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByPublicationId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {publicationId};
							}

							_log.warn(
								"OfferPersistenceImpl.fetchByPublicationId(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					Offer offer = list.get(0);

					result = offer;

					cacheResult(offer);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByPublicationId, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Offer)result;
		}
	}

	/**
	 * Removes the offer where publicationId = &#63; from the database.
	 *
	 * @param publicationId the publication ID
	 * @return the offer that was removed
	 */
	@Override
	public Offer removeByPublicationId(String publicationId)
		throws NoSuchOfferException {

		Offer offer = findByPublicationId(publicationId);

		return remove(offer);
	}

	/**
	 * Returns the number of offers where publicationId = &#63;.
	 *
	 * @param publicationId the publication ID
	 * @return the number of matching offers
	 */
	@Override
	public int countByPublicationId(String publicationId) {
		publicationId = Objects.toString(publicationId, "");

		FinderPath finderPath = _finderPathCountByPublicationId;

		Object[] finderArgs = new Object[] {publicationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFER_WHERE);

			boolean bindPublicationId = false;

			if (publicationId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLICATIONID_PUBLICATIONID_3);
			}
			else {
				bindPublicationId = true;

				sb.append(_FINDER_COLUMN_PUBLICATIONID_PUBLICATIONID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublicationId) {
					queryPos.add(publicationId);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PUBLICATIONID_PUBLICATIONID_2 =
		"offer.publicationId = ?";

	private static final String _FINDER_COLUMN_PUBLICATIONID_PUBLICATIONID_3 =
		"(offer.publicationId IS NULL OR offer.publicationId = '')";

	private FinderPath _finderPathWithPaginationFindByPublicationStartDate;
	private FinderPath _finderPathWithoutPaginationFindByPublicationStartDate;
	private FinderPath _finderPathCountByPublicationStartDate;

	/**
	 * Returns all the offers where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @return the matching offers
	 */
	@Override
	public List<Offer> findByPublicationStartDate(Date publicationStartDate) {
		return findByPublicationStartDate(
			publicationStartDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offers where publicationStartDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param publicationStartDate the publication start date
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	@Override
	public List<Offer> findByPublicationStartDate(
		Date publicationStartDate, int start, int end) {

		return findByPublicationStartDate(
			publicationStartDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offers where publicationStartDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param publicationStartDate the publication start date
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByPublicationStartDate(
		Date publicationStartDate, int start, int end,
		OrderByComparator<Offer> orderByComparator) {

		return findByPublicationStartDate(
			publicationStartDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offers where publicationStartDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param publicationStartDate the publication start date
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByPublicationStartDate(
		Date publicationStartDate, int start, int end,
		OrderByComparator<Offer> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByPublicationStartDate;
				finderArgs = new Object[] {_getTime(publicationStartDate)};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPublicationStartDate;
			finderArgs = new Object[] {
				_getTime(publicationStartDate), start, end, orderByComparator
			};
		}

		List<Offer> list = null;

		if (useFinderCache) {
			list = (List<Offer>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Offer offer : list) {
					if (!Objects.equals(
							publicationStartDate,
							offer.getPublicationStartDate())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_OFFER_WHERE);

			boolean bindPublicationStartDate = false;

			if (publicationStartDate == null) {
				sb.append(
					_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_1);
			}
			else {
				bindPublicationStartDate = true;

				sb.append(
					_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OfferModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublicationStartDate) {
					queryPos.add(new Timestamp(publicationStartDate.getTime()));
				}

				list = (List<Offer>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByPublicationStartDate_First(
			Date publicationStartDate,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByPublicationStartDate_First(
			publicationStartDate, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publicationStartDate=");
		sb.append(publicationStartDate);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the first offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByPublicationStartDate_First(
		Date publicationStartDate, OrderByComparator<Offer> orderByComparator) {

		List<Offer> list = findByPublicationStartDate(
			publicationStartDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByPublicationStartDate_Last(
			Date publicationStartDate,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByPublicationStartDate_Last(
			publicationStartDate, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publicationStartDate=");
		sb.append(publicationStartDate);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the last offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByPublicationStartDate_Last(
		Date publicationStartDate, OrderByComparator<Offer> orderByComparator) {

		int count = countByPublicationStartDate(publicationStartDate);

		if (count == 0) {
			return null;
		}

		List<Offer> list = findByPublicationStartDate(
			publicationStartDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offers before and after the current offer in the ordered set where publicationStartDate = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param publicationStartDate the publication start date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer[] findByPublicationStartDate_PrevAndNext(
			long offerId, Date publicationStartDate,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = findByPrimaryKey(offerId);

		Session session = null;

		try {
			session = openSession();

			Offer[] array = new OfferImpl[3];

			array[0] = getByPublicationStartDate_PrevAndNext(
				session, offer, publicationStartDate, orderByComparator, true);

			array[1] = offer;

			array[2] = getByPublicationStartDate_PrevAndNext(
				session, offer, publicationStartDate, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Offer getByPublicationStartDate_PrevAndNext(
		Session session, Offer offer, Date publicationStartDate,
		OrderByComparator<Offer> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_OFFER_WHERE);

		boolean bindPublicationStartDate = false;

		if (publicationStartDate == null) {
			sb.append(
				_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_1);
		}
		else {
			bindPublicationStartDate = true;

			sb.append(
				_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(OfferModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindPublicationStartDate) {
			queryPos.add(new Timestamp(publicationStartDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(offer)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Offer> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offers where publicationStartDate = &#63; from the database.
	 *
	 * @param publicationStartDate the publication start date
	 */
	@Override
	public void removeByPublicationStartDate(Date publicationStartDate) {
		for (Offer offer :
				findByPublicationStartDate(
					publicationStartDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(offer);
		}
	}

	/**
	 * Returns the number of offers where publicationStartDate = &#63;.
	 *
	 * @param publicationStartDate the publication start date
	 * @return the number of matching offers
	 */
	@Override
	public int countByPublicationStartDate(Date publicationStartDate) {
		FinderPath finderPath = _finderPathCountByPublicationStartDate;

		Object[] finderArgs = new Object[] {_getTime(publicationStartDate)};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFER_WHERE);

			boolean bindPublicationStartDate = false;

			if (publicationStartDate == null) {
				sb.append(
					_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_1);
			}
			else {
				bindPublicationStartDate = true;

				sb.append(
					_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublicationStartDate) {
					queryPos.add(new Timestamp(publicationStartDate.getTime()));
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_1 =
			"offer.publicationStartDate IS NULL";

	private static final String
		_FINDER_COLUMN_PUBLICATIONSTARTDATE_PUBLICATIONSTARTDATE_2 =
			"offer.publicationStartDate = ?";

	private FinderPath _finderPathWithPaginationFindByExport;
	private FinderPath _finderPathWithoutPaginationFindByExport;
	private FinderPath _finderPathCountByExport;

	/**
	 * Returns all the offers where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @return the matching offers
	 */
	@Override
	public List<Offer> findByExport(int isExported) {
		return findByExport(
			isExported, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offers where isExported = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param isExported the is exported
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	@Override
	public List<Offer> findByExport(int isExported, int start, int end) {
		return findByExport(isExported, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offers where isExported = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param isExported the is exported
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByExport(
		int isExported, int start, int end,
		OrderByComparator<Offer> orderByComparator) {

		return findByExport(isExported, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offers where isExported = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param isExported the is exported
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findByExport(
		int isExported, int start, int end,
		OrderByComparator<Offer> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByExport;
				finderArgs = new Object[] {isExported};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByExport;
			finderArgs = new Object[] {
				isExported, start, end, orderByComparator
			};
		}

		List<Offer> list = null;

		if (useFinderCache) {
			list = (List<Offer>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Offer offer : list) {
					if (isExported != offer.getIsExported()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_OFFER_WHERE);

			sb.append(_FINDER_COLUMN_EXPORT_ISEXPORTED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OfferModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isExported);

				list = (List<Offer>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByExport_First(
			int isExported, OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByExport_First(isExported, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isExported=");
		sb.append(isExported);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the first offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByExport_First(
		int isExported, OrderByComparator<Offer> orderByComparator) {

		List<Offer> list = findByExport(isExported, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findByExport_Last(
			int isExported, OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchByExport_Last(isExported, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("isExported=");
		sb.append(isExported);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the last offer in the ordered set where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchByExport_Last(
		int isExported, OrderByComparator<Offer> orderByComparator) {

		int count = countByExport(isExported);

		if (count == 0) {
			return null;
		}

		List<Offer> list = findByExport(
			isExported, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offers before and after the current offer in the ordered set where isExported = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param isExported the is exported
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer[] findByExport_PrevAndNext(
			long offerId, int isExported,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = findByPrimaryKey(offerId);

		Session session = null;

		try {
			session = openSession();

			Offer[] array = new OfferImpl[3];

			array[0] = getByExport_PrevAndNext(
				session, offer, isExported, orderByComparator, true);

			array[1] = offer;

			array[2] = getByExport_PrevAndNext(
				session, offer, isExported, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Offer getByExport_PrevAndNext(
		Session session, Offer offer, int isExported,
		OrderByComparator<Offer> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_OFFER_WHERE);

		sb.append(_FINDER_COLUMN_EXPORT_ISEXPORTED_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(OfferModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(isExported);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(offer)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Offer> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offers where isExported = &#63; from the database.
	 *
	 * @param isExported the is exported
	 */
	@Override
	public void removeByExport(int isExported) {
		for (Offer offer :
				findByExport(
					isExported, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(offer);
		}
	}

	/**
	 * Returns the number of offers where isExported = &#63;.
	 *
	 * @param isExported the is exported
	 * @return the number of matching offers
	 */
	@Override
	public int countByExport(int isExported) {
		FinderPath finderPath = _finderPathCountByExport;

		Object[] finderArgs = new Object[] {isExported};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFER_WHERE);

			sb.append(_FINDER_COLUMN_EXPORT_ISEXPORTED_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(isExported);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_EXPORT_ISEXPORTED_2 =
		"offer.isExported = ?";

	private FinderPath _finderPathWithPaginationFindBySent;
	private FinderPath _finderPathWithoutPaginationFindBySent;
	private FinderPath _finderPathCountBySent;

	/**
	 * Returns all the offers where emailPartnerSent = &#63;.
	 *
	 * @param emailPartnerSent the email partner sent
	 * @return the matching offers
	 */
	@Override
	public List<Offer> findBySent(int emailPartnerSent) {
		return findBySent(
			emailPartnerSent, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offers where emailPartnerSent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param emailPartnerSent the email partner sent
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of matching offers
	 */
	@Override
	public List<Offer> findBySent(int emailPartnerSent, int start, int end) {
		return findBySent(emailPartnerSent, start, end, null);
	}

	/**
	 * Returns an ordered range of all the offers where emailPartnerSent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param emailPartnerSent the email partner sent
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findBySent(
		int emailPartnerSent, int start, int end,
		OrderByComparator<Offer> orderByComparator) {

		return findBySent(
			emailPartnerSent, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offers where emailPartnerSent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param emailPartnerSent the email partner sent
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching offers
	 */
	@Override
	public List<Offer> findBySent(
		int emailPartnerSent, int start, int end,
		OrderByComparator<Offer> orderByComparator, boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindBySent;
				finderArgs = new Object[] {emailPartnerSent};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindBySent;
			finderArgs = new Object[] {
				emailPartnerSent, start, end, orderByComparator
			};
		}

		List<Offer> list = null;

		if (useFinderCache) {
			list = (List<Offer>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Offer offer : list) {
					if (emailPartnerSent != offer.getEmailPartnerSent()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_OFFER_WHERE);

			sb.append(_FINDER_COLUMN_SENT_EMAILPARTNERSENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(OfferModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(emailPartnerSent);

				list = (List<Offer>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first offer in the ordered set where emailPartnerSent = &#63;.
	 *
	 * @param emailPartnerSent the email partner sent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findBySent_First(
			int emailPartnerSent, OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchBySent_First(emailPartnerSent, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("emailPartnerSent=");
		sb.append(emailPartnerSent);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the first offer in the ordered set where emailPartnerSent = &#63;.
	 *
	 * @param emailPartnerSent the email partner sent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchBySent_First(
		int emailPartnerSent, OrderByComparator<Offer> orderByComparator) {

		List<Offer> list = findBySent(
			emailPartnerSent, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last offer in the ordered set where emailPartnerSent = &#63;.
	 *
	 * @param emailPartnerSent the email partner sent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer
	 * @throws NoSuchOfferException if a matching offer could not be found
	 */
	@Override
	public Offer findBySent_Last(
			int emailPartnerSent, OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = fetchBySent_Last(emailPartnerSent, orderByComparator);

		if (offer != null) {
			return offer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("emailPartnerSent=");
		sb.append(emailPartnerSent);

		sb.append("}");

		throw new NoSuchOfferException(sb.toString());
	}

	/**
	 * Returns the last offer in the ordered set where emailPartnerSent = &#63;.
	 *
	 * @param emailPartnerSent the email partner sent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching offer, or <code>null</code> if a matching offer could not be found
	 */
	@Override
	public Offer fetchBySent_Last(
		int emailPartnerSent, OrderByComparator<Offer> orderByComparator) {

		int count = countBySent(emailPartnerSent);

		if (count == 0) {
			return null;
		}

		List<Offer> list = findBySent(
			emailPartnerSent, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the offers before and after the current offer in the ordered set where emailPartnerSent = &#63;.
	 *
	 * @param offerId the primary key of the current offer
	 * @param emailPartnerSent the email partner sent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer[] findBySent_PrevAndNext(
			long offerId, int emailPartnerSent,
			OrderByComparator<Offer> orderByComparator)
		throws NoSuchOfferException {

		Offer offer = findByPrimaryKey(offerId);

		Session session = null;

		try {
			session = openSession();

			Offer[] array = new OfferImpl[3];

			array[0] = getBySent_PrevAndNext(
				session, offer, emailPartnerSent, orderByComparator, true);

			array[1] = offer;

			array[2] = getBySent_PrevAndNext(
				session, offer, emailPartnerSent, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected Offer getBySent_PrevAndNext(
		Session session, Offer offer, int emailPartnerSent,
		OrderByComparator<Offer> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_OFFER_WHERE);

		sb.append(_FINDER_COLUMN_SENT_EMAILPARTNERSENT_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(OfferModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(emailPartnerSent);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(offer)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Offer> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the offers where emailPartnerSent = &#63; from the database.
	 *
	 * @param emailPartnerSent the email partner sent
	 */
	@Override
	public void removeBySent(int emailPartnerSent) {
		for (Offer offer :
				findBySent(
					emailPartnerSent, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(offer);
		}
	}

	/**
	 * Returns the number of offers where emailPartnerSent = &#63;.
	 *
	 * @param emailPartnerSent the email partner sent
	 * @return the number of matching offers
	 */
	@Override
	public int countBySent(int emailPartnerSent) {
		FinderPath finderPath = _finderPathCountBySent;

		Object[] finderArgs = new Object[] {emailPartnerSent};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_OFFER_WHERE);

			sb.append(_FINDER_COLUMN_SENT_EMAILPARTNERSENT_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(emailPartnerSent);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SENT_EMAILPARTNERSENT_2 =
		"offer.emailPartnerSent = ?";

	public OfferPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		setModelClass(Offer.class);
	}

	/**
	 * Caches the offer in the entity cache if it is enabled.
	 *
	 * @param offer the offer
	 */
	@Override
	public void cacheResult(Offer offer) {
		entityCache.putResult(
			OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
			offer.getPrimaryKey(), offer);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {offer.getUuid(), offer.getGroupId()}, offer);

		finderCache.putResult(
			_finderPathFetchByPublicationId,
			new Object[] {offer.getPublicationId()}, offer);

		offer.resetOriginalValues();
	}

	/**
	 * Caches the offers in the entity cache if it is enabled.
	 *
	 * @param offers the offers
	 */
	@Override
	public void cacheResult(List<Offer> offers) {
		for (Offer offer : offers) {
			if (entityCache.getResult(
					OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
					offer.getPrimaryKey()) == null) {

				cacheResult(offer);
			}
			else {
				offer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all offers.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(OfferImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the offer.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Offer offer) {
		entityCache.removeResult(
			OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
			offer.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((OfferModelImpl)offer, true);
	}

	@Override
	public void clearCache(List<Offer> offers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Offer offer : offers) {
			entityCache.removeResult(
				OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
				offer.getPrimaryKey());

			clearUniqueFindersCache((OfferModelImpl)offer, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(OfferModelImpl offerModelImpl) {
		Object[] args = new Object[] {
			offerModelImpl.getUuid(), offerModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, offerModelImpl, false);

		args = new Object[] {offerModelImpl.getPublicationId()};

		finderCache.putResult(
			_finderPathCountByPublicationId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByPublicationId, args, offerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		OfferModelImpl offerModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				offerModelImpl.getUuid(), offerModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((offerModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				offerModelImpl.getOriginalUuid(),
				offerModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {offerModelImpl.getPublicationId()};

			finderCache.removeResult(_finderPathCountByPublicationId, args);
			finderCache.removeResult(_finderPathFetchByPublicationId, args);
		}

		if ((offerModelImpl.getColumnBitmask() &
			 _finderPathFetchByPublicationId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				offerModelImpl.getOriginalPublicationId()
			};

			finderCache.removeResult(_finderPathCountByPublicationId, args);
			finderCache.removeResult(_finderPathFetchByPublicationId, args);
		}
	}

	/**
	 * Creates a new offer with the primary key. Does not add the offer to the database.
	 *
	 * @param offerId the primary key for the new offer
	 * @return the new offer
	 */
	@Override
	public Offer create(long offerId) {
		Offer offer = new OfferImpl();

		offer.setNew(true);
		offer.setPrimaryKey(offerId);

		String uuid = PortalUUIDUtil.generate();

		offer.setUuid(uuid);

		offer.setCompanyId(CompanyThreadLocal.getCompanyId());

		return offer;
	}

	/**
	 * Removes the offer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer that was removed
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer remove(long offerId) throws NoSuchOfferException {
		return remove((Serializable)offerId);
	}

	/**
	 * Removes the offer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the offer
	 * @return the offer that was removed
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer remove(Serializable primaryKey) throws NoSuchOfferException {
		Session session = null;

		try {
			session = openSession();

			Offer offer = (Offer)session.get(OfferImpl.class, primaryKey);

			if (offer == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOfferException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(offer);
		}
		catch (NoSuchOfferException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Offer removeImpl(Offer offer) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(offer)) {
				offer = (Offer)session.get(
					OfferImpl.class, offer.getPrimaryKeyObj());
			}

			if (offer != null) {
				session.delete(offer);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (offer != null) {
			clearCache(offer);
		}

		return offer;
	}

	@Override
	public Offer updateImpl(Offer offer) {
		boolean isNew = offer.isNew();

		if (!(offer instanceof OfferModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(offer.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(offer);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in offer proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Offer implementation " +
					offer.getClass());
		}

		OfferModelImpl offerModelImpl = (OfferModelImpl)offer;

		if (Validator.isNull(offer.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			offer.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (offer.getCreateDate() == null)) {
			if (serviceContext == null) {
				offer.setCreateDate(now);
			}
			else {
				offer.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!offerModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				offer.setModifiedDate(now);
			}
			else {
				offer.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (offer.isNew()) {
				session.save(offer);

				offer.setNew(false);
			}
			else {
				offer = (Offer)session.merge(offer);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!OfferModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {offerModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				offerModelImpl.getUuid(), offerModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {offerModelImpl.getPublicationStartDate()};

			finderCache.removeResult(
				_finderPathCountByPublicationStartDate, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPublicationStartDate, args);

			args = new Object[] {offerModelImpl.getIsExported()};

			finderCache.removeResult(_finderPathCountByExport, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByExport, args);

			args = new Object[] {offerModelImpl.getEmailPartnerSent()};

			finderCache.removeResult(_finderPathCountBySent, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindBySent, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((offerModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {offerModelImpl.getOriginalUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {offerModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((offerModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					offerModelImpl.getOriginalUuid(),
					offerModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					offerModelImpl.getUuid(), offerModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((offerModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPublicationStartDate.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					offerModelImpl.getOriginalPublicationStartDate()
				};

				finderCache.removeResult(
					_finderPathCountByPublicationStartDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublicationStartDate,
					args);

				args = new Object[] {offerModelImpl.getPublicationStartDate()};

				finderCache.removeResult(
					_finderPathCountByPublicationStartDate, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublicationStartDate,
					args);
			}

			if ((offerModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByExport.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					offerModelImpl.getOriginalIsExported()
				};

				finderCache.removeResult(_finderPathCountByExport, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByExport, args);

				args = new Object[] {offerModelImpl.getIsExported()};

				finderCache.removeResult(_finderPathCountByExport, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByExport, args);
			}

			if ((offerModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindBySent.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					offerModelImpl.getOriginalEmailPartnerSent()
				};

				finderCache.removeResult(_finderPathCountBySent, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySent, args);

				args = new Object[] {offerModelImpl.getEmailPartnerSent()};

				finderCache.removeResult(_finderPathCountBySent, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindBySent, args);
			}
		}

		entityCache.putResult(
			OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
			offer.getPrimaryKey(), offer, false);

		clearUniqueFindersCache(offerModelImpl, false);
		cacheUniqueFindersCache(offerModelImpl);

		offer.resetOriginalValues();

		return offer;
	}

	/**
	 * Returns the offer with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offer
	 * @return the offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchOfferException {

		Offer offer = fetchByPrimaryKey(primaryKey);

		if (offer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchOfferException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return offer;
	}

	/**
	 * Returns the offer with the primary key or throws a <code>NoSuchOfferException</code> if it could not be found.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer
	 * @throws NoSuchOfferException if a offer with the primary key could not be found
	 */
	@Override
	public Offer findByPrimaryKey(long offerId) throws NoSuchOfferException {
		return findByPrimaryKey((Serializable)offerId);
	}

	/**
	 * Returns the offer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the offer
	 * @return the offer, or <code>null</code> if a offer with the primary key could not be found
	 */
	@Override
	public Offer fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Offer offer = (Offer)serializable;

		if (offer == null) {
			Session session = null;

			try {
				session = openSession();

				offer = (Offer)session.get(OfferImpl.class, primaryKey);

				if (offer != null) {
					cacheResult(offer);
				}
				else {
					entityCache.putResult(
						OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
						primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
					primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return offer;
	}

	/**
	 * Returns the offer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param offerId the primary key of the offer
	 * @return the offer, or <code>null</code> if a offer with the primary key could not be found
	 */
	@Override
	public Offer fetchByPrimaryKey(long offerId) {
		return fetchByPrimaryKey((Serializable)offerId);
	}

	@Override
	public Map<Serializable, Offer> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Offer> map = new HashMap<Serializable, Offer>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Offer offer = fetchByPrimaryKey(primaryKey);

			if (offer != null) {
				map.put(primaryKey, offer);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Offer)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_OFFER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			sb.append((long)primaryKey);

			sb.append(",");
		}

		sb.setIndex(sb.index() - 1);

		sb.append(")");

		String sql = sb.toString();

		Session session = null;

		try {
			session = openSession();

			Query query = session.createQuery(sql);

			for (Offer offer : (List<Offer>)query.list()) {
				map.put(offer.getPrimaryKeyObj(), offer);

				cacheResult(offer);

				uncachedPrimaryKeys.remove(offer.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					OfferModelImpl.ENTITY_CACHE_ENABLED, OfferImpl.class,
					primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the offers.
	 *
	 * @return the offers
	 */
	@Override
	public List<Offer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the offers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @return the range of offers
	 */
	@Override
	public List<Offer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the offers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of offers
	 */
	@Override
	public List<Offer> findAll(
		int start, int end, OrderByComparator<Offer> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the offers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfferModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of offers
	 * @param end the upper bound of the range of offers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of offers
	 */
	@Override
	public List<Offer> findAll(
		int start, int end, OrderByComparator<Offer> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Offer> list = null;

		if (useFinderCache) {
			list = (List<Offer>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_OFFER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_OFFER;

				sql = sql.concat(OfferModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Offer>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the offers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Offer offer : findAll()) {
			remove(offer);
		}
	}

	/**
	 * Returns the number of offers.
	 *
	 * @return the number of offers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_OFFER);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return OfferModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the offer persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			OfferModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			OfferModelImpl.UUID_COLUMN_BITMASK |
			OfferModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			OfferModelImpl.UUID_COLUMN_BITMASK |
			OfferModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByPublicationId = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPublicationId",
			new String[] {String.class.getName()},
			OfferModelImpl.PUBLICATIONID_COLUMN_BITMASK);

		_finderPathCountByPublicationId = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublicationId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByPublicationStartDate = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublicationStartDate",
			new String[] {
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPublicationStartDate = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPublicationStartDate", new String[] {Date.class.getName()},
			OfferModelImpl.PUBLICATIONSTARTDATE_COLUMN_BITMASK);

		_finderPathCountByPublicationStartDate = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPublicationStartDate", new String[] {Date.class.getName()});

		_finderPathWithPaginationFindByExport = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByExport",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByExport = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByExport",
			new String[] {Integer.class.getName()},
			OfferModelImpl.ISEXPORTED_COLUMN_BITMASK);

		_finderPathCountByExport = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByExport",
			new String[] {Integer.class.getName()});

		_finderPathWithPaginationFindBySent = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySent",
			new String[] {
				Integer.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindBySent = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, OfferImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySent",
			new String[] {Integer.class.getName()},
			OfferModelImpl.EMAILPARTNERSENT_COLUMN_BITMASK);

		_finderPathCountBySent = new FinderPath(
			OfferModelImpl.ENTITY_CACHE_ENABLED,
			OfferModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySent",
			new String[] {Integer.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(OfferImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_OFFER =
		"SELECT offer FROM Offer offer";

	private static final String _SQL_SELECT_OFFER_WHERE_PKS_IN =
		"SELECT offer FROM Offer offer WHERE offerId IN (";

	private static final String _SQL_SELECT_OFFER_WHERE =
		"SELECT offer FROM Offer offer WHERE ";

	private static final String _SQL_COUNT_OFFER =
		"SELECT COUNT(offer) FROM Offer offer";

	private static final String _SQL_COUNT_OFFER_WHERE =
		"SELECT COUNT(offer) FROM Offer offer WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "offer.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Offer exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Offer exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		OfferPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}