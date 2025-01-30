--liquibase formatted sql

--changeset rniyazov:1
CREATE TABLE IF NOT EXISTS type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(16) UNIQUE NOT NULL
);

--changeset rniyazov:2
CREATE TABLE IF NOT EXISTS unit
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(16) UNIQUE NOT NULL
);

--changeset rniyazov:3
CREATE TABLE IF NOT EXISTS sensor
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(30) UNIQUE       NOT NULL,
    model       VARCHAR(15)              NOT NULL,
    range_from  INT                      NOT NULL,
    range_to    INT                      NOT NULL,
    type_id     INT REFERENCES type (id) NOT NULL,
    unit_id     INT REFERENCES unit (id),
    location    VARCHAR(40),
    description VARCHAR(200),
    create_date DATE                     NOT NULL
);

--changeset rniyazov:4
CREATE TABLE IF NOT EXISTS users
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(32) UNIQUE NOT NULL,
    password  VARCHAR(128)       NOT NULL,
    firstname VARCHAR(32)        NOT NULL,
    lastname  VARCHAR(32)        NOT NULL,
    role      VARCHAR(16)        NOT NULL
);