create table tipi_TipiEntry (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	date_ DATE null,
	total INTEGER,
	paidCount INTEGER,
	refusedCount INTEGER,
	canceledCount INTEGER,
	type_ VARCHAR(75) null
);