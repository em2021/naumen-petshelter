--changeset em2021:1
insert into petshelter.animal_breed (animal_type, name)
values ('1', 'Mixed');

insert into petshelter.animal_breed (animal_type, name)
values ('1', 'French Bulldog');

insert into petshelter.animal_breed (animal_type, name)
values ('1', 'Labrador Retriever');

insert into petshelter.animal_breed (animal_type, name)
values ('2', 'Mixed');

insert into petshelter.animal_breed (animal_type, name)
values ('2', 'Siamese');

insert into petshelter.animal_breed (animal_type, name)
values ('2', 'British Shorthair');
--rollback truncate table petshelter.animal_breed;