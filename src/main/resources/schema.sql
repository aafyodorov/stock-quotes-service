create table if not exists users
(
    user_id        bigint not null primary key,
    cur_subscribes integer,
    max_subscribes integer
);

create table if not exists company
(
    symbol        varchar(10),
    company_name  varchar(128),
    exchange_id   integer,
    industry      varchar(128),
    website       varchar(128),
    security_name varchar(128),
    issue_type    varchar(10),
    sector        varchar(128),
    country       varchar(3),
    primary key (symbol)
);

create table if not exists expectation
(
    user_id   bigint not null,
    symbol    varchar(10),
    exp_price decimal(10, 2),
    primary key (user_id, symbol),
    foreign key (user_id) references users (user_id),
    foreign key (symbol) references company (symbol)
);

create table if not exists exchange
(
    exchange_id   serial primary key,
    exchange_name varchar(128)
);

