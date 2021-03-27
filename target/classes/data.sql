-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

insert into genre (name) values ('Классика');
insert into genre (name) values ('Фантастика');
insert into genre (name) values ('Зарубежная литература');

insert into book (author, title, price) values ('А.С. Пушкин', 'Евгений Онегин', 11.22);
insert into book (author, title, price) values ('А.С. Пушкин', 'Капитанская дочка', 1.33);
insert into book (author, title, price) values ('Л.Н. Толстой', 'Война и мир', 99.22);
insert into book (author, title, price) values ('А. Беляев', 'Звезда КЭЦ', 7.43);
insert into book (author, title, price) values ('А. Дюма', 'Три мушкетера', 17.43);

insert into book_genre(book_id, genre_id) values (1,1);
insert into book_genre(book_id, genre_id) values (2,1);
insert into book_genre(book_id, genre_id) values (3,1);
insert into book_genre(book_id, genre_id) values (4,1);
insert into book_genre(book_id, genre_id) values (4,2);
insert into book_genre(book_id, genre_id) values (5,1);
insert into book_genre(book_id, genre_id) values (5,3);

insert into brand(name) values ('XIAOMI');
insert into brand(name) values ('Apple');
insert into brand(name) values ('Honor');
insert into brand(name) values ('Samsung');

insert into smartphone(model_name, brand_id, price, cpu, gpu, ram, rom, description)
values ('A2 Lite', (select id from brand where name = 'XIAOMI'), 12990, 'Snapdragon 420', 'auf 223', 4, 64, 'Lorem Ipsum');
