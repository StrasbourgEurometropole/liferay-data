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

package eu.strasbourg.service.place.model.impl;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;

/**
 * The extended model implementation for the Price service. Represents a row in the &quot;place_Price&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.place.model.Price} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class PriceImpl extends PriceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a price model instance should use the {@link eu.strasbourg.service.place.model.Price} interface instead.
	 */
	public PriceImpl() {
	}
	
	/**
	 *  Retourne les lieux du tarif
	 */
	@Override
	public List<Place> getPlaces(){
		return PlaceLocalServiceUtil.getByPriceId(this.getPriceId());
	}

	/**
	 * Renvoie la liste des IDs des lieux auxquelles ce tarif
	 * appartient sous forme de String
	 */
	@Override
	public String getPlacesIds() {
		List<Place> places = this.getPlaces();
		String ids = "";
		for (Place place : places) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += place.getPlaceId();
		}
		return ids;
	}
}