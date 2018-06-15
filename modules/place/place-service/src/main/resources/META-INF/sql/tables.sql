create table place_Period (
	uuid_ VARCHAR(75) null,
	periodId LONG not null primary key,
	name STRING null,
	defaultPeriod BOOLEAN,
	startDate DATE null,
	endDate DATE null,
	linkLabel STRING null,
	linkURL STRING null,
	alwaysOpen BOOLEAN,
	RTGreenThreshold LONG,
	RTOrangeThreshold LONG,
	RTRedThreshold LONG,
	RTMaxThreshold LONG,
	placeId LONG
);

create table place_Place (
	uuid_ VARCHAR(75) null,
	placeId LONG not null primary key,
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
	SIGid VARCHAR(75) null,
	name VARCHAR(400) null,
	addressComplement VARCHAR(400) null,
	addressStreet VARCHAR(400) null,
	addressDistribution VARCHAR(400) null,
	addressZipCode VARCHAR(75) null,
	addressCountry VARCHAR(75) null,
	mercatorX VARCHAR(75) null,
	mercatorY VARCHAR(75) null,
	RGF93X VARCHAR(75) null,
	RGF93Y VARCHAR(75) null,
	alias_ STRING null,
	presentation TEXT null,
	serviceAndActivities TEXT null,
	characteristics TEXT null,
	subjectToPublicHoliday BOOLEAN,
	exceptionalSchedule TEXT null,
	displayEvents BOOLEAN,
	additionalInformation TEXT null,
	phone VARCHAR(75) null,
	mail VARCHAR(75) null,
	siteURL STRING null,
	siteLabel STRING null,
	facebookURL STRING null,
	facebookLabel STRING null,
	accesMap STRING null,
	access_ TEXT null,
	accessForDisabled TEXT null,
	accessForBlind BOOLEAN,
	accessForDeaf BOOLEAN,
	accessForWheelchair BOOLEAN,
	accessForElder BOOLEAN,
	accessForDeficient BOOLEAN,
	RTEnabled BOOLEAN,
	RTType VARCHAR(75) null,
	RTExternalId VARCHAR(75) null,
	RTAvailable LONG,
	RTOccupation LONG,
	RTCapacity LONG,
	RTStatus VARCHAR(75) null,
	RTLastUpdate DATE null,
	imageId LONG,
	imageIds VARCHAR(400) null,
	videosIds VARCHAR(400) null,
	priceId LONG,
	documentsIds VARCHAR(400) null
);

create table place_Price (
	uuid_ VARCHAR(75) null,
	priceId LONG not null primary key,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	title STRING null,
	price TEXT null
);

create table place_PublicHoliday (
	uuid_ VARCHAR(75) null,
	publicHolidayId LONG not null primary key,
	name STRING null,
	date_ DATE null,
	recurrent BOOLEAN
);

create table place_ScheduleException (
	uuid_ VARCHAR(75) null,
	exceptionId LONG not null primary key,
	startDate DATE null,
	endDate DATE null,
	openingTimes VARCHAR(75) null,
	comment_ STRING null,
	closed BOOLEAN,
	placeId LONG,
	subPlaceId LONG
);

create table place_Slot (
	uuid_ VARCHAR(75) null,
	slotId LONG not null primary key,
	dayOfWeek LONG,
	startHour VARCHAR(75) null,
	endHour VARCHAR(75) null,
	comment_ STRING null,
	periodId LONG,
	subPlaceId LONG
);

create table place_SubPlace (
	uuid_ VARCHAR(75) null,
	subPlaceId LONG not null primary key,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	name STRING null,
	description TEXT null,
	placeId LONG
);