INSERT INTO `tmw`.`priority` (`name`) VALUES ('High');
 INSERT INTO `tmw`.`priority` (`name`) VALUES ('Normal');
 INSERT INTO `tmw`.`priority` (`name`) VALUES ('Low');

 INSERT INTO `tmw`.`role` (`name`) VALUES ('Manager');
 INSERT INTO `tmw`.`role` (`name`) VALUES ('Developer');

 INSERT INTO `tmw`.`status` (`name`) VALUES ('To do');
 INSERT INTO `tmw`.`status` (`name`) VALUES ('In progress');
 INSERT INTO `tmw`.`status` (`name`) VALUES ('Done');

 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Artem', '1111', 'artem@gmail.com');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Rostyslav', '1111', 'rostyk@gmail.com');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Svitlana', '1111', 'sveta@gmail.com');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Makar', '1111', 'makar@gmail.com');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Oleg', '1111', 'oleg@gmail.com');
 INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Roman', '1111', 'roman@gmail.com');
INSERT INTO `tmw`.`user` (`name`, `pass`, `email`) VALUES ('Dmytro', '1111', 'dima@gmail.com');

 INSERT INTO `tmw`.`task` (`name`, `created_date`, `planning_date`, `start_date`, `end_date`, `estimate_time`, `status_id`, `priority_id`, `parent_id`) VALUES ('Time Manager Wizard', '2017-10-01 00:00:00', '2017-10-01 00:00:00', '2017-10-01 00:00:00', '2017-12-01 00:00:00', '01:00:00', '2', '1', '0');

INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `status_id`, `priority_id`, `parent_id`) VALUES ('Documentation', '2017-10-01 00:00:00', '2017-10-01 00:00:00', '2017-12-01 00:00:00', '01:00:00', '2', '1', '1');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `status_id`, `priority_id`, `parent_id`) VALUES ('User interface', '2017-10-01 00:00:00', '2017-10-01 00:00:00', '2017-12-01 00:00:00', '01:00:00', '2', '1', '1');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `status_id`, `priority_id`, `parent_id`) VALUES ('Backend', '2017-10-01 00:00:00', '2017-10-01 00:00:00', '2017-12-01 00:00:00', '01:00:00', '2', '1', '1');


INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('UML diagrams', '2017-10-01 00:00:00', '3', '2', '2');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Use-case diagram', '2017-10-01 00:00:00', '3', '1', '2');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Clacces diagram', '2017-10-01 00:00:00', '3', '1', '2');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Write specification', '2017-10-01 00:00:00', '3', '3', '2');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Write HTML-page', '2017-10-01 00:00:00', '3', '2', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add CSS for page', '2017-10-01 00:00:00', '3', '3', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Use JavaScript. jQuery', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Introduce AJAX', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Develop JS file for main page', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add JS file for filters and calendar', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Design filters', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Design tree of tasks', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Design table and full view of task', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Design calendar', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add registration and login pages', '2017-10-01 00:00:00', '3', '1', '3');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Create database with necessary tables', '2017-10-01 00:00:00', '3', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Create entites', '2017-10-01 00:00:00', '3', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Develop DAO for each entity', '2017-10-01 00:00:00', '3', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add DTO for table of tasks on UI', '2017-10-01 00:00:00', '3', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add DTO for full task on UI', '2017-10-01 00:00:00', '3', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add DTO for calendar task', '2017-10-01 00:00:00', '3', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Write services for each DAO', '2017-10-01 00:00:00', '2', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Develop mappers for task entity', '2017-10-01 00:00:00', '2', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Use JOOQ for SQL queries', '2017-10-01 00:00:00', '2', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Develop controller-layer', '2017-10-01 00:00:00', '2', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add security', '2017-10-01 00:00:00', '2', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Develop populator for tests', '2017-10-01 00:00:00', '1', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Write unit-tests', '2017-10-01 00:00:00', '1', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Write integration tests', '2017-10-01 00:00:00', '1', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add validation', '2017-10-01 00:00:00', '3', '1', '4');




