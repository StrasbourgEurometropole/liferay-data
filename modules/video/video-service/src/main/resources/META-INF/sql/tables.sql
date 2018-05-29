create table video_Video (
	uuid_ VARCHAR(75) null,
	videoId LONG not null primary key,
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
	copyright STRING null,
	source STRING null,
	publicationDate DATE null,
	imageId LONG,
	transcriptionFileId LONG
);

create table video_VideoGallery (
	uuid_ VARCHAR(75) null,
	galleryId LONG not null primary key,
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
	publicationDate DATE null,
	imageId LONG
);

create table video_VideoToVideoGallery (
	companyId LONG not null,
	videoId LONG not null,
	galleryId LONG not null,
	primary key (videoId, galleryId)
);