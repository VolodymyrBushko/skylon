create table if not exists usr
(
    id            bigint                        not null,
    first_name    varchar(25)                   not null,
    last_name     varchar(25)                   not null,
    login         varchar(50)                   not null,
    email         varchar(100)                  not null,
    description   varchar(255),
    image         varchar(255),
    password      varchar(510)                  not null,
    age           integer                       not null,
    created_at    timestamp without time zone   not null,
    updated_at    timestamp without time zone   not null,

    constraint PK_USR primary key (id),

    constraint UQ_USR_LOGIN unique (login),
    constraint UQ_USR_EMAIL unique (email),

    check (age >= 0)
);
