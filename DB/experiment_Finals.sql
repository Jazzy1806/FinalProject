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
  `profile_image_url` VARCHAR(200) NULL,
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
  `color` VARCHAR(45) NULL,
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
  `title` VARCHAR(45) NOT NULL,
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
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `role`, `bio`, `profile_image_url`) VALUES (1, 'Ad', 'Min', 'admin', '$2a$10$yT3X.SqpZ8Z5quWdaD.HhubjpYAKGEKDZy4irFZ0G.PwGuBHjVV6O', NULL, 1, 4, NULL, NULL, '1', NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `role`, `bio`, `profile_image_url`) VALUES (2, 'Dog', 'Gy', 'doggy', '$2a$10$yT3X.SqpZ8Z5quWdaD.HhubjpYAKGEKDZy4irFZ0G.PwGuBHjVV6O', NULL, 1, 5, NULL, NULL, '2', NULL, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `address_id`, `created_on`, `updated_on`, `role`, `bio`, `profile_image_url`) VALUES (3, 'Dog', 'Gy2', 'doggy2', '$2a$10$yT3X.SqpZ8Z5quWdaD.HhubjpYAKGEKDZy4irFZ0G.PwGuBHjVV6O', NULL, 1, 6, NULL, NULL, '3', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `store`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `store` (`id`, `name`, `address_id`, `description`, `website_url`, `logo_url`, `chain_id`, `enabled`, `user_id`) VALUES (1, 'Petco on Ken Pratt', 1, '', '', '', 1, 1, 1);
INSERT INTO `store` (`id`, `name`, `address_id`, `description`, `website_url`, `logo_url`, `chain_id`, `enabled`, `user_id`) VALUES (2, 'Petssmart on Hover', 2, NULL, NULL, NULL, 2, 1, 1);
INSERT INTO `store` (`id`, `name`, `address_id`, `description`, `website_url`, `logo_url`, `chain_id`, `enabled`, `user_id`) VALUES (3, 'Petsmart in Boulder', 3, NULL, NULL, NULL, 2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (1, 'Pacific Catch Recipe', 'Merrick', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (2, 'Some type', 'Iams', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (3, 'Generic name here', 'Generic brand here', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (4, 'Green food', 'Yam', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (5, 'Stochmas pamper', 'Yan', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (6, 'easy bean', 'Merrick', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (7, 'salmon grill', 'Petpamper', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (8, 'pork bean deli', 'Petpamper', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (9, 'green stotch', 'Meri', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);
INSERT INTO `product` (`id`, `name`, `brand`, `description`, `image`, `created_on`, `updated_on`, `enabled`) VALUES (10, 'lick and chew', 'Yam', 'Merrick Backcountry - Raw Infused with Healthy Grains - Pacific Catch Recipe + Grains Dry Dog Food is an ancestral canine diet packed with protein dogs crave. This natural formula provides your dog with the nutritional benefits of a raw diet they would have discovered in the wild in a safe and convenient recipe that\'s easy to serve.', 'https://www.merrickresources.com/web-resources/products/MER-Backcountry-Dog-Pacific-Catch-lg.png', '2022-09-28 12:08:02', '2022-09-28 12:08:02', 1);

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
INSERT INTO `group_message` (`id`, `message`, `created`, `squad_id`, `user_id`) VALUES (1, 'Is this showing?', NULL, 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `message`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (1, 'First Message', NULL, 1, 2);
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (2, 'Second Message', NULL, 2, 1);
INSERT INTO `message` (`id`, `message_content`, `created`, `message_from`, `message_to`) VALUES (3, 'Third Message', NULL, 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_member`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `group_member` (`id`, `user_id`, `squad_id`) VALUES (1, 2, 3);

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
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 1, 12.99, 10, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (2, 2, 25.45, 12, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (3, 3, 10.99, 13, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 4, 11.99, 14, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 5, 8.99, 15, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 6, 15.99, 16, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 7, 32.50, 17, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 8, 12.99, 18, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 9, 13.99, 19, 1, NULL, NULL);
INSERT INTO `inventory` (`store_id`, `product_id`, `price`, `quantity`, `enabled`, `created_on`, `updated_on`) VALUES (1, 10, 14.99, 20, 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `pet` (`id`, `name`, `weight`, `user_id`, `gender`, `image`, `birth_date`, `enabled`, `color`) VALUES (1, 'Specter', 70.0, 1, 'male', NULL, NULL, 1, 'white-black');
INSERT INTO `pet` (`id`, `name`, `weight`, `user_id`, `gender`, `image`, `birth_date`, `enabled`, `color`) VALUES (2, 'Peepers', 80.1, 2, 'female', NULL, NULL, 1, 'brown');
INSERT INTO `pet` (`id`, `name`, `weight`, `user_id`, `gender`, `image`, `birth_date`, `enabled`, `color`) VALUES (3, 'Marley', 56.7, 3, 'non-binary', NULL, NULL, 1, 'brown-white');

COMMIT;


-- -----------------------------------------------------
-- Data for table `breed`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `breed` (`id`, `name`, `color`) VALUES (1, 'Husky', 'white-black');
INSERT INTO `breed` (`id`, `name`, `color`) VALUES (2, 'Staffordshire Terrier', 'brown');
INSERT INTO `breed` (`id`, `name`, `color`) VALUES (3, 'Brindel Boxer', 'brown-white');

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
INSERT INTO `ingredient` (`id`, `name`) VALUES (1, 'chicken');
INSERT INTO `ingredient` (`id`, `name`) VALUES (2, 'soy');
INSERT INTO `ingredient` (`id`, `name`) VALUES (3, 'beef');
INSERT INTO `ingredient` (`id`, `name`) VALUES (4, 'rice');
INSERT INTO `ingredient` (`id`, `name`) VALUES (5, 'salmon');
INSERT INTO `ingredient` (`id`, `name`) VALUES (6, 'white fish');
INSERT INTO `ingredient` (`id`, `name`) VALUES (7, 'pork');
INSERT INTO `ingredient` (`id`, `name`) VALUES (8, 'lamb');

COMMIT;


-- -----------------------------------------------------
-- Data for table `ingredient_has_product`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (1, 1);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (2, 2);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (3, 1);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (3, 2);
INSERT INTO `ingredient_has_product` (`product_id`, `ingredient_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `product_report`
-- -----------------------------------------------------
START TRANSACTION;
USE `doggydb`;
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (1, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 9.99, 1, 'Finally!', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (2, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 10.99, 1, 'Love this stuff', 2, 2, 2);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (3, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 11.99, 1, 'Took long enough', 3, 3, 3);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (4, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 8.99, 1, 'Blah blah blah', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (5, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 7.99, 1, 'Blah blah blah', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (6, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 6.99, 1, 'Blah blah blah', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (7, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 5.99, 1, 'Blah blah blah', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (8, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 4.99, 1, 'Blah blah blah', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (9, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 3.99, 1, 'Blah blah blah', 1, 1, 1);
INSERT INTO `product_report` (`id`, `created_on`, `updated_on`, `user_quantity`, `price`, `is_in_stock`, `remark`, `user_id`, `product_id`, `store_id`) VALUES (10, '2022-09-28 08:54:51', '2022-09-28 08:54:51', 1, 3.99, 1, 'Blah blah blah', 1, 1, 1);

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
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (1, 'Product comment 1', 'Description 1', 1, NULL, 1, NULL);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (2, 'Product comment 2 in reply to 1', 'Description 2', 2, NULL, 1, 1);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (3, 'Product comment 3 in reply to 2', 'Description 3', 3, NULL, 1, 2);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (4, 'Product comment 4', 'Description 4', 1, NULL, 2, NULL);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (5, 'Product comment 5 in reply to 4', 'Description 5', 2, NULL, 2, 4);
INSERT INTO `product_comment` (`id`, `title`, `description`, `rating`, `created_on`, `product_id`, `in_reply_to_id`) VALUES (6, 'Product comment 6 in reply to 4', 'Description 6', 3, NULL, 2, 4);

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

