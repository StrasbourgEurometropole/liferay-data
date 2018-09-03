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
 * This class is a wrapper for {@link ProjectTimeline}.
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectTimeline
 * @generated
 */
@ProviderType
public class ProjectTimelineWrapper implements ProjectTimeline,
	ModelWrapper<ProjectTimeline> {
	public ProjectTimelineWrapper(ProjectTimeline projectTimeline) {
		_projectTimeline = projectTimeline;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectTimeline.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectTimeline.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectTimelineId", getProjectTimelineId());
		attributes.put("startDay", getStartDay());
		attributes.put("spacing", getSpacing());
		attributes.put("date", getDate());
		attributes.put("title", getTitle());
		attributes.put("link", getLink());
		attributes.put("projectId", getProjectId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long projectTimelineId = (Long)attributes.get("projectTimelineId");

		if (projectTimelineId != null) {
			setProjectTimelineId(projectTimelineId);
		}

		Integer startDay = (Integer)attributes.get("startDay");

		if (startDay != null) {
			setStartDay(startDay);
		}

		Integer spacing = (Integer)attributes.get("spacing");

		if (spacing != null) {
			setSpacing(spacing);
		}

		Date date = (Date)attributes.get("date");

		if (date != null) {
			setDate(date);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String link = (String)attributes.get("link");

		if (link != null) {
			setLink(link);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _projectTimeline.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectTimeline.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectTimeline.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectTimeline.getExpandoBridge();
	}

	/**
	* Retourne la version JSON de l'entité
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject toJSON() {
		return _projectTimeline.toJSON();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<eu.strasbourg.service.project.model.ProjectTimeline> toCacheModel() {
		return _projectTimeline.toCacheModel();
	}

	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline toEscapedModel() {
		return new ProjectTimelineWrapper(_projectTimeline.toEscapedModel());
	}

	@Override
	public eu.strasbourg.service.project.model.ProjectTimeline toUnescapedModel() {
		return new ProjectTimelineWrapper(_projectTimeline.toUnescapedModel());
	}

	@Override
	public int compareTo(
		eu.strasbourg.service.project.model.ProjectTimeline projectTimeline) {
		return _projectTimeline.compareTo(projectTimeline);
	}

	/**
	* Returns the spacing of this project timeline.
	*
	* @return the spacing of this project timeline
	*/
	@Override
	public int getSpacing() {
		return _projectTimeline.getSpacing();
	}

	/**
	* Returns the start day of this project timeline.
	*
	* @return the start day of this project timeline
	*/
	@Override
	public int getStartDay() {
		return _projectTimeline.getStartDay();
	}

	@Override
	public int hashCode() {
		return _projectTimeline.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectTimeline.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectTimelineWrapper((ProjectTimeline)_projectTimeline.clone());
	}

	/**
	* Returns the link of this project timeline.
	*
	* @return the link of this project timeline
	*/
	@Override
	public java.lang.String getLink() {
		return _projectTimeline.getLink();
	}

	/**
	* Returns the title of this project timeline.
	*
	* @return the title of this project timeline
	*/
	@Override
	public java.lang.String getTitle() {
		return _projectTimeline.getTitle();
	}

	@Override
	public java.lang.String toString() {
		return _projectTimeline.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectTimeline.toXmlString();
	}

	/**
	* Returns the date of this project timeline.
	*
	* @return the date of this project timeline
	*/
	@Override
	public Date getDate() {
		return _projectTimeline.getDate();
	}

	/**
	* Returns the primary key of this project timeline.
	*
	* @return the primary key of this project timeline
	*/
	@Override
	public long getPrimaryKey() {
		return _projectTimeline.getPrimaryKey();
	}

	/**
	* Returns the project ID of this project timeline.
	*
	* @return the project ID of this project timeline
	*/
	@Override
	public long getProjectId() {
		return _projectTimeline.getProjectId();
	}

	/**
	* Returns the project timeline ID of this project timeline.
	*
	* @return the project timeline ID of this project timeline
	*/
	@Override
	public long getProjectTimelineId() {
		return _projectTimeline.getProjectTimelineId();
	}

	@Override
	public void persist() {
		_projectTimeline.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectTimeline.setCachedModel(cachedModel);
	}

	/**
	* Sets the date of this project timeline.
	*
	* @param date the date of this project timeline
	*/
	@Override
	public void setDate(Date date) {
		_projectTimeline.setDate(date);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectTimeline.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectTimeline.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectTimeline.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the link of this project timeline.
	*
	* @param link the link of this project timeline
	*/
	@Override
	public void setLink(java.lang.String link) {
		_projectTimeline.setLink(link);
	}

	@Override
	public void setNew(boolean n) {
		_projectTimeline.setNew(n);
	}

	/**
	* Sets the primary key of this project timeline.
	*
	* @param primaryKey the primary key of this project timeline
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_projectTimeline.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectTimeline.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project ID of this project timeline.
	*
	* @param projectId the project ID of this project timeline
	*/
	@Override
	public void setProjectId(long projectId) {
		_projectTimeline.setProjectId(projectId);
	}

	/**
	* Sets the project timeline ID of this project timeline.
	*
	* @param projectTimelineId the project timeline ID of this project timeline
	*/
	@Override
	public void setProjectTimelineId(long projectTimelineId) {
		_projectTimeline.setProjectTimelineId(projectTimelineId);
	}

	/**
	* Sets the spacing of this project timeline.
	*
	* @param spacing the spacing of this project timeline
	*/
	@Override
	public void setSpacing(int spacing) {
		_projectTimeline.setSpacing(spacing);
	}

	/**
	* Sets the start day of this project timeline.
	*
	* @param startDay the start day of this project timeline
	*/
	@Override
	public void setStartDay(int startDay) {
		_projectTimeline.setStartDay(startDay);
	}

	/**
	* Sets the title of this project timeline.
	*
	* @param title the title of this project timeline
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_projectTimeline.setTitle(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectTimelineWrapper)) {
			return false;
		}

		ProjectTimelineWrapper projectTimelineWrapper = (ProjectTimelineWrapper)obj;

		if (Objects.equals(_projectTimeline,
					projectTimelineWrapper._projectTimeline)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectTimeline getWrappedModel() {
		return _projectTimeline;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectTimeline.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectTimeline.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectTimeline.resetOriginalValues();
	}

	private final ProjectTimeline _projectTimeline;
}