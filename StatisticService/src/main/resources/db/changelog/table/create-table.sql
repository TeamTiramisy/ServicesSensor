--liquibase formatted sql

--changeset rniyazov:1
CREATE TABLE IF NOT EXISTS statistic
(
    id   SERIAL PRIMARY KEY,
    data VARCHAR(1024) NOT NULL
);