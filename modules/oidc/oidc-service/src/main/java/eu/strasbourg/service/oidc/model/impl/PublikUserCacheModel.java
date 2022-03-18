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

package eu.strasbourg.service.oidc.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import eu.strasbourg.service.oidc.model.PublikUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PublikUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class PublikUserCacheModel
	implements CacheModel<PublikUser>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PublikUserCacheModel)) {
			return false;
		}

		PublikUserCacheModel publikUserCacheModel = (PublikUserCacheModel)obj;

		if (publikUserLiferayId == publikUserCacheModel.publikUserLiferayId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, publikUserLiferayId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", publikUserLiferayId=");
		sb.append(publikUserLiferayId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", publikId=");
		sb.append(publikId);
		sb.append(", accessToken=");
		sb.append(accessToken);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", mapConfig=");
		sb.append(mapConfig);
		sb.append(", displayConfig=");
		sb.append(displayConfig);
		sb.append(", pactSignature=");
		sb.append(pactSignature);
		sb.append(", banishDate=");
		sb.append(banishDate);
		sb.append(", banishDescription=");
		sb.append(banishDescription);
		sb.append(", imageURL=");
		sb.append(imageURL);
		sb.append(", pactDisplay=");
		sb.append(pactDisplay);
		sb.append(", csmapJSON=");
		sb.append(csmapJSON);
		sb.append(", modifiedDateJSON=");
		sb.append(modifiedDateJSON);
		sb.append(", topicsFCM=");
		sb.append(topicsFCM);
		sb.append(", lastUpdateTimeTopics=");
		sb.append(lastUpdateTimeTopics);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PublikUser toEntityModel() {
		PublikUserImpl publikUserImpl = new PublikUserImpl();

		if (uuid == null) {
			publikUserImpl.setUuid("");
		}
		else {
			publikUserImpl.setUuid(uuid);
		}

		publikUserImpl.setPublikUserLiferayId(publikUserLiferayId);

		if (createDate == Long.MIN_VALUE) {
			publikUserImpl.setCreateDate(null);
		}
		else {
			publikUserImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			publikUserImpl.setModifiedDate(null);
		}
		else {
			publikUserImpl.setModifiedDate(new Date(modifiedDate));
		}

		publikUserImpl.setUserId(userId);

		if (userName == null) {
			publikUserImpl.setUserName("");
		}
		else {
			publikUserImpl.setUserName(userName);
		}

		if (publikId == null) {
			publikUserImpl.setPublikId("");
		}
		else {
			publikUserImpl.setPublikId(publikId);
		}

		if (accessToken == null) {
			publikUserImpl.setAccessToken("");
		}
		else {
			publikUserImpl.setAccessToken(accessToken);
		}

		if (firstName == null) {
			publikUserImpl.setFirstName("");
		}
		else {
			publikUserImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			publikUserImpl.setLastName("");
		}
		else {
			publikUserImpl.setLastName(lastName);
		}

		if (email == null) {
			publikUserImpl.setEmail("");
		}
		else {
			publikUserImpl.setEmail(email);
		}

		if (mapConfig == null) {
			publikUserImpl.setMapConfig("");
		}
		else {
			publikUserImpl.setMapConfig(mapConfig);
		}

		if (displayConfig == null) {
			publikUserImpl.setDisplayConfig("");
		}
		else {
			publikUserImpl.setDisplayConfig(displayConfig);
		}

		if (pactSignature == Long.MIN_VALUE) {
			publikUserImpl.setPactSignature(null);
		}
		else {
			publikUserImpl.setPactSignature(new Date(pactSignature));
		}

		if (banishDate == Long.MIN_VALUE) {
			publikUserImpl.setBanishDate(null);
		}
		else {
			publikUserImpl.setBanishDate(new Date(banishDate));
		}

		if (banishDescription == null) {
			publikUserImpl.setBanishDescription("");
		}
		else {
			publikUserImpl.setBanishDescription(banishDescription);
		}

		if (imageURL == null) {
			publikUserImpl.setImageURL("");
		}
		else {
			publikUserImpl.setImageURL(imageURL);
		}

		publikUserImpl.setPactDisplay(pactDisplay);

		if (csmapJSON == null) {
			publikUserImpl.setCsmapJSON("");
		}
		else {
			publikUserImpl.setCsmapJSON(csmapJSON);
		}

		if (modifiedDateJSON == Long.MIN_VALUE) {
			publikUserImpl.setModifiedDateJSON(null);
		}
		else {
			publikUserImpl.setModifiedDateJSON(new Date(modifiedDateJSON));
		}

		if (topicsFCM == null) {
			publikUserImpl.setTopicsFCM("");
		}
		else {
			publikUserImpl.setTopicsFCM(topicsFCM);
		}

		publikUserImpl.setLastUpdateTimeTopics(lastUpdateTimeTopics);

		publikUserImpl.resetOriginalValues();

		return publikUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		publikUserLiferayId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		publikId = objectInput.readUTF();
		accessToken = objectInput.readUTF();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		email = objectInput.readUTF();
		mapConfig = objectInput.readUTF();
		displayConfig = objectInput.readUTF();
		pactSignature = objectInput.readLong();
		banishDate = objectInput.readLong();
		banishDescription = objectInput.readUTF();
		imageURL = objectInput.readUTF();

		pactDisplay = objectInput.readBoolean();
		csmapJSON = objectInput.readUTF();
		modifiedDateJSON = objectInput.readLong();
		topicsFCM = objectInput.readUTF();

		lastUpdateTimeTopics = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(publikUserLiferayId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		if (publikId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(publikId);
		}

		if (accessToken == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accessToken);
		}

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (mapConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(mapConfig);
		}

		if (displayConfig == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(displayConfig);
		}

		objectOutput.writeLong(pactSignature);
		objectOutput.writeLong(banishDate);

		if (banishDescription == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(banishDescription);
		}

		if (imageURL == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(imageURL);
		}

		objectOutput.writeBoolean(pactDisplay);

		if (csmapJSON == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(csmapJSON);
		}

		objectOutput.writeLong(modifiedDateJSON);

		if (topicsFCM == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(topicsFCM);
		}

		objectOutput.writeLong(lastUpdateTimeTopics);
	}

	public String uuid;
	public long publikUserLiferayId;
	public long createDate;
	public long modifiedDate;
	public long userId;
	public String userName;
	public String publikId;
	public String accessToken;
	public String firstName;
	public String lastName;
	public String email;
	public String mapConfig;
	public String displayConfig;
	public long pactSignature;
	public long banishDate;
	public String banishDescription;
	public String imageURL;
	public boolean pactDisplay;
	public String csmapJSON;
	public long modifiedDateJSON;
	public String topicsFCM;
	public long lastUpdateTimeTopics;

}