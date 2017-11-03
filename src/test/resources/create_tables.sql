 INSERT INTO `tmw`.`priority` (`name`) VALUES ('high');
 INSERT INTO `tmw`.`priority` (`name`) VALUES ('mid');
 INSERT INTO `tmw`.`priority` (`name`) VALUES ('low');

 INSERT INTO `tmw`.`role` (`name`) VALUES ('manager');
 INSERT INTO `tmw`.`role` (`name`) VALUES ('developer');

 INSERT INTO `tmw`.`status` (`name`) VALUES ('to do');
 INSERT INTO `tmw`.`status` (`name`) VALUES ('in progress');
 INSERT INTO `tmw`.`status` (`name`) VALUES ('done');

 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Petro', '1111', 'petro@gmail.com');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Ivan', '2222', 'ivan@gmail.com');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Nadia', '3333', 'nadia@gmail.com');

 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Task manager', '2017-09-18', '2017-10-22 00:00:00', '2017-11-15 00:00:00', '02:00:00', '1', '2', '1', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('User control', '2017-09-19', '2017-10-23 00:00:00', '2017-11-15 00:00:00', '01:00:00', '2', '1', '2', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Create DB', '2017-09-15', '2017-10-25 00:00:00', '2017-11-15 00:00:00', '02:00:00', '1', '3', '1', '1');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Write DAO layer', '2017-09-16', '2017-11-01 00:00:00', '2017-11-15 00:00:00', '03:00:00', '2', '3', '1', '1');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add controllers and services', '2017-09-17', '2017-11-01 00:00:00', '2017-11-15 00:00:00', '02:00:00', '2', '2', '1', '1');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Fix frontend', '2017-09-23', '2017-11-01 00:00:00', '2017-11-15 00:00:00', '04:00:00', '3', '1', '2', '1');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Write tests', '2017-09-18', '2017-11-03 00:00:00', '2017-11-15 00:00:00', '05:00:00', '1', '2', '3', '1');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Design DB and entities', '2017-09-18 00:00:00', '2017-10-12 00:00:00', '2017-11-15 00:00:00', '02:00:00', '2', '3', '2', '2');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add validation for username, email, password', '2017-09-18 00:00:00', '2017-10-31 00:00:00', '2017-11-15 00:00:00', '02:00:00', '3', '2', '2', '2');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add encryption for password', '2017-09-18 00:00:00', '2017-11-01 00:00:00', '2017-11-15 00:00:00', '02:00:00', '3', '2', '1', '2');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Design UI', '2017-09-18 00:00:00', '2017-11-02 00:00:00', '2017-11-15 00:00:00', '02:00:00', '1', '1', '3', '2');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Fix controllers', '2017-09-18 00:00:00', '2017-10-25 00:00:00', '2017-11-15 00:00:00', '02:00:00', '2', '3', '2', '5');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add service layer', '2017-09-18 00:00:00', '2017-10-12 00:00:00', '2017-11-15 00:00:00', '02:00:00', '1', '2', '1', '5');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Security', '2017-10-12 00:00:00', '2017-10-16 00:00:00', '2017-11-15 00:00:00', '02:00:00', '3', '2', '1', '10');

 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#db', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#dao', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#security', '2');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#java', '3');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#sql', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#ui', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#spring', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#test', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#dto', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#JS', '1');

 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('1', '1', '1');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('2', '2', '1');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('3', '3', '2');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('1', '2', '2');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('2', '3', '2');

 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '1');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '1');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('7', '1');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('3', '2');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '2');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '3');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('5', '3');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '4');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('2', '4');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('9', '4');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '4');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '5');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('9', '5');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '6');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('8', '7');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '8');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('2', '8');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('9', '8');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '8');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '9');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('7', '9');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('3', '10');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('7', '10');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '11');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '11');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '12');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '13');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '13');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('7', '13');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('3', '14');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('7', '14');


 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment1', '1', '1', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment2', '2', '2', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment3', '3', '3', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment5', '1', '2', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment6', '2', '3', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment7', '3', '1', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment9', '1', '3', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment10', '2', '1', '2017-09-17 00:00:00');
