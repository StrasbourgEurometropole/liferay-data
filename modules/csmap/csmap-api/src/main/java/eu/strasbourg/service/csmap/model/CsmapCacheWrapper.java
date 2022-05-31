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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CsmapCache}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CsmapCache
 * @generated
 */
public class CsmapCacheWrapper
	extends BaseModelWrapper<CsmapCache>
	implements CsmapCache, ModelWrapper<CsmapCache> {

	public CsmapCacheWrapper(CsmapCache csmapCache) {
		super(csmapCache);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("cacheId", getCacheId());
		attributes.put("codeCache", getCodeCache());
		attributes.put("description", getDescription());
		attributes.put("cacheJson", getCacheJson());
		attributes.put("isLastProcessSuccess", getIsLastProcessSuccess());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("processedDate", getProcessedDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long cacheId = (Long)attributes.get("cacheId");

		if (cacheId != null) {
			setCacheId(cacheId);
		}

		Long codeCache = (Long)attributes.get("codeCache");

		if (codeCache != null) {
			setCodeCache(codeCache);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String cacheJson = (String)attributes.get("cacheJson");

		if (cacheJson != null) {
			setCacheJson(cacheJson);
		}

		Boolean isLastProcessSuccess = (Boolean)attributes.get(
			"isLastProcessSuccess");

		if (isLastProcessSuccess != null) {
			setIsLastProcessSuccess(isLastProcessSuccess);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date processedDate = (Date)attributes.get("processedDate");

		if (processedDate != null) {
			setProcessedDate(processedDate);
		}
	}

	/**
	 * Returns the cache ID of this csmap cache.
	 *
	 * @return the cache ID of this csmap cache
	 */
	@Override
	public long getCacheId() {
		return model.getCacheId();
	}

	/**
	 * Returns the cache json of this csmap cache.
	 *
	 * @return the cache json of this csmap cache
	 */
	@Override
	public String getCacheJson() {
		return model.getCacheJson();
	}

	/**
	 * Returns the code cache of this csmap cache.
	 *
	 * @return the code cache of this csmap cache
	 */
	@Override
	public long getCodeCache() {
		return model.getCodeCache();
	}

	/**
	 * Returns the description of this csmap cache.
	 *
	 * @return the description of this csmap cache
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the is last process success of this csmap cache.
	 *
	 * @return the is last process success of this csmap cache
	 */
	@Override
	public Boolean getIsLastProcessSuccess() {
		return model.getIsLastProcessSuccess();
	}

	/**
	 * Returns the modified date of this csmap cache.
	 *
	 * @return the modified date of this csmap cache
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this csmap cache.
	 *
	 * @return the primary key of this csmap cache
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the processed date of this csmap cache.
	 *
	 * @return the processed date of this csmap cache
	 */
	@Override
	public Date getProcessedDate() {
		return model.getProcessedDate();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the cache ID of this csmap cache.
	 *
	 * @param cacheId the cache ID of this csmap cache
	 */
	@Override
	public void setCacheId(long cacheId) {
		model.setCacheId(cacheId);
	}

	/**
	 * Sets the cache json of this csmap cache.
	 *
	 * @param cacheJson the cache json of this csmap cache
	 */
	@Override
	public void setCacheJson(String cacheJson) {
		model.setCacheJson(cacheJson);
	}

	/**
	 * Sets the code cache of this csmap cache.
	 *
	 * @param codeCache the code cache of this csmap cache
	 */
	@Override
	public void setCodeCache(long codeCache) {
		model.setCodeCache(codeCache);
	}

	/**
	 * Sets the description of this csmap cache.
	 *
	 * @param description the description of this csmap cache
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the is last process success of this csmap cache.
	 *
	 * @param isLastProcessSuccess the is last process success of this csmap cache
	 */
	@Override
	public void setIsLastProcessSuccess(Boolean isLastProcessSuccess) {
		model.setIsLastProcessSuccess(isLastProcessSuccess);
	}

	/**
	 * Sets the modified date of this csmap cache.
	 *
	 * @param modifiedDate the modified date of this csmap cache
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this csmap cache.
	 *
	 * @param primaryKey the primary key of this csmap cache
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the processed date of this csmap cache.
	 *
	 * @param processedDate the processed date of this csmap cache
	 */
	@Override
	public void setProcessedDate(Date processedDate) {
		model.setProcessedDate(processedDate);
	}

	@Override
	protected CsmapCacheWrapper wrap(CsmapCache csmapCache) {
		return new CsmapCacheWrapper(csmapCache);
	}

}