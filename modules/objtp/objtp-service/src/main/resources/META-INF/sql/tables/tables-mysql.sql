create table objtp_FoundObject (
	number_ varchar(75) not null primary key,
	date_ datetime(6) null,
	imageUrl varchar(75) null,
	categoryCode varchar(75) null
) engine InnoDB;

create table objtp_ObjectCategory (
	code_ varchar(75) not null primary key,
	name varchar(75) null
) engine InnoDB;
