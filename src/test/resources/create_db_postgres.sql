
create table userr
(
	id serial
		primary key,
	name varchar(45) not null,
	pass varchar(200) not null,
	email varchar(254) null,
    active int default 0
)
;

create table user_activation
(
	user_key int not null,
    user_id int not null,
    constraint fk_user_id foreign key (user_id)
    references userr(id)
    			on update cascade on delete cascade
);


create table priority
(
	id serial
		primary key,
	name varchar(45) not null
)
;



create table status
(
	id serial
		primary key,
	name varchar(45) not null
)
;


create table task
(
	id serial
		primary key,
	name varchar(45) not null,
	created_date timestamp null,
	planning_date timestamp null,
	start_date timestamp null,
	end_date timestamp null,
	estimate_time int null,
	spent_time int null,
	left_time int null,
	assign_to int null,
	status_id int null,
	priority_id int null,
	parent_id int null,
	author_id int null,
	project_id int null,
	constraint fk_Tasks_Status1
		foreign key (status_id) references status (id)
			on update cascade on delete cascade,
	constraint fk_Tasks_Priorities1
		foreign key (priority_id) references priority (id)
			on update cascade
)
;

create index fk_Tasks_Priorities1
	on task (priority_id)
;

create index fk_Tasks_Status1
	on task (status_id)
;




create table users_tasks
(
	id serial
		primary key,
	user_id int not null,
	task_id int not null,
	constraint fk_users_tasks_Users1
		foreign key (user_id) references userr (id)
			on update cascade on delete cascade,
	constraint fk_users_tasks_Tasks1
		foreign key (task_id) references task (id)
			on update cascade on delete cascade
)
;

create index fk_users_tasks_Tasks1
	on users_tasks (task_id)
;

create index fk_users_tasks_Users1
	on users_tasks (user_id)
;

create table comment
(
	id serial
		primary key,
	comment_text text not null,
	created_date timestamp null,
	task_id int not null,
	user_id int not null
)
;

create index fk_Comments_Tasks1
	on comment (task_id)
;

create index fk_Comments_Users1
	on comment (user_id)
;

alter table comment
	add constraint fk_Comments_Tasks1
		foreign key (task_id) references task (id)
			on update cascade on delete cascade
;

alter table comment
	add constraint fk_Comments_Users1
		foreign key (user_id) references userr (id)
			on update cascade on delete cascade
;

create table tag
(
	id serial
		primary key,
	name varchar(45) not null,
	user_id int not null,
	project_id int not null
)
;

create index fk_Tags_Users1
	on tag (user_id)
;

create index fk_Tags_Project
	on tag (project_id)
;

alter table tag
	add constraint fk_Tags_Users1
		foreign key (user_id) references userr (id)
;

alter table tag
	add constraint fk_Tags_Project
		foreign key (project_id) references task (id)
;



create table tags_tasks
(
	id serial
		primary key,
	tag_id int not null,
	task_id int not null,
	constraint fk_tags_tasks_Tags1
		foreign key (tag_id) references tag (id)
			on update cascade on delete cascade
)
;

create index fk_tags_tasks_Tags1
	on tags_tasks (tag_id)
;

create index fk_tags_tasks_Tasks1
	on tags_tasks (task_id)
;


alter table tags_tasks
	add constraint fk_tags_tasks_Tasks1
		foreign key (task_id) references task (id)
			on update cascade on delete cascade
;