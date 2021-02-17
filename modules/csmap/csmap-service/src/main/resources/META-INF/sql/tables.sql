create table csmap_Nonce (
	uuid_ VARCHAR(75) null,
	nonceId LONG not null primary key,
	createDate DATE null,
	value VARCHAR(255) null
);

create table csmap_RefreshToken (
	uuid_ VARCHAR(75) null,
	refreshTokenId LONG not null primary key,
	createDate DATE null,
	value VARCHAR(255) null,
	publikId VARCHAR(200) null
);