CREATE SEQUENCE IF NOT EXISTS customer_seq START WITH 1 INCREMENT BY 1;

create table if not exists Customer
(
    id BIGINT PRIMARY KEY,
    name    varchar(50) not null,
    surname varchar(50) not null,
    age     varchar(50) not null,
    city    varchar(50),
    ccnumber varchar(255),
    ccexpiration varchar(50),
    cccvv int
    );