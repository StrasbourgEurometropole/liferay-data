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

package eu.strasbourg.service.project.service.persistence.impl;

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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.project.exception.NoSuchProjectFollowedException;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.model.impl.ProjectFollowedImpl;
import eu.strasbourg.service.project.model.impl.ProjectFollowedModelImpl;
import eu.strasbourg.service.project.service.persistence.ProjectFollowedPersistence;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the project followed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
public class ProjectFollowedPersistenceImpl
	extends BasePersistenceImpl<ProjectFollowed>
	implements ProjectFollowedPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProjectFollowedUtil</code> to access the project followed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProjectFollowedImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByPublikUserId;
	private FinderPath _finderPathWithoutPaginationFindByPublikUserId;
	private FinderPath _finderPathCountByPublikUserId;

	/**
	 * Returns all the project followeds where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByPublikUserId(String publikUserId) {
		return findByPublikUserId(
			publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the project followeds where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @return the range of matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByPublikUserId(
		String publikUserId, int start, int end) {

		return findByPublikUserId(publikUserId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the project followeds where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator) {

		return findByPublikUserId(
			publikUserId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the project followeds where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator,
		boolean useFinderCache) {

		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPublikUserId;
				finderArgs = new Object[] {publikUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPublikUserId;
			finderArgs = new Object[] {
				publikUserId, start, end, orderByComparator
			};
		}

		List<ProjectFollowed> list = null;

		if (useFinderCache) {
			list = (List<ProjectFollowed>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProjectFollowed projectFollowed : list) {
					if (!publikUserId.equals(
							projectFollowed.getPublikUserId())) {

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

			sb.append(_SQL_SELECT_PROJECTFOLLOWED_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProjectFollowedModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
				}

				list = (List<ProjectFollowed>)QueryUtil.list(
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
	 * Returns the first project followed in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project followed
	 * @throws NoSuchProjectFollowedException if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed findByPublikUserId_First(
			String publikUserId,
			OrderByComparator<ProjectFollowed> orderByComparator)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = fetchByPublikUserId_First(
			publikUserId, orderByComparator);

		if (projectFollowed != null) {
			return projectFollowed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchProjectFollowedException(sb.toString());
	}

	/**
	 * Returns the first project followed in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project followed, or <code>null</code> if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed fetchByPublikUserId_First(
		String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator) {

		List<ProjectFollowed> list = findByPublikUserId(
			publikUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last project followed in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project followed
	 * @throws NoSuchProjectFollowedException if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed findByPublikUserId_Last(
			String publikUserId,
			OrderByComparator<ProjectFollowed> orderByComparator)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = fetchByPublikUserId_Last(
			publikUserId, orderByComparator);

		if (projectFollowed != null) {
			return projectFollowed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("publikUserId=");
		sb.append(publikUserId);

		sb.append("}");

		throw new NoSuchProjectFollowedException(sb.toString());
	}

	/**
	 * Returns the last project followed in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project followed, or <code>null</code> if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed fetchByPublikUserId_Last(
		String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator) {

		int count = countByPublikUserId(publikUserId);

		if (count == 0) {
			return null;
		}

		List<ProjectFollowed> list = findByPublikUserId(
			publikUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the project followeds before and after the current project followed in the ordered set where publikUserId = &#63;.
	 *
	 * @param projectFollowedId the primary key of the current project followed
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next project followed
	 * @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed[] findByPublikUserId_PrevAndNext(
			long projectFollowedId, String publikUserId,
			OrderByComparator<ProjectFollowed> orderByComparator)
		throws NoSuchProjectFollowedException {

		publikUserId = Objects.toString(publikUserId, "");

		ProjectFollowed projectFollowed = findByPrimaryKey(projectFollowedId);

		Session session = null;

		try {
			session = openSession();

			ProjectFollowed[] array = new ProjectFollowedImpl[3];

			array[0] = getByPublikUserId_PrevAndNext(
				session, projectFollowed, publikUserId, orderByComparator,
				true);

			array[1] = projectFollowed;

			array[2] = getByPublikUserId_PrevAndNext(
				session, projectFollowed, publikUserId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProjectFollowed getByPublikUserId_PrevAndNext(
		Session session, ProjectFollowed projectFollowed, String publikUserId,
		OrderByComparator<ProjectFollowed> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_PROJECTFOLLOWED_WHERE);

		boolean bindPublikUserId = false;

		if (publikUserId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
		}
		else {
			bindPublikUserId = true;

			sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
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
			sb.append(ProjectFollowedModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindPublikUserId) {
			queryPos.add(publikUserId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						projectFollowed)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProjectFollowed> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the project followeds where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	@Override
	public void removeByPublikUserId(String publikUserId) {
		for (ProjectFollowed projectFollowed :
				findByPublikUserId(
					publikUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(projectFollowed);
		}
	}

	/**
	 * Returns the number of project followeds where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching project followeds
	 */
	@Override
	public int countByPublikUserId(String publikUserId) {
		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = _finderPathCountByPublikUserId;

		Object[] finderArgs = new Object[] {publikUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROJECTFOLLOWED_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(_FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
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

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_2 =
		"projectFollowed.publikUserId = ?";

	private static final String _FINDER_COLUMN_PUBLIKUSERID_PUBLIKUSERID_3 =
		"(projectFollowed.publikUserId IS NULL OR projectFollowed.publikUserId = '')";

	private FinderPath _finderPathWithPaginationFindByProjectId;
	private FinderPath _finderPathWithoutPaginationFindByProjectId;
	private FinderPath _finderPathCountByProjectId;

	/**
	 * Returns all the project followeds where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByProjectId(long projectId) {
		return findByProjectId(
			projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the project followeds where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @return the range of matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByProjectId(
		long projectId, int start, int end) {

		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the project followeds where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByProjectId(
		long projectId, int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator) {

		return findByProjectId(projectId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the project followeds where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching project followeds
	 */
	@Override
	public List<ProjectFollowed> findByProjectId(
		long projectId, int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByProjectId;
				finderArgs = new Object[] {projectId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByProjectId;
			finderArgs = new Object[] {
				projectId, start, end, orderByComparator
			};
		}

		List<ProjectFollowed> list = null;

		if (useFinderCache) {
			list = (List<ProjectFollowed>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProjectFollowed projectFollowed : list) {
					if (projectId != projectFollowed.getProjectId()) {
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

			sb.append(_SQL_SELECT_PROJECTFOLLOWED_WHERE);

			sb.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProjectFollowedModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(projectId);

				list = (List<ProjectFollowed>)QueryUtil.list(
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
	 * Returns the first project followed in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project followed
	 * @throws NoSuchProjectFollowedException if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed findByProjectId_First(
			long projectId,
			OrderByComparator<ProjectFollowed> orderByComparator)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = fetchByProjectId_First(
			projectId, orderByComparator);

		if (projectFollowed != null) {
			return projectFollowed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("projectId=");
		sb.append(projectId);

		sb.append("}");

		throw new NoSuchProjectFollowedException(sb.toString());
	}

	/**
	 * Returns the first project followed in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project followed, or <code>null</code> if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed fetchByProjectId_First(
		long projectId, OrderByComparator<ProjectFollowed> orderByComparator) {

		List<ProjectFollowed> list = findByProjectId(
			projectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last project followed in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project followed
	 * @throws NoSuchProjectFollowedException if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed findByProjectId_Last(
			long projectId,
			OrderByComparator<ProjectFollowed> orderByComparator)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = fetchByProjectId_Last(
			projectId, orderByComparator);

		if (projectFollowed != null) {
			return projectFollowed;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("projectId=");
		sb.append(projectId);

		sb.append("}");

		throw new NoSuchProjectFollowedException(sb.toString());
	}

	/**
	 * Returns the last project followed in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project followed, or <code>null</code> if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed fetchByProjectId_Last(
		long projectId, OrderByComparator<ProjectFollowed> orderByComparator) {

		int count = countByProjectId(projectId);

		if (count == 0) {
			return null;
		}

		List<ProjectFollowed> list = findByProjectId(
			projectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the project followeds before and after the current project followed in the ordered set where projectId = &#63;.
	 *
	 * @param projectFollowedId the primary key of the current project followed
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next project followed
	 * @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed[] findByProjectId_PrevAndNext(
			long projectFollowedId, long projectId,
			OrderByComparator<ProjectFollowed> orderByComparator)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = findByPrimaryKey(projectFollowedId);

		Session session = null;

		try {
			session = openSession();

			ProjectFollowed[] array = new ProjectFollowedImpl[3];

			array[0] = getByProjectId_PrevAndNext(
				session, projectFollowed, projectId, orderByComparator, true);

			array[1] = projectFollowed;

			array[2] = getByProjectId_PrevAndNext(
				session, projectFollowed, projectId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProjectFollowed getByProjectId_PrevAndNext(
		Session session, ProjectFollowed projectFollowed, long projectId,
		OrderByComparator<ProjectFollowed> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_PROJECTFOLLOWED_WHERE);

		sb.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

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
			sb.append(ProjectFollowedModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(projectId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						projectFollowed)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProjectFollowed> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the project followeds where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 */
	@Override
	public void removeByProjectId(long projectId) {
		for (ProjectFollowed projectFollowed :
				findByProjectId(
					projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(projectFollowed);
		}
	}

	/**
	 * Returns the number of project followeds where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching project followeds
	 */
	@Override
	public int countByProjectId(long projectId) {
		FinderPath finderPath = _finderPathCountByProjectId;

		Object[] finderArgs = new Object[] {projectId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROJECTFOLLOWED_WHERE);

			sb.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(projectId);

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

	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 =
		"projectFollowed.projectId = ?";

	private FinderPath _finderPathFetchByPublikUserIdAndProjectId;
	private FinderPath _finderPathCountByPublikUserIdAndProjectId;

	/**
	 * Returns the project followed where publikUserId = &#63; and projectId = &#63; or throws a <code>NoSuchProjectFollowedException</code> if it could not be found.
	 *
	 * @param publikUserId the publik user ID
	 * @param projectId the project ID
	 * @return the matching project followed
	 * @throws NoSuchProjectFollowedException if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed findByPublikUserIdAndProjectId(
			String publikUserId, long projectId)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = fetchByPublikUserIdAndProjectId(
			publikUserId, projectId);

		if (projectFollowed == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("publikUserId=");
			sb.append(publikUserId);

			sb.append(", projectId=");
			sb.append(projectId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchProjectFollowedException(sb.toString());
		}

		return projectFollowed;
	}

	/**
	 * Returns the project followed where publikUserId = &#63; and projectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param projectId the project ID
	 * @return the matching project followed, or <code>null</code> if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed fetchByPublikUserIdAndProjectId(
		String publikUserId, long projectId) {

		return fetchByPublikUserIdAndProjectId(publikUserId, projectId, true);
	}

	/**
	 * Returns the project followed where publikUserId = &#63; and projectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param projectId the project ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching project followed, or <code>null</code> if a matching project followed could not be found
	 */
	@Override
	public ProjectFollowed fetchByPublikUserIdAndProjectId(
		String publikUserId, long projectId, boolean useFinderCache) {

		publikUserId = Objects.toString(publikUserId, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {publikUserId, projectId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByPublikUserIdAndProjectId, finderArgs, this);
		}

		if (result instanceof ProjectFollowed) {
			ProjectFollowed projectFollowed = (ProjectFollowed)result;

			if (!Objects.equals(
					publikUserId, projectFollowed.getPublikUserId()) ||
				(projectId != projectFollowed.getProjectId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_PROJECTFOLLOWED_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(
					_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PUBLIKUSERID_2);
			}

			sb.append(_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PROJECTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
				}

				queryPos.add(projectId);

				List<ProjectFollowed> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByPublikUserIdAndProjectId,
							finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									publikUserId, projectId
								};
							}

							_log.warn(
								"ProjectFollowedPersistenceImpl.fetchByPublikUserIdAndProjectId(String, long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProjectFollowed projectFollowed = list.get(0);

					result = projectFollowed;

					cacheResult(projectFollowed);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByPublikUserIdAndProjectId, finderArgs);
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
			return (ProjectFollowed)result;
		}
	}

	/**
	 * Removes the project followed where publikUserId = &#63; and projectId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 * @param projectId the project ID
	 * @return the project followed that was removed
	 */
	@Override
	public ProjectFollowed removeByPublikUserIdAndProjectId(
			String publikUserId, long projectId)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = findByPublikUserIdAndProjectId(
			publikUserId, projectId);

		return remove(projectFollowed);
	}

	/**
	 * Returns the number of project followeds where publikUserId = &#63; and projectId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param projectId the project ID
	 * @return the number of matching project followeds
	 */
	@Override
	public int countByPublikUserIdAndProjectId(
		String publikUserId, long projectId) {

		publikUserId = Objects.toString(publikUserId, "");

		FinderPath finderPath = _finderPathCountByPublikUserIdAndProjectId;

		Object[] finderArgs = new Object[] {publikUserId, projectId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PROJECTFOLLOWED_WHERE);

			boolean bindPublikUserId = false;

			if (publikUserId.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PUBLIKUSERID_3);
			}
			else {
				bindPublikUserId = true;

				sb.append(
					_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PUBLIKUSERID_2);
			}

			sb.append(_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PROJECTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindPublikUserId) {
					queryPos.add(publikUserId);
				}

				queryPos.add(projectId);

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
		_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PUBLIKUSERID_2 =
			"projectFollowed.publikUserId = ? AND ";

	private static final String
		_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PUBLIKUSERID_3 =
			"(projectFollowed.publikUserId IS NULL OR projectFollowed.publikUserId = '') AND ";

	private static final String
		_FINDER_COLUMN_PUBLIKUSERIDANDPROJECTID_PROJECTID_2 =
			"projectFollowed.projectId = ?";

	public ProjectFollowedPersistenceImpl() {
		setModelClass(ProjectFollowed.class);
	}

	/**
	 * Caches the project followed in the entity cache if it is enabled.
	 *
	 * @param projectFollowed the project followed
	 */
	@Override
	public void cacheResult(ProjectFollowed projectFollowed) {
		entityCache.putResult(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedImpl.class, projectFollowed.getPrimaryKey(),
			projectFollowed);

		finderCache.putResult(
			_finderPathFetchByPublikUserIdAndProjectId,
			new Object[] {
				projectFollowed.getPublikUserId(),
				projectFollowed.getProjectId()
			},
			projectFollowed);

		projectFollowed.resetOriginalValues();
	}

	/**
	 * Caches the project followeds in the entity cache if it is enabled.
	 *
	 * @param projectFolloweds the project followeds
	 */
	@Override
	public void cacheResult(List<ProjectFollowed> projectFolloweds) {
		for (ProjectFollowed projectFollowed : projectFolloweds) {
			if (entityCache.getResult(
					ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
					ProjectFollowedImpl.class,
					projectFollowed.getPrimaryKey()) == null) {

				cacheResult(projectFollowed);
			}
			else {
				projectFollowed.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all project followeds.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectFollowedImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the project followed.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectFollowed projectFollowed) {
		entityCache.removeResult(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedImpl.class, projectFollowed.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ProjectFollowedModelImpl)projectFollowed, true);
	}

	@Override
	public void clearCache(List<ProjectFollowed> projectFolloweds) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectFollowed projectFollowed : projectFolloweds) {
			entityCache.removeResult(
				ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
				ProjectFollowedImpl.class, projectFollowed.getPrimaryKey());

			clearUniqueFindersCache(
				(ProjectFollowedModelImpl)projectFollowed, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
				ProjectFollowedImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProjectFollowedModelImpl projectFollowedModelImpl) {

		Object[] args = new Object[] {
			projectFollowedModelImpl.getPublikUserId(),
			projectFollowedModelImpl.getProjectId()
		};

		finderCache.putResult(
			_finderPathCountByPublikUserIdAndProjectId, args, Long.valueOf(1),
			false);
		finderCache.putResult(
			_finderPathFetchByPublikUserIdAndProjectId, args,
			projectFollowedModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProjectFollowedModelImpl projectFollowedModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				projectFollowedModelImpl.getPublikUserId(),
				projectFollowedModelImpl.getProjectId()
			};

			finderCache.removeResult(
				_finderPathCountByPublikUserIdAndProjectId, args);
			finderCache.removeResult(
				_finderPathFetchByPublikUserIdAndProjectId, args);
		}

		if ((projectFollowedModelImpl.getColumnBitmask() &
			 _finderPathFetchByPublikUserIdAndProjectId.getColumnBitmask()) !=
				 0) {

			Object[] args = new Object[] {
				projectFollowedModelImpl.getOriginalPublikUserId(),
				projectFollowedModelImpl.getOriginalProjectId()
			};

			finderCache.removeResult(
				_finderPathCountByPublikUserIdAndProjectId, args);
			finderCache.removeResult(
				_finderPathFetchByPublikUserIdAndProjectId, args);
		}
	}

	/**
	 * Creates a new project followed with the primary key. Does not add the project followed to the database.
	 *
	 * @param projectFollowedId the primary key for the new project followed
	 * @return the new project followed
	 */
	@Override
	public ProjectFollowed create(long projectFollowedId) {
		ProjectFollowed projectFollowed = new ProjectFollowedImpl();

		projectFollowed.setNew(true);
		projectFollowed.setPrimaryKey(projectFollowedId);

		return projectFollowed;
	}

	/**
	 * Removes the project followed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectFollowedId the primary key of the project followed
	 * @return the project followed that was removed
	 * @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed remove(long projectFollowedId)
		throws NoSuchProjectFollowedException {

		return remove((Serializable)projectFollowedId);
	}

	/**
	 * Removes the project followed with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the project followed
	 * @return the project followed that was removed
	 * @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed remove(Serializable primaryKey)
		throws NoSuchProjectFollowedException {

		Session session = null;

		try {
			session = openSession();

			ProjectFollowed projectFollowed = (ProjectFollowed)session.get(
				ProjectFollowedImpl.class, primaryKey);

			if (projectFollowed == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectFollowedException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(projectFollowed);
		}
		catch (NoSuchProjectFollowedException noSuchEntityException) {
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
	protected ProjectFollowed removeImpl(ProjectFollowed projectFollowed) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectFollowed)) {
				projectFollowed = (ProjectFollowed)session.get(
					ProjectFollowedImpl.class,
					projectFollowed.getPrimaryKeyObj());
			}

			if (projectFollowed != null) {
				session.delete(projectFollowed);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (projectFollowed != null) {
			clearCache(projectFollowed);
		}

		return projectFollowed;
	}

	@Override
	public ProjectFollowed updateImpl(ProjectFollowed projectFollowed) {
		boolean isNew = projectFollowed.isNew();

		if (!(projectFollowed instanceof ProjectFollowedModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(projectFollowed.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					projectFollowed);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in projectFollowed proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProjectFollowed implementation " +
					projectFollowed.getClass());
		}

		ProjectFollowedModelImpl projectFollowedModelImpl =
			(ProjectFollowedModelImpl)projectFollowed;

		Session session = null;

		try {
			session = openSession();

			if (projectFollowed.isNew()) {
				session.save(projectFollowed);

				projectFollowed.setNew(false);
			}
			else {
				projectFollowed = (ProjectFollowed)session.merge(
					projectFollowed);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProjectFollowedModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				projectFollowedModelImpl.getPublikUserId()
			};

			finderCache.removeResult(_finderPathCountByPublikUserId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPublikUserId, args);

			args = new Object[] {projectFollowedModelImpl.getProjectId()};

			finderCache.removeResult(_finderPathCountByProjectId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProjectId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((projectFollowedModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPublikUserId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					projectFollowedModelImpl.getOriginalPublikUserId()
				};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);

				args = new Object[] {
					projectFollowedModelImpl.getPublikUserId()
				};

				finderCache.removeResult(_finderPathCountByPublikUserId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPublikUserId, args);
			}

			if ((projectFollowedModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProjectId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					projectFollowedModelImpl.getOriginalProjectId()
				};

				finderCache.removeResult(_finderPathCountByProjectId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProjectId, args);

				args = new Object[] {projectFollowedModelImpl.getProjectId()};

				finderCache.removeResult(_finderPathCountByProjectId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProjectId, args);
			}
		}

		entityCache.putResult(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedImpl.class, projectFollowed.getPrimaryKey(),
			projectFollowed, false);

		clearUniqueFindersCache(projectFollowedModelImpl, false);
		cacheUniqueFindersCache(projectFollowedModelImpl);

		projectFollowed.resetOriginalValues();

		return projectFollowed;
	}

	/**
	 * Returns the project followed with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the project followed
	 * @return the project followed
	 * @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectFollowedException {

		ProjectFollowed projectFollowed = fetchByPrimaryKey(primaryKey);

		if (projectFollowed == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectFollowedException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return projectFollowed;
	}

	/**
	 * Returns the project followed with the primary key or throws a <code>NoSuchProjectFollowedException</code> if it could not be found.
	 *
	 * @param projectFollowedId the primary key of the project followed
	 * @return the project followed
	 * @throws NoSuchProjectFollowedException if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed findByPrimaryKey(long projectFollowedId)
		throws NoSuchProjectFollowedException {

		return findByPrimaryKey((Serializable)projectFollowedId);
	}

	/**
	 * Returns the project followed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the project followed
	 * @return the project followed, or <code>null</code> if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectFollowed projectFollowed = (ProjectFollowed)serializable;

		if (projectFollowed == null) {
			Session session = null;

			try {
				session = openSession();

				projectFollowed = (ProjectFollowed)session.get(
					ProjectFollowedImpl.class, primaryKey);

				if (projectFollowed != null) {
					cacheResult(projectFollowed);
				}
				else {
					entityCache.putResult(
						ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
						ProjectFollowedImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
					ProjectFollowedImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return projectFollowed;
	}

	/**
	 * Returns the project followed with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectFollowedId the primary key of the project followed
	 * @return the project followed, or <code>null</code> if a project followed with the primary key could not be found
	 */
	@Override
	public ProjectFollowed fetchByPrimaryKey(long projectFollowedId) {
		return fetchByPrimaryKey((Serializable)projectFollowedId);
	}

	@Override
	public Map<Serializable, ProjectFollowed> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectFollowed> map =
			new HashMap<Serializable, ProjectFollowed>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectFollowed projectFollowed = fetchByPrimaryKey(primaryKey);

			if (projectFollowed != null) {
				map.put(primaryKey, projectFollowed);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
				ProjectFollowedImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectFollowed)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_PROJECTFOLLOWED_WHERE_PKS_IN);

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

			for (ProjectFollowed projectFollowed :
					(List<ProjectFollowed>)query.list()) {

				map.put(projectFollowed.getPrimaryKeyObj(), projectFollowed);

				cacheResult(projectFollowed);

				uncachedPrimaryKeys.remove(projectFollowed.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
					ProjectFollowedImpl.class, primaryKey, nullModel);
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
	 * Returns all the project followeds.
	 *
	 * @return the project followeds
	 */
	@Override
	public List<ProjectFollowed> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the project followeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @return the range of project followeds
	 */
	@Override
	public List<ProjectFollowed> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the project followeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of project followeds
	 */
	@Override
	public List<ProjectFollowed> findAll(
		int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the project followeds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectFollowedModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project followeds
	 * @param end the upper bound of the range of project followeds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of project followeds
	 */
	@Override
	public List<ProjectFollowed> findAll(
		int start, int end,
		OrderByComparator<ProjectFollowed> orderByComparator,
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

		List<ProjectFollowed> list = null;

		if (useFinderCache) {
			list = (List<ProjectFollowed>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROJECTFOLLOWED);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTFOLLOWED;

				sql = sql.concat(ProjectFollowedModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProjectFollowed>)QueryUtil.list(
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
	 * Removes all the project followeds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectFollowed projectFollowed : findAll()) {
			remove(projectFollowed);
		}
	}

	/**
	 * Returns the number of project followeds.
	 *
	 * @return the number of project followeds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROJECTFOLLOWED);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return ProjectFollowedModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the project followed persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED,
			ProjectFollowedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED,
			ProjectFollowedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByPublikUserId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED,
			ProjectFollowedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPublikUserId",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPublikUserId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED,
			ProjectFollowedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPublikUserId",
			new String[] {String.class.getName()},
			ProjectFollowedModelImpl.PUBLIKUSERID_COLUMN_BITMASK);

		_finderPathCountByPublikUserId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPublikUserId",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByProjectId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED,
			ProjectFollowedImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByProjectId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProjectId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED,
			ProjectFollowedImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProjectId",
			new String[] {Long.class.getName()},
			ProjectFollowedModelImpl.PROJECTID_COLUMN_BITMASK);

		_finderPathCountByProjectId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProjectId",
			new String[] {Long.class.getName()});

		_finderPathFetchByPublikUserIdAndProjectId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED,
			ProjectFollowedImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByPublikUserIdAndProjectId",
			new String[] {String.class.getName(), Long.class.getName()},
			ProjectFollowedModelImpl.PUBLIKUSERID_COLUMN_BITMASK |
			ProjectFollowedModelImpl.PROJECTID_COLUMN_BITMASK);

		_finderPathCountByPublikUserIdAndProjectId = new FinderPath(
			ProjectFollowedModelImpl.ENTITY_CACHE_ENABLED,
			ProjectFollowedModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByPublikUserIdAndProjectId",
			new String[] {String.class.getName(), Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(ProjectFollowedImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PROJECTFOLLOWED =
		"SELECT projectFollowed FROM ProjectFollowed projectFollowed";

	private static final String _SQL_SELECT_PROJECTFOLLOWED_WHERE_PKS_IN =
		"SELECT projectFollowed FROM ProjectFollowed projectFollowed WHERE projectFollowedId IN (";

	private static final String _SQL_SELECT_PROJECTFOLLOWED_WHERE =
		"SELECT projectFollowed FROM ProjectFollowed projectFollowed WHERE ";

	private static final String _SQL_COUNT_PROJECTFOLLOWED =
		"SELECT COUNT(projectFollowed) FROM ProjectFollowed projectFollowed";

	private static final String _SQL_COUNT_PROJECTFOLLOWED_WHERE =
		"SELECT COUNT(projectFollowed) FROM ProjectFollowed projectFollowed WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "projectFollowed.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProjectFollowed exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProjectFollowed exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProjectFollowedPersistenceImpl.class);

}