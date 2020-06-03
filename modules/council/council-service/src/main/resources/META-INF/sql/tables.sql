create table council_CouncilSession (
	uuid_ VARCHAR(75) null,
	councilSessionId LONG not null primary key,
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
	title VARCHAR(75) null,
	date_ DATE null,
	type_ VARCHAR(75) null,
	officialLeaderId LONG,
	typeId LONG
);

create table council_Deliberation (
	uuid_ VARCHAR(75) null,
	deliberationId LONG not null primary key,
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
	title VARCHAR(500) null,
	order_ INTEGER,
	stage VARCHAR(75) null,
	countOfficialsVoting INTEGER,
	countOfficialsActive INTEGER,
	councilSessionId LONG
);

create table council_Official (
	uuid_ VARCHAR(75) null,
	officialId LONG not null primary key,
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
	email VARCHAR(75) null,
	firstname VARCHAR(75) null,
	lastname VARCHAR(75) null,
	isMunicipal BOOLEAN,
	isEurometropolitan BOOLEAN,
	isActive BOOLEAN,
	lastActivity DATE null,
	lastSignInDeviceInfo VARCHAR(300) null
);

create table council_Procuration (
	uuid_ VARCHAR(75) null,
	procurationId LONG not null primary key,
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
	officialVotersId LONG,
	officialUnavailableId LONG,
	councilSessionId LONG,
	isAbsent BOOLEAN
);

create table council_Type (
	uuid_ VARCHAR(75) null,
	typeId LONG not null primary key,
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
	title VARCHAR(75) null
);

create table council_Vote (
	uuid_ VARCHAR(75) null,
	officialId LONG not null,
	deliberationId LONG not null,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	result VARCHAR(75) null,
	officialProcurationId LONG,
	primary key (officialId, deliberationId)
);