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

package eu.strasbourg.service.favorite.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.base.FavoriteLocalServiceBaseImpl;

/**
 * The implementation of the favorite local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.favorite.service.FavoriteLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see FavoriteLocalServiceBaseImpl
 * @see eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil
 */
public class FavoriteLocalServiceImpl extends FavoriteLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil} to
	 * access the favorite local service.
	 */

	/**
	 * Crée un nouveau favoris
	 */
	@Override
	public Favorite createFavorite() {
		long pk = this.counterLocalService.increment();
		return this.favoriteLocalService.createFavorite(pk);
	}
	
	/**
	 * Retourne la liste des favoris d'un utilisateur
	 */
	@Override
	public List<Favorite> getByPublikUser(String publikUserId) {
		return this.favoritePersistence.findByPublikUserId(publikUserId);
	}
	
	public void deleteFavoriteByEntityIdAndType(long entityId, long typeId) {
		List<Favorite> favorites = this.favoritePersistence.findByEntityIdAndTypeId(entityId, typeId);
		
		for (Favorite favorite : favorites) {
			this.favoritePersistence.remove(favorite);
		}
	}

	
	/**
	 * Retourne le type du favori de l'élément
	 */
	@Override
	public Long getFavoriteTypeByClass(String favoriteClass) {
		Long type = null;
		List<FavoriteType> allFavoriteType = FavoriteType.getAll();
		List<FavoriteType> favoritesType = allFavoriteType.stream().filter(f -> f.getFavoriteClass().getName().equals(favoriteClass)).collect(Collectors.toList());
		if (!favoritesType.isEmpty()) {
			FavoriteType favoriteType = favoritesType.get(0);
			type = favoriteType.getId();
		}
		return type;
	}
}