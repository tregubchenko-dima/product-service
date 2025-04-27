--liquibase formatted sql
--changeset tregubchenko:initial

create table if not exists template (
    id          UUID,
    name        varchar,
    value       integer,
    status      varchar,
    date        date,
    version     bigint      default 0,
    constraint pk_template primary key (id)
);

--rollback drop table if not exists template cascade;