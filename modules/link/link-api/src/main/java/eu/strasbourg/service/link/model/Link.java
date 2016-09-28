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

package eu.strasbourg.service.link.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Link service. Represents a row in the &quot;link_Link&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see LinkModel
 * @see eu.strasbourg.service.link.model.impl.LinkImpl
 * @see eu.strasbourg.service.link.model.impl.LinkModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.link.model.impl.LinkImpl")
@ProviderType
public interface Link extends LinkModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.link.model.impl.LinkImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Link, Long> LINK_ID_ACCESSOR = new Accessor<Link, Long>() {
			@Override
			public Long get(Link link) {
				return link.getLinkId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Link> getTypeClass() {
				return Link.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Renvoie la version live du lien, si elle existe
	*/
	public eu.strasbourg.service.link.model.Link getLiveVersion();
}