UPDATE `tmw`.`task` SET `start_date`='2017-11-01 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='01:00:00', `assign_to`='1' WHERE `id`='5';
UPDATE `tmw`.`task` SET `start_date`='2017-11-01 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='03:00:00', `assign_to`='2' WHERE `id`='6';
UPDATE `tmw`.`task` SET `start_date`='2017-11-01 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='03:00:00', `assign_to`='3' WHERE `id`='7';
UPDATE `tmw`.`task` SET `start_date`='2017-11-01 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='03:00:00', `assign_to`='4' WHERE `id`='8';
UPDATE `tmw`.`task` SET `start_date`='2017-11-02 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='02:00:00', `assign_to`='5' WHERE `id`='9';
UPDATE `tmw`.`task` SET `start_date`='2017-11-02 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='01:00:00', `assign_to`='6' WHERE `id`='10';
UPDATE `tmw`.`task` SET `start_date`='2017-11-02 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='01:00:00', `assign_to`='7' WHERE `id`='11';
UPDATE `tmw`.`task` SET `start_date`='2017-11-03 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='01:00:00', `assign_to`='1' WHERE `id`='12';
UPDATE `tmw`.`task` SET `start_date`='2017-11-03 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='20:00:00', `assign_to`='2' WHERE `id`='13';
UPDATE `tmw`.`task` SET `start_date`='2017-11-03 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='20:00:00', `assign_to`='3' WHERE `id`='14';
UPDATE `tmw`.`task` SET `start_date`='2017-11-03 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='05:00:00', `assign_to`='4' WHERE `id`='15';
UPDATE `tmw`.`task` SET `start_date`='2017-11-06 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='04:00:00', `assign_to`='5' WHERE `id`='16';
UPDATE `tmw`.`task` SET `start_date`='2017-11-06 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='06:00:00', `assign_to`='6' WHERE `id`='17';
UPDATE `tmw`.`task` SET `start_date`='2017-11-06 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='07:00:00', `assign_to`='7' WHERE `id`='18';
UPDATE `tmw`.`task` SET `start_date`='2017-11-07 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='04:00:00', `assign_to`='1' WHERE `id`='19';
UPDATE `tmw`.`task` SET `start_date`='2017-11-08 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='05:00:00', `assign_to`='2' WHERE `id`='20';
UPDATE `tmw`.`task` SET `start_date`='2017-11-09 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='08:00:00', `assign_to`='3' WHERE `id`='21';
UPDATE `tmw`.`task` SET `start_date`='2017-11-10 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='15:00:00', `assign_to`='4' WHERE `id`='22';
UPDATE `tmw`.`task` SET `start_date`='2017-11-11 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='01:00:00', `assign_to`='5' WHERE `id`='23';
UPDATE `tmw`.`task` SET `start_date`='2017-11-12 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='02:00:00', `assign_to`='6' WHERE `id`='24';
UPDATE `tmw`.`task` SET `start_date`='2017-11-10 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='01:00:00', `assign_to`='7' WHERE `id`='25';
UPDATE `tmw`.`task` SET `name`='Write service layer', `start_date`='2017-11-11 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='01:00:00', `assign_to`='1' WHERE `id`='26';
UPDATE `tmw`.`task` SET `start_date`='2017-11-14 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='03:00:00', `assign_to`='2' WHERE `id`='27';
UPDATE `tmw`.`task` SET `start_date`='2017-11-12 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='02:00:00', `assign_to`='3' WHERE `id`='28';
UPDATE `tmw`.`task` SET `start_date`='2017-11-13 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='07:00:00', `assign_to`='4' WHERE `id`='29';
UPDATE `tmw`.`task` SET `start_date`='2017-11-13 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='18:00:00', `assign_to`='5' WHERE `id`='30';
UPDATE `tmw`.`task` SET `start_date`='2017-11-14 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='03:00:00', `assign_to`='6' WHERE `id`='31';
UPDATE `tmw`.`task` SET `start_date`='2017-11-15 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='05:00:00', `assign_to`='7' WHERE `id`='32';
UPDATE `tmw`.`task` SET `start_date`='2017-11-15 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='07:00:00', `assign_to`='1' WHERE `id`='33';
UPDATE `tmw`.`task` SET `start_date`='2017-11-16 00:00:00', `end_date`='2017-12-01 00:00:00', `estimate_time`='04:00:00', `assign_to`='2' WHERE `id`='34';
INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Fix tests', '2017-10-01 00:00:00', '2017-11-17 00:00:00', '2017-11-17 00:00:00', '02:00:00', '3', '1', '2', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Improw team plannint', '2017-10-01 00:00:00', '2017-11-17 00:00:00', '2017-11-17 00:00:00', '04:00:00', '4', '1', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Fix drag and drop in calendar', '2017-10-01 00:00:00', '2017-11-17 00:00:00', '2017-11-17 00:00:00', '06:00:00', '5', '1', '1', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add tags view to task', '2017-10-01 00:00:00', '2017-11-17 00:00:00', '2017-11-17 00:00:00', '03:00:00', '6', '1', '2', '4');
INSERT INTO `tmw`.`task` (`name`, `created_date`, `start_date`, `end_date`, `estimate_time`, `assign_to`, `status_id`, `priority_id`, `parent_id`) VALUES ('Add list of comments to task', '2017-10-01 00:00:00', '2017-11-17 00:00:00', '2017-11-17 00:00:00', '05:00:00', '7', '1', '3', '4');



INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#db', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#dao', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#security', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#java', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#sql', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#ui', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#spring', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#test', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#dto', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#JS', '1');
 INSERT INTO `tmw`.`tag` (`name`, `user_id`) VALUES ('#documentation', '1');


INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('11', '5');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('11', '6');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('11', '7');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('11', '8');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '11');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '12');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '13');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '14');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '9');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '10');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '11');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '12');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '13');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '14');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '15');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '16');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '17');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '18');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '19');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '20');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('5', '20');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('2', '22');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '22');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '23');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '24');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '25');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('9', '23');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('9', '24');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('9', '25');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '26');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '27');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('1', '28');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '28');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('5', '28');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '29');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '29');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('3', '30');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '30');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('7', '30');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '31');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('8', '31');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('8', '32');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('8', '33');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '34');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '34');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('7', '34');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('8', '35');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '36');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '36');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '36');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '37');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '37');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '37');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '38');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '38');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '38');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('4', '39');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('6', '39');
INSERT INTO `tmw`.`tags_tasks` (`tag_id`, `task_id`) VALUES ('10', '39');


INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '5', '1');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '6', '2');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '7', '3');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '8', '4');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '9', '5');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '10', '6');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '39', '7');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '38', '1');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '37', '2');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '36', '3');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '35', '4');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '34', '5');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '33', '6');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '32', '7');
INSERT INTO `tmw`.`comment` (`comment_text`, `created_date`, `task_id`, `user_id`) VALUES ('comment1', '2017-11-15 00:00:00', '31', '1');


 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('1', '1', '1');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('2', '2', '1');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('3', '3', '2');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('1', '2', '2');
 INSERT INTO `tmw`.`users_tasks` (`user_id`, `task_id`, `role_id`) VALUES ('2', '3', '2');

