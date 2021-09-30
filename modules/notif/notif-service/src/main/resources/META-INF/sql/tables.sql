create table notif_Message (
	messageId LONG not null primary key,
	serviceId LONG,
	content TEXT null
);

create table notif_Nature (
	natureId LONG not null primary key,
	serviceId LONG,
	name VARCHAR(400) null
);

create table notif_NatureNotif (
	natureId LONG not null primary key,
	serviceId LONG,
	name VARCHAR(75) null
);

create table notif_Notif (
	uuid_ VARCHAR(75) null,
	notificationId LONG not null primary key,
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
	serviceId LONG,
	isAlert INTEGER,
	natureId LONG,
	title STRING null,
	subtitle STRING null,
	startDate DATE null,
	endDate DATE null,
	broadcastDate DATE null,
	isAutomaticMessage INTEGER,
	content TEXT null,
	labelUrl STRING null,
	url STRING null,
	typeBroadcast LONG,
	broadcastChannels VARCHAR(75) null,
	sendStatusCsmap LONG,
	sendStatusTwitter LONG,
	sendStatusMonst LONG,
	sendStatusMail LONG,
	sendStatusSegur LONG
);

create table notif_Notification (
	uuid_ VARCHAR(75) null,
	notificationId LONG not null primary key,
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
	serviceId LONG,
	isAlert INTEGER,
	natureId LONG,
	title STRING null,
	subtitle STRING null,
	startDate DATE null,
	endDate DATE null,
	broadcastDate DATE null,
	isAutomaticMessage INTEGER,
	content STRING null,
	labelUrl STRING null,
	url STRING null,
	typeBroadcast LONG,
	broadcastChannels VARCHAR(75) null,
	sendStatusCsmap LONG,
	sendStatusTwitter LONG,
	sendStatusMonst LONG,
	sendStatusMail LONG,
	sendStatusSegur LONG
);

create table notif_Service (
	serviceId LONG not null primary key,
	organisationId LONG,
	name VARCHAR(400) null,
	pictoId LONG
);

create table notif_ServiceNotif (
	serviceId LONG not null primary key,
	organisationId LONG,
	name VARCHAR(75) null,
	pictoId LONG
);