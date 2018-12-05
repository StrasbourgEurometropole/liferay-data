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

package eu.strasbourg.service.project.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.project.model.ProjectTimeline;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProjectTimeline in entity cache.
 *
 * @author Cedric Henry
 * @see ProjectTimeline
 * @generated
 */
@ProviderType
public class ProjectTimelineCacheModel implements CacheModel<ProjectTimeline>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectTimelineCacheModel)) {
			return false;
		}

		ProjectTimelineCacheModel projectTimelineCacheModel = (ProjectTimelineCacheModel)obj;

		if (projectTimelineId == projectTimelineCacheModel.projectTimelineId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectTimelineId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{projectTimelineId=");
		sb.append(projectTimelineId);
		sb.append(", startDay=");
		sb.append(startDay);
		sb.append(", spacing=");
		sb.append(spacing);
		sb.append(", date=");
		sb.append(date);
		sb.append(", dateFormat=");
		sb.append(dateFormat);
		sb.append(", title=");
		sb.append(title);
		sb.append(", link=");
		sb.append(link);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectTimeline toEntityModel() {
		ProjectTimelineImpl projectTimelineImpl = new ProjectTimelineImpl();

		projectTimelineImpl.setProjectTimelineId(projectTimelineId);
		projectTimelineImpl.setStartDay(startDay);
		projectTimelineImpl.setSpacing(spacing);

		if (date == Long.MIN_VALUE) {
			projectTimelineImpl.setDate(null);
		}
		else {
			projectTimelineImpl.setDate(new Date(date));
		}

		if (dateFormat == null) {
			projectTimelineImpl.setDateFormat(StringPool.BLANK);
		}
		else {
			projectTimelineImpl.setDateFormat(dateFormat);
		}

		if (title == null) {
			projectTimelineImpl.setTitle(StringPool.BLANK);
		}
		else {
			projectTimelineImpl.setTitle(title);
		}

		if (link == null) {
			projectTimelineImpl.setLink(StringPool.BLANK);
		}
		else {
			projectTimelineImpl.setLink(link);
		}

		projectTimelineImpl.setProjectId(projectId);

		projectTimelineImpl.resetOriginalValues();

		return projectTimelineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectTimelineId = objectInput.readLong();

		startDay = objectInput.readInt();

		spacing = objectInput.readInt();
		date = objectInput.readLong();
		dateFormat = objectInput.readUTF();
		title = objectInput.readUTF();
		link = objectInput.readUTF();

		projectId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(projectTimelineId);

		objectOutput.writeInt(startDay);

		objectOutput.writeInt(spacing);
		objectOutput.writeLong(date);

		if (dateFormat == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dateFormat);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (link == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(link);
		}

		objectOutput.writeLong(projectId);
	}

	public long projectTimelineId;
	public int startDay;
	public int spacing;
	public long date;
	public String dateFormat;
	public String title;
	public String link;
	public long projectId;
}