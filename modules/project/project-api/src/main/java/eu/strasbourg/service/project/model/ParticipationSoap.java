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
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.project.service.http.ParticipationServiceSoap}.
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.http.ParticipationServiceSoap
 * @generated
 */
@ProviderType
public class ParticipationSoap implements Serializable {
	public static ParticipationSoap toSoapModel(Participation model) {
		ParticipationSoap soapModel = new ParticipationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setParticipationId(model.getParticipationId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setAuthor(model.getAuthor());
		soapModel.setContactName(model.getContactName());
		soapModel.setContactLine1(model.getContactLine1());
		soapModel.setContactLine2(model.getContactLine2());
		soapModel.setContactPhoneNumber(model.getContactPhoneNumber());
		soapModel.setPublicationDate(model.getPublicationDate());
		soapModel.setExpirationDate(model.getExpirationDate());

		return soapModel;
	}

	public static ParticipationSoap[] toSoapModels(Participation[] models) {
		ParticipationSoap[] soapModels = new ParticipationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ParticipationSoap[][] toSoapModels(Participation[][] models) {
		ParticipationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ParticipationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ParticipationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ParticipationSoap[] toSoapModels(List<Participation> models) {
		List<ParticipationSoap> soapModels = new ArrayList<ParticipationSoap>(models.size());

		for (Participation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ParticipationSoap[soapModels.size()]);
	}

	public ParticipationSoap() {
	}

	public long getPrimaryKey() {
		return _participationId;
	}

	public void setPrimaryKey(long pk) {
		setParticipationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getParticipationId() {
		return _participationId;
	}

	public void setParticipationId(long participationId) {
		_participationId = participationId;
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

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getAuthor() {
		return _author;
	}

	public void setAuthor(String author) {
		_author = author;
	}

	public String getContactName() {
		return _contactName;
	}

	public void setContactName(String contactName) {
		_contactName = contactName;
	}

	public String getContactLine1() {
		return _contactLine1;
	}

	public void setContactLine1(String contactLine1) {
		_contactLine1 = contactLine1;
	}

	public String getContactLine2() {
		return _contactLine2;
	}

	public void setContactLine2(String contactLine2) {
		_contactLine2 = contactLine2;
	}

	public String getContactPhoneNumber() {
		return _contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		_contactPhoneNumber = contactPhoneNumber;
	}

	public Date getPublicationDate() {
		return _publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		_publicationDate = publicationDate;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	private String _uuid;
	private long _participationId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _title;
	private String _author;
	private String _contactName;
	private String _contactLine1;
	private String _contactLine2;
	private String _contactPhoneNumber;
	private Date _publicationDate;
	private Date _expirationDate;
}