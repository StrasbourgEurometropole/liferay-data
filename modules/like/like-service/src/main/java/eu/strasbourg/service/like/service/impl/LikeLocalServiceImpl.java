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

package eu.strasbourg.service.like.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.model.LikeType;
import eu.strasbourg.service.like.service.base.LikeLocalServiceBaseImpl;

/**
 * The implementation of the like local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.like.service.LikeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see LikeLocalServiceBaseImpl
 * @see eu.strasbourg.service.like.service.LikeLocalServiceUtil
 */
public class LikeLocalServiceImpl extends LikeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.like.service.LikeLocalServiceUtil} to access the like local service.
	 */
	
	/**
	 * Crée un nouveau like/dislike
	 */
	@Override
	public Like createLike() {
		long pk = this.counterLocalService.increment();
		return this.likeLocalService.createLike(pk);
	}
	
	/**
	 * Retourne la liste des likes/dislikes d'un utilisateur
	 */
	@Override
	public List<Like> getByPublikUser(String publikUserId) {
		return this.likePersistence.findByPublikUserId(publikUserId);
	}
	
	/**
	 * Supprime les likes/dislike d'une entité
	 */
	@Override
	public void deleteLikeByEntityIdAndType(long entityId, long typeId) {
		List<Like> likes = this.likePersistence.findByEntityIdAndTypeId(entityId, typeId);
		
		for (Like like : likes) {
			this.likePersistence.remove(like);
		}
	}
	
	/**
	 * Supprime les likes/dislike d'une entité selon son type (Like ou Dislike)
	 */
	@Override
	public void deleteLikeByEntityIdAndTypeAndIsDislike(long entityId, long typeId, boolean isDislike) {
		List<Like> likes = this.likePersistence.findByEntityIdAndTypeIdAndIsDislike(entityId, typeId, isDislike);
		
		for (Like like : likes) {
			this.likePersistence.remove(like);
		}
	}
	
	/**
	 * Retourne le type du like/dislike de l'element
	 */
	@Override
	public Long getLikeTypeByClass(String likeClass) {
		Long type = null;
		List<LikeType> allLikeType = LikeType.getAll();
		List<LikeType> likeTypes = allLikeType.stream().filter(f -> f.getLikeClass().getName().equals(likeClass)).collect(Collectors.toList());
		if (!likeTypes.isEmpty()) {
			LikeType likeType = likeTypes.get(0);
			type = likeType.getId();
		}
		return type;
	}
	
	
	
}