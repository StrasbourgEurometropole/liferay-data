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

package eu.strasbourg.service.notif.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.notif.model.Notification;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Notification in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class NotificationCacheModel
	implements CacheModel<Notification>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof NotificationCacheModel)) {
			return false;
		}

		NotificationCacheModel notificationCacheModel =
			(NotificationCacheModel)obj;

		if (notificationId == notificationCacheModel.notificationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, notificationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(65);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", notificationId=");
		sb.append(notificationId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", serviceId=");
		sb.append(serviceId);
		sb.append(", isAlert=");
		sb.append(isAlert);
		sb.append(", natureId=");
		sb.append(natureId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", subtitle=");
		sb.append(subtitle);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", broadcastDate=");
		sb.append(broadcastDate);
		sb.append(", messageId=");
		sb.append(messageId);
		sb.append(", content=");
		sb.append(content);
		sb.append(", labelUrl=");
		sb.append(labelUrl);
		sb.append(", url=");
		sb.append(url);
		sb.append(", typeBroadcast=");
		sb.append(typeBroadcast);
		sb.append(", district=");
		sb.append(district);
		sb.append(", broadcastChannels=");
		sb.append(broadcastChannels);
		sb.append(", sendStatusCsmap=");
		sb.append(sendStatusCsmap);
		sb.append(", sendStatusTwitter=");
		sb.append(sendStatusTwitter);
		sb.append(", sendStatusMonst=");
		sb.append(sendStatusMonst);
		sb.append(", sendStatusMail=");
		sb.append(sendStatusMail);
		sb.append(", sendStatusSegur=");
		sb.append(sendStatusSegur);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Notification toEntityModel() {
		NotificationImpl notificationImpl = new NotificationImpl();

		if (uuid == null) {
			notificationImpl.setUuid("");
		}
		else {
			notificationImpl.setUuid(uuid);
		}

		notificationImpl.setNotificationId(notificationId);
		notificationImpl.setGroupId(groupId);
		notificationImpl.setCompanyId(companyId);
		notificationImpl.setUserId(userId);

		if (userName == null) {
			notificationImpl.setUserName("");
		}
		else {
			notificationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			notificationImpl.setCreateDate(null);
		}
		else {
			notificationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			notificationImpl.setModifiedDate(null);
		}
		else {
			notificationImpl.setModifiedDate(new Date(modifiedDate));
		}

		notificationImpl.setStatus(status);
		notificationImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			notificationImpl.setStatusByUserName("");
		}
		else {
			notificationImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			notificationImpl.setStatusDate(null);
		}
		else {
			notificationImpl.setStatusDate(new Date(statusDate));
		}

		notificationImpl.setServiceId(serviceId);
		notificationImpl.setIsAlert(isAlert);
		notificationImpl.setNatureId(natureId);

		if (title == null) {
			notificationImpl.setTitle("");
		}
		else {
			notificationImpl.setTitle(title);
		}

		if (subtitle == null) {
			notificationImpl.setSubtitle("");
		}
		else {
			notificationImpl.setSubtitle(subtitle);
		}

		if (startDate == Long.MIN_VALUE) {
			notificationImpl.setStartDate(null);
		}
		else {
			notificationImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			notificationImpl.setEndDate(null);
		}
		else {
			notificationImpl.setEndDate(new Date(endDate));
		}

		if (broadcastDate == Long.MIN_VALUE) {
			notificationImpl.setBroadcastDate(null);
		}
		else {
			notificationImpl.setBroadcastDate(new Date(broadcastDate));
		}

		notificationImpl.setMessageId(messageId);

		if (content == null) {
			notificationImpl.setContent("");
		}
		else {
			notificationImpl.setContent(content);
		}

		if (labelUrl == null) {
			notificationImpl.setLabelUrl("");
		}
		else {
			notificationImpl.setLabelUrl(labelUrl);
		}

		if (url == null) {
			notificationImpl.setUrl("");
		}
		else {
			notificationImpl.setUrl(url);
		}

		notificationImpl.setTypeBroadcast(typeBroadcast);
		notificationImpl.setDistrict(district);

		if (broadcastChannels == null) {
			notificationImpl.setBroadcastChannels("");
		}
		else {
			notificationImpl.setBroadcastChannels(broadcastChannels);
		}

		notificationImpl.setSendStatusCsmap(sendStatusCsmap);
		notificationImpl.setSendStatusTwitter(sendStatusTwitter);
		notificationImpl.setSendStatusMonst(sendStatusMonst);
		notificationImpl.setSendStatusMail(sendStatusMail);
		notificationImpl.setSendStatusSegur(sendStatusSegur);

		notificationImpl.resetOriginalValues();

		return notificationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		notificationId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();

		serviceId = objectInput.readLong();

		isAlert = objectInput.readInt();

		natureId = objectInput.readLong();
		title = objectInput.readUTF();
		subtitle = objectInput.readUTF();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		broadcastDate = objectInput.readLong();

		messageId = objectInput.readLong();
		content = objectInput.readUTF();
		labelUrl = objectInput.readUTF();
		url = objectInput.readUTF();

		typeBroadcast = objectInput.readLong();

		district = objectInput.readLong();
		broadcastChannels = objectInput.readUTF();

		sendStatusCsmap = objectInput.readLong();

		sendStatusTwitter = objectInput.readLong();

		sendStatusMonst = objectInput.readLong();

		sendStatusMail = objectInput.readLong();

		sendStatusSegur = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(notificationId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		objectOutput.writeLong(serviceId);

		objectOutput.writeInt(isAlert);

		objectOutput.writeLong(natureId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (subtitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subtitle);
		}

		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);
		objectOutput.writeLong(broadcastDate);

		objectOutput.writeLong(messageId);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		if (labelUrl == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(labelUrl);
		}

		if (url == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeLong(typeBroadcast);

		objectOutput.writeLong(district);

		if (broadcastChannels == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(broadcastChannels);
		}

		objectOutput.writeLong(sendStatusCsmap);

		objectOutput.writeLong(sendStatusTwitter);

		objectOutput.writeLong(sendStatusMonst);

		objectOutput.writeLong(sendStatusMail);

		objectOutput.writeLong(sendStatusSegur);
	}

	public String uuid;
	public long notificationId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public long serviceId;
	public int isAlert;
	public long natureId;
	public String title;
	public String subtitle;
	public long startDate;
	public long endDate;
	public long broadcastDate;
	public long messageId;
	public String content;
	public String labelUrl;
	public String url;
	public long typeBroadcast;
	public long district;
	public String broadcastChannels;
	public long sendStatusCsmap;
	public long sendStatusTwitter;
	public long sendStatusMonst;
	public long sendStatusMail;
	public long sendStatusSegur;

}