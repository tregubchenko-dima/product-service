--liquibase formatted sql
--changeset tregubchenko:initial

insert into product (id, name, price, count)
values ('84c44672-32c2-40ef-977b-f85616daae55', 'Monitor', 30000, 5),
       ('84c44672-32c2-40ef-977b-f85616daae56', 'Keyboard', 10000, 5),
       ('84c44672-32c2-40ef-977b-f85616daae57', 'Mouse', 10000, 5);
