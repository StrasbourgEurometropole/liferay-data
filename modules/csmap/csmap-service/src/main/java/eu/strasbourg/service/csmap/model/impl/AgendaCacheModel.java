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

package eu.strasbourg.service.csmap.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import eu.strasbourg.service.csmap.model.Agenda;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Agenda in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AgendaCacheModel implements CacheModel<Agenda>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AgendaCacheModel)) {
			return false;
		}

		AgendaCacheModel agendaCacheModel = (AgendaCacheModel)object;

		if (agendaId == agendaCacheModel.agendaId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, agendaId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", agendaId=");
		sb.append(agendaId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", editorialTitle=");
		sb.append(editorialTitle);
		sb.append(", subtitle=");
		sb.append(subtitle);
		sb.append(", imageId=");
		sb.append(imageId);
		sb.append(", labelLink=");
		sb.append(labelLink);
		sb.append(", link=");
		sb.append(link);
		sb.append(", publicationStartDate=");
		sb.append(publicationStartDate);
		sb.append(", publicationEndDate=");
		sb.append(publicationEndDate);
		sb.append(", isPrincipal=");
		sb.append(isPrincipal);
		sb.append(", isActive=");
		sb.append(isActive);
		sb.append(", campaignsIds=");
		sb.append(campaignsIds);
		sb.append(", themesIds=");
		sb.append(themesIds);
		sb.append(", typesIds=");
		sb.append(typesIds);
		sb.append(", territoriesIds=");
		sb.append(territoriesIds);
		sb.append(", tags=");
		sb.append(tags);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Agenda toEntityModel() {
		AgendaImpl agendaImpl = new AgendaImpl();

		if (uuid == null) {
			agendaImpl.setUuid("");
		}
		else {
			agendaImpl.setUuid(uuid);
		}

		agendaImpl.setAgendaId(agendaId);

		if (title == null) {
			agendaImpl.setTitle("");
		}
		else {
			agendaImpl.setTitle(title);
		}

		if (editorialTitle == null) {
			agendaImpl.setEditorialTitle("");
		}
		else {
			agendaImpl.setEditorialTitle(editorialTitle);
		}

		if (subtitle == null) {
			agendaImpl.setSubtitle("");
		}
		else {
			agendaImpl.setSubtitle(subtitle);
		}

		agendaImpl.setImageId(imageId);

		if (labelLink == null) {
			agendaImpl.setLabelLink("");
		}
		else {
			agendaImpl.setLabelLink(labelLink);
		}

		if (link == null) {
			agendaImpl.setLink("");
		}
		else {
			agendaImpl.setLink(link);
		}

		if (publicationStartDate == Long.MIN_VALUE) {
			agendaImpl.setPublicationStartDate(null);
		}
		else {
			agendaImpl.setPublicationStartDate(new Date(publicationStartDate));
		}

		if (publicationEndDate == Long.MIN_VALUE) {
			agendaImpl.setPublicationEndDate(null);
		}
		else {
			agendaImpl.setPublicationEndDate(new Date(publicationEndDate));
		}

		agendaImpl.setIsPrincipal(isPrincipal);
		agendaImpl.setIsActive(isActive);

		if (campaignsIds == null) {
			agendaImpl.setCampaignsIds("");
		}
		else {
			agendaImpl.setCampaignsIds(campaignsIds);
		}

		if (themesIds == null) {
			agendaImpl.setThemesIds("");
		}
		else {
			agendaImpl.setThemesIds(themesIds);
		}

		if (typesIds == null) {
			agendaImpl.setTypesIds("");
		}
		else {
			agendaImpl.setTypesIds(typesIds);
		}

		if (territoriesIds == null) {
			agendaImpl.setTerritoriesIds("");
		}
		else {
			agendaImpl.setTerritoriesIds(territoriesIds);
		}

		if (tags == null) {
			agendaImpl.setTags("");
		}
		else {
			agendaImpl.setTags(tags);
		}

		agendaImpl.resetOriginalValues();

		return agendaImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		agendaId = objectInput.readLong();
		title = objectInput.readUTF();
		editorialTitle = objectInput.readUTF();
		subtitle = objectInput.readUTF();

		imageId = objectInput.readLong();
		labelLink = objectInput.readUTF();
		link = objectInput.readUTF();
		publicationStartDate = objectInput.readLong();
		publicationEndDate = objectInput.readLong();

		isPrincipal = objectInput.readBoolean();

		isActive = objectInput.readBoolean();
		campaignsIds = objectInput.readUTF();
		themesIds = objectInput.readUTF();
		typesIds = objectInput.readUTF();
		territoriesIds = objectInput.readUTF();
		tags = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(agendaId);

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (editorialTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(editorialTitle);
		}

		if (subtitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(subtitle);
		}

		objectOutput.writeLong(imageId);

		if (labelLink == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(labelLink);
		}

		if (link == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(link);
		}

		objectOutput.writeLong(publicationStartDate);
		objectOutput.writeLong(publicationEndDate);

		objectOutput.writeBoolean(isPrincipal);

		objectOutput.writeBoolean(isActive);

		if (campaignsIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(campaignsIds);
		}

		if (themesIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(themesIds);
		}

		if (typesIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(typesIds);
		}

		if (territoriesIds == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(territoriesIds);
		}

		if (tags == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(tags);
		}
	}

	public String uuid;
	public long agendaId;
	public String title;
	public String editorialTitle;
	public String subtitle;
	public long imageId;
	public String labelLink;
	public String link;
	public long publicationStartDate;
	public long publicationEndDate;
	public boolean isPrincipal;
	public boolean isActive;
	public String campaignsIds;
	public String themesIds;
	public String typesIds;
	public String territoriesIds;
	public String tags;

}