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

package eu.strasbourg.service.search.log.model;

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
 * This class is a wrapper for {@link SearchLog}.
 * </p>
 *
 * @author BenjaminBini
 * @see SearchLog
 * @generated
 */
@ProviderType
public class SearchLogWrapper implements SearchLog, ModelWrapper<SearchLog> {
	public SearchLogWrapper(SearchLog searchLog) {
		_searchLog = searchLog;
	}

	@Override
	public Class<?> getModelClass() {
		return SearchLog.class;
	}

	@Override
	public String getModelClassName() {
		return SearchLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("searchLogId", getSearchLogId());
		attributes.put("keywords", getKeywords());
		attributes.put("searchTime", getSearchTime());
		attributes.put("resultCount", getResultCount());
		attributes.put("result1ClassId", getResult1ClassId());
		attributes.put("result1ClassPK", getResult1ClassPK());
		attributes.put("result1Title", getResult1Title());
		attributes.put("result2ClassId", getResult2ClassId());
		attributes.put("result2ClassPK", getResult2ClassPK());
		attributes.put("result2Title", getResult2Title());
		attributes.put("result3ClassId", getResult3ClassId());
		attributes.put("result3ClassPK", getResult3ClassPK());
		attributes.put("result3Title", getResult3Title());
		attributes.put("userTargetClassId", getUserTargetClassId());
		attributes.put("userTargetClassPK", getUserTargetClassPK());
		attributes.put("userTargetTitle", getUserTargetTitle());
		attributes.put("groupId", getGroupId());
		attributes.put("layoutId", getLayoutId());
		attributes.put("layoutFriendlyURL", getLayoutFriendlyURL());
		attributes.put("language", getLanguage());
		attributes.put("date", getDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long searchLogId = (Long)attributes.get("searchLogId");

		if (searchLogId != null) {
			setSearchLogId(searchLogId);
		}

		String keywords = (String)attributes.get("keywords");

		if (keywords != null) {
			setKeywords(keywords);
		}

		Long searchTime = (Long)attributes.get("searchTime");

		if (searchTime != null) {
			setSearchTime(searchTime);
		}

		Long resultCount = (Long)attributes.get("resultCount");

		if (resultCount != null) {
			setResultCount(resultCount);
		}

		Long result1ClassId = (Long)attributes.get("result1ClassId");

		if (result1ClassId != null) {
			setResult1ClassId(result1ClassId);
		}

		Long result1ClassPK = (Long)attributes.get("result1ClassPK");

		if (result1ClassPK != null) {
			setResult1ClassPK(result1ClassPK);
		}

		String result1Title = (String)attributes.get("result1Title");

		if (result1Title != null) {
			setResult1Title(result1Title);
		}

		Long result2ClassId = (Long)attributes.get("result2ClassId");

		if (result2ClassId != null) {
			setResult2ClassId(result2ClassId);
		}

		Long result2ClassPK = (Long)attributes.get("result2ClassPK");

		if (result2ClassPK != null) {
			setResult2ClassPK(result2ClassPK);
		}

		String result2Title = (String)attributes.get("result2Title");

		if (result2Title != null) {
			setResult2Title(result2Title);
		}

		Long result3ClassId = (Long)attributes.get("result3ClassId");

		if (result3ClassId != null) {
			setResult3ClassId(result3ClassId);
		}

		Long result3ClassPK = (Long)attributes.get("result3ClassPK");

		if (result3ClassPK != null) {
			setResult3ClassPK(result3ClassPK);
		}

		String result3Title = (String)attributes.get("result3Title");

		if (result3Title != null) {
			setResult3Title(result3Title);
		}

		Long userTargetClassId = (Long)attributes.get("userTargetClassId");

		if (userTargetClassId != null) {
			setUserTargetClassId(userTargetClassId);
		}

		Long userTargetClassPK = (Long)attributes.get("userTargetClassPK");

		if (userTargetClassPK != null) {
			setUserTargetClassPK(userTargetClassPK);
		}

		String userTargetTitle = (String)attributes.get("userTargetTitle");

		if (userTargetTitle != null) {
			setUserTargetTitle(userTargetTitle);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long layoutId = (Long)attributes.get("layoutId");

		if (layoutId != null) {
			setLayoutId(layoutId);
		}

		String layoutFriendlyURL = (String)attributes.get("layoutFriendlyURL");

		if (layoutFriendlyURL != null) {
			setLayoutFriendlyURL(layoutFriendlyURL);
		}

		String language = (String)attributes.get("language");

		if (language != null) {
			setLanguage(language);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _searchLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _searchLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _searchLog.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _searchLog.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.search.log.model.SearchLog> toCacheModel() {
		return _searchLog.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.search.log.model.SearchLog toEscapedModel() {
		return new SearchLogWrapper(_searchLog.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.search.log.model.SearchLog toUnescapedModel() {
		return new SearchLogWrapper(_searchLog.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.search.log.model.SearchLog searchLog) {
		return _searchLog.compareTo(searchLog);
	}

	@Override
	public int hashCode() {
		return _searchLog.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _searchLog.getPrimaryKeyObj();
	}

	/**
	* Returns the layout ID of this search log.
	*
	* @return the layout ID of this search log
	*/
	@Override
	public java.lang.Long getLayoutId() {
		return _searchLog.getLayoutId();
	}

	@Override
	public java.lang.Object clone() {
		return new SearchLogWrapper((SearchLog)_searchLog.clone());
	}

	/**
	* Returns the keywords of this search log.
	*
	* @return the keywords of this search log
	*/
	@Override
	public java.lang.String getKeywords() {
		return _searchLog.getKeywords();
	}

	/**
	* Returns the language of this search log.
	*
	* @return the language of this search log
	*/
	@Override
	public java.lang.String getLanguage() {
		return _searchLog.getLanguage();
	}

	/**
	* Returns the layout friendly url of this search log.
	*
	* @return the layout friendly url of this search log
	*/
	@Override
	public java.lang.String getLayoutFriendlyURL() {
		return _searchLog.getLayoutFriendlyURL();
	}

	/**
	* Returns the result1 title of this search log.
	*
	* @return the result1 title of this search log
	*/
	@Override
	public java.lang.String getResult1Title() {
		return _searchLog.getResult1Title();
	}

	/**
	* Returns the result2 title of this search log.
	*
	* @return the result2 title of this search log
	*/
	@Override
	public java.lang.String getResult2Title() {
		return _searchLog.getResult2Title();
	}

	/**
	* Returns the result3 title of this search log.
	*
	* @return the result3 title of this search log
	*/
	@Override
	public java.lang.String getResult3Title() {
		return _searchLog.getResult3Title();
	}

	/**
	* Returns the user target title of this search log.
	*
	* @return the user target title of this search log
	*/
	@Override
	public java.lang.String getUserTargetTitle() {
		return _searchLog.getUserTargetTitle();
	}

	@Override
	public java.lang.String toString() {
		return _searchLog.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _searchLog.toXmlString();
	}

	/**
	* Returns the date of this search log.
	*
	* @return the date of this search log
	*/
	@Override
	public Date getDate() {
		return _searchLog.getDate();
	}

	/**
	* Returns the group ID of this search log.
	*
	* @return the group ID of this search log
	*/
	@Override
	public long getGroupId() {
		return _searchLog.getGroupId();
	}

	/**
	* Returns the primary key of this search log.
	*
	* @return the primary key of this search log
	*/
	@Override
	public long getPrimaryKey() {
		return _searchLog.getPrimaryKey();
	}

	/**
	* Returns the result1 class ID of this search log.
	*
	* @return the result1 class ID of this search log
	*/
	@Override
	public long getResult1ClassId() {
		return _searchLog.getResult1ClassId();
	}

	/**
	* Returns the result1 class pk of this search log.
	*
	* @return the result1 class pk of this search log
	*/
	@Override
	public long getResult1ClassPK() {
		return _searchLog.getResult1ClassPK();
	}

	/**
	* Returns the result2 class ID of this search log.
	*
	* @return the result2 class ID of this search log
	*/
	@Override
	public long getResult2ClassId() {
		return _searchLog.getResult2ClassId();
	}

	/**
	* Returns the result2 class pk of this search log.
	*
	* @return the result2 class pk of this search log
	*/
	@Override
	public long getResult2ClassPK() {
		return _searchLog.getResult2ClassPK();
	}

	/**
	* Returns the result3 class ID of this search log.
	*
	* @return the result3 class ID of this search log
	*/
	@Override
	public long getResult3ClassId() {
		return _searchLog.getResult3ClassId();
	}

	/**
	* Returns the result3 class pk of this search log.
	*
	* @return the result3 class pk of this search log
	*/
	@Override
	public long getResult3ClassPK() {
		return _searchLog.getResult3ClassPK();
	}

	/**
	* Returns the result count of this search log.
	*
	* @return the result count of this search log
	*/
	@Override
	public long getResultCount() {
		return _searchLog.getResultCount();
	}

	/**
	* Returns the search log ID of this search log.
	*
	* @return the search log ID of this search log
	*/
	@Override
	public long getSearchLogId() {
		return _searchLog.getSearchLogId();
	}

	/**
	* Returns the search time of this search log.
	*
	* @return the search time of this search log
	*/
	@Override
	public long getSearchTime() {
		return _searchLog.getSearchTime();
	}

	/**
	* Returns the user target class ID of this search log.
	*
	* @return the user target class ID of this search log
	*/
	@Override
	public long getUserTargetClassId() {
		return _searchLog.getUserTargetClassId();
	}

	/**
	* Returns the user target class pk of this search log.
	*
	* @return the user target class pk of this search log
	*/
	@Override
	public long getUserTargetClassPK() {
		return _searchLog.getUserTargetClassPK();
	}

	@Override
	public void persist() {
		_searchLog.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_searchLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the date of this search log.
	*
	* @param date the date of this search log
	*/
	@Override
	public void setDate(Date date) {
		_searchLog.setDate(date);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_searchLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_searchLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_searchLog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this search log.
	*
	* @param groupId the group ID of this search log
	*/
	@Override
	public void setGroupId(long groupId) {
		_searchLog.setGroupId(groupId);
	}

	/**
	* Sets the keywords of this search log.
	*
	* @param keywords the keywords of this search log
	*/
	@Override
	public void setKeywords(java.lang.String keywords) {
		_searchLog.setKeywords(keywords);
	}

	/**
	* Sets the language of this search log.
	*
	* @param language the language of this search log
	*/
	@Override
	public void setLanguage(java.lang.String language) {
		_searchLog.setLanguage(language);
	}

	/**
	* Sets the layout friendly url of this search log.
	*
	* @param layoutFriendlyURL the layout friendly url of this search log
	*/
	@Override
	public void setLayoutFriendlyURL(java.lang.String layoutFriendlyURL) {
		_searchLog.setLayoutFriendlyURL(layoutFriendlyURL);
	}

	/**
	* Sets the layout ID of this search log.
	*
	* @param layoutId the layout ID of this search log
	*/
	@Override
	public void setLayoutId(java.lang.Long layoutId) {
		_searchLog.setLayoutId(layoutId);
	}

	@Override
	public void setNew(boolean n) {
		_searchLog.setNew(n);
	}

	/**
	* Sets the primary key of this search log.
	*
	* @param primaryKey the primary key of this search log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_searchLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_searchLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the result1 class ID of this search log.
	*
	* @param result1ClassId the result1 class ID of this search log
	*/
	@Override
	public void setResult1ClassId(long result1ClassId) {
		_searchLog.setResult1ClassId(result1ClassId);
	}

	/**
	* Sets the result1 class pk of this search log.
	*
	* @param result1ClassPK the result1 class pk of this search log
	*/
	@Override
	public void setResult1ClassPK(long result1ClassPK) {
		_searchLog.setResult1ClassPK(result1ClassPK);
	}

	/**
	* Sets the result1 title of this search log.
	*
	* @param result1Title the result1 title of this search log
	*/
	@Override
	public void setResult1Title(java.lang.String result1Title) {
		_searchLog.setResult1Title(result1Title);
	}

	/**
	* Sets the result2 class ID of this search log.
	*
	* @param result2ClassId the result2 class ID of this search log
	*/
	@Override
	public void setResult2ClassId(long result2ClassId) {
		_searchLog.setResult2ClassId(result2ClassId);
	}

	/**
	* Sets the result2 class pk of this search log.
	*
	* @param result2ClassPK the result2 class pk of this search log
	*/
	@Override
	public void setResult2ClassPK(long result2ClassPK) {
		_searchLog.setResult2ClassPK(result2ClassPK);
	}

	/**
	* Sets the result2 title of this search log.
	*
	* @param result2Title the result2 title of this search log
	*/
	@Override
	public void setResult2Title(java.lang.String result2Title) {
		_searchLog.setResult2Title(result2Title);
	}

	/**
	* Sets the result3 class ID of this search log.
	*
	* @param result3ClassId the result3 class ID of this search log
	*/
	@Override
	public void setResult3ClassId(long result3ClassId) {
		_searchLog.setResult3ClassId(result3ClassId);
	}

	/**
	* Sets the result3 class pk of this search log.
	*
	* @param result3ClassPK the result3 class pk of this search log
	*/
	@Override
	public void setResult3ClassPK(long result3ClassPK) {
		_searchLog.setResult3ClassPK(result3ClassPK);
	}

	/**
	* Sets the result3 title of this search log.
	*
	* @param result3Title the result3 title of this search log
	*/
	@Override
	public void setResult3Title(java.lang.String result3Title) {
		_searchLog.setResult3Title(result3Title);
	}

	/**
	* Sets the result count of this search log.
	*
	* @param resultCount the result count of this search log
	*/
	@Override
	public void setResultCount(long resultCount) {
		_searchLog.setResultCount(resultCount);
	}

	/**
	* Sets the search log ID of this search log.
	*
	* @param searchLogId the search log ID of this search log
	*/
	@Override
	public void setSearchLogId(long searchLogId) {
		_searchLog.setSearchLogId(searchLogId);
	}

	/**
	* Sets the search time of this search log.
	*
	* @param searchTime the search time of this search log
	*/
	@Override
	public void setSearchTime(long searchTime) {
		_searchLog.setSearchTime(searchTime);
	}

	/**
	* Sets the user target class ID of this search log.
	*
	* @param userTargetClassId the user target class ID of this search log
	*/
	@Override
	public void setUserTargetClassId(long userTargetClassId) {
		_searchLog.setUserTargetClassId(userTargetClassId);
	}

	/**
	* Sets the user target class pk of this search log.
	*
	* @param userTargetClassPK the user target class pk of this search log
	*/
	@Override
	public void setUserTargetClassPK(long userTargetClassPK) {
		_searchLog.setUserTargetClassPK(userTargetClassPK);
	}

	/**
	* Sets the user target title of this search log.
	*
	* @param userTargetTitle the user target title of this search log
	*/
	@Override
	public void setUserTargetTitle(java.lang.String userTargetTitle) {
		_searchLog.setUserTargetTitle(userTargetTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchLogWrapper)) {
			return false;
		}

		SearchLogWrapper searchLogWrapper = (SearchLogWrapper)obj;

		if (Objects.equals(_searchLog, searchLogWrapper._searchLog)) {
			return true;
		}

		return false;
	}

	@Override
	public SearchLog getWrappedModel() {
		return _searchLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _searchLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _searchLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_searchLog.resetOriginalValues();
	}

	private final SearchLog _searchLog;
}