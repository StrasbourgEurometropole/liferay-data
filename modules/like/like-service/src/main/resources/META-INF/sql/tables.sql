create table like_Like (
	likeId LONG not null primary key,
	publikUserId VARCHAR(75) null,
	title VARCHAR(255) null,
	isDislike BOOLEAN,
	typeId LONG,
	entityId LONG,
	entityGroupId LONG
);