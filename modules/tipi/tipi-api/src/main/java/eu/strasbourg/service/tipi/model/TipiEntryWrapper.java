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

package eu.strasbourg.service.tipi.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link TipiEntry}.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see TipiEntry
 * @generated
 */
@ProviderType
public class TipiEntryWrapper implements TipiEntry, ModelWrapper<TipiEntry> {
	public TipiEntryWrapper(TipiEntry tipiEntry) {
		_tipiEntry = tipiEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return TipiEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TipiEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("id", getId());
		attributes.put("date", getDate());
		attributes.put("total", getTotal());
		attributes.put("paidCount", getPaidCount());
		attributes.put("refusedCount", getRefusedCount());
		attributes.put("canceledCount", getCanceledCount());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		Integer total = (Integer)attributes.get("total");

		if (total != null) {
			setTotal(total);
		}

		Integer paidCount = (Integer)attributes.get("paidCount");

		if (paidCount != null) {
			setPaidCount(paidCount);
		}

		Integer refusedCount = (Integer)attributes.get("refusedCount");

		if (refusedCount != null) {
			setRefusedCount(refusedCount);
		}

		Integer canceledCount = (Integer)attributes.get("canceledCount");

		if (canceledCount != null) {
			setCanceledCount(canceledCount);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _tipiEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _tipiEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _tipiEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _tipiEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.tipi.model.TipiEntry> toCacheModel() {
		return _tipiEntry.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.tipi.model.TipiEntry toEscapedModel() {
		return new TipiEntryWrapper(_tipiEntry.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.tipi.model.TipiEntry toUnescapedModel() {
		return new TipiEntryWrapper(_tipiEntry.toUnescapedModel());
	}

	@Override
	public int compareTo(eu.strasbourg.service.tipi.model.TipiEntry tipiEntry) {
		return _tipiEntry.compareTo(tipiEntry);
	}

	/**
	* Returns the canceled count of this tipi entry.
	*
	* @return the canceled count of this tipi entry
	*/
	@Override
	public int getCanceledCount() {
		return _tipiEntry.getCanceledCount();
	}

	/**
	* Returns the paid count of this tipi entry.
	*
	* @return the paid count of this tipi entry
	*/
	@Override
	public int getPaidCount() {
		return _tipiEntry.getPaidCount();
	}

	/**
	* Returns the refused count of this tipi entry.
	*
	* @return the refused count of this tipi entry
	*/
	@Override
	public int getRefusedCount() {
		return _tipiEntry.getRefusedCount();
	}

	/**
	* Returns the total of this tipi entry.
	*
	* @return the total of this tipi entry
	*/
	@Override
	public int getTotal() {
		return _tipiEntry.getTotal();
	}

	@Override
	public int hashCode() {
		return _tipiEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _tipiEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new TipiEntryWrapper((TipiEntry)_tipiEntry.clone());
	}

	/**
	* Returns the type of this tipi entry.
	*
	* @return the type of this tipi entry
	*/
	@Override
	public java.lang.String getType() {
		return _tipiEntry.getType();
	}

	/**
	* Returns the uuid of this tipi entry.
	*
	* @return the uuid of this tipi entry
	*/
	@Override
	public java.lang.String getUuid() {
		return _tipiEntry.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _tipiEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _tipiEntry.toXmlString();
	}

	/**
	* Returns the date of this tipi entry.
	*
	* @return the date of this tipi entry
	*/
	@Override
	public Date getDate() {
		return _tipiEntry.getDate();
	}

	/**
	* Returns the ID of this tipi entry.
	*
	* @return the ID of this tipi entry
	*/
	@Override
	public long getId() {
		return _tipiEntry.getId();
	}

	/**
	* Returns the primary key of this tipi entry.
	*
	* @return the primary key of this tipi entry
	*/
	@Override
	public long getPrimaryKey() {
		return _tipiEntry.getPrimaryKey();
	}

	@Override
	public void persist() {
		_tipiEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_tipiEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the canceled count of this tipi entry.
	*
	* @param canceledCount the canceled count of this tipi entry
	*/
	@Override
	public void setCanceledCount(int canceledCount) {
		_tipiEntry.setCanceledCount(canceledCount);
	}

	/**
	* Sets the date of this tipi entry.
	*
	* @param date the date of this tipi entry
	*/
	@Override
	public void setDate(Date date) {
		_tipiEntry.setDate(date);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_tipiEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_tipiEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_tipiEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the ID of this tipi entry.
	*
	* @param id the ID of this tipi entry
	*/
	@Override
	public void setId(long id) {
		_tipiEntry.setId(id);
	}

	@Override
	public void setNew(boolean n) {
		_tipiEntry.setNew(n);
	}

	/**
	* Sets the paid count of this tipi entry.
	*
	* @param paidCount the paid count of this tipi entry
	*/
	@Override
	public void setPaidCount(int paidCount) {
		_tipiEntry.setPaidCount(paidCount);
	}

	/**
	* Sets the primary key of this tipi entry.
	*
	* @param primaryKey the primary key of this tipi entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_tipiEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_tipiEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the refused count of this tipi entry.
	*
	* @param refusedCount the refused count of this tipi entry
	*/
	@Override
	public void setRefusedCount(int refusedCount) {
		_tipiEntry.setRefusedCount(refusedCount);
	}

	/**
	* Sets the total of this tipi entry.
	*
	* @param total the total of this tipi entry
	*/
	@Override
	public void setTotal(int total) {
		_tipiEntry.setTotal(total);
	}

	/**
	* Sets the type of this tipi entry.
	*
	* @param type the type of this tipi entry
	*/
	@Override
	public void setType(java.lang.String type) {
		_tipiEntry.setType(type);
	}

	/**
	* Sets the uuid of this tipi entry.
	*
	* @param uuid the uuid of this tipi entry
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_tipiEntry.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TipiEntryWrapper)) {
			return false;
		}

		TipiEntryWrapper tipiEntryWrapper = (TipiEntryWrapper)obj;

		if (Objects.equals(_tipiEntry, tipiEntryWrapper._tipiEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public TipiEntry getWrappedModel() {
		return _tipiEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _tipiEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _tipiEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_tipiEntry.resetOriginalValues();
	}

	private final TipiEntry _tipiEntry;
}