create table users (
                       user_id         bigint primary key ,
                       cur_subscribes  int,
                       max_subscribes  int
);

create table symbol(
                       symbol_id       serial,
                       symbol          varchar(10),
                       exchange        varchar(10),
                       comp_name       varchar(128),
                       type            varchar(10),
                       gen_date        date,
                       iex_id          varchar(32),
                       region          varchar(10),
                       currency        varchar(3),
                       is_enabled      bool,
                       primary key (symbol_id, exchange)
);

create table expectation(
                            user_id         bigint,
                            symbol_id       bigint,
                            exchange        varchar(10),
                            exp_price       decimal(10,2),
                            primary key (user_id, symbol_id, exchange),
                            foreign key (user_id) references users(user_id),
                            foreign key (symbol_id, exchange) references symbol(symbol_id, exchange)
);