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

package eu.strasbourg.service.notif.model;

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
public class NotificationSoap implements Serializable {

	public static NotificationSoap toSoapModel(Notification model) {
		NotificationSoap soapModel = new NotificationSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setNotificationId(model.getNotificationId());
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
		soapModel.setServiceId(model.getServiceId());
		soapModel.setIsAlert(model.getIsAlert());
		soapModel.setNatureId(model.getNatureId());
		soapModel.setTitle(model.getTitle());
		soapModel.setSubtitle(model.getSubtitle());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setBroadcastDate(model.getBroadcastDate());
		soapModel.setMessageId(model.getMessageId());
		soapModel.setContent(model.getContent());
		soapModel.setLabelUrl(model.getLabelUrl());
		soapModel.setUrl(model.getUrl());
		soapModel.setTypeBroadcast(model.getTypeBroadcast());
		soapModel.setDistrict(model.getDistrict());
		soapModel.setBroadcastChannels(model.getBroadcastChannels());
		soapModel.setSendStatusCsmap(model.getSendStatusCsmap());
		soapModel.setSendStatusTwitter(model.getSendStatusTwitter());
		soapModel.setSendStatusMonst(model.getSendStatusMonst());
		soapModel.setSendStatusMail(model.getSendStatusMail());
		soapModel.setSendStatusSegur(model.getSendStatusSegur());
		soapModel.setIsSend(model.getIsSend());

		return soapModel;
	}

	public static NotificationSoap[] toSoapModels(Notification[] models) {
		NotificationSoap[] soapModels = new NotificationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NotificationSoap[][] toSoapModels(Notification[][] models) {
		NotificationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NotificationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NotificationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NotificationSoap[] toSoapModels(List<Notification> models) {
		List<NotificationSoap> soapModels = new ArrayList<NotificationSoap>(
			models.size());

		for (Notification model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NotificationSoap[soapModels.size()]);
	}

	public NotificationSoap() {
	}

	public long getPrimaryKey() {
		return _notificationId;
	}

	public void setPrimaryKey(long pk) {
		setNotificationId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getNotificationId() {
		return _notificationId;
	}

	public void setNotificationId(long notificationId) {
		_notificationId = notificationId;
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

	public long getServiceId() {
		return _serviceId;
	}

	public void setServiceId(long serviceId) {
		_serviceId = serviceId;
	}

	public int getIsAlert() {
		return _isAlert;
	}

	public void setIsAlert(int isAlert) {
		_isAlert = isAlert;
	}

	public long getNatureId() {
		return _natureId;
	}

	public void setNatureId(long natureId) {
		_natureId = natureId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getSubtitle() {
		return _subtitle;
	}

	public void setSubtitle(String subtitle) {
		_subtitle = subtitle;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public Date getBroadcastDate() {
		return _broadcastDate;
	}

	public void setBroadcastDate(Date broadcastDate) {
		_broadcastDate = broadcastDate;
	}

	public long getMessageId() {
		return _messageId;
	}

	public void setMessageId(long messageId) {
		_messageId = messageId;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getLabelUrl() {
		return _labelUrl;
	}

	public void setLabelUrl(String labelUrl) {
		_labelUrl = labelUrl;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public long getTypeBroadcast() {
		return _typeBroadcast;
	}

	public void setTypeBroadcast(long typeBroadcast) {
		_typeBroadcast = typeBroadcast;
	}

	public long getDistrict() {
		return _district;
	}

	public void setDistrict(long district) {
		_district = district;
	}

	public String getBroadcastChannels() {
		return _broadcastChannels;
	}

	public void setBroadcastChannels(String broadcastChannels) {
		_broadcastChannels = broadcastChannels;
	}

	public long getSendStatusCsmap() {
		return _sendStatusCsmap;
	}

	public void setSendStatusCsmap(long sendStatusCsmap) {
		_sendStatusCsmap = sendStatusCsmap;
	}

	public long getSendStatusTwitter() {
		return _sendStatusTwitter;
	}

	public void setSendStatusTwitter(long sendStatusTwitter) {
		_sendStatusTwitter = sendStatusTwitter;
	}

	public long getSendStatusMonst() {
		return _sendStatusMonst;
	}

	public void setSendStatusMonst(long sendStatusMonst) {
		_sendStatusMonst = sendStatusMonst;
	}

	public long getSendStatusMail() {
		return _sendStatusMail;
	}

	public void setSendStatusMail(long sendStatusMail) {
		_sendStatusMail = sendStatusMail;
	}

	public long getSendStatusSegur() {
		return _sendStatusSegur;
	}

	public void setSendStatusSegur(long sendStatusSegur) {
		_sendStatusSegur = sendStatusSegur;
	}

	public Boolean getIsSend() {
		return _isSend;
	}

	public void setIsSend(Boolean isSend) {
		_isSend = isSend;
	}

	private String _uuid;
	private long _notificationId;
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
	private long _serviceId;
	private int _isAlert;
	private long _natureId;
	private String _title;
	private String _subtitle;
	private Date _startDate;
	private Date _endDate;
	private Date _broadcastDate;
	private long _messageId;
	private String _content;
	private String _labelUrl;
	private String _url;
	private long _typeBroadcast;
	private long _district;
	private String _broadcastChannels;
	private long _sendStatusCsmap;
	private long _sendStatusTwitter;
	private long _sendStatusMonst;
	private long _sendStatusMail;
	private long _sendStatusSegur;
	private Boolean _isSend;

}