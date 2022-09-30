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
-- Table `chain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chain` ;

CREATE TABLE IF NOT EXISTS `chain` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `website_url` VARCHAR(100) NULL,
  `logo_url` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
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
  `email` VARCHAR(100) NULL,
  `enabled` TINYINT NULL,
  `address_id` INT NULL,
  `created_on` TIMESTAMP NULL,
  `updated_on` TIMESTAMP NULL,
  `role` VARCHAR(45) NULL,
  `bio` TEXT NULL,
  `profile_image_url` VARCHAR(2000) NULL,
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
  `chain_id` INT NULL,
  `enabled` TINYINT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_company_address1_idx` (`address_id` ASC),
  INDEX `fk_store_chain1_idx` (`chain_id` ASC),
  INDEX `fk_store_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_company_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_chain1`
    FOREIGN KEY (`chain_id`)
    REFERENCES `chain` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
  `image` VARCHAR(2000) NULL,
  `created_on` TIMESTAMP NULL,
  `updated_on` TIMESTAMP NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`))
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
  `squad_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_member_group1_idx` (`squad_id` ASC),
  INDEX `fk_group_member_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_group_member_group1`
    FOREIGN KEY (`squad_id`)
    REFERENCES `squad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_member_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `store_comment` ;

CREATE TABLE IF NOT EXISTS `store_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `rating` INT NULL,
  `created_on` TIMESTAMP NULL,
  `in_reply_to_id` INT NULL,
  `store_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_store1_idx` (`store_id` ASC),
  INDEX `fk_store_comment_store_comment1_idx` (`in_reply_to_id` ASC),
  INDEX `fk_store_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_comment_store_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `store_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory` ;

CREATE TABLE IF NOT EXISTS `inventory` (
  `store_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `price` DECIMAL(5,3) NULL,
  `quantity` INT NULL,
  `enabled` TINYINT NULL,
  `created_on` TIMESTAMP NULL,
  `updated_on` TIMESTAMP NULL,
  PRIMARY KEY (`store_id`, `product_id`),
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
  `enabled` TINYINT NULL,
  `color` VARCHAR(45) NULL,
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
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `diet` ;

CREATE TABLE IF NOT EXISTS `diet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ingredient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ingredient` ;

CREATE TABLE IF NOT EXISTS `ingredient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ingredient_has_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ingredient_has_product` ;

