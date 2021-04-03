-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

insert into brand(name) values ('XIAOMI');
insert into brand(name) values ('Apple');
insert into brand(name) values ('Honor');
insert into brand(name) values ('Samsung');

insert into smartphone(model_name, brand_id, price, cpu, gpu, ram, rom, description)
values ('A2 Lite', (select id from brand where name = 'XIAOMI'), 12990, 'Snapdragon 420', 'auf 223', 4, 64, 'Lorem Ipsum');

insert into purchase_order(number, full_price, status, user_id) values (1111, 140000, 'PROCESSING', 1);

insert into order_smartphones(smartphone_id, order_id) values (1,1);
