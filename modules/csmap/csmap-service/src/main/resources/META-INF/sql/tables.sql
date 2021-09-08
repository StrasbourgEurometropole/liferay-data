create table csmap_PlaceCategories (
	uuid_ VARCHAR(75) null,
	placeCategoriesId LONG not null primary key,
	categoriesIds STRING null
);

create table csmap_RefreshToken (
	uuid_ VARCHAR(75) null,
	refreshTokenId LONG not null primary key,
	createDate DATE null,
	value VARCHAR(300) null,
	publikId VARCHAR(200) null
);