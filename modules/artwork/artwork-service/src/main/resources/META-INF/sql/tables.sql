create table artwork_Artwork (
	uuid_ VARCHAR(75) null,
	artworkId LONG not null primary key,
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
	technicalInformation TEXT null,
	noticeLink STRING null,
	artistName STRING null,
	creationYear STRING null,
	origin STRING null,
	exhibitionName STRING null,
	exhibitionPlace STRING null,
	loanPeriod STRING null,
	linkName STRING null,
	link STRING null,
	imageId LONG,
	imagesIds VARCHAR(75) null
);

create table artwork_ArtworkCollection (
	uuid_ VARCHAR(75) null,
	collectionId LONG not null primary key,
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
	contributors STRING null,
	imageId LONG
);

create table artwork_ArtworkToArtworkCollection (
	companyId LONG not null,
	artworkId LONG not null,
	collectionId LONG not null,
	primary key (artworkId, collectionId)
);