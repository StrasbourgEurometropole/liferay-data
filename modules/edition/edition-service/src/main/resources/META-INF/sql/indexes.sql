create index IX_2215B1C1 on edition_Edition (groupId, title[$COLUMN_LENGTH:400$]);
create index IX_216DB04D on edition_Edition (publicationDate, status);
create index IX_9FE39905 on edition_Edition (title[$COLUMN_LENGTH:400$]);
create index IX_953ABBD7 on edition_Edition (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_9D1E4C19 on edition_Edition (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_61A3CC0F on edition_EditionGallery (groupId);
create index IX_9F1A2CF5 on edition_EditionGallery (publicationDate, status);
create index IX_E7496FAD on edition_EditionGallery (title[$COLUMN_LENGTH:400$]);
create index IX_B987AC2F on edition_EditionGallery (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8C1B7271 on edition_EditionGallery (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_8982DED6 on edition_EditionToEditionGallery (companyId);
create index IX_2AE30F97 on edition_EditionToEditionGallery (editionId);
create index IX_27B4A72B on edition_EditionToEditionGallery (galleryId);