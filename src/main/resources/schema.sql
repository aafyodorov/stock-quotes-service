create table users
(
    user_id        bigint primary key,
    cur_subscribes int,
    max_subscribes int
);

create table company
(
    symbol        varchar(10),
    company_name  varchar(128),
    exchange      varchar(10),
    industry      varchar(128),
    website       varchar(128),
    security_name varchar(128),
    issueType     varchar(10),
    sector        varchar(128),
    country       varchar(3),
    primary key (symbol)
);

create table expectation
(
    user_id   bigint,
    symbol    varchar(10),
    exp_price decimal(10, 2),
    primary key (user_id, symbol),
    foreign key (user_id) references users (user_id),
    foreign key (symbol) references company (symbol)
);
