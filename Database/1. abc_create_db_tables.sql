-- create database
drop database if exists abc_restaurant_db;
create database abc_restaurant_db;

-- switch to above database
use abc_restaurant_db;

-- create table customer
create table customer (
customer_id bigint auto_increment,
first_name varchar(30) not null,
last_name varchar(30) not null,
phone varchar(16) null,
email varchar (30) null,
constraint pk_customer primary key clustered (customer_id asc)
);
-- create table employee
create table employee (
employee_id int auto_increment,
first_name varchar(30) not null,
last_name varchar(30) not null,
phone varchar(16) null,
email varchar (30) null,
title varchar(30) not null,
sin varchar(11) not null,
dob date not null,
address varchar (100) not null,
start_date date not null,
termination_date date null,
salary double not null,
user_name varchar(30) not null,
password varchar(30) not null,
otp int null,
constraint pk_employee primary key clustered (employee_id asc)
);

-- reservation table
create table reservation (
reservation_id bigint auto_increment not null,
customer_id bigint not null,
reservation_date date not null,
reservation_start time not null,
reservation_end time not null, -- for breakfast duration 1.5 hours for dinner 2 hours
guest_number int not null,
no_of_table int not null,
communication_mode varchar(10) not null,
special_request varchar(255) null,
update_status varchar(15) null,
updated_by int null,
constraint pk_reservation primary key clustered (reservation_id asc),
constraint fk_reservation_customer foreign key (customer_id) references customer(customer_id)
);

drop view if exists abc_restaurant_db.reservation_view;
create view reservation_view AS
SELECT r.reservation_id, c.customer_id, c.first_name, c.last_name, c.phone, c.email, r.reservation_date, r.reservation_start, r.reservation_end, r.guest_number, r.no_of_table, r.communication_mode, r.special_request, r.update_status, r.updated_by
FROM reservation AS r
LEFT JOIN customer AS c
ON r.customer_id = c.customer_id;

