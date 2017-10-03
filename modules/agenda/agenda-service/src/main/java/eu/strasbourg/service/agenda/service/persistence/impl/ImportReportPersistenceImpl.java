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

package eu.strasbourg.service.agenda.service.persistence.impl;

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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.exception.NoSuchImportReportException;
import eu.strasbourg.service.agenda.model.ImportReport;
import eu.strasbourg.service.agenda.model.impl.ImportReportImpl;
import eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl;
import eu.strasbourg.service.agenda.service.persistence.ImportReportPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the import report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReportPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.ImportReportUtil
 * @generated
 */
@ProviderType
public class ImportReportPersistenceImpl extends BasePersistenceImpl<ImportReport>
	implements ImportReportPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImportReportUtil} to access the import report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImportReportImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportModelImpl.FINDER_CACHE_ENABLED, ImportReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportModelImpl.FINDER_CACHE_ENABLED, ImportReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportModelImpl.FINDER_CACHE_ENABLED, ImportReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportModelImpl.FINDER_CACHE_ENABLED, ImportReportImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			ImportReportModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the import reports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching import reports
	 */
	@Override
	public List<ImportReport> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the import reports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @return the range of matching import reports
	 */
	@Override
	public List<ImportReport> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the import reports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching import reports
	 */
	@Override
	public List<ImportReport> findByUuid(String uuid, int start, int end,
		OrderByComparator<ImportReport> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the import reports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching import reports
	 */
	@Override
	public List<ImportReport> findByUuid(String uuid, int start, int end,
		OrderByComparator<ImportReport> orderByComparator,
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

		List<ImportReport> list = null;

		if (retrieveFromCache) {
			list = (List<ImportReport>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ImportReport importReport : list) {
					if (!Objects.equals(uuid, importReport.getUuid())) {
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

			query.append(_SQL_SELECT_IMPORTREPORT_WHERE);

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
				query.append(ImportReportModelImpl.ORDER_BY_JPQL);
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
					list = (List<ImportReport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImportReport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first import report in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import report
	 * @throws NoSuchImportReportException if a matching import report could not be found
	 */
	@Override
	public ImportReport findByUuid_First(String uuid,
		OrderByComparator<ImportReport> orderByComparator)
		throws NoSuchImportReportException {
		ImportReport importReport = fetchByUuid_First(uuid, orderByComparator);

		if (importReport != null) {
			return importReport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchImportReportException(msg.toString());
	}

	/**
	 * Returns the first import report in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching import report, or <code>null</code> if a matching import report could not be found
	 */
	@Override
	public ImportReport fetchByUuid_First(String uuid,
		OrderByComparator<ImportReport> orderByComparator) {
		List<ImportReport> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last import report in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import report
	 * @throws NoSuchImportReportException if a matching import report could not be found
	 */
	@Override
	public ImportReport findByUuid_Last(String uuid,
		OrderByComparator<ImportReport> orderByComparator)
		throws NoSuchImportReportException {
		ImportReport importReport = fetchByUuid_Last(uuid, orderByComparator);

		if (importReport != null) {
			return importReport;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchImportReportException(msg.toString());
	}

	/**
	 * Returns the last import report in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching import report, or <code>null</code> if a matching import report could not be found
	 */
	@Override
	public ImportReport fetchByUuid_Last(String uuid,
		OrderByComparator<ImportReport> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ImportReport> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the import reports before and after the current import report in the ordered set where uuid = &#63;.
	 *
	 * @param reportId the primary key of the current import report
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next import report
	 * @throws NoSuchImportReportException if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport[] findByUuid_PrevAndNext(long reportId, String uuid,
		OrderByComparator<ImportReport> orderByComparator)
		throws NoSuchImportReportException {
		ImportReport importReport = findByPrimaryKey(reportId);

		Session session = null;

		try {
			session = openSession();

			ImportReport[] array = new ImportReportImpl[3];

			array[0] = getByUuid_PrevAndNext(session, importReport, uuid,
					orderByComparator, true);

			array[1] = importReport;

			array[2] = getByUuid_PrevAndNext(session, importReport, uuid,
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

	protected ImportReport getByUuid_PrevAndNext(Session session,
		ImportReport importReport, String uuid,
		OrderByComparator<ImportReport> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_IMPORTREPORT_WHERE);

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
			query.append(ImportReportModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(importReport);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<ImportReport> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the import reports where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ImportReport importReport : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(importReport);
		}
	}

	/**
	 * Returns the number of import reports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching import reports
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_IMPORTREPORT_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "importReport.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "importReport.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(importReport.uuid IS NULL OR importReport.uuid = '')";

	public ImportReportPersistenceImpl() {
		setModelClass(ImportReport.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");
			dbColumnNames.put("lines", "lines_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the import report in the entity cache if it is enabled.
	 *
	 * @param importReport the import report
	 */
	@Override
	public void cacheResult(ImportReport importReport) {
		entityCache.putResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportImpl.class, importReport.getPrimaryKey(), importReport);

		importReport.resetOriginalValues();
	}

	/**
	 * Caches the import reports in the entity cache if it is enabled.
	 *
	 * @param importReports the import reports
	 */
	@Override
	public void cacheResult(List<ImportReport> importReports) {
		for (ImportReport importReport : importReports) {
			if (entityCache.getResult(
						ImportReportModelImpl.ENTITY_CACHE_ENABLED,
						ImportReportImpl.class, importReport.getPrimaryKey()) == null) {
				cacheResult(importReport);
			}
			else {
				importReport.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all import reports.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImportReportImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the import report.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImportReport importReport) {
		entityCache.removeResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportImpl.class, importReport.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImportReport> importReports) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImportReport importReport : importReports) {
			entityCache.removeResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
				ImportReportImpl.class, importReport.getPrimaryKey());
		}
	}

	/**
	 * Creates a new import report with the primary key. Does not add the import report to the database.
	 *
	 * @param reportId the primary key for the new import report
	 * @return the new import report
	 */
	@Override
	public ImportReport create(long reportId) {
		ImportReport importReport = new ImportReportImpl();

		importReport.setNew(true);
		importReport.setPrimaryKey(reportId);

		String uuid = PortalUUIDUtil.generate();

		importReport.setUuid(uuid);

		return importReport;
	}

	/**
	 * Removes the import report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reportId the primary key of the import report
	 * @return the import report that was removed
	 * @throws NoSuchImportReportException if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport remove(long reportId)
		throws NoSuchImportReportException {
		return remove((Serializable)reportId);
	}

	/**
	 * Removes the import report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the import report
	 * @return the import report that was removed
	 * @throws NoSuchImportReportException if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport remove(Serializable primaryKey)
		throws NoSuchImportReportException {
		Session session = null;

		try {
			session = openSession();

			ImportReport importReport = (ImportReport)session.get(ImportReportImpl.class,
					primaryKey);

			if (importReport == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImportReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(importReport);
		}
		catch (NoSuchImportReportException nsee) {
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
	protected ImportReport removeImpl(ImportReport importReport) {
		importReport = toUnwrappedModel(importReport);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(importReport)) {
				importReport = (ImportReport)session.get(ImportReportImpl.class,
						importReport.getPrimaryKeyObj());
			}

			if (importReport != null) {
				session.delete(importReport);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (importReport != null) {
			clearCache(importReport);
		}

		return importReport;
	}

	@Override
	public ImportReport updateImpl(ImportReport importReport) {
		importReport = toUnwrappedModel(importReport);

		boolean isNew = importReport.isNew();

		ImportReportModelImpl importReportModelImpl = (ImportReportModelImpl)importReport;

		if (Validator.isNull(importReport.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			importReport.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (importReport.isNew()) {
				session.save(importReport);

				importReport.setNew(false);
			}
			else {
				importReport = (ImportReport)session.merge(importReport);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ImportReportModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { importReportModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((importReportModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						importReportModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { importReportModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}
		}

		entityCache.putResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
			ImportReportImpl.class, importReport.getPrimaryKey(), importReport,
			false);

		importReport.resetOriginalValues();

		return importReport;
	}

	protected ImportReport toUnwrappedModel(ImportReport importReport) {
		if (importReport instanceof ImportReportImpl) {
			return importReport;
		}

		ImportReportImpl importReportImpl = new ImportReportImpl();

		importReportImpl.setNew(importReport.isNew());
		importReportImpl.setPrimaryKey(importReport.getPrimaryKey());

		importReportImpl.setUuid(importReport.getUuid());
		importReportImpl.setReportId(importReport.getReportId());
		importReportImpl.setProvider(importReport.getProvider());
		importReportImpl.setFilename(importReport.getFilename());
		importReportImpl.setStatus(importReport.getStatus());
		importReportImpl.setGlobalErrorCause(importReport.getGlobalErrorCause());
		importReportImpl.setNewEventsCount(importReport.getNewEventsCount());
		importReportImpl.setModifiedEventsCount(importReport.getModifiedEventsCount());
		importReportImpl.setErrorEventsCount(importReport.getErrorEventsCount());
		importReportImpl.setNewManifestationsCount(importReport.getNewManifestationsCount());
		importReportImpl.setModifiedManifestationsCount(importReport.getModifiedManifestationsCount());
		importReportImpl.setErrorManifestationsCount(importReport.getErrorManifestationsCount());
		importReportImpl.setStartDate(importReport.getStartDate());
		importReportImpl.setEndDate(importReport.getEndDate());

		return importReportImpl;
	}

	/**
	 * Returns the import report with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the import report
	 * @return the import report
	 * @throws NoSuchImportReportException if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImportReportException {
		ImportReport importReport = fetchByPrimaryKey(primaryKey);

		if (importReport == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImportReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return importReport;
	}

	/**
	 * Returns the import report with the primary key or throws a {@link NoSuchImportReportException} if it could not be found.
	 *
	 * @param reportId the primary key of the import report
	 * @return the import report
	 * @throws NoSuchImportReportException if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport findByPrimaryKey(long reportId)
		throws NoSuchImportReportException {
		return findByPrimaryKey((Serializable)reportId);
	}

	/**
	 * Returns the import report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the import report
	 * @return the import report, or <code>null</code> if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
				ImportReportImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImportReport importReport = (ImportReport)serializable;

		if (importReport == null) {
			Session session = null;

			try {
				session = openSession();

				importReport = (ImportReport)session.get(ImportReportImpl.class,
						primaryKey);

				if (importReport != null) {
					cacheResult(importReport);
				}
				else {
					entityCache.putResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
						ImportReportImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
					ImportReportImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return importReport;
	}

	/**
	 * Returns the import report with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param reportId the primary key of the import report
	 * @return the import report, or <code>null</code> if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport fetchByPrimaryKey(long reportId) {
		return fetchByPrimaryKey((Serializable)reportId);
	}

	@Override
	public Map<Serializable, ImportReport> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImportReport> map = new HashMap<Serializable, ImportReport>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImportReport importReport = fetchByPrimaryKey(primaryKey);

			if (importReport != null) {
				map.put(primaryKey, importReport);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
					ImportReportImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImportReport)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMPORTREPORT_WHERE_PKS_IN);

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

			for (ImportReport importReport : (List<ImportReport>)q.list()) {
				map.put(importReport.getPrimaryKeyObj(), importReport);

				cacheResult(importReport);

				uncachedPrimaryKeys.remove(importReport.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImportReportModelImpl.ENTITY_CACHE_ENABLED,
					ImportReportImpl.class, primaryKey, nullModel);
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
	 * Returns all the import reports.
	 *
	 * @return the import reports
	 */
	@Override
	public List<ImportReport> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the import reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @return the range of import reports
	 */
	@Override
	public List<ImportReport> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the import reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of import reports
	 */
	@Override
	public List<ImportReport> findAll(int start, int end,
		OrderByComparator<ImportReport> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the import reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of import reports
	 */
	@Override
	public List<ImportReport> findAll(int start, int end,
		OrderByComparator<ImportReport> orderByComparator,
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

		List<ImportReport> list = null;

		if (retrieveFromCache) {
			list = (List<ImportReport>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMPORTREPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMPORTREPORT;

				if (pagination) {
					sql = sql.concat(ImportReportModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImportReport>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImportReport>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the import reports from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImportReport importReport : findAll()) {
			remove(importReport);
		}
	}

	/**
	 * Returns the number of import reports.
	 *
	 * @return the number of import reports
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMPORTREPORT);

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
		return ImportReportModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the import report persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImportReportImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMPORTREPORT = "SELECT importReport FROM ImportReport importReport";
	private static final String _SQL_SELECT_IMPORTREPORT_WHERE_PKS_IN = "SELECT importReport FROM ImportReport importReport WHERE reportId IN (";
	private static final String _SQL_SELECT_IMPORTREPORT_WHERE = "SELECT importReport FROM ImportReport importReport WHERE ";
	private static final String _SQL_COUNT_IMPORTREPORT = "SELECT COUNT(importReport) FROM ImportReport importReport";
	private static final String _SQL_COUNT_IMPORTREPORT_WHERE = "SELECT COUNT(importReport) FROM ImportReport importReport WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "importReport.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImportReport exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImportReport exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ImportReportPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "lines"
			});
}