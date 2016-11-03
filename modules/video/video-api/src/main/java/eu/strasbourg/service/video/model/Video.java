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
 * The extended model interface for the Video service. Represents a row in the &quot;video_Video&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see VideoModel
 * @see eu.strasbourg.service.video.model.impl.VideoImpl
 * @see eu.strasbourg.service.video.model.impl.VideoModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.video.model.impl.VideoImpl")
@ProviderType
public interface Video extends VideoModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.video.model.impl.VideoImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Video, Long> VIDEO_ID_ACCESSOR = new Accessor<Video, Long>() {
			@Override
			public Long get(Video video) {
				return video.getVideoId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Video> getTypeClass() {
				return Video.class;
			}
		};

	/**
	* Retourne l'AssetEntry correspondant à cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des AssetCategory correspondant à cet item (via
	* l'AssetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne la liste des galeries de la vidéo
	*/
	public java.util.List<eu.strasbourg.service.video.model.VideoGallery> getVideoGalleries();

	/**
	* Retourne la liste des ids des galeries de la vidéo
	*/
	public java.lang.String getVideoGalleriesIds();

	/**
	* Renvoie la liste des galeries vidéos publiées de la
	*/
	public java.util.List<eu.strasbourg.service.video.model.VideoGallery> getPublishedVideoGalleries();

	/**
	* Retourne la version live de la vidéo si elle existe, null sinon
	*/
	public eu.strasbourg.service.video.model.Video getLiveVersion();

	/**
	* Retourne le code html embed du player si le champ "source" est un lien
	* vers une vidéo Dailymotion, YouTube ou Vimeo, retourne le contenu du
	* champ source sinon
	*/
	public java.lang.String getPlayer(java.util.Locale locale);

	/**
	* Retourne l'URL de téléchargement du fichier de transcription
	*/
	public java.lang.String getTranscriptionDownloadURL();
}