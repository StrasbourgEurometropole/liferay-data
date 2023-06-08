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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AgendaSoap implements Serializable {

	public static AgendaSoap toSoapModel(Agenda model) {
		AgendaSoap soapModel = new AgendaSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAgendaId(model.getAgendaId());
		soapModel.setTitle(model.getTitle());
		soapModel.setEditorialTitle(model.getEditorialTitle());
		soapModel.setSubtitle(model.getSubtitle());
		soapModel.setImageId(model.getImageId());
		soapModel.setLabelLink(model.getLabelLink());
		soapModel.setLink(model.getLink());
		soapModel.setPublicationStartDate(model.getPublicationStartDate());
		soapModel.setPublicationEndDate(model.getPublicationEndDate());
		soapModel.setIsPrincipal(model.getIsPrincipal());
		soapModel.setIsActive(model.getIsActive());
		soapModel.setCampaignsIds(model.getCampaignsIds());
		soapModel.setThemesIds(model.getThemesIds());
		soapModel.setTypesIds(model.getTypesIds());
		soapModel.setTerritoriesIds(model.getTerritoriesIds());
		soapModel.setTags(model.getTags());

		return soapModel;
	}

	public static AgendaSoap[] toSoapModels(Agenda[] models) {
		AgendaSoap[] soapModels = new AgendaSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AgendaSoap[][] toSoapModels(Agenda[][] models) {
		AgendaSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AgendaSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AgendaSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AgendaSoap[] toSoapModels(List<Agenda> models) {
		List<AgendaSoap> soapModels = new ArrayList<AgendaSoap>(models.size());

		for (Agenda model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AgendaSoap[soapModels.size()]);
	}

	public AgendaSoap() {
	}

	public long getPrimaryKey() {
		return _agendaId;
	}

	public void setPrimaryKey(long pk) {
		setAgendaId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAgendaId() {
		return _agendaId;
	}

	public void setAgendaId(long agendaId) {
		_agendaId = agendaId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getEditorialTitle() {
		return _editorialTitle;
	}

	public void setEditorialTitle(String editorialTitle) {
		_editorialTitle = editorialTitle;
	}

	public String getSubtitle() {
		return _subtitle;
	}

	public void setSubtitle(String subtitle) {
		_subtitle = subtitle;
	}

	public Long getImageId() {
		return _imageId;
	}

	public void setImageId(Long imageId) {
		_imageId = imageId;
	}

	public String getLabelLink() {
		return _labelLink;
	}

	public void setLabelLink(String labelLink) {
		_labelLink = labelLink;
	}

	public String getLink() {
		return _link;
	}

	public void setLink(String link) {
		_link = link;
	}

	public Date getPublicationStartDate() {
		return _publicationStartDate;
	}

	public void setPublicationStartDate(Date publicationStartDate) {
		_publicationStartDate = publicationStartDate;
	}

	public Date getPublicationEndDate() {
		return _publicationEndDate;
	}

	public void setPublicationEndDate(Date publicationEndDate) {
		_publicationEndDate = publicationEndDate;
	}

	public Boolean getIsPrincipal() {
		return _isPrincipal;
	}

	public void setIsPrincipal(Boolean isPrincipal) {
		_isPrincipal = isPrincipal;
	}

	public Boolean getIsActive() {
		return _isActive;
	}

	public void setIsActive(Boolean isActive) {
		_isActive = isActive;
	}

	public String getCampaignsIds() {
		return _campaignsIds;
	}

	public void setCampaignsIds(String campaignsIds) {
		_campaignsIds = campaignsIds;
	}

	public String getThemesIds() {
		return _themesIds;
	}

	public void setThemesIds(String themesIds) {
		_themesIds = themesIds;
	}

	public String getTypesIds() {
		return _typesIds;
	}

	public void setTypesIds(String typesIds) {
		_typesIds = typesIds;
	}

	public String getTerritoriesIds() {
		return _territoriesIds;
	}

	public void setTerritoriesIds(String territoriesIds) {
		_territoriesIds = territoriesIds;
	}

	public String getTags() {
		return _tags;
	}

	public void setTags(String tags) {
		_tags = tags;
	}

	private String _uuid;
	private long _agendaId;
	private String _title;
	private String _editorialTitle;
	private String _subtitle;
	private Long _imageId;
	private String _labelLink;
	private String _link;
	private Date _publicationStartDate;
	private Date _publicationEndDate;
	private Boolean _isPrincipal;
	private Boolean _isActive;
	private String _campaignsIds;
	private String _themesIds;
	private String _typesIds;
	private String _territoriesIds;
	private String _tags;

}