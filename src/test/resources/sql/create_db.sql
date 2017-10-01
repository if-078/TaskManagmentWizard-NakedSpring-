CREATE SCHEMA IF NOT EXISTS "tmw" ;
USE "tmw" ;

-- -----------------------------------------------------
-- Table `task management wizard`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."User" (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pass` CHAR(32) NULL,
  `email` VARCHAR(254) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."Status" (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Priorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."Priority" (
  `priority_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`priority_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."Task" (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_date` TIMESTAMP NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `estimate_time` TIMESTAMP NULL,
  `assign_to` INT NULL,
  `status_id` INT NULL,
  `priority_id` INT NULL,
  PRIMARY KEY (`task_id`),
    FOREIGN KEY (`status_id`)
    REFERENCES "tmw"."Status" (`status_id`),
    FOREIGN KEY (`priority_id`)
    REFERENCES "tmw"."Priority" (`priority_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."Comment" (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `created_date` TIMESTAMP NULL,
  `task_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`comment_id`),
     FOREIGN KEY (`task_id`)
    REFERENCES "tmw"."Task" (`task_id`),
    FOREIGN KEY (`user_id`)
    REFERENCES "tmw"."User" (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task management wizard`.`Tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."Tag" (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`tag_id`),
    FOREIGN KEY (`user_id`)
    REFERENCES "tmw"."User" (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task management wizard`.`tags_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."tags_tasks" (
  `tag_task_id` INT NOT NULL AUTO_INCREMENT,
  `tag_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  PRIMARY KEY (`tag_task_id`, `tag_id`, `task_id`),
    FOREIGN KEY (`tag_id`)
    REFERENCES "tmw"."Tag" (`tag_id`),
    FOREIGN KEY (`task_id`)
    REFERENCES "tmw"."Task" (`task_id`))
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `task management wizard`.`Roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."Role" (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `task management wizard`.`users_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "tmw"."users_tasks" (
  `users_tasks_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`users_tasks_id`),
    FOREIGN KEY (`user_id`)
    REFERENCES "tmw"."User" (`user_id`),
    FOREIGN KEY (`task_id`)
    REFERENCES "tmw"."Task" (`task_id`),
    FOREIGN KEY (`role_id`)
    REFERENCES "tmw"."Role" (`role_id`))
ENGINE = InnoDB;