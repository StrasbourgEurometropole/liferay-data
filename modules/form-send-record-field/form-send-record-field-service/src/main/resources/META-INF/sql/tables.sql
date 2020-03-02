create table FormSendRecordField_FormSendRecordField (
	uuid_ VARCHAR(75) null,
	formSendRecordFieldId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	response TEXT null,
	assetEntryId LONG,
	contentId LONG,
	instanceId VARCHAR(75) null,
	responseUserId LONG
);

create table FormSendRecordField_FormSendRecordFieldSignalement (
	uuid_ VARCHAR(75) null,
	signalementId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	formSendRecordFieldId LONG,
	publikId VARCHAR(75) null
);

create table formSendRecordField_FormSendRecordField (
	uuid_ VARCHAR(75) null,
	formSendRecordFieldId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	response TEXT null,
	assetEntryId LONG,
	contentId LONG,
	instanceId VARCHAR(75) null,
	responseUserId LONG
);

create table formSendRecordField_FormSendRecordFieldSignalement (
	uuid_ VARCHAR(75) null,
	signalementId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	formSendRecordFieldId LONG,
	publikId VARCHAR(75) null
);