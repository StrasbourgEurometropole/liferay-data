create table objtp_FoundObject (
	number_ varchar(75) not null primary key,
	date_ timestamp null,
	imageUrl varchar(75) null,
	categoryCode varchar(75) null
);

create table objtp_ObjectCategory (
	code_ varchar(75) not null primary key,
	name varchar(75) null
);
