--liquibase formatted sql
--changeset tregubchenko:initial

create table if not exists product (
    id          UUID,
    name        varchar,
    price       integer,
    count       integer,
    version     bigint      default 0,
    constraint pk_product primary key (id)
);

--rollback drop table if not exists product cascade;