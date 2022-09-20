-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema doggydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `doggydb` ;

-- -----------------------------------------------------
-- Schema doggydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `doggydb` ;
USE `doggydb` ;

-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role` ;

CREATE TABLE IF NOT EXISTS `user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `postal_code` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store` ;

CREATE TABLE IF NOT EXISTS `store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `address_id` INT NOT NULL,
  `description` TEXT NULL,
  `website_url` TEXT NULL,
  `logo_url` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_company_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_company_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product` ;

CREATE TABLE IF NOT EXISTS `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `brand` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `price` DECIMAL NULL,
  `store_id` INT NULL,
  `image` VARCHAR(2000) NULL,
  `created_on` TIMESTAMP NULL,
  `updated_on` TIMESTAMP NULL,
  INDEX `fk_product_store1_idx` (`store_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NULL,
  `email` INT NULL,
  `enabled` TINYINT NULL,
  `address_id` INT NULL,
  `created_on` TIMESTAMP NULL,
  `updated_on` TIMESTAMP NULL,
  `active` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_role` ;

CREATE TABLE IF NOT EXISTS `user_has_role` (
  `user_id` INT NOT NULL,
  `user_role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `user_role_id`),
  INDEX `fk_user_has_user_role_user_role1_idx` (`user_role_id` ASC),
  INDEX `fk_user_has_user_role_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_user_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_role_user_role1`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `user_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `squad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `squad` ;

CREATE TABLE IF NOT EXISTS `squad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(70) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_message` ;

CREATE TABLE IF NOT EXISTS `group_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` TEXT NULL DEFAULT NULL,
  `created` DATETIME NULL DEFAULT NULL,
  `squad_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_message_group1_idx` (`squad_id` ASC),
  INDEX `fk_group_message_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_group_message_group1`
    FOREIGN KEY (`squad_id`)
    REFERENCES `squad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_message_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message_content` TEXT NULL DEFAULT NULL,
  `created` DATETIME NULL DEFAULT NULL,
  `message_from` INT NULL DEFAULT NULL,
  `message_to` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_user1_idx` (`message_from` ASC),
  INDEX `fk_message_user2_idx` (`message_to` ASC),
  CONSTRAINT `fk_message_user1`
    FOREIGN KEY (`message_from`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_user2`
    FOREIGN KEY (`message_to`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_member` ;

CREATE TABLE IF NOT EXISTS `group_member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `squad_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_member_user1_idx` (`user_id` ASC),
  INDEX `fk_group_member_group1_idx` (`squad_id` ASC),
  CONSTRAINT `fk_group_member_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_member_group1`
    FOREIGN KEY (`squad_id`)
    REFERENCES `squad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `rating` INT NULL,
  `store_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `created_on` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_store1_idx` (`store_id` ASC),
  INDEX `fk_comment_product1_idx` (`product_id` ASC),
  CONSTRAINT `fk_comment_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory` ;

CREATE TABLE IF NOT EXISTS `inventory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `store_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_inventory_store1_idx` (`store_id` ASC),
  INDEX `fk_inventory_product1_idx` (`product_id` ASC),
  CONSTRAINT `fk_inventory_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet` ;

CREATE TABLE IF NOT EXISTS `pet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `weight` DECIMAL(5,2) NULL,
  `user_id` INT NOT NULL,
  `gender` VARCHAR(45) NULL,
  `image` VARCHAR(2000) NULL,
  `birth_date` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pet_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_pet_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `breed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `breed` ;

CREATE TABLE IF NOT EXISTS `breed` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NULL,
  `color` VARCHAR(45) NULL,
  `pet_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_breed_pet1_idx` (`pet_id` ASC),
  CONSTRAINT `fk_breed_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dietary_needs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dietary_needs` ;

CREATE TABLE IF NOT EXISTS `dietary_needs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `pet_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dietary_needs_pet1_idx` (`pet_id` ASC),
  CONSTRAINT `fk_dietary_needs_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ingredient` ;

CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ingredient_has_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ingredient_has_product` ;

CREATE TABLE IF NOT EXISTS `ingredient_has_product` (
  `ingredient_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`ingredient_id`, `product_id`),
  INDEX `fk_ingredient_has_product_product1_idx` (`product_id` ASC),
  INDEX `fk_ingredient_has_product_ingredient1_idx` (`ingredient_id` ASC),
  CONSTRAINT `fk_ingredient_has_product_ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredient_has_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_history` ;

CREATE TABLE IF NOT EXISTS `product_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_on` TIMESTAMP NULL,
  `updated_on` TIMESTAMP NULL,
  `user_quantity` INT NULL,
  `price` DECIMAL(5,2) NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_history_product1_idx` (`product_id` ASC),
  CONSTRAINT `fk_product_history_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS doggyuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'doggyuser'@'localhost' IDENTIFIED BY 'doggyuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'doggyuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `user_role` (`id`, `name`) VALUES (1, 'Admin');
INSERT INTO `user_role` (`id`, `name`) VALUES (2, 'Employee');
INSERT INTO `user_role` (`id`, `name`) VALUES (3, 'Guest');

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (1, '11924 Se Division', 'Portland', 'Oregon', '97266', '503-761-5974');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (2, '111 Sw Fifth Ave Suite 3150', 'Portland', 'Oregon', '97204', '503-217-4114');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (3, '1441 North Marine Drive', 'Portland', 'Oregon', '97217', '503-894-9279');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (4, '20360 Sw Westside Dr', 'Portland', 'Oregon', '97078', '503-277-8642');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (5, '5934 Se Duke St', 'Portland', 'Oregon', '97206', '503-771-4637');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (6, '5934 Se Duke St', 'Portland', 'Oregon', '97206', '503-771-4637');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (7, '5934 Se Duke St', 'Portland', 'Oregon', '97206', '503-771-4637');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (8, 'Guest address', 'Guest city', 'Guest state', '97206', '111-111-1111');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (9, '4540 Haul Road', 'Portland', 'Oregon', '97206', '650-938-9509');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (10, '606 Wilson Street', 'Portland', 'Oregon', '93546', '760-924-1991');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (11, '194 Franklin Avenue', 'Portland', 'Oregon', '32810', '386-216-1071');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (12, '3362 Braxton Street', 'Portland', 'Oregon', '60606', '815-534-3849');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (13, '4043 Radio Park Drive', 'Portland', 'Oregon', '30648', '912-552-3444');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (14, '4841 Scheuvront Drive', 'Portland', 'Oregon', '80202', '303-534-4325');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (15, '4712 Chicago Avenue', 'Portland', 'Oregon', '93721', '559-618-2099');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (16, '2249 Stratford Court', 'Portland', 'Oregon', '27565', '919-693-9547');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (17, '3720 Yorkie Lane', 'Portland', 'Oregon', '31401', '912-916-2432');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (18, '2574 Meadow Lane', 'Portland', 'Oregon', '94597', '707-293-3186');

COMMIT;


-- -----------------------------------------------------
-- Data for table `store`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `store` (`id`, `name`, `address_id`, `description`, `website_url`, `logo_url`) VALUES (1, 'Social Engineering', 5, 'Bringing together people, whether you like it or not.', 'https://helloworld.com/', 'data:image/png;base64,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');

COMMIT;


-- -----------------------------------------------------
-- Data for table `product`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `price`, `store_id`, `image`, `created_on`, `updated_on`) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (1, NULL, NULL, 'admin', 'admin', 1, 1, NULL, NULL, NULL, 1, '1');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (2, NULL, NULL, 'bdobbs', 'bdobbs', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (3, NULL, NULL, 'rcosby', 'rcosby', 3, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (4, NULL, NULL, 'rmiller', 'rmiller', 4, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (5, NULL, NULL, 'zkott', 'zkott', 5, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (6, NULL, NULL, 'jpatt', 'jpatt', 6, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (7, NULL, NULL, 'dschu', 'dschu', 7, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (8, NULL, NULL, 'guest', 'guest', 8, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (9, NULL, NULL, 'mscott', 'mscott', 9, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (10, NULL, NULL, 'dschru', 'dschru', 10, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (11, NULL, NULL, 'phalp', 'phalp', 11, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (12, NULL, NULL, 'jhalp', 'jhalp', 12, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (13, NULL, NULL, 'kkapo', 'kkapo', 13, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (14, NULL, NULL, 'pvanc', 'pvanc', 14, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (15, NULL, NULL, 'abern', 'abern', 15, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (16, NULL, NULL, 'dphil', 'dphil', 16, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (17, NULL, NULL, 'amart', 'amart', 17, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `active`, `role`) VALUES (18, NULL, NULL, 'kmalon', 'kmalon', 18, 1, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (1, 1);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (2, 2);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (3, 1);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (4, 1);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (1, 2);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (5, 1);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (6, 1);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (7, 1);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (3, 2);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (4, 2);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (5, 2);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (6, 2);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (7, 2);
INSERT INTO `user_has_role` (`user_id`, `user_role_id`) VALUES (8, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `squad`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `squad` (`id`, `name`) VALUES (1, 'Technology');
INSERT INTO `squad` (`id`, `name`) VALUES (2, 'Human Resouces');
INSERT INTO `squad` (`id`, `name`) VALUES (3, 'Engineering');

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `group_message` (`id`, `message`, `created`, `squad_id`, `user_id`) VALUES (1, 'Is this showing?', NULL, 3, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (1, 'Maybe now?', NULL, 6, 1);
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (2, 'Why?', NULL, 1, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_member`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `group_member` (`id`, `user_id`, `squad_id`) VALUES (1, 6, 3);
INSERT INTO `group_member` (`id`, `user_id`, `squad_id`) VALUES (2, 5, 2);
INSERT INTO `group_member` (`id`, `user_id`, `squad_id`) VALUES (3, 7, 1);
INSERT INTO `group_member` (`id`, `user_id`, `squad_id`) VALUES (4, 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dietary_needs`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `dietary_needs` (`id`, `name`, `pet_id`) VALUES (1, 'vegan', NULL);
INSERT INTO `dietary_needs` (`id`, `name`, `pet_id`) VALUES (2, 'grain-free', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `ingredient` (`id`, `name`) VALUES (1, 'chicken');
INSERT INTO `ingredient` (`id`, `name`) VALUES (2, 'soy');
INSERT INTO `ingredient` (`id`, `name`) VALUES (3, 'beef');
INSERT INTO `ingredient` (`id`, `name`) VALUES (4, 'rice');
INSERT INTO `ingredient` (`id`, `name`) VALUES (5, 'salmon');
INSERT INTO `ingredient` (`id`, `name`) VALUES (6, 'white fish');
INSERT INTO `ingredient` (`id`, `name`) VALUES (7, 'pork');
INSERT INTO `ingredient` (`id`, `name`) VALUES (8, 'lamb');

COMMIT;

