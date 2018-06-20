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

package eu.strasbourg.service.place.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import eu.strasbourg.service.place.model.ScheduleException;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ScheduleException in entity cache.
 *
 * @author Angelique Zunino Champougny
 * @see ScheduleException
 * @generated
 */
@ProviderType
public class ScheduleExceptionCacheModel implements CacheModel<ScheduleException>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ScheduleExceptionCacheModel)) {
			return false;
		}

		ScheduleExceptionCacheModel scheduleExceptionCacheModel = (ScheduleExceptionCacheModel)obj;

		if (exceptionId == scheduleExceptionCacheModel.exceptionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, exceptionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", exceptionId=");
		sb.append(exceptionId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", openingTimes=");
		sb.append(openingTimes);
		sb.append(", firstComment=");
		sb.append(firstComment);
		sb.append(", secondComment=");
		sb.append(secondComment);
		sb.append(", thirdComment=");
		sb.append(thirdComment);
		sb.append(", fourthComment=");
		sb.append(fourthComment);
		sb.append(", fifthComment=");
		sb.append(fifthComment);
		sb.append(", comment=");
		sb.append(comment);
		sb.append(", closed=");
		sb.append(closed);
		sb.append(", placeId=");
		sb.append(placeId);
		sb.append(", subPlaceId=");
		sb.append(subPlaceId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ScheduleException toEntityModel() {
		ScheduleExceptionImpl scheduleExceptionImpl = new ScheduleExceptionImpl();

		if (uuid == null) {
			scheduleExceptionImpl.setUuid(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setUuid(uuid);
		}

		scheduleExceptionImpl.setExceptionId(exceptionId);

		if (startDate == Long.MIN_VALUE) {
			scheduleExceptionImpl.setStartDate(null);
		}
		else {
			scheduleExceptionImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			scheduleExceptionImpl.setEndDate(null);
		}
		else {
			scheduleExceptionImpl.setEndDate(new Date(endDate));
		}

		if (openingTimes == null) {
			scheduleExceptionImpl.setOpeningTimes(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setOpeningTimes(openingTimes);
		}

		if (firstComment == null) {
			scheduleExceptionImpl.setFirstComment(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setFirstComment(firstComment);
		}

		if (secondComment == null) {
			scheduleExceptionImpl.setSecondComment(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setSecondComment(secondComment);
		}

		if (thirdComment == null) {
			scheduleExceptionImpl.setThirdComment(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setThirdComment(thirdComment);
		}

		if (fourthComment == null) {
			scheduleExceptionImpl.setFourthComment(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setFourthComment(fourthComment);
		}

		if (fifthComment == null) {
			scheduleExceptionImpl.setFifthComment(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setFifthComment(fifthComment);
		}

		if (comment == null) {
			scheduleExceptionImpl.setComment(StringPool.BLANK);
		}
		else {
			scheduleExceptionImpl.setComment(comment);
		}

		scheduleExceptionImpl.setClosed(closed);
		scheduleExceptionImpl.setPlaceId(placeId);
		scheduleExceptionImpl.setSubPlaceId(subPlaceId);

		scheduleExceptionImpl.resetOriginalValues();

		return scheduleExceptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		exceptionId = objectInput.readLong();
		startDate = objectInput.readLong();
		endDate = objectInput.readLong();
		openingTimes = objectInput.readUTF();
		firstComment = objectInput.readUTF();
		secondComment = objectInput.readUTF();
		thirdComment = objectInput.readUTF();
		fourthComment = objectInput.readUTF();
		fifthComment = objectInput.readUTF();
		comment = objectInput.readUTF();

		closed = objectInput.readBoolean();

		placeId = objectInput.readLong();

		subPlaceId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(exceptionId);
		objectOutput.writeLong(startDate);
		objectOutput.writeLong(endDate);

		if (openingTimes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(openingTimes);
		}

		if (firstComment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(firstComment);
		}

		if (secondComment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(secondComment);
		}

		if (thirdComment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(thirdComment);
		}

		if (fourthComment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fourthComment);
		}

		if (fifthComment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fifthComment);
		}

		if (comment == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(comment);
		}

		objectOutput.writeBoolean(closed);

		objectOutput.writeLong(placeId);

		objectOutput.writeLong(subPlaceId);
	}

	public String uuid;
	public long exceptionId;
	public long startDate;
	public long endDate;
	public String openingTimes;
	public String firstComment;
	public String secondComment;
	public String thirdComment;
	public String fourthComment;
	public String fifthComment;
	public String comment;
	public boolean closed;
	public long placeId;
	public long subPlaceId;
}