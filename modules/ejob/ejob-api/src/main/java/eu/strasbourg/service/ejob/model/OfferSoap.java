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

package eu.strasbourg.service.ejob.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link eu.strasbourg.service.ejob.service.http.OfferServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OfferSoap implements Serializable {

	public static OfferSoap toSoapModel(Offer model) {
		OfferSoap soapModel = new OfferSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setOfferId(model.getOfferId());
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
		soapModel.setPublicationId(model.getPublicationId());
		soapModel.setPostNumber(model.getPostNumber());
		soapModel.setJobCreationDescription(model.getJobCreationDescription());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setPermanentDescription(model.getPermanentDescription());
		soapModel.setDuration(model.getDuration());
		soapModel.setPost(model.getPost());
		soapModel.setIsFullTime(model.isIsFullTime());
		soapModel.setFullTimeDescription(model.getFullTimeDescription());
		soapModel.setIntroduction(model.getIntroduction());
		soapModel.setActivities(model.getActivities());
		soapModel.setProfil(model.getProfil());
		soapModel.setConditions(model.getConditions());
		soapModel.setAvantages(model.getAvantages());
		soapModel.setLimitDate(model.getLimitDate());
		soapModel.setContact(model.getContact());
		soapModel.setEmails(model.getEmails());
		soapModel.setShareLinkedin(model.isShareLinkedin());
		soapModel.setPublicationStartDate(model.getPublicationStartDate());
		soapModel.setPublicationEndDate(model.getPublicationEndDate());
		soapModel.setIsExported(model.getIsExported());
		soapModel.setEmailSend(model.getEmailSend());
		soapModel.setEmailPartnerSent(model.getEmailPartnerSent());

		return soapModel;
	}

	public static OfferSoap[] toSoapModels(Offer[] models) {
		OfferSoap[] soapModels = new OfferSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OfferSoap[][] toSoapModels(Offer[][] models) {
		OfferSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OfferSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OfferSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OfferSoap[] toSoapModels(List<Offer> models) {
		List<OfferSoap> soapModels = new ArrayList<OfferSoap>(models.size());

		for (Offer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OfferSoap[soapModels.size()]);
	}

	public OfferSoap() {
	}

	public long getPrimaryKey() {
		return _offerId;
	}

	public void setPrimaryKey(long pk) {
		setOfferId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getOfferId() {
		return _offerId;
	}

	public void setOfferId(long offerId) {
		_offerId = offerId;
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

	public String getPublicationId() {
		return _publicationId;
	}

	public void setPublicationId(String publicationId) {
		_publicationId = publicationId;
	}

	public String getPostNumber() {
		return _postNumber;
	}

	public void setPostNumber(String postNumber) {
		_postNumber = postNumber;
	}

	public String getJobCreationDescription() {
		return _jobCreationDescription;
	}

	public void setJobCreationDescription(String jobCreationDescription) {
		_jobCreationDescription = jobCreationDescription;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public String getPermanentDescription() {
		return _permanentDescription;
	}

	public void setPermanentDescription(String permanentDescription) {
		_permanentDescription = permanentDescription;
	}

	public String getDuration() {
		return _duration;
	}

	public void setDuration(String duration) {
		_duration = duration;
	}

	public String getPost() {
		return _post;
	}

	public void setPost(String post) {
		_post = post;
	}

	public boolean getIsFullTime() {
		return _isFullTime;
	}

	public boolean isIsFullTime() {
		return _isFullTime;
	}

	public void setIsFullTime(boolean isFullTime) {
		_isFullTime = isFullTime;
	}

	public String getFullTimeDescription() {
		return _fullTimeDescription;
	}

	public void setFullTimeDescription(String fullTimeDescription) {
		_fullTimeDescription = fullTimeDescription;
	}

	public String getIntroduction() {
		return _introduction;
	}

	public void setIntroduction(String introduction) {
		_introduction = introduction;
	}

	public String getActivities() {
		return _activities;
	}

	public void setActivities(String activities) {
		_activities = activities;
	}

	public String getProfil() {
		return _profil;
	}

	public void setProfil(String profil) {
		_profil = profil;
	}

	public String getConditions() {
		return _conditions;
	}

	public void setConditions(String conditions) {
		_conditions = conditions;
	}

	public String getAvantages() {
		return _avantages;
	}

	public void setAvantages(String avantages) {
		_avantages = avantages;
	}

	public Date getLimitDate() {
		return _limitDate;
	}

	public void setLimitDate(Date limitDate) {
		_limitDate = limitDate;
	}

	public String getContact() {
		return _contact;
	}

	public void setContact(String contact) {
		_contact = contact;
	}

	public String getEmails() {
		return _emails;
	}

	public void setEmails(String emails) {
		_emails = emails;
	}

	public boolean getShareLinkedin() {
		return _shareLinkedin;
	}

	public boolean isShareLinkedin() {
		return _shareLinkedin;
	}

	public void setShareLinkedin(boolean shareLinkedin) {
		_shareLinkedin = shareLinkedin;
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

	public int getIsExported() {
		return _isExported;
	}

	public void setIsExported(int isExported) {
		_isExported = isExported;
	}

	public int getEmailSend() {
		return _emailSend;
	}

	public void setEmailSend(int emailSend) {
		_emailSend = emailSend;
	}

	public int getEmailPartnerSent() {
		return _emailPartnerSent;
	}

	public void setEmailPartnerSent(int emailPartnerSent) {
		_emailPartnerSent = emailPartnerSent;
	}

	private String _uuid;
	private long _offerId;
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
	private String _publicationId;
	private String _postNumber;
	private String _jobCreationDescription;
	private Date _startDate;
	private String _permanentDescription;
	private String _duration;
	private String _post;
	private boolean _isFullTime;
	private String _fullTimeDescription;
	private String _introduction;
	private String _activities;
	private String _profil;
	private String _conditions;
	private String _avantages;
	private Date _limitDate;
	private String _contact;
	private String _emails;
	private boolean _shareLinkedin;
	private Date _publicationStartDate;
	private Date _publicationEndDate;
	private int _isExported;
	private int _emailSend;
	private int _emailPartnerSent;

}