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

package eu.strasbourg.service.csmap.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.csmap.exception.NoSuchAgendaException;
import eu.strasbourg.service.csmap.model.Agenda;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the agenda service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AgendaUtil
 * @generated
 */
@ProviderType
public interface AgendaPersistence extends BasePersistence<Agenda> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AgendaUtil} to access the agenda persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the agendas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching agendas
	 */
	public java.util.List<Agenda> findByUuid(String uuid);

	/**
	 * Returns a range of all the agendas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of matching agendas
	 */
	public java.util.List<Agenda> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the agendas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agendas
	 */
	public java.util.List<Agenda> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns an ordered range of all the agendas where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching agendas
	 */
	public java.util.List<Agenda> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public Agenda findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Returns the first agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public Agenda fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns the last agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public Agenda findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Returns the last agenda in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public Agenda fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns the agendas before and after the current agenda in the ordered set where uuid = &#63;.
	 *
	 * @param agendaId the primary key of the current agenda
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public Agenda[] findByUuid_PrevAndNext(
			long agendaId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Removes all the agendas where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of agendas where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching agendas
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the agendas where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @return the matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipal(Boolean isPrincipal);

	/**
	 * Returns a range of all the agendas where isPrincipal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipal(
		Boolean isPrincipal, int start, int end);

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipal(
		Boolean isPrincipal, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipal(
		Boolean isPrincipal, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public Agenda findByIsPrincipal_First(
			Boolean isPrincipal,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public Agenda fetchByIsPrincipal_First(
		Boolean isPrincipal,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public Agenda findByIsPrincipal_Last(
			Boolean isPrincipal,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public Agenda fetchByIsPrincipal_Last(
		Boolean isPrincipal,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns the agendas before and after the current agenda in the ordered set where isPrincipal = &#63;.
	 *
	 * @param agendaId the primary key of the current agenda
	 * @param isPrincipal the is principal
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public Agenda[] findByIsPrincipal_PrevAndNext(
			long agendaId, Boolean isPrincipal,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Removes all the agendas where isPrincipal = &#63; from the database.
	 *
	 * @param isPrincipal the is principal
	 */
	public void removeByIsPrincipal(Boolean isPrincipal);

	/**
	 * Returns the number of agendas where isPrincipal = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @return the number of matching agendas
	 */
	public int countByIsPrincipal(Boolean isPrincipal);

	/**
	 * Returns all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @return the matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive);

	/**
	 * Returns a range of all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive, int start, int end);

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns an ordered range of all the agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching agendas
	 */
	public java.util.List<Agenda> findByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public Agenda findByIsPrincipalAndIsActive_First(
			Boolean isPrincipal, Boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Returns the first agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public Agenda fetchByIsPrincipalAndIsActive_First(
		Boolean isPrincipal, Boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda
	 * @throws NoSuchAgendaException if a matching agenda could not be found
	 */
	public Agenda findByIsPrincipalAndIsActive_Last(
			Boolean isPrincipal, Boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Returns the last agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching agenda, or <code>null</code> if a matching agenda could not be found
	 */
	public Agenda fetchByIsPrincipalAndIsActive_Last(
		Boolean isPrincipal, Boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns the agendas before and after the current agenda in the ordered set where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param agendaId the primary key of the current agenda
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public Agenda[] findByIsPrincipalAndIsActive_PrevAndNext(
			long agendaId, Boolean isPrincipal, Boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<Agenda>
				orderByComparator)
		throws NoSuchAgendaException;

	/**
	 * Removes all the agendas where isPrincipal = &#63; and isActive = &#63; from the database.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 */
	public void removeByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive);

	/**
	 * Returns the number of agendas where isPrincipal = &#63; and isActive = &#63;.
	 *
	 * @param isPrincipal the is principal
	 * @param isActive the is active
	 * @return the number of matching agendas
	 */
	public int countByIsPrincipalAndIsActive(
		Boolean isPrincipal, Boolean isActive);

	/**
	 * Caches the agenda in the entity cache if it is enabled.
	 *
	 * @param agenda the agenda
	 */
	public void cacheResult(Agenda agenda);

	/**
	 * Caches the agendas in the entity cache if it is enabled.
	 *
	 * @param agendas the agendas
	 */
	public void cacheResult(java.util.List<Agenda> agendas);

	/**
	 * Creates a new agenda with the primary key. Does not add the agenda to the database.
	 *
	 * @param agendaId the primary key for the new agenda
	 * @return the new agenda
	 */
	public Agenda create(long agendaId);

	/**
	 * Removes the agenda with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param agendaId the primary key of the agenda
	 * @return the agenda that was removed
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public Agenda remove(long agendaId) throws NoSuchAgendaException;

	public Agenda updateImpl(Agenda agenda);

	/**
	 * Returns the agenda with the primary key or throws a <code>NoSuchAgendaException</code> if it could not be found.
	 *
	 * @param agendaId the primary key of the agenda
	 * @return the agenda
	 * @throws NoSuchAgendaException if a agenda with the primary key could not be found
	 */
	public Agenda findByPrimaryKey(long agendaId) throws NoSuchAgendaException;

	/**
	 * Returns the agenda with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param agendaId the primary key of the agenda
	 * @return the agenda, or <code>null</code> if a agenda with the primary key could not be found
	 */
	public Agenda fetchByPrimaryKey(long agendaId);

	/**
	 * Returns all the agendas.
	 *
	 * @return the agendas
	 */
	public java.util.List<Agenda> findAll();

	/**
	 * Returns a range of all the agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @return the range of agendas
	 */
	public java.util.List<Agenda> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of agendas
	 */
	public java.util.List<Agenda> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator);

	/**
	 * Returns an ordered range of all the agendas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AgendaModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of agendas
	 * @param end the upper bound of the range of agendas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of agendas
	 */
	public java.util.List<Agenda> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agenda>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the agendas from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of agendas.
	 *
	 * @return the number of agendas
	 */
	public int countAll();

}