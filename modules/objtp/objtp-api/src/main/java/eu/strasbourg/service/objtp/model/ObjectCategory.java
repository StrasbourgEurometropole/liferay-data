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

package eu.strasbourg.service.objtp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ObjectCategory service. Represents a row in the &quot;objtp_ObjectCategory&quot; database table, with each column mapped to a property of this class.
 *
 * @author JeremyZwickert
 * @see ObjectCategoryModel
 * @see eu.strasbourg.service.objtp.model.impl.ObjectCategoryImpl
 * @see eu.strasbourg.service.objtp.model.impl.ObjectCategoryModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.objtp.model.impl.ObjectCategoryImpl")
@ProviderType
public interface ObjectCategory extends ObjectCategoryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.objtp.model.impl.ObjectCategoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ObjectCategory, String> CODE_ACCESSOR = new Accessor<ObjectCategory, String>() {
			@Override
			public String get(ObjectCategory objectCategory) {
				return objectCategory.getCode();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ObjectCategory> getTypeClass() {
				return ObjectCategory.class;
			}
		};
}