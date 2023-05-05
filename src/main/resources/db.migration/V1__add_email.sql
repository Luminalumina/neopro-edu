create table clients (
    id bigserial not null,
    name varchar(255), primary key (id));

ALTER TABLE clients ADD COLUMN email varchar(255);
