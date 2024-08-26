create table if not exists users (
    id bigint not null auto_increment,
    username varchar(255),
    password varchar(255),
    primary key (id)
    );