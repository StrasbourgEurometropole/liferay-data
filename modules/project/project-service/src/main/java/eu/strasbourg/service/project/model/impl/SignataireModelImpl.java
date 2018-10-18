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

package eu.strasbourg.service.project.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.model.SignataireModel;
import eu.strasbourg.service.project.model.SignataireSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Signataire service. Represents a row in the &quot;project_Signataire&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SignataireModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SignataireImpl}.
 * </p>
 *
 * @author Cedric Henry
 * @see SignataireImpl
 * @see Signataire
 * @see SignataireModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SignataireModelImpl extends BaseModelImpl<Signataire>
	implements SignataireModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a signataire model instance should use the {@link Signataire} interface instead.
	 */
	public static final String TABLE_NAME = "project_Signataire";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "signataireId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP },
			{ "signataireName", Types.VARCHAR },
			{ "signataireFirstname", Types.VARCHAR },
			{ "birthday", Types.TIMESTAMP },
			{ "address", Types.VARCHAR },
			{ "mail", Types.VARCHAR },
			{ "postalCode", Types.BIGINT },
			{ "mobilePhone", Types.VARCHAR },
			{ "phone", Types.VARCHAR },
			{ "city", Types.VARCHAR },
			{ "signatureDate", Types.TIMESTAMP },
			{ "publikUserId", Types.VARCHAR },
			{ "petitionId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("signataireId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("signataireName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("signataireFirstname", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("birthday", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("address", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("mail", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("postalCode", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("mobilePhone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("phone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("city", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("signatureDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("publikUserId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("petitionId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table project_Signataire (uuid_ VARCHAR(75) null,signataireId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null,signataireName VARCHAR(75) null,signataireFirstname VARCHAR(75) null,birthday DATE null,address VARCHAR(75) null,mail VARCHAR(75) null,postalCode LONG,mobilePhone VARCHAR(75) null,phone VARCHAR(75) null,city VARCHAR(75) null,signatureDate DATE null,publikUserId VARCHAR(75) null,petitionId LONG)";
	public static final String TABLE_SQL_DROP = "drop table project_Signataire";
	public static final String ORDER_BY_JPQL = " ORDER BY signataire.signataireId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY project_Signataire.signataireId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.eu.strasbourg.service.project.model.Signataire"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.eu.strasbourg.service.project.model.Signataire"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.eu.strasbourg.service.project.model.Signataire"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long PETITIONID_COLUMN_BITMASK = 4L;
	public static final long PUBLIKUSERID_COLUMN_BITMASK = 8L;
	public static final long SIGNATAIRENAME_COLUMN_BITMASK = 16L;
	public static final long UUID_COLUMN_BITMASK = 32L;
	public static final long SIGNATAIREID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Signataire toModel(SignataireSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Signataire model = new SignataireImpl();

		model.setUuid(soapModel.getUuid());
		model.setSignataireId(soapModel.getSignataireId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());
		model.setSignataireName(soapModel.getSignataireName());
		model.setSignataireFirstname(soapModel.getSignataireFirstname());
		model.setBirthday(soapModel.getBirthday());
		model.setAddress(soapModel.getAddress());
		model.setMail(soapModel.getMail());
		model.setPostalCode(soapModel.getPostalCode());
		model.setMobilePhone(soapModel.getMobilePhone());
		model.setPhone(soapModel.getPhone());
		model.setCity(soapModel.getCity());
		model.setSignatureDate(soapModel.getSignatureDate());
		model.setPublikUserId(soapModel.getPublikUserId());
		model.setPetitionId(soapModel.getPetitionId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Signataire> toModels(SignataireSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Signataire> models = new ArrayList<Signataire>(soapModels.length);

		for (SignataireSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(eu.strasbourg.service.project.service.util.PropsUtil.get(
				"lock.expiration.time.eu.strasbourg.service.project.model.Signataire"));

	public SignataireModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _signataireId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSignataireId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _signataireId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Signataire.class;
	}

	@Override
	public String getModelClassName() {
		return Signataire.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("signataireId", getSignataireId());
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
		attributes.put("signataireName", getSignataireName());
		attributes.put("signataireFirstname", getSignataireFirstname());
		attributes.put("birthday", getBirthday());
		attributes.put("address", getAddress());
		attributes.put("mail", getMail());
		attributes.put("postalCode", getPostalCode());
		attributes.put("mobilePhone", getMobilePhone());
		attributes.put("phone", getPhone());
		attributes.put("city", getCity());
		attributes.put("signatureDate", getSignatureDate());
		attributes.put("publikUserId", getPublikUserId());
		attributes.put("petitionId", getPetitionId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long signataireId = (Long)attributes.get("signataireId");

		if (signataireId != null) {
			setSignataireId(signataireId);
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

		String signataireName = (String)attributes.get("signataireName");

		if (signataireName != null) {
			setSignataireName(signataireName);
		}

		String signataireFirstname = (String)attributes.get(
				"signataireFirstname");

		if (signataireFirstname != null) {
			setSignataireFirstname(signataireFirstname);
		}

		Date birthday = (Date)attributes.get("birthday");

		if (birthday != null) {
			setBirthday(birthday);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String mail = (String)attributes.get("mail");

		if (mail != null) {
			setMail(mail);
		}

		Long postalCode = (Long)attributes.get("postalCode");

		if (postalCode != null) {
			setPostalCode(postalCode);
		}

		String mobilePhone = (String)attributes.get("mobilePhone");

		if (mobilePhone != null) {
			setMobilePhone(mobilePhone);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		Date signatureDate = (Date)attributes.get("signatureDate");

		if (signatureDate != null) {
			setSignatureDate(signatureDate);
		}

		String publikUserId = (String)attributes.get("publikUserId");

		if (publikUserId != null) {
			setPublikUserId(publikUserId);
		}

		Long petitionId = (Long)attributes.get("petitionId");

		if (petitionId != null) {
			setPetitionId(petitionId);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getSignataireId() {
		return _signataireId;
	}

	@Override
	public void setSignataireId(long signataireId) {
		_signataireId = signataireId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return StringPool.BLANK;
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@JSON
	@Override
	public String getSignataireName() {
		if (_signataireName == null) {
			return StringPool.BLANK;
		}
		else {
			return _signataireName;
		}
	}

	@Override
	public void setSignataireName(String signataireName) {
		_columnBitmask |= SIGNATAIRENAME_COLUMN_BITMASK;

		if (_originalSignataireName == null) {
			_originalSignataireName = _signataireName;
		}

		_signataireName = signataireName;
	}

	public String getOriginalSignataireName() {
		return GetterUtil.getString(_originalSignataireName);
	}

	@JSON
	@Override
	public String getSignataireFirstname() {
		if (_signataireFirstname == null) {
			return StringPool.BLANK;
		}
		else {
			return _signataireFirstname;
		}
	}

	@Override
	public void setSignataireFirstname(String signataireFirstname) {
		_signataireFirstname = signataireFirstname;
	}

	@JSON
	@Override
	public Date getBirthday() {
		return _birthday;
	}

	@Override
	public void setBirthday(Date birthday) {
		_birthday = birthday;
	}

	@JSON
	@Override
	public String getAddress() {
		if (_address == null) {
			return StringPool.BLANK;
		}
		else {
			return _address;
		}
	}

	@Override
	public void setAddress(String address) {
		_address = address;
	}

	@JSON
	@Override
	public String getMail() {
		if (_mail == null) {
			return StringPool.BLANK;
		}
		else {
			return _mail;
		}
	}

	@Override
	public void setMail(String mail) {
		_mail = mail;
	}

	@JSON
	@Override
	public long getPostalCode() {
		return _postalCode;
	}

	@Override
	public void setPostalCode(long postalCode) {
		_postalCode = postalCode;
	}

	@JSON
	@Override
	public String getMobilePhone() {
		if (_mobilePhone == null) {
			return StringPool.BLANK;
		}
		else {
			return _mobilePhone;
		}
	}

	@Override
	public void setMobilePhone(String mobilePhone) {
		_mobilePhone = mobilePhone;
	}

	@JSON
	@Override
	public String getPhone() {
		if (_phone == null) {
			return StringPool.BLANK;
		}
		else {
			return _phone;
		}
	}

	@Override
	public void setPhone(String phone) {
		_phone = phone;
	}

	@JSON
	@Override
	public String getCity() {
		if (_city == null) {
			return StringPool.BLANK;
		}
		else {
			return _city;
		}
	}

	@Override
	public void setCity(String city) {
		_city = city;
	}

	@JSON
	@Override
	public Date getSignatureDate() {
		return _signatureDate;
	}

	@Override
	public void setSignatureDate(Date signatureDate) {
		_signatureDate = signatureDate;
	}

	@JSON
	@Override
	public String getPublikUserId() {
		if (_publikUserId == null) {
			return StringPool.BLANK;
		}
		else {
			return _publikUserId;
		}
	}

	@Override
	public void setPublikUserId(String publikUserId) {
		_columnBitmask |= PUBLIKUSERID_COLUMN_BITMASK;

		if (_originalPublikUserId == null) {
			_originalPublikUserId = _publikUserId;
		}

		_publikUserId = publikUserId;
	}

	public String getOriginalPublikUserId() {
		return GetterUtil.getString(_originalPublikUserId);
	}

	@JSON
	@Override
	public long getPetitionId() {
		return _petitionId;
	}

	@Override
	public void setPetitionId(long petitionId) {
		_columnBitmask |= PETITIONID_COLUMN_BITMASK;

		if (!_setOriginalPetitionId) {
			_setOriginalPetitionId = true;

			_originalPetitionId = _petitionId;
		}

		_petitionId = petitionId;
	}

	public long getOriginalPetitionId() {
		return _originalPetitionId;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Signataire.class.getName()));
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Signataire.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Signataire toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Signataire)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SignataireImpl signataireImpl = new SignataireImpl();

		signataireImpl.setUuid(getUuid());
		signataireImpl.setSignataireId(getSignataireId());
		signataireImpl.setGroupId(getGroupId());
		signataireImpl.setCompanyId(getCompanyId());
		signataireImpl.setUserId(getUserId());
		signataireImpl.setUserName(getUserName());
		signataireImpl.setCreateDate(getCreateDate());
		signataireImpl.setModifiedDate(getModifiedDate());
		signataireImpl.setStatus(getStatus());
		signataireImpl.setStatusByUserId(getStatusByUserId());
		signataireImpl.setStatusByUserName(getStatusByUserName());
		signataireImpl.setStatusDate(getStatusDate());
		signataireImpl.setSignataireName(getSignataireName());
		signataireImpl.setSignataireFirstname(getSignataireFirstname());
		signataireImpl.setBirthday(getBirthday());
		signataireImpl.setAddress(getAddress());
		signataireImpl.setMail(getMail());
		signataireImpl.setPostalCode(getPostalCode());
		signataireImpl.setMobilePhone(getMobilePhone());
		signataireImpl.setPhone(getPhone());
		signataireImpl.setCity(getCity());
		signataireImpl.setSignatureDate(getSignatureDate());
		signataireImpl.setPublikUserId(getPublikUserId());
		signataireImpl.setPetitionId(getPetitionId());

		signataireImpl.resetOriginalValues();

		return signataireImpl;
	}

	@Override
	public int compareTo(Signataire signataire) {
		long primaryKey = signataire.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Signataire)) {
			return false;
		}

		Signataire signataire = (Signataire)obj;

		long primaryKey = signataire.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SignataireModelImpl signataireModelImpl = this;

		signataireModelImpl._originalUuid = signataireModelImpl._uuid;

		signataireModelImpl._originalGroupId = signataireModelImpl._groupId;

		signataireModelImpl._setOriginalGroupId = false;

		signataireModelImpl._originalCompanyId = signataireModelImpl._companyId;

		signataireModelImpl._setOriginalCompanyId = false;

		signataireModelImpl._setModifiedDate = false;

		signataireModelImpl._originalSignataireName = signataireModelImpl._signataireName;

		signataireModelImpl._originalPublikUserId = signataireModelImpl._publikUserId;

		signataireModelImpl._originalPetitionId = signataireModelImpl._petitionId;

		signataireModelImpl._setOriginalPetitionId = false;

		signataireModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Signataire> toCacheModel() {
		SignataireCacheModel signataireCacheModel = new SignataireCacheModel();

		signataireCacheModel.uuid = getUuid();

		String uuid = signataireCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			signataireCacheModel.uuid = null;
		}

		signataireCacheModel.signataireId = getSignataireId();

		signataireCacheModel.groupId = getGroupId();

		signataireCacheModel.companyId = getCompanyId();

		signataireCacheModel.userId = getUserId();

		signataireCacheModel.userName = getUserName();

		String userName = signataireCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			signataireCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			signataireCacheModel.createDate = createDate.getTime();
		}
		else {
			signataireCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			signataireCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			signataireCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		signataireCacheModel.status = getStatus();

		signataireCacheModel.statusByUserId = getStatusByUserId();

		signataireCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = signataireCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			signataireCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			signataireCacheModel.statusDate = statusDate.getTime();
		}
		else {
			signataireCacheModel.statusDate = Long.MIN_VALUE;
		}

		signataireCacheModel.signataireName = getSignataireName();

		String signataireName = signataireCacheModel.signataireName;

		if ((signataireName != null) && (signataireName.length() == 0)) {
			signataireCacheModel.signataireName = null;
		}

		signataireCacheModel.signataireFirstname = getSignataireFirstname();

		String signataireFirstname = signataireCacheModel.signataireFirstname;

		if ((signataireFirstname != null) &&
				(signataireFirstname.length() == 0)) {
			signataireCacheModel.signataireFirstname = null;
		}

		Date birthday = getBirthday();

		if (birthday != null) {
			signataireCacheModel.birthday = birthday.getTime();
		}
		else {
			signataireCacheModel.birthday = Long.MIN_VALUE;
		}

		signataireCacheModel.address = getAddress();

		String address = signataireCacheModel.address;

		if ((address != null) && (address.length() == 0)) {
			signataireCacheModel.address = null;
		}

		signataireCacheModel.mail = getMail();

		String mail = signataireCacheModel.mail;

		if ((mail != null) && (mail.length() == 0)) {
			signataireCacheModel.mail = null;
		}

		signataireCacheModel.postalCode = getPostalCode();

		signataireCacheModel.mobilePhone = getMobilePhone();

		String mobilePhone = signataireCacheModel.mobilePhone;

		if ((mobilePhone != null) && (mobilePhone.length() == 0)) {
			signataireCacheModel.mobilePhone = null;
		}

		signataireCacheModel.phone = getPhone();

		String phone = signataireCacheModel.phone;

		if ((phone != null) && (phone.length() == 0)) {
			signataireCacheModel.phone = null;
		}

		signataireCacheModel.city = getCity();

		String city = signataireCacheModel.city;

		if ((city != null) && (city.length() == 0)) {
			signataireCacheModel.city = null;
		}

		Date signatureDate = getSignatureDate();

		if (signatureDate != null) {
			signataireCacheModel.signatureDate = signatureDate.getTime();
		}
		else {
			signataireCacheModel.signatureDate = Long.MIN_VALUE;
		}

		signataireCacheModel.publikUserId = getPublikUserId();

		String publikUserId = signataireCacheModel.publikUserId;

		if ((publikUserId != null) && (publikUserId.length() == 0)) {
			signataireCacheModel.publikUserId = null;
		}

		signataireCacheModel.petitionId = getPetitionId();

		return signataireCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(49);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", signataireId=");
		sb.append(getSignataireId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", statusByUserId=");
		sb.append(getStatusByUserId());
		sb.append(", statusByUserName=");
		sb.append(getStatusByUserName());
		sb.append(", statusDate=");
		sb.append(getStatusDate());
		sb.append(", signataireName=");
		sb.append(getSignataireName());
		sb.append(", signataireFirstname=");
		sb.append(getSignataireFirstname());
		sb.append(", birthday=");
		sb.append(getBirthday());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append(", mail=");
		sb.append(getMail());
		sb.append(", postalCode=");
		sb.append(getPostalCode());
		sb.append(", mobilePhone=");
		sb.append(getMobilePhone());
		sb.append(", phone=");
		sb.append(getPhone());
		sb.append(", city=");
		sb.append(getCity());
		sb.append(", signatureDate=");
		sb.append(getSignatureDate());
		sb.append(", publikUserId=");
		sb.append(getPublikUserId());
		sb.append(", petitionId=");
		sb.append(getPetitionId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(76);

		sb.append("<model><model-name>");
		sb.append("eu.strasbourg.service.project.model.Signataire");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signataireId</column-name><column-value><![CDATA[");
		sb.append(getSignataireId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserId</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusByUserName</column-name><column-value><![CDATA[");
		sb.append(getStatusByUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>statusDate</column-name><column-value><![CDATA[");
		sb.append(getStatusDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signataireName</column-name><column-value><![CDATA[");
		sb.append(getSignataireName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signataireFirstname</column-name><column-value><![CDATA[");
		sb.append(getSignataireFirstname());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>birthday</column-name><column-value><![CDATA[");
		sb.append(getBirthday());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mail</column-name><column-value><![CDATA[");
		sb.append(getMail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>postalCode</column-name><column-value><![CDATA[");
		sb.append(getPostalCode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mobilePhone</column-name><column-value><![CDATA[");
		sb.append(getMobilePhone());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>phone</column-name><column-value><![CDATA[");
		sb.append(getPhone());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>city</column-name><column-value><![CDATA[");
		sb.append(getCity());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signatureDate</column-name><column-value><![CDATA[");
		sb.append(getSignatureDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>publikUserId</column-name><column-value><![CDATA[");
		sb.append(getPublikUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>petitionId</column-name><column-value><![CDATA[");
		sb.append(getPetitionId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Signataire.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Signataire.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _signataireId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _signataireName;
	private String _originalSignataireName;
	private String _signataireFirstname;
	private Date _birthday;
	private String _address;
	private String _mail;
	private long _postalCode;
	private String _mobilePhone;
	private String _phone;
	private String _city;
	private Date _signatureDate;
	private String _publikUserId;
	private String _originalPublikUserId;
	private long _petitionId;
	private long _originalPetitionId;
	private boolean _setOriginalPetitionId;
	private long _columnBitmask;
	private Signataire _escapedModel;
}