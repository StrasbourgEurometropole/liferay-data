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

package eu.strasbourg.service.like.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Like service. Represents a row in the &quot;like_Like&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see LikeModel
 * @see eu.strasbourg.service.like.model.impl.LikeImpl
 * @see eu.strasbourg.service.like.model.impl.LikeModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.like.model.impl.LikeImpl")
@ProviderType
public interface Like extends LikeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.like.model.impl.LikeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Like, Long> LIKE_ID_ACCESSOR = new Accessor<Like, Long>() {
			@Override
			public Long get(Like like) {
				return like.getLikeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Like> getTypeClass() {
				return Like.class;
			}
		};

	public eu.strasbourg.service.like.model.LikeType getLikeType();

	/**
	* Retourne la version JSON d'un like/dislike
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}