create index IX_DF0191B8 on FormSendRecordField_FormSendRecordField (assetEntryId, status);
create index IX_5EADA247 on FormSendRecordField_FormSendRecordField (contentId, instanceId[$COLUMN_LENGTH:75$]);
create index IX_3F384609 on FormSendRecordField_FormSendRecordField (groupId);
create index IX_28A3CB75 on FormSendRecordField_FormSendRecordField (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E022AB37 on FormSendRecordField_FormSendRecordField (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_FE1E1D62 on FormSendRecordField_FormSendRecordFieldSignalement (formSendRecordFieldId);
create index IX_DC15AFC4 on FormSendRecordField_FormSendRecordFieldSignalement (groupId);
create index IX_4572241A on FormSendRecordField_FormSendRecordFieldSignalement (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BD88E91C on FormSendRecordField_FormSendRecordFieldSignalement (uuid_[$COLUMN_LENGTH:75$], groupId);