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

package eu.strasbourg.service.search.log.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.search.log.model.SearchLog;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SearchLog in entity cache.
 *
 * @author BenjaminBini
 * @see SearchLog
 * @generated
 */
@ProviderType
public class SearchLogCacheModel implements CacheModel<SearchLog>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchLogCacheModel)) {
			return false;
		}

		SearchLogCacheModel searchLogCacheModel = (SearchLogCacheModel)obj;

		if (searchLogId == searchLogCacheModel.searchLogId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, searchLogId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{searchLogId=");
		sb.append(searchLogId);
		sb.append(", keywords=");
		sb.append(keywords);
		sb.append(", searchTime=");
		sb.append(searchTime);
		sb.append(", resultCount=");
		sb.append(resultCount);
		sb.append(", result1ClassId=");
		sb.append(result1ClassId);
		sb.append(", result1ClassPK=");
		sb.append(result1ClassPK);
		sb.append(", result1Title=");
		sb.append(result1Title);
		sb.append(", result2ClassId=");
		sb.append(result2ClassId);
		sb.append(", result2ClassPK=");
		sb.append(result2ClassPK);
		sb.append(", result2Title=");
		sb.append(result2Title);
		sb.append(", result3ClassId=");
		sb.append(result3ClassId);
		sb.append(", result3ClassPK=");
		sb.append(result3ClassPK);
		sb.append(", result3Title=");
		sb.append(result3Title);
		sb.append(", userTargetClassId=");
		sb.append(userTargetClassId);
		sb.append(", userTargetClassPK=");
		sb.append(userTargetClassPK);
		sb.append(", userTargetTitle=");
		sb.append(userTargetTitle);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", layoutId=");
		sb.append(layoutId);
		sb.append(", layoutFriendlyURL=");
		sb.append(layoutFriendlyURL);
		sb.append(", language=");
		sb.append(language);
		sb.append(", date=");
		sb.append(date);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SearchLog toEntityModel() {
		SearchLogImpl searchLogImpl = new SearchLogImpl();

		searchLogImpl.setSearchLogId(searchLogId);

		if (keywords == null) {
			searchLogImpl.setKeywords(StringPool.BLANK);
		}
		else {
			searchLogImpl.setKeywords(keywords);
		}

		searchLogImpl.setSearchTime(searchTime);
		searchLogImpl.setResultCount(resultCount);
		searchLogImpl.setResult1ClassId(result1ClassId);
		searchLogImpl.setResult1ClassPK(result1ClassPK);

		if (result1Title == null) {
			searchLogImpl.setResult1Title(StringPool.BLANK);
		}
		else {
			searchLogImpl.setResult1Title(result1Title);
		}

		searchLogImpl.setResult2ClassId(result2ClassId);
		searchLogImpl.setResult2ClassPK(result2ClassPK);

		if (result2Title == null) {
			searchLogImpl.setResult2Title(StringPool.BLANK);
		}
		else {
			searchLogImpl.setResult2Title(result2Title);
		}

		searchLogImpl.setResult3ClassId(result3ClassId);
		searchLogImpl.setResult3ClassPK(result3ClassPK);

		if (result3Title == null) {
			searchLogImpl.setResult3Title(StringPool.BLANK);
		}
		else {
			searchLogImpl.setResult3Title(result3Title);
		}

		searchLogImpl.setUserTargetClassId(userTargetClassId);
		searchLogImpl.setUserTargetClassPK(userTargetClassPK);

		if (userTargetTitle == null) {
			searchLogImpl.setUserTargetTitle(StringPool.BLANK);
		}
		else {
			searchLogImpl.setUserTargetTitle(userTargetTitle);
		}

		searchLogImpl.setGroupId(groupId);
		searchLogImpl.setLayoutId(layoutId);

		if (layoutFriendlyURL == null) {
			searchLogImpl.setLayoutFriendlyURL(StringPool.BLANK);
		}
		else {
			searchLogImpl.setLayoutFriendlyURL(layoutFriendlyURL);
		}

		if (language == null) {
			searchLogImpl.setLanguage(StringPool.BLANK);
		}
		else {
			searchLogImpl.setLanguage(language);
		}

		if (date == Long.MIN_VALUE) {
			searchLogImpl.setDate(null);
		}
		else {
			searchLogImpl.setDate(new Date(date));
		}

		searchLogImpl.resetOriginalValues();

		return searchLogImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		searchLogId = objectInput.readLong();
		keywords = objectInput.readUTF();

		searchTime = objectInput.readLong();

		resultCount = objectInput.readLong();

		result1ClassId = objectInput.readLong();

		result1ClassPK = objectInput.readLong();
		result1Title = objectInput.readUTF();

		result2ClassId = objectInput.readLong();

		result2ClassPK = objectInput.readLong();
		result2Title = objectInput.readUTF();

		result3ClassId = objectInput.readLong();

		result3ClassPK = objectInput.readLong();
		result3Title = objectInput.readUTF();

		userTargetClassId = objectInput.readLong();

		userTargetClassPK = objectInput.readLong();
		userTargetTitle = objectInput.readUTF();

		groupId = objectInput.readLong();

		layoutId = objectInput.readLong();
		layoutFriendlyURL = objectInput.readUTF();
		language = objectInput.readUTF();
		date = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(searchLogId);

		if (keywords == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(keywords);
		}

		objectOutput.writeLong(searchTime);

		objectOutput.writeLong(resultCount);

		objectOutput.writeLong(result1ClassId);

		objectOutput.writeLong(result1ClassPK);

		if (result1Title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(result1Title);
		}

		objectOutput.writeLong(result2ClassId);

		objectOutput.writeLong(result2ClassPK);

		if (result2Title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(result2Title);
		}

		objectOutput.writeLong(result3ClassId);

		objectOutput.writeLong(result3ClassPK);

		if (result3Title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(result3Title);
		}

		objectOutput.writeLong(userTargetClassId);

		objectOutput.writeLong(userTargetClassPK);

		if (userTargetTitle == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userTargetTitle);
		}

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(layoutId);

		if (layoutFriendlyURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(layoutFriendlyURL);
		}

		if (language == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(language);
		}

		objectOutput.writeLong(date);
	}

	public long searchLogId;
	public String keywords;
	public long searchTime;
	public long resultCount;
	public long result1ClassId;
	public long result1ClassPK;
	public String result1Title;
	public long result2ClassId;
	public long result2ClassPK;
	public String result2Title;
	public long result3ClassId;
	public long result3ClassPK;
	public String result3Title;
	public long userTargetClassId;
	public long userTargetClassPK;
	public String userTargetTitle;
	public long groupId;
	public long layoutId;
	public String layoutFriendlyURL;
	public String language;
	public long date;
}