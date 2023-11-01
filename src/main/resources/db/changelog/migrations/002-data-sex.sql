--changeset em2021:1
insert into petshelter.sex (name)
values ('male');

insert into petshelter.sex (name)
values ('female');
--rollback truncate table petshelter.sex;