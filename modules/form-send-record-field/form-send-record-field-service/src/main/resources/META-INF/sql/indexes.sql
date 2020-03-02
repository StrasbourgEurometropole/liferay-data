create index IX_DF0191B8 on FormSendRecordField_FormSendRecordField (assetEntryId, status);
create index IX_5EADA247 on FormSendRecordField_FormSendRecordField (contentId, instanceId[$COLUMN_LENGTH:75$]);
create index IX_3F384609 on FormSendRecordField_FormSendRecordField (groupId);
create index IX_28A3CB75 on FormSendRecordField_FormSendRecordField (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_E022AB37 on FormSendRecordField_FormSendRecordField (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_FE1E1D62 on FormSendRecordField_FormSendRecordFieldSignalement (formSendRecordFieldId);
create index IX_DC15AFC4 on FormSendRecordField_FormSendRecordFieldSignalement (groupId);
create index IX_CD5CFB86 on FormSendRecordField_FormSendRecordFieldSignalement (publikId[$COLUMN_LENGTH:75$]);
create index IX_4572241A on FormSendRecordField_FormSendRecordFieldSignalement (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_BD88E91C on FormSendRecordField_FormSendRecordFieldSignalement (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_50F219D8 on formSendRecordField_FormSendRecordField (assetEntryId, status);
create index IX_2ACE1E27 on formSendRecordField_FormSendRecordField (contentId, instanceId[$COLUMN_LENGTH:75$]);
create index IX_746D09E9 on formSendRecordField_FormSendRecordField (groupId);
create index IX_3F5D6395 on formSendRecordField_FormSendRecordField (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_92A3CB57 on formSendRecordField_FormSendRecordField (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_73E37582 on formSendRecordField_FormSendRecordFieldSignalement (formSendRecordFieldId);
create index IX_2AC9BFE4 on formSendRecordField_FormSendRecordFieldSignalement (groupId);
create index IX_552AEF66 on formSendRecordField_FormSendRecordFieldSignalement (publikId[$COLUMN_LENGTH:75$]);
create index IX_617E37FA on formSendRecordField_FormSendRecordFieldSignalement (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_4B4874FC on formSendRecordField_FormSendRecordFieldSignalement (uuid_[$COLUMN_LENGTH:75$], groupId);