CREATE TABLE IF NOT EXISTS `ingredient_has_product` (
  `product_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `ingredient_id`),
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
-- Table `product_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_report` ;

CREATE TABLE IF NOT EXISTS `product_report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_on` TIMESTAMP NULL,
  `updated_on` TIMESTAMP NULL,
  `user_quantity` INT NULL,
  `price` DECIMAL(5,2) NULL,
  `is_in_stock` TINYINT NULL,
  `remark` TEXT NULL,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_history_user1_idx` (`user_id` ASC),
  INDEX `fk_product_history_product1_idx` (`product_id` ASC),
  INDEX `fk_product_history_store1_idx` (`store_id` ASC),
  CONSTRAINT `fk_product_history_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_history_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_history_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_has_dietary_needs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_has_dietary_needs` ;

CREATE TABLE IF NOT EXISTS `pet_has_dietary_needs` (
  `pet_id` INT NOT NULL,
  `dietary_needs_id` INT NOT NULL,
  PRIMARY KEY (`pet_id`, `dietary_needs_id`),
  INDEX `fk_pet_has_dietary_needs_dietary_needs1_idx` (`dietary_needs_id` ASC),
  INDEX `fk_pet_has_dietary_needs_pet1_idx` (`pet_id` ASC),
  CONSTRAINT `fk_pet_has_dietary_needs_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_has_dietary_needs_dietary_needs1`
    FOREIGN KEY (`dietary_needs_id`)
    REFERENCES `diet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_comment` ;

CREATE TABLE IF NOT EXISTS `product_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `rating` INT NULL,
  `created_on` TIMESTAMP NULL,
  `product_id` INT NOT NULL,
  `in_reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_comment_product1_idx` (`product_id` ASC),
  INDEX `fk_product_comment_product_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_product_comment_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_comment_product_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `product_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_has_breed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_has_breed` ;

CREATE TABLE IF NOT EXISTS `pet_has_breed` (
  `pet_id` INT NOT NULL,
  `breed_id` INT NOT NULL,
  PRIMARY KEY (`pet_id`, `breed_id`),
  INDEX `fk_pet_has_breed_breed1_idx` (`breed_id` ASC),
  INDEX `fk_pet_has_breed_pet1_idx` (`pet_id` ASC),
  CONSTRAINT `fk_pet_has_breed_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_has_breed_breed1`
    FOREIGN KEY (`breed_id`)
    REFERENCES `breed` (`id`)
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
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (1, '4875 Ward Road', 'Wheat Ridge', 'Colorado', '80033', '720-522-6689');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (2, '9870 West 48th Avenue', 'Wheat Ridge', 'Colorado', '80033', '720-522-7385');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (3, '3595 Wynkoop Street', 'Denver', 'Colorado', '80216', '720-687-6854');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (4, '4265 Barr Lane', 'Westminster', 'Colorado', '80031', '303-687-3512');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (5, '2600 Lawrence Street', 'Denver', 'Colorado', '80205', '720-786-3458');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (6, '1497 South Brentwood Street', 'Lakewood', 'Colorado', '80232', '303-965-6873');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (7, '1708 West Baltic Place', 'Englewood', 'Colorado', '80110', '720-357-3540');
INSERT INTO `address` (`id`, `address`, `city`, `state`, `postal_code`, `phone`) VALUES (8, '5839 Pierce Street', 'Arvada', 'Colorado', '80003', '303-354-6824');

COMMIT;


-- -----------------------------------------------------
-- Data for table `chain`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `chain` (`id`, `name`, `description`, `website_url`, `logo_url`) VALUES (1, 'Petco', NULL, NULL, NULL);
INSERT INTO `chain` (`id`, `name`, `description`, `website_url`, `logo_url`) VALUES (2, 'Petsmart', NULL, NULL, NULL);
INSERT INTO `chain` (`id`, `name`, `description`, `website_url`, `logo_url`) VALUES (3, 'Chewy', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `role`, `bio`, `profile_image_url`) VALUES (1, 'Ad', 'Min', 'admin', '$2a$10$yT3X.SqpZ8Z5quWdaD.HhubjpYAKGEKDZy4irFZ0G.PwGuBHjVV6O', NULL, 1, 4, NULL, NULL, '1', NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRFK2kC36ai7QPyBcr-nuaOPXFzSGoTKoor2w&usqp=CAU');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `role`, `bio`, `profile_image_url`) VALUES (2, 'Dog', 'Gy', 'doggy', '$2a$10$yT3X.SqpZ8Z5quWdaD.HhubjpYAKGEKDZy4irFZ0G.PwGuBHjVV6O', NULL, 1, 5, NULL, NULL, '2', NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqTP_GP_7xX2ERKeUi1qSYgyS5z4c5ObeVnw&usqp=CAU');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `role`, `bio`, `profile_image_url`) VALUES (3, 'Dog', 'Gy2', 'doggy2', '$2a$10$yT3X.SqpZ8Z5quWdaD.HhubjpYAKGEKDZy4irFZ0G.PwGuBHjVV6O', NULL, 1, 6, NULL, NULL, '3', NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQo1sLrBP0Uqnw59_pDDmAbk-uCIQbpHUvhIA&usqp=CAU');

COMMIT;


-- -----------------------------------------------------
-- Data for table `store`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `store` (`id`, `name`, `address_id`, `description`, `website_url`, `logo_url`, `chain_id`, `enabled`, `user_id`) VALUES (1, 'Petco on Ken Pratt', 1, '', '', 'data:image/jpeg;base64,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', 1, 1, 1);
INSERT INTO `store` (`id`, `name`, `address_id`, `description`, `website_url`, `logo_url`, `chain_id`, `enabled`, `user_id`) VALUES (2, 'Petsmart - Hover', 2, NULL, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSjyINPqMgi8m6XDoP-okoxG2bdqgzQWvio-A&usqp=CAU', 2, 1, 1);
INSERT INTO `store` (`id`, `name`, `address_id`, `description`, `website_url`, `logo_url`, `chain_id`, `enabled`, `user_id`) VALUES (3, 'Petsmart - Boulder', 3, NULL, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD6e2qSYTrOQqXPVDR2mCg0GZnhCmYMGw09w&usqp=CAU', 2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (1, 'Pacific Catch Recipe', 'Merrick', 'Connect your dog to an ancestral diet with Merrick Backcountry Raw Infused Grain Free Dog Food, Pacific Catch Recipe, Freeze Dried Dog Food. This dog dry food combines freeze-dried raw-coated kibble and freeze-dried raw dog food bites to offer adult dogs the benefits of a high protein dog food and a raw diet.', 'https://s7d2.scene7.com/is/image/PetSmart/5299386?$CLEARjpg$', '2022-09-28 12:00:02', '2022-09-28 12:10:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (2, 'Big Game Recipe', 'Merrick', 'This dog dry food combines freeze-dried raw-coated kibble and freeze dried raw dog food bites to offer adult dogs the benefits of a high protein dog food and a raw diet.', 'https://s7d2.scene7.com/is/image/PetSmart/5299390?$CLEARjpg$', '2022-09-28 12:01:02', '2022-09-28 12:11:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (3, 'Large Breed Chicken', 'Eukanuba', 'Animal proteins, glucosamine, and chondroitin sulfate help keep muscles lean and joints limber and strong.', 'https://s7d2.scene7.com/is/image/PetSmart/5237736?$CLEARjpg$', '2022-09-28 12:02:02', '2022-09-28 12:12:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (4, 'Wilderness Chicken', 'Blue Buffalo', 'Inspired by the diet of wolves, true omnivores whose endurance is legendary, BLUE Wilderness is a grain-free, protein-rich food that contains more of the delicious chicken your dog loves.', 'https://s7d2.scene7.com/is/image/PetSmart/5149892?$CLEARjpg$', '2022-09-28 12:03:02', '2022-09-28 12:13:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (5, 'Chicken & Barley - Large Breed', 'Hills Science Diet', 'Big dogs have their own set of nutrition needs, so Hill\'s Science Diet Large Breed adult dog food is specially formulated to fuel the energy needs of large breed dogs during the prime of their life.', 'https://s7d2.scene7.com/is/image/PetSmart/5266809?$CLEARjpg$', '2022-09-28 12:04:02', '2022-09-28 12:14:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (6, 'Small Breed - Adult', 'Royal Canin', 'Royal Canin Small Adult dry dog food is formulated to meet the unique nutritional needs of small breed adult dogs, as they actually require higher levels of calories per pound of body weight than large dogs.', 'https://s7d2.scene7.com/is/image/PetSmart/5173207?$CLEARjpg$', '2022-09-28 12:05:02', '2022-09-28 12:15:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (7, 'Limited Ingredient Diet, Salmon', 'Canidae', 'Canidae PURE features premium proteins and clean recipes using a limited number of wholesome ingredients that are easily recognizable. These limited ingredient formulas offer your dog a well-rounded meal that\'s been crafted with their health and well-being in mind.', 'https://s7d2.scene7.com/is/image/PetSmart/5288787?$CLEARjpg$', '2022-09-28 12:06:02', '2022-09-28 12:16:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (8, 'Everyday Health All Life Stage', 'Authority', 'Provide your dog with a delicious and nutritious meal time choice by serving Authority Everyday Health Chicken & Rice Formula All Life Stages Dog Food. This delicious dry blend supports a healthy immune system, healthy skin and a shiny coat, digestive health, and oral care.', 'https://s7d2.scene7.com/is/image/PetSmart/5279202?$CLEARjpg$', '2022-09-28 12:06:02', '2022-09-28 12:16:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (9, 'Grain Free, Sweet Potato & Venison', 'Natural Balance', 'Sometimes what\'s NOT in your dog\'s food is just as important as what is. That\'s the guiding philosophy behind Natural Balance L.I.D. Limited Ingredient Diets Sweet Potato & Venison Formula, a super-premium dry dog food made to help feed your dog\'s greatness.', 'https://s7d2.scene7.com/is/image/PetSmart/5306479?$CLEARjpg$', '2022-09-28 12:06:02', '2022-09-28 12:06:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (10, 'Chicken, Lamb, Salmon', 'Nutro Ultra', 'NUTRO ULTRA believes that dogs deserve to enjoy their food as much as we enjoy ours. And like us, they shouldn\'t have to compromise on taste. ', 'https://s7d2.scene7.com/is/image/PetSmart/5168912?$CLEARjpg$', '2022-09-28 12:06:02', '2022-09-28 12:06:02', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `squad`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `squad` (`id`, `name`) VALUES (1, 'Husky');
INSERT INTO `squad` (`id`, `name`) VALUES (2, 'Stafforshire Terrier');
INSERT INTO `squad` (`id`, `name`) VALUES (3, 'Brindel Boxer');
INSERT INTO `squad` (`id`, `name`) VALUES (4, 'Shitzu');
INSERT INTO `squad` (`id`, `name`) VALUES (5, 'Labrador');
INSERT INTO `squad` (`id`, `name`) VALUES (6, 'Poodle');
INSERT INTO `squad` (`id`, `name`) VALUES (7, 'Doberman');
INSERT INTO `squad` (`id`, `name`) VALUES (8, 'German Shepherd');
INSERT INTO `squad` (`id`, `name`) VALUES (9, 'Border Collie');
INSERT INTO `squad` (`id`, `name`) VALUES (10, 'Chow');
INSERT INTO `squad` (`id`, `name`) VALUES (11, 'Golden Retriever');
INSERT INTO `squad` (`id`, `name`) VALUES (12, 'Rottweiler');

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `group_message` (`id`, `message`, `created`, `squad_id`, `user_id`) VALUES (1, 'Is this showing?', '2022-09-28 12:10:02', 3, 1);
INSERT INTO `group_message` (`id`, `message`, `created`, `squad_id`, `user_id`) VALUES (2, 'This is a message', '2022-09-28 12:11:02', 1, 1);
INSERT INTO `group_message` (`id`, `message`, `created`, `squad_id`, `user_id`) VALUES (3, 'This is also a message', '2022-09-28 12:12:02', 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (1, 'First Message', '2022-09-28 12:10:02', 1, 2);
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (2, 'Second Message', '2022-09-28 12:11:02', 2, 1);
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (3, 'Third Message', '2022-09-28 12:13:02', 3, 1);
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (4, 'Quadernary message', '2022-09-28 12:14:02', 2, 1);
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (5, 'Penta message', '2022-09-28 12:15:02', 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_member`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `group_member` (`id`, `squad_id`, `user_id`) VALUES (1, 1, 1);
INSERT INTO `group_member` (`id`, `squad_id`, `user_id`) VALUES (2, 2, 2);
INSERT INTO `group_member` (`id`, `squad_id`, `user_id`) VALUES (3, 3, 3);
INSERT INTO `group_member` (`id`, `squad_id`, `user_id`) VALUES (4, 1, 2);
INSERT INTO `group_member` (`id`, `squad_id`, `user_id`) VALUES (5, 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `store_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `store_comment` (`id`, `title`, `description`, `rating`, `created_on`, `in_reply_to_id`, `store_id`, `user_id`) VALUES (1, 'Store comment 1', 'Description 1', 1, NULL, 1, 1, 1);
INSERT INTO `store_comment` (`id`, `title`, `description`, `rating`, `created_on`, `in_reply_to_id`, `store_id`, `user_id`) VALUES (2, 'Store comment 2 in reply to 1', 'Description 2', 2, NULL, 1, 1, 2);
INSERT INTO `store_comment` (`id`, `title`, `description`, `rating`, `created_on`, `in_reply_to_id`, `store_id`, `user_id`) VALUES (3, 'Store comment 3 in reply to 2', 'Description 3', 3, NULL, 1, 1, 2);
INSERT INTO `store_comment` (`id`, `title`, `description`, `rating`, `created_on`, `in_reply_to_id`, `store_id`, `user_id`) VALUES (4, 'Store comment 4', 'Description 4', 1, NULL, NULL, 2, 2);
INSERT INTO `store_comment` (`id`, `title`, `description`, `rating`, `created_on`, `in_reply_to_id`, `store_id`, `user_id`) VALUES (5, 'Store comment 5 in reply to 4', 'Description 5', 2, NULL, 4, 2, 3);
INSERT INTO `store_comment` (`id`, `title`, `description`, `rating`, `created_on`, `in_reply_to_id`, `store_id`, `user_id`) VALUES (6, 'Store comment 3 in reply to 4', 'Description 6', 3, NULL, 4, 2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 1, 12.99, 10, 1, '2022-09-28 12:10:02', '2022-09-28 12:20:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (2, 2, 25.45, 12, 1, '2022-09-28 12:11:02', '2022-09-28 12:21:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (3, 3, 10.99, 13, 1, '2022-09-28 12:12:02', '2022-09-28 12:22:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (3, 4, 11.99, 14, 1, '2022-09-28 12:13:02', '2022-09-28 12:23:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 5, 8.99, 15, 1, '2022-09-28 12:14:02', '2022-09-28 12:24:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (2, 6, 15.99, 16, 1, '2022-09-28 12:15:02', '2022-09-28 12:25:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 7, 32.52, 17, 1, '2022-09-28 12:16:02', '2022-09-28 12:26:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (2, 8, 12.99, 18, 1, '2022-09-28 12:17:02', '2022-09-28 12:27:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (3, 9, 13.99, 19, 1, '2022-09-28 12:18:02', '2022-09-28 12:28:02');
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 10, 14.99, 20, 1, '2022-09-28 12:19:02', '2022-09-28 12:29:02');

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `pet` (`id`, `name`, `weight`, `user_id`, `gender`, `image`, `birth_date`, `enabled`, `color`) VALUES (1, 'Specter', 70.0, 1, 'male', 'https://s36700.pcdn.co/wp-content/uploads/2017/10/Siberian-Husky-courtesy-Eileen-M.-Gacke-2-600x400.jpg.optimal.jpg', '2019-01-21', 1, 'white-black');
INSERT INTO `pet` (`id`, `name`, `weight`, `user_id`, `gender`, `image`, `birth_date`, `enabled`, `color`) VALUES (2, 'Peepers', 80.1, 2, 'female', 'https://upload.wikimedia.org/wikipedia/commons/1/17/Augustwiki.jpg', '2015-03-12', 1, 'brown');
INSERT INTO `pet` (`id`, `name`, `weight`, `user_id`, `gender`, `image`, `birth_date`, `enabled`, `color`) VALUES (3, 'Marley', 56.7, 3, 'male', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtuAq96pBngsR0riDlWzmpp5Dplpus8EPNyw&usqp=CAU', '2021-06-14', 1, 'brown-white');

COMMIT;


-- -----------------------------------------------------
-- Data for table `breed`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `breed` (`id`, `name`) VALUES (1, 'Husky');
INSERT INTO `breed` (`id`, `name`) VALUES (2, 'Staffordshire Terrier');
INSERT INTO `breed` (`id`, `name`) VALUES (3, 'Brindel Boxer');
INSERT INTO `breed` (`id`, `name`) VALUES (4, 'Shitzu');
INSERT INTO `breed` (`id`, `name`) VALUES (5, 'Labrador');
INSERT INTO `breed` (`id`, `name`) VALUES (6, 'Poodle');
INSERT INTO `breed` (`id`, `name`) VALUES (7, 'Doberman');
INSERT INTO `breed` (`id`, `name`) VALUES (8, 'German Shepherd');
INSERT INTO `breed` (`id`, `name`) VALUES (9, 'Border Collie');
INSERT INTO `breed` (`id`, `name`) VALUES (10, 'Chow');
INSERT INTO `breed` (`id`, `name`) VALUES (11, 'Golden Retriever');
INSERT INTO `breed` (`id`, `name`) VALUES (12, 'Rottweiler');

COMMIT;


-- -----------------------------------------------------
-- Data for table `diet`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `diet` (`id`, `name`) VALUES (1, 'Wild Game');
INSERT INTO `diet` (`id`, `name`) VALUES (2, 'Omni');
INSERT INTO `diet` (`id`, `name`) VALUES (3, 'Senior');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (1, 'Venison', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (2, 'Chicken', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (3, 'Salmon', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (4, 'Duck', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (5, 'Pea Protein', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (6, 'Soybean Flour', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (7, 'Canola Meal', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (8, 'Corn Germ Meal', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (9, 'Corn Gluten Meal', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (10, 'Dried Egg Product', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (11, 'Chicken by-product Meal', 'protein');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (12, 'Barley', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (13, 'Whole Wheat', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (14, 'Pea Starch', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (15, 'Pea Fiber', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (16, 'Oatmeal', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (17, 'Dried Beet Pulp', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (18, 'Lentil Flour', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (19, 'Corn', 'carb');
INSERT INTO `ingredient` (`id`, `name`, `type`) VALUES (20, 'Wheat Bran Aleurone', 'carb');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient_has_product`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (1, 3);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (1, 5);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (2, 1);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (2, 6);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product_report`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (1, '2022-09-28 12:00:02', '2022-09-28 12:10:02', 1, 59.97, 1, 'This is the best price I\'ve seen since COVID started!', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (2, '2022-09-28 12:01:02', '2022-09-28 12:11:02', 1, 54.99, 1, 'This is currently on sale.', 2, 1, 2);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (3, '2022-09-28 12:02:02', '2022-09-28 12:12:02', 1, 11.99, 1, 'Took long enough', 3, 3, 3);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (4, '2022-09-28 12:03:02', '2022-09-28 12:13:02', 1, 8.99, 1, 'Blah blah blah', 1, 2, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (5, '2022-09-28 12:04:02', '2022-09-28 12:14:02', 1, 7.99, 1, 'Blah blah blah', 1, 3, 2);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (6, '2022-09-28 12:05:02', '2022-09-28 12:15:02', 1, 6.99, 1, 'Blah blah blah', 1, 4, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (7, '2022-09-28 12:06:02', '2022-09-28 12:16:02', 1, 5.99, 1, 'Blah blah blah', 1, 5, 3);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (8, '2022-09-28 12:07:02', '2022-09-28 12:17:02', 1, 4.99, 1, 'Blah blah blah', 1, 6, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (9, '2022-09-28 12:08:02', '2022-09-28 12:18:02', 1, 3.99, 1, 'Blah blah blah', 1, 7, 2);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (10, '2022-09-28 12:09:02', '2022-09-28 12:19:02', 1, 3.99, 1, 'Blah blah blah', 1, 8, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_has_dietary_needs`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `pet_has_dietary_needs` (`pet_id`, `dietary_needs_id`) VALUES (1, 1);
INSERT INTO `pet_has_dietary_needs` (`pet_id`, `dietary_needs_id`) VALUES (2, 2);
INSERT INTO `pet_has_dietary_needs` (`pet_id`, `dietary_needs_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (1, 'My Husky feels so much better now that he eats this.', 'Solid ingredients at a reasonable price.', 1, '2022-09-28 12:00:02', 1, NULL);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (2, 'Specter runs when he hears me open the bag.', 'We tried multiple brands, and this one finally works.', 1, '2022-09-28 12:00:02', 1, 1);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (3, 'Simply Awesome!', 'I tried everything before finding this.', 1, '2022-09-28 12:00:02', 2, NULL);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (4, 'Product comment 4', 'Description 4', 1, NULL, 3, NULL);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (5, 'Product comment 5 in reply to 4', 'Description 5', 2, NULL, 3, NULL);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (6, 'Product comment 6 in reply to 4', 'Description 6', 3, NULL, 3, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_has_breed`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `pet_has_breed` (`pet_id`, `breed_id`) VALUES (1, 1);
INSERT INTO `pet_has_breed` (`pet_id`, `breed_id`) VALUES (2, 2);
INSERT INTO `pet_has_breed` (`pet_id`, `breed_id`) VALUES (3, 1);
INSERT INTO `pet_has_breed` (`pet_id`, `breed_id`) VALUES (3, 2);

COMMIT;

