--changeset em2021:1
insert into petshelter.animal (type, breed, name, birth_year, sex, color, eye_color, height, weight, photo_url)
values (2, 6, 'Дымок', 2020, 1, 3, 6, 30, 3231, 'https://disk.yandex.ru/i/7MW9EzaZ5bwRew');

insert into petshelter.animal (type, breed, name, birth_year, sex, color, eye_color, height, weight, photo_url)
values (2, 4, 'Пушок', 2018, 1, 3, 4, 26, 2923, 'https://disk.yandex.ru/i/ny0H5mCo_lpiaA');

insert into petshelter.animal (type, breed, name, birth_year, sex, color, eye_color, height, weight, photo_url)
values (2, 5, 'Кузя', 2017, 1, 5, 3, 25, 2423, 'https://disk.yandex.ru/i/1NHe-No9Mel4VQ');

insert into petshelter.animal (type, breed, name, birth_year, sex, color, eye_color, height, weight, photo_url)
values (1, 2, 'Батон', 2021, 1, 2, 5, 28, 7462, 'https://disk.yandex.ru/i/wzRtFfzp8TDA-Q');

insert into petshelter.animal (type, breed, name, birth_year, sex, color, eye_color, height, weight, photo_url)
values (1, 3, 'Лола', 2015, 2, 8, 5, 57, 30321, 'https://disk.yandex.ru/i/PFD3Nvh6ne1-pQ');

insert into petshelter.animal (type, breed, name, birth_year, sex, color, eye_color, height, weight, photo_url)
values (1, 1, 'Шарик', 2021, 1, 2, 8, 36, 10262, 'https://disk.yandex.ru/i/wkclfQlzpvgzXA');
--rollback truncate table petshelter.animal;