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

package eu.strasbourg.service.project.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.ProjectTimelineServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.ProjectTimelineServiceSoap
 * @generated
 */
@ProviderType
public class ProjectTimelineSoap implements Serializable {
	public static ProjectTimelineSoap toSoapModel(ProjectTimeline model) {
		ProjectTimelineSoap soapModel = new ProjectTimelineSoap();

		soapModel.setProjectTimelineId(model.getProjectTimelineId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setDate(model.getDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setProjectId(model.getProjectId());

		return soapModel;
	}

	public static ProjectTimelineSoap[] toSoapModels(ProjectTimeline[] models) {
		ProjectTimelineSoap[] soapModels = new ProjectTimelineSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectTimelineSoap[][] toSoapModels(
		ProjectTimeline[][] models) {
		ProjectTimelineSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectTimelineSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectTimelineSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectTimelineSoap[] toSoapModels(
		List<ProjectTimeline> models) {
		List<ProjectTimelineSoap> soapModels = new ArrayList<ProjectTimelineSoap>(models.size());

		for (ProjectTimeline model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectTimelineSoap[soapModels.size()]);
	}

	public ProjectTimelineSoap() {
	}

	public long getPrimaryKey() {
		return _projectTimelineId;
	}

	public void setPrimaryKey(long pk) {
		setProjectTimelineId(pk);
	}

	public long getProjectTimelineId() {
		return _projectTimelineId;
	}

	public void setProjectTimelineId(long projectTimelineId) {
		_projectTimelineId = projectTimelineId;
	}

	public int getStartDate() {
		return _startDate;
	}

	public void setStartDate(int startDate) {
		_startDate = startDate;
	}

	public Date getDate() {
		return _date;
	}

	public void setDate(Date date) {
		_date = date;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	private long _projectTimelineId;
	private int _startDate;
	private Date _date;
	private String _title;
	private long _projectId;
}