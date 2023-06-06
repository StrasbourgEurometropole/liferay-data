create index IX_11EF1D93 on csmap_Agenda (isPrincipal, isActive);
create index IX_89D4E52F on csmap_Agenda (uuid_[$COLUMN_LENGTH:75$]);

create index IX_D68E97D0 on csmap_BaseNonce (value[$COLUMN_LENGTH:75$]);

create index IX_5B6AA3D6 on csmap_CsmapCache (codeCache);
create index IX_4096E817 on csmap_CsmapCache (isLastProcessSuccess);

create index IX_24001F16 on csmap_PlaceCategories (categoriesIds[$COLUMN_LENGTH:4000$]);
create index IX_C04F161E on csmap_PlaceCategories (uuid_[$COLUMN_LENGTH:75$]);

create index IX_DDDFD897 on csmap_RefreshToken (publikId[$COLUMN_LENGTH:200$]);
create index IX_E80807DD on csmap_RefreshToken (uuid_[$COLUMN_LENGTH:75$]);
create index IX_FAFF6E0A on csmap_RefreshToken (value[$COLUMN_LENGTH:300$]);

create index IX_C4C39F2 on csmap_Thematic (uuid_[$COLUMN_LENGTH:75$]);