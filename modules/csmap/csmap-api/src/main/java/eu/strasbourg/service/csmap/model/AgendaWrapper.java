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
 * This class is a wrapper for {@link Agenda}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Agenda
 * @generated
 */
@ProviderType
public class AgendaWrapper
	extends BaseModelWrapper<Agenda> implements Agenda, ModelWrapper<Agenda> {

	public AgendaWrapper(Agenda agenda) {
		super(agenda);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("agendaId", getAgendaId());
		attributes.put("title", getTitle());
		attributes.put("editorialTitle", getEditorialTitle());
		attributes.put("subtitle", getSubtitle());
		attributes.put("imageId", getImageId());
		attributes.put("isPrincipal", getIsPrincipal());
		attributes.put("isActive", getIsActive());
		attributes.put("campaignsIds", getCampaignsIds());
		attributes.put("themesIds", getThemesIds());
		attributes.put("typesIds", getTypesIds());
		attributes.put("tags", getTags());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long agendaId = (Long)attributes.get("agendaId");

		if (agendaId != null) {
			setAgendaId(agendaId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String editorialTitle = (String)attributes.get("editorialTitle");

		if (editorialTitle != null) {
			setEditorialTitle(editorialTitle);
		}

		String subtitle = (String)attributes.get("subtitle");

		if (subtitle != null) {
			setSubtitle(subtitle);
		}

		Long imageId = (Long)attributes.get("imageId");

		if (imageId != null) {
			setImageId(imageId);
		}

		Boolean isPrincipal = (Boolean)attributes.get("isPrincipal");

		if (isPrincipal != null) {
			setIsPrincipal(isPrincipal);
		}

		Boolean isActive = (Boolean)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		String campaignsIds = (String)attributes.get("campaignsIds");

		if (campaignsIds != null) {
			setCampaignsIds(campaignsIds);
		}

		String themesIds = (String)attributes.get("themesIds");

		if (themesIds != null) {
			setThemesIds(themesIds);
		}

		String typesIds = (String)attributes.get("typesIds");

		if (typesIds != null) {
			setTypesIds(typesIds);
		}

		String tags = (String)attributes.get("tags");

		if (tags != null) {
			setTags(tags);
		}
	}

	/**
	 * Returns the agenda ID of this agenda.
	 *
	 * @return the agenda ID of this agenda
	 */
	@Override
	public long getAgendaId() {
		return model.getAgendaId();
	}

	/**
	 * Returns the campaigns IDs of this agenda.
	 *
	 * @return the campaigns IDs of this agenda
	 */
	@Override
	public String getCampaignsIds() {
		return model.getCampaignsIds();
	}

	/**
	 * Returns the editorial title of this agenda.
	 *
	 * @return the editorial title of this agenda
	 */
	@Override
	public String getEditorialTitle() {
		return model.getEditorialTitle();
	}

	/**
	 * Returns the image ID of this agenda.
	 *
	 * @return the image ID of this agenda
	 */
	@Override
	public Long getImageId() {
		return model.getImageId();
	}

	/**
	 * Returns the is active of this agenda.
	 *
	 * @return the is active of this agenda
	 */
	@Override
	public Boolean getIsActive() {
		return model.getIsActive();
	}

	/**
	 * Returns the is principal of this agenda.
	 *
	 * @return the is principal of this agenda
	 */
	@Override
	public Boolean getIsPrincipal() {
		return model.getIsPrincipal();
	}

	/**
	 * Returns the primary key of this agenda.
	 *
	 * @return the primary key of this agenda
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the subtitle of this agenda.
	 *
	 * @return the subtitle of this agenda
	 */
	@Override
	public String getSubtitle() {
		return model.getSubtitle();
	}

	/**
	 * Returns the tags of this agenda.
	 *
	 * @return the tags of this agenda
	 */
	@Override
	public String getTags() {
		return model.getTags();
	}

	/**
	 * Returns the themes IDs of this agenda.
	 *
	 * @return the themes IDs of this agenda
	 */
	@Override
	public String getThemesIds() {
		return model.getThemesIds();
	}

	/**
	 * Returns the title of this agenda.
	 *
	 * @return the title of this agenda
	 */
	@Override
	public String getTitle() {
		return model.getTitle();
	}

	/**
	 * Returns the types IDs of this agenda.
	 *
	 * @return the types IDs of this agenda
	 */
	@Override
	public String getTypesIds() {
		return model.getTypesIds();
	}

	/**
	 * Returns the uuid of this agenda.
	 *
	 * @return the uuid of this agenda
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the agenda ID of this agenda.
	 *
	 * @param agendaId the agenda ID of this agenda
	 */
	@Override
	public void setAgendaId(long agendaId) {
		model.setAgendaId(agendaId);
	}

	/**
	 * Sets the campaigns IDs of this agenda.
	 *
	 * @param campaignsIds the campaigns IDs of this agenda
	 */
	@Override
	public void setCampaignsIds(String campaignsIds) {
		model.setCampaignsIds(campaignsIds);
	}

	/**
	 * Sets the editorial title of this agenda.
	 *
	 * @param editorialTitle the editorial title of this agenda
	 */
	@Override
	public void setEditorialTitle(String editorialTitle) {
		model.setEditorialTitle(editorialTitle);
	}

	/**
	 * Sets the image ID of this agenda.
	 *
	 * @param imageId the image ID of this agenda
	 */
	@Override
	public void setImageId(Long imageId) {
		model.setImageId(imageId);
	}

	/**
	 * Sets the is active of this agenda.
	 *
	 * @param isActive the is active of this agenda
	 */
	@Override
	public void setIsActive(Boolean isActive) {
		model.setIsActive(isActive);
	}

	/**
	 * Sets the is principal of this agenda.
	 *
	 * @param isPrincipal the is principal of this agenda
	 */
	@Override
	public void setIsPrincipal(Boolean isPrincipal) {
		model.setIsPrincipal(isPrincipal);
	}

	/**
	 * Sets the primary key of this agenda.
	 *
	 * @param primaryKey the primary key of this agenda
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the subtitle of this agenda.
	 *
	 * @param subtitle the subtitle of this agenda
	 */
	@Override
	public void setSubtitle(String subtitle) {
		model.setSubtitle(subtitle);
	}

	/**
	 * Sets the tags of this agenda.
	 *
	 * @param tags the tags of this agenda
	 */
	@Override
	public void setTags(String tags) {
		model.setTags(tags);
	}

	/**
	 * Sets the themes IDs of this agenda.
	 *
	 * @param themesIds the themes IDs of this agenda
	 */
	@Override
	public void setThemesIds(String themesIds) {
		model.setThemesIds(themesIds);
	}

	/**
	 * Sets the title of this agenda.
	 *
	 * @param title the title of this agenda
	 */
	@Override
	public void setTitle(String title) {
		model.setTitle(title);
	}

	/**
	 * Sets the types IDs of this agenda.
	 *
	 * @param typesIds the types IDs of this agenda
	 */
	@Override
	public void setTypesIds(String typesIds) {
		model.setTypesIds(typesIds);
	}

	/**
	 * Sets the uuid of this agenda.
	 *
	 * @param uuid the uuid of this agenda
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected AgendaWrapper wrap(Agenda agenda) {
		return new AgendaWrapper(agenda);
	}

}