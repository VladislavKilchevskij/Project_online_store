ALTER TABLE `techstoredb`.`user`
    CHANGE COLUMN `userName` `username` VARCHAR(16) NOT NULL;
ALTER TABLE `techstoredb`.`user`
    CHANGE COLUMN `password` `password` VARCHAR(255) NOT NULL ;
