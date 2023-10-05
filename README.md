# **Web-приложение для курса**
# **Промышленная разработка на Java от Naumen**
## **Telegram-бот приюта бездомных животных**

### Функционал

### С позиции пользователя:
1. Просмотр животных приюта (разбивка по категориям - кошки, собаки; возможно по возрасту)
2. Действия с конкретным животным (запись на просмотр для того чтобы забрать животное, донат животному)
3. Заявка на размещение животного в приют (где-то на улице увидел бездомного пёселя)
4. Расписание работы приюта.
5. Запись для посещения приюта в свободный слот расписания.
6. Просмотр задач приюта.
7. Просмотр целевых сборов
8. Пожертвование

### С позиции владельца приюта:
1. Передача животного в добрые руки.
2. Внесение животного в БД.
3. Создание расписания приюта.
4. Создание задач приюта.
5. Создание целевых сборов


Отображение меню разделено на уровни доступа:
1. Мастер-админ
2. Модератор
3. Пользователь

### Мастер-админ
Может быть только один – может все:
- назначать уровни доступа
- добавлять/удалять:  
пользователей  
животных  
задачи  
заявки на размещение  
записи на посещение  
целевые сборы  

### Модератор
Может все то же самое, что и админ кроме назначения уровней доступа  

### Пользователь
Может:  
- получать:  
список животных и переходить в карточки определенных  
список задач и переходить в детали определенных  
расписание приюта
список целевых сборов  
- создавать/отменять:    
заявки на размещение животных  
заявки на посещение  
