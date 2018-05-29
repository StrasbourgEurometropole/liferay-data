create index IX_D3CE2D85 on interest_Interest (groupId);
create index IX_84548F79 on interest_Interest (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_6A7CA03B on interest_Interest (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_352F9B89 on interest_UserInterest (interestId);
create index IX_947818BB on interest_UserInterest (publikUserId[$COLUMN_LENGTH:75$]);