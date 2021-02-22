create index IX_1E063953 on help_HelpProposal (groupId);
create index IX_C97DA3D7 on help_HelpProposal (publikId[$COLUMN_LENGTH:75$]);
create index IX_512A42D on help_HelpProposal (status, groupId);
create index IX_F411EB6B on help_HelpProposal (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F56450AD on help_HelpProposal (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_927AB78 on help_HelpRequest (helpProposalId);
create index IX_41B11358 on help_HelpRequest (publikId[$COLUMN_LENGTH:75$], helpProposalId);
create index IX_34BB442A on help_HelpRequest (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F1BACD2C on help_HelpRequest (uuid_[$COLUMN_LENGTH:75$], groupId);