--liquibase formatted sql

--changeset rniyazov:1
INSERT INTO type (name)
VALUES ('Pressure'),
       ('Voltage'),
       ('Temperature'),
       ('Humidity');

--changeset rniyazov:2
INSERT INTO unit (name)
VALUES ('bar'),
       ('voltage'),
       ('°С'),
       ('%');

--changeset rniyazov:3
INSERT INTO users (username, password, firstname, lastname, role)
VALUES ('admin@mail.ru', '{noop}123', 'admin', 'admin', 'ADMIN'),
       ('user@mail.ru', '{noop}123', 'user', 'user', 'VIEWER')