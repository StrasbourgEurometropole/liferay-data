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

package eu.strasbourg.service.artwork.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.artwork.service.http.ArtworkServiceSoap}.
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.artwork.service.http.ArtworkServiceSoap
 * @generated
 */
@ProviderType
public class ArtworkSoap implements Serializable {
	public static ArtworkSoap toSoapModel(Artwork model) {
		ArtworkSoap soapModel = new ArtworkSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setArtworkId(model.getArtworkId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setTechnicalInformation(model.getTechnicalInformation());
		soapModel.setNoticeLink(model.getNoticeLink());
		soapModel.setArtistName(model.getArtistName());
		soapModel.setCreationYear(model.getCreationYear());
		soapModel.setOrigin(model.getOrigin());
		soapModel.setExhibitionName(model.getExhibitionName());
		soapModel.setExhibitionPlace(model.getExhibitionPlace());
		soapModel.setLoanPeriod(model.getLoanPeriod());
		soapModel.setLinkName(model.getLinkName());
		soapModel.setLink(model.getLink());
		soapModel.setStatus(model.getStatus());
		soapModel.setImageId(model.getImageId());
		soapModel.setImagesIds(model.getImagesIds());

		return soapModel;
	}

	public static ArtworkSoap[] toSoapModels(Artwork[] models) {
		ArtworkSoap[] soapModels = new ArtworkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ArtworkSoap[][] toSoapModels(Artwork[][] models) {
		ArtworkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ArtworkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ArtworkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ArtworkSoap[] toSoapModels(List<Artwork> models) {
		List<ArtworkSoap> soapModels = new ArrayList<ArtworkSoap>(models.size());

		for (Artwork model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ArtworkSoap[soapModels.size()]);
	}

	public ArtworkSoap() {
	}

	public long getPrimaryKey() {
		return _artworkId;
	}

	public void setPrimaryKey(long pk) {
		setArtworkId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getArtworkId() {
		return _artworkId;
	}

	public void setArtworkId(long artworkId) {
		_artworkId = artworkId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getTechnicalInformation() {
		return _technicalInformation;
	}

	public void setTechnicalInformation(String technicalInformation) {
		_technicalInformation = technicalInformation;
	}

	public String getNoticeLink() {
		return _noticeLink;
	}

	public void setNoticeLink(String noticeLink) {
		_noticeLink = noticeLink;
	}

	public String getArtistName() {
		return _artistName;
	}

	public void setArtistName(String artistName) {
		_artistName = artistName;
	}

	public String getCreationYear() {
		return _creationYear;
	}

	public void setCreationYear(String creationYear) {
		_creationYear = creationYear;
	}

	public String getOrigin() {
		return _origin;
	}

	public void setOrigin(String origin) {
		_origin = origin;
	}

	public String getExhibitionName() {
		return _exhibitionName;
	}

	public void setExhibitionName(String exhibitionName) {
		_exhibitionName = exhibitionName;
	}

	public String getExhibitionPlace() {
		return _exhibitionPlace;
	}

	public void setExhibitionPlace(String exhibitionPlace) {
		_exhibitionPlace = exhibitionPlace;
	}

	public String getLoanPeriod() {
		return _loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		_loanPeriod = loanPeriod;
	}

	public String getLinkName() {
		return _linkName;
	}

	public void setLinkName(String linkName) {
		_linkName = linkName;
	}

	public String getLink() {
		return _link;
	}

	public void setLink(String link) {
		_link = link;
	}

	public boolean getStatus() {
		return _status;
	}

	public boolean isStatus() {
		return _status;
	}

	public void setStatus(boolean status) {
		_status = status;
	}

	public Long getImageId() {
		return _imageId;
	}

	public void setImageId(Long imageId) {
		_imageId = imageId;
	}

	public String getImagesIds() {
		return _imagesIds;
	}

	public void setImagesIds(String imagesIds) {
		_imagesIds = imagesIds;
	}

	private String _uuid;
	private long _artworkId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private String _technicalInformation;
	private String _noticeLink;
	private String _artistName;
	private String _creationYear;
	private String _origin;
	private String _exhibitionName;
	private String _exhibitionPlace;
	private String _loanPeriod;
	private String _linkName;
	private String _link;
	private boolean _status;
	private Long _imageId;
	private String _imagesIds;
}