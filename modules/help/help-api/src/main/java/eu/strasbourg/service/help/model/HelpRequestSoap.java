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

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class HelpRequestSoap implements Serializable {

	public static HelpRequestSoap toSoapModel(HelpRequest model) {
		HelpRequestSoap soapModel = new HelpRequestSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setHelpRequestId(model.getHelpRequestId());
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
		soapModel.setPublikId(model.getPublikId());
		soapModel.setHelpProposalId(model.getHelpProposalId());
		soapModel.setPhoneNumber(model.getPhoneNumber());
		soapModel.setMessage(model.getMessage());
		soapModel.setStudentCardImageId(model.getStudentCardImageId());
		soapModel.setAgreementSigned1(model.isAgreementSigned1());
		soapModel.setAgreementSigned2(model.isAgreementSigned2());
		soapModel.setAgreementSigned3(model.isAgreementSigned3());
		soapModel.setComment(model.getComment());

		return soapModel;
	}

	public static HelpRequestSoap[] toSoapModels(HelpRequest[] models) {
		HelpRequestSoap[] soapModels = new HelpRequestSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelpRequestSoap[][] toSoapModels(HelpRequest[][] models) {
		HelpRequestSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelpRequestSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelpRequestSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelpRequestSoap[] toSoapModels(List<HelpRequest> models) {
		List<HelpRequestSoap> soapModels = new ArrayList<HelpRequestSoap>(
			models.size());

		for (HelpRequest model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelpRequestSoap[soapModels.size()]);
	}

	public HelpRequestSoap() {
	}

	public long getPrimaryKey() {
		return _helpRequestId;
	}

	public void setPrimaryKey(long pk) {
		setHelpRequestId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getHelpRequestId() {
		return _helpRequestId;
	}

	public void setHelpRequestId(long helpRequestId) {
		_helpRequestId = helpRequestId;
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

	public String getPublikId() {
		return _publikId;
	}

	public void setPublikId(String publikId) {
		_publikId = publikId;
	}

	public long getHelpProposalId() {
		return _helpProposalId;
	}

	public void setHelpProposalId(long helpProposalId) {
		_helpProposalId = helpProposalId;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public long getStudentCardImageId() {
		return _studentCardImageId;
	}

	public void setStudentCardImageId(long studentCardImageId) {
		_studentCardImageId = studentCardImageId;
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

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	private String _uuid;
	private long _helpRequestId;
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
	private String _publikId;
	private long _helpProposalId;
	private String _phoneNumber;
	private String _message;
	private long _studentCardImageId;
	private boolean _agreementSigned1;
	private boolean _agreementSigned2;
	private boolean _agreementSigned3;
	private String _comment;

}