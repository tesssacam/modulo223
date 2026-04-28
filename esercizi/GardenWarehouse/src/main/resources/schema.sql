CREATE SEQUENCE IF NOT EXISTS item_seq START WITH 1 INCREMENT BY 1;

create table if not exists Item
(
    id BIGINT PRIMARY KEY,
    code varchar(255),
    type varchar(50),
    name varchar(50),
    price double,
    itemcount int
    );