create index IX_808E41F2 on csmap_Nonce (uuid_[$COLUMN_LENGTH:75$]);
create index IX_9385A81F on csmap_Nonce (value[$COLUMN_LENGTH:255$]);

create index IX_E80807DD on csmap_RefreshToken (uuid_[$COLUMN_LENGTH:75$]);
create index IX_72E2BCEA on csmap_RefreshToken (value[$COLUMN_LENGTH:255$], publikId[$COLUMN_LENGTH:200$]);