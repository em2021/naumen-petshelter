--changeset em2021:1
insert into petshelter.animal_status (name, description)
values ('arrived', 'Just arrived to the shelter, has to pass registration and health check procedures');

insert into petshelter.animal_status (name, description)
values ('resident', 'Registered, vaccinated and is waiting for a new owner');

insert into petshelter.animal_status (name, description)
values ('on treatment', 'Undergoing some sort of treatment, and unable to meet anyone');

insert into petshelter.animal_status (name, description)
values ('waiting to be picked up', 'Has found a new owner and is waiting to be picked up by him');

insert into petshelter.animal_status (name, description)
values ('adopted', 'Adopted by a new owner');

insert into petshelter.animal_status (name, description)
values ('returned', 'Returned by the previous owner');

insert into petshelter.animal_status (name, description)
values ('put to sleep', 'Has been put to sleep due to medical conditions');
--rollback truncate table petshelter.animal_status;