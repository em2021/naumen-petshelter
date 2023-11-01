--changeset em2021:1
create schema if not exists petshelter;
--rollback drop schema petshelter;

--changeset em2021:2
create table if not exists petshelter.sex (
	id serial primary key unique,
	name varchar(10) not null unique);
--rollback drop table petshelter.sex;

--changeset em2021:3
create table if not exists petshelter.user (
	id serial primary key unique,
	name varchar(255) constraint name_contains_only_letters check (name similar to '[A-z]*'),
	lastname varchar(255) constraint lastname_contains_only_letters check (lastname similar to '[A-z]*'),
	birthdate date,
	sex int not null references petshelter.sex (id),
	phone_number varchar(50) constraint phone_number_contains_only_numbers check (phone_number similar to '[0-9]*'),
	email varchar(255) not null,
	username varchar(255) not null,
	about text constraint text_less_than_1000_symbols check (char_length(about) <= 1000),
	registration_date timestamptz not null default now(),
	last_update timestamptz not null default now(),
	last_activity timestamptz not null default now());
--rollback drop table petshelter.user;

--changeset em2021:4
create table if not exists petshelter.color (
    id serial primary key unique,
	name varchar(255) not null,
	description text constraint text_less_than_200_symbols check (char_length(description) <= 200),
	creation_date timestamptz not null default now(),
	last_update timestamptz not null default now());
--rollback drop table petshelter.color;

--changeset em2021:5
create table if not exists petshelter.animal_type (
    id serial primary key unique,
	name varchar(255) not null unique,
	description text constraint text_less_than_200_symbols check (char_length(description) <= 200),
	creation_date timestamptz not null default now(),
	last_update timestamptz not null default now());
--rollback drop table petshelter.animal_type;

--changeset em2021:6
create table if not exists petshelter.animal_breed (
    id serial primary key,
    animal_type int not null references petshelter.animal_type (id),
	name varchar(255) not null,
	description text constraint text_less_than_200_symbols check (char_length(description) <= 200),
	creation_date timestamptz not null default now(),
	last_update timestamptz not null default now());
--rollback drop table petshelter.animal_breed;

--changeset em2021:7
create table if not exists petshelter.animal_status (
    id serial primary key unique,
	name varchar(255) not null unique,
	description text constraint text_less_than_200_symbols check (char_length(description) <= 200),
	creation_date timestamptz not null default now(),
	last_update timestamptz not null default now());
--rollback drop table petshelter.animal_status;

--changeset em2021:8
create table if not exists petshelter.animal (
    id serial primary key unique,
	type int not null references petshelter.animal_type (id),
	breed int not null references petshelter.animal_breed (id),
	name varchar(255),
	birth_year int,
	sex int not null references petshelter.sex (id),
	color int not null references petshelter.color (id),
	eye_color int not null references petshelter.color (id),
	height int,
	weight int,
	description text constraint text_less_than_1000_symbols check (char_length(description) <= 1000),
	photo_url text,
	registration_date timestamptz not null default now(),
	last_update timestamptz not null default now());
--rollback drop table petshelter.animal;

--changeset em2021:9
create table if not exists petshelter.animals_statuses_cross_ref (
    animal int not null references petshelter.animal (id),
	status int not null references petshelter.animal_status (id),
	creation_date timestamptz not null default now(),
	primary key (animal, status, creation_date));
--rollback drop table petshelter.animals_statuses_cross_ref;