--changeset em2021:1
insert into petshelter.animal_type (name)
values ('dog');

insert into petshelter.animal_type (name)
values ('cat');
--rollback truncate table petshelter.animal_type;