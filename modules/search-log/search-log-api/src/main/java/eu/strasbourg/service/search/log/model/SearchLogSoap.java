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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.search.log.service.http.SearchLogServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.search.log.service.http.SearchLogServiceSoap
 * @generated
 */
@ProviderType
public class SearchLogSoap implements Serializable {
	public static SearchLogSoap toSoapModel(SearchLog model) {
		SearchLogSoap soapModel = new SearchLogSoap();

		soapModel.setSearchLogId(model.getSearchLogId());
		soapModel.setKeywords(model.getKeywords());
		soapModel.setSearchTime(model.getSearchTime());
		soapModel.setResultCount(model.getResultCount());
		soapModel.setResult1ClassId(model.getResult1ClassId());
		soapModel.setResult1ClassPK(model.getResult1ClassPK());
		soapModel.setResult1Title(model.getResult1Title());
		soapModel.setResult2ClassId(model.getResult2ClassId());
		soapModel.setResult2ClassPK(model.getResult2ClassPK());
		soapModel.setResult2Title(model.getResult2Title());
		soapModel.setResult3ClassId(model.getResult3ClassId());
		soapModel.setResult3ClassPK(model.getResult3ClassPK());
		soapModel.setResult3Title(model.getResult3Title());
		soapModel.setUserTargetClassId(model.getUserTargetClassId());
		soapModel.setUserTargetClassPK(model.getUserTargetClassPK());
		soapModel.setUserTargetTitle(model.getUserTargetTitle());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setLayoutId(model.getLayoutId());
		soapModel.setLayoutFriendlyURL(model.getLayoutFriendlyURL());
		soapModel.setLanguage(model.getLanguage());
		soapModel.setDate(model.getDate());

		return soapModel;
	}

	public static SearchLogSoap[] toSoapModels(SearchLog[] models) {
		SearchLogSoap[] soapModels = new SearchLogSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SearchLogSoap[][] toSoapModels(SearchLog[][] models) {
		SearchLogSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SearchLogSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SearchLogSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SearchLogSoap[] toSoapModels(List<SearchLog> models) {
		List<SearchLogSoap> soapModels = new ArrayList<SearchLogSoap>(models.size());

		for (SearchLog model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SearchLogSoap[soapModels.size()]);
	}

	public SearchLogSoap() {
	}

	public long getPrimaryKey() {
		return _searchLogId;
	}

	public void setPrimaryKey(long pk) {
		setSearchLogId(pk);
	}

	public long getSearchLogId() {
		return _searchLogId;
	}

	public void setSearchLogId(long searchLogId) {
		_searchLogId = searchLogId;
	}

	public String getKeywords() {
		return _keywords;
	}

	public void setKeywords(String keywords) {
		_keywords = keywords;
	}

	public long getSearchTime() {
		return _searchTime;
	}

	public void setSearchTime(long searchTime) {
		_searchTime = searchTime;
	}

	public long getResultCount() {
		return _resultCount;
	}

	public void setResultCount(long resultCount) {
		_resultCount = resultCount;
	}

	public long getResult1ClassId() {
		return _result1ClassId;
	}

	public void setResult1ClassId(long result1ClassId) {
		_result1ClassId = result1ClassId;
	}

	public long getResult1ClassPK() {
		return _result1ClassPK;
	}

	public void setResult1ClassPK(long result1ClassPK) {
		_result1ClassPK = result1ClassPK;
	}

	public String getResult1Title() {
		return _result1Title;
	}

	public void setResult1Title(String result1Title) {
		_result1Title = result1Title;
	}

	public long getResult2ClassId() {
		return _result2ClassId;
	}

	public void setResult2ClassId(long result2ClassId) {
		_result2ClassId = result2ClassId;
	}

	public long getResult2ClassPK() {
		return _result2ClassPK;
	}

	public void setResult2ClassPK(long result2ClassPK) {
		_result2ClassPK = result2ClassPK;
	}

	public String getResult2Title() {
		return _result2Title;
	}

	public void setResult2Title(String result2Title) {
		_result2Title = result2Title;
	}

	public long getResult3ClassId() {
		return _result3ClassId;
	}

	public void setResult3ClassId(long result3ClassId) {
		_result3ClassId = result3ClassId;
	}

	public long getResult3ClassPK() {
		return _result3ClassPK;
	}

	public void setResult3ClassPK(long result3ClassPK) {
		_result3ClassPK = result3ClassPK;
	}

	public String getResult3Title() {
		return _result3Title;
	}

	public void setResult3Title(String result3Title) {
		_result3Title = result3Title;
	}

	public long getUserTargetClassId() {
		return _userTargetClassId;
	}

	public void setUserTargetClassId(long userTargetClassId) {
		_userTargetClassId = userTargetClassId;
	}

	public long getUserTargetClassPK() {
		return _userTargetClassPK;
	}

	public void setUserTargetClassPK(long userTargetClassPK) {
		_userTargetClassPK = userTargetClassPK;
	}

	public String getUserTargetTitle() {
		return _userTargetTitle;
	}

	public void setUserTargetTitle(String userTargetTitle) {
		_userTargetTitle = userTargetTitle;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public Long getLayoutId() {
		return _layoutId;
	}

	public void setLayoutId(Long layoutId) {
		_layoutId = layoutId;
	}

	public String getLayoutFriendlyURL() {
		return _layoutFriendlyURL;
	}

	public void setLayoutFriendlyURL(String layoutFriendlyURL) {
		_layoutFriendlyURL = layoutFriendlyURL;
	}

	public String getLanguage() {
		return _language;
	}

	public void setLanguage(String language) {
		_language = language;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	private long _searchLogId;
	private String _keywords;
	private long _searchTime;
	private long _resultCount;
	private long _result1ClassId;
	private long _result1ClassPK;
	private String _result1Title;
	private long _result2ClassId;
	private long _result2ClassPK;
	private String _result2Title;
	private long _result3ClassId;
	private long _result3ClassPK;
	private String _result3Title;
	private long _userTargetClassId;
	private long _userTargetClassPK;
	private String _userTargetTitle;
	private long _groupId;
	private Long _layoutId;
	private String _layoutFriendlyURL;
	private String _language;
	private Date _date;
}