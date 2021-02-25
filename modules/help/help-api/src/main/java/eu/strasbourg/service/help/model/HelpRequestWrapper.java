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

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;
import org.osgi.annotation.versioning.ProviderType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HelpRequest}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequest
 * @generated
 */
@ProviderType
public class HelpRequestWrapper
	extends BaseModelWrapper<HelpRequest>
	implements HelpRequest, ModelWrapper<HelpRequest> {

	public HelpRequestWrapper(HelpRequest helpRequest) {
		super(helpRequest);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("helpRequestId", getHelpRequestId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("publikId", getPublikId());
		attributes.put("helpProposalId", getHelpProposalId());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("message", getMessage());
		attributes.put("studentCardImageId", getStudentCardImageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long helpRequestId = (Long)attributes.get("helpRequestId");

		if (helpRequestId != null) {
			setHelpRequestId(helpRequestId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String publikId = (String)attributes.get("publikId");

		if (publikId != null) {
			setPublikId(publikId);
		}

		Long helpProposalId = (Long)attributes.get("helpProposalId");

		if (helpProposalId != null) {
			setHelpProposalId(helpProposalId);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		Long studentCardImageId = (Long)attributes.get("studentCardImageId");

		if (studentCardImageId != null) {
			setStudentCardImageId(studentCardImageId);
		}
	}

	/**
	 * Retourne l'utilisateur Publik depositaire
	 *
	 * @return
	 */
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser getAuthor() {
		return model.getAuthor();
	}

	/**
	 * Retourne l'URL de l'image de l'utilisateur
	 */
	@Override
	public String getAuthorImageURL() {
		return model.getAuthorImageURL();
	}

	/**
	 * Retourne le nom de du depositaire sous forme "Truc M." ou le "Au nom de ..."
	 */
	@Override
	public String getAuthorLabel() {
		return model.getAuthorLabel();
	}

	/**
	 * Returns the company ID of this help request.
	 *
	 * @return the company ID of this help request
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this help request.
	 *
	 * @return the create date of this help request
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Retourne le message d'accompagnement sans les balises et autres fioritures
	 *
	 * @return
	 */
	@Override
	public String getFormatedMessage() {
		return model.getFormatedMessage();
	}

	/**
	 * Returns the group ID of this help request.
	 *
	 * @return the group ID of this help request
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Retourne la proposition d'aide de la demande
	 *
	 * @return
	 * @throws PortalException
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal getHelpProposal() {
		return model.getHelpProposal();
	}

	/**
	 * Returns the help proposal ID of this help request.
	 *
	 * @return the help proposal ID of this help request
	 */
	@Override
	public long getHelpProposalId() {
		return model.getHelpProposalId();
	}

	/**
	 * Returns the help request ID of this help request.
	 *
	 * @return the help request ID of this help request
	 */
	@Override
	public long getHelpRequestId() {
		return model.getHelpRequestId();
	}

	/**
	 * Returns the message of this help request.
	 *
	 * @return the message of this help request
	 */
	@Override
	public String getMessage() {
		return model.getMessage();
	}

	/**
	 * Returns the modified date of this help request.
	 *
	 * @return the modified date of this help request
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the phone number of this help request.
	 *
	 * @return the phone number of this help request
	 */
	@Override
	public String getPhoneNumber() {
		return model.getPhoneNumber();
	}

	/**
	 * Returns the primary key of this help request.
	 *
	 * @return the primary key of this help request
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the publik ID of this help request.
	 *
	 * @return the publik ID of this help request
	 */
	@Override
	public String getPublikId() {
		return model.getPublikId();
	}

	/**
	 * Returns the status of this help request.
	 *
	 * @return the status of this help request
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this help request.
	 *
	 * @return the status by user ID of this help request
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this help request.
	 *
	 * @return the status by user name of this help request
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this help request.
	 *
	 * @return the status by user uuid of this help request
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this help request.
	 *
	 * @return the status date of this help request
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the student card image ID of this help request.
	 *
	 * @return the student card image ID of this help request
	 */
	@Override
	public long getStudentCardImageId() {
		return model.getStudentCardImageId();
	}

	/**
	 * Returns the user ID of this help request.
	 *
	 * @return the user ID of this help request
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this help request.
	 *
	 * @return the user name of this help request
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this help request.
	 *
	 * @return the user uuid of this help request
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this help request.
	 *
	 * @return the uuid of this help request
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this help request is approved.
	 *
	 * @return <code>true</code> if this help request is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this help request is denied.
	 *
	 * @return <code>true</code> if this help request is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this help request is a draft.
	 *
	 * @return <code>true</code> if this help request is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this help request is expired.
	 *
	 * @return <code>true</code> if this help request is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this help request is inactive.
	 *
	 * @return <code>true</code> if this help request is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this help request is incomplete.
	 *
	 * @return <code>true</code> if this help request is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this help request is pending.
	 *
	 * @return <code>true</code> if this help request is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this help request is scheduled.
	 *
	 * @return <code>true</code> if this help request is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this help request.
	 *
	 * @param companyId the company ID of this help request
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this help request.
	 *
	 * @param createDate the create date of this help request
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this help request.
	 *
	 * @param groupId the group ID of this help request
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the help proposal ID of this help request.
	 *
	 * @param helpProposalId the help proposal ID of this help request
	 */
	@Override
	public void setHelpProposalId(long helpProposalId) {
		model.setHelpProposalId(helpProposalId);
	}

	/**
	 * Sets the help request ID of this help request.
	 *
	 * @param helpRequestId the help request ID of this help request
	 */
	@Override
	public void setHelpRequestId(long helpRequestId) {
		model.setHelpRequestId(helpRequestId);
	}

	/**
	 * Sets the message of this help request.
	 *
	 * @param message the message of this help request
	 */
	@Override
	public void setMessage(String message) {
		model.setMessage(message);
	}

	/**
	 * Sets the modified date of this help request.
	 *
	 * @param modifiedDate the modified date of this help request
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the phone number of this help request.
	 *
	 * @param phoneNumber the phone number of this help request
	 */
	@Override
	public void setPhoneNumber(String phoneNumber) {
		model.setPhoneNumber(phoneNumber);
	}

	/**
	 * Sets the primary key of this help request.
	 *
	 * @param primaryKey the primary key of this help request
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the publik ID of this help request.
	 *
	 * @param publikId the publik ID of this help request
	 */
	@Override
	public void setPublikId(String publikId) {
		model.setPublikId(publikId);
	}

	/**
	 * Sets the status of this help request.
	 *
	 * @param status the status of this help request
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this help request.
	 *
	 * @param statusByUserId the status by user ID of this help request
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this help request.
	 *
	 * @param statusByUserName the status by user name of this help request
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this help request.
	 *
	 * @param statusByUserUuid the status by user uuid of this help request
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this help request.
	 *
	 * @param statusDate the status date of this help request
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the student card image ID of this help request.
	 *
	 * @param studentCardImageId the student card image ID of this help request
	 */
	@Override
	public void setStudentCardImageId(long studentCardImageId) {
		model.setStudentCardImageId(studentCardImageId);
	}

	/**
	 * Sets the user ID of this help request.
	 *
	 * @param userId the user ID of this help request
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this help request.
	 *
	 * @param userName the user name of this help request
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this help request.
	 *
	 * @param userUuid the user uuid of this help request
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this help request.
	 *
	 * @param uuid the uuid of this help request
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected HelpRequestWrapper wrap(HelpRequest helpRequest) {
		return new HelpRequestWrapper(helpRequest);
	}

}