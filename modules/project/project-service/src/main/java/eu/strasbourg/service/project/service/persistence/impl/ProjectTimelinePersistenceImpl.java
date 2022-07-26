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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.project.exception.NoSuchProjectTimelineException;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.model.impl.ProjectTimelineImpl;
import eu.strasbourg.service.project.model.impl.ProjectTimelineModelImpl;
import eu.strasbourg.service.project.service.persistence.ProjectTimelinePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the project timeline service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @generated
 */
public class ProjectTimelinePersistenceImpl
	extends BasePersistenceImpl<ProjectTimeline>
	implements ProjectTimelinePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProjectTimelineUtil</code> to access the project timeline persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProjectTimelineImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByProjectId;
	private FinderPath _finderPathWithoutPaginationFindByProjectId;
	private FinderPath _finderPathCountByProjectId;

	/**
	 * Returns all the project timelines where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByProjectId(long projectId) {
		return findByProjectId(
			projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the project timelines where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @return the range of matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByProjectId(
		long projectId, int start, int end) {

		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the project timelines where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByProjectId(
		long projectId, int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator) {

		return findByProjectId(projectId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the project timelines where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByProjectId(
		long projectId, int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator,
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

		List<ProjectTimeline> list = null;

		if (useFinderCache) {
			list = (List<ProjectTimeline>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProjectTimeline projectTimeline : list) {
					if (projectId != projectTimeline.getProjectId()) {
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

			sb.append(_SQL_SELECT_PROJECTTIMELINE_WHERE);

			sb.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProjectTimelineModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(projectId);

				list = (List<ProjectTimeline>)QueryUtil.list(
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
	 * Returns the first project timeline in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project timeline
	 * @throws NoSuchProjectTimelineException if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline findByProjectId_First(
			long projectId,
			OrderByComparator<ProjectTimeline> orderByComparator)
		throws NoSuchProjectTimelineException {

		ProjectTimeline projectTimeline = fetchByProjectId_First(
			projectId, orderByComparator);

		if (projectTimeline != null) {
			return projectTimeline;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("projectId=");
		sb.append(projectId);

		sb.append("}");

		throw new NoSuchProjectTimelineException(sb.toString());
	}

	/**
	 * Returns the first project timeline in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project timeline, or <code>null</code> if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline fetchByProjectId_First(
		long projectId, OrderByComparator<ProjectTimeline> orderByComparator) {

		List<ProjectTimeline> list = findByProjectId(
			projectId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last project timeline in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project timeline
	 * @throws NoSuchProjectTimelineException if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline findByProjectId_Last(
			long projectId,
			OrderByComparator<ProjectTimeline> orderByComparator)
		throws NoSuchProjectTimelineException {

		ProjectTimeline projectTimeline = fetchByProjectId_Last(
			projectId, orderByComparator);

		if (projectTimeline != null) {
			return projectTimeline;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("projectId=");
		sb.append(projectId);

		sb.append("}");

		throw new NoSuchProjectTimelineException(sb.toString());
	}

	/**
	 * Returns the last project timeline in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project timeline, or <code>null</code> if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline fetchByProjectId_Last(
		long projectId, OrderByComparator<ProjectTimeline> orderByComparator) {

		int count = countByProjectId(projectId);

		if (count == 0) {
			return null;
		}

		List<ProjectTimeline> list = findByProjectId(
			projectId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the project timelines before and after the current project timeline in the ordered set where projectId = &#63;.
	 *
	 * @param projectTimelineId the primary key of the current project timeline
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next project timeline
	 * @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline[] findByProjectId_PrevAndNext(
			long projectTimelineId, long projectId,
			OrderByComparator<ProjectTimeline> orderByComparator)
		throws NoSuchProjectTimelineException {

		ProjectTimeline projectTimeline = findByPrimaryKey(projectTimelineId);

		Session session = null;

		try {
			session = openSession();

			ProjectTimeline[] array = new ProjectTimelineImpl[3];

			array[0] = getByProjectId_PrevAndNext(
				session, projectTimeline, projectId, orderByComparator, true);

			array[1] = projectTimeline;

			array[2] = getByProjectId_PrevAndNext(
				session, projectTimeline, projectId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProjectTimeline getByProjectId_PrevAndNext(
		Session session, ProjectTimeline projectTimeline, long projectId,
		OrderByComparator<ProjectTimeline> orderByComparator,
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

		sb.append(_SQL_SELECT_PROJECTTIMELINE_WHERE);

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
			sb.append(ProjectTimelineModelImpl.ORDER_BY_JPQL);
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
						projectTimeline)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProjectTimeline> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the project timelines where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 */
	@Override
	public void removeByProjectId(long projectId) {
		for (ProjectTimeline projectTimeline :
				findByProjectId(
					projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(projectTimeline);
		}
	}

	/**
	 * Returns the number of project timelines where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching project timelines
	 */
	@Override
	public int countByProjectId(long projectId) {
		FinderPath finderPath = _finderPathCountByProjectId;

		Object[] finderArgs = new Object[] {projectId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROJECTTIMELINE_WHERE);

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
		"projectTimeline.projectId = ?";

	private FinderPath _finderPathWithPaginationFindByBudgetParticipatifId;
	private FinderPath _finderPathWithoutPaginationFindByBudgetParticipatifId;
	private FinderPath _finderPathCountByBudgetParticipatifId;

	/**
	 * Returns all the project timelines where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByBudgetParticipatifId(
		long budgetParticipatifId) {

		return findByBudgetParticipatifId(
			budgetParticipatifId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the project timelines where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @return the range of matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByBudgetParticipatifId(
		long budgetParticipatifId, int start, int end) {

		return findByBudgetParticipatifId(
			budgetParticipatifId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the project timelines where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByBudgetParticipatifId(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator) {

		return findByBudgetParticipatifId(
			budgetParticipatifId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the project timelines where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching project timelines
	 */
	@Override
	public List<ProjectTimeline> findByBudgetParticipatifId(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByBudgetParticipatifId;
				finderArgs = new Object[] {budgetParticipatifId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByBudgetParticipatifId;
			finderArgs = new Object[] {
				budgetParticipatifId, start, end, orderByComparator
			};
		}

		List<ProjectTimeline> list = null;

		if (useFinderCache) {
			list = (List<ProjectTimeline>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProjectTimeline projectTimeline : list) {
					if (budgetParticipatifId !=
							projectTimeline.getBudgetParticipatifId()) {

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

			sb.append(_SQL_SELECT_PROJECTTIMELINE_WHERE);

			sb.append(
				_FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProjectTimelineModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(budgetParticipatifId);

				list = (List<ProjectTimeline>)QueryUtil.list(
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
	 * Returns the first project timeline in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project timeline
	 * @throws NoSuchProjectTimelineException if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline findByBudgetParticipatifId_First(
			long budgetParticipatifId,
			OrderByComparator<ProjectTimeline> orderByComparator)
		throws NoSuchProjectTimelineException {

		ProjectTimeline projectTimeline = fetchByBudgetParticipatifId_First(
			budgetParticipatifId, orderByComparator);

		if (projectTimeline != null) {
			return projectTimeline;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("budgetParticipatifId=");
		sb.append(budgetParticipatifId);

		sb.append("}");

		throw new NoSuchProjectTimelineException(sb.toString());
	}

	/**
	 * Returns the first project timeline in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching project timeline, or <code>null</code> if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline fetchByBudgetParticipatifId_First(
		long budgetParticipatifId,
		OrderByComparator<ProjectTimeline> orderByComparator) {

		List<ProjectTimeline> list = findByBudgetParticipatifId(
			budgetParticipatifId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last project timeline in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project timeline
	 * @throws NoSuchProjectTimelineException if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline findByBudgetParticipatifId_Last(
			long budgetParticipatifId,
			OrderByComparator<ProjectTimeline> orderByComparator)
		throws NoSuchProjectTimelineException {

		ProjectTimeline projectTimeline = fetchByBudgetParticipatifId_Last(
			budgetParticipatifId, orderByComparator);

		if (projectTimeline != null) {
			return projectTimeline;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("budgetParticipatifId=");
		sb.append(budgetParticipatifId);

		sb.append("}");

		throw new NoSuchProjectTimelineException(sb.toString());
	}

	/**
	 * Returns the last project timeline in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching project timeline, or <code>null</code> if a matching project timeline could not be found
	 */
	@Override
	public ProjectTimeline fetchByBudgetParticipatifId_Last(
		long budgetParticipatifId,
		OrderByComparator<ProjectTimeline> orderByComparator) {

		int count = countByBudgetParticipatifId(budgetParticipatifId);

		if (count == 0) {
			return null;
		}

		List<ProjectTimeline> list = findByBudgetParticipatifId(
			budgetParticipatifId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the project timelines before and after the current project timeline in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param projectTimelineId the primary key of the current project timeline
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next project timeline
	 * @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline[] findByBudgetParticipatifId_PrevAndNext(
			long projectTimelineId, long budgetParticipatifId,
			OrderByComparator<ProjectTimeline> orderByComparator)
		throws NoSuchProjectTimelineException {

		ProjectTimeline projectTimeline = findByPrimaryKey(projectTimelineId);

		Session session = null;

		try {
			session = openSession();

			ProjectTimeline[] array = new ProjectTimelineImpl[3];

			array[0] = getByBudgetParticipatifId_PrevAndNext(
				session, projectTimeline, budgetParticipatifId,
				orderByComparator, true);

			array[1] = projectTimeline;

			array[2] = getByBudgetParticipatifId_PrevAndNext(
				session, projectTimeline, budgetParticipatifId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProjectTimeline getByBudgetParticipatifId_PrevAndNext(
		Session session, ProjectTimeline projectTimeline,
		long budgetParticipatifId,
		OrderByComparator<ProjectTimeline> orderByComparator,
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

		sb.append(_SQL_SELECT_PROJECTTIMELINE_WHERE);

		sb.append(_FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2);

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
			sb.append(ProjectTimelineModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(budgetParticipatifId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						projectTimeline)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProjectTimeline> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the project timelines where budgetParticipatifId = &#63; from the database.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 */
	@Override
	public void removeByBudgetParticipatifId(long budgetParticipatifId) {
		for (ProjectTimeline projectTimeline :
				findByBudgetParticipatifId(
					budgetParticipatifId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(projectTimeline);
		}
	}

	/**
	 * Returns the number of project timelines where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the number of matching project timelines
	 */
	@Override
	public int countByBudgetParticipatifId(long budgetParticipatifId) {
		FinderPath finderPath = _finderPathCountByBudgetParticipatifId;

		Object[] finderArgs = new Object[] {budgetParticipatifId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PROJECTTIMELINE_WHERE);

			sb.append(
				_FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(budgetParticipatifId);

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
		_FINDER_COLUMN_BUDGETPARTICIPATIFID_BUDGETPARTICIPATIFID_2 =
			"projectTimeline.budgetParticipatifId = ?";

	public ProjectTimelinePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("date", "date_");

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

		setModelClass(ProjectTimeline.class);
	}

	/**
	 * Caches the project timeline in the entity cache if it is enabled.
	 *
	 * @param projectTimeline the project timeline
	 */
	@Override
	public void cacheResult(ProjectTimeline projectTimeline) {
		entityCache.putResult(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineImpl.class, projectTimeline.getPrimaryKey(),
			projectTimeline);

		projectTimeline.resetOriginalValues();
	}

	/**
	 * Caches the project timelines in the entity cache if it is enabled.
	 *
	 * @param projectTimelines the project timelines
	 */
	@Override
	public void cacheResult(List<ProjectTimeline> projectTimelines) {
		for (ProjectTimeline projectTimeline : projectTimelines) {
			if (entityCache.getResult(
					ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
					ProjectTimelineImpl.class,
					projectTimeline.getPrimaryKey()) == null) {

				cacheResult(projectTimeline);
			}
			else {
				projectTimeline.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all project timelines.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectTimelineImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the project timeline.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectTimeline projectTimeline) {
		entityCache.removeResult(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineImpl.class, projectTimeline.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectTimeline> projectTimelines) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectTimeline projectTimeline : projectTimelines) {
			entityCache.removeResult(
				ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
				ProjectTimelineImpl.class, projectTimeline.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
				ProjectTimelineImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new project timeline with the primary key. Does not add the project timeline to the database.
	 *
	 * @param projectTimelineId the primary key for the new project timeline
	 * @return the new project timeline
	 */
	@Override
	public ProjectTimeline create(long projectTimelineId) {
		ProjectTimeline projectTimeline = new ProjectTimelineImpl();

		projectTimeline.setNew(true);
		projectTimeline.setPrimaryKey(projectTimelineId);

		return projectTimeline;
	}

	/**
	 * Removes the project timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectTimelineId the primary key of the project timeline
	 * @return the project timeline that was removed
	 * @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline remove(long projectTimelineId)
		throws NoSuchProjectTimelineException {

		return remove((Serializable)projectTimelineId);
	}

	/**
	 * Removes the project timeline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the project timeline
	 * @return the project timeline that was removed
	 * @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline remove(Serializable primaryKey)
		throws NoSuchProjectTimelineException {

		Session session = null;

		try {
			session = openSession();

			ProjectTimeline projectTimeline = (ProjectTimeline)session.get(
				ProjectTimelineImpl.class, primaryKey);

			if (projectTimeline == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectTimelineException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(projectTimeline);
		}
		catch (NoSuchProjectTimelineException noSuchEntityException) {
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
	protected ProjectTimeline removeImpl(ProjectTimeline projectTimeline) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectTimeline)) {
				projectTimeline = (ProjectTimeline)session.get(
					ProjectTimelineImpl.class,
					projectTimeline.getPrimaryKeyObj());
			}

			if (projectTimeline != null) {
				session.delete(projectTimeline);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (projectTimeline != null) {
			clearCache(projectTimeline);
		}

		return projectTimeline;
	}

	@Override
	public ProjectTimeline updateImpl(ProjectTimeline projectTimeline) {
		boolean isNew = projectTimeline.isNew();

		if (!(projectTimeline instanceof ProjectTimelineModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(projectTimeline.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					projectTimeline);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in projectTimeline proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProjectTimeline implementation " +
					projectTimeline.getClass());
		}

		ProjectTimelineModelImpl projectTimelineModelImpl =
			(ProjectTimelineModelImpl)projectTimeline;

		Session session = null;

		try {
			session = openSession();

			if (projectTimeline.isNew()) {
				session.save(projectTimeline);

				projectTimeline.setNew(false);
			}
			else {
				projectTimeline = (ProjectTimeline)session.merge(
					projectTimeline);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!ProjectTimelineModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				projectTimelineModelImpl.getProjectId()
			};

			finderCache.removeResult(_finderPathCountByProjectId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProjectId, args);

			args = new Object[] {
				projectTimelineModelImpl.getBudgetParticipatifId()
			};

			finderCache.removeResult(
				_finderPathCountByBudgetParticipatifId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByBudgetParticipatifId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((projectTimelineModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProjectId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					projectTimelineModelImpl.getOriginalProjectId()
				};

				finderCache.removeResult(_finderPathCountByProjectId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProjectId, args);

				args = new Object[] {projectTimelineModelImpl.getProjectId()};

				finderCache.removeResult(_finderPathCountByProjectId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProjectId, args);
			}

			if ((projectTimelineModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByBudgetParticipatifId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					projectTimelineModelImpl.getOriginalBudgetParticipatifId()
				};

				finderCache.removeResult(
					_finderPathCountByBudgetParticipatifId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBudgetParticipatifId,
					args);

				args = new Object[] {
					projectTimelineModelImpl.getBudgetParticipatifId()
				};

				finderCache.removeResult(
					_finderPathCountByBudgetParticipatifId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByBudgetParticipatifId,
					args);
			}
		}

		entityCache.putResult(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineImpl.class, projectTimeline.getPrimaryKey(),
			projectTimeline, false);

		projectTimeline.resetOriginalValues();

		return projectTimeline;
	}

	/**
	 * Returns the project timeline with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the project timeline
	 * @return the project timeline
	 * @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectTimelineException {

		ProjectTimeline projectTimeline = fetchByPrimaryKey(primaryKey);

		if (projectTimeline == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectTimelineException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return projectTimeline;
	}

	/**
	 * Returns the project timeline with the primary key or throws a <code>NoSuchProjectTimelineException</code> if it could not be found.
	 *
	 * @param projectTimelineId the primary key of the project timeline
	 * @return the project timeline
	 * @throws NoSuchProjectTimelineException if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline findByPrimaryKey(long projectTimelineId)
		throws NoSuchProjectTimelineException {

		return findByPrimaryKey((Serializable)projectTimelineId);
	}

	/**
	 * Returns the project timeline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the project timeline
	 * @return the project timeline, or <code>null</code> if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectTimeline projectTimeline = (ProjectTimeline)serializable;

		if (projectTimeline == null) {
			Session session = null;

			try {
				session = openSession();

				projectTimeline = (ProjectTimeline)session.get(
					ProjectTimelineImpl.class, primaryKey);

				if (projectTimeline != null) {
					cacheResult(projectTimeline);
				}
				else {
					entityCache.putResult(
						ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
						ProjectTimelineImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
					ProjectTimelineImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return projectTimeline;
	}

	/**
	 * Returns the project timeline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectTimelineId the primary key of the project timeline
	 * @return the project timeline, or <code>null</code> if a project timeline with the primary key could not be found
	 */
	@Override
	public ProjectTimeline fetchByPrimaryKey(long projectTimelineId) {
		return fetchByPrimaryKey((Serializable)projectTimelineId);
	}

	@Override
	public Map<Serializable, ProjectTimeline> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectTimeline> map =
			new HashMap<Serializable, ProjectTimeline>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectTimeline projectTimeline = fetchByPrimaryKey(primaryKey);

			if (projectTimeline != null) {
				map.put(primaryKey, projectTimeline);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
				ProjectTimelineImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectTimeline)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler sb = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		sb.append(_SQL_SELECT_PROJECTTIMELINE_WHERE_PKS_IN);

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

			for (ProjectTimeline projectTimeline :
					(List<ProjectTimeline>)query.list()) {

				map.put(projectTimeline.getPrimaryKeyObj(), projectTimeline);

				cacheResult(projectTimeline);

				uncachedPrimaryKeys.remove(projectTimeline.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
					ProjectTimelineImpl.class, primaryKey, nullModel);
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
	 * Returns all the project timelines.
	 *
	 * @return the project timelines
	 */
	@Override
	public List<ProjectTimeline> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the project timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @return the range of project timelines
	 */
	@Override
	public List<ProjectTimeline> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the project timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of project timelines
	 */
	@Override
	public List<ProjectTimeline> findAll(
		int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the project timelines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectTimelineModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project timelines
	 * @param end the upper bound of the range of project timelines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of project timelines
	 */
	@Override
	public List<ProjectTimeline> findAll(
		int start, int end,
		OrderByComparator<ProjectTimeline> orderByComparator,
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

		List<ProjectTimeline> list = null;

		if (useFinderCache) {
			list = (List<ProjectTimeline>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PROJECTTIMELINE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTTIMELINE;

				sql = sql.concat(ProjectTimelineModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProjectTimeline>)QueryUtil.list(
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
	 * Removes all the project timelines from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectTimeline projectTimeline : findAll()) {
			remove(projectTimeline);
		}
	}

	/**
	 * Returns the number of project timelines.
	 *
	 * @return the number of project timelines
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PROJECTTIMELINE);

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
		return ProjectTimelineModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the project timeline persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED,
			ProjectTimelineImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED,
			ProjectTimelineImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByProjectId = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED,
			ProjectTimelineImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByProjectId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProjectId = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED,
			ProjectTimelineImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProjectId",
			new String[] {Long.class.getName()},
			ProjectTimelineModelImpl.PROJECTID_COLUMN_BITMASK |
			ProjectTimelineModelImpl.DATE_COLUMN_BITMASK);

		_finderPathCountByProjectId = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProjectId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByBudgetParticipatifId = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED,
			ProjectTimelineImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByBudgetParticipatifId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByBudgetParticipatifId = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED,
			ProjectTimelineImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByBudgetParticipatifId", new String[] {Long.class.getName()},
			ProjectTimelineModelImpl.BUDGETPARTICIPATIFID_COLUMN_BITMASK |
			ProjectTimelineModelImpl.DATE_COLUMN_BITMASK);

		_finderPathCountByBudgetParticipatifId = new FinderPath(
			ProjectTimelineModelImpl.ENTITY_CACHE_ENABLED,
			ProjectTimelineModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByBudgetParticipatifId", new String[] {Long.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(ProjectTimelineImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PROJECTTIMELINE =
		"SELECT projectTimeline FROM ProjectTimeline projectTimeline";

	private static final String _SQL_SELECT_PROJECTTIMELINE_WHERE_PKS_IN =
		"SELECT projectTimeline FROM ProjectTimeline projectTimeline WHERE projectTimelineId IN (";

	private static final String _SQL_SELECT_PROJECTTIMELINE_WHERE =
		"SELECT projectTimeline FROM ProjectTimeline projectTimeline WHERE ";

	private static final String _SQL_COUNT_PROJECTTIMELINE =
		"SELECT COUNT(projectTimeline) FROM ProjectTimeline projectTimeline";

	private static final String _SQL_COUNT_PROJECTTIMELINE_WHERE =
		"SELECT COUNT(projectTimeline) FROM ProjectTimeline projectTimeline WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "projectTimeline.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProjectTimeline exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProjectTimeline exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProjectTimelinePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"date"});

}