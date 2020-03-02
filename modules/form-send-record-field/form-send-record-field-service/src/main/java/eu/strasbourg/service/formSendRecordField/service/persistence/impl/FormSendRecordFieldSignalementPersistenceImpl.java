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

package eu.strasbourg.service.formSendRecordField.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.formSendRecordField.exception.NoSuchFormSendRecordFieldSignalementException;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementImpl;
import eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldSignalementModelImpl;
import eu.strasbourg.service.formSendRecordField.service.persistence.FormSendRecordFieldSignalementPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

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
 * The persistence implementation for the form send record field signalement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldSignalementPersistence
 * @see eu.strasbourg.service.formSendRecordField.service.persistence.FormSendRecordFieldSignalementUtil
 * @generated
 */
@ProviderType
public class FormSendRecordFieldSignalementPersistenceImpl
	extends BasePersistenceImpl<FormSendRecordFieldSignalement>
	implements FormSendRecordFieldSignalementPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FormSendRecordFieldSignalementUtil} to access the form send record field signalement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FormSendRecordFieldSignalementImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			FormSendRecordFieldSignalementModelImpl.UUID_COLUMN_BITMASK |
			FormSendRecordFieldSignalementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid", new String[] { String.class.getName() });

	/**
	 * Returns all the form send record field signalements where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form send record field signalements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid(String uuid,
		int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid(String uuid,
		int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<FormSendRecordFieldSignalement> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordFieldSignalement>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : list) {
					if (!Objects.equals(uuid,
								formSendRecordFieldSignalement.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByUuid_First(String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByUuid_First(uuid,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByUuid_First(String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		List<FormSendRecordFieldSignalement> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByUuid_Last(String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByUuid_Last(uuid,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByUuid_Last(String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordFieldSignalement> list = findByUuid(uuid, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where uuid = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement[] findByUuid_PrevAndNext(
		long signalementId, String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = findByPrimaryKey(signalementId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordFieldSignalement[] array = new FormSendRecordFieldSignalementImpl[3];

			array[0] = getByUuid_PrevAndNext(session,
					formSendRecordFieldSignalement, uuid, orderByComparator,
					true);

			array[1] = formSendRecordFieldSignalement;

			array[2] = getByUuid_PrevAndNext(session,
					formSendRecordFieldSignalement, uuid, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordFieldSignalement getByUuid_PrevAndNext(
		Session session,
		FormSendRecordFieldSignalement formSendRecordFieldSignalement,
		String uuid,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordFieldSignalement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordFieldSignalement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record field signalements where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : findByUuid(
				uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordFieldSignalement);
		}
	}

	/**
	 * Returns the number of form send record field signalements where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching form send record field signalements
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "formSendRecordFieldSignalement.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "formSendRecordFieldSignalement.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(formSendRecordFieldSignalement.uuid IS NULL OR formSendRecordFieldSignalement.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			FormSendRecordFieldSignalementModelImpl.UUID_COLUMN_BITMASK |
			FormSendRecordFieldSignalementModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the form send record field signalement where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFormSendRecordFieldSignalementException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByUUID_G(String uuid, long groupId)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByUUID_G(uuid,
				groupId);

		if (formSendRecordFieldSignalement == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
		}

		return formSendRecordFieldSignalement;
	}

	/**
	 * Returns the form send record field signalement where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByUUID_G(String uuid,
		long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the form send record field signalement where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByUUID_G(String uuid,
		long groupId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof FormSendRecordFieldSignalement) {
			FormSendRecordFieldSignalement formSendRecordFieldSignalement = (FormSendRecordFieldSignalement)result;

			if (!Objects.equals(uuid, formSendRecordFieldSignalement.getUuid()) ||
					(groupId != formSendRecordFieldSignalement.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<FormSendRecordFieldSignalement> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					FormSendRecordFieldSignalement formSendRecordFieldSignalement =
						list.get(0);

					result = formSendRecordFieldSignalement;

					cacheResult(formSendRecordFieldSignalement);

					if ((formSendRecordFieldSignalement.getUuid() == null) ||
							!formSendRecordFieldSignalement.getUuid()
															   .equals(uuid) ||
							(formSendRecordFieldSignalement.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, formSendRecordFieldSignalement);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (FormSendRecordFieldSignalement)result;
		}
	}

	/**
	 * Removes the form send record field signalement where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the form send record field signalement that was removed
	 */
	@Override
	public FormSendRecordFieldSignalement removeByUUID_G(String uuid,
		long groupId) throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = findByUUID_G(uuid,
				groupId);

		return remove(formSendRecordFieldSignalement);
	}

	/**
	 * Returns the number of form send record field signalements where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching form send record field signalements
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "formSendRecordFieldSignalement.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "formSendRecordFieldSignalement.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(formSendRecordFieldSignalement.uuid IS NULL OR formSendRecordFieldSignalement.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "formSendRecordFieldSignalement.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			FormSendRecordFieldSignalementModelImpl.UUID_COLUMN_BITMASK |
			FormSendRecordFieldSignalementModelImpl.COMPANYID_COLUMN_BITMASK |
			FormSendRecordFieldSignalementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid_C(String uuid,
		long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<FormSendRecordFieldSignalement> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordFieldSignalement>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : list) {
					if (!Objects.equals(uuid,
								formSendRecordFieldSignalement.getUuid()) ||
							(companyId != formSendRecordFieldSignalement.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		List<FormSendRecordFieldSignalement> list = findByUuid_C(uuid,
				companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordFieldSignalement> list = findByUuid_C(uuid,
				companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement[] findByUuid_C_PrevAndNext(
		long signalementId, String uuid, long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = findByPrimaryKey(signalementId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordFieldSignalement[] array = new FormSendRecordFieldSignalementImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session,
					formSendRecordFieldSignalement, uuid, companyId,
					orderByComparator, true);

			array[1] = formSendRecordFieldSignalement;

			array[2] = getByUuid_C_PrevAndNext(session,
					formSendRecordFieldSignalement, uuid, companyId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordFieldSignalement getByUuid_C_PrevAndNext(
		Session session,
		FormSendRecordFieldSignalement formSendRecordFieldSignalement,
		String uuid, long companyId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordFieldSignalement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordFieldSignalement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record field signalements where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : findByUuid_C(
				uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordFieldSignalement);
		}
	}

	/**
	 * Returns the number of form send record field signalements where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching form send record field signalements
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "formSendRecordFieldSignalement.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "formSendRecordFieldSignalement.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(formSendRecordFieldSignalement.uuid IS NULL OR formSendRecordFieldSignalement.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "formSendRecordFieldSignalement.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			FormSendRecordFieldSignalementModelImpl.GROUPID_COLUMN_BITMASK |
			FormSendRecordFieldSignalementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByGroupId", new String[] { Long.class.getName() });

	/**
	 * Returns all the form send record field signalements where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form send record field signalements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByGroupId(long groupId,
		int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<FormSendRecordFieldSignalement> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordFieldSignalement>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : list) {
					if ((groupId != formSendRecordFieldSignalement.getGroupId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByGroupId_First(long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByGroupId_First(groupId,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByGroupId_First(long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		List<FormSendRecordFieldSignalement> list = findByGroupId(groupId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByGroupId_Last(long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByGroupId_Last(long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordFieldSignalement> list = findByGroupId(groupId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where groupId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement[] findByGroupId_PrevAndNext(
		long signalementId, long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = findByPrimaryKey(signalementId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordFieldSignalement[] array = new FormSendRecordFieldSignalementImpl[3];

			array[0] = getByGroupId_PrevAndNext(session,
					formSendRecordFieldSignalement, groupId, orderByComparator,
					true);

			array[1] = formSendRecordFieldSignalement;

			array[2] = getByGroupId_PrevAndNext(session,
					formSendRecordFieldSignalement, groupId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordFieldSignalement getByGroupId_PrevAndNext(
		Session session,
		FormSendRecordFieldSignalement formSendRecordFieldSignalement,
		long groupId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordFieldSignalement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordFieldSignalement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record field signalements where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : findByGroupId(
				groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordFieldSignalement);
		}
	}

	/**
	 * Returns the number of form send record field signalements where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching form send record field signalements
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "formSendRecordFieldSignalement.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPublikId",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID =
		new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikId",
			new String[] { String.class.getName() },
			FormSendRecordFieldSignalementModelImpl.PUBLIKID_COLUMN_BITMASK |
			FormSendRecordFieldSignalementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PUBLIKID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPublikId", new String[] { String.class.getName() });

	/**
	 * Returns all the form send record field signalements where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByPublikId(String publikId) {
		return findByPublikId(publikId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the form send record field signalements where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByPublikId(
		String publikId, int start, int end) {
		return findByPublikId(publikId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		return findByPublikId(publikId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID;
			finderArgs = new Object[] { publikId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PUBLIKID;
			finderArgs = new Object[] { publikId, start, end, orderByComparator };
		}

		List<FormSendRecordFieldSignalement> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordFieldSignalement>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : list) {
					if (!Objects.equals(publikId,
								formSendRecordFieldSignalement.getPublikId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindPublikId = false;

			if (publikId == null) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
			}
			else if (publikId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikId) {
					qPos.add(publikId);
				}

				if (!pagination) {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByPublikId_First(
		String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByPublikId_First(publikId,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikId=");
		msg.append(publikId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByPublikId_First(
		String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		List<FormSendRecordFieldSignalement> list = findByPublikId(publikId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByPublikId_Last(String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByPublikId_Last(publikId,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("publikId=");
		msg.append(publikId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByPublikId_Last(
		String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		int count = countByPublikId(publikId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordFieldSignalement> list = findByPublikId(publikId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where publikId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement[] findByPublikId_PrevAndNext(
		long signalementId, String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = findByPrimaryKey(signalementId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordFieldSignalement[] array = new FormSendRecordFieldSignalementImpl[3];

			array[0] = getByPublikId_PrevAndNext(session,
					formSendRecordFieldSignalement, publikId,
					orderByComparator, true);

			array[1] = formSendRecordFieldSignalement;

			array[2] = getByPublikId_PrevAndNext(session,
					formSendRecordFieldSignalement, publikId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordFieldSignalement getByPublikId_PrevAndNext(
		Session session,
		FormSendRecordFieldSignalement formSendRecordFieldSignalement,
		String publikId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

		boolean bindPublikId = false;

		if (publikId == null) {
			query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
		}
		else if (publikId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
		}
		else {
			bindPublikId = true;

			query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPublikId) {
			qPos.add(publikId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordFieldSignalement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordFieldSignalement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record field signalements where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	@Override
	public void removeByPublikId(String publikId) {
		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : findByPublikId(
				publikId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordFieldSignalement);
		}
	}

	/**
	 * Returns the number of form send record field signalements where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching form send record field signalements
	 */
	@Override
	public int countByPublikId(String publikId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PUBLIKID;

		Object[] finderArgs = new Object[] { publikId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			boolean bindPublikId = false;

			if (publikId == null) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_1);
			}
			else if (publikId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_3);
			}
			else {
				bindPublikId = true;

				query.append(_FINDER_COLUMN_PUBLIKID_PUBLIKID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPublikId) {
					qPos.add(publikId);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_1 = "formSendRecordFieldSignalement.publikId IS NULL";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_2 = "formSendRecordFieldSignalement.publikId = ?";
	private static final String _FINDER_COLUMN_PUBLIKID_PUBLIKID_3 = "(formSendRecordFieldSignalement.publikId IS NULL OR formSendRecordFieldSignalement.publikId = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID =
		new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByFormSendRecordFieldId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID =
		new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFormSendRecordFieldId",
			new String[] { Long.class.getName() },
			FormSendRecordFieldSignalementModelImpl.FORMSENDRECORDFIELDID_COLUMN_BITMASK |
			FormSendRecordFieldSignalementModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FORMSENDRECORDFIELDID = new FinderPath(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFormSendRecordFieldId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @return the matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByFormSendRecordFieldId(
		long formSendRecordFieldId) {
		return findByFormSendRecordFieldId(formSendRecordFieldId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByFormSendRecordFieldId(
		long formSendRecordFieldId, int start, int end) {
		return findByFormSendRecordFieldId(formSendRecordFieldId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByFormSendRecordFieldId(
		long formSendRecordFieldId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		return findByFormSendRecordFieldId(formSendRecordFieldId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findByFormSendRecordFieldId(
		long formSendRecordFieldId, int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID;
			finderArgs = new Object[] { formSendRecordFieldId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID;
			finderArgs = new Object[] {
					formSendRecordFieldId,
					
					start, end, orderByComparator
				};
		}

		List<FormSendRecordFieldSignalement> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordFieldSignalement>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : list) {
					if ((formSendRecordFieldId != formSendRecordFieldSignalement.getFormSendRecordFieldId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			query.append(_FINDER_COLUMN_FORMSENDRECORDFIELDID_FORMSENDRECORDFIELDID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formSendRecordFieldId);

				if (!pagination) {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByFormSendRecordFieldId_First(
		long formSendRecordFieldId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByFormSendRecordFieldId_First(formSendRecordFieldId,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formSendRecordFieldId=");
		msg.append(formSendRecordFieldId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the first form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByFormSendRecordFieldId_First(
		long formSendRecordFieldId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		List<FormSendRecordFieldSignalement> list = findByFormSendRecordFieldId(formSendRecordFieldId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByFormSendRecordFieldId_Last(
		long formSendRecordFieldId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByFormSendRecordFieldId_Last(formSendRecordFieldId,
				orderByComparator);

		if (formSendRecordFieldSignalement != null) {
			return formSendRecordFieldSignalement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("formSendRecordFieldId=");
		msg.append(formSendRecordFieldId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldSignalementException(msg.toString());
	}

	/**
	 * Returns the last form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field signalement, or <code>null</code> if a matching form send record field signalement could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByFormSendRecordFieldId_Last(
		long formSendRecordFieldId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		int count = countByFormSendRecordFieldId(formSendRecordFieldId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordFieldSignalement> list = findByFormSendRecordFieldId(formSendRecordFieldId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record field signalements before and after the current form send record field signalement in the ordered set where formSendRecordFieldId = &#63;.
	 *
	 * @param signalementId the primary key of the current form send record field signalement
	 * @param formSendRecordFieldId the form send record field ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement[] findByFormSendRecordFieldId_PrevAndNext(
		long signalementId, long formSendRecordFieldId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = findByPrimaryKey(signalementId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordFieldSignalement[] array = new FormSendRecordFieldSignalementImpl[3];

			array[0] = getByFormSendRecordFieldId_PrevAndNext(session,
					formSendRecordFieldSignalement, formSendRecordFieldId,
					orderByComparator, true);

			array[1] = formSendRecordFieldSignalement;

			array[2] = getByFormSendRecordFieldId_PrevAndNext(session,
					formSendRecordFieldSignalement, formSendRecordFieldId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordFieldSignalement getByFormSendRecordFieldId_PrevAndNext(
		Session session,
		FormSendRecordFieldSignalement formSendRecordFieldSignalement,
		long formSendRecordFieldId,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

		query.append(_FINDER_COLUMN_FORMSENDRECORDFIELDID_FORMSENDRECORDFIELDID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(formSendRecordFieldId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordFieldSignalement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordFieldSignalement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record field signalements where formSendRecordFieldId = &#63; from the database.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 */
	@Override
	public void removeByFormSendRecordFieldId(long formSendRecordFieldId) {
		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : findByFormSendRecordFieldId(
				formSendRecordFieldId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(formSendRecordFieldSignalement);
		}
	}

	/**
	 * Returns the number of form send record field signalements where formSendRecordFieldId = &#63;.
	 *
	 * @param formSendRecordFieldId the form send record field ID
	 * @return the number of matching form send record field signalements
	 */
	@Override
	public int countByFormSendRecordFieldId(long formSendRecordFieldId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FORMSENDRECORDFIELDID;

		Object[] finderArgs = new Object[] { formSendRecordFieldId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE);

			query.append(_FINDER_COLUMN_FORMSENDRECORDFIELDID_FORMSENDRECORDFIELDID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formSendRecordFieldId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FORMSENDRECORDFIELDID_FORMSENDRECORDFIELDID_2 =
		"formSendRecordFieldSignalement.formSendRecordFieldId = ?";

	public FormSendRecordFieldSignalementPersistenceImpl() {
		setModelClass(FormSendRecordFieldSignalement.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the form send record field signalement in the entity cache if it is enabled.
	 *
	 * @param formSendRecordFieldSignalement the form send record field signalement
	 */
	@Override
	public void cacheResult(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		entityCache.putResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			formSendRecordFieldSignalement.getPrimaryKey(),
			formSendRecordFieldSignalement);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				formSendRecordFieldSignalement.getUuid(),
				formSendRecordFieldSignalement.getGroupId()
			}, formSendRecordFieldSignalement);

		formSendRecordFieldSignalement.resetOriginalValues();
	}

	/**
	 * Caches the form send record field signalements in the entity cache if it is enabled.
	 *
	 * @param formSendRecordFieldSignalements the form send record field signalements
	 */
	@Override
	public void cacheResult(
		List<FormSendRecordFieldSignalement> formSendRecordFieldSignalements) {
		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : formSendRecordFieldSignalements) {
			if (entityCache.getResult(
						FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
						FormSendRecordFieldSignalementImpl.class,
						formSendRecordFieldSignalement.getPrimaryKey()) == null) {
				cacheResult(formSendRecordFieldSignalement);
			}
			else {
				formSendRecordFieldSignalement.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all form send record field signalements.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormSendRecordFieldSignalementImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the form send record field signalement.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		entityCache.removeResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			formSendRecordFieldSignalement.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((FormSendRecordFieldSignalementModelImpl)formSendRecordFieldSignalement,
			true);
	}

	@Override
	public void clearCache(
		List<FormSendRecordFieldSignalement> formSendRecordFieldSignalements) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : formSendRecordFieldSignalements) {
			entityCache.removeResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
				FormSendRecordFieldSignalementImpl.class,
				formSendRecordFieldSignalement.getPrimaryKey());

			clearUniqueFindersCache((FormSendRecordFieldSignalementModelImpl)formSendRecordFieldSignalement,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		FormSendRecordFieldSignalementModelImpl formSendRecordFieldSignalementModelImpl) {
		Object[] args = new Object[] {
				formSendRecordFieldSignalementModelImpl.getUuid(),
				formSendRecordFieldSignalementModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			formSendRecordFieldSignalementModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		FormSendRecordFieldSignalementModelImpl formSendRecordFieldSignalementModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					formSendRecordFieldSignalementModelImpl.getUuid(),
					formSendRecordFieldSignalementModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((formSendRecordFieldSignalementModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					formSendRecordFieldSignalementModelImpl.getOriginalUuid(),
					formSendRecordFieldSignalementModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new form send record field signalement with the primary key. Does not add the form send record field signalement to the database.
	 *
	 * @param signalementId the primary key for the new form send record field signalement
	 * @return the new form send record field signalement
	 */
	@Override
	public FormSendRecordFieldSignalement create(long signalementId) {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = new FormSendRecordFieldSignalementImpl();

		formSendRecordFieldSignalement.setNew(true);
		formSendRecordFieldSignalement.setPrimaryKey(signalementId);

		String uuid = PortalUUIDUtil.generate();

		formSendRecordFieldSignalement.setUuid(uuid);

		formSendRecordFieldSignalement.setCompanyId(companyProvider.getCompanyId());

		return formSendRecordFieldSignalement;
	}

	/**
	 * Removes the form send record field signalement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement that was removed
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement remove(long signalementId)
		throws NoSuchFormSendRecordFieldSignalementException {
		return remove((Serializable)signalementId);
	}

	/**
	 * Removes the form send record field signalement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the form send record field signalement
	 * @return the form send record field signalement that was removed
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement remove(Serializable primaryKey)
		throws NoSuchFormSendRecordFieldSignalementException {
		Session session = null;

		try {
			session = openSession();

			FormSendRecordFieldSignalement formSendRecordFieldSignalement = (FormSendRecordFieldSignalement)session.get(FormSendRecordFieldSignalementImpl.class,
					primaryKey);

			if (formSendRecordFieldSignalement == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormSendRecordFieldSignalementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(formSendRecordFieldSignalement);
		}
		catch (NoSuchFormSendRecordFieldSignalementException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected FormSendRecordFieldSignalement removeImpl(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		formSendRecordFieldSignalement = toUnwrappedModel(formSendRecordFieldSignalement);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(formSendRecordFieldSignalement)) {
				formSendRecordFieldSignalement = (FormSendRecordFieldSignalement)session.get(FormSendRecordFieldSignalementImpl.class,
						formSendRecordFieldSignalement.getPrimaryKeyObj());
			}

			if (formSendRecordFieldSignalement != null) {
				session.delete(formSendRecordFieldSignalement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (formSendRecordFieldSignalement != null) {
			clearCache(formSendRecordFieldSignalement);
		}

		return formSendRecordFieldSignalement;
	}

	@Override
	public FormSendRecordFieldSignalement updateImpl(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		formSendRecordFieldSignalement = toUnwrappedModel(formSendRecordFieldSignalement);

		boolean isNew = formSendRecordFieldSignalement.isNew();

		FormSendRecordFieldSignalementModelImpl formSendRecordFieldSignalementModelImpl =
			(FormSendRecordFieldSignalementModelImpl)formSendRecordFieldSignalement;

		if (Validator.isNull(formSendRecordFieldSignalement.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			formSendRecordFieldSignalement.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (formSendRecordFieldSignalement.getCreateDate() == null)) {
			if (serviceContext == null) {
				formSendRecordFieldSignalement.setCreateDate(now);
			}
			else {
				formSendRecordFieldSignalement.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!formSendRecordFieldSignalementModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				formSendRecordFieldSignalement.setModifiedDate(now);
			}
			else {
				formSendRecordFieldSignalement.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (formSendRecordFieldSignalement.isNew()) {
				session.save(formSendRecordFieldSignalement);

				formSendRecordFieldSignalement.setNew(false);
			}
			else {
				formSendRecordFieldSignalement = (FormSendRecordFieldSignalement)session.merge(formSendRecordFieldSignalement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!FormSendRecordFieldSignalementModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] {
					formSendRecordFieldSignalementModelImpl.getUuid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					formSendRecordFieldSignalementModelImpl.getUuid(),
					formSendRecordFieldSignalementModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] {
					formSendRecordFieldSignalementModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					formSendRecordFieldSignalementModelImpl.getPublikId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
				args);

			args = new Object[] {
					formSendRecordFieldSignalementModelImpl.getFormSendRecordFieldId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMSENDRECORDFIELDID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((formSendRecordFieldSignalementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((formSendRecordFieldSignalementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getOriginalUuid(),
						formSendRecordFieldSignalementModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getUuid(),
						formSendRecordFieldSignalementModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((formSendRecordFieldSignalementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((formSendRecordFieldSignalementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getOriginalPublikId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
					args);

				args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getPublikId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PUBLIKID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PUBLIKID,
					args);
			}

			if ((formSendRecordFieldSignalementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getOriginalFormSendRecordFieldId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMSENDRECORDFIELDID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID,
					args);

				args = new Object[] {
						formSendRecordFieldSignalementModelImpl.getFormSendRecordFieldId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_FORMSENDRECORDFIELDID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FORMSENDRECORDFIELDID,
					args);
			}
		}

		entityCache.putResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldSignalementImpl.class,
			formSendRecordFieldSignalement.getPrimaryKey(),
			formSendRecordFieldSignalement, false);

		clearUniqueFindersCache(formSendRecordFieldSignalementModelImpl, false);
		cacheUniqueFindersCache(formSendRecordFieldSignalementModelImpl);

		formSendRecordFieldSignalement.resetOriginalValues();

		return formSendRecordFieldSignalement;
	}

	protected FormSendRecordFieldSignalement toUnwrappedModel(
		FormSendRecordFieldSignalement formSendRecordFieldSignalement) {
		if (formSendRecordFieldSignalement instanceof FormSendRecordFieldSignalementImpl) {
			return formSendRecordFieldSignalement;
		}

		FormSendRecordFieldSignalementImpl formSendRecordFieldSignalementImpl = new FormSendRecordFieldSignalementImpl();

		formSendRecordFieldSignalementImpl.setNew(formSendRecordFieldSignalement.isNew());
		formSendRecordFieldSignalementImpl.setPrimaryKey(formSendRecordFieldSignalement.getPrimaryKey());

		formSendRecordFieldSignalementImpl.setUuid(formSendRecordFieldSignalement.getUuid());
		formSendRecordFieldSignalementImpl.setSignalementId(formSendRecordFieldSignalement.getSignalementId());
		formSendRecordFieldSignalementImpl.setGroupId(formSendRecordFieldSignalement.getGroupId());
		formSendRecordFieldSignalementImpl.setCompanyId(formSendRecordFieldSignalement.getCompanyId());
		formSendRecordFieldSignalementImpl.setUserId(formSendRecordFieldSignalement.getUserId());
		formSendRecordFieldSignalementImpl.setUserName(formSendRecordFieldSignalement.getUserName());
		formSendRecordFieldSignalementImpl.setCreateDate(formSendRecordFieldSignalement.getCreateDate());
		formSendRecordFieldSignalementImpl.setModifiedDate(formSendRecordFieldSignalement.getModifiedDate());
		formSendRecordFieldSignalementImpl.setStatus(formSendRecordFieldSignalement.getStatus());
		formSendRecordFieldSignalementImpl.setStatusByUserId(formSendRecordFieldSignalement.getStatusByUserId());
		formSendRecordFieldSignalementImpl.setStatusByUserName(formSendRecordFieldSignalement.getStatusByUserName());
		formSendRecordFieldSignalementImpl.setStatusDate(formSendRecordFieldSignalement.getStatusDate());
		formSendRecordFieldSignalementImpl.setFormSendRecordFieldId(formSendRecordFieldSignalement.getFormSendRecordFieldId());
		formSendRecordFieldSignalementImpl.setPublikId(formSendRecordFieldSignalement.getPublikId());

		return formSendRecordFieldSignalementImpl;
	}

	/**
	 * Returns the form send record field signalement with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the form send record field signalement
	 * @return the form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchFormSendRecordFieldSignalementException {
		FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByPrimaryKey(primaryKey);

		if (formSendRecordFieldSignalement == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormSendRecordFieldSignalementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return formSendRecordFieldSignalement;
	}

	/**
	 * Returns the form send record field signalement with the primary key or throws a {@link NoSuchFormSendRecordFieldSignalementException} if it could not be found.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement
	 * @throws NoSuchFormSendRecordFieldSignalementException if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement findByPrimaryKey(long signalementId)
		throws NoSuchFormSendRecordFieldSignalementException {
		return findByPrimaryKey((Serializable)signalementId);
	}

	/**
	 * Returns the form send record field signalement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the form send record field signalement
	 * @return the form send record field signalement, or <code>null</code> if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
				FormSendRecordFieldSignalementImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FormSendRecordFieldSignalement formSendRecordFieldSignalement = (FormSendRecordFieldSignalement)serializable;

		if (formSendRecordFieldSignalement == null) {
			Session session = null;

			try {
				session = openSession();

				formSendRecordFieldSignalement = (FormSendRecordFieldSignalement)session.get(FormSendRecordFieldSignalementImpl.class,
						primaryKey);

				if (formSendRecordFieldSignalement != null) {
					cacheResult(formSendRecordFieldSignalement);
				}
				else {
					entityCache.putResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
						FormSendRecordFieldSignalementImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
					FormSendRecordFieldSignalementImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return formSendRecordFieldSignalement;
	}

	/**
	 * Returns the form send record field signalement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param signalementId the primary key of the form send record field signalement
	 * @return the form send record field signalement, or <code>null</code> if a form send record field signalement with the primary key could not be found
	 */
	@Override
	public FormSendRecordFieldSignalement fetchByPrimaryKey(long signalementId) {
		return fetchByPrimaryKey((Serializable)signalementId);
	}

	@Override
	public Map<Serializable, FormSendRecordFieldSignalement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FormSendRecordFieldSignalement> map = new HashMap<Serializable, FormSendRecordFieldSignalement>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FormSendRecordFieldSignalement formSendRecordFieldSignalement = fetchByPrimaryKey(primaryKey);

			if (formSendRecordFieldSignalement != null) {
				map.put(primaryKey, formSendRecordFieldSignalement);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
					FormSendRecordFieldSignalementImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(FormSendRecordFieldSignalement)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : (List<FormSendRecordFieldSignalement>)q.list()) {
				map.put(formSendRecordFieldSignalement.getPrimaryKeyObj(),
					formSendRecordFieldSignalement);

				cacheResult(formSendRecordFieldSignalement);

				uncachedPrimaryKeys.remove(formSendRecordFieldSignalement.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FormSendRecordFieldSignalementModelImpl.ENTITY_CACHE_ENABLED,
					FormSendRecordFieldSignalementImpl.class, primaryKey,
					nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the form send record field signalements.
	 *
	 * @return the form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the form send record field signalements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @return the range of form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findAll(int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the form send record field signalements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormSendRecordFieldSignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of form send record field signalements
	 * @param end the upper bound of the range of form send record field signalements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of form send record field signalements
	 */
	@Override
	public List<FormSendRecordFieldSignalement> findAll(int start, int end,
		OrderByComparator<FormSendRecordFieldSignalement> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<FormSendRecordFieldSignalement> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordFieldSignalement>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT;

				if (pagination) {
					sql = sql.concat(FormSendRecordFieldSignalementModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordFieldSignalement>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the form send record field signalements from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : findAll()) {
			remove(formSendRecordFieldSignalement);
		}
	}

	/**
	 * Returns the number of form send record field signalements.
	 *
	 * @return the number of form send record field signalements
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
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
		return FormSendRecordFieldSignalementModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the form send record field signalement persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FormSendRecordFieldSignalementImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT = "SELECT formSendRecordFieldSignalement FROM FormSendRecordFieldSignalement formSendRecordFieldSignalement";
	private static final String _SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE_PKS_IN =
		"SELECT formSendRecordFieldSignalement FROM FormSendRecordFieldSignalement formSendRecordFieldSignalement WHERE signalementId IN (";
	private static final String _SQL_SELECT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE =
		"SELECT formSendRecordFieldSignalement FROM FormSendRecordFieldSignalement formSendRecordFieldSignalement WHERE ";
	private static final String _SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT = "SELECT COUNT(formSendRecordFieldSignalement) FROM FormSendRecordFieldSignalement formSendRecordFieldSignalement";
	private static final String _SQL_COUNT_FORMSENDRECORDFIELDSIGNALEMENT_WHERE = "SELECT COUNT(formSendRecordFieldSignalement) FROM FormSendRecordFieldSignalement formSendRecordFieldSignalement WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "formSendRecordFieldSignalement.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FormSendRecordFieldSignalement exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FormSendRecordFieldSignalement exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FormSendRecordFieldSignalementPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}