ALTER TABLE council_Deliberation ADD COLUMN quorum INTEGER AFTER countOfficialsActive;
COMMIT_TRANSACTION;
UPDATE council_Deliberation SET quorum = 0;
UPDATE council_Deliberation SET quorum = (FLOOR(countOfficialsActive/3)+1) Where countOfficialsActive != 0;
COMMIT_TRANSACTION;