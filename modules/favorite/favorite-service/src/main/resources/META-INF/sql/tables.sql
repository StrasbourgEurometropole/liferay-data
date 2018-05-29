create table favorite_Favorite (
	favoriteId LONG not null primary key,
	publikUserId VARCHAR(75) null,
	title VARCHAR(255) null,
	url VARCHAR(255) null,
	typeId LONG,
	entityId LONG,
	entityGroupId LONG
);