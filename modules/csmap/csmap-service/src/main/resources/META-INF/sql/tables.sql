create table csmap_Nonce (
	uuid_ VARCHAR(75) null,
	nonceId LONG not null primary key,
	value VARCHAR(75) null,
	expirationDate DATE null
);