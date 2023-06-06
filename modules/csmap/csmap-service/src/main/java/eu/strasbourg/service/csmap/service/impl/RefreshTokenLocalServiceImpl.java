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

package eu.strasbourg.service.csmap.service.impl;

import com.liferay.portal.aop.AopService;

import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.service.csmap.model.RefreshToken;
import eu.strasbourg.service.csmap.service.base.RefreshTokenLocalServiceBaseImpl;

import com.liferay.portal.kernel.service.ServiceContext;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the refresh token local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.csmap.service.RefreshTokenLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RefreshTokenLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.csmap.model.RefreshToken",
	service = AopService.class
)
public class RefreshTokenLocalServiceImpl extends RefreshTokenLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.csmap.service.RefreshTokenLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.csmap.service.RefreshTokenLocalServiceUtil</code>.
	 */

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public RefreshToken createRefreshToken(ServiceContext sc) {
		long pk = counterLocalService.increment();
		return refreshTokenLocalService.createRefreshToken(pk);
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public RefreshToken updateRefreshToken(RefreshToken refreshToken, ServiceContext sc) {
		return refreshTokenLocalService.updateRefreshToken(refreshToken);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public RefreshToken removeRefreshToken(long refreshTokenId) throws NoSuchRefreshTokenException {
		return refreshTokenPersistence.remove(refreshTokenId);
	}

	/**
	 * Retrouve un refresh token par sa valeur
	 */
	@Override
	public RefreshToken fetchByValue(String value) {
		return refreshTokenPersistence.fetchByValue(value);
	}


	/**
	 * Retrouve un refresh token par sa valeur
	 */
	@Override
	public List<RefreshToken> getByPublikId(String publikId) {
		return refreshTokenPersistence.findByPublikId(publikId);
	}

}