--changeset em2021:1
insert into petshelter.animals_statuses_cross_ref (animal, status)
values (1, 1);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (1, 2);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (2, 1);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (2, 2);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (2, 3);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (3, 1);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (3, 2);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (4, 1);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (4, 2);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (4, 4);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (4, 5);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (5, 1);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (5, 2);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (6, 1);

insert into petshelter.animals_statuses_cross_ref (animal, status)
values (6, 2);
--rollback truncate table petshelter.animals_statuses_cross_ref;