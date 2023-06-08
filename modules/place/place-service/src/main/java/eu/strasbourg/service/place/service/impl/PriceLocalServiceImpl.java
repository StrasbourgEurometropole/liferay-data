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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.Price;
import eu.strasbourg.service.place.service.base.PriceLocalServiceBaseImpl;

/**
 * The implementation of the price local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.place.service.PlacePriceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PriceLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.PriceLocalServiceUtil
 */
@ProviderType
public class PriceLocalServiceImpl extends PriceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.place.service.PriceLocalServiceUtil} to access the price local service.
	 */

	/**
	 * Crée un prix vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Price createPrice(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();

		Price price = this.priceLocalService.createPrice(pk);
		price.setStatus(WorkflowConstants.STATUS_DRAFT);
		
		return this.priceLocalService.createPrice(pk);
	}

	/**
	 * Met à jour un tarif et l'enregistre en base de données
	 */
	@Override
	public Price updatePrice(Price price, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		price.setStatusByUserId(sc.getUserId());
		price.setStatusByUserName(user.getFullName());
		price.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			price.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			price.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		price = this.priceLocalService.updatePrice(price);

		return price;
	}

	/**
	 * Met à jour le statut du tarif par le framework workflow
	 */
	@Override
	public Price updateStatus(long userId, long entryId, int status,
			ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
//		Date now = new Date();
		// Statut de l'entité
		Price price = this.getPrice(entryId);
		price.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			price.setStatusByUserId(user.getUserId());
			price.setStatusByUserName(user.getFullName());
		}
		price.setStatusDate(new Date());
		price = this.priceLocalService.updatePrice(price);

//		// Statut de l'entry
//		AssetEntry entry = this.assetEntryLocalService
//				.getEntry(Price.class.getName(), price.getPrimaryKey());
//		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
//		if (entry.isVisible()) {
//			entry.setPublishDate(now);
//		}
//		this.assetEntryLocalService.updateAssetEntry(entry);
//
//		this.reindex(price, false);

		return price;
	}

	/**
	 * Met à jour le statut du tarif "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(long userId, Price price, int status) throws PortalException {
		this.updateStatus(userId, price.getPriceId(), status, null,
				null);
	}

	/**
	 * Supprime un tarif
	 */
	@Override
	public Price removePrice(long priceId) throws PortalException {

		// Supprime le tarif
		Price price = pricePersistence.remove(priceId);

		// Supprime LE LIEN des lieux
		List<Place> places = price.getPlaces();
		for (Place place : places) {
			place.setPriceId(0);
			this.placeLocalService.updatePlace(place);
		}
		
		return price;
	}
}