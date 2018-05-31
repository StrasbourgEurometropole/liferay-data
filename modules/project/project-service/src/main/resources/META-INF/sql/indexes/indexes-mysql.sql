create index IX_46F38A49 on project_Participation (groupId);
create index IX_37B14F35 on project_Participation (uuid_, companyId);
create unique index IX_883E1EF7 on project_Participation (uuid_, groupId);

create index IX_84EC11F1 on project_Project (groupId);
create index IX_3B92E48D on project_Project (uuid_, companyId);
create unique index IX_DB52A4F on project_Project (uuid_, groupId);

create index IX_6F4E9C8A on project_ProjectTimeline (projectId);
