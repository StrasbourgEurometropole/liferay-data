create table objtp_FoundObject (
	number_ VARCHAR(75) not null primary key,
	date_ DATE null,
	imageUrl VARCHAR(75) null,
	categoryCode VARCHAR(75) null
);

create table objtp_ObjectCategory (
	code_ VARCHAR(75) not null primary key,
	name VARCHAR(75) null
);