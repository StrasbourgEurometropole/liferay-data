create table notification_Notification (
	notificationId LONG not null primary key,
	title STRING null,
	description TEXT null,
	url VARCHAR(75) null,
	automatic BOOLEAN,
	singleUser BOOLEAN,
	singleUserId VARCHAR(75) null,
	publicationDate DATE null,
	expirationDate DATE null,
	status INTEGER,
	typeId LONG
);

create table notification_UserNotificationChannel (
	publikUserId VARCHAR(75) not null,
	channelId LONG not null,
	primary key (publikUserId, channelId)
);

create table notification_UserNotificationStatus (
	notificationId LONG not null,
	publikUserId VARCHAR(75) not null,
	read_ BOOLEAN,
	primary key (notificationId, publikUserId)
);

create table notification_UserNotificationType (
	publikUserId VARCHAR(75) not null,
	typeId LONG not null,
	primary key (publikUserId, typeId)
);