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

package eu.strasbourg.service.video.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the VideoGallery service. Represents a row in the &quot;video_VideoGallery&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see VideoGalleryModel
 * @see eu.strasbourg.service.video.model.impl.VideoGalleryImpl
 * @see eu.strasbourg.service.video.model.impl.VideoGalleryModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.video.model.impl.VideoGalleryImpl")
@ProviderType
public interface VideoGallery extends VideoGalleryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.video.model.impl.VideoGalleryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<VideoGallery, Long> GALLERY_ID_ACCESSOR = new Accessor<VideoGallery, Long>() {
			@Override
			public Long get(VideoGallery videoGallery) {
				return videoGallery.getGalleryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<VideoGallery> getTypeClass() {
				return VideoGallery.class;
			}
		};

	/**
	* Retourne l'AssetEntry correspondant à cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des AssetCategory correspondant à cet item (via l'AssetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*
	* @throws PortalException
	* @throws NumberFormatException
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne la liste des vidéos de la galerie
	*/
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos();

	/**
	* Retourne la liste des ids des vidéos de la galerie
	*/
	public java.lang.String getVideosIds();

	/**
	* Renvoie la liste des éditions publiées de la galerie
	*/
	public java.util.List<eu.strasbourg.service.video.model.Video> getPublishedVideos();

	/**
	* Renvoie la version live de la galerie, si elle existe
	*/
	public eu.strasbourg.service.video.model.VideoGallery getLiveVersion();
}