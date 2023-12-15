-- noinspection SqlNoDataSourceInspectionForFile

CREATE SCHEMA `wikipedia`;

CREATE TABLE `wikipedia`.`user_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `wikipedia`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `type_id`
    FOREIGN KEY (`type_id`)
    REFERENCES `wikipedia`.`user_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);

CREATE TABLE `wikipedia`.`article_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `wikipedia`.`article_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `wikipedia`.`login_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `person_id`
    FOREIGN KEY (`person_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);

CREATE TABLE `wikipedia`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `author_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  `num_views` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `category_id`
    FOREIGN KEY (`category_id`)
    REFERENCES `wikipedia`.`category` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `status_id`
    FOREIGN KEY (`status_id`)
    REFERENCES `wikipedia`.`article_status` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);

CREATE TABLE `wikipedia`.`article_borrower_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `borrower_id` INT NOT NULL,
  `borrow_date` DATE NOT NULL,
  `return_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `article_id`
    FOREIGN KEY (`article_id`)
    REFERENCES `wikipedia`.`article` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `borrower_id2`
    FOREIGN KEY (`borrower_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);


CREATE TABLE `wikipedia`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` TEXT(400) NOT NULL,
  `person_id` INT NOT NULL,
  `article_id` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `person_id2`
    FOREIGN KEY (`person_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `article_id2`
    FOREIGN KEY (`article_id`)
    REFERENCES `wikipedia`.`article` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);

CREATE TABLE `wikipedia`.`error_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(100) NOT NULL,
  `person_id` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `person_id3`
    FOREIGN KEY (`person_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);
