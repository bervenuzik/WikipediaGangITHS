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
  INDEX `type_id_idx` (`type_id` ASC) VISIBLE,
  CONSTRAINT `type_id`
    FOREIGN KEY (`type_id`)
    REFERENCES `wikipedia`.`user_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);

CREATE TABLE `wikipedia`.`theme` (
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
  INDEX `person_id_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `person_id`
    FOREIGN KEY (`person_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);
CREATE TABLE `wikipedia`.`article` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `author_id` INT NOT NULL,
  `theme_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `text` MEDIUMTEXT NOT NULL,
  `borrower_id` INT NOT NULL,
  `num_views` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `author_id_idx` (`author_id` ASC) VISIBLE,
  INDEX `borrower_id_idx` (`borrower_id` ASC) VISIBLE,
  INDEX `theme_id_idx` (`theme_id` ASC) VISIBLE,
  INDEX `status_id_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `borrower_id`
    FOREIGN KEY (`borrower_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `theme_id`
    FOREIGN KEY (`theme_id`)
    REFERENCES `wikipedia`.`theme` (`id`)
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
  INDEX `article_id_idx` (`article_id` ASC) VISIBLE,
  INDEX `borrower_id_idx` (`borrower_id` ASC) VISIBLE,
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
  INDEX `person_id_idx` (`person_id` ASC) VISIBLE,
  INDEX `article_id2_idx` (`article_id` ASC) VISIBLE,
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
  INDEX `person_id_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `person_id3`
    FOREIGN KEY (`person_id`)
    REFERENCES `wikipedia`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);
