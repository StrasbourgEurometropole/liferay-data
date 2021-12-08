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

package eu.strasbourg.service.csmap.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link CacheAgendaJson}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CacheAgendaJson
 * @generated
 */
@ProviderType
public class CacheAgendaJsonWrapper
	extends BaseModelWrapper<CacheAgendaJson>
	implements CacheAgendaJson, ModelWrapper<CacheAgendaJson> {

	public CacheAgendaJsonWrapper(CacheAgendaJson cacheAgendaJson) {
		super(cacheAgendaJson);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cacheId", getCacheId());
		attributes.put("json", getJson());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long cacheId = (Long)attributes.get("cacheId");

		if (cacheId != null) {
			setCacheId(cacheId);
		}

		String json = (String)attributes.get("json");

		if (json != null) {
			setJson(json);
		}
	}

	/**
	 * Returns the cache ID of this cache agenda json.
	 *
	 * @return the cache ID of this cache agenda json
	 */
	@Override
	public long getCacheId() {
		return model.getCacheId();
	}

	/**
	 * Returns the json of this cache agenda json.
	 *
	 * @return the json of this cache agenda json
	 */
	@Override
	public String getJson() {
		return model.getJson();
	}

	/**
	 * Returns the primary key of this cache agenda json.
	 *
	 * @return the primary key of this cache agenda json
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the cache ID of this cache agenda json.
	 *
	 * @param cacheId the cache ID of this cache agenda json
	 */
	@Override
	public void setCacheId(long cacheId) {
		model.setCacheId(cacheId);
	}

	/**
	 * Sets the json of this cache agenda json.
	 *
	 * @param json the json of this cache agenda json
	 */
	@Override
	public void setJson(String json) {
		model.setJson(json);
	}

	/**
	 * Sets the primary key of this cache agenda json.
	 *
	 * @param primaryKey the primary key of this cache agenda json
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected CacheAgendaJsonWrapper wrap(CacheAgendaJson cacheAgendaJson) {
		return new CacheAgendaJsonWrapper(cacheAgendaJson);
	}

}