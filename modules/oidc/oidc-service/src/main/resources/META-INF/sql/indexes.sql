create index IX_5BE106E8 on publik_AnonymisationHistoric (groupId);
create index IX_A9CC9576 on publik_AnonymisationHistoric (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_15480178 on publik_AnonymisationHistoric (uuid_[$COLUMN_LENGTH:75$], groupId);

create unique index IX_C4288930 on publik_PublikUser (publikId[$COLUMN_LENGTH:200$]);
create index IX_B981C5E4 on publik_PublikUser (uuid_[$COLUMN_LENGTH:75$]);