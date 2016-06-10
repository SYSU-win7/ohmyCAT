drop table if exists user;

create table user(
	`id` int(11) not null auto_increment primary key, 
	`name` text not null
) engine=InnoDB auto_increment=9 default charset=utf8;

insert into user value(null, 'win7');
insert into user value(null, 'win8');
insert into user value(null, 'win9');
