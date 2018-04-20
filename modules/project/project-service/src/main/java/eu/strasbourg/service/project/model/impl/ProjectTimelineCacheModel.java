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
		StringBundler sb = new StringBundler(11);

		sb.append("{projectTimelineId=");
		sb.append(projectTimelineId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", date=");
		sb.append(date);
		sb.append(", title=");
		sb.append(title);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProjectTimeline toEntityModel() {
		ProjectTimelineImpl projectTimelineImpl = new ProjectTimelineImpl();

		projectTimelineImpl.setProjectTimelineId(projectTimelineId);
		projectTimelineImpl.setStartDate(startDate);

		if (date == Long.MIN_VALUE) {
			projectTimelineImpl.setDate(null);
		}
		else {
			projectTimelineImpl.setDate(new Date(date));
		}

		if (title == null) {
			projectTimelineImpl.setTitle(StringPool.BLANK);
		}
		else {
			projectTimelineImpl.setTitle(title);
		}

		projectTimelineImpl.setProjectId(projectId);

		projectTimelineImpl.resetOriginalValues();

		return projectTimelineImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectTimelineId = objectInput.readLong();

		startDate = objectInput.readInt();
		date = objectInput.readLong();
		title = objectInput.readUTF();

		projectId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(projectTimelineId);

		objectOutput.writeInt(startDate);
		objectOutput.writeLong(date);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(projectId);
	}

	public long projectTimelineId;
	public int startDate;
	public long date;
	public String title;
	public long projectId;
}