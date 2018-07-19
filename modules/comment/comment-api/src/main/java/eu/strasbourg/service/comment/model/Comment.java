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

package eu.strasbourg.service.comment.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Comment service. Represents a row in the &quot;comment_Comment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Romain Vergnais
 * @see CommentModel
 * @see eu.strasbourg.service.comment.model.impl.CommentImpl
 * @see eu.strasbourg.service.comment.model.impl.CommentModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.comment.model.impl.CommentImpl")
@ProviderType
public interface Comment extends CommentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.comment.model.impl.CommentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Comment, Long> COMMENT_ID_ACCESSOR = new Accessor<Comment, Long>() {
			@Override
			public Long get(Comment comment) {
				return comment.getCommentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Comment> getTypeClass() {
				return Comment.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	public java.lang.String getTypeAssetEntry();

	public java.lang.String getAssetEntryTitle();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	public eu.strasbourg.service.oidc.model.PublikUser getPublikUser();

	public java.lang.String getPublikUserName();

	/**
	* méthode qui renvoie la liste des signalements d'un commentaire.
	*
	* @return la liste des signalements
	*/
	public java.util.List<eu.strasbourg.service.comment.model.Signalement> findSignalements();

	/**
	* méthode qui renvoie le nombre de signalement pour un commentaire.
	*
	* @return le nombre de signalement en int.
	*/
	public int getCountSignalements();
}