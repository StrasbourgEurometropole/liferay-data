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

import eu.strasbourg.service.formSendRecordField.exception.NoSuchFormSendRecordFieldException;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldImpl;
import eu.strasbourg.service.formSendRecordField.model.impl.FormSendRecordFieldModelImpl;
import eu.strasbourg.service.formSendRecordField.service.persistence.FormSendRecordFieldPersistence;

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
 * The persistence implementation for the form send record field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angélique Zunino
 * @see FormSendRecordFieldPersistence
 * @see eu.strasbourg.service.formSendRecordField.service.persistence.FormSendRecordFieldUtil
 * @generated
 */
@ProviderType
public class FormSendRecordFieldPersistenceImpl extends BasePersistenceImpl<FormSendRecordField>
	implements FormSendRecordFieldPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FormSendRecordFieldUtil} to access the form send record field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FormSendRecordFieldImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			FormSendRecordFieldModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the form send record fields where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching form send record fields
	 */
	@Override
	public List<FormSendRecordField> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<FormSendRecordField> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<FormSendRecordField> findByUuid(String uuid, int start,
		int end, OrderByComparator<FormSendRecordField> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<FormSendRecordField> findByUuid(String uuid, int start,
		int end, OrderByComparator<FormSendRecordField> orderByComparator,
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

		List<FormSendRecordField> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordField>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordField formSendRecordField : list) {
					if (!Objects.equals(uuid, formSendRecordField.getUuid())) {
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

			query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

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
				query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
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
					list = (List<FormSendRecordField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
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
	 * Returns the first form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByUuid_First(String uuid,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByUuid_First(uuid,
				orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the first form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByUuid_First(String uuid,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		List<FormSendRecordField> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByUuid_Last(String uuid,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByUuid_Last(uuid,
				orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByUuid_Last(String uuid,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordField> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where uuid = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField[] findByUuid_PrevAndNext(
		long formSendRecordFieldId, String uuid,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = findByPrimaryKey(formSendRecordFieldId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordField[] array = new FormSendRecordFieldImpl[3];

			array[0] = getByUuid_PrevAndNext(session, formSendRecordField,
					uuid, orderByComparator, true);

			array[1] = formSendRecordField;

			array[2] = getByUuid_PrevAndNext(session, formSendRecordField,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordField getByUuid_PrevAndNext(Session session,
		FormSendRecordField formSendRecordField, String uuid,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

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
			query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record fields where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (FormSendRecordField formSendRecordField : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordField);
		}
	}

	/**
	 * Returns the number of form send record fields where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching form send record fields
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "formSendRecordField.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "formSendRecordField.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(formSendRecordField.uuid IS NULL OR formSendRecordField.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			FormSendRecordFieldModelImpl.UUID_COLUMN_BITMASK |
			FormSendRecordFieldModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the form send record field where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFormSendRecordFieldException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByUUID_G(String uuid, long groupId)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByUUID_G(uuid, groupId);

		if (formSendRecordField == null) {
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

			throw new NoSuchFormSendRecordFieldException(msg.toString());
		}

		return formSendRecordField;
	}

	/**
	 * Returns the form send record field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the form send record field where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof FormSendRecordField) {
			FormSendRecordField formSendRecordField = (FormSendRecordField)result;

			if (!Objects.equals(uuid, formSendRecordField.getUuid()) ||
					(groupId != formSendRecordField.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

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

				List<FormSendRecordField> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					FormSendRecordField formSendRecordField = list.get(0);

					result = formSendRecordField;

					cacheResult(formSendRecordField);

					if ((formSendRecordField.getUuid() == null) ||
							!formSendRecordField.getUuid().equals(uuid) ||
							(formSendRecordField.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, formSendRecordField);
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
			return (FormSendRecordField)result;
		}
	}

	/**
	 * Removes the form send record field where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the form send record field that was removed
	 */
	@Override
	public FormSendRecordField removeByUUID_G(String uuid, long groupId)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = findByUUID_G(uuid, groupId);

		return remove(formSendRecordField);
	}

	/**
	 * Returns the number of form send record fields where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching form send record fields
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "formSendRecordField.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "formSendRecordField.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(formSendRecordField.uuid IS NULL OR formSendRecordField.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "formSendRecordField.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			FormSendRecordFieldModelImpl.UUID_COLUMN_BITMASK |
			FormSendRecordFieldModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the form send record fields where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching form send record fields
	 */
	@Override
	public List<FormSendRecordField> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<FormSendRecordField> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

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
	@Override
	public List<FormSendRecordField> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<FormSendRecordField> findByUuid_C(String uuid, long companyId,
		int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		List<FormSendRecordField> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordField>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordField formSendRecordField : list) {
					if (!Objects.equals(uuid, formSendRecordField.getUuid()) ||
							(companyId != formSendRecordField.getCompanyId())) {
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

			query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

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
				query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
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
					list = (List<FormSendRecordField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
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
	 * Returns the first form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the first form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		List<FormSendRecordField> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the last form send record field in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordField> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public FormSendRecordField[] findByUuid_C_PrevAndNext(
		long formSendRecordFieldId, String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = findByPrimaryKey(formSendRecordFieldId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordField[] array = new FormSendRecordFieldImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, formSendRecordField,
					uuid, companyId, orderByComparator, true);

			array[1] = formSendRecordField;

			array[2] = getByUuid_C_PrevAndNext(session, formSendRecordField,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordField getByUuid_C_PrevAndNext(Session session,
		FormSendRecordField formSendRecordField, String uuid, long companyId,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

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
			query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record fields where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (FormSendRecordField formSendRecordField : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordField);
		}
	}

	/**
	 * Returns the number of form send record fields where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching form send record fields
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELD_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "formSendRecordField.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "formSendRecordField.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(formSendRecordField.uuid IS NULL OR formSendRecordField.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "formSendRecordField.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			FormSendRecordFieldModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the form send record fields where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching form send record fields
	 */
	@Override
	public List<FormSendRecordField> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<FormSendRecordField> findByGroupId(long groupId, int start,
		int end) {
		return findByGroupId(groupId, start, end, null);
	}

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
	@Override
	public List<FormSendRecordField> findByGroupId(long groupId, int start,
		int end, OrderByComparator<FormSendRecordField> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<FormSendRecordField> findByGroupId(long groupId, int start,
		int end, OrderByComparator<FormSendRecordField> orderByComparator,
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

		List<FormSendRecordField> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordField>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordField formSendRecordField : list) {
					if ((groupId != formSendRecordField.getGroupId())) {
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

			query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
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
	 * Returns the first form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByGroupId_First(long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByGroupId_First(groupId,
				orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the first form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByGroupId_First(long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		List<FormSendRecordField> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByGroupId_Last(long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the last form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByGroupId_Last(long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordField> list = findByGroupId(groupId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where groupId = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField[] findByGroupId_PrevAndNext(
		long formSendRecordFieldId, long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = findByPrimaryKey(formSendRecordFieldId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordField[] array = new FormSendRecordFieldImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, formSendRecordField,
					groupId, orderByComparator, true);

			array[1] = formSendRecordField;

			array[2] = getByGroupId_PrevAndNext(session, formSendRecordField,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordField getByGroupId_PrevAndNext(Session session,
		FormSendRecordField formSendRecordField, long groupId,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

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
			query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record fields where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (FormSendRecordField formSendRecordField : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordField);
		}
	}

	/**
	 * Returns the number of form send record fields where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching form send record fields
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELD_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "formSendRecordField.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAssetEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAssetEntryId",
			new String[] { Long.class.getName(), Integer.class.getName() },
			FormSendRecordFieldModelImpl.ASSETENTRYID_COLUMN_BITMASK |
			FormSendRecordFieldModelImpl.STATUS_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ASSETENTRYID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAssetEntryId",
			new String[] { Long.class.getName(), Integer.class.getName() });

	/**
	 * Returns all the form send record fields where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @return the matching form send record fields
	 */
	@Override
	public List<FormSendRecordField> findByAssetEntryId(long assetEntryId,
		int status) {
		return findByAssetEntryId(assetEntryId, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<FormSendRecordField> findByAssetEntryId(long assetEntryId,
		int status, int start, int end) {
		return findByAssetEntryId(assetEntryId, status, start, end, null);
	}

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
	@Override
	public List<FormSendRecordField> findByAssetEntryId(long assetEntryId,
		int status, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		return findByAssetEntryId(assetEntryId, status, start, end,
			orderByComparator, true);
	}

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
	@Override
	public List<FormSendRecordField> findByAssetEntryId(long assetEntryId,
		int status, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID;
			finderArgs = new Object[] { assetEntryId, status };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ASSETENTRYID;
			finderArgs = new Object[] {
					assetEntryId, status,
					
					start, end, orderByComparator
				};
		}

		List<FormSendRecordField> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordField>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordField formSendRecordField : list) {
					if ((assetEntryId != formSendRecordField.getAssetEntryId()) ||
							(status != formSendRecordField.getStatus())) {
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

			query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

			query.append(_FINDER_COLUMN_ASSETENTRYID_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				qPos.add(status);

				if (!pagination) {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
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
	 * Returns the first form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByAssetEntryId_First(long assetEntryId,
		int status, OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByAssetEntryId_First(assetEntryId,
				status, orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the first form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByAssetEntryId_First(long assetEntryId,
		int status, OrderByComparator<FormSendRecordField> orderByComparator) {
		List<FormSendRecordField> list = findByAssetEntryId(assetEntryId,
				status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByAssetEntryId_Last(long assetEntryId,
		int status, OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByAssetEntryId_Last(assetEntryId,
				status, orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("assetEntryId=");
		msg.append(assetEntryId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the last form send record field in the ordered set where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByAssetEntryId_Last(long assetEntryId,
		int status, OrderByComparator<FormSendRecordField> orderByComparator) {
		int count = countByAssetEntryId(assetEntryId, status);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordField> list = findByAssetEntryId(assetEntryId,
				status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public FormSendRecordField[] findByAssetEntryId_PrevAndNext(
		long formSendRecordFieldId, long assetEntryId, int status,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = findByPrimaryKey(formSendRecordFieldId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordField[] array = new FormSendRecordFieldImpl[3];

			array[0] = getByAssetEntryId_PrevAndNext(session,
					formSendRecordField, assetEntryId, status,
					orderByComparator, true);

			array[1] = formSendRecordField;

			array[2] = getByAssetEntryId_PrevAndNext(session,
					formSendRecordField, assetEntryId, status,
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

	protected FormSendRecordField getByAssetEntryId_PrevAndNext(
		Session session, FormSendRecordField formSendRecordField,
		long assetEntryId, int status,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

		query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

		query.append(_FINDER_COLUMN_ASSETENTRYID_STATUS_2);

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
			query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(assetEntryId);

		qPos.add(status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record fields where assetEntryId = &#63; and status = &#63; from the database.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 */
	@Override
	public void removeByAssetEntryId(long assetEntryId, int status) {
		for (FormSendRecordField formSendRecordField : findByAssetEntryId(
				assetEntryId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordField);
		}
	}

	/**
	 * Returns the number of form send record fields where assetEntryId = &#63; and status = &#63;.
	 *
	 * @param assetEntryId the asset entry ID
	 * @param status the status
	 * @return the number of matching form send record fields
	 */
	@Override
	public int countByAssetEntryId(long assetEntryId, int status) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ASSETENTRYID;

		Object[] finderArgs = new Object[] { assetEntryId, status };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELD_WHERE);

			query.append(_FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2);

			query.append(_FINDER_COLUMN_ASSETENTRYID_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(assetEntryId);

				qPos.add(status);

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

	private static final String _FINDER_COLUMN_ASSETENTRYID_ASSETENTRYID_2 = "formSendRecordField.assetEntryId = ? AND ";
	private static final String _FINDER_COLUMN_ASSETENTRYID_STATUS_2 = "formSendRecordField.status = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTID =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContentId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTID =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContentId",
			new String[] { Long.class.getName() },
			FormSendRecordFieldModelImpl.CONTENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContentId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the form send record fields where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @return the matching form send record fields
	 */
	@Override
	public List<FormSendRecordField> findByContentId(long contentId) {
		return findByContentId(contentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

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
	@Override
	public List<FormSendRecordField> findByContentId(long contentId, int start,
		int end) {
		return findByContentId(contentId, start, end, null);
	}

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
	@Override
	public List<FormSendRecordField> findByContentId(long contentId, int start,
		int end, OrderByComparator<FormSendRecordField> orderByComparator) {
		return findByContentId(contentId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<FormSendRecordField> findByContentId(long contentId, int start,
		int end, OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTID;
			finderArgs = new Object[] { contentId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTID;
			finderArgs = new Object[] { contentId, start, end, orderByComparator };
		}

		List<FormSendRecordField> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordField>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordField formSendRecordField : list) {
					if ((contentId != formSendRecordField.getContentId())) {
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

			query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

			query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

				if (!pagination) {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
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
	 * Returns the first form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByContentId_First(long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByContentId_First(contentId,
				orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentId=");
		msg.append(contentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the first form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByContentId_First(long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		List<FormSendRecordField> list = findByContentId(contentId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByContentId_Last(long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByContentId_Last(contentId,
				orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentId=");
		msg.append(contentId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByContentId_Last(long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		int count = countByContentId(contentId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordField> list = findByContentId(contentId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the form send record fields before and after the current form send record field in the ordered set where contentId = &#63;.
	 *
	 * @param formSendRecordFieldId the primary key of the current form send record field
	 * @param contentId the content ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField[] findByContentId_PrevAndNext(
		long formSendRecordFieldId, long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = findByPrimaryKey(formSendRecordFieldId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordField[] array = new FormSendRecordFieldImpl[3];

			array[0] = getByContentId_PrevAndNext(session, formSendRecordField,
					contentId, orderByComparator, true);

			array[1] = formSendRecordField;

			array[2] = getByContentId_PrevAndNext(session, formSendRecordField,
					contentId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FormSendRecordField getByContentId_PrevAndNext(Session session,
		FormSendRecordField formSendRecordField, long contentId,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

		query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

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
			query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contentId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record fields where contentId = &#63; from the database.
	 *
	 * @param contentId the content ID
	 */
	@Override
	public void removeByContentId(long contentId) {
		for (FormSendRecordField formSendRecordField : findByContentId(
				contentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(formSendRecordField);
		}
	}

	/**
	 * Returns the number of form send record fields where contentId = &#63;.
	 *
	 * @param contentId the content ID
	 * @return the number of matching form send record fields
	 */
	@Override
	public int countByContentId(long contentId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTID;

		Object[] finderArgs = new Object[] { contentId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELD_WHERE);

			query.append(_FINDER_COLUMN_CONTENTID_CONTENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

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

	private static final String _FINDER_COLUMN_CONTENTID_CONTENTID_2 = "formSendRecordField.contentId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTANDINSTANCEID =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByContentAndInstanceId",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTANDINSTANCEID =
		new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED,
			FormSendRecordFieldImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByContentAndInstanceId",
			new String[] { Long.class.getName(), String.class.getName() },
			FormSendRecordFieldModelImpl.CONTENTID_COLUMN_BITMASK |
			FormSendRecordFieldModelImpl.INSTANCEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CONTENTANDINSTANCEID = new FinderPath(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByContentAndInstanceId",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the form send record fields where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @return the matching form send record fields
	 */
	@Override
	public List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId) {
		return findByContentAndInstanceId(contentId, instanceId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId, int start, int end) {
		return findByContentAndInstanceId(contentId, instanceId, start, end,
			null);
	}

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
	@Override
	public List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		return findByContentAndInstanceId(contentId, instanceId, start, end,
			orderByComparator, true);
	}

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
	@Override
	public List<FormSendRecordField> findByContentAndInstanceId(
		long contentId, String instanceId, int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTANDINSTANCEID;
			finderArgs = new Object[] { contentId, instanceId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTENTANDINSTANCEID;
			finderArgs = new Object[] {
					contentId, instanceId,
					
					start, end, orderByComparator
				};
		}

		List<FormSendRecordField> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordField>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FormSendRecordField formSendRecordField : list) {
					if ((contentId != formSendRecordField.getContentId()) ||
							!Objects.equals(instanceId,
								formSendRecordField.getInstanceId())) {
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

			query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

			query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_CONTENTID_2);

			boolean bindInstanceId = false;

			if (instanceId == null) {
				query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_1);
			}
			else if (instanceId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_3);
			}
			else {
				bindInstanceId = true;

				query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

				if (bindInstanceId) {
					qPos.add(instanceId);
				}

				if (!pagination) {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
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
	 * Returns the first form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByContentAndInstanceId_First(
		long contentId, String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByContentAndInstanceId_First(contentId,
				instanceId, orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentId=");
		msg.append(contentId);

		msg.append(", instanceId=");
		msg.append(instanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the first form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByContentAndInstanceId_First(
		long contentId, String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		List<FormSendRecordField> list = findByContentAndInstanceId(contentId,
				instanceId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field
	 * @throws NoSuchFormSendRecordFieldException if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField findByContentAndInstanceId_Last(long contentId,
		String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByContentAndInstanceId_Last(contentId,
				instanceId, orderByComparator);

		if (formSendRecordField != null) {
			return formSendRecordField;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("contentId=");
		msg.append(contentId);

		msg.append(", instanceId=");
		msg.append(instanceId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFormSendRecordFieldException(msg.toString());
	}

	/**
	 * Returns the last form send record field in the ordered set where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching form send record field, or <code>null</code> if a matching form send record field could not be found
	 */
	@Override
	public FormSendRecordField fetchByContentAndInstanceId_Last(
		long contentId, String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		int count = countByContentAndInstanceId(contentId, instanceId);

		if (count == 0) {
			return null;
		}

		List<FormSendRecordField> list = findByContentAndInstanceId(contentId,
				instanceId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public FormSendRecordField[] findByContentAndInstanceId_PrevAndNext(
		long formSendRecordFieldId, long contentId, String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = findByPrimaryKey(formSendRecordFieldId);

		Session session = null;

		try {
			session = openSession();

			FormSendRecordField[] array = new FormSendRecordFieldImpl[3];

			array[0] = getByContentAndInstanceId_PrevAndNext(session,
					formSendRecordField, contentId, instanceId,
					orderByComparator, true);

			array[1] = formSendRecordField;

			array[2] = getByContentAndInstanceId_PrevAndNext(session,
					formSendRecordField, contentId, instanceId,
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

	protected FormSendRecordField getByContentAndInstanceId_PrevAndNext(
		Session session, FormSendRecordField formSendRecordField,
		long contentId, String instanceId,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE);

		query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_CONTENTID_2);

		boolean bindInstanceId = false;

		if (instanceId == null) {
			query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_1);
		}
		else if (instanceId.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_3);
		}
		else {
			bindInstanceId = true;

			query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_2);
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
			query.append(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(contentId);

		if (bindInstanceId) {
			qPos.add(instanceId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(formSendRecordField);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<FormSendRecordField> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the form send record fields where contentId = &#63; and instanceId = &#63; from the database.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 */
	@Override
	public void removeByContentAndInstanceId(long contentId, String instanceId) {
		for (FormSendRecordField formSendRecordField : findByContentAndInstanceId(
				contentId, instanceId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(formSendRecordField);
		}
	}

	/**
	 * Returns the number of form send record fields where contentId = &#63; and instanceId = &#63;.
	 *
	 * @param contentId the content ID
	 * @param instanceId the instance ID
	 * @return the number of matching form send record fields
	 */
	@Override
	public int countByContentAndInstanceId(long contentId, String instanceId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTENTANDINSTANCEID;

		Object[] finderArgs = new Object[] { contentId, instanceId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FORMSENDRECORDFIELD_WHERE);

			query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_CONTENTID_2);

			boolean bindInstanceId = false;

			if (instanceId == null) {
				query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_1);
			}
			else if (instanceId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_3);
			}
			else {
				bindInstanceId = true;

				query.append(_FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(contentId);

				if (bindInstanceId) {
					qPos.add(instanceId);
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

	private static final String _FINDER_COLUMN_CONTENTANDINSTANCEID_CONTENTID_2 = "formSendRecordField.contentId = ? AND ";
	private static final String _FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_1 =
		"formSendRecordField.instanceId IS NULL";
	private static final String _FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_2 =
		"formSendRecordField.instanceId = ?";
	private static final String _FINDER_COLUMN_CONTENTANDINSTANCEID_INSTANCEID_3 =
		"(formSendRecordField.instanceId IS NULL OR formSendRecordField.instanceId = '')";

	public FormSendRecordFieldPersistenceImpl() {
		setModelClass(FormSendRecordField.class);

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
	 * Caches the form send record field in the entity cache if it is enabled.
	 *
	 * @param formSendRecordField the form send record field
	 */
	@Override
	public void cacheResult(FormSendRecordField formSendRecordField) {
		entityCache.putResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldImpl.class, formSendRecordField.getPrimaryKey(),
			formSendRecordField);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				formSendRecordField.getUuid(), formSendRecordField.getGroupId()
			}, formSendRecordField);

		formSendRecordField.resetOriginalValues();
	}

	/**
	 * Caches the form send record fields in the entity cache if it is enabled.
	 *
	 * @param formSendRecordFields the form send record fields
	 */
	@Override
	public void cacheResult(List<FormSendRecordField> formSendRecordFields) {
		for (FormSendRecordField formSendRecordField : formSendRecordFields) {
			if (entityCache.getResult(
						FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
						FormSendRecordFieldImpl.class,
						formSendRecordField.getPrimaryKey()) == null) {
				cacheResult(formSendRecordField);
			}
			else {
				formSendRecordField.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all form send record fields.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FormSendRecordFieldImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the form send record field.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FormSendRecordField formSendRecordField) {
		entityCache.removeResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldImpl.class, formSendRecordField.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((FormSendRecordFieldModelImpl)formSendRecordField,
			true);
	}

	@Override
	public void clearCache(List<FormSendRecordField> formSendRecordFields) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FormSendRecordField formSendRecordField : formSendRecordFields) {
			entityCache.removeResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
				FormSendRecordFieldImpl.class,
				formSendRecordField.getPrimaryKey());

			clearUniqueFindersCache((FormSendRecordFieldModelImpl)formSendRecordField,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		FormSendRecordFieldModelImpl formSendRecordFieldModelImpl) {
		Object[] args = new Object[] {
				formSendRecordFieldModelImpl.getUuid(),
				formSendRecordFieldModelImpl.getGroupId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
			Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
			formSendRecordFieldModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		FormSendRecordFieldModelImpl formSendRecordFieldModelImpl,
		boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					formSendRecordFieldModelImpl.getUuid(),
					formSendRecordFieldModelImpl.getGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		if ((formSendRecordFieldModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					formSendRecordFieldModelImpl.getOriginalUuid(),
					formSendRecordFieldModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new form send record field with the primary key. Does not add the form send record field to the database.
	 *
	 * @param formSendRecordFieldId the primary key for the new form send record field
	 * @return the new form send record field
	 */
	@Override
	public FormSendRecordField create(long formSendRecordFieldId) {
		FormSendRecordField formSendRecordField = new FormSendRecordFieldImpl();

		formSendRecordField.setNew(true);
		formSendRecordField.setPrimaryKey(formSendRecordFieldId);

		String uuid = PortalUUIDUtil.generate();

		formSendRecordField.setUuid(uuid);

		formSendRecordField.setCompanyId(companyProvider.getCompanyId());

		return formSendRecordField;
	}

	/**
	 * Removes the form send record field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formSendRecordFieldId the primary key of the form send record field
	 * @return the form send record field that was removed
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField remove(long formSendRecordFieldId)
		throws NoSuchFormSendRecordFieldException {
		return remove((Serializable)formSendRecordFieldId);
	}

	/**
	 * Removes the form send record field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the form send record field
	 * @return the form send record field that was removed
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField remove(Serializable primaryKey)
		throws NoSuchFormSendRecordFieldException {
		Session session = null;

		try {
			session = openSession();

			FormSendRecordField formSendRecordField = (FormSendRecordField)session.get(FormSendRecordFieldImpl.class,
					primaryKey);

			if (formSendRecordField == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormSendRecordFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(formSendRecordField);
		}
		catch (NoSuchFormSendRecordFieldException nsee) {
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
	protected FormSendRecordField removeImpl(
		FormSendRecordField formSendRecordField) {
		formSendRecordField = toUnwrappedModel(formSendRecordField);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(formSendRecordField)) {
				formSendRecordField = (FormSendRecordField)session.get(FormSendRecordFieldImpl.class,
						formSendRecordField.getPrimaryKeyObj());
			}

			if (formSendRecordField != null) {
				session.delete(formSendRecordField);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (formSendRecordField != null) {
			clearCache(formSendRecordField);
		}

		return formSendRecordField;
	}

	@Override
	public FormSendRecordField updateImpl(
		FormSendRecordField formSendRecordField) {
		formSendRecordField = toUnwrappedModel(formSendRecordField);

		boolean isNew = formSendRecordField.isNew();

		FormSendRecordFieldModelImpl formSendRecordFieldModelImpl = (FormSendRecordFieldModelImpl)formSendRecordField;

		if (Validator.isNull(formSendRecordField.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			formSendRecordField.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (formSendRecordField.getCreateDate() == null)) {
			if (serviceContext == null) {
				formSendRecordField.setCreateDate(now);
			}
			else {
				formSendRecordField.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!formSendRecordFieldModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				formSendRecordField.setModifiedDate(now);
			}
			else {
				formSendRecordField.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (formSendRecordField.isNew()) {
				session.save(formSendRecordField);

				formSendRecordField.setNew(false);
			}
			else {
				formSendRecordField = (FormSendRecordField)session.merge(formSendRecordField);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!FormSendRecordFieldModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { formSendRecordFieldModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					formSendRecordFieldModelImpl.getUuid(),
					formSendRecordFieldModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			args = new Object[] { formSendRecordFieldModelImpl.getGroupId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
				args);

			args = new Object[] {
					formSendRecordFieldModelImpl.getAssetEntryId(),
					formSendRecordFieldModelImpl.getStatus()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
				args);

			args = new Object[] { formSendRecordFieldModelImpl.getContentId() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTID,
				args);

			args = new Object[] {
					formSendRecordFieldModelImpl.getContentId(),
					formSendRecordFieldModelImpl.getInstanceId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTANDINSTANCEID,
				args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTANDINSTANCEID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((formSendRecordFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { formSendRecordFieldModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((formSendRecordFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldModelImpl.getOriginalUuid(),
						formSendRecordFieldModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						formSendRecordFieldModelImpl.getUuid(),
						formSendRecordFieldModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((formSendRecordFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { formSendRecordFieldModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((formSendRecordFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldModelImpl.getOriginalAssetEntryId(),
						formSendRecordFieldModelImpl.getOriginalStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);

				args = new Object[] {
						formSendRecordFieldModelImpl.getAssetEntryId(),
						formSendRecordFieldModelImpl.getStatus()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_ASSETENTRYID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ASSETENTRYID,
					args);
			}

			if ((formSendRecordFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldModelImpl.getOriginalContentId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTID,
					args);

				args = new Object[] { formSendRecordFieldModelImpl.getContentId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTID,
					args);
			}

			if ((formSendRecordFieldModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTANDINSTANCEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						formSendRecordFieldModelImpl.getOriginalContentId(),
						formSendRecordFieldModelImpl.getOriginalInstanceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTANDINSTANCEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTANDINSTANCEID,
					args);

				args = new Object[] {
						formSendRecordFieldModelImpl.getContentId(),
						formSendRecordFieldModelImpl.getInstanceId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_CONTENTANDINSTANCEID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTENTANDINSTANCEID,
					args);
			}
		}

		entityCache.putResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
			FormSendRecordFieldImpl.class, formSendRecordField.getPrimaryKey(),
			formSendRecordField, false);

		clearUniqueFindersCache(formSendRecordFieldModelImpl, false);
		cacheUniqueFindersCache(formSendRecordFieldModelImpl);

		formSendRecordField.resetOriginalValues();

		return formSendRecordField;
	}

	protected FormSendRecordField toUnwrappedModel(
		FormSendRecordField formSendRecordField) {
		if (formSendRecordField instanceof FormSendRecordFieldImpl) {
			return formSendRecordField;
		}

		FormSendRecordFieldImpl formSendRecordFieldImpl = new FormSendRecordFieldImpl();

		formSendRecordFieldImpl.setNew(formSendRecordField.isNew());
		formSendRecordFieldImpl.setPrimaryKey(formSendRecordField.getPrimaryKey());

		formSendRecordFieldImpl.setUuid(formSendRecordField.getUuid());
		formSendRecordFieldImpl.setFormSendRecordFieldId(formSendRecordField.getFormSendRecordFieldId());
		formSendRecordFieldImpl.setGroupId(formSendRecordField.getGroupId());
		formSendRecordFieldImpl.setCompanyId(formSendRecordField.getCompanyId());
		formSendRecordFieldImpl.setUserId(formSendRecordField.getUserId());
		formSendRecordFieldImpl.setUserName(formSendRecordField.getUserName());
		formSendRecordFieldImpl.setCreateDate(formSendRecordField.getCreateDate());
		formSendRecordFieldImpl.setModifiedDate(formSendRecordField.getModifiedDate());
		formSendRecordFieldImpl.setStatus(formSendRecordField.getStatus());
		formSendRecordFieldImpl.setStatusByUserId(formSendRecordField.getStatusByUserId());
		formSendRecordFieldImpl.setStatusByUserName(formSendRecordField.getStatusByUserName());
		formSendRecordFieldImpl.setStatusDate(formSendRecordField.getStatusDate());
		formSendRecordFieldImpl.setResponse(formSendRecordField.getResponse());
		formSendRecordFieldImpl.setAssetEntryId(formSendRecordField.getAssetEntryId());
		formSendRecordFieldImpl.setContentId(formSendRecordField.getContentId());
		formSendRecordFieldImpl.setInstanceId(formSendRecordField.getInstanceId());
		formSendRecordFieldImpl.setResponseUserId(formSendRecordField.getResponseUserId());

		return formSendRecordFieldImpl;
	}

	/**
	 * Returns the form send record field with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the form send record field
	 * @return the form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormSendRecordFieldException {
		FormSendRecordField formSendRecordField = fetchByPrimaryKey(primaryKey);

		if (formSendRecordField == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormSendRecordFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return formSendRecordField;
	}

	/**
	 * Returns the form send record field with the primary key or throws a {@link NoSuchFormSendRecordFieldException} if it could not be found.
	 *
	 * @param formSendRecordFieldId the primary key of the form send record field
	 * @return the form send record field
	 * @throws NoSuchFormSendRecordFieldException if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField findByPrimaryKey(long formSendRecordFieldId)
		throws NoSuchFormSendRecordFieldException {
		return findByPrimaryKey((Serializable)formSendRecordFieldId);
	}

	/**
	 * Returns the form send record field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the form send record field
	 * @return the form send record field, or <code>null</code> if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
				FormSendRecordFieldImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FormSendRecordField formSendRecordField = (FormSendRecordField)serializable;

		if (formSendRecordField == null) {
			Session session = null;

			try {
				session = openSession();

				formSendRecordField = (FormSendRecordField)session.get(FormSendRecordFieldImpl.class,
						primaryKey);

				if (formSendRecordField != null) {
					cacheResult(formSendRecordField);
				}
				else {
					entityCache.putResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
						FormSendRecordFieldImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
					FormSendRecordFieldImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return formSendRecordField;
	}

	/**
	 * Returns the form send record field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formSendRecordFieldId the primary key of the form send record field
	 * @return the form send record field, or <code>null</code> if a form send record field with the primary key could not be found
	 */
	@Override
	public FormSendRecordField fetchByPrimaryKey(long formSendRecordFieldId) {
		return fetchByPrimaryKey((Serializable)formSendRecordFieldId);
	}

	@Override
	public Map<Serializable, FormSendRecordField> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FormSendRecordField> map = new HashMap<Serializable, FormSendRecordField>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FormSendRecordField formSendRecordField = fetchByPrimaryKey(primaryKey);

			if (formSendRecordField != null) {
				map.put(primaryKey, formSendRecordField);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
					FormSendRecordFieldImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FormSendRecordField)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FORMSENDRECORDFIELD_WHERE_PKS_IN);

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

			for (FormSendRecordField formSendRecordField : (List<FormSendRecordField>)q.list()) {
				map.put(formSendRecordField.getPrimaryKeyObj(),
					formSendRecordField);

				cacheResult(formSendRecordField);

				uncachedPrimaryKeys.remove(formSendRecordField.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FormSendRecordFieldModelImpl.ENTITY_CACHE_ENABLED,
					FormSendRecordFieldImpl.class, primaryKey, nullModel);
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
	 * Returns all the form send record fields.
	 *
	 * @return the form send record fields
	 */
	@Override
	public List<FormSendRecordField> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<FormSendRecordField> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<FormSendRecordField> findAll(int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<FormSendRecordField> findAll(int start, int end,
		OrderByComparator<FormSendRecordField> orderByComparator,
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

		List<FormSendRecordField> list = null;

		if (retrieveFromCache) {
			list = (List<FormSendRecordField>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FORMSENDRECORDFIELD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FORMSENDRECORDFIELD;

				if (pagination) {
					sql = sql.concat(FormSendRecordFieldModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FormSendRecordField>)QueryUtil.list(q,
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
	 * Removes all the form send record fields from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FormSendRecordField formSendRecordField : findAll()) {
			remove(formSendRecordField);
		}
	}

	/**
	 * Returns the number of form send record fields.
	 *
	 * @return the number of form send record fields
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FORMSENDRECORDFIELD);

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
		return FormSendRecordFieldModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the form send record field persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FormSendRecordFieldImpl.class.getName());
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
	private static final String _SQL_SELECT_FORMSENDRECORDFIELD = "SELECT formSendRecordField FROM FormSendRecordField formSendRecordField";
	private static final String _SQL_SELECT_FORMSENDRECORDFIELD_WHERE_PKS_IN = "SELECT formSendRecordField FROM FormSendRecordField formSendRecordField WHERE formSendRecordFieldId IN (";
	private static final String _SQL_SELECT_FORMSENDRECORDFIELD_WHERE = "SELECT formSendRecordField FROM FormSendRecordField formSendRecordField WHERE ";
	private static final String _SQL_COUNT_FORMSENDRECORDFIELD = "SELECT COUNT(formSendRecordField) FROM FormSendRecordField formSendRecordField";
	private static final String _SQL_COUNT_FORMSENDRECORDFIELD_WHERE = "SELECT COUNT(formSendRecordField) FROM FormSendRecordField formSendRecordField WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "formSendRecordField.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FormSendRecordField exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FormSendRecordField exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(FormSendRecordFieldPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}