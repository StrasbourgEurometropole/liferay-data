create table csmap_RefreshToken (
	uuid_ VARCHAR(75) null,
	refreshTokenId LONG not null primary key,
	createDate DATE null,
	value VARCHAR(255) null,
	publikId VARCHAR(200) null
);