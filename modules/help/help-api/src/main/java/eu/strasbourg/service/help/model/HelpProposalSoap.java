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

package eu.strasbourg.service.help.model;

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
public class HelpProposalSoap implements Serializable {

	public static HelpProposalSoap toSoapModel(HelpProposal model) {
		HelpProposalSoap soapModel = new HelpProposalSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setHelpProposalId(model.getHelpProposalId());
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
		soapModel.setDescription(model.getDescription());
		soapModel.setInTheNameOf(model.getInTheNameOf());
		soapModel.setAddress(model.getAddress());
		soapModel.setCity(model.getCity());
		soapModel.setPostalCode(model.getPostalCode());
		soapModel.setPhoneNumber(model.getPhoneNumber());
		soapModel.setModifiedByUserDate(model.getModifiedByUserDate());
		soapModel.setSpokenLanguages(model.getSpokenLanguages());
		soapModel.setAgreementSigned1(model.isAgreementSigned1());
		soapModel.setAgreementSigned2(model.isAgreementSigned2());
		soapModel.setAgreementSigned3(model.isAgreementSigned3());
		soapModel.setImageId(model.getImageId());
		soapModel.setPublikId(model.getPublikId());
		soapModel.setComment(model.getComment());

		return soapModel;
	}

	public static HelpProposalSoap[] toSoapModels(HelpProposal[] models) {
		HelpProposalSoap[] soapModels = new HelpProposalSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpProposalSoap[][] toSoapModels(HelpProposal[][] models) {
		HelpProposalSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpProposalSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpProposalSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpProposalSoap[] toSoapModels(List<HelpProposal> models) {
		List<HelpProposalSoap> soapModels = new ArrayList<HelpProposalSoap>(
			models.size());

		for (HelpProposal model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpProposalSoap[soapModels.size()]);
	}

	public HelpProposalSoap() {
	}

	public long getPrimaryKey() {
		return _helpProposalId;
	}

	public void setPrimaryKey(long pk) {
		setHelpProposalId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getHelpProposalId() {
		return _helpProposalId;
	}

	public void setHelpProposalId(long helpProposalId) {
		_helpProposalId = helpProposalId;
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getInTheNameOf() {
		return _inTheNameOf;
	}

	public void setInTheNameOf(String inTheNameOf) {
		_inTheNameOf = inTheNameOf;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public long getPostalCode() {
		return _postalCode;
	}

	public void setPostalCode(long postalCode) {
		_postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public Date getModifiedByUserDate() {
		return _modifiedByUserDate;
	}

	public void setModifiedByUserDate(Date modifiedByUserDate) {
		_modifiedByUserDate = modifiedByUserDate;
	}

	public String getSpokenLanguages() {
		return _spokenLanguages;
	}

	public void setSpokenLanguages(String spokenLanguages) {
		_spokenLanguages = spokenLanguages;
	}

	public boolean getAgreementSigned1() {
		return _agreementSigned1;
	}

	public boolean isAgreementSigned1() {
		return _agreementSigned1;
	}

	public void setAgreementSigned1(boolean agreementSigned1) {
		_agreementSigned1 = agreementSigned1;
	}

	public boolean getAgreementSigned2() {
		return _agreementSigned2;
	}

	public boolean isAgreementSigned2() {
		return _agreementSigned2;
	}

	public void setAgreementSigned2(boolean agreementSigned2) {
		_agreementSigned2 = agreementSigned2;
	}

	public boolean getAgreementSigned3() {
		return _agreementSigned3;
	}

	public boolean isAgreementSigned3() {
		return _agreementSigned3;
	}

	public void setAgreementSigned3(boolean agreementSigned3) {
		_agreementSigned3 = agreementSigned3;
	}

	public long getImageId() {
		return _imageId;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	public String getPublikId() {
		return _publikId;
	}

	public void setPublikId(String publikId) {
		_publikId = publikId;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	private String _uuid;
	private long _helpProposalId;
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
	private String _description;
	private String _inTheNameOf;
	private String _address;
	private String _city;
	private long _postalCode;
	private String _phoneNumber;
	private Date _modifiedByUserDate;
	private String _spokenLanguages;
	private boolean _agreementSigned1;
	private boolean _agreementSigned2;
	private boolean _agreementSigned3;
	private long _imageId;
	private String _publikId;
	private String _comment;

}