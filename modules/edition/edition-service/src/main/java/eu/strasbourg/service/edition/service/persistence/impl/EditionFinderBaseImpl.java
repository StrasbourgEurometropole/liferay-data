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

package eu.strasbourg.service.edition.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ReflectionUtil;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.service.persistence.EditionPersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author BenjaminBini
 * @generated
 */
public class EditionFinderBaseImpl extends BasePersistenceImpl<Edition> {
	public EditionFinderBaseImpl() {
		setModelClass(Edition.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getEditionPersistence().getBadColumnNames();
	}

	/**
	 * Returns the edition persistence.
	 *
	 * @return the edition persistence
	 */
	public EditionPersistence getEditionPersistence() {
		return editionPersistence;
	}

	/**
	 * Sets the edition persistence.
	 *
	 * @param editionPersistence the edition persistence
	 */
	public void setEditionPersistence(EditionPersistence editionPersistence) {
		this.editionPersistence = editionPersistence;
	}

	@BeanReference(type = EditionPersistence.class)
	protected EditionPersistence editionPersistence;
	private static final Log _log = LogFactoryUtil.getLog(EditionFinderBaseImpl.class);
}