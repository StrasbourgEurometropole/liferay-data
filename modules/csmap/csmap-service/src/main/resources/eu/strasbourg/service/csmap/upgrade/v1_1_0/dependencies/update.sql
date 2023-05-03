alter table csmap_Agenda
    add labelLink LONGTEXT null AFTER imageId;
alter table csmap_Agenda
    add link LONGTEXT null AFTER labelLink;
alter table csmap_Agenda
    add publicationStartDate DATE null AFTER link;
alter table csmap_Agenda
    add publicationEndDate DATE null AFTER publicationStartDate;

COMMIT_TRANSACTION;