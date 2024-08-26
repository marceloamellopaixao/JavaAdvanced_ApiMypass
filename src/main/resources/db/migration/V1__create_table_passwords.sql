CREATE TABLE passwords (
    id bigint NOT NULL auto_increment,
    url varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    primary key (id)
);
