create table interest_Interest (
	uuid_ VARCHAR(75) null,
	interestId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	title STRING null,
	description TEXT null,
	typeId LONG
);

create table interest_UserInterest (
	interestId LONG not null,
	publikUserId VARCHAR(75) not null,
	primary key (interestId, publikUserId)
);