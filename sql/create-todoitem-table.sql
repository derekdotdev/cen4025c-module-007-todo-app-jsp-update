CREATE DATABASE 'todo-app-jsp-update';
USE todo-app-jsp-update;

create table todo_items (

	id int(3) NOT NULL AUTO_INCREMENT,
	description varchar(120) NOT NULL,
	PRIMARY KEY (id)

);