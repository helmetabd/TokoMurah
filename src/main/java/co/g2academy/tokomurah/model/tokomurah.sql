create table t_product(
    id          integer auto_increment,
    name        varchar(100),
    description varchar(255),
    price       integer      not null,
    stock       integer      not null,
    primary key(id)
);
