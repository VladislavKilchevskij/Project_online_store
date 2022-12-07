ALTER TABLE `techstoredb`.`user`
    ADD COLUMN `account_non_locked`      TINYINT(1) NULL DEFAULT 1 AFTER `createTime`,
    ADD COLUMN `account_non_expired`     TINYINT(1) NULL DEFAULT 1 AFTER `account_non_locked`,
    ADD COLUMN `credentials_non_expired` TINYINT(1) NULL DEFAULT 1 AFTER `account_non_expired`;