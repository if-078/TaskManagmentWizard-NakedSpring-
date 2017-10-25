 INSERT INTO `tmw`.`priority` (`name`) VALUES ('high');
 INSERT INTO `tmw`.`priority` (`name`) VALUES ('mid');
 INSERT INTO `tmw`.`priority` (`name`) VALUES ('low');

 INSERT INTO `tmw`.`role` (`name`) VALUES ('manager');
 INSERT INTO `tmw`.`role` (`name`) VALUES ('developer');

 INSERT INTO `tmw`.`status` (`name`) VALUES ('to do');
 INSERT INTO `tmw`.`status` (`name`) VALUES ('in progress');
 INSERT INTO `tmw`.`status` (`name`) VALUES ('done');

 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('user1', '1111', 'email1');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('user2', '2222', 'email2');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('user3', '3333', 'email3');

 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 1', '2017-09-18', '2017-09-21 00:00:00', '2017-09-22 00:00:00', '02:00:00', '1', '1', '3', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 2', '2017-09-19', '2017-09-23 00:00:00', '2017-09-24 00:00:00', '01:00:00', '2', '2', '2', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 3', '2017-09-18', '2017-09-20 00:00:00', '2017-09-26 00:00:00', '03:00:00', '3', '3', '1', '1');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 4', '2017-09-15', '2017-09-24 00:00:00', '2017-09-25 00:00:00', '02:00:00', '1', '1', '3', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 5', '2017-09-16', '2017-09-22 00:00:00', '2017-09-25 00:00:00', '03:00:00', '1', '2', '2', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 6', '2017-09-17', '2017-09-22 00:00:00', '2017-09-26 00:00:00', '02:00:00', '1', '3', '1', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 7', '2017-09-23', '2017-09-25 00:00:00', '2017-09-27 00:00:00', '04:00:00', '1', '1', '2', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('add smth 8', '2017-09-18', '2017-09-21 00:00:00', '2017-09-22 00:00:00', '05:00:00', '1', '2', '2', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('today1', '2017-09-18 00:00:00', '2017-10-12 00:00:00', '2017-10-12 00:00:00', '02:00:00', '1', '1', '3', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('today2', '2017-09-18 00:00:00', '2017-10-13 00:00:00', '2017-10-12 00:00:00', '02:00:00', '1', '2', '2', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('task1', '2017-09-18 00:00:00', '2017-10-09 00:00:00', '2017-10-12 00:00:00', '02:00:00', '1', '3', '1', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('task2', '2017-09-18 00:00:00', '2017-10-10 00:00:00', '2017-10-12 00:00:00', '02:00:00', '1', '1', '3', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('task3', '2017-09-18 00:00:00', '2017-10-11 00:00:00', '2017-10-12 00:00:00', '02:00:00', '1', '2', '2', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('task4', '2017-09-18 00:00:00', '2017-10-12 00:00:00', '2017-10-12 00:00:00', '02:00:00', '1', '3', '1', '0');
 INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('task5', '2017-10-12 00:00:00', '2017-10-16 00:00:00', '2017-10-12 00:00:00', '02:00:00', '1', '1', '1', '0');



 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('tag1', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('tag2', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('tag3', '2');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('tag4', '3');

 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('1', '1', '1');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('2', '2', '1');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('3', '3', '2');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('1', '2', '2');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('2', '3', '2');


 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '1');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '2');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '3');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('2', '1');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('2', '2');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('2', '3');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('3', '1');
 INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('3', '2');

 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment1', '1', '1', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment2', '2', '2', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment3', '3', '3', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment5', '1', '2', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment6', '2', '3', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment7', '3', '1', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment9', '1', '3', '2017-09-17 00:00:00');
 INSERT INTO `tmw`.`comment` (`comment`, `task_id`, `user_id`, `created_date`) VALUES ('comment10', '2', '1', '2017-09-17 00:00:00');
