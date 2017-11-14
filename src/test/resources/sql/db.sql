CREATE SCHEMA IF NOT EXISTS tmw;
USE tmw;

-- -----------------------------------------------------
-- Table tmw.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.user (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `pass` CHAR(32) NULL,
  `email` VARCHAR(254) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tmw.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.status(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tmw.`priority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.priority (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tmw.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.task (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_date` TIMESTAMP NULL,
  `planning_date` TIMESTAMP NULL,
  `start_date` TIMESTAMP NULL,
  `end_date` TIMESTAMP NULL,
  `estimate_time` TIME NULL,
  `assign_to` INT NULL,
  `status_id` INT NULL,
  `priority_id` INT NULL,
  `parent_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Tasks_Status1`
    FOREIGN KEY (`status_id`)
    REFERENCES tmw.status(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Tasks_Priorities1`
    FOREIGN KEY (`priority_id`)
    REFERENCES tmw.priority (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tmw.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.comment (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `created_date` DATETIME NULL,
  `task_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Comments_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES tmw.task (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Comments_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES tmw.user (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tmw.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.tag (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Tags_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES tmw.user (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tmw.`tags_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.tags_tasks (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tag_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tags_tasks_Tags1`
    FOREIGN KEY (`tag_id`)
    REFERENCES tmw.tag (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tags_tasks_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES tmw.task (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table tmw.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.role(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table tmw.`users_tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tmw.users_tasks (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `task_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_users_tasks_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES tmw.user(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_tasks_Tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES tmw.task(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_tasks_Roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES tmw.role (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

