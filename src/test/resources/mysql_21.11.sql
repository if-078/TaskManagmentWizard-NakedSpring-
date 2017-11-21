-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tmw
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tmw
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tmw` DEFAULT CHARACTER SET utf8 ;
USE `tmw` ;

-- -----------------------------------------------------
-- Table `tmw`.`priority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`priority` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tmw`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tmw`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`task` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_date` TIMESTAMP NULL DEFAULT NULL,
  `planning_date` TIMESTAMP NULL DEFAULT NULL,
  `start_date` TIMESTAMP NULL DEFAULT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `estimate_time` TIME NULL DEFAULT NULL,
  `assign_to` INT(11) NULL DEFAULT NULL,
  `status_id` INT(11) NULL DEFAULT NULL,
  `priority_id` INT(11) NULL DEFAULT NULL,
  `parent_id` INT(11) NULL DEFAULT NULL,
  `author_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Tasks_Status1` (`status_id` ASC),
  INDEX `fk_Tasks_Priorities1` (`priority_id` ASC),
  CONSTRAINT `fk_Tasks_Priorities1`
    FOREIGN KEY (`priority_id`)
    REFERENCES `tmw`.`priority` (`id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Tasks_Status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `tmw`.`status` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tmw`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pass` BINARY(60) NOT NULL,
  `email` VARCHAR(254) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tmw`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `comment_text` TEXT NOT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `task_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Comments_Tasks1` (`task_id` ASC),
  INDEX `fk_Comments_Users1` (`user_id` ASC),
  CONSTRAINT `fk_Comments_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES `tmw`.`task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comments_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `tmw`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tmw`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`tag` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Tags_Users1` (`user_id` ASC),
  CONSTRAINT `fk_Tags_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `tmw`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tmw`.`tags_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`tags_tasks` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tag_id` INT(11) NOT NULL,
  `task_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tags_tasks_Tags1` (`tag_id` ASC),
  INDEX `fk_tags_tasks_Tasks1` (`task_id` ASC),
  CONSTRAINT `fk_tags_tasks_Tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `tmw`.`tag` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tags_tasks_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES `tmw`.`task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tmw`.`users_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tmw`.`users_tasks` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `task_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_tasks_Users1` (`user_id` ASC),
  INDEX `fk_users_tasks_Tasks1` (`task_id` ASC),
  CONSTRAINT `fk_users_tasks_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES `tmw`.`task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_tasks_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `tmw`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
