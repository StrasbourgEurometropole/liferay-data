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

package eu.strasbourg.service.place.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.base.SubPlaceLocalServiceBaseImpl;

/**
 * The implementation of the sub place local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.place.service.SubPlaceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SubPlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.SubPlaceLocalServiceUtil
 */
@ProviderType
public class SubPlaceLocalServiceImpl extends SubPlaceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.place.service.SubPlaceLocalServiceUtil} to access
	 * the sub place local service.
	 */

	/**
	 * Crée un sous-lieu vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public SubPlace createSubPlace(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();

		SubPlace subPlace = this.subPlaceLocalService.createSubPlace(pk);
		subPlace.setStatus(WorkflowConstants.STATUS_DRAFT);

		return this.subPlaceLocalService.createSubPlace(pk);
	}

	/**
	 * Met à jour un sous-lieu et l'enregistre en base de données
	 */
	@Override
	public SubPlace updateSubPlace(SubPlace subPlace, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		subPlace.setStatusByUserId(sc.getUserId());
		subPlace.setStatusByUserName(user.getFullName());
		subPlace.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			subPlace.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			subPlace.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		subPlace = this.subPlaceLocalService.updateSubPlace(subPlace);

		return subPlace;
	}

	/**
	 * Met à jour le statut du sous-lieu par le framework workflow
	 */
	@Override
	public SubPlace updateStatus(long userId, long entryId, int status,
			ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		// Statut de l'entité
		SubPlace subPlace = this.getSubPlace(entryId);
		subPlace.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			subPlace.setStatusByUserId(user.getUserId());
			subPlace.setStatusByUserName(user.getFullName());
		}
		subPlace.setStatusDate(new Date());
		subPlace = this.subPlaceLocalService.updateSubPlace(subPlace);

		return subPlace;
	}

	/**
	 * Met à jour le statut du sous-lieu "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(long userId, SubPlace subPlace, int status)
			throws PortalException {
		this.updateStatus(userId, subPlace.getSubPlaceId(), status, null, null);
	}

	/**
	 * Supprime un sous-lieu
	 */
	@Override
	public SubPlace removeSubPlace(long subPlaceId) throws PortalException {

		// Supprime le sous-lieu
		SubPlace subPlace = subPlacePersistence.remove(subPlaceId);

		// Supprime les exceptions liées au sous-lieu
		List<ScheduleException> exceptions = subPlace.getScheduleExceptions();
		for (ScheduleException exception : exceptions) {
			this.scheduleExceptionLocalService
					.deleteScheduleException(exception.getExceptionId());
		}

		// Supprime les slots
		List<Period> periods = subPlace.getPeriods();
		for (Period period : periods) {
			List<Slot> slots = period.getSlots(subPlaceId);
			for (Slot slot : slots) {
				this.slotLocalService.deleteSlot(slot.getSlotId());
			}
		}

		return subPlace;
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<SubPlace> findByKeyword(String keyword, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}

		return subPlacePersistence.findWithDynamicQuery(dynamicQuery, start,
				end);
	}

	/**
	 * Compte de la recherche par mots-clés
	 */
	@Override
	public long findByKeywordCount(String keyword) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}

		return subPlacePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Retourne les SubPlace rattachés à un lieu
	 */
	@Override
	public List<SubPlace> getByPlaceId(long placeId) {
		return this.subPlacePersistence.findByPlaceId(placeId);
	}